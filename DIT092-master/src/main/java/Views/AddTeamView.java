package Views;

import Controllers.ProjectController;
import Controllers.TeamController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import Objects.Project.Project;
import Objects.Team.Team;
import Utilities.IO;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTeamView implements Initializable {
    public ListView<Team> teamListView;
    ObservableList<Team> teamList;
    int ID;
    Project project;

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "OpenProjectPage.fxml");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       project = ProjectController.getCurrentProject();
       teamList = FXCollections.observableArrayList(TeamController.getTeams());
       teamListView.setItems(teamList);
        try {
            ID = IO.loadTeamID();
        }catch(Exception e){
        }
    }

    public void addTeam(ActionEvent actionEvent) {
        Team t = teamListView.getSelectionModel().getSelectedItem();
        project.assignTeam(t);
    }

    public void removeTeam(ActionEvent actionEvent) {
        Team t = teamListView.getSelectionModel().getSelectedItem();
        project.removeTeam(t);
    }

    public void openTeam(ActionEvent actionEvent) {
        Team t = teamListView.getSelectionModel().getSelectedItem();
        TeamController.setTeam(t);
        if(t!=null) {
            new ChangeScene().changeScene(actionEvent, "Team.Home.Page.fxml");
        }
    }
}
