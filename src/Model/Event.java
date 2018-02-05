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
import utils.Queue;

/**
 *
 * @author BN
 */
public class Event implements Serializable{
    private String eventTitle = "";
    private LinkList projectList;
    private int currProject;
    
    public Event() {
        projectList = new LinkList();
        currProject = 0;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
    
    public boolean hasProject(Project p) {
        for(int i = 0; i < projectList.getNoOfElement(); i++) {
            if(projectList.get(i) == p) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfProject() {
        return projectList.getNoOfElement();
    }

    public LinkList getProjectList() {
        return projectList;
    }

    public void setProjectList(LinkList projectList) {
        this.projectList = projectList;
    }

    public Project getProject(int index) {
        return (Project) projectList.get(index);
    }

    public void addProject(Project p) {
        projectList.addLast(p);
    }

    public Project getNext() {
        if (currProject < getNumberOfProject() - 1) {
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
        currProject = getNumberOfProject() - 1;
        return (Project) projectList.get(currProject);
    }

    public Project getFirst() {
        currProject = 0;
        return (Project) projectList.get(currProject);
    }
    


    
   

}
