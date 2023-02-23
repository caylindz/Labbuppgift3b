package ui;

import Matcher.ITaskMatcher;
import Matcher.TakenByMatcher;
import Matcher.NotDoneMatcher;
import Matcher.PrioMatcher;
import model.*;

import java.util.List;
import java.util.Scanner;

/**
 * User interactions for a specific project, current project.
 * The user selects actions on current project in the projectLoop method.
 */
class CurrentProjectUI {
    private Project currentProject;
    private final Scanner scan;

    // package private visibility - only visible to other classes in
    // package ui - intended for MainUI.
    CurrentProjectUI(Scanner scan) {
        this.scan = scan;
        this.currentProject = null; // TODO: Ugly!
    }

    void setCurrentProject(Project project) {
        this.currentProject = project;
        projectLoop();
    }

    Project getCurrentProject() {
        return currentProject;
    }

    void projectLoop() {
        char choice;
        do {
            printCurrentProjectMenu();
            choice = InputUtils.scanAndReturnFirstChar(scan);

            switch (choice) {
                case 'T':
                    System.out.print("Name? ");
                    String takenBy = scan.nextLine();
                    viewTasks(new TakenByMatcher(takenBy));
                    break;
                case 'N':
                    viewTasks(new NotDoneMatcher());
                    break;
                case 'H':
                    viewTasks(new PrioMatcher(Prio.High));
                    break;
                case 'A':
                    addTask();
                    break;
                case 'U':
                    updateTask();
                    break;
                case 'R':
                    removeTask();
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Unknown command");
            }

        } while (choice != 'X');
    }

    private void viewTasks(ITaskMatcher matcher) {
        System.out.println(currentProject.toString());
        List<Task> tasks = currentProject.findTasks(matcher);
        printTasks(tasks);
    }

    private void addTask() {
        System.out.print("Description? ");
        String descr = scan.nextLine();
        System.out.print("Priority (L)ow, (M)edium, (H)igh? ");
        char prioChar = InputUtils.scanAndReturnFirstChar(scan);
        Prio prio = prioChar == 'H' ? Prio.High : prioChar == 'L' ? Prio.Low : Prio.Medium;
        currentProject.addTask(descr, prio);
    }


    /**
     *  Lets user change either state, prio or takenBy
     */
    private void updateTask() {
        System.out.print("Task id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Task task = currentProject.getTaskById(id);
        if (task != null) {
            System.out.println(task);
            System.out.println("Update (P)rio, (T)aken by or (S)tate ?");
            char choiceChar = InputUtils.scanAndReturnFirstChar(scan);

            switch(choiceChar){
                case 'P':
                    System.out.println("Change to (H)igh, (M)edium or (L)ow?");
                    char choicePrio = InputUtils.scanAndReturnFirstChar(scan);
                    if(choicePrio=='H'){
                        task.setPrio(Prio.High);
                    }else if(choicePrio=='M'){
                        task.setPrio(Prio.Medium);
                    }else if(choicePrio=='L'){
                        task.setPrio(Prio.Low);
                    }else{
                        System.out.println("Error, wrong input");
                    } break;


                case 'T':
                    System.out.print("Taken by (name or email address)? ");
                    String emailStr = scan.nextLine();
                    task.setState(TaskState.IN_PROGRESS);
                    task.setTakenBy(emailStr); break;


                case 'S':
                    System.out.print("New state (T)odo (I)n progress (D)one? ");
                    char stateChar = InputUtils.scanAndReturnFirstChar(scan);
                    if(stateChar=='T'){
                        task.setState(TaskState.TO_DO);
                    }else if(stateChar=='I'){
                        task.setState(TaskState.IN_PROGRESS);
                    }else if(stateChar=='D'){
                        task.setState(TaskState.DONE);
                    }else{
                        System.out.println("Error, wrong input");
                    } break;

                default:
                    System.out.println("Error, does not match input.");
            }
        } else {
            System.out.println("Id not found.");
        }
    }


    /**Removes task using the removeTask method in project class by matching taskID*/
    private void removeTask(){
        System.out.print("Task id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Task task = currentProject.getTaskById(id);
        if (task != null) {
            currentProject.removeTask(task);
            System.out.println(task + "has been removed!");
        } else {
            System.out.println("No such project");
        }

    }


    private void printCurrentProjectMenu() {
        System.out.println("--- Manage " + currentProject.getTitle() + " ---");
        System.out.println("T - list tasks taken by ...");
        System.out.println("N - list tasks not done");
        System.out.println("H - list high priority tasks");
        System.out.println("A - add task");
        System.out.println("U - update task");
        System.out.println("R - remove task");
        System.out.println("X - exit project menu");
        System.out.println("----------");
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added");
        } else {
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
    }
}
