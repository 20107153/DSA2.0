package org.example.demo.models;

public class Entry<K, V> {
    K key;
    V value;
    Entry(K key, V value){
        this.key = key;
        this.value = value;
    }
}
