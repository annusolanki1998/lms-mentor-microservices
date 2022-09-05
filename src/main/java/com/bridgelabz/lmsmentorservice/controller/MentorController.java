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

    /*
     * Purpose: Welcome to mentor service
     * @author: Annu kumari
     * */

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "Welcome to LMS Spring application project";
    }

    /*
     * Purpose: Create mentor details
     * @author: Annu Kumari
     * @Param: mentorDTO and token
     * */
    @PostMapping("/addmentor")
    public ResponseEntity<ResponseUtil> addMentor(@Valid @RequestBody MentorDTO mentorDTO,
                                                  @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.addMentor(mentorDTO, token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose: Update existing mentor details by using id
     * @author: Annu Kumari
     * @Param: id,mentorsDTO,and token
     * */

    @PutMapping("/updatementor/{id}")
    public ResponseEntity<ResponseUtil> updateMentor(@PathVariable Long id,
                                                     @Valid @RequestBody MentorDTO mentorDTO,
                                                     @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.updateMentor(mentorDTO, token, id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose: Retrieve all metors details
     * @author: Annu Kumari
     * @Param: token
     * */

    @GetMapping("/getmentors")
    public ResponseEntity<List<?>> getMentors(@RequestHeader String token) {
        List<MentorModel> responseUtil = mentorService.getMentors(token);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    /*
     * Purpose: Delete existing mentor details by using id
     * @author: Annu Kumari
     * @Param: id and token
     * */

    @DeleteMapping("deletementor/{id}")
    public ResponseEntity<ResponseUtil> deleteMentor(@PathVariable Long id,
                                                     @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.deleteMentor(token, id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);

    }

    /*
     * Purpose: Retrieve existing mentor details by using id
     * @author: Annu Kumari
     * @Param: id and token
     * */

    @GetMapping("getmentor/{id}")
    public ResponseEntity<ResponseUtil> getMentor(@PathVariable Long id,
                                                  @RequestHeader String token) {
        ResponseUtil responseUtil = mentorService.getMentor(token, id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose: Add Profilepic url to mentor
     * @author: Annu Kumari
     * @Param: token,profilePic and id
     * */
    @PostMapping("/addProfilePicUrl")
    public ResponseEntity<ResponseUtil> addProfilePic(@RequestHeader String token,
                                                      @RequestParam String profilePic,
                                                      @RequestParam Long id) {
        ResponseUtil responseUtil = mentorService.addProfilePic(token,profilePic,id);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }


    /*
     * Purpose: Retrieve all mentors count
     * @author: Annu Kumari
     * */
    @GetMapping("/mentorsCount")
    public ResponseEntity<Long> mentorsCount() {
        Long responseUtil = mentorService.mentorsCount();
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }

    /*
     * Purpose: Retrieve mentor details by mentorRole
     * @author: Annu Kumari
     * @Param: mentorRole
     * */
    @GetMapping("/getMentorByRole")
    public ResponseEntity<Long> getMentorByRole(@RequestParam String mentorRole) {
        Long responseUtil = mentorService.getMentorByRole(mentorRole);
        return new ResponseEntity<>(responseUtil, HttpStatus.OK);
    }
}
