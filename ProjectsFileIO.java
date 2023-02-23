package io;

import model.Project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, ArrayList<Project> data) throws IOException {

        ObjectOutputStream oos = null;
        try{
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);

            oos.writeObject(data);

            System.out.println("Completed serialization");
        } finally {
            try {
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {

            }
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {

        ObjectInputStream ois = null;
        ArrayList<Project> data = new ArrayList<>();

        try{

            FileInputStream fin = new FileInputStream(file);
            ois = new ObjectInputStream(fin);

            data = (ArrayList<Project>) ois.readObject();

            System.out.println(data.toString());

        } catch (ClassNotFoundException e){
            System.out.println("Does not exist");
            throw e;
        } finally{
            try{
                if(ois != null){
                    ois.close();
                }
            } catch (IOException e){

            }
        }
        return data;
    }

    private ProjectsFileIO() {}
}
