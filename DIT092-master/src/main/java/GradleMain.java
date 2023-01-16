import Controllers.UserController;
import Utilities.DateHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import Utilities.IO;

import java.time.LocalDate;

public class GradleMain extends Application {

    public static void main(String[] args) {
        UserController.loadUsers();
    //    UserController.addTestUser();
        IO.loadAllProjects();
        IO.loadAllTeams();
        launch(args);
    }

    //@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/SignIn.Page.fxml"));
        Image icon = new Image ("/Icons/LTSCP-icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("LTSCP");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
