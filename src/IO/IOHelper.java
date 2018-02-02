/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Enum.Gender;
import Model.Event;
import Model.EventCollection;
import Model.Project;
import Model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author BN
 */
public class IOHelper {

    public IOHelper() {
    }

    public List<Project> readProjectListFile(String fileName) throws FileNotFoundException {
        BufferedReader br;
        String line;
        int numOfProject;
        List<Project> projects;

        try {
            br = new BufferedReader(new FileReader(fileName));

            String firstLine = br.readLine();
            numOfProject = Integer.parseInt(firstLine);
            projects = new ArrayList<>();
            line = br.readLine();

            for (int u = 0; u < numOfProject; u++) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {//process tokens in line
                    String projectTitle = tokenizer.nextToken();
                    String school = tokenizer.nextToken();
                    String supervisorName = tokenizer.nextToken();
                    int numOfStudents = Integer.parseInt(tokenizer.nextToken());
                    Project project = new Project(projectTitle, school, supervisorName);
                    for (int i = 0; i < numOfStudents; i++) {
                        String admissionNo = tokenizer.nextToken();
                        String name = tokenizer.nextToken();
                        String course = tokenizer.nextToken();
                        String g = tokenizer.nextToken();
                        Gender gender = Gender.MALE;
                        if (g.equals("F")) {
                            gender = Gender.FEMALE;
                        }
                        Student student = new Student(admissionNo, name, course, gender);
                        project.addStudent(student);
                    }
                    projects.add(project);
                }//close inner while

                line = br.readLine();
            }
            return projects;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public void saveToProjectListFile(List<Project> projects, String fileName) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String content = "";
        content += (projects.size() + "\r\n");

        for (int i = 0; i < projects.size(); i++) {
            content += projects.get(i).getProjectTitle();
            content += "," + projects.get(i).getSchool();
            content += "," + projects.get(i).getSupervisorName();
            List<Student> students = projects.get(i).getStudents();
            content += "," + students.size();
            for (int u = 0; u < students.size(); u++) {
                content += "," + students.get(u).getAdmissionNo();
                content += "," + students.get(u).getName();
                content += "," + students.get(u).getCourse();
                if (students.get(u).getGender() == Gender.MALE) {
                    content += ",M";
                } else {
                    content += ",F";
                }

            }
            content += "\r\n";

        }

        try {

            File file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

    public void writeOneProjectToFile(Project project, String fileName, String fileType) {
        File file = new File(fileName);

        if ("TXT".equals(fileType)) {
            BufferedWriter bw = null;
            FileWriter fw = null;
            String content = "";

            content += "Title:\t\t" + project.getProjectTitle() + "\r\n";
            content += "School:\t\t" + project.getSchool() + "\r\n";
            content += "Supervisor:\t" + project.getSupervisorName() + "\r\n";
            List<Student> students = project.getStudents();
            content += "Students:\t";
            for (int u = 0; u < students.size(); u++) {
                content += students.get(u).getName();
                if (u + 1 != students.size()) {
                    content += "  ==>  ";
                }
            }
            content += "\r\n---------------\r\n";

            try {
                if (!file.exists()) {
                    file.createNewFile();
                }

                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                bw.write(content);

            } catch (IOException e) {
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Error in closing the BufferedWriter" + ex);
                }
            }
        } else {

            try {
                ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
                outStream.writeObject(project);
                outStream.close();
            } catch (IOException ex) {
            }
        }
    }

    public EventCollection readEventCollection(String folderDir) {
        EventCollection eventCollection = new EventCollection();
        File dir = new File(folderDir);
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                try {
                    String eventTitle = file.getName();
                    List<Project> projects = readProjectListFile(file.getPath());
                    Event event = new Event();
                    event.setEventTitle(eventTitle);
                    for(int i=0;i<projects.size();i++){
                        event.addProject(projects.get(i));
                    }
                    eventCollection.addEvent(event);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
               
            }
        }
        return eventCollection;
    }
    
    public void serializedEvent(Event event) throws FileNotFoundException, IOException{
        FileOutputStream fileOut =
         new FileOutputStream("src/DataFile/"+event.getEventTitle()+".ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(event);

         out.close();
         fileOut.close();
    }
    
    public Event deserializedEvent(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fileIn = new FileInputStream(fileName);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         Event event = new Event();
         event = (Event) in.readObject();
         in.close();
         fileIn.close();    
         return event;
    }
    
        public void serializedEventCollection(EventCollection eventCollection) throws FileNotFoundException, IOException{
        FileOutputStream fileOut =
         new FileOutputStream("src/DataFile/eventCollection.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(eventCollection);

         out.close();
         fileOut.close();
    }
    
    public EventCollection deserializedEventCollection(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fileIn = new FileInputStream(fileName);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         EventCollection eventCollection = new EventCollection();
         eventCollection = (EventCollection) in.readObject();
         in.close();
         fileIn.close();
         return eventCollection;
    }

}
