package com.evaluation.demo.service;

import com.evaluation.demo.dto.MemberDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.entity.Member;
import com.evaluation.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImplementation implements MemberService
{
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private IdeaService ideaService;

    @Override
    public Boolean addMembers(List<String> memberNames,int ideaID)
    {
        //this.removeMembers(ideaID);
        for(String name:memberNames)
        {
            Member member=new Member();
            member.setMemberIdea(ideaService.getIdeaByIdeaID(ideaID));
            member.setMemberName(name);
            member.setMemberID(memberRepository.getMaxMemberID()+1);
            Member testMember=memberRepository.save(member);
            if(testMember!=null)
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean removeMembers(int ideaID) {

        System.out.println("inside");
        int flag=memberRepository.removeByMemberIdea(ideaID);
        System.out.println("Removinf b4");
        if(flag>0)
        {
            System.out.println("Removed!");
            return true;
        }
        System.out.println("Not Removed");
        return false;
    }

    @Override
    public String getMembersPerIdea(int ideaID)
    {
        List<Member> memberList=memberRepository.findAllByMemberIdea_IdeaID(ideaID);
        String memberNames="";
        for(Member member:memberList)
        {
            memberNames+=member.getMemberName()+",";
        }
        return memberNames;
    }

    @Override
    public ResponseDTO addNewMember(MemberDTO memberDTO) {
        ResponseDTO responseDTO=new ResponseDTO();

        Member member=new Member();
        member.setMemberName(memberDTO.getMemberName());
        member.setMemberID(memberRepository.getMaxMemberID()+1);

        member=memberRepository.save(member);
        if(member!=null)
        {
            responseDTO.setStatus("SUCCESS");
            responseDTO.setErrorMessage(""+member.getMemberID());
        }
        else {
            responseDTO.setStatus("FAILURE");
        }

        return responseDTO;
    }

    @Override
    public MemberDTO getMember(int memberID) {
        Member member=getMemberByID(memberID);
        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setMemberID(member.getMemberID());
        memberDTO.setMemberName(member.getMemberName());

        return memberDTO;
    }

    @Override
    public Member getMemberByID(int memberID) {
        return memberRepository.getOne(memberID);
    }
}