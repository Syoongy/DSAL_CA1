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
import utils.LinkList;
import utils.Queue;

/**
 *
 * @author BN
 */
public class Test {

    public static void main(String[] args) {
        IOHelper pf = new IOHelper();
        EventCollection eventCollection = new EventCollection();
        eventCollection = pf.readEventCollection("src/DataFile/EventCollection");
        //serialized and deserialize event
//            try{             
//                  pf.serializedEvent(eventCollection.getEvent(0));
//                 System.out.println(eventCollection.getEvent(0).getEventTitle());              
//                 Event e = pf.deserializedEvent("src/DataFile/"+eventCollection.getEvent(0).getEventTitle()+".ser");
//            } catch (IOException ex) {
//              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            
//          } catch (ClassNotFoundException ex) {
//              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//          }
//         serialized and deserialized eventCollection
//                 try{             
//                  pf.serializedEventCollection(eventCollection);
//                     
//                 EventCollection e = pf.deserializedEventCollection("src/DataFile/eventCollection.ser");
//            } catch (IOException ex) {
//              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//            
//          } catch (ClassNotFoundException ex) {
//              Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//          }

//sorting
//            LinkList ls = sortProjectListByNumberOfStudentAsc(eventCollection.getEvent(0).getProjectList());
//            for(int i=0;i<ls.getNoOfElement();i++){
//               Project p = (Project)ls.get(i);
//                System.out.println(p.getNumberOfStudent());
//            }
//                 LinkList ls = sortEventListByNumberOfProjectAsc(eventCollection.getEventList(),1);
//                  for(int i=0;i<ls.getNoOfElement();i++){
//               Event e = (Event)ls.get(i);
//                System.out.println(e.getNumberOfProject());
//            }
//                                 LinkList ls1 = sortEventListByNumberOfProjectDesc(eventCollection.getEventList(),1);
//                  for(int i=0;i<ls1.getNoOfElement();i++){
//               Event e = (Event)ls1.get(i);
//                System.out.println(e.getNumberOfProject());
//            }
    }

    public static LinkList searchProjectByTitle(LinkList projectList,String searchStr){
        LinkList result = new LinkList();
        int numOfProject = projectList.getNoOfElement();
        for(int i=0;i<numOfProject;i++){
            Project p = (Project) projectList.get(i);
            if(p.getProjectTitle().contains(searchStr)){
                result.addLast(p);
            }
        }
        return result;
    } 
    
        public static LinkList searchEventByTitle(LinkList eventList,String searchStr){
        LinkList result = new LinkList();
        int numOfEvent = eventList.getNoOfElement();
        for(int i=0;i<numOfEvent;i++){
           Event e = (Event) eventList.get(i);
            if(e.getEventTitle().contains(searchStr)){
                result.addLast(e);
            }
        }
        return result;
    } 
        
    public static LinkList sortProjectListByNumberOfStudentAsc(LinkList projectList) {
        //Radix sort
        //we assume that one project will not have more than 9 students

        // Step 1: Create 10 Queues
        Queue[] queues = new Queue[10];

        for (int i = 0; i < 10; i++) {
            queues[i] = new Queue();
        }

  

        int numberOfProject = projectList.getNoOfElement();
        for (int i = 0; i < numberOfProject; i++) {
            Project p = (Project) projectList.get(i);
            queues[p.getNumberOfStudent()].enqueue(p);
        }
        projectList.removeAll();
        for (int k = 0; k < 10; k++) {
            while (!queues[k].isEmpty()) {
                projectList.addLast((Project) queues[k].dequeue());
            }
        }
        return projectList;
    }

    public static LinkList sortProjectListByNumberOfStudentDesc(LinkList projectList) {
        //Radix sort
        //we assume that one project will not have more than 9 students

        // Step 1: Create 10 Queues
        Queue[] queues = new Queue[10];

        for (int i = 0; i < 10; i++) {
            queues[i] = new Queue();
        }

        int divisor = (int) Math.pow(10, 1);

        int numberOfProject = projectList.getNoOfElement();
             for (int i = 0; i < numberOfProject; i++) {
            Project p = (Project) projectList.get(i);
            queues[p.getNumberOfStudent()].enqueue(p);
        }
        projectList.removeAll();
        for (int k = 0; k < 10; k++) {
            while (!queues[k].isEmpty()) {
                projectList.add(0, (Project) queues[k].dequeue());
            }
        }
        return projectList;
    }
    
        public static LinkList sortEventListByNumberOfProjectAsc(LinkList eventList,int numOfDigitsOfMaxNumberOfProjects) {
        //Radix sort

        // Step 1: Create 10 Queues
        Queue[] queues = new Queue[10];

        for (int i = 0; i < 10; i++) {
            queues[i] = new Queue();
        }

          // Step 2: Process each digit in the number
        for (int u = 0; u < numOfDigitsOfMaxNumberOfProjects; u++) {
            int divisor = (int) Math.pow(10, u);

        int numberOfProject = eventList.getNoOfElement();
        for (int i = 0; i < numberOfProject; i++) {
            Event e = (Event) eventList.get(i);
            int qNo = (e.getNumberOfProject() / divisor) % 10;
            queues[qNo ].enqueue(e);
        }
        eventList.removeAll();
        for (int k = 0; k < 10; k++) {
            while (!queues[k].isEmpty()) {
                eventList.addLast((Event) queues[k].dequeue());
            }
        }
        }
        return eventList;
    }

         public static LinkList sortEventListByNumberOfProjectDesc(LinkList eventList,int numOfDigitsOfMaxNumberOfProjects) {
        //Radix sort
               //Radix sort

        // Step 1: Create 10 Queues
        Queue[] queues = new Queue[10];

        for (int i = 0; i < 10; i++) {
            queues[i] = new Queue();
        }

          // Step 2: Process each digit in the number
        for (int u = 0; u < numOfDigitsOfMaxNumberOfProjects; u++) {
            int divisor = (int) Math.pow(10, u);

        int numberOfProject = eventList.getNoOfElement();
        for (int i = 0; i < numberOfProject; i++) {
            Event e = (Event) eventList.get(i);
            int qNo = (e.getNumberOfProject() / divisor) % 10;
            queues[qNo ].enqueue(e);
        }
        eventList.removeAll();
        for (int k = 0; k < 10; k++) {
            while (!queues[k].isEmpty()) {
               eventList.add(0, (Event) queues[k].dequeue());
            }
        }
        }
        return eventList;
    }

    public List<Student> search(Project p, String searchString) {
        List<Student> students = p.searchStudent("a");
        return students;
    }

    public void addStudent(String admissionNo, String name, String course, Gender gender) {
        Student s = new Student(admissionNo, name, course, gender);
        Project currentProject = new Project("21", "", "");
        currentProject.addStudent(s);
    }

    public void refresh() {
        //load from the txt file again
    }

    public void saveToFile() {

    }

    public void addProject(String projectTitle, String school, String supervisorName) {
        Project p = new Project(projectTitle, school, supervisorName);
        p.addStudent(new Student("123", "beining", "DIT", Gender.FEMALE));

    }

    public void updateProject(String projectTitle, String school, String supervisorName) {
        Project currentProject = new Project("21", "", "");
        currentProject.setProjectTitle(projectTitle);
        currentProject.setSchool(school);
        currentProject.setSupervisorName(supervisorName);
    }

    public void updateStudent(int position, String admissionNo, String name, String course, Gender gender) {
        Project currentProject = new Project("21", "", "");
        currentProject.getStudents().get(position).setAdmissionNo(admissionNo);
        currentProject.getStudents().get(position).setName(name);
        currentProject.getStudents().get(position).setCourse(course);
        currentProject.getStudents().get(position).setGender(gender);

    }
}
