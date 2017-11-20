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
    private ArrayList<Student> students = new ArrayList<Student>();
    
    public Project(String projectTitle, String school, String supervisorName){
        this.projectTitle = projectTitle;
        this.school = school;
        this.supervisorName = supervisorName;
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
        return students.size();
    }


    public ArrayList<Student> getStudents() {
        return students;
    }

    
    public void addStudent(Student student){       
        students.add(student);        
    }
    
    public void deleteStudent(int position){
        students.remove(position);
    }
    
    
}
