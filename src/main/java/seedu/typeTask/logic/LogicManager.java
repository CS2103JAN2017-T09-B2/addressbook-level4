package seedu.typeTask.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.typeTask.commons.core.ComponentManager;
import seedu.typeTask.commons.core.LogsCenter;
import seedu.typeTask.logic.commands.Command;
import seedu.typeTask.logic.commands.CommandResult;
import seedu.typeTask.logic.commands.exceptions.CommandException;
import seedu.typeTask.logic.parser.Parser;
import seedu.typeTask.model.Model;
import seedu.typeTask.model.task.ReadOnlyTask;
import seedu.typeTask.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new Parser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        command.setData(model);
        return command.execute();
    }

    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }
}
