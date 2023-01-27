package com.couchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.couchbase.config.CouchbaseConfig;


@SpringBootApplication
public class CouchbaseApplication {


	public static void main(String[] args) {
		
		SpringApplication.run(CouchbaseApplication.class, args);
		com.couchbase.config.CouchbaseConfig config = new CouchbaseConfig();
		config.helper();

	}

}
