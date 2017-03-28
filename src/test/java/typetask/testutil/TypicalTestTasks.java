package typetask.testutil;

import typetask.commons.exceptions.IllegalValueException;
import typetask.model.TaskManager;
import typetask.model.task.Task;

/**
 *
 */
public class TypicalTestTasks {

    public TestTask alice, benson, carl, daniel, elle, fiona, george, hoon, ida;

    public TypicalTestTasks() {
        try {
            alice = new TaskBuilder().withName("Alice Pauline").withDate("").withEndDate("")
                    .withCompleted(false).build();
            benson = new TaskBuilder().withName("Benson Meier").withDate("").withEndDate("")
                    .withCompleted(false).build();
            carl = new TaskBuilder().withName("Carl Kurz").withDate("").withEndDate("")
                    .withCompleted(false).build();
            daniel = new TaskBuilder().withName("Daniel Meier").withDate("").withEndDate("")
                    .withCompleted(false).build();
            elle = new TaskBuilder().withName("Elle Meyer").withDate("").withEndDate("")
                    .withCompleted(false).build();
            fiona = new TaskBuilder().withName("Fiona Kunz").withDate("").withEndDate("")
                    .withCompleted(false).build();
            george = new TaskBuilder().withName("George Best").withDate("").withEndDate("")
                    .withCompleted(false).build();

            // Manually added
            hoon = new TaskBuilder().withName("Hoon Meier").withDate("").withEndDate("")
                    .withCompleted(false).build();
            ida = new TaskBuilder().withName("Ida Mueller").withDate("").withEndDate("")
                    .withCompleted(false).build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTaskManagerWithSampleData(TaskManager tm) {
        for (TestTask task : new TypicalTestTasks().getTypicalTasks()) {
            tm.addTask(new Task(task));
        }
    }

    public TestTask[] getTypicalTasks() {
        return new TestTask[]{alice, benson, carl, daniel, elle, fiona, george};
    }

    public TaskManager getTypicalTaskManager() {
        TaskManager tm = new TaskManager();
        loadTaskManagerWithSampleData(tm);
        return tm;
    }
}
