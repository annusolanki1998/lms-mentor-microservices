package com.bridgelabz.lmsmentorservice.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Purpose : MentorNotFoundException is used to handle the exceptions
 * Version : 1.0
 * @author : Annu kumari
 * */

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

