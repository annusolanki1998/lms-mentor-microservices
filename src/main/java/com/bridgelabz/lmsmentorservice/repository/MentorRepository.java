package com.bridgelabz.lmsmentorservice.repository;

import com.bridgelabz.lmsmentorservice.model.MentorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Purpose: Creating repository for mentor
 * @author: Annu Kumari
 * @Param:  To save in database
 * Version: 1.0
 */

public interface MentorRepository extends JpaRepository<MentorModel, Long> {

    @Query(value = "select count(id) from mentor", nativeQuery = true)
    Long mentorsCount();

    @Query(value = "select count(mentor_role) from mentor where mentor_role=:isMentorRole", nativeQuery = true)
    Long mentorsCountByRole(String mentorRole);
}
