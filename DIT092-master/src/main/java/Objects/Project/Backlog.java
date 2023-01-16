package Objects.Project;

import java.io.Serializable;
import java.util.ArrayList;


public class Backlog implements Serializable{
    private ArrayList<UserStory> backlogList = new ArrayList<>();

    public void remove(int id){
        backlogList.remove(id);
    }

    public int idMaker(){
        int id = backlogList.size()+1;
        return id;
    }
    public ArrayList<UserStory> getBacklogList() {
        return backlogList;
    }
}
