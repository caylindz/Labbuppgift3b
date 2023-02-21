package Matcher;

import model.ProjectState;
import model.Task;
import model.TaskState;

public class NotDoneMatcher implements ITaskMatcher{

    private NotDoneMatcher() {

    }

    @Override
    public boolean match(Task task) {
        if(task.getState().compareTo(TaskState.DONE)==0){
            return true;
        } else {
            return false;
        }
    }


}
