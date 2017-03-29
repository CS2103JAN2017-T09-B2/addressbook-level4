package typetask.logic.commands;


//@@author A0139926R
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String COMMAND_WORD_SHORT = "r";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redo a command that was previously undone." + "Example: "
            + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Redo previous command.";
    public static final String MESSAGE_FAILURE = "An error occured when running redo command.";
    public static final String MESSAGE_NOTHING_TO_REDO = "There is nothing to redo.";

    private static final int STATUS_EMPTY_HISTORY = 0;
    private static final int STATUS_ERROR_HISTORY = -1;

    @Override
    public CommandResult execute() {
        switch (model.revertTaskManager()) {
        case STATUS_ERROR_HISTORY:
            return new CommandResult(MESSAGE_FAILURE);
        case STATUS_EMPTY_HISTORY:
            return new CommandResult(MESSAGE_FAILURE);
        default:
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}