package Views;

import Controllers.ProjectController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import Objects.Project.Milestone;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateMilestoneView implements Initializable {

    public DatePicker datePicker;
    public ListView mileStones;
    public TextField mileStone;
    ObservableList<Milestone> milestoneList;

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "ProjectDetailsView.fxml");
    }

    public void setDueDate(ActionEvent actionEvent) {
        if(mileStones.getSelectionModel().getSelectedItem() != null) {
            Milestone m  = (Milestone) mileStones.getItems();
            m.setMilestoneDate(datePicker.getValue());
            mileStones.getItems().remove(m);
            mileStones.getItems().add(m);
        }

    }

    public void createMileStone(ActionEvent actionEvent) {
        if (!mileStone.equals("") && datePicker != null){
            Milestone m = ProjectController.createMilestone(mileStone.getText(), datePicker.getValue());
            mileStones.getItems().add(m);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        milestoneList = ProjectController.getMilestones();
        mileStones.getItems().addAll(milestoneList);
    }

    public void setApproved(ActionEvent actionEvent) {
        if(mileStones.getSelectionModel().getSelectedItem() != null) {
            Milestone m  = (Milestone) mileStones.getItems();
            m.setApproved(true);
        }
    }

}
