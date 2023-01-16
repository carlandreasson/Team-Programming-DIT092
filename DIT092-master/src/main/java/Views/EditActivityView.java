package Views;

import Views.ChangeScene;
import Views.ProjectManagementView;
import Controllers.ProjectController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import Objects.Project.Activity;
import Objects.Project.Milestone;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class EditActivityView implements Initializable {
    public ListView<Milestone> mileStone;
    public TextField activityName;
    public Text title;
    ObservableList<Milestone> milestoneList;
    public Spinner<Integer> duration;
    Activity a = ProjectManagementView.a;

    public void editActivity(ActionEvent actionEvent) {
        if(mileStone.getSelectionModel().getSelectedItem() != null){
            a.setM(mileStone.getSelectionModel().getSelectedItem());
        }
        if(duration.getValue() != 0){
            a.setDuration(duration.getValue());
        }
        if(!activityName.getText().equals("")){
            a.setName(activityName.getText());
        }

    }

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "ProjectDetailsView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        milestoneList = ProjectController.getMilestones();
        mileStone.getItems().addAll(milestoneList);
        title.setText(a.getName());
    }
}
