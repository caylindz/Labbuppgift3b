package model;

import Matcher.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Project implements Comparable<Project>, Serializable {

    private String title;
    private int id;
    private String description;
    private LocalDate created;
    private int nextTaskId = 0;
    private ArrayList<Task> taskList = new ArrayList<>();

    Project(String title, int id, String description) {
        this.title = title;
        this.id = id;
        this.description = description;
    }


    public Task getTaskById(int id){

        for(int i=0; i<taskList.size(); i++){
            if(id == taskList.get(i).getId()){
                return taskList.get(i);
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public Task addTask(String desc, Prio prio){
        Task addedTask = new Task(desc, nextTaskId, prio);
        nextTaskId++;
        taskList.add(addedTask);
        return addedTask;
    }

    public ProjectState getProjState(){
        ProjectState state = null;
        int count = 0;

        if(taskList.size()==0){
            return ProjectState.EMPTY;
        }

        for (int i=0; i<taskList.size(); i++){
            if (taskList.get(i).getState() == TaskState.DONE){
                count ++;
            }
        }

        if (count == taskList.size()){
            return ProjectState.COMPLETED;
        }

        return ProjectState.ONGOING;
    }



    public LocalDate getLastUpdated(){
        LocalDate temp = taskList.get(0).getLastUpdate();
        for (int i=0; i<taskList.size()-1; i++){
            int check = taskList.get(i).getLastUpdate().compareTo(taskList.get(i+1).getLastUpdate());
            if(check<0){
                temp = taskList.get(i+1).getLastUpdate();
            }
        }

        return temp;
    }

    public ArrayList<Task> findTasks(ITaskMatcher matcher){
        ArrayList<Task> matchedTasks = new ArrayList<>();

        for(int i=0; i<taskList.size(); i++){

            if(matcher.match(taskList.get(i))){
                matchedTasks.add(taskList.get(i));
            }
        }

        Collections.sort(matchedTasks, Collections.reverseOrder(Task::compareTo));

        //TODO Check if this is correct, matcher and search?

        return matchedTasks;
    }


    public boolean removeTask(Task task){
        return taskList.remove(task);
    }

    @Override
    public int compareTo(Project anotherProject) {
        return title.compareTo(anotherProject.title);
    }

    @Override
    public String toString() {
        return
                "title:" + title + '\n' +
                "id:" + id + '\n'+
                "description:" + description + '\n' ;
    }
}

