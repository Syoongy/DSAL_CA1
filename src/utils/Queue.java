/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author BN
 */
public class Queue{

    private LinkList list = null;

    public Queue() {
        list = new LinkList();
    }

    public boolean isEmpty(){
      return list.isEmpty();
    }

    public void enqueue(Object obj){
            list.addLast(obj);
    }

    public Object dequeue() throws RuntimeException {

        if (!isEmpty()) {
          Object obj = list.get(0);
          list.remove(0);
          return obj;
        }
        else
          throw new RuntimeException(
                    "Queue empty. Nothing to dequeue.");
    }

    public void display(){
        for (int i=0; i<list.getNoOfElement();i++){
            System.out.print(list.get(i) + "==>");
        }
        System.out.println("null");
    }

    public int getQueueSize() {
        return list.getNoOfElement();
    }

    public void dequeueAll() {
        list.removeAll();
    }

}
