package Objects.Project;


import Utilities.Print;

import java.io.Serializable;
import java.time.LocalDate;

public class Activity implements Serializable {
    private String name;
    private int duration;
    private Milestone m;
    private LocalDate startDate;

    public Activity(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        LocalDate startDate = m.getMilestoneDate().minusDays(duration);
        return startDate;
    }

    public Milestone getMilestone() {
        return m;
    }

    public int getDuration() {
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setM(Milestone m) {
        this.m = m;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        if(getMilestone() == null){
            return getName() + Print.CONNECT_TO_MILESTONE_START_DATE;
        }
        return getName() + " " + getStartDate();
    }
}