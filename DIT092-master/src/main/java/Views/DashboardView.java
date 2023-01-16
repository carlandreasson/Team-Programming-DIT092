package Views;

import Controllers.ProjectController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import Objects.Project.Project;
import Objects.Project.Risk;
import Objects.Project.UserStory;
import Utilities.GraphBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardView implements Initializable {
    public LineChart<String , Number> burnDownChart;
    public LineChart<String, Number> budgetChart;
    public BarChart<String, Number> riskChart;

    Project project;

    public void back(ActionEvent actionEvent) {
        new ChangeScene().changeScene(actionEvent, "OpenProjectPage.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        project = ProjectController.getCurrentProject();
        ObservableList<UserStory> userStories = project.getUserStories();
        burnDownChart.getData().add(GraphBuilder.burnDownChart(userStories,project));
        burnDownChart.getData().add(GraphBuilder.burnDownForecast(userStories,project));
        burnDownChart.setLegendSide(Side.TOP);
        burnDownChart.setCreateSymbols(false);
        budgetChart.getData().add(GraphBuilder.budgetReport(userStories,project));
        budgetChart.getData().add(GraphBuilder.budgetForecast(userStories,project));
        budgetChart.setLegendSide(Side.TOP);
        budgetChart.setCreateSymbols(false);

        ObservableList<Risk> risks = ProjectController.getRisk();
        riskChart.getData().add(GraphBuilder.riskChart(risks,project));
        riskChart.setLegendSide(Side.LEFT);
    }
}
