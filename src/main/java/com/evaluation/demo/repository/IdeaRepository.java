package com.evaluation.demo.repository;

import com.evaluation.demo.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea,Integer>
{
    List<Idea> findAllByHackathonID_HackathonID(int hackathonID);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update idea set like_count=like_count+1 where ideaID=?1",nativeQuery = true)
    int addLikeToIdea(int ideaID);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update idea set like_count=like_count-1 where ideaID=?1",nativeQuery = true)
    int removeLikeFromIdea(int ideaID);

    @Query(value = "select max(ideaID) from idea;",nativeQuery = true)
    int getMaxIdeaID();
}
