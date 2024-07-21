package com.marcinsz.recruitingtask.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String username) {
        super("User " + username + " does not exist.");

    }
}
