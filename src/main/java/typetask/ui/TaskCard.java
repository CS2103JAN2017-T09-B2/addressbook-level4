package typetask.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import typetask.model.task.EventTask;
import typetask.model.task.ReadOnlyTask;

public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label time;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;


    public TaskCard(ReadOnlyTask task, int displayedIndex) {
        super(FXML);
        name.setText(task.getName().fullName);
        id.setText(displayedIndex + ". ");
        if (task instanceof EventTask) {
            date.setText("");
            time.setText("");
            EventTask eventTask = (EventTask) task;
            startDate.setText(eventTask.getStartDateTime().value);
            endDate.setText(eventTask.getEndDateTime().value);
        } else {
            date.setText(task.getDate().value);
            time.setText(task.getTime().value);
            startDate.setText("");
            endDate.setText("");
        }
    }

}
