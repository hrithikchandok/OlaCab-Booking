package com.MBD.CabBooking.Exceptionhandling;
public class OneToOneViolationException extends RuntimeException {
    public OneToOneViolationException(String message) {
        super(message);
    }
}