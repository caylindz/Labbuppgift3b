package Matcher;

import model.ProjectState;
import model.Task;
import model.TaskState;

/**
 * Compares a task with the enum TaskState "DONE" and finds all tasks that are not completed
 */
public class NotDoneMatcher implements ITaskMatcher{

    public NotDoneMatcher() {

    }

    @Override
    public boolean match(Task task) {
        if(task.getState().compareTo(TaskState.DONE)!=0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "NotDoneMatcher{}";
    }
}
