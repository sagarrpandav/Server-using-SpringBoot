package com.evaluation.demo.service;

import com.evaluation.demo.entity.Likes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikeService
{
    int addLike(Likes likes);
    int removeLike(Likes likes);
    List<Likes> getAllLikesOfMember(int memberID);
}
