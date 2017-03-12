package seedu.typeTask.model.util;

import seedu.typeTask.commons.exceptions.IllegalValueException;
import seedu.typeTask.model.ReadOnlyTaskManager;
import seedu.typeTask.model.TaskManager;
import seedu.typeTask.model.task.Name;
import seedu.typeTask.model.task.Task;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new Name("Eat Breakfast")),
                new Task(new Name("Eat Lunch")),
                new Task(new Name("Eat Dinner")),
                new Task(new Name("Read Harry Potter book 3")),
                new Task(new Name("Wash shoe")),
                new Task(new Name("Sweep floor")),
            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        TaskManager sampleAB = new TaskManager();
        for (Task sampleTask : getSampleTasks()) {
            sampleAB.addTask(sampleTask);
        }
        return sampleAB;
    }
}
