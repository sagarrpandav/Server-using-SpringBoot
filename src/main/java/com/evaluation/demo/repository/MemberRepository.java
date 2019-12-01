package com.evaluation.demo.repository;

import com.evaluation.demo.entity.Idea;
import com.evaluation.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>
{
    List<Member> findAllByMemberIdea_IdeaID(int ideaID);

    @Modifying
    @Transactional
    @Query(value="update member set ideaID=null where ideaID=?1" ,nativeQuery = true)
    int removeByMemberIdea(int ideaID);

    @Query(value = "select max(memberID) from member;",nativeQuery = true)
    int getMaxMemberID();
}
