//package org.example.demo.controllers;
//
//import org.example.demo.models.Entry;
//
//import java.util.LinkedHashMap;
//
//public class HashTable<K, V> {
//
//    private LinkedList<Entry<K, V>>[] buckets;
//    private int size;
//    private static final int defaultCapacity = 11;
//    private static final double loadFactor = 0.75;
//
//    public HashTable() {
//        buckets= new LinkedList[defaultCapacity];
//        for(int i = 0; i < buckets.length; i++) {
//            buckets[i] = new LinkedList<>();
//        }
//        size = 0;
//    }
//
//    private int getBucketIndex(K key){
//        return Math.abs(key.hashCode() % buckets.length);
//    }
//
//    public void put(K key, V value) {
//        int index = getBucketIndex(key);
//        for (Entry<K, V> entry : buckets[index]) {
//            if (entry.getKey().equals(key)) {
//                entry.setValue(value); // Update existing value
//                return;
//            }
//        }
//        buckets[index].add(new Entry<>(key, value)); // Add new entry
//        size++;
//        if ((double) size / buckets.length > loadFactor) {
//            resize();
//        }
//    }
//
//    public V get(K key) {
//        int index = getBucketIndex(key);
//        for (Entry<K, V> entry : buckets[index]) {
//            if (entry.getKey().equals(key)) {
//                return entry.getValue();
//            }
//        }
//        return null; // Key not found
//    }
//
//    public void remove(K key) {
//        int index = getBucketIndex(key);
//        buckets[index].removeIf(entry -> entry.key.equals(key));
//    }
//
//    private void resize() {
//        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
//        buckets = new LinkedList[oldBuckets.length * 2];
//        for (int i = 0; i < buckets.length; i++) {
//            buckets[i] = new LinkedList<>();
//        }
//        size = 0;
//        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
//            for (Entry<K, V> entry : bucket) {
//                put(entry.key, entry.value);
//            }
//        }
//    }
//
//    public int size() {
//        return size;
//    }
//
//
//}
