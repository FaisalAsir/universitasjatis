package com.universitasjatis.universitasjatis.exceptionHandler;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException() {
        super("Data NotFound");
    }
}
