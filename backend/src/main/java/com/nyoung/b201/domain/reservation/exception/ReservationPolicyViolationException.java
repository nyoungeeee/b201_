package com.nyoung.b201.domain.reservation.exception;


public class ReservationPolicyViolationException extends RuntimeException {
    public ReservationPolicyViolationException(String message) { super(message); }
}
