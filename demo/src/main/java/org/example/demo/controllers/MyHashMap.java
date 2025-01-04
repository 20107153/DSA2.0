package org.example.demo.controllers;

import org.example.demo.models.Entry;

import java.util.Arrays;

public class MyHashMap<K,V> {
    private final int SIZE = 11;
    private Entry<K,V> table[]; //Initialised in constructor



    public MyHashMap(){
        table = new Entry[SIZE];
    }

    /**
     * hashString() takes in a key and returns an int hash
     *              -if it's a string it's done by adding up the values of each character to the hash int,
     *               then performing an equation to make it "unique enough"
     *              -if it's an integer the int itself just becomes the hash (easiest route)
     *
     *              i don't see a reason for writing a hash function for any other datatype i don't think it will be used
     */
    public int hashKey(K kKey){
        int hash = 0;
        if (kKey instanceof String){
            String sKey = (String) kKey;

            for (int i=0;i<sKey.length();i++){
                hash += sKey.charAt(i);
            }
            //multiply value of all characters by the last character - the length^2 to get a more unique hash
            hash *= (sKey.charAt(sKey.length()-1) - (sKey.length() * sKey.length()));
        } else if (kKey instanceof Integer){
            int iKey = (Integer) kKey;
            hash += iKey;
        }

        return hash;
    }


    /**
     *put() takes in a key and value to be put into hashTable
     *      It is able to check if this is an update by comparing the key entered to existing keys
     */
    public void put(K key, V value){
        int hash = hashKey(key) % SIZE;

        //find the existing entry at hash
        Entry<K,V> currentEntry = table[hash];
        //check if it's empty -> if so create a new entry using the input key and value
        if(currentEntry == null){
            table[hash] = new Entry<K,V>(key,value);
        } else {
        // entry at hash is not empty -> cycle along linked list
            while (currentEntry.getNext() != null){
                //check if the key matches input key
                //-> set value of existing entry to new value
                //   as this is an UPDATE
                if(currentEntry.getKey().equals(key)){
                    currentEntry.setValue(value);
                    return;
                }
                currentEntry=currentEntry.getNext();
            }
            //Now at last entry in list, check if it's an update one last time
            if(currentEntry.getKey().equals(key)){
                currentEntry.setValue(value);
                return;
            }
            //No entries in this list match key, therefore it is a new entry
            //still on the last item of list
            //-> set the 'next' value as a new entry using the input key and value
            currentEntry.setNext(new Entry<K,V>(key,value));

        }
    }

    /**
     *get() takes in a key and returns the value related
     */

    public V get(K key){
        int hash = hashKey(key) % SIZE;

        //find the existing entry at hash
        Entry<K,V> currentEntry = table[hash];

        //Check if it's empty, if so return null
        if (currentEntry==null){
            return null;
        }
        //cycle through all and compare keys
        while (currentEntry!=null){
            if (currentEntry.getKey()== key){
                //It has been found!
                return currentEntry.getValue();
            }
            //Not this one -> get the next one
            currentEntry=currentEntry.getNext();
        }
        //at this point we have gone beyond the existing list
        //therefore the entry relating to the input key doesn't exist -> return null
        return null;
    }


    /**
     *remove() Takes in a key to remove and removes it from the hashmap
     *         -it returns the entry that has been removed
     */
    public Entry<K,V> remove(K key){
        int hash = hashKey(key) % SIZE;
        System.out.println("removing in hashmap");
        //find the existing entry at hash
        Entry<K,V> currentEntry = table[hash];
        //Check if it's empty, if so return null
        if (currentEntry==null){
            return null;
        }
        //check if the first entry is the one to remove
        if (currentEntry.getKey().equals(key)){
            //the first entry in list is the one to remove
            // -> set the value in the table array to the next entry
            table[hash] = currentEntry.getNext();
            // Make sure removed object isn't pointing towards the rest of the list before removing
            currentEntry.setNext(null);
        }
        //it's not the first one
        Entry<K,V> previousEntry = currentEntry;
        currentEntry = currentEntry.getNext();
        //cycle through the list
        while (currentEntry!=null){
            if(currentEntry.getKey().equals(key)){
                //it has been found, set the previous next to current next ro remove it from list
                previousEntry.setNext(currentEntry.getNext());
                // Make sure removed object isn't pointing towards the rest of the list before removing
                currentEntry.setNext(null);
                return currentEntry;
            }
            //not this entry, set previous to current and advance current to next
            previousEntry = currentEntry;
            currentEntry = currentEntry.getNext();
        }
        //at this point we have gone beyond the existing list
        //therefore the entry relating to the input key doesn't exist -> return null
        return null;

    }

    /**
     * Searches the hashmap for all entries with a key that contains the given name.
     */
    public Entry<K, V> searchByName(String name) {
        Entry<K, V> resultHead = null; // Head of the result linked list
        Entry<K, V> currentResult = null; // Point to the current entry in the result linked list

        // Iterate through all buckets
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> currentEntry = table[i];

            //Go through linked list at table[i]
            while (currentEntry != null) {
                // Check if key is a String and contains the search name
                if (currentEntry.getKey() instanceof String) {
                    //Check if the key string contains the search name
                    String keyString = (String) currentEntry.getKey();
                    if (keyString.contains(name)) {
                        // Create a new entry for the result linked list
                        Entry<K, V> newResult = new Entry<>(currentEntry.getKey(), currentEntry.getValue());
                        newResult.setNext(null);
                        //If this is the first matching rsult, make it the head
                        if (resultHead == null) {
                            resultHead = newResult;
                        } else {
                            currentResult.setNext(newResult); // Append to the result list
                        }

                        currentResult = newResult; // Move the current pointer
                    }
                }
                currentEntry = currentEntry.getNext();
            }
        }
        System.out.println(resultHead);
        return resultHead; // Return the head of the result linked list
    }

    public Entry<K, V> sort() {
        // Counting the number of entries in the hashtable
        int totalEntries = 0;
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> currentEntry = table[i];
            while (currentEntry != null) {
                totalEntries++;
                currentEntry = currentEntry.getNext();
            }
        }

        // Create an array, with the size of total entries, to hold all entries
        Entry<K, V>[] entriesArray = new Entry[totalEntries];
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> currentEntry = table[i];
            while (currentEntry != null) {
                entriesArray[index++] = new Entry<>(currentEntry.getKey(), currentEntry.getValue());
                currentEntry = currentEntry.getNext();
            }
        }

        // Step 2: Sort the array using Selection Sort
        for (int i = 0; i < totalEntries - 1; i++) {
            // Assume the minimum is at index i
            int minIndex = i;

            // Find the smallest key in the unsorted portion
            for (int j = i + 1; j < totalEntries; j++) {
                //makes key from minIndex and j(comparisonStr)
                String minIndexStr = (String)entriesArray[minIndex].getKey();
                String comparisonStr = (String)entriesArray[j].getKey();

                //compares if value to compare(j) is less than the minimum index, if it is. then update minimum index to j
                if (comparisonStr.compareTo(minIndexStr) < 0){
                    minIndex = j;
                }

            }


            // Swap the minimum element with the first element of the unsorted portion
            if (minIndex != i) {
                Entry<K, V> temp = entriesArray[i];
                entriesArray[i] = entriesArray[minIndex];
                entriesArray[minIndex] = temp;
            }
        }

        // Step 3: Convert the sorted array back into a linked list
        Entry<K, V> sortedHead = null;
        Entry<K, V> currentResult = null;

        for (Entry<K, V> entry : entriesArray) {
            if (sortedHead == null) {
                sortedHead = new Entry<>(entry.getKey(), entry.getValue());
                currentResult = sortedHead;
            } else {
                currentResult.setNext(new Entry<>(entry.getKey(), entry.getValue()));
                currentResult = currentResult.getNext();
            }
        }

        return sortedHead;
    }

    //I have no clue is string builder is allowed but this code was the only thing that would work
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            if (table[i] != null) {
                sb.append(i + " " + table[i] + "\n");
            } else {
                sb.append(i + " " + "null" + "\n");
            }
        }

        return sb.toString();
    }
}
