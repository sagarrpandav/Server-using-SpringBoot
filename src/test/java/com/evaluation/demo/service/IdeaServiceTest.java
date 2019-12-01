package com.evaluation.demo.service;

import com.evaluation.demo.customException.BlankFieldException;
import com.evaluation.demo.dto.HackathonCategoryDto;
import com.evaluation.demo.dto.IdeaDTO;
import com.evaluation.demo.dto.LikeDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.entity.Category;
import com.evaluation.demo.entity.Hackathon;
import com.evaluation.demo.entity.Idea;
import com.evaluation.demo.repository.IdeaRepository;
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
public class IdeaServiceTest {

    private List<Idea> ideaRecords;
    private IdeaDTO ideaDTO = new IdeaDTO();
    private Idea targetIdea;

    @Before
    public void setup() {
        ideaRecords = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Idea idea = new Idea();
            idea.setIdeaID(i);
            idea.setTeamCount(4);
            idea.setLikeCount(0);
            idea.setIdeaSummary(i + "Idea Summary");
            idea.setIdeaDetails(i + "Idea Details");

            Hackathon hackathon = new Hackathon();
            hackathon.setHackathonID(1);
            hackathon.setEventName("Hackathon Name");

            idea.setHackathonID(hackathon);

            Category category = new Category();
            category.setCategoryID(1);

            idea.setIdeaCategory(category);

            ideaRecords.add(idea);
        }
        targetIdea = new Idea();
        targetIdea.setIdeaSummary("Summary");
        ideaDTO.setIdeaSummary("Summary");
        targetIdea.setIdeaDetails("Details");
        ideaDTO.setIdeaDetails("Details");
        targetIdea.setTeamCount(2);
        ideaDTO.setTeamMemberNames("dasds\nadsad");
        ideaDTO.setCategoryName("category");
        Category category = new Category();
        category.setCategoryID(1);
        category.setCategoryName("category");
        targetIdea.setIdeaCategory(category);
        targetIdea.setIdeaID(ideaRecords.size() + 1);
    }

    @InjectMocks
    private IdeaServiceImplementation mockIdeaServiceImplementation;

    @Mock
    private IdeaRepository mockIdeaRepository;

    @Mock
    private HackathonServiceImplementation mockHackathonServiceImplementation;

    @Mock
    private MemberServiceImplementation mockMemberServiceImplementation;

    @Mock
    private CategoryServiceImplementation mockCategoryServiceImplementation;

    @Test
    public void getAllIdeasPerHackathon() {
        Mockito.when(mockHackathonServiceImplementation.getHackathonName(1)).thenReturn(ideaRecords.get(1).getHackathonID().getEventName());
        Mockito.when(mockIdeaRepository.findAllByHackathonID_HackathonID(1)).thenReturn(ideaRecords);

        HackathonCategoryDto target = mockIdeaServiceImplementation.getAllIdeasPerHackathon(1,1);

        Assert.assertEquals(target.getHackathonName(), ideaRecords.get(1).getHackathonID().getEventName());
        Assert.assertEquals(target.getIdeaDTOList().size(), ideaRecords.size());
    }

    @Test
    public void addIdeaNormal() throws BlankFieldException {
        Mockito.when(mockHackathonServiceImplementation.getHackathonByID(1)).thenReturn(ideaRecords.get(0).getHackathonID());
        Mockito.when(mockIdeaRepository.getMaxIdeaID()).thenReturn(ideaRecords.size() + 1);
        Mockito.when(mockMemberServiceImplementation.addMembers(Matchers.anyList(), Matchers.anyInt())).thenReturn(true);
        Mockito.when(mockIdeaRepository.save(Matchers.any(Idea.class))).thenReturn(targetIdea);
        ideaDTO.setIdeaDetails("Details");
        ideaDTO.setIdeaSummary("Summary");
        ideaDTO.setTeamMemberNames("dsadsadsadas\ndfsdss");

        ResponseDTO target = mockIdeaServiceImplementation.addIdea(ideaDTO, 1);

        Assert.assertEquals("SUCCESS", target.getStatus());
    }

    @Test(expected = BlankFieldException.class)
    public void addIdeaBlank() throws BlankFieldException {
        Mockito.when(mockHackathonServiceImplementation.getHackathonByID(1)).thenReturn(ideaRecords.get(0).getHackathonID());
        Mockito.when(mockIdeaRepository.getMaxIdeaID()).thenReturn(ideaRecords.size() + 1);
        Mockito.when(mockIdeaRepository.save(Matchers.any(Idea.class))).thenReturn(targetIdea);

        ideaDTO.setIdeaSummary("");
        ideaDTO.setIdeaDetails("");

        ResponseDTO target = mockIdeaServiceImplementation.addIdea(ideaDTO, 1);

    }

    @Test(expected = NullPointerException.class)
    public void addIdeaInnerBlank() throws BlankFieldException {
        Mockito.when(mockHackathonServiceImplementation.getHackathonByID(1)).thenReturn(ideaRecords.get(0).getHackathonID());
        Mockito.when(mockIdeaRepository.getMaxIdeaID()).thenReturn(ideaRecords.size() + 1);
        Mockito.when(mockIdeaRepository.save(Matchers.any(Idea.class))).thenReturn(null);

        ideaDTO.setIdeaSummary("fdfsdf");
        ideaDTO.setIdeaDetails("fefsdfs");

        ResponseDTO target = mockIdeaServiceImplementation.addIdea(ideaDTO, 1);

    }

    @Test
    public void updateIdeaNormal() throws BlankFieldException {
        Mockito.when(mockIdeaRepository.getOne(Matchers.anyInt())).thenReturn(ideaRecords.get(0));
        Mockito.when(mockIdeaRepository.save(Matchers.any(Idea.class))).thenReturn(targetIdea);
        //Mockito.when(mockMemberServiceImplementation.removeMembers(Matchers.any())).thenReturn(null);
        Mockito.when(mockMemberServiceImplementation.addMembers(Matchers.anyList(), Matchers.anyInt())).thenReturn(true);

        ideaDTO.setIdeaID(6);

        ResponseDTO target = mockIdeaServiceImplementation.updateIdea(ideaDTO);
        System.out.println(target);

        Assert.assertEquals(target.getStatus(), "SUCCESS");
    }


    @Test(expected = BlankFieldException.class)
    public void updateIdeaInnerBlank() throws BlankFieldException {
        Mockito.when(mockIdeaRepository.getOne(Matchers.anyInt())).thenReturn(ideaRecords.get(0));
        Mockito.when(mockIdeaRepository.save(Matchers.any(Idea.class))).thenReturn(targetIdea);
        //Mockito.when(mockMemberServiceImplementation.removeMembers(Matchers.any())).thenReturn(null);
        Mockito.when(mockMemberServiceImplementation.addMembers(Matchers.anyList(), Matchers.anyInt())).thenReturn(false);

        ideaDTO.setIdeaID(6);

        ResponseDTO target = mockIdeaServiceImplementation.updateIdea(ideaDTO);
    }

    @Test
    public void likeIdea() throws BlankFieldException {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setideaID(1);

        Mockito.when(mockIdeaRepository.addLikeToIdea(Matchers.anyInt())).thenReturn(1);

        ResponseDTO target = mockIdeaServiceImplementation.likeIdea(likeDTO);

        Assert.assertEquals(target.getStatus(), "SUCCESS");
    }

   /* @Test
    public void IdeaToIdeaDTO() {
        Assert.assertEquals(mockIdeaServiceImplementation.IdeaToIdeaDTO(targetIdea).getIdeaDetails(), ideaDTO.getIdeaDetails());
        Assert.assertEquals(mockIdeaServiceImplementation.IdeaToIdeaDTO(targetIdea).getIdeaSummary(), ideaDTO.getIdeaSummary());
        Assert.assertEquals(mockIdeaServiceImplementation.IdeaToIdeaDTO(targetIdea).getCategoryName(), ideaDTO.getCategoryName());
    }*/
}
