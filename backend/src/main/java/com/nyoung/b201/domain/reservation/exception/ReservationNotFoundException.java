package com.nyoung.b201.domain.reservation.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(String message) { super(message); }
}
