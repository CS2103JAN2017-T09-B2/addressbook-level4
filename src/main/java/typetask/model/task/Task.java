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
    private boolean isComplete;

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
    
    public Task(Name name, DueDate date, Time time, boolean isComplete) {
        assert !CollectionUtil.isAnyNull(name);
        this.name = name;
        this.date = date;
        this.time = time;
        this.isComplete = isComplete;
    }
    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {
       this(source.getName(), source.getDate(), source.getTime(), source.getIsCompleted());
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
    
    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
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
    
    public boolean getIsCompleted() {
        return isComplete;
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
    
    public void markComplete(ReadOnlyTask complete) {
        assert complete != null;
        this.setIsComplete(true);
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
