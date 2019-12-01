package com.evaluation.demo.service;

import com.evaluation.demo.customException.BlankFieldException;
import com.evaluation.demo.dto.HackathonCategoryDto;
import com.evaluation.demo.dto.IdeaDTO;
import com.evaluation.demo.dto.LikeDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.entity.Idea;
import org.springframework.stereotype.Service;

@Service
interface IdeaService
{
    HackathonCategoryDto getAllIdeasPerHackathon(int hackathonID,int memberID);
    ResponseDTO addIdea(IdeaDTO ideaDTO,int hackathonID) throws BlankFieldException;
    ResponseDTO updateIdea(IdeaDTO ideaDTO)throws BlankFieldException;
    Idea getIdeaByIdeaID(int ideaID);
    ResponseDTO likeIdea(LikeDTO likeDTO)throws BlankFieldException;

    ResponseDTO dislikeIdea(LikeDTO likeDTO) throws BlankFieldException;
}
