package org.example.kun_uzz.exp;

public class AppForbiddenException extends RuntimeException{
    public AppForbiddenException(String message){
        super(message);
    }
}
