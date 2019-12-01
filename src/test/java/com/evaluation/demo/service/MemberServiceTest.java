package com.evaluation.demo.service;

import com.evaluation.demo.entity.Member;
import com.evaluation.demo.repository.IdeaRepository;
import com.evaluation.demo.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest
{
    private List<Member> memberList;
    private List<String> memberNameList;
    private String op="";
    @InjectMocks
    private MemberServiceImplementation mockMemberServiceImplementation;

    @Mock
    private MemberRepository mockMemberRepository;

    @Mock
    private IdeaService mockIdeaService;

    @Before
    public void setup(){
        memberList=new ArrayList<>();
        memberNameList=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            Member member=new Member();
            member.setMemberName("Member"+i);
            memberNameList.add("Member"+i);

            memberList.add(member);
            op+="Member"+i+",";
        }
    };

    @Test
    public void getMembersPerIdea(){
        Mockito.when(mockMemberRepository.findAllByMemberIdea_IdeaID(Matchers.anyInt())).thenReturn(memberList);
        Assert.assertEquals(mockMemberServiceImplementation.getMembersPerIdea(1),op);
    };

    @Test
    public void addMembers(){
        Mockito.when(mockIdeaService.getIdeaByIdeaID(Matchers.anyInt())).thenReturn(null);
        Mockito.when(mockMemberRepository.getMaxMemberID()).thenReturn(0);
        Mockito.when(mockMemberRepository.save(Matchers.any(Member.class))).thenReturn(new Member());

        Assert.assertEquals(mockMemberServiceImplementation.addMembers(memberNameList,0),true);

    };

    @Test
    public void addMembersFail(){
        Mockito.when(mockIdeaService.getIdeaByIdeaID(Matchers.anyInt())).thenReturn(null);
        Mockito.when(mockMemberRepository.getMaxMemberID()).thenReturn(0);
        Mockito.when(mockMemberRepository.save(Matchers.any(Member.class))).thenReturn(null);

        Assert.assertEquals(mockMemberServiceImplementation.addMembers(memberNameList,0),false);

    };



    @Test
    public void removeMembersSuccess(){
        Mockito.when(mockMemberRepository.removeByMemberIdea(Matchers.anyInt())).thenReturn(1);

        Assert.assertEquals(mockMemberServiceImplementation.removeMembers(0),true);
    };


    @Test
    public void removeMembersFail(){
        Mockito.when(mockMemberRepository.removeByMemberIdea(Matchers.anyInt())).thenReturn(-1);

        Assert.assertEquals(mockMemberServiceImplementation.removeMembers(0),false);
    };
}
