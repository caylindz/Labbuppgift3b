package model;

import java.util.ArrayList;
import java.util.Collections;

public class ProjectManager {

    private int nextProjectId = 10000;
    private ArrayList<Project> projectList;

    public ProjectManager(int nextProjectId) {
        projectList = new ArrayList<>();

    }


    public Project getProjectById(int id){


        for(int i=0; i<projectList.size(); i++){
            if(id == projectList.get(i).getId()){
                return projectList.get(i);
            }
        }

        return null;
    }


    public Project addProject(String title, String desc){
        isTitleUnique(title);
        Project addedProject = new Project(title, nextProjectId,desc);
        nextProjectId++;
        return addedProject;
    }

    public boolean removeProject(Project project){
        return projectList.remove(project);
    }


    public void setProjects(ArrayList<Project> incomingProjects){
        //TODO check if title unique needs to be checked before added
        projectList.clear();
        projectList.addAll(incomingProjects);
    }


    private int getHighestId(){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i=0; i<projectList.size(); i++){
            tmp.add(projectList.get(i).getId());
        }
        Collections.sort(tmp);

        return tmp.get(tmp.size()-1);
    }

    public boolean isTitleUnique(String title) {


        projectList.forEach((n) -> {
            if (n.getTitle().compareTo(title) == 0) {
                throw new TitleNotUniqueException();
            }
        });

        return true;

        /*
        int sameTitle = 5;
        for(int i=0; i<projectList.size(); i++){
            int sameTitle = projectList.get(i).getTitle().compareTo(title);
        }

        if(sameTitle==0){
            throw new TitleNotUniqueException("Title Exists Already" );
           }
            */
    }

    public ArrayList<Project> findProjects(String title){
        ArrayList<Project> matchedProjects = new ArrayList<>();

        for(int i=0; i<projectList.size(); i++){

            if(projectList.get(i).getTitle().compareTo(title)==0){
                matchedProjects.add(projectList.get(i));
            }
        }

        return matchedProjects;
    }

}
