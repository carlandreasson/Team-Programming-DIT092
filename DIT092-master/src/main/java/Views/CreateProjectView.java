package Views;

import Controllers.ProjectController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Objects.Project.Project;

public class CreateProjectView {

    public TextField projectDescription;
    @FXML
    TextField projectName;
    @FXML
    DatePicker datePicker;
    @FXML
    Label label;

    public void createProject(ActionEvent actionEvent){
        Project p = ProjectController.createProject(setName(),datePicker.getValue());
        if(projectDescription.getText() != ""){
            p.setProjectDescription(projectDescription.getText());
        }

        new ChangeScene().changeScene(actionEvent,"Projects.Page.fxml");
    }
    public void setDate(ActionEvent actionEvent){
     datePicker.setValue(datePicker.getValue());
    }
    public String setName() {
        return projectName.getText();
    }

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Projects.Page.fxml");
    }
}
