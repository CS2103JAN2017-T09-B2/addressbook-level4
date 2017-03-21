package guitests;

import static org.junit.Assert.assertTrue;
import static typetask.logic.commands.DoneCommand.MESSAGE_COMPLETE_TASK_SUCCESS;

import org.junit.Test;

import typetask.testutil.TestTask;
import typetask.testutil.TestUtil;

//@@author A0144902L
public class DoneCommandTest extends AddressBookGuiTest {

    @Test
    public void done() {

        //Marks the first task in the list as complete
        TestTask[] currentList = td.getTypicalTasks();
        int targetIndex = 1;
        currentList[targetIndex - 1].setIsCompleted(true);
        assertDoneSuccess(targetIndex, currentList);

        //Marks the last task in the list as complete
        targetIndex = currentList.length;
        currentList[targetIndex - 1].setIsCompleted(true);
        assertDoneSuccess(targetIndex, currentList);

        //Marks the middle task in the list as complete
        currentList = TestUtil.removePersonFromList(currentList, targetIndex);
        targetIndex = currentList.length / 2;
        currentList[targetIndex - 1].setIsCompleted(true);
        assertDoneSuccess(targetIndex, currentList);

        //invalid index
        commandBox.runCommand("done " + currentList.length + 1);
        assertResultMessage("The task index provided is invalid");

    }

    /**
     * Runs the done command to mark the task as done at specified index and confirms the result is correct.
     * @param targetIndexOneIndexed e.g. index 1 to delete the first person in the list,
     * @param currentList A copy of the current list of persons (before deletion).
     */
    private void assertDoneSuccess(int targetIndexOneIndexed, final TestTask[] currentList) {
        TestTask TaskToComplete = currentList[targetIndexOneIndexed - 1]; // -1 as array uses zero indexing
        boolean isTestCompleted = TaskToComplete.getIsCompleted();

        commandBox.runCommand("done " + targetIndexOneIndexed);

        //confirm the task is completed
        assertTrue(isTestCompleted);

        //confirm the result message is correct
        assertResultMessage(String.format(MESSAGE_COMPLETE_TASK_SUCCESS, TaskToComplete));
    }

}
