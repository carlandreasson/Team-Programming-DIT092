package Objects.Project;

import java.io.Serializable;
import java.util.ArrayList;

public class ScrumBoard implements Serializable {
    ArrayList<UserStory> sprint = new ArrayList<>();

    public void setSprint(Backlog b){
        ArrayList<UserStory> list = b.getBacklogList();
        for (UserStory u: list ) {
            if(u.getState().equals(UserStoryState.TODO)){
                sprint.add(u);
                b.remove(u.getId());
            }
        }
    }
}
