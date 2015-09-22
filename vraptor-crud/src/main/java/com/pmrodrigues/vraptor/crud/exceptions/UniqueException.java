package com.pmrodrigues.vraptor.crud.exceptions;

/**
 * Created by Marceloo on 15/12/2014.
 */
public class UniqueException extends RuntimeException {
    public UniqueException(String message) {
        super(message);
    }
}
