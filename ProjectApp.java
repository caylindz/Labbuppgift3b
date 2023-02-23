import io.ProjectsFileIO;
import model.Project;
import model.ProjectManager;
import ui.MainUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class ProjectApp {

    private static final String FILE_NAME = "projects.ser";

    public void run() throws Exception { // we do not catch all exceptions

        File projectsFile = new File(FILE_NAME);
        ProjectManager projectsManager = new ProjectManager();
        boolean couldReadFile = false;

        try {

            if (projectsFile.exists()) {
                ArrayList<Project> fileList= new ArrayList<>();
                fileList.addAll(ProjectsFileIO.deSerializeFromFile(projectsFile));
                projectsManager.setProjects(fileList);
                couldReadFile = true;
            }

            MainUI ui = new MainUI(projectsManager);
            ui.mainLoop();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Could not load projects from file, please check the data file.");
            System.out.println("Continuing with empty manager.");
        }

        // run method about to exit - save data
        if(couldReadFile || !projectsFile.exists()) {

            ArrayList<Project> fileSaveList= new ArrayList<>();

            fileSaveList.addAll(projectsManager.getProjects());
            ProjectsFileIO.serializeToFile(projectsFile, fileSaveList);
        }
        System.out.println("Application exits");
    }

    public static void main(String[] args) throws Exception {

        ProjectApp app = new ProjectApp();
        app.run();
    }
}
