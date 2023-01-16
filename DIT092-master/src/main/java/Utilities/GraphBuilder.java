package Utilities;

import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import Objects.Project.Project;
import Objects.Project.Risk;
import Objects.Project.UserStory;
import Objects.Project.UserStoryState;

import java.time.LocalDate;
import java.util.*;

public class GraphBuilder {

    public static XYChart.Series<String, Number> budgetReport(ObservableList<UserStory> userStories, Project p) {
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        ArrayList<LocalDate> days = new ArrayList<>();
        try {
            days = DateHandler.getBusinessDaysBetween(p.getCreatedDate(), p.getEndDate());
        } catch (Exception e) {

        }
        for (LocalDate l : days) {
            int budgetUsed = 0;
            for (UserStory u : userStories) {
                if (u.getState() == UserStoryState.DONE && l.isAfter(u.getDoneDate())) {
                    budgetUsed += u.getHours();
                }
            }
            data.getData().add(new XYChart.Data<String, Number>
                    (l.format(DateHandler.format()), budgetUsed));
        }
        data.setName("Budget Used");
        return data;
    }

    public static XYChart.Series<String, Number> budgetForecast(ObservableList<UserStory> userStories, Project p) {
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        int budget = p.getBudget();
        ArrayList<LocalDate> days = new ArrayList<>();
        try {
            days = DateHandler.getBusinessDaysBetween(p.getCreatedDate(), p.getEndDate());
        } catch (Exception e) {
        }
        double b = (double) budget / (double) days.size();
        // days.sort(LocalDate::compareTo);
        int budgetUsed = 0;
        for (LocalDate l : days) {

            budgetUsed += b;
            data.getData().add(new XYChart.Data<String, Number>
                    (l.format(DateHandler.format()), budgetUsed));
        }
        data.setName("Budget Forecast");

        return data;
    }

    public static XYChart.Series<String, Number> burnDownChart(ObservableList<UserStory> userStories, Project p) {
        int storyPointsLeft = 0;
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        ArrayList<LocalDate> days = new ArrayList<>();
        try {
            days = DateHandler.getBusinessDaysBetween(p.getCreatedDate(), p.getEndDate());
        } catch (Exception e) {

        }
        for (LocalDate l : days) {
            storyPointsLeft = 0;
            for (UserStory u : userStories) {

                if (l.isAfter(u.getCreatedDate())) {
                    storyPointsLeft += u.getPoints();
                }
                if (u.getState() == UserStoryState.DONE && l.isAfter(u.getDoneDate())) {
                    storyPointsLeft -= u.getPoints();
                }
            }
            data.getData().add(new XYChart.Data<String, Number>
                    (l.format(DateHandler.format()), storyPointsLeft));
        }
        data.setName("Burn Down Chart");

        return data;
    }

    public static XYChart.Series<String, Number> burnDownForecast(ObservableList<UserStory> userStories, Project p) {
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        int storyPointsLeft = 0;
        ArrayList<LocalDate> days = new ArrayList<>();
        try {
            days = DateHandler.getBusinessDaysBetween(p.getCreatedDate(), p.getEndDate());
        } catch (Exception e) {

        }
        for (UserStory u : userStories) {
            storyPointsLeft += u.getPoints();
        }
        double burnDown = (double) storyPointsLeft / (double) days.size();

        for (LocalDate l : days) {
            if(storyPointsLeft <=0 ){
                storyPointsLeft = 0;
            }
            data.getData().add(new XYChart.Data<String, Number>
                    (l.format(DateHandler.format()), storyPointsLeft));
            storyPointsLeft -= burnDown;

        }
        data.setName("Burn Down Forecast");
        return data;
    }
    public static XYChart.Series<String, Number> riskChart(ObservableList<Risk> risks, Project p) {
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Risk Chart");
        for(Risk r: risks){
            data.getData().add(new XYChart.Data<String, Number>
                    (r.getName(), r.getCost()));
        }
        return data;
    }
}
