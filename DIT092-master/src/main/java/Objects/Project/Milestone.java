package Objects.Project;

import Utilities.Print;

import java.io.Serializable;
import java.time.LocalDate;

public class Milestone implements Serializable {
    String name;
    LocalDate milestoneDate;
    boolean approved = false;
    public  Milestone(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setMilestoneDate(LocalDate milestoneDate) {
        this.milestoneDate = milestoneDate;
    }

    public LocalDate getMilestoneDate() {
        return milestoneDate;
    }

    public void setApproved(boolean approved) {
        this.approved = true;
    }

    @Override
    public String toString() {
        return getName() + "" + Print.DEAD_LINE + milestoneDate;
    }
}
