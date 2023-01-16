package Objects.Team;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Objects.Project.UserStory;
import Objects.User.User;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private String teamName;
    private int teamID;
    private String createdDate;
    public ArrayList<User> memberList = new ArrayList<>();
    public ArrayList<UserStory> backlog = new ArrayList<>();


    public Team (String teamName, int teamID, String date){
        this.teamName = teamName;
        this.teamID = teamID;
        this.createdDate = date;
    }

    public ObservableList<UserStory> getBacklog() {
        return FXCollections.observableArrayList(backlog);
    }
    public void addToBacklog(UserStory u){
    backlog.add(u);
    }

    public Team (String teamName, int teamID, String date, ArrayList<User> memberList){
        this.teamName = teamName;
        this.teamID = teamID;
        this.createdDate = date;
        this.memberList = memberList;
    }



    public ArrayList<User> getMemberList() {
        return this.memberList;
    }
    public String getName() {
        return teamName;
    }

    public void setName(String name) {
        this.teamName = teamName;
    }
    public String getCreatedDate() {
        return createdDate;
    }

    public int getTeamID() {
        return teamID;
    }

    @Override
    public String toString() {
        return "ID: "+ getTeamID()+" Team: " + teamName + "Team members :" + this.memberList.toString();
    }
}
