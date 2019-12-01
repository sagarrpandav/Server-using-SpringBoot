package com.evaluation.demo.repository;

import com.evaluation.demo.entity.Category;
import com.evaluation.demo.entity.Idea;
import com.evaluation.demo.entity.Likes;
import com.evaluation.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes,Integer>
{
    @Query(value = "select max(likeID) from likes;",nativeQuery = true)
    int getMaxIdeaID();

    List<Likes> findAllByMemberID_MemberID(int memberID);

    Likes findByIdeaIDAndMemberID(Idea ideaID,Member memberID);

    @Modifying
    @Transactional
    @Query(value="delete from likes where likeID=?1" ,nativeQuery = true)
    int removeByLikeID(Integer likeID);
}