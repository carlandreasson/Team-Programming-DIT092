package Views;

import Controllers.ProjectController;
import Objects.Project.UserStory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import Objects.Project.Project;
import Utilities.IO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProjectView implements Initializable {
    public ListView<String> projectInformationView;
    public Text projectName;
    public Button loadFronfile;
    public Button aProject;
    Project project = ProjectController.getCurrentProject();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFronfile.setVisible(ProjectController.getCurrentProject().getLoadArchive());
        projectName.setText(project.getName());
        projectInformationView.getItems().addAll(project.daysLeft() + ": Days left",project.getName(),project.getMilestones().toString(),
                project.getTeamList().toString(), project.getProjectDescription());
        try {
            IO.saveProject(project);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,
                "Projects.Page.fxml");
        try {
            IO.saveProject(project);
        } catch (IOException e){
            System.out.println("Error while saving project");
        }
    }

    public void openScrum(ActionEvent actionEvent){
        new ChangeScene().changeScene(actionEvent, "Scrum-Board.Page.fxml");
    }
    public void openBacklog(ActionEvent actionEvent){
        new ChangeScene().changeScene(actionEvent,
                "Project.BackLog.Page.fxml");

    }
    public void editTeam(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,
                "Add.Team.Page.fxml");
    }

    public void openDashBoard(ActionEvent actionEvent) {
        ProjectController.setCurrentProject(project);
        new ChangeScene().changeScene(actionEvent, "DashBoardPage.fxml");
    }

    public void editProject(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "EditProjectView.fxml");
    }

    public void projectDetails(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "ProjectDetailsView.fxml");
    }

    public void loadBacklog(ActionEvent actionEvent) {

        ProjectController.getCurrentProject().setLoadArchive(false);
        ProjectController.getCurrentProject().getUserStories().clear();
        ProjectController.getCurrentProject().getActivities().clear();
        ProjectController.getCurrentProject().getMilestones().clear();
        ProjectController.getCurrentProject().getRisks().clear();
        IO.importObject();
        IO.importRisk();
        IO.importActivity();
        IO.importMilestone();
        IO.importProjectData();
        loadFronfile.setVisible(ProjectController.getCurrentProject().getLoadArchive());

    }
    public void saveToFile(ActionEvent actionEvent) {
        IO.writeProject();
    }
}
