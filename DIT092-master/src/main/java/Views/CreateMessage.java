package Views;

import Controllers.Controller;
import Controllers.MessageController;
import Controllers.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import Utilities.Print;
import Objects.User.User;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateMessage implements Initializable {

    public Label errorMessage;
    public TextArea message;
    public ObservableList<User> userList; //ska dessa variablar vara privata?
    @FXML
    public ListView<User> userListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userList = FXCollections.observableArrayList(UserController.getUsers());
        userList.remove(Controller.getCurrentUser());
        userListView.setItems(userList);
    }

    public void cancelMessage (ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Inbox.Page.fxml");
    }

    public void sendMessage (ActionEvent actionEvent){
        User user = userListView.getSelectionModel().getSelectedItem();
        if(user != null){
                MessageController.createMessage(userListView.getSelectionModel().getSelectedItem().getID(), message.getText());
                UserController.saveUserMap();
                new ChangeScene().changeScene(actionEvent,"Inbox.Page.fxml");
        }else{
            errorMessage.setText(Print.SELECT_A_USER);
            System.out.println(Print.SELECT_A_USER);
        }

        }

    }

