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
public class ListStack {

    private LinkList data;

    public ListStack() {
        data = new LinkList();
    }

    public boolean isEmpty() {
        return (data.isEmpty());
    }

    public void push(Object newData) {
        data.add(0, newData);
    }

    public Object pop() throws RuntimeException {
        if (!data.isEmpty()) {
            Object temp = data.get(0);
            data.remove(0);
            return temp;
        } else {
            throw new RuntimeException("RuntimeException on "
                    + "pop: stack empty");
        }
    }

    public void popAll() {
        data.removeAll();
    }

    public Object peek() throws RuntimeException {
        if (!isEmpty()) {
            return data.get(0);
        } else {
            throw new RuntimeException("RuntimeException on "
                    + "peek: stack empty");
        }
    }



}
