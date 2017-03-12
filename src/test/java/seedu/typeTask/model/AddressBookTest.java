package seedu.typeTask.model;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.typeTask.model.ReadOnlyTaskManager;
import seedu.typeTask.model.TaskManager;
import seedu.typeTask.model.task.ReadOnlyTask;
import seedu.typeTask.testutil.TypicalTestTasks;

public class AddressBookTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final TaskManager taskManager = new TaskManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskManager.getTaskList());
    }

    @Test
    public void resetData_null_throwsAssertionError() {
        thrown.expect(AssertionError.class);
        taskManager.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyTaskManager_replacesData() {
        TaskManager newData = new TypicalTestTasks().getTypicalTaskManager();
        taskManager.resetData(newData);
        assertEquals(newData, taskManager);
    }




    /**
     * A stub ReadOnlyTaskManager whose task lists can violate interface constraints.
     */
    private static class TaskManagerStub implements ReadOnlyTaskManager {
        private final ObservableList<ReadOnlyTask> tasks = FXCollections.observableArrayList();

        TaskManagerStub(Collection<? extends ReadOnlyTask> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<ReadOnlyTask> getTaskList() {
            return tasks;
        }

    }

}
