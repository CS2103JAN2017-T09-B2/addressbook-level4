package seedu.typeTask.testutil;

import seedu.typeTask.model.task.Date;
import seedu.typeTask.model.task.Name;
import seedu.typeTask.model.task.ReadOnlyTask;
import seedu.typeTask.model.task.Time;

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Date date;
    private Time time;

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

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public Name getName() {
        return name;
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
