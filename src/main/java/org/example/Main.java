package org.example;
/*
* This is just a sample exercise/experiments with RocksDB
* Types of tasks were are planning to do:
* 1) Initiate a RocksDB instance
* 2) Experiment put <K,V>, get <K,V> and delete <K,V> in RocksDB
* 3) Show how to iterate using a RocksDB iterator
*
*
* */



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RocksDBOperations rocksDBOperations = new RocksDBOperations();
        rocksDBOperations.put("Akash", "India");
        String ans = rocksDBOperations.get("Akash");
        System.out.println(ans);
        boolean reply = rocksDBOperations.delete("Akash");
        System.out.println(reply);
        ans = rocksDBOperations.get("Akash");
        System.out.println(ans);
        reply = rocksDBOperations.delete("Akash");
        System.out.println(reply);
    }
}