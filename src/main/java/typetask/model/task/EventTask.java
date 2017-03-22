package typetask.model.task;


import java.util.Objects;


public class EventTask extends Task {

    private Name name;
    private DueDate startDateTime;
    private DueDate endDateTime;
    private boolean isCompleted;

    public EventTask(Name name, DueDate startDateTime, DueDate endDateTime,boolean isCompleted) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public EventTask(ReadOnlyTask source) {
        super(source);
        if (source instanceof EventTask) {
            startDateTime = ((EventTask) source).getStartDateTime();
            endDateTime = ((EventTask) source).getEndDateTime();
            isCompleted = ((EventTask) source).getIsCompleted();
        }
    }

    public DueDate getEndDateTime() {
        return endDateTime;
    }

    public DueDate getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(DueDate startDateTime) {
        this.startDateTime = startDateTime;
    }
    public void setEndDateTime(DueDate endDateTime) {
        this.endDateTime = endDateTime;
    }
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReadOnlyTask && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
               .append(" StartDate: ")
               .append(getStartDateTime())
               .append(" EndDate: ")
               .append(getEndDateTime());
        return builder.toString();
    }
}
