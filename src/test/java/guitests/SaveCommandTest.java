package guitests;

import org.junit.Test;

import typetask.commons.core.Messages;
import typetask.logic.commands.SaveCommand;

//@@author A0144902L
public class SaveCommandTest extends TypeTaskGuiTest {

    @Test
    public void save() {

        //valid path
        commandBox.runCommand("save c:\\desktop\\typetask");
        assertResultMessage(SaveCommand.MESSAGE_SUCCESS);

        //invalid command
        commandBox.runCommand("savec:\\desktop\\typetask");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }
}
