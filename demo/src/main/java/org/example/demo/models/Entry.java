package org.example.demo.models;

import java.io.Serializable;

public class Entry<K, V> implements Serializable {

    private K key;
    private V value;
    private Entry<K, V> next;


    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setNext(Entry<K, V> next){
        this.next=next;
    }
    public Entry getNext(){
        return next;
    }

    //Since the hashTable will only call the toString of first value,
    // the toString of an Entry will include all that follows
    //
    //I have no clue is string builder is allowed but this code was the only thing that would work
    @Override
    public String toString() {
        Entry<K,V> temp = this;
        String sb ="";
        while (temp != null) {
            sb+=(temp.key + " -> " + temp.value + ", ");
            temp = temp.next;
        }
        return sb;
    }

    public String thisString(){
        String st ="";
        st+=(this.key + " -> "+ this.value);
        return st;
    }
}
