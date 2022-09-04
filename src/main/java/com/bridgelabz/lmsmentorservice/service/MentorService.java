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


    @Override
    public ResponseUtil addMentor(MentorDTO mentorDTO, String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8081/admin/validate/" + token, Boolean.class);
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

    @Override
    public ResponseUtil updateMentor(MentorDTO mentorDTO, String token, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8081/admin/validate/" + token, Boolean.class);
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
                isMentorPresent.get().setProfileImageURL(mentorDTO.getProfileImageURL());
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


    @Override
    public List<MentorModel> getMentors(String token) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8081/admin/validate/" + token, Boolean.class);
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

    @Override
    public ResponseUtil deleteMentor(String token, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8081/admin/validate/" + token, Boolean.class);
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


    @Override
    public ResponseUtil getMentor(String token, Long id) {
        boolean isUserPresent = restTemplate.getForObject("http://localhost:8081/admin/validate/" + token, Boolean.class);
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
}

