package com.original.desafio.exception;

public class GraphNotFoundException extends RuntimeException{
    public GraphNotFoundException(Long id) {
        super("Could not find graph " + id);
    }
}
