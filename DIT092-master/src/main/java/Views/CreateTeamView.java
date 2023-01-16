package Views;

import Controllers.TeamController;
import Controllers.UserController;
import Utilities.Print;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Objects.User.User;
import Utilities.IO;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CreateTeamView implements Initializable {
    public ListView<User> usersInTeamListView;
    public ListView<User> usersListView;
    public TextField teamNameField;
    public Text teamIDDisplay;
    public Text errorMessage;
    public Button createButton;
    ObservableList<User> userList;
    ObservableList<User> userInTeamList;
    int teamID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList = FXCollections.observableArrayList(UserController.getUsers());
        userInTeamList = FXCollections.observableArrayList();
        usersListView.setItems(userList);
        usersInTeamListView.setItems(userInTeamList);
        try {
            teamID = IO.loadTeamID();
        }catch(Exception e){
            teamID = -1;
            errorMessage.setText(Print.ERROR_LOADING_ID);
            createButton.setVisible(false);
        }
        int t = teamID + 1;
        teamIDDisplay.setText(Integer.toString((t)));
    }


    public void backButton(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "Teams.Page.fxml");
    }


    public void removeSelectedFromTeam(ActionEvent actionEvent) {
        User u = usersInTeamListView.getSelectionModel().getSelectedItem();
        if(u != null) {
            userList.add(u);
            userInTeamList.remove(u);
        }
    }


    public void moveSelectedToTeam(ActionEvent actionEvent) {
        User u = usersListView.getSelectionModel().getSelectedItem();
        if(u != null) {
            userInTeamList.add(u);
            userList.remove(u);
        }
    }


    public void createTeam(ActionEvent actionEvent) {
        ArrayList<User> teamMembers= new ArrayList<>();
        String teamName = teamNameField.getText();

        for(User user: userInTeamList){
            teamMembers.add(user);
        }
            TeamController.createTeam(teamName, teamMembers);
            System.out.println(Print.TEAM_CREATED);
            try{
            IO.saveCurrentTeam();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        backButton(actionEvent);
    }
}
