package org.example.demo.models;

public class Node<T>{
    int index=-1;
    T object;
    Node<T> next= null;

    public Node(int index, T object){
        this.index=index;
        this.object=object;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

