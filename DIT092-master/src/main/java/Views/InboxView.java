package Views;

import Controllers.Controller;
import Controllers.MessageController;
import Controllers.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import Utilities.Print;
import Objects.Message.Message;
import java.net.URL;
import java.util.ResourceBundle;

public class InboxView implements Initializable {

    public Label errorMessage;
    public TextArea text;
    public TextArea replyText;
    public ObservableList<Message> messageList;
    @FXML
    public ListView<Message> messageListView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageList = FXCollections.observableArrayList(UserController.getUser(Controller.getCurrentUser().getID()).getMessages());
        messageListView.setItems(messageList);

    }

    public void openMessage(ActionEvent actionEvent){
        Message message = messageListView.getSelectionModel().getSelectedItem();
        if(message != null){
            text.setText(message.getMessage());
            if(message.getSenderID() != Controller.getCurrentUser().getID()){
                UserController.getUser(Controller.getCurrentUser().getID()).getMessage(message.getMessageID()).setRead(true);
            }
            messageList = FXCollections.observableArrayList(UserController.getUser(Controller.getCurrentUser().getID()).getMessages());
            messageListView.setItems(messageList);
            UserController.saveUserMap();
        }else{
            errorMessage.setText(Print.SELECT_A_MESSAGE);
            System.out.println(Print.SELECT_A_MESSAGE);
        }
    }
    public void newMessage(ActionEvent actionEvent){
        new ChangeScene().changeScene(actionEvent,"Create-Message.Page.fxml");
    }
    public void deleteMessage(ActionEvent actionEvent){
        Message message = messageListView.getSelectionModel().getSelectedItem();
        if(message != null){
            MessageController.deleteMessage(message.getMessageID());
                messageList.remove(message);
        }else{
            errorMessage.setText(Print.SELECT_A_MESSAGE);
            System.out.println(Print.SELECT_A_MESSAGE);
        }
        UserController.saveUserMap();
    }

    public void replyMessage(ActionEvent actionEvent){
        Message message = messageListView.getSelectionModel().getSelectedItem();
        if(message != null){
            MessageController.replyMessage(message, replyText.getText(), message.getSenderID());
            text.setText(UserController.getUser(message.getSenderID()).getMessage(message.getMessageID()).getMessage());
            replyText.clear();
            messageList = FXCollections.observableArrayList(UserController.getUser(Controller.getCurrentUser().getID()).getMessages());
            messageListView.setItems(messageList);
            UserController.saveUserMap();
        }else{
            errorMessage.setText(Print.SELECT_A_MESSAGE);
            System.out.println(Print.SELECT_A_MESSAGE);
        }
    }

    public void backToMainMenu (ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"MainMenu.Page.fxml");
        UserController.saveUserMap();
    }

}
