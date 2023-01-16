package Views;

import Controllers.Controller;
import javafx.event.ActionEvent;

public class MainMenuView {

    public void openProjectView(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Projects.Page.fxml");
    }
    public void openUsersView(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Users.Page.fxml");
    }

    public void openTeamView(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Teams.Page.fxml");
    }

    public void openInboxView(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent,"Inbox.Page.fxml");
    }

    public void signOut(ActionEvent actionEvent) {
        Controller.logOut();

        new ChangeScene().changeScene(actionEvent,"SignIn.Page.fxml");
    }
}
