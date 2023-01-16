package Views;

import Controllers.Controller;
import Controllers.UserController;
import Utilities.Print;
import javafx.scene.control.*;


import javafx.event.ActionEvent;

public class LogInView {
    public Button signInButton;
    public PasswordField passwordField;
    public TextField usernameField;
    public Label errorMessage;
    public Button quitButton;

    public Button createAccount;

    public void signIn(ActionEvent event){
            try{

                int userID = Integer.parseInt(usernameField.getText());
                String password = passwordField.getText();
                UserController.saveUserMap();
                if(Controller.checkUserID(userID) && Controller.checkPassword(userID, password)){
                    Controller.loginSuccessful(UserController.getUser(userID));
                    new ChangeScene().changeScene(event, "MainMenu.Page.fxml");
                }else if(Controller.timeOutChecker(userID)) {
                    errorMessage.setText(Print.SIGN_IN_FAILED +  Controller.getAttemptsLeft(UserController.getUser(userID)));
                    } else{
                  errorMessage.setText(Controller.getLockedOutTime(UserController.getUser(userID)));
                }


            }catch (Exception e){
                errorMessage.setText(Print.USER_NOT_EXIST);
                System.out.println(Print.USER_NOT_EXIST);
            }
    }
    public void newUserView(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Create-User-Login.Page.fxml");
    }
}
