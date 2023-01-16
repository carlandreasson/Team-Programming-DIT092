package Views;

import Controllers.ProjectController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import Objects.Project.Risk;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateRiskView implements Initializable {


    public TextField createRisk;
    public Spinner severity;
    public Spinner occurrence;
    public ListView risks;
    public Spinner impact;
    ObservableList<Risk> risksList;

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "ProjectDetailsView.fxml");
    }

    public void createRisk(ActionEvent actionEvent) {
        Risk r = ProjectController.createRisk(createRisk.getText());
        if(severity.getValue() != null){
            r.setSeverity(Integer.parseInt(severity.getValue().toString()));
        }
        if(occurrence.getValue() != null){
            r.setOccurrence(Integer.parseInt(occurrence.getValue().toString()));
        }
        if(impact.getValue() != null){
            r.setImpact(Integer.parseInt(impact.getValue().toString()));
        }
        createRisk.clear();
        risks.getItems().add(r);
    }

    public void setSeverity(ActionEvent actionEvent) {

        if(risks.getSelectionModel().getSelectedItem() != null) {
            Risk r = (Risk)risks.getSelectionModel().getSelectedItem();
            r.setSeverity(Integer.parseInt(severity.getValue().toString()));
            risks.getItems().remove(r);
            risks.getItems().add(r);
        }


    }

    public void setOccurance(ActionEvent actionEvent) {
        if(risks.getSelectionModel().getSelectedItem() != null) {
            Risk r = (Risk)risks.getSelectionModel().getSelectedItem();
            r.setOccurrence(Integer.parseInt(severity.getValue().toString()));
            risks.getItems().remove(r);
            risks.getItems().add(r);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        risksList = ProjectController.getRisk();
        risks.getItems().addAll(risksList);
    }

    public void setImpact(ActionEvent actionEvent) {
        if(risks.getSelectionModel().getSelectedItem() != null) {
            Risk r = (Risk)risks.getSelectionModel().getSelectedItem();
            r.setImpact(Integer.parseInt(impact.getValue().toString()));
            risks.getItems().remove(r);
            risks.getItems().add(r);
        }
    }
}
