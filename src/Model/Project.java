/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enum.Gender;
import java.util.ArrayList;

/**
 *
 * @author BN
 */
public class Project {
    private String projectTitle;
    private String school;
    private String supervisorName;
    private int numOfStudents;
    private ArrayList<Student> students = new ArrayList<Student>();
    
    public Project(String projectTitle, String school, String supervisorName, int numOfStudents){
        this.projectTitle = projectTitle;
        this.school = school;
        this.supervisorName = supervisorName;
        this.numOfStudents = numOfStudents;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    
    public void addStudent(String admissionNo, String name, String course, Gender gender){
        Student student = new Student(admissionNo, name, course, gender);
        students.add(student);        
    }
    
    
}
