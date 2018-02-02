/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import utils.LinkList;

/**
 *
 * @author BN
 */
public class Event implements Serializable{
    private String eventTitle = "";
    private LinkList projectList;
    private int currProject;
    private int numberOfProject;
    
    public Event() {
        projectList = new LinkList();
        currProject = 0;
        numberOfProject = 0;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public int getNoOfProjects() {
        return numberOfProject;
    }

    public Project getProject(int index) {
        return (Project) projectList.get(index);
    }

    public void addProject(Project p) {
        numberOfProject++;
        projectList.addLast(p);
    }

    public Project getNext() {
        if (currProject < getNoOfProjects() - 1) {
            currProject++;
        }

        return (Project) projectList.get(currProject);
    }

    public Project getPrev() {
        if (currProject > 0) {
            currProject--;
        }

        return (Project) projectList.get(currProject);
    }

    public Project getLast() {
        currProject = getNoOfProjects() - 1;
        return (Project) projectList.get(currProject);
    }

    public Project getFirst() {
        currProject = 0;
        return (Project) projectList.get(currProject);
    }

   

}
