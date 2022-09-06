package com.bridgelabz.lmsmentorservice.service;

import com.bridgelabz.lmsmentorservice.dto.MentorDTO;
import com.bridgelabz.lmsmentorservice.exception.MentorNotFoundException;
import com.bridgelabz.lmsmentorservice.model.MentorModel;
import com.bridgelabz.lmsmentorservice.repository.MentorRepository;
import com.bridgelabz.lmsmentorservice.util.ResponseUtil;
import com.bridgelabz.lmsmentorservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Purpose: Creating service for mentor
 * @author: Annu Kumari
 * @Param:  business logic is present here
 * Version: 1.0
 */

@Service
public class MentorService implements IMentorService {
    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * Purpose: Creating method to add mentor details
     * @author: Annu Kumari
     * @Param: mentorDto
     */
    @Override
    public ResponseUtil addMentor(MentorDTO mentorDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://EUREKA-ADMIN-CLIENT:8081/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            MentorModel mentorModel = new MentorModel(mentorDTO);
            mentorModel.setCreatedTimeStamp(LocalDateTime.now());
            mentorRepository.save(mentorModel);
            String body = "Mentor is added successfully with mentorId " + mentorModel.getId();
            String subject = "Mentor registration successfully";
            mailService.send(mentorModel.getEmail(), subject, body);
            return new ResponseUtil(200, "Sucessfully", mentorModel);
        }
        throw new MentorNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose: Creating method to update existing mentor details
     * @author: Annu Kumari
     * @Param: mentorDto,id and token
     */

    @Override
    public ResponseUtil updateMentor(MentorDTO mentorDTO, String token, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http://EUREKA-ADMIN-CLIENT:8081/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = mentorRepository.findById(id);
            if (isMentorPresent.isPresent()) {
                isMentorPresent.get().setEmployeeId(mentorDTO.getEmployeeId());
                isMentorPresent.get().setFirstName(mentorDTO.getFirstName());
                isMentorPresent.get().setLastName(mentorDTO.getLastName());
                isMentorPresent.get().setMentorType(mentorDTO.getMentorType());
                isMentorPresent.get().setMentorRole(mentorDTO.getMentorRole());
                isMentorPresent.get().setMobileNumber(mentorDTO.getMobileNumber());
                isMentorPresent.get().setEmail(mentorDTO.getEmail());
                isMentorPresent.get().setExperienceYears(mentorDTO.getExperienceYears());
                isMentorPresent.get().setPreferredTime(mentorDTO.getPreferredTime());
                isMentorPresent.get().setStatus(mentorDTO.getStatus());
                isMentorPresent.get().setMentorDescription(mentorDTO.getMentorDescription());
                isMentorPresent.get().setProfilePic(mentorDTO.getProfilePic());
                isMentorPresent.get().setCreatorUser(mentorDTO.getCreatorUser());
                isMentorPresent.get().setSupervisorId(mentorDTO.getSupervisorId());
                isMentorPresent.get().setUpdatedTimeStamp(LocalDateTime.now());
                mentorRepository.save(isMentorPresent.get());
                String body = "Mentor is added successfully with mentorId " + isMentorPresent.get().getId();
                String subject = "Mentor registration successfully";
                mailService.send(isMentorPresent.get().getEmail(), subject, body);
                return new ResponseUtil(200, "Sucessfully", isMentorPresent.get());
            } else {
                throw new MentorNotFoundException(400, "Mentor not found");
            }
        }
        throw new MentorNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose: Creating method to get mentor details
     * @author: Annu Kumari
     * @Param: token
     */


    @Override
    public List<MentorModel> getMentors(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://EUREKA-ADMIN-CLIENT:8081/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            List<MentorModel> isMentorPresent = mentorRepository.findAll();
            if (isMentorPresent.size() > 0) {
                return isMentorPresent;
            } else {
                throw new MentorNotFoundException(400, "No mentor is present");
            }
        }
        throw new MentorNotFoundException(400, "Token is wrong");

    }

    /**
     * Purpose: Creating method to delete existing mentor details
     * @author: Annu Kumari
     * @Param: id and token
     */

    @Override
    public ResponseUtil deleteMentor(String token, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http://EUREKA-ADMIN-CLIENT:8081/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = mentorRepository.findById(id);
            if (isMentorPresent.isPresent()) {
                mentorRepository.delete(isMentorPresent.get());
                return new ResponseUtil(200, "Sucessfully", isMentorPresent.get());
            } else {
                throw new MentorNotFoundException(400, "Mentor not found");
            }
        }
        throw new MentorNotFoundException(400, "Token is wrong");

    }

    /**
     * Purpose: Creating method to get admin details
     * @author: Annu Kumari
     * @Param: id and token
     */


    @Override
    public ResponseUtil getMentor(String token, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http:/EUREKA-ADMIN-CLIENT:8081/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = mentorRepository.findById(id);
            if (isMentorPresent.isPresent()) {
                return new ResponseUtil(200, "Sucessfully", isMentorPresent.get());
            } else {
                throw new MentorNotFoundException(400, "Mentor not found");
            }
        }
        throw new MentorNotFoundException(400, "Token is wrong");
    }

    /**
     * Purpose: Creating method to add proofile pic
     * @author: Annu Kumari
     * @Param: id ,token and profile pic
     */

    @Override
    public ResponseUtil addProfilePic(String token, String profilePic, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http://EUREKA-ADMIN-CLIENT:8081/admin/validate/" + token, Boolean.class);
        if (isUserPresent) {
            Optional<MentorModel> isMentorPresent = mentorRepository.findById(id);
            if (isMentorPresent.isPresent()) {
                isMentorPresent.get().setProfilePic(profilePic);
                mentorRepository.save(isMentorPresent.get());
                String body = "Mentor profilePic Added With Id is : " + isMentorPresent.get().getId();
                String subject = "Mentor ProfilePic Uploaded ...";
                mailService.send(isMentorPresent.get().getEmail(), body, subject);
                return new ResponseUtil(200, "Successfully", isMentorPresent.get());
            } else {
                throw new MentorNotFoundException(400, "No profile pic url found with this id");
            }
        }
        throw new MentorNotFoundException(400, "Token is wrong");

    }

    /**
     * Purpose: Creating method to count existing mentor
     * @author: Annu Kumari
     */

    @Override
    public Long mentorsCount() {
        Long mentorsCount = mentorRepository.mentorsCount();
        return mentorsCount;
    }

    /**
     * Purpose: Creating method to get existing mentor by role
     * @author: Annu Kumari
     * @Param: mentorRole
     */

    @Override
    public Long getMentorByRole(String mentorRole) {
        Long mentorCountByRole = mentorRepository.mentorsCountByRole(mentorRole);
        return mentorCountByRole;
    }

}


