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
public class EventCollection implements Serializable{

    private LinkList eventList;
    private int currEvent;

    public EventCollection() {
        eventList = new LinkList();
        currEvent = 0;
    }

    public LinkList getEventList() {
        return eventList;
    }

    public void setEventList(LinkList eventList) {
        this.eventList = eventList;
    }

    public int getNoOfEvents() {
        return eventList.getNoOfElement();
    }

    public Event getEvent(int index) {
        return (Event) eventList.get(index);
    }

    public void addEvent(Event e) {
        eventList.addLast(e);
    }


    public int getNext() {
        if (currEvent < getNoOfEvents() - 1) {
            currEvent++;
        }

        return currEvent;
    }

    public int getPrev() {
        if (currEvent > 0) {
            currEvent--;
        }

        return currEvent;
    }

    public int getLast() {
        currEvent = getNoOfEvents() - 1;
        return currEvent;
    }

    public int getFirst() {
        currEvent = 0;
        return currEvent;
    }

}


