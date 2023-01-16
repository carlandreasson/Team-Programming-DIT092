package Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Objects.Project.*;
import Utilities.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectController {

    private static TeamController teamController;
    private static int projectID; // save to file
    private static Project currentProject;
    private static HashMap<Integer, Project> projectStorage = new HashMap<Integer, Project>();

    public ProjectController() {
        try {
            this.projectID = IO.loadProjectID();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int idMaker() {
        int id;
        int size;
        size = currentProject.getUserStories().size();
        if (size == 0) {
            id = 1;
        } else {
            id = size + 1;
        }
        return id;
    }

    public static UserStory createUserStory(String desc, Project p) {

    UserStory u = new UserStory(desc, idMaker());
        p.addUserStories(u);
    return u;
}
    public static ObservableList<Project> getProjects(){
        return FXCollections.observableArrayList(projectStorage.values());
    }
    public static  ObservableList<UserStory> getBacklog(Project p){

        return FXCollections.observableArrayList(p.getUserStories());
    }
    public static void addToBacklog(Project p, UserStory u){
        p.addUserStories(u);
    }
    public static void setCurrentProject(Project currentProject) {
        ProjectController.currentProject = currentProject;
    }
    public static Project getCurrentProject() {
        return ProjectController.currentProject;
    }
    public static Project createProject(String name, LocalDate date) {
        LocalDate currentDateTime = LocalDate.now();
        LocalDate endDate = date;
        try {
            projectID = IO.loadProjectID();
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            projectID++;
            currentProject = new Project(name, projectID, currentDateTime, endDate);
            projectStorage.put(projectID, currentProject);
            IO.saveCurrentProject();
        }catch (Exception e){
        }
        try {
            IO.saveProjectID(projectID);
        }catch(Exception e){
            e.printStackTrace();
        }
        return currentProject;
    }
    public static void removeProject(Project p){
        projectStorage.remove(p.getID());
    }
    public static void removeUserStory(Project p, UserStory u){
        p.removeUserStories(u);
    }

    public static void addProject(Project project){
        projectStorage.put(project.getID(),project);
    }

    public static void saveProjectToExcel() { // this method is better but implementations comes later =)
        
        printProjectStorage();
        int id;
        Print.print("Input project id to save: ");

        do {
            id = Input.fetchInputInt("");
            if (!projectStorage.containsKey(id)) {
                Print.print(Print.ERROR_INPUT);
            }
        } while (!projectStorage.containsKey(id));
        currentProject = projectStorage.get(id);

        try {
           SaveToExcel.saveToExcel(currentProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printProjectStorage() {
        if (projectStorage.isEmpty()) {
            System.out.println(Print.THE_LIST_IS_EMPTY);
           // Controller.runProjectController();
        } else {
            for (Map.Entry<Integer, Project> entry : projectStorage.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }
    public static void setProjectDescription(String s, Project p){
        p.setProjectDescription(s);
    }
    public static String getProjectDescription(Project p){
        return p.getProjectDescription();
    }

    public static String setEndDate() {
        String endDate = "";
        int check;
        do {
            try {
                endDate = DateHandler.setDate();

            } catch (Exception e) {
                check = 1;
            }
            check = 0;
        } while (check == 1);
        return endDate;
    }

    public static Risk createRisk(String name){
        Risk risk = new Risk(name);
        addRisk(risk);
        return risk;
    }
    public static boolean defaultMilestones(){
       // ArrayList<LocalDate> days = DateHandler.getBusinessDaysBetween(currentProject.getCreatedDate(), currentProject.getEndDate());
        createMilestone("Project initiation", currentProject.getCreatedDate().plusWeeks(1));
        createMilestone("Something else", currentProject.getCreatedDate().plusWeeks(2));
        createMilestone("Project Almost Done", currentProject.getCreatedDate().plusWeeks(3));
        createMilestone("Project Done", currentProject.getCreatedDate().plusWeeks(4));
        if(currentProject.getCreatedDate().plusWeeks(4).isAfter(currentProject.getEndDate())){
            return false;
        }else {
            return true;
        }
    }
    public static boolean defaultActivities(){
        return true;
    }
    public static Activity createActivity(String name, int duration){
        Activity a = new Activity(name, duration);
        addActivity(a);
        return a;
    }
    public static void addRisk(Risk r){
        currentProject.getRisks().add(r);
    }
    public static Milestone createMilestone(String name, LocalDate d){
        Milestone m = new Milestone(name);
        m.setMilestoneDate(d);
        addMilestone(m);
        return m;
    }

    public static void addMilestone(Milestone m){
        currentProject.getMilestones().add(m);
    }
    public static void addActivity(Activity a){
        currentProject.getActivities().add(a);
    }
    public static ObservableList<Activity> getActivities(){
        return FXCollections.observableArrayList(currentProject.getActivities());
    }

    public static ObservableList<Milestone> getMilestones(){
        return FXCollections.observableArrayList(currentProject.getMilestones());
    }


    public static ObservableList<Risk> getRisk(){
        return FXCollections.observableArrayList(currentProject.getRisks());
    }

}
