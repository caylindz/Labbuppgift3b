package model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

import static java.time.LocalTime.now;

public class Task implements Comparable<Task>, Serializable {

    private String description;
    private int id;
    private String takenBy = null;
    private TaskState state;
    private LocalDate lastUpdate;
    private Prio prio;


    Task(String description, int id, Prio prio) {
        this.description = description;
        this.id = id;
        this.prio = prio;
    }

    public void setTakenBy(String takenBy) {
        if(this.takenBy!=null){
            throw new IllegalStateException();
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
}
