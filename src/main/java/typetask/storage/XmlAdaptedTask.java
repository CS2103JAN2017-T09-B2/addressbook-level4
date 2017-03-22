package typetask.storage;



import javax.xml.bind.annotation.XmlElement;

import typetask.commons.exceptions.IllegalValueException;
import typetask.model.task.DueDate;
import typetask.model.task.EventTask;
import typetask.model.task.Name;
import typetask.model.task.ReadOnlyTask;
import typetask.model.task.Task;
import typetask.model.task.Time;
/**
 * JAXB-friendly version of the Task.
 */
public class XmlAdaptedTask {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String date;
    @XmlElement(required = true)
    private String time;
    @XmlElement(required = true)
    private Boolean isCompleted;

    @XmlElement(required = true)
    private String startDate;
    
    @XmlElement(required = true)
    private String endDate;


    /**
     * Constructs an XmlAdaptedTask.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedTask() {}


    /**
     * Converts a given Task into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedTask
     */
    public XmlAdaptedTask(ReadOnlyTask source) {
        name = source.getName().fullName;
        if (source instanceof EventTask) {
            EventTask eventTask = (EventTask) source;
            startDate = eventTask.getStartDateTime().value;
            endDate = eventTask.getEndDateTime().value;
            isCompleted = eventTask.getIsCompleted();
        } else {
            date = source.getDate().value;
            time = source.getTime().value;
            isCompleted = source.getIsCompleted();
        }

    }

    /**
     * Converts this jaxb-friendly adapted task object into the model's Task object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task
     */
    public Task toModelType() throws IllegalValueException {
        Task task;
        final Name name = new Name(this.name);
        boolean isCompleted = false;
        if (this.isCompleted != null) {
            isCompleted = this.isCompleted;
        }
        if (startDate != null && endDate != null) {
            final DueDate startDate = new DueDate(this.startDate);
            final DueDate endDate = new DueDate(this.endDate);
            task = new EventTask(name, startDate, endDate,isCompleted);
        } else {
            final DueDate date = new DueDate(this.date);
            final Time time = new Time(this.time);
            task = new Task(name, date, time, isCompleted);
        }
        return task;
    }
}
