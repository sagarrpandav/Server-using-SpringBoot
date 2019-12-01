package com.evaluation.demo.service;

import com.evaluation.demo.entity.Likes;
import com.evaluation.demo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImplementation implements LikeService
{
    @Autowired
    private LikeRepository likeRepository;

    @Override
    public int addLike(Likes likes) {
        likes.setLikeID(likeRepository.getMaxIdeaID()+1);
        likes=likeRepository.save(likes);
        return likes.getLikeID();
    }

    @Override
    public int removeLike(Likes likes) {
        return likeRepository.removeByLikeID(likes.getLikeID());
    }

    @Override
    public List<Likes> getAllLikesOfMember(int memberID) {
        return likeRepository.findAllByMemberID_MemberID(memberID);
    }
}
