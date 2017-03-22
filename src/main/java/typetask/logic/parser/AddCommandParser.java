package typetask.logic.parser;

import static typetask.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static typetask.logic.parser.CliSyntax.PREFIX_DATE;
import static typetask.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static typetask.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static typetask.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.NoSuchElementException;

import typetask.commons.exceptions.IllegalValueException;
import typetask.logic.commands.AddCommand;
import typetask.logic.commands.Command;
import typetask.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser {
    private final int floatingTask = 0;
    private final int deadlineTaskWithTime = 1;
    private final int deadlineTaskWithoutTime = 2;
    private final int deadlineTaskWithTimeNoDate = 3;
    private final int eventTask = 4;
    private final int incorrectEventFormat = 5;
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     */
    public Command parse(String args) {
        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(PREFIX_DATE, PREFIX_TIME, PREFIX_STARTDATE, PREFIX_ENDDATE);
        argsTokenizer.tokenize(args);
        try {
            return addTask(argsTokenizer);

        } catch (NoSuchElementException nsee) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    private Command addTask(ArgumentTokenizer argsTokenizer) throws IllegalValueException {
        if (checkTaskType(argsTokenizer) == floatingTask) {
            return new AddCommand(
                    argsTokenizer.getPreamble().get()
            );
        } else if (checkTaskType(argsTokenizer) == deadlineTaskWithTime) {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    argsTokenizer.getValue(PREFIX_DATE).get(),
                    argsTokenizer.getValue(PREFIX_TIME).get()
            );
        } else if (checkTaskType(argsTokenizer) == deadlineTaskWithTimeNoDate) {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    argsTokenizer.getValue(PREFIX_TIME).get(),
                    deadlineTaskWithTimeNoDate
                    );
        } else if (checkTaskType(argsTokenizer) == eventTask) {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    argsTokenizer.getValue(PREFIX_STARTDATE).get(),
                    argsTokenizer.getValue(PREFIX_ENDDATE).get(),
                    eventTask
                    );
        } else if (checkTaskType(argsTokenizer) == incorrectEventFormat) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.EVENT_MESSAGE_USAGE));
        } else {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    argsTokenizer.getValue(PREFIX_DATE).get()
                    );
        }
    }

    public int checkTaskType(ArgumentTokenizer argsTokenizer) {
        if (argsTokenizer.getValue(PREFIX_DATE).isPresent() &&
                argsTokenizer.getValue(PREFIX_TIME).isPresent()) {
            return deadlineTaskWithTime;
        } else if (argsTokenizer.getValue(PREFIX_DATE).isPresent()) {
            return deadlineTaskWithoutTime;
        } else if (argsTokenizer.getValue(PREFIX_TIME).isPresent()) {
            return deadlineTaskWithTimeNoDate;
        } else if (argsTokenizer.getValue(PREFIX_STARTDATE).isPresent() &&
                argsTokenizer.getValue(PREFIX_ENDDATE).isPresent()) {
            return eventTask;
        } else if (argsTokenizer.getValue(PREFIX_STARTDATE).isPresent() &&
                !argsTokenizer.getValue(PREFIX_ENDDATE).isPresent()) {
            return incorrectEventFormat;
        } else if (!argsTokenizer.getValue(PREFIX_STARTDATE).isPresent() &&
                argsTokenizer.getValue(PREFIX_ENDDATE).isPresent()) {
            return incorrectEventFormat;
        } else {
            return floatingTask;
        }
    }
}
