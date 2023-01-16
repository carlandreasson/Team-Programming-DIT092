package Objects.Project;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Objects.Team.Team;
import Utilities.DateHandler;


public class Project implements Serializable {
    private String name;
    private int id;
    private LocalDate createdDate;
    private LocalDate endDate;
    private String lastTimeOpened;
    private ScrumBoard scrumboard;
    private Team assignedTeam;
    private String projectDescription;
    private ArrayList<Team> teamList = new ArrayList<>();
    private ArrayList<UserStory> userStories = new ArrayList<>();
    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private ArrayList<Milestone> milestones= new ArrayList<Milestone>();
    private ArrayList<Risk> risks = new ArrayList<Risk>();
    private int budget;
    private boolean loadArchive = true;

    public boolean getLoadArchive() {
        return loadArchive;
    }

    public void setLoadArchive(boolean loadArchive) {
        this.loadArchive = loadArchive;
    }





    public Project(String name, int teamID, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.id = teamID;
        this.createdDate = startDate;
        this.endDate = endDate;
    }

    public ObservableList<UserStory> getUserStories() {
        return FXCollections.observableArrayList(userStories);
    }
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void addUserStories(UserStory u) {
        userStories.add(u);
    }
    public void removeUserStories(UserStory u){
        userStories.remove(u);
    }

    public void assignTeam(Team team) {
        teamList.add(team);
    }

    public void removeTeam(Team team) {
        teamList.remove(team.getTeamID());
    }
    public String getName() {
        return name;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
    public void setLastTimeOpened(String lastTimeOpened) {
        this.lastTimeOpened = lastTimeOpened;
    }

    public Team getTeam() {
        return assignedTeam;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setTeam(Team assignedTeam) {
        this.assignedTeam = assignedTeam;
    }
    public int daysLeft(){
        List<LocalDate> workingDays= new ArrayList<>();
        try{
         workingDays =  DateHandler.getBusinessDaysBetween(LocalDate.now(),getEndDate());
        }catch(Exception e){

        }
        return workingDays.size();
    }

    public int getID() {
        return id;
    }

    public ArrayList<Risk> getRisks() {
        return risks;
    }

    public ArrayList<Milestone> getMilestones() {
        return milestones;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return  " ID: " + id +"  Project name: " + name + " Created date: " + getCreatedDate() + " " + daysLeft() +" days to complete.";
    }
}

