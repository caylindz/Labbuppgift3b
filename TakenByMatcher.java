package Matcher;

import model.*;


/**
 * Compares a task with the person taking the task and finds all tasks that
 * are taken by that certain name
 */
public class TakenByMatcher implements ITaskMatcher {

    private String searchName;

    public TakenByMatcher(String search){
        this.searchName = search;
    }

    @Override
    public boolean match(Task task) {
        if(task.getTakenBy().compareTo(searchName)==0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "TakenByMatcher{" +
                "searchName='" + searchName + '\'' +
                '}';
    }


}
