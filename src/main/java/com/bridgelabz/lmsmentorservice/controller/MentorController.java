package com.bridgelabz.lmsmentorservice.controller;

import com.bridgelabz.lmsmentorservice.dto.MentorDTO;
import com.bridgelabz.lmsmentorservice.model.MentorModel;
import com.bridgelabz.lmsmentorservice.service.IMentorService;
import com.bridgelabz.lmsmentorservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    IMentorService mentorService;

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to LMS Spring application project";
    }


    @PostMapping("/addmentor")
    public ResponseEntity<ResponseUtil> addMentor(@Valid @RequestBody MentorDTO mentorDTO,
                                                  @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.addMentor(mentorDTO, token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }


    @PutMapping("/updatementor/{id}")
    public ResponseEntity<ResponseUtil> updateMentor(@PathVariable Long id,
                                                     @Valid @RequestBody MentorDTO mentorDTO,
                                                     @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.updateMentor(mentorDTO, token, id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    @GetMapping("/getmentors")
    public ResponseEntity<List<?>> getMentors(@RequestHeader String token) {
        List<MentorModel> responseUtil = mentorService.getMentors(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }


    @DeleteMapping("deletementor/{id}")
    public ResponseEntity<ResponseUtil> deleteMentor(@PathVariable Long id,
                                                     @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.deleteMentor(token, id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    @GetMapping("getmentor/{id}")
    public ResponseEntity<ResponseUtil> getMentor(@PathVariable Long id,
                                                  @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.getMentor(token, id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }
}
