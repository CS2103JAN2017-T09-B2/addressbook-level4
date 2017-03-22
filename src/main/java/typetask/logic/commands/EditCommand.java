package typetask.logic.commands;

import java.util.List;
import java.util.Optional;

import typetask.commons.core.Messages;
import typetask.commons.util.CollectionUtil;
import typetask.logic.commands.exceptions.CommandException;
import typetask.model.task.DueDate;
import typetask.model.task.EventTask;
import typetask.model.task.Name;
import typetask.model.task.ReadOnlyTask;
import typetask.model.task.Task;
import typetask.model.task.Time;

/**
 * Edits the details of an existing task in the TaskManager.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the last task listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [NAME] [d/DATE] [t/TIME] [p/PRIORITY ]\n"
            + "Example: " + COMMAND_WORD + " 1 p/91234567 t/11:25pm p/1";
    public static final String EVENT_MESSAGE_USAGE = COMMAND_WORD
            + "Event need only [from:DATE] [to:DATE].";
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited TASK: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final int filteredTaskListIndex;
    private final EditTaskDescriptor editTaskDescriptor;
    private int taskType = 0;
    private final int otherTasks = 1;

    /**
     * @param filteredTaskListIndex the index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(int filteredTaskListIndex, EditTaskDescriptor editTaskDescriptor, int taskType) {
        assert filteredTaskListIndex > 0;
        assert editTaskDescriptor != null;
        this.taskType = taskType;
        // converts filteredTaskListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;

        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (taskType == otherTasks) {
            List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

            if (filteredTaskListIndex >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);
            Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
            model.storeTaskManager(COMMAND_WORD);
            model.updateTask(filteredTaskListIndex, editedTask);
            model.updateFilteredListToShowAll();
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToEdit));
        } else {
            List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

            if (filteredTaskListIndex >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
            }

            ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);
            EventTask editedTask = createEditedEvent(taskToEdit, editTaskDescriptor);
            model.storeTaskManager(COMMAND_WORD);
            model.updateTask(filteredTaskListIndex, editedTask);
            model.updateFilteredListToShowAll();
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToEdit));
        }
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(ReadOnlyTask taskToEdit,
                                             EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElseGet(taskToEdit::getName);
        DueDate updatedDate = editTaskDescriptor.getDate().orElseGet(taskToEdit::getDate);
        Time updatedTime = editTaskDescriptor.getTime().orElseGet(taskToEdit::getTime);

        return new Task(updatedName, updatedDate, updatedTime);
    }
    private static EventTask createEditedEvent(ReadOnlyTask taskToEdit,
                                                    EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;
        
        EventTask eventTask = (EventTask) taskToEdit;
        Name updatedName = editTaskDescriptor.getName().orElseGet(eventTask::getName);
        DueDate updatedStartDate = editTaskDescriptor.getStartDate().orElseGet(eventTask::getStartDateTime);
        DueDate updatedEndDate = editTaskDescriptor.getEndDate().orElseGet(eventTask::getEndDateTime);

        return new EventTask(updatedName, updatedStartDate, updatedEndDate,false);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Optional<Name> name = Optional.empty();
        private Optional<DueDate> date = Optional.empty();
        private Optional<Time> time = Optional.empty();
        private Optional<DueDate> startDate = Optional.empty();
        private Optional<DueDate> endDate = Optional.empty();

        public EditTaskDescriptor() {}

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.name = toCopy.getName();
            this.date = toCopy.getDate();
            this.time = toCopy.getTime();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyPresent(this.name, this.date, this.time);
        }

        public void setName(Optional<Name> name) {
            assert name != null;
            this.name = name;
        }

        public Optional<Name> getName() {
            return name;
        }

        public Optional<Time> getTime() {
            return time;
        }

        public void setTime(Optional<Time> time) {
            assert time != null;
            this.time = time;
        }
        public void setDate(Optional<DueDate> date) {
            assert date != null;
            this.date = date;
        }

        public Optional<DueDate> getDate() {
            return date;
        }

        public void setStartDate(Optional<DueDate> startDate) {
            assert startDate != null;
            this.startDate = startDate;
        }
        public void setEndDate(Optional<DueDate> endDate) {
            assert endDate != null;
            this.endDate = endDate;
        }
        public Optional<DueDate> getStartDate() {
            return startDate;
        }
        public Optional<DueDate> getEndDate() {
            return endDate;
        }
    }
}
