/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author BN
 */
public class LinkList implements Serializable{

    private int noOfElement = 0;		// Number of elements in the list
    private ListNode headnode = null;	// Headnode of the LinkList

    public LinkList() {
    }

    public LinkList(Object data) {

        add(0, data);

    }

// Checks whether the LinkList is empty
    public boolean isEmpty() {

        // You are required to complete this method.
        return (headnode == null);

    }
       
    // Checks the number of nodes in the link list
    public int getNoOfElement() {

// You are required to complete this method.
        return noOfElement;

    }

// Finds a specific node in a linked list. 
    private ListNode find(int index) {

        ListNode currnode = headnode;

        for (int i = 0; i < index; i++) {
            currnode = currnode.getNext();
        }

        return currnode;
    }

// Returns the data object at a particular location in the Link List
    public Object get(int index) throws IndexOutOfBoundsException {

        if (index >= 0 && index < noOfElement) {

            ListNode currnode = find(index);
            Object data = currnode.getData();
            return data;

        } else {

            throw new IndexOutOfBoundsException(
                    "index out of bounds exception on get");
        }
    }

// Adds a data object to the link list at the location specified by index
    public void add(int index, Object data) throws IndexOutOfBoundsException {

        if (index >= 0 && index <= noOfElement) {

            if (index == 0) {

// insert new node at beginning of list
                ListNode newnode = new ListNode(data, headnode);
                headnode = newnode;
                

            } else {

// insert between nodes
                ListNode prevnode = find(index - 1);
                ListNode newnode = new ListNode(data, prevnode.getNext());
                prevnode.setNext(newnode);

            } // end if

            noOfElement++;

        } else {
            throw new IndexOutOfBoundsException("index out of bounds exception on add");
        }
    }

// Adds a data object at the end of the link list
    public void addLast(Object data) throws IndexOutOfBoundsException {
        add(noOfElement, data);

    }

// Destroys all the nodes in the link list
    public void removeAll() {
        headnode = null;
        noOfElement = 0;
    }

// Deletes a particular node in the link list 
    public void remove(int index) throws IndexOutOfBoundsException {

        if (index >= 0 && index < noOfElement) {

            if (index == 0) {

// delete the first node from the list
                headnode = headnode.getNext();

            } else {

// delete the node
                ListNode prevnode = find(index - 1);
                ListNode currnode = prevnode.getNext();
                prevnode.setNext(currnode.getNext());

            }  // end-if

            noOfElement--;

        } else {
            throw new IndexOutOfBoundsException(
                    "index out of bounds exception on remove");
        } // end-if

    } // end-method
    
    public void removeRange(int startIndex, int numberOfNodes){
         if (startIndex >= 0 && startIndex < noOfElement && startIndex+numberOfNodes < noOfElement) {

            if (startIndex == 0) {
                
                for(int i=0;i< numberOfNodes;i++){
                    headnode = headnode.getNext();
                }
                

            } else {

                ListNode prevnode = find(startIndex - 1);
                ListNode endNode = find(startIndex + numberOfNodes);
                prevnode.setNext(endNode);

            }  // end-if

            noOfElement-=numberOfNodes;

        } else {
            throw new IndexOutOfBoundsException(
                    "index out of bounds exception on remove");
        } // end-if
    }
 
} // end LinkList

