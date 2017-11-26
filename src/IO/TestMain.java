/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import Enum.Gender;
import Model.Project;
import Model.Student;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author BN
 */
public class TestMain {
    private ArrayList<Project>  projects = new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Project> projects = ProjectFile.readFile("src/DataFile/projects.txt");
        System.out.println(projects);
        //ProjectFile.writeFile(projects,"src/DataFile/output.txt");
    }
    
    public void addStudent(String admissionNo, String name, String course, Gender gender){
        Student s = new Student(admissionNo, name, course, gender);
        Project currentProject = new Project("21","","");
        currentProject.addStudent(s);
    }
    
    public void refresh(){
        //load from the txt file again
    }
    
    public void saveToFile(){
        
    }
    
    public void addProject(String projectTitle, String school, String supervisorName){
        Project p = new Project(projectTitle,school,supervisorName);
        p.addStudent(new Student("123","beining","DIT",Gender.FEMALE));
        projects.add(p);
    }
    
    public void updateProject(String projectTitle, String school, String supervisorName){
        Project currentProject = new Project("21","","");
        currentProject.setProjectTitle(projectTitle);
        currentProject.setSchool(school);
        currentProject.setSupervisorName(supervisorName);
    }
    
    public void updateStudent(int position,String admissionNo, String name, String course, Gender gender){
        Project currentProject = new Project("21","","");
        currentProject.getStudents().get(position).setAdmissionNo(admissionNo);
        currentProject.getStudents().get(position).setName(name);
        currentProject.getStudents().get(position).setCourse(course);
        currentProject.getStudents().get(position).setGender(gender);
        
    }
 
}
