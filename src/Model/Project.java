/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BN
 */
public class Project implements Serializable{
    private String projectTitle;
    private String school;
    private String supervisorName;
    private int numberOfStudent;
    private List<Student> students = new ArrayList<Student>();
    
    public Project(String projectTitle, String school, String supervisorName){
        this.projectTitle = projectTitle;
        this.school = school;
        this.supervisorName = supervisorName;
        this.numberOfStudent = 0;
    }

    public int getNumberOfStudent(){
        return numberOfStudent;
    }
    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudent(int i){
        return students.get(i);
    }
    public void addStudent(Student student){  
        numberOfStudent++;
        students.add(student);        
    }
    
    public void deleteStudent(int position){
        students.remove(position);
    }
    
    public List<Student> searchStudent(String searchStr){
        List<Student> result = new ArrayList();
        for(int i=0;i<students.size();i++){
            if(students.get(i).getName().toLowerCase().contains(searchStr.toLowerCase())){
                  result.add(students.get(i));
            }
        }
        return result;
    }
    
}
