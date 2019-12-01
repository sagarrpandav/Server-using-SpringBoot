package com.evaluation.demo.repository;

import com.evaluation.demo.entity.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update hackathon set total_ideas=total_ideas+1 where hackathonID=?1",nativeQuery = true)
    int incrementIdeaCount(Integer hackathonID);
}