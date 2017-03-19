package typetask.testutil;

import typetask.model.task.DueDate;
import typetask.model.task.Name;
import typetask.model.task.ReadOnlyTask;
import typetask.model.task.Time;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private DueDate date;
    private Time time;
    private boolean isComplete;

    public TestTask() {
    }

    /**
     * Creates a copy of {@code taskToCopy}.
     */
    public TestTask(TestTask taskToCopy) {
        this.name = taskToCopy.getName();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setDate(DueDate date) {
        this.date = date;
    }
    
    public boolean setIsCompleted() {
        return isComplete;
    }


    @Override
    public Name getName() {
        return name;
    }
    @Override
    public DueDate getDate() {
        return date;
    }
    @Override
    public Time getTime() {
        return time;
    }
    @Override
    public boolean getIsCompleted() {
        return isComplete;
    }
    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        return sb.toString();
    }
}
