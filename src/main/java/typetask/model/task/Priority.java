package typetask.model.task;

import java.util.Vector;

public class Priority {

    private boolean priority;

    public static final String MESSAGE_PRIORITY_CONSTRAINTS =
            "Priority should be either in high or low.";
    
    //priorityList stores the list of priority tasks
    private static Vector<Task> priorityList;
    private Task task;

    /**
     * Tasks added without priority will be in low priority by default.
     * @param task
     */
    public Priority (Task task) {
        this.priority = false;
        this.task = task;
    }

    public Priority(String priority, Task task) {
        String amended = priority.substring(2).trim();

        this.task = task;

        if (amended.toLowerCase().compareTo("high") == 0) {
            setHighPriority();
        } else if (amended.toLowerCase().compareTo("low") == 0){
            this.priority = false;
        }

    }

    /**
     * Returns the priority of the task
     * @return the boolean priority of the priority
     */
    public boolean getPriority() {
        return this.priority;
    }
    
    public void setLowPriority() {
      priorityList.remove(this.task);
      this.priority = false;
    }
    
    public void setHighPriority() {
      priorityList.addElement(task);
      this.priority = true;
    }


    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
