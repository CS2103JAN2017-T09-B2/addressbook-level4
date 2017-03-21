package typetask.model.task;

import java.util.Objects;

import typetask.commons.util.CollectionUtil;

/**
 * Represents a Task in TypeTask.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private DueDate date;
    private Time time;
    private boolean isCompleted;

    /**
     * Every field must be present and not null.
     */

    public Task(Name name) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
    }
    public Task(Name name, DueDate date, Time time) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.date = date;
        this.time = time;
    }

    //@@author A0144902L
    public Task(Name name, DueDate date, Time time, boolean isCompleted) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.date = date;
        this.time = time;
        this.isCompleted = isCompleted;
    }

    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
       this(source.getName(), source.getDate(), source.getTime());
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    public void setDate(DueDate date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    //@@author A0144902L
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
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

    //@@author A0144902L
    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;
        this.setName(replacement.getName());
        this.setDate(replacement.getDate());
        this.setTime(replacement.getTime());
    }

    /**
     * @@author A0144902L
     * Marks this task as completed {boolean is set TRUE}.
     */
    public void markComplete(ReadOnlyTask complete) {
        assert complete != null;
        this.setIsCompleted(true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                        && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
