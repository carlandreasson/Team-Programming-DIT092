package Utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import Controllers.ProjectController;
import Controllers.TeamController;
import javafx.collections.ObservableList;
import Objects.Project.*;
import Objects.Team.Team;
import Objects.User.User;


public class IO {
    public static final File USER_DATA = new File("src/main/java/Files/UserData.txt");
    public static final String PROJECT_LOCATION = ("src/main/java/Files/Projects/ProjectREPLACE_WITH_ID.txt");
    public static final String PROJECT_FOLDER = ("src/main/java/Files/Projects");
    public static final File PROJECT_ID = new File("src/main/java/Files/ProjectID.txt");
    public static final String TEAM_LOCATION = ("src/main/java/Files/Teams/TeamREPLACE_WITH_ID.txt");
    public static final String TEAM_FOLDER = ("src/main/java/Files/Teams");
    public static final File TEAM_ID = new File("src/main/java/Files/TeamID.txt");

    public static void saveUsers(HashMap<Integer, User> users) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA));
            oos.writeObject(users);
        } catch (Exception e) {
            System.out.println("saveUsers error");
            e.printStackTrace();
        }
    }


    public static HashMap<Integer, User> readUsers() {
        HashMap<Integer, User> users = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA));
            users = (HashMap<Integer, User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("readUsers error");
        }
        return users;
    }

    public static ArrayList<Project> loadAllProjects() {
        ArrayList<Project> projects = new ArrayList<>();
        File path = new File(PROJECT_FOLDER);
        File[] fileList = path.listFiles();
        for (File file : fileList) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Project loadedProject = (Project) ois.readObject();
                ProjectController.addProject(loadedProject);
                ois.close();
            } catch (Exception e) {
                System.out.println("loadAllProjects error");
                e.printStackTrace();
            }
        }
        return projects;
    }

    public static void saveProject(Project project) throws IOException {
        File saveProjectFile = new File(PROJECT_LOCATION.replace(Print.REPLACE_WITH_ID, Integer.toString(project.getID())));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveProjectFile));
        oos.writeObject(project);
    }

    public static void saveCurrentProject() {
        Project project = ProjectController.getCurrentProject();
        File saveProjectFile = new File(PROJECT_LOCATION.replace(Print.REPLACE_WITH_ID, Integer.toString(project.getID())));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveProjectFile));
            oos.writeObject(project);
        } catch (Exception e) {
            System.out.println("SaveCurrentProject error");
        }
    }

    public static int loadProjectID() {
        int loadedID;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PROJECT_ID));
            loadedID = (int) ois.readObject();
        } catch (Exception e) {
            loadedID = 0;
        }
        return loadedID;
    }
    public static void removeProjectFile(Project project){
        String projectFileName = "Project" + project.getID() + ".txt";
        try{
            Files.deleteIfExists(Path.of("src/main/java/Files/Projects/" + projectFileName));
        }catch(Exception IO){
            System.out.println(IO);
        }
    }
    public static void saveProjectID(int projectID) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PROJECT_ID));
        oos.writeObject(projectID);
    }

    public static void saveTeamID(int teamID) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TEAM_ID));
        oos.writeObject(teamID);
    }

    public static void saveCurrentTeam() throws IOException {
        Team t = TeamController.getTeam();
        File saveTeamFile = new File(TEAM_LOCATION.replace(Print.REPLACE_WITH_ID, Integer.toString(t.getTeamID())));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveTeamFile));
        oos.writeObject(t);
    }

    public static void loadAllTeams() {
        File path = new File(TEAM_FOLDER);
        File[] fileList = path.listFiles();

        for (File file : fileList) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Team loadedTeam = (Team) ois.readObject();
                TeamController.addTeam(loadedTeam);
                ois.close();
            } catch (Exception e) {
                System.out.println("loadAllTeams error");
            }
        }
    }


    public static void removeTeamFile(Team team){
        String textFileName = "Team" + team.getTeamID() + ".txt";
        try{
            Files.deleteIfExists(Path.of("src/main/java/Files/Teams/" + textFileName));
        }catch(Exception IO){
            System.out.println(IO);
        }
    }

    public static int loadTeamID() {
        int loadedID;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TEAM_ID));
            loadedID = (int) ois.readObject();
        } catch (Exception e) {
            loadedID = 0;
        }

        return loadedID;
    }

    public static void importObject() {
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("src/main/java/Files/Resent/userStory.txt"));
            String data = "";
            while ((data = myReader.readLine()) != null) {
                String[] objects = data.split("[;]");
                String name = objects[0];
                String createdDate = objects[1];
                String endDate = objects[2];
                int point = Integer.parseInt(objects[3]);
                int hours = Integer.parseInt(objects[4]);
                UserStory u = ProjectController.createUserStory(name, ProjectController.getCurrentProject());
                u.setCreatedDate(LocalDate.parse(createdDate, DateHandler.format()));
                u.setDoneDate(LocalDate.parse(endDate, DateHandler.format()));
                u.setPoints(point);
                u.overRideHours(hours);
            }
        } catch (IOException e) {
            System.out.println("Error while reading importObjects.");
            e.printStackTrace();
        }
        System.out.println("User stories loaded successfully");
    }

    public static void importRisk() {
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("src/main/java/Files/Resent/risks.txt"));
            String data = "";
            while ((data = myReader.readLine()) != null) {
                String[] objects = data.split("[;]");
                String name = objects[0];
                int severity = Integer.parseInt(objects[1]);
                int occurrence = Integer.parseInt(objects[2]);
                int impact = Integer.parseInt(objects[3]);
                Risk r = ProjectController.createRisk(name);
                r.setSeverity(severity);
                r.setOccurrence(occurrence);
                r.setImpact(impact);
            }
        } catch (IOException e) {
            System.out.println("Error while reading importRisk.");
            e.printStackTrace();// Its printing like an error message
        }
    }

    public static void importActivity() {
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("src/main/java/Files/Resent/activities.txt"));
            String data = "";
            while ((data = myReader.readLine()) != null) {
                String[] objects = data.split("[;]");
                String name = objects[0];
                int duration = Integer.parseInt(objects[1]);
                Activity a = ProjectController.createActivity(name,duration);
            }
        } catch (IOException e) {
            System.out.println("Error while reading importActivity.");
            e.printStackTrace();
        }
    }
    public static void importMilestone() {
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("src/main/java/Files/Resent/milestones.txt"));
            String data = "";
            while ((data = myReader.readLine()) != null) {
                String[] objects = data.split("[;]");
                String name = objects[0];
                String deadLine = objects[1];
                Milestone m = ProjectController.createMilestone(name,LocalDate.parse(deadLine, DateHandler.format()));
            }
        } catch (IOException e) {
            System.out.println("Error while reading importMilestone.");
            e.printStackTrace();
        }
    }
    public static void importProjectData() {
        Project p = ProjectController.getCurrentProject();
        try {
            BufferedReader myReader = new BufferedReader(new FileReader("src/main/java/Files/Resent/projectInformation.txt"));
            String data = "";
            while ((data = myReader.readLine()) != null) {
                String[] objects = data.split("[;]");
                p.setCreatedDate(LocalDate.parse(objects[0],DateHandler.format()));
                p.setEndDate(LocalDate.parse(objects[1],DateHandler.format()));
                p.setBudget(Integer.parseInt(objects[2]));
            }
        } catch (IOException e) {
            System.out.println("Error while reading importProjectDate.");
            e.printStackTrace();// Its printing like a error message
        }
    }

    public static void writeProject (){
        Project p = ProjectController.getCurrentProject();
        String directoryName = "src/main/java/Files/Resent/" + p.getName();
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }
        ArrayList<Risk> risks =  p.getRisks();
        ArrayList<Activity> activities = p.getActivities();
        ArrayList<Milestone> milestones =  p.getMilestones();
        ObservableList<UserStory> userStories = p.getUserStories();

        BufferedWriter writer;
        try {
            int count = 0;
            writer = new BufferedWriter(new FileWriter(directoryName + "/risks.txt", true));
            writer.flush();
            for (Risk r : risks) {
                if (count != 0) {
                    writer.newLine();
                }
                writer.write(r.getName() + ";" + r.getSeverity() + ";" + r.getOccurrence() + ";" + r.getImpact());
                count ++;
            }
            count = 0;
            writer = new BufferedWriter(new FileWriter(directoryName + "/activities.txt", true));
            writer.flush();
            for (Activity a : activities) {
                if (count != 0) {
                    writer.newLine();
                }
                writer.write(a.getName() + ";" + a.getDuration());
                count++;
            }

            count = 0;
            writer = new BufferedWriter(new FileWriter(directoryName + "/milestones.txt", true));
            writer.flush();
            for (Milestone m : milestones) {
                if (count != 0) {
                    writer.newLine();
                }
                writer.write(m.getName() + ";" + m.getMilestoneDate().format(DateHandler.format()));
                count ++;
            }
            count = 0;
            writer = new BufferedWriter(new FileWriter(directoryName + "/userStory.txt", true));
            writer.flush();
            for (UserStory u : userStories) {
                if (count != 0) {
                    writer.newLine();
                }
                writer.write(u.getDescription() + ";" + u.getCreatedDate().format(DateHandler.format()) + ";" + u.getDoneDate().format(DateHandler.format()) + ";" + u.getPoints() + ";" + u.getHours());
                count++;
            }

            writer = new BufferedWriter(new FileWriter(directoryName + "/projectInformation.txt", false));
            writer.flush();
                writer.write(p.getCreatedDate().format(DateHandler.format())+ ";" + p.getEndDate().format(DateHandler.format())+ ";" +
                        p.getBudget());
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing Project");
            e.printStackTrace();
        }
    }
}

