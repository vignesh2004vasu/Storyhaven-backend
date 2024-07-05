package com.VIGNESH.logindatabase.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String id) {
        super("Could not find the book with ID: " + id);
    }
}
