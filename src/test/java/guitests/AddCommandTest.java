package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import typetask.commons.core.Messages;
import typetask.testutil.TestTask;
import typetask.testutil.TestUtil;

public class AddCommandTest extends TypeTaskGuiTest {

    @Test
    public void add() {
        //add one task
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToAdd = td.hoon;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);

        //add another person
        taskToAdd = td.ida;
        assertAddSuccess(taskToAdd, currentList);
        currentList = TestUtil.addTasksToList(currentList, taskToAdd);


        //add to empty list
        commandBox.runCommand("clear");
        assertAddSuccess(td.alice);

        //invalid command
        commandBox.runCommand("adds Johnny");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertAddSuccess(TestTask personToAdd, TestTask... currentList) {
        commandBox.runCommand(personToAdd.getAddCommand());

        //confirm the new card contains the right data
        TaskCardHandle addedCard = personListPanel.navigateToTask(personToAdd.getName().fullName);
        assertMatching(personToAdd, addedCard);

        //confirm the list now contains all previous persons plus the new person
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, personToAdd);
        assertTrue(personListPanel.isListMatching(expectedList));
    }

}
