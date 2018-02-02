/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Enum.Gender;
import java.io.Serializable;


/**
 *
 * @author BN
 */
public class Student implements Serializable{
    private String admissionNo;
    private String name;
    private String course;
    private Gender gender;
    
    public Student(String admissionNo, String name, String course, Gender gender){
        this.admissionNo = admissionNo;
        this.name = name;
        this.course = course;
        this.gender = gender;            
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
          
}
