package Views;

import Utilities.IO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import Controllers.*;

import Objects.Project.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectMenuView implements Initializable  {

    public Button openProject;
    ObservableList<Project> projectList;
    @FXML
    public ListView<Project> projectListView;

    public void openProject(ActionEvent actionEvent) {
        Project project = projectListView.getSelectionModel().getSelectedItem();
        ProjectController.setCurrentProject(project);
        if(project!=null) {
            new ChangeScene().changeScene(actionEvent, "OpenProjectPage.fxml");
        }
    }
    public void newProject(ActionEvent actionEvent){
        new ChangeScene().changeScene(actionEvent,"Create-Project.Page.fxml");

    }

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"MainMenu.Page.fxml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectList = FXCollections.observableArrayList(ProjectController.getProjects());
        projectListView.setItems(projectList);
    }


    public void removeProject(ActionEvent actionEvent) {
        Project project = projectListView.getSelectionModel().getSelectedItem();
        if(project!=null) {
            ProjectController.removeProject(project);
            projectList.remove(project);
            IO.removeProjectFile(project);
        }
    }
}
