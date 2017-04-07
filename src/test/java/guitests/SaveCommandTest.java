package guitests;

import org.junit.Test;

import typetask.commons.core.Messages;
import typetask.logic.commands.SaveCommand;

//@@author A0144902L
public class SaveCommandTest extends AddressBookGuiTest{

    @Test
    public void save() {
        //invalid filepath
        commandBox.runCommand("save c:\\desktop\\^*+#");
        assertResultMessage(Messages.MESSAGE_INVALID_PATH);

        //valid filepath
        commandBox.runCommand("save c:\\desktop\\typetask");
        assertResultMessage(SaveCommand.MESSAGE_SUCCESS);

        //invalid command
        commandBox.runCommand("savec:\\desktop\\typetask");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }
}
