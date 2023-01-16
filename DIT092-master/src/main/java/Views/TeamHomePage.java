package Views;

import Controllers.TeamController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javafx.scene.text.Text;
import Objects.Team.Team;
import Objects.User.User;
import Objects.Project.UserStory;


import java.net.URL;
import java.util.ResourceBundle;


public class TeamHomePage implements Initializable {
    public Text teamName;
    @FXML
    ListView<User> teamListView;
    public ObservableList<User> teamList;
    @FXML
    ListView<UserStory> backlogListView;
    public ObservableList<UserStory> backLogList;
    Team currentTeam;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentTeam = TeamController.getTeam();
        System.out.println(currentTeam.backlog);
        teamName.setText(currentTeam.getName());
        backLogList = FXCollections.observableArrayList(currentTeam.getBacklog());
        teamList = FXCollections.observableArrayList(TeamController.getTeam().getMemberList());
        backlogListView.setItems(backLogList);
        teamListView.setItems(teamList);
    }

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "Teams.Page.fxml");
    }

    public void editTeam(ActionEvent actionEvent) {
    }
}
