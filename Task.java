package model;


import java.io.Serializable;
import java.time.LocalDate;


/**
 * Represents logic and data for all tasks
 */
public class Task implements Comparable<Task>, Serializable {

    private final String description;
    private final int id;
    private String takenBy = " No One " ;
    private TaskState state = TaskState.TO_DO;
    private LocalDate lastUpdate;
    private Prio prio;


    /** Contructs a new task*/
    Task(String description, int id, Prio prio) {
        this.description = description;
        this.id = id;
        this.prio = prio;
    }


    /** Sets new person/name to a task but throws an exception if the task is already taken*/
    public void setTakenBy(String takenBy) {
        if(this.takenBy!=" No One "){
            throw new IllegalStateException("Already Taken");
        } else {
            this.takenBy = takenBy;
            updateLastUpdate();
        }
    }

    public void setState(TaskState state) {
        this.state = state;
        updateLastUpdate();
    }

    public void setPrio(Prio prio) {
        this.prio = prio;
        updateLastUpdate();
    }

    public int getId() {
        return id;
    }


    public TaskState getState() {
        return state;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }


    public String getDescription() {
        return description;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public Prio getPrio() {
        return prio;
    }

    public void updateLastUpdate(){
        this.lastUpdate = LocalDate.now();
    }


    @Override
    public int compareTo(Task anotherTask) {
        int rank = prio.compareTo(anotherTask.prio);
        if(rank==0){
            rank = description.compareTo(anotherTask.description);
        }

        return rank;
    }

    @Override
    public String toString() {
        return    "id:" + id + '\n'+
                    description + '\n' +
                "state:" + state + '\n' +
                "prio:" + prio + '\n' +
                "taken by:" + takenBy + '\n' ;
    }
}
