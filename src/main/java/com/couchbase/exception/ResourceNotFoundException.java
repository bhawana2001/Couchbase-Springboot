package com.couchbase.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String msg){
        super(msg);
    }
    public ResourceNotFoundException(){
        
    }

}
