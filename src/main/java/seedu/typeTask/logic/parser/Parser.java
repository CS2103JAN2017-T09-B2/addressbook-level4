package seedu.typeTask.logic.parser;

import static seedu.typeTask.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.typeTask.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.typeTask.logic.commands.AddCommand;
import seedu.typeTask.logic.commands.ClearCommand;
import seedu.typeTask.logic.commands.Command;
import seedu.typeTask.logic.commands.DeleteCommand;
import seedu.typeTask.logic.commands.EditCommand;
import seedu.typeTask.logic.commands.ExitCommand;
import seedu.typeTask.logic.commands.FindCommand;
import seedu.typeTask.logic.commands.HelpCommand;
import seedu.typeTask.logic.commands.IncorrectCommand;
import seedu.typeTask.logic.commands.ListCommand;
import seedu.typeTask.logic.commands.SelectCommand;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case AddCommand.COMMAND_WORD2:
            return new AddCommandParser().parse(arguments);
        case AddCommand.COMMAND_WORD3:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD2:
            return new DeleteCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD3:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD2:
            return new FindCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD3:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            return new IncorrectCommand(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
