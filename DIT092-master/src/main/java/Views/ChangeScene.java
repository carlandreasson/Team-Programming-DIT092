package Views;

import Utilities.Print;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangeScene {
    public void changeScene(ActionEvent event, String nextScenePath){
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(nextScenePath));
            Scene nextScene = new Scene(parent);
            window.setScene(nextScene);
        } catch (Exception e){
            System.out.println(Print.FILE_PATH_NOT_FOUND);
        }
        window.show();

    }
}
