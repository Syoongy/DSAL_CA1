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

public class ListNode implements Serializable{

private ListNode next = null;	//Self referential node
private Object data = null;	//The Data Segment

// Constrcutor:Create a new node that reference to an object
public ListNode(Object obj) {
this(obj, null);
}

// Creates a new node that reference to listNode object
public ListNode(Object obj, ListNode nextNode) {
data = obj; 		//referencing to a Java Object
next = nextNode;	//referencing the next node
}

public void setData(Object newData) {
	data = newData;
}

public Object getData() {
return data;
}

public void setNext(ListNode next) {
this.next = next;
}

public ListNode getNext() {
return next;
}
}
