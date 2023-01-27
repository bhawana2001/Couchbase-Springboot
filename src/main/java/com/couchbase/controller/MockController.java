package com.couchbase.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.couchbase.service.MockService;

@RestController
public class MockController<T> {
    
    @Autowired
    MockService mockService;

    @GetMapping("/mocks")
    public ResponseEntity<?> getMocks(){
        return mockService.getMocks();
    }
    @PostMapping("/createmock")
    public ResponseEntity<?> createMock (@RequestBody Object mock){
        return ResponseEntity.ok(mockService.createMock(mock)); 
    } 

    @PutMapping("/updatemock/{id}")
    public ResponseEntity<?> updateMockbyid(@PathVariable String id, @RequestBody Object object){
       return mockService.updateMock(id,object);
    }

    @GetMapping(value = "/mocks/{id}")
    public ResponseEntity<?> getMockById(@PathVariable String id){
        return mockService.getById(id);
    }

    @DeleteMapping("/mocks/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        return mockService.deleteById(id);
    } 
 }
