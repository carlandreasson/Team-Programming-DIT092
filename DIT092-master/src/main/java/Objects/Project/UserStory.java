package Objects.Project;

import Utilities.DateHandler;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserStory implements Serializable {


    String description;
    int points;
    int id;
    int hours;
    List<String> tasks;
    LocalDate createdDate;
    LocalDate doneDate;
    LocalDate startDate;
    UserStoryState state = UserStoryState.BACK_LOGG;

    public UserStory(String description, int id){
        this.description = description;
        this.id = id;
        this.createdDate = LocalDate.now();
    }
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    public void setDoneDate(LocalDate doneDate) {
        setHours();
        this.state = UserStoryState.DONE;
        this.doneDate = doneDate;
    }
    public LocalDate getDoneDate() {
        return doneDate;
    }

    public int getId()
    {
	return this.id;
    }

    public void setStartDate(LocalDate localDate) {
        this.startDate = localDate;
    }

    public int getPoints()
    {
	return this.points;
    }

    public String getDescription()
    {
	return this.description;
    }

    public void setState() {
        if(state.equals(UserStoryState.BACK_LOGG)) {
            this.state = UserStoryState.TODO;
        }else if(state.equals(UserStoryState.TODO)) {
            this.state = UserStoryState.IN_PROGRESS;
            startDate = LocalDate.now();
        }else if (state.equals(UserStoryState.IN_PROGRESS)) {
            this.state = UserStoryState.TESTING;
        }else if (state.equals(UserStoryState.TESTING)) {
            this.state = UserStoryState.DONE;
            doneDate = LocalDate.now();
        }
    }

    public void reverseState(){
        if(state.equals(UserStoryState.DONE)) {
            this.state = UserStoryState.TESTING;
        }else if(state.equals(UserStoryState.TESTING)) {
            this.state = UserStoryState.IN_PROGRESS;
        }else if (state.equals(UserStoryState.IN_PROGRESS)) {
            this.state = UserStoryState.TODO;
        }else if (state.equals(UserStoryState.TODO)) {
            this.state = UserStoryState.BACK_LOGG;
        }
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String createTask(String task){
        int id = tasks.size() + 1;
        String mTask = "Task "+id+": "+" " + task;
        addTask(mTask);
        return mTask;
    }
    public void addTask(String task){
        tasks.add(task);
    }
    public void removeTask(int id){
        tasks.remove(id);
    }
    public List<String> getTasks() {
        return tasks;
    }

    public UserStoryState getState() {
        return state;
    }
    public int setHours(){
        ArrayList<LocalDate> days = new ArrayList<>();
        try{
            days = DateHandler.getBusinessDaysBetween(startDate,LocalDate.now());
        }catch (Exception e){

        }
        hours = days.size() * 8;
        return this.hours;
    }

    public void overRideHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return description + " Story Points: " + points +
                " ID: " + getId() +
                " state: " + getState();
    }
}
