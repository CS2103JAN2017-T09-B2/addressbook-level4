package seedu.typeTask.model;


import javafx.collections.ObservableList;
import seedu.typeTask.model.task.ReadOnlyTask;

/**
 * Unmodifiable view of a TaskManager
 */
public interface ReadOnlyTaskManager {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<ReadOnlyTask> getTaskList();

}
