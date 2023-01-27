package com.couchbase.config;

import com.couchbase.client.java.*;


public class CouchbaseConfig {

    static String connectionString = "127.0.0.1";
    static String username = "bhawana";   
    static String password = "bhawana";
    static String bucketName = "Test";   
    
    public void helper(){
    
        Cluster cluster = Cluster.connect("couchbase://" + connectionString, username, password);

        Bucket bucket = cluster.bucket(bucketName);
        // bucket.waitUntilReady(Duration.ofSeconds(10));

        Scope scope = bucket.scope("_default");
        Collection collection = scope.collection("_default");
        System.out.println(collection.toString());
        
    }

}
