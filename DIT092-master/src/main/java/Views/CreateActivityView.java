package Views;

import Controllers.ProjectController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import Objects.Project.Activity;
import Objects.Project.Milestone;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateActivityView implements Initializable {

    public Spinner<Integer> duration;
    public TextField activityName;

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "ProjectDetailsView.fxml");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void createActivity(ActionEvent actionEvent) {
        if(!duration.getValue().toString().equals("")
                && !activityName.getText().equals("")){
            Activity a = ProjectController.createActivity(activityName.getText(),
                    Integer.parseInt(duration.getValue().toString()));

            activityName.clear();
        }
    }
}
