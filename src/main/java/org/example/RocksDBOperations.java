package org.example;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class RocksDBOperations {

    private static final String pathDBName = "MyRocksDB";
    private static final String encodingFormat = "UTF-8";
    private File dbDir;
    private RocksDB rocksDB;

    public RocksDBOperations() {
        RocksDB.loadLibrary();
        final Options options = new Options();
        options.setCreateIfMissing(true);
        try {
            dbDir = new File("/Users/akashshirale/Downloads/" + pathDBName);
            rocksDB = RocksDB.open(options, dbDir.getAbsolutePath());
        } catch (NullPointerException npe) {
            System.out.println("Error while opening file: " + dbDir.getName());
        } catch (RocksDBException rocksDBException) {
            System.out.println("Error while opening RocksDB instance: " + rocksDBException);
        }
    }

    public boolean put (String key, String value) {
        try {
            byte[] inputKey = key.getBytes(StandardCharsets.UTF_8);
            byte[] inputValue = value.getBytes(StandardCharsets.UTF_8);
            rocksDB.put(inputKey,inputValue);
        } catch (RocksDBException rocksDBException) {
            System.out.println("Error while doing put in RocksDB: " + rocksDBException);
            return false;
        }
        return true;
    }

    public String get(String key) {
        String output = null;
        try {
            byte[] inputKey = key.getBytes(StandardCharsets.UTF_8);
            byte[] outputValue = rocksDB.get(inputKey);
            output = new String(outputValue,StandardCharsets.UTF_8);
        } catch (NullPointerException npe) {
            System.out.println("Error while decoding: " + npe);
            return null;
        } catch (RocksDBException rocksDBException) {
            System.out.println("Error while getting output value: " + rocksDBException);
        }
        return output;
    }

    public boolean delete(String key) {
        try {
            byte[] inputKey = key.getBytes(StandardCharsets.UTF_8);
            rocksDB.delete(inputKey);
        } catch (RocksDBException rocksDBException) {
            System.out.println("Error while performing delete operation :" + key);
            return false;
        }
        return true;
    }

}
