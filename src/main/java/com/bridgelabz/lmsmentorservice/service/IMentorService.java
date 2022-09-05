package com.bridgelabz.lmsmentorservice.service;

import com.bridgelabz.lmsmentorservice.dto.MentorDTO;
import com.bridgelabz.lmsmentorservice.model.MentorModel;
import com.bridgelabz.lmsmentorservice.util.ResponseUtil;

import java.util.List;

/**
 * Purpose: Creating Interface for Admin service
 * @author: Annu Kumari
 * @Param:  All service methods
 * Version: 1.0
 */

public interface IMentorService {

    ResponseUtil addMentor(MentorDTO mentorDTO, String token);

    ResponseUtil updateMentor(MentorDTO mentorDTO, String token, Long id);

    List<MentorModel> getMentors(String token);

    ResponseUtil deleteMentor(String token, Long id);

    ResponseUtil getMentor(String token, Long id);

    ResponseUtil addProfilePic(String token, String profilePic, Long id);

    Long mentorsCount();

    Long getMentorByRole(String mentorRole);
}
