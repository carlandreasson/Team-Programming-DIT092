package Views;

import Controllers.Controller;
import Controllers.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import Utilities.Print;
import Objects.User.User;

import java.net.URL;
import java.util.ResourceBundle;


public class UsersView implements Initializable {
    public Label errorMessage;
    private ObservableList<User> userList;
    public ListView<User> userListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList = FXCollections.observableArrayList(UserController.getUsers());
        userListView.setItems(userList);
    }

    public void newUserView(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Create-User.Page.fxml");
    }

    public void deleteUser(ActionEvent actionEvent) {
        User user = userListView.getSelectionModel().getSelectedItem();
    if(user != null){
    if(user.equals(Controller.getCurrentUser())){
        errorMessage.setText(Print.CANNOT_DELETE_LOGGED_IN_USER);
        System.out.println(Print.CANNOT_DELETE_LOGGED_IN_USER);
    }else {
        UserController.deleteUser(user);
        userList.remove(user);
    }
    }else{
        errorMessage.setText(Print.SELECT_A_USER);
        System.out.println(Print.SELECT_A_USER);
    }

    }
    public void backToMainMenu (ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"MainMenu.Page.fxml");
    }
}
