package typetask.testutil;

import typetask.commons.exceptions.IllegalValueException;
import typetask.model.task.DueDate;
import typetask.model.task.Name;

/**
 *
 */
public class TaskBuilder {

    private TestTask task;

    public TaskBuilder() {
        this.task = new TestTask();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public TaskBuilder(TestTask personToCopy) {
        this.task = new TestTask(personToCopy);
    }

    public TaskBuilder withName(String name) throws IllegalValueException {
        this.task.setName(new Name(name));
        return this;
    }
    public TaskBuilder withCompleted(boolean isCompleted) {
        this.task.setIsCompleted(isCompleted);
        return this;
    }

    public TaskBuilder withDate(String date) throws IllegalValueException {
        this.task.setDate(new DueDate(date));
        return this;
    }
    public TaskBuilder withEndDate(String date) throws IllegalValueException {
        this.task.setEndDate(new DueDate(date));
        return this;
    }

    public TestTask build() {
        return this.task;
    }

}
