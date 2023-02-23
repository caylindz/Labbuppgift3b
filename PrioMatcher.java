package Matcher;

import model.*;


/**
 * Compares a task with the enum PRIO and finds all tasks that match the prio that was searched for
 */
public class PrioMatcher implements ITaskMatcher {

    private Prio searchPrio;

    public PrioMatcher(Prio search){
        this.searchPrio = search;
    }

    @Override
    public boolean match(Task task) {
        if(task.getPrio().compareTo(searchPrio)==0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "PrioMatcher{" +
                "searchPrio=" + searchPrio +
                '}';
    }
}