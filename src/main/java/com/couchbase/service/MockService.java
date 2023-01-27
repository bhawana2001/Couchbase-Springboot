package com.couchbase.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.couchbase.client.java.kv.*;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.exception.ResourceNotFoundException;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.*;

@Service
public class MockService {

	static String connectionString = "127.0.0.1";
	static String username = "bhawana";
	static String password = "bhawana";
	static String bucketName = "Test";

	Cluster cluster = Cluster.connect("couchbase://" + connectionString, username, password);

	Bucket bucket = cluster.bucket(bucketName);

	Scope scope = bucket.scope("_default");
	Collection collection = scope.collection("_default");

	public ResponseEntity<?> getMocks() {
		QueryResult mocks = cluster.query("select meta (`Test`).id, * from `Test`");
		List<Object> output = mocks.rowsAs(Object.class);
		return ResponseEntity.ok(output);
	}

	// // Upsert Document
	public ResponseEntity<?> createMock(Object object) {
		// long min = 1;
		// long max = 2147483647;S
		// long random_int = (long)Math.floor(Math.random() * (max - min + 1) + min);
		String id = UUID.randomUUID().toString();
		MutationResult upsertResult = collection.insert(id, object);
		System.out.println(upsertResult.toString());
		return ResponseEntity.ok(object);
	}

	public ResponseEntity<?> getById(String id) {
		
		QueryResult mock=cluster.query("select * , meta().id from `Test`.`_default`.`_default` data use index(`#primary`) where meta().id=\""+id+"\"");
		mock.rowsAs(Object.class);
		System.out.println(mock.rowsAs(Object.class));		
		return ResponseEntity.ok(mock.rowsAs(Object.class));
		
		
//		return ResponseEntity.ok(collection.list(id, collection.getClass()));
		// QueryResult mocks = cluster.query("select meta (`Test`).id, * from `Test`");
		// List<Object> output = mocks.rowsAs(Object.class);
		// for(int i=0;i<output.size();i++){
		// if(output.get(i).equals(id)){
		// return ResponseEntity.ok(output.get(i));
		// }else{
		// throw new ResourceNotFoundException();
		// }
		// }

		// String value = "\"" + id + "\"";
		// System.out.println(value);
		// System.out.println("select meta ().id, Test.* from Test use keys " + value);
		// return ResponseEntity.ok(cluster.query("select meta ().id, Test.* from Test
		// use keys " + value));
		// return ResponseEntity.ok(collection.get(id));
		// System.out.println()
		// return ResponseEntity.ok(cluster.query("SELECT * FROM `Test`._default WHERE
		// META().id = $value"));
	}

	public ResponseEntity<?> updateMock(String id, Object object) {
		ExistsResult temp = collection.exists(id);

		if (temp != null) {

			return ResponseEntity.ok(collection.upsert(id, object));
		} else {
			throw new ResourceNotFoundException("data with given id does not exist...");
		}

	}

	public ResponseEntity<?> deleteById(String id) {
		QueryResult mock=cluster.query("delete from `Test`.`_default`.`_default` data use index(`#primary`) where meta().id=\""+id+"\"");
		return ResponseEntity.ok(mock);
	}

}
