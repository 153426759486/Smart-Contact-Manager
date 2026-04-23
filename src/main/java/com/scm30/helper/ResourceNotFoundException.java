package com.scm30.helper;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(){
        super("Resource Not Found!");
    }
}
