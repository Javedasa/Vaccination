package com.example.Vaccinationation.exception;

public class NotEligibleForDoseException extends Exception{
    public NotEligibleForDoseException(String message){
        super(message);
    }
}
