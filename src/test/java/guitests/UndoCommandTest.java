package guitests;
import static org.junit.Assert.assertTrue;
import static typetask.logic.commands.DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import typetask.testutil.TestTask;
import typetask.testutil.TestUtil;


public class UndoCommandTest extends TypeTaskGuiTest {

    @Test
    public void undo() {
        //undo clear command
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
        commandBox.runCommand("clear");
        assertListSize(0);
        assertUndoSuccess();

        //undo delete command
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        TestTask taskToDelete = currentList[targetIndex - 1]; // -1 as array uses zero indexing
        TestTask[] expectedRemainder = TestUtil.removeTaskFromList(currentList, targetIndex);
        commandBox.runCommand("delete " + targetIndex);
        //confirm the list now contains all previous tasks except the deleted task
        assertTrue(taskListPanel.isListMatching(expectedRemainder));
        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
        assertUndoSuccess();

        //undo add command
        //add one task
        TestTask taskToAdd = td.hoon;
        commandBox.runCommand(taskToAdd.getAddCommand());
        //confirm the new card contains the right data
        TaskCardHandle addedCard = taskListPanel.navigateToTask(taskToAdd.getName().fullName);
        assertMatching(taskToAdd, addedCard);
        //confirm the list now contains all previous tasks plus the new task
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, taskToAdd);
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertUndoSuccess();


        //undo edit command....working in progress...
    }
    private void assertUndoSuccess() {
        commandBox.runCommand("undo");
        assertTrue(taskListPanel.isListMatching(td.getTypicalTasks()));
    }
}
