package com.bridgelabz.lmsmentorservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class MentorNotFoundException extends RuntimeException {
    private int statusCode;
    private String statusMessage;

    public MentorNotFoundException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}

