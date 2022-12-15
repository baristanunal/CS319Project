package com.ErasmusApplication.ErasmusApp.Exceptions;

public class FileStorageException extends RuntimeException{
    // Constructors
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
