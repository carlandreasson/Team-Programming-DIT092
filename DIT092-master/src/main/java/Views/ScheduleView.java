package Views;

import Controllers.ProjectController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import Objects.Project.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleView implements Initializable {

    public GridPane schedule;

    public void backToMainMenu (ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"MainMenu.Page.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Project p = ProjectController.getCurrentProject();
        p.getActivities();
        p.getMilestones();

    }
}
