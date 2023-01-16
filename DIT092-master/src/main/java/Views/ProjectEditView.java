package Views;

import Controllers.ProjectController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Objects.Project.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectEditView implements Initializable {
    public TextField projectName;
    Project project;
    public DatePicker datePickerStartDate;
    public TextField projectDescription;
    public DatePicker datePickerEndDate;
    public TextField projectBudget;

    public void editProject(ActionEvent actionEvent) {
        if(!projectDescription.getText().equalsIgnoreCase("")) {
            project.setProjectDescription(projectDescription.getText());
        }
        if(!projectBudget.getText().equalsIgnoreCase("")) {
            project.setBudget(Integer.parseInt(projectBudget.getText()));
        }
        if(datePickerStartDate.getValue() != null ){
            project.setCreatedDate(datePickerStartDate.getValue());
        }
        if(datePickerEndDate.getValue() != null) {
            project.setEndDate(datePickerEndDate.getValue());
        }
        if(!projectName.getText().equalsIgnoreCase("")) {
            project.setName(projectName.getText());
        }
        projectBudget.clear();
        projectDescription.clear();
        datePickerEndDate.getEditor().clear();
        datePickerStartDate.getEditor().clear();

    }

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "OpenProjectPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        project = ProjectController.getCurrentProject();
    }
}
