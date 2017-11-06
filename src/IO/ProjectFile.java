/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Enum.Gender;
import Model.Project;
import Model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author BN
 */
public class ProjectFile {

    public static Project[] readFile(String fileName) throws FileNotFoundException {
        BufferedReader br;
        String line;
        int numOfProject;
        Project[] projects;

        try {
            br = new BufferedReader(new FileReader(fileName));

            String firstLine = br.readLine();
            numOfProject = Integer.parseInt(firstLine);
            projects = new Project[numOfProject];
            line = br.readLine();

            for (int u = 0; u < numOfProject; u++) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                while (tokenizer.hasMoreTokens()) {//process tokens in line
                    String projectTitle = tokenizer.nextToken();
                    String school = tokenizer.nextToken();
                    String supervisorName = tokenizer.nextToken();
                    int numOfStudents = Integer.parseInt(tokenizer.nextToken());
                    Project project = new Project(projectTitle, school, supervisorName, numOfStudents);
                    for (int i = 0; i < numOfStudents; i++) {
                        String admissionNo = tokenizer.nextToken();
                        String name = tokenizer.nextToken();
                        String course = tokenizer.nextToken();
                        String g = tokenizer.nextToken();
                        Gender gender = Gender.MALE;
                        if (g.equals("F")) {
                            gender = Gender.FEMALE;
                        }
                        project.addStudent(admissionNo, name, course, gender);
                    }
                    projects[u] = project;
                }//close inner while

                line = br.readLine();
            }
            return projects;

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;

    }

    public static void writeFile(Project[] projects,String fileName) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String content = "";

        for (int i = 0; i < projects.length; i++) {
            content += "Title:\t\t" + projects[i].getProjectTitle() + "\r\n";
            content += "School:\t\t" + projects[i].getSchool() + "\r\n";
            content += "Supervisor:\t" + projects[i].getSupervisorName() + "\r\n";
            List<Student> students = projects[i].getStudents();
            content += "Students:\t";
            for (int u = 0; u < students.size(); u++) {
                content += students.get(u).getName();
                if (u + 1 != students.size()) {
                    content += "  ==>  ";
                }
            }
            content += "\r\n---------------\r\n";
           
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
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }
}
