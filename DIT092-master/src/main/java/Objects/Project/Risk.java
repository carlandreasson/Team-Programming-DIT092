package Objects.Project;


import java.io.Serializable;

public class Risk implements Serializable {
    private String name;
    private int ID;
    private int severity;
    private int occurrence;
    private int rating;
    private int impact;
    private double cost;
    public Risk(String name){
        super();
        this.name = name;
    }
    public String getName() {
            return name;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public int getImpact() {
        return impact;
    }

    public double getCost() {
        cost = (severity * occurrence * impact );
        return this.cost;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return getName() + " Cost Of Risk " + getCost();
    }
}
