package org.example.demo.controllers;

import org.example.demo.models.Node;

public class LinkedList<T>{
    private Node<T> head;
    private int indexCounter=0;

    public int getIndexCounter() {
        return indexCounter;
    }

    /***
     * Input any class object, and it will attatch it to a node to be placed in the linked list
     * @param item
     */
    public void add(T item){
        Node<T> nodeItem = new Node<>(indexCounter, item);
        indexCounter++;
        if (head==null){
            head = nodeItem;
        } else {
            Node<T> n = head;
            while (n.getNext()!=null){
                n=n.getNext();
            }
            n.setNext(nodeItem);

        }
    }

    /***
     * Takes in an index and compares it to all the nodes
     * @param index
     * @return
     */
    public Node<T> getNode(int index){
        Node<T> node = head;
        while (node.getIndex()!=index){
            if (node.getIndex()==index){
                return node;
            }
            node = node.getNext();
        }
        if (node.getIndex()==index){
            return node;
        } else return null;
    }
    public T getObject(int index){
        Node<T> node = head;
        while (node.getIndex()!=index){
            if (node.getIndex()==index){
                return node.getObject();
            }
            node = node.getNext();
        }
        if (node.getIndex()==index){
            return node.getObject();
        } else return null;
    }


    public String listAll(){
        Node<T> node = head;
        String str="";
        while (node.getNext()!=null){
            str+=node.getIndex()+") ";
            str+=node.getObject();
            str+="\n";
            node=node.getNext();
        }
        str+=node.getIndex()+") ";
        str+=node.getObject();
        if (str.equals("")){
            return "No items in list";
        } else {
            return str;
        }
    }

    public void deleteNode(int indexToDelete){
        try {
            if(indexToDelete==0){
                head=head.getNext();
            } else {
                Node<T> current = head;
                Node<T> previous = head;
                while (current.getIndex()!=indexToDelete){
                    previous = current;
                    current = current.getNext();
                }
                previous.setNext(current.getNext());
            }

        } catch (Exception e){
            System.out.println("index out of bounds");
        }
    }

}
