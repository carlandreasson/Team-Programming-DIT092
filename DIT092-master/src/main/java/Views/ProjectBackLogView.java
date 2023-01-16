package Views;

import Controllers.ProjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import Objects.Project.Project;
import Objects.Project.UserStory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProjectBackLogView implements Initializable {
    @FXML
    public ListView<UserStory> backlogListView;
    @FXML
    public Text teamName;
    @FXML
    public TextField textBox;
    @FXML
    public TextField storyPoints;
    public DatePicker datePicker;

    ObservableList<UserStory> backlogList;
    Project p;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        p = ProjectController.getCurrentProject();
        backlogList = FXCollections.observableList(ProjectController.getCurrentProject().getUserStories());
        backlogListView.setItems(backlogList);
    }
    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "OpenProjectPage.fxml");
    }
    public void createUserStory(ActionEvent actionEvent) {
        if(textBox.getText()!="") {
            UserStory u = ProjectController.createUserStory(textBox.getText(), p);

            if(storyPoints.getText() != ""){
                try{
                    u.setPoints(Integer.parseInt(storyPoints.getText()));
                    storyPoints.clear();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            backlogListView.getItems().add(u);

            textBox.clear();
        }

    }

    public void setValue(ActionEvent actionEvent) {
        UserStory u = backlogListView.getSelectionModel().getSelectedItem();
        if(u!=null) {
            try {
                u.setPoints(Integer.parseInt(storyPoints.getText()));
            }catch (Exception e){
                e.printStackTrace();
            }

            backlogListView.getItems().remove(u);
            backlogListView.getItems().add(u);
            storyPoints.clear();
        }
    }
    public void setDone(ActionEvent actionEvent){
        UserStory u = backlogListView.getSelectionModel().getSelectedItem();
        LocalDate l = datePicker.getValue();
        if(l!=null){
            u.setDoneDate(l);
        }
        backlogListView.getItems().remove(u);
        backlogListView.getItems().add(u);
    }

    public void remove(ActionEvent actionEvent) {
        UserStory u = backlogListView.getSelectionModel().getSelectedItem();
        ProjectController.removeUserStory(p, u);
        backlogListView.getItems().remove(u);
    }

}
