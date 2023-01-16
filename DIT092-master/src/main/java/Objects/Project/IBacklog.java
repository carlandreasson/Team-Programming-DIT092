package Objects.Project;

public interface IBacklog {

    public void addToBacklog(String desc, int score);
    public void remove(int id);
    public void view();
}
