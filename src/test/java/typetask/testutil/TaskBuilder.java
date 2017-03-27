package typetask.testutil;

import typetask.commons.exceptions.IllegalValueException;
import typetask.model.task.DueDate;
import typetask.model.task.Name;
import typetask.model.task.Time;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(TestTask taskToCopy) {
        this.task = new TestTask(taskToCopy);
    }

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }


    public TaskBuilder withTime(String time) throws IllegalValueException {
        this.task.setTime(new Time(time));
        return this;
    }

    public TaskBuilder withDate(String date) throws IllegalValueException {
        this.task.setDate(new DueDate(date));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
