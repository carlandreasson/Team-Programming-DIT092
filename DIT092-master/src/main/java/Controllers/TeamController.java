package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utilities.Print;
import Objects.Team.Team;
import Objects.User.User;
import Utilities.DateHandler;
import Utilities.IO;
import Utilities.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TeamController {

    private static int teamID;
    private static Team team;
    public static HashMap<Integer, Team> teamStorage = new HashMap();
    private static Team currentTeam;

    public static HashMap<Integer, Team> getTeamStorage() {
        return teamStorage;
    }

    public static void setTeam(Team team) {
        currentTeam = team;
    }

    public static ObservableList<Team> getTeams() {
        return FXCollections.observableArrayList(teamStorage.values());
    }


    public static Team getTeam() {
        return team;
    }

    public static void createTeam(String name, ArrayList<User> teamMembers) {
        teamID = IO.loadTeamID();
        String currentDateTime = DateHandler.getCurrentDate();
        teamID++;
        team = new Team(name, teamID, currentDateTime, teamMembers);
        teamStorage.put(teamID, team);
        printTeamStorage();
        currentTeam = team;
        try {
            IO.saveTeamID(teamID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void addTeam(Team team) {teamStorage.put(team.getTeamID(), team);}


    public static void deleteTeam(Team team) {
        System.out.println(team.getTeamID() + Print.HAS_BEEN_DELETED);
        teamStorage.remove(team.getTeamID());
        try {
            IO.removeTeamFile(team);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printTeamStorage() {

        if (teamStorage.isEmpty()) {
            System.out.println(Print.THE_LIST_IS_EMPTY);
        } else {
            for (Map.Entry<Integer, Team> entry : teamStorage.entrySet()) {
                System.out.println(entry.getValue().toString());

            }
        }
    }
}
