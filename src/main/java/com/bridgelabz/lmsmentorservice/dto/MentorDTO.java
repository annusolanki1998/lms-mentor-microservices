package com.bridgelabz.lmsmentorservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class MentorDTO {

    @NotNull(message = "Employee id should not be empty")
    private String employeeId;

    @NotNull(message = "FirstName should not be empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Firstname should start with capital letter and  atleast min of three character")
    private String firstName;

    @NotNull(message = "LastName should not be empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Lastname should start with capital letter and  atleast min of three character")
    private String lastName;

    @NotNull(message = "Mentor type should not be empty")
    private String mentorType;

    @NotNull(message = "Mentor role should not be empty")
    private String mentorRole;

    @NotNull(message = "Mobilenumber should not be empty")
    @Pattern(regexp = "[+]91 [6-9]\\d{9}", message = "Invalid mobile number")
    private String mobileNumber;

    @NotNull(message = "Email id should not be empty")
    @Pattern(regexp = "[a-z][A-Z a-z 0-9]+[@][a-z]+[.][a-z]{2,}", message = "Invalid email id")
    private String email;

    @NotNull(message = "Experience year should not be empty")
    private String experienceYears;

    @NotNull(message = "Preferred time should not be empty")
    private String preferredTime;

    @NotNull(message = "Status should not be empty")
    private String status;

    @NotNull(message = "Mentor description should not be empty")
    private String mentorDescription;

    @NotNull(message = "Profile image url should not be empty")
    private String profileImageURL;

    @NotNull(message = "Creator user should not be empty")
    private int creatorUser;

    @NotNull(message = "Supervisor id should not be empty")
    private long supervisorId;
}
