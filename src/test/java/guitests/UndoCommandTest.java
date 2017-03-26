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
        assertTrue(personListPanel.isListMatching(td.getTypicalTasks()));
        commandBox.runCommand("clear");
        assertListSize(0);
        assertUndoSuccess();

        //undo delete command
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        TestTask personToDelete = currentList[targetIndex - 1]; // -1 as array uses zero indexing
        TestTask[] expectedRemainder = TestUtil.removeTaskFromList(currentList, targetIndex);
        commandBox.runCommand("delete " + targetIndex);
        //confirm the list now contains all previous persons except the deleted person
        assertTrue(personListPanel.isListMatching(expectedRemainder));
        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_DELETE_TASK_SUCCESS, personToDelete));
        assertUndoSuccess();

        //undo add command
        //add one person
        TestTask personToAdd = td.hoon;
        commandBox.runCommand(personToAdd.getAddCommand());
        //confirm the new card contains the right data
        TaskCardHandle addedCard = personListPanel.navigateToTask(personToAdd.getName().fullName);
        assertMatching(personToAdd, addedCard);
        //confirm the list now contains all previous persons plus the new person
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, personToAdd);
        assertTrue(personListPanel.isListMatching(expectedList));
        assertUndoSuccess();


        //undo edit command....working in progress...
    }
    private void assertUndoSuccess() {
        commandBox.runCommand("undo");
        assertTrue(personListPanel.isListMatching(td.getTypicalTasks()));
    }
}
