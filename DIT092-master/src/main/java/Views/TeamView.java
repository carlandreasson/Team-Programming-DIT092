package Views;

import Controllers.TeamController;
import Utilities.Print;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import Objects.Team.Team;
import Utilities.IO;


import java.net.URL;
import java.util.ResourceBundle;

public class TeamView implements Initializable {

    public ListView<Team> teamListView;
    ObservableList<Team> teamList;
    public Label errorMessage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IO.loadAllTeams();
        teamList = FXCollections.observableArrayList(TeamController.getTeams());
        teamListView.setItems(teamList);
    }

    public void newTeam(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "Create-Team.Page.fxml");
    }

    public void deleteTeam(ActionEvent actionEvent) {
        Team team = teamListView.getSelectionModel().getSelectedItem();
        if(team != null) {
            TeamController.deleteTeam(team);
            teamList.remove(team);
        }else{
            errorMessage.setText(Print.SELECT_A_TEAM);
            System.out.println(Print.SELECT_A_TEAM);
        }
    }

    public void backButton(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "MainMenu.Page.fxml");
    }
}
