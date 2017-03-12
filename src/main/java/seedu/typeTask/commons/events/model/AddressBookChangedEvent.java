package seedu.typeTask.commons.events.model;

import seedu.typeTask.commons.events.BaseEvent;
import seedu.typeTask.model.ReadOnlyTaskManager;

/** Indicates the AddressBook in the model has changed*/
public class AddressBookChangedEvent extends BaseEvent {

    public final ReadOnlyTaskManager data;

    public AddressBookChangedEvent(ReadOnlyTaskManager data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "number of persons " + data.getTaskList().size();
    }
}
