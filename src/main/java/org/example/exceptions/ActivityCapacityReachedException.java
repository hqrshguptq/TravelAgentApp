package org.example.exceptions;

public class ActivityCapacityReachedException extends RuntimeException {
    public ActivityCapacityReachedException(String message) {
        super(message);
    }
}
