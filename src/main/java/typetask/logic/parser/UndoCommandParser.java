package typetask.logic.parser;

import typetask.logic.commands.Command;
import typetask.logic.commands.UndoCommand;
//@@author A0139926R
public class UndoCommandParser {

    public Command parse(String args) {
        return new UndoCommand();
    }
}
