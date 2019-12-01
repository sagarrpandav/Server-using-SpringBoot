package com.evaluation.demo.service;

import com.evaluation.demo.dto.IdeaDTO;
import com.evaluation.demo.dto.MemberDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.entity.Idea;
import com.evaluation.demo.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface MemberService
{
    Boolean addMembers(List<String> memberNames,int ideaID);
    ResponseDTO addNewMember(MemberDTO memberDTO);
    Boolean removeMembers(int ideaID);

    Member getMemberByID(int memberID);

    MemberDTO getMember(int memberID);

    String getMembersPerIdea(int ideaID);
}
