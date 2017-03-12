package typetask.model.task;

import java.util.Vector;

public class Priority {

    private boolean priority;

    public static final String MESSAGE_PRIORITY_CONSTRAINTS =
            "Priority should be either in true or false.";
    
    //priorityList stores the list of priority tasks
    private static Vector<Task> priorityList;
    private Task task;

    /**
     * Tasks added without priority will be false by default.
     * @param task
     */
    public Priority (Task task) {
        this.priority = false;
        this.task = task;
    }

    public Priority(boolean priority, Task task) {
        this.task = task;
        addPriority();
    }

    public Priority(String priority, Task task) {
        String amended = priority.substring(2).trim();

        this.task = task;
        
        if (amended.toLowerCase().compareTo("true") == 0) {
            addPriority();
        } else {
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
    
    public void removePriority() {
      priorityList.remove(this.task);
      this.priority = false;
    }
    
    public void addPriority() {
      priorityList.addElement(task);
      this.priority = true;
    }


    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
