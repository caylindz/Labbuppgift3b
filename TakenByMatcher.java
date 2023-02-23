package Matcher;

import model.*;

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
