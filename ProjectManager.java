package model;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Represents logic and data for project managing
 */
public class ProjectManager {



    private int nextProjectId = 1000;
    private ArrayList<Project> projectList;


    /** Constructs new list of projects*/
    public ProjectManager() {
        projectList = new ArrayList<>();

    }


    /** Finds the projects that matched the id received*/
    public Project getProjectById(int id){


        for(int i=0; i<projectList.size(); i++){
            if(id == projectList.get(i).getId()){
                return projectList.get(i);
            }
        }

        return null;
    }


    /** Adds project to the end of the list of all projects and checks that the
     * project has a unique name
     *
     * @param title the title of the project
     * @param desc the description of the project
     * @return addedProject the project added to the list
     */
    public Project addProject(String title, String desc){
        isTitleUnique(title);
        Project addedProject = new Project(title, nextProjectId,desc);
        nextProjectId++;
        projectList.add(addedProject);
        return addedProject;
    }

    public boolean removeProject(Project project){
        return projectList.remove(project);
    }


    /** Makes sure the list of projects is cleared before adding saved ones from the file */
    public void setProjects(ArrayList<Project> incomingProjects){
        projectList.clear();
        projectList.addAll(incomingProjects);
        nextProjectId = incomingProjects.get(incomingProjects.size()-1).getId() + 1 ;
    }


    private int getHighestId(){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=0; i<projectList.size(); i++){
            tmp.add(projectList.get(i).getId());
        }
        Collections.sort(tmp);

        return tmp.get(tmp.size()-1);
    }


    /** Makes sure the title added is unique and throws an exception otherwise*/
    public boolean isTitleUnique(String title) {

        projectList.forEach((n) -> {
            if (n.getTitle().compareTo(title) == 0) {
                throw new TitleNotUniqueException();
            }
        });

        return true;
    }


    /** Finds projects by matching substring in titles*/
    public ArrayList<Project> findProjects(String title){
        ArrayList<Project> matchedProjects = new ArrayList<>();

        for(int i=0; i<projectList.size(); i++){
            if(projectList.get(i).getTitle().contains(title)){
                matchedProjects.add(projectList.get(i));
            }
        }

        return matchedProjects;
    }


    /** Returns a copy of all projects*/
    public ArrayList<Project> getProjects() {
        ArrayList<Project> Cpy = new ArrayList<>();

        for(int i=0; i<projectList.size(); i++){
            Cpy.add(projectList.get(i));
        }

        return Cpy;
    }

    @Override
    public String toString() {
        return "ProjectManager{" +
                "nextProjectId=" + nextProjectId +
                ", projectList=" + projectList +
                '}';
    }
}
