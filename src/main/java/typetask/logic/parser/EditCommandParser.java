package typetask.logic.parser;

import static typetask.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static typetask.logic.parser.CliSyntax.PREFIX_DATE;
import static typetask.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static typetask.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static typetask.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.Optional;

import typetask.commons.exceptions.IllegalValueException;
import typetask.logic.commands.Command;
import typetask.logic.commands.EditCommand;
import typetask.logic.commands.EditCommand.EditTaskDescriptor;
import typetask.logic.commands.IncorrectCommand;


/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser {

    private int currentType = 0;
    private final int noType = 0;
    private final int otherTasks = 1;
    private final int eventTask = 2;
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     */
    public Command parse(String args) {
        assert args != null;
        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(PREFIX_DATE, PREFIX_TIME, PREFIX_STARTDATE, PREFIX_ENDDATE);
        argsTokenizer.tokenize(args);
        List<Optional<String>> preambleFields = ParserUtil.splitPreamble(argsTokenizer.getPreamble().orElse(""), 2);

        Optional<Integer> index = preambleFields.get(0).flatMap(ParserUtil::parseIndex);
        if (!index.isPresent()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        try {
            editTaskDescriptor.setName(ParserUtil.parseName(preambleFields.get(1)));
            if (argsTokenizer.getValue(PREFIX_DATE).isPresent()) {
                editTaskDescriptor.setDate(ParserUtil.parseDate(argsTokenizer.getValue(PREFIX_DATE)));
                currentType = otherTasks;
            }
            if (argsTokenizer.getValue(PREFIX_TIME).isPresent()) {
                editTaskDescriptor.setTime(ParserUtil.parseTime(argsTokenizer.getValue(PREFIX_TIME)));
                currentType = otherTasks;
            }
            if (argsTokenizer.getValue(PREFIX_STARTDATE).isPresent()) {
                if (currentType != noType) {
                    return new IncorrectCommand(EditCommand.EVENT_MESSAGE_USAGE);
                } else {
                    editTaskDescriptor.setStartDate(ParserUtil.parseDate(argsTokenizer.getValue(PREFIX_STARTDATE)));
                    currentType = eventTask;
                }
            }
            if (argsTokenizer.getValue(PREFIX_STARTDATE).isPresent()) {
                if (currentType != noType || currentType != eventTask) {
                    return new IncorrectCommand(EditCommand.EVENT_MESSAGE_USAGE);
                } else {
                    editTaskDescriptor.setEndDate(ParserUtil.parseDate(argsTokenizer.getValue(PREFIX_ENDDATE)));
                    currentType = eventTask;
                }
            }

        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }

        if (!editTaskDescriptor.isAnyFieldEdited()) {
            return new IncorrectCommand(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index.get(), editTaskDescriptor, currentType);
    }


}
