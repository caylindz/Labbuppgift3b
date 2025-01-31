package ui;

import model.Project;
import model.ProjectManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User interactions for managing projects.
 * The user selects actions in the mainLoop method.
 */
public class MainUI {

    private final Scanner scan;
    private final ProjectManager manager;
    private final CurrentProjectUI currentProjectUI;

    public MainUI(ProjectManager manager) {
        this.manager = manager;
        this.scan = new Scanner(System.in);
        // create object handling user interaction for current project
        this.currentProjectUI = new CurrentProjectUI(scan);
    }

    public void mainLoop() {
        char choice;

        do {
            printMainMenu();
            choice = InputUtils.scanAndReturnFirstChar(scan);

            switch (choice) {
                case 'F':
                    findProjects();
                    break;
                case 'A':
                    addProject();
                    break;
                case 'M':
                    manageProject();
                    break;
                case 'R':
                    removeProject();
                    break;
                case 'L':
                    listAll();
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Unknown command");
            }

        } while (choice != 'X');
        System.out.println("Bye bye! This was an ugly ui - I hope I'll learn about JavaFX and gui:s");
    }

    private void findProjects() {
        System.out.print("Project name? ");
        String name = scan.nextLine();
        List<Project> result = manager.findProjects(name);
        if (result.isEmpty()) {
            System.out.println("No matches.");
        } else {
            for (Project project : result) {
                System.out.println(project.toString());
            }
        }
    }

    private void addProject() {
        try {
            System.out.print("Project title: ");
            String title = scan.nextLine();
            System.out.print("Description: ");
            String description = scan.nextLine();
            Project newProject = manager.addProject(title, description);
            System.out.println("\nPROJECT CREATED: \n" + newProject);
        } catch (IllegalArgumentException e) {
            System.out.println("A project with that title already exists.");
        }
    }

    private void manageProject() {
        System.out.print("Project id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Project currentProject = manager.getProjectById(id);
        if (currentProject != null) {
            System.out.println(currentProject);
            currentProjectUI.setCurrentProject(currentProject);
        } else {
            System.out.println("Project not found");
        }
    }


    /**Removes project using the removeProject method in projectManager class by matching projectID*/
    private void removeProject(){
        System.out.print("Project id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Project currentProject = manager.getProjectById(id);
        if (currentProject != null) {
            manager.removeProject(currentProject);
            System.out.println(currentProject + "has been removed!");
        }
    }


    /** Lets user see all projects represented in i list*/
    private void listAll(){
        ArrayList<Project> list = manager.getProjects();

        for(int i=0; i<list.size(); i++)
        System.out.println(list.get(i));
    }

    private void printMainMenu() {
        System.out.println("---Main menu---");
        System.out.println("F - find project");
        System.out.println("A - add project");
        System.out.println("M - manage project");
        System.out.println("R - remove project");
        System.out.println("L - list all projects");
        System.out.println("X - exit");
        System.out.println("----------");
    }
}
