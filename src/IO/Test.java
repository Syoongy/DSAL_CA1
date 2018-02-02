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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BN
 */
public class Test {
      public static void main(String[] args) {
            IOHelper pf = new IOHelper();
            EventCollection eventCollection = new EventCollection();
            eventCollection = pf.readEventCollection("src/DataFile/EventCollection");          
            try{             
                  pf.serializedEvent(eventCollection.getEvent(0));
                 System.out.println(eventCollection.getEvent(0).getEventTitle());              
                 Event e = pf.deserializedEvent("src/DataFile/"+eventCollection.getEvent(0).getEventTitle()+".ser");
            } catch (IOException ex) {
              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
          }
         

    }
      
      
      
      public List<Student> search(Project p,String searchString){
          List<Student> students = p.searchStudent("a");
          return students;
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
