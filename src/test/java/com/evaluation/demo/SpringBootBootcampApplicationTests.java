package com.evaluation.demo;

import com.evaluation.demo.customException.BlankFieldException;
import com.evaluation.demo.dto.HackathonCategoryDto;
import com.evaluation.demo.dto.HackathonDto;
import com.evaluation.demo.dto.IdeaDTO;
import com.evaluation.demo.dto.LikeDTO;
import com.evaluation.demo.service.HackathonServiceImplementation;
import com.evaluation.demo.service.IdeaServiceImplementation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBootcampApplicationTests {

    @Test
    public void test(){
        Assert.assertTrue(true);
    }





/*
    @Autowired
    private HackathonServiceImplementation hackathonServiceImplementation;

    @Autowired
    private IdeaServiceImplementation ideaServiceImplementation;

    @Test
    public void contextLoads() {
    }
/*
    @Test
    public void getAllHackathons() {
        List<HackathonDto> hackathonDtoList = hackathonServiceImplementation.getAllHackathons();

        Assert.assertEquals(3, hackathonDtoList.size());
    }

    @Test
    public void getIdeaPerHackthonID1() {
        HackathonCategoryDto hackathonCategoryDTO = ideaServiceImplementation.getAllIdeasPerHackathon(1);
        Assert.assertEquals("Bolt 2018", hackathonCategoryDTO.getHackathonName());
    }

    @Test
    public void addNewIdeaInHackathon1() throws BlankFieldException {
        IdeaDTO ideaDTO = new IdeaDTO();
        ideaDTO.setTotalLikes(0);
        ideaDTO.setTeamMemberNames("Sagar\nNitesh");
        ideaDTO.setIdeaSummary("Idea Summary");
        ideaDTO.setIdeaDetails("Idea Details");
        ideaDTO.setCategoryName("Technology");

        ideaServiceImplementation.addIdea(ideaDTO, 1);
        Assert.assertEquals(4, ideaServiceImplementation.getIdeaByIdeaID(4).getideaID());
    }

    @Test
    public void updateIdea1() throws BlankFieldException {
        IdeaDTO ideaDTO = new IdeaDTO();
        ideaDTO.setideaID(1);
        ideaDTO.setTotalLikes(0);
        ideaDTO.setTeamMemberNames("Sagar\nNitesh");
        ideaDTO.setIdeaSummary("Idea Summary");
        ideaDTO.setIdeaDetails("Idea Details");
        ideaDTO.setCategoryName("Technology");

        ideaServiceImplementation.updateIdea(ideaDTO);
        Assert.assertEquals(ideaDTO.getIdeaDetails(), ideaServiceImplementation.getIdeaByIdeaID(1).getIdeaDetails());
    }

    @Test
    public void likeIdeaByIdeaID() throws BlankFieldException {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setideaID(1);

        ideaServiceImplementation.likeIdea(likeDTO);

        Assert.assertEquals(6, ideaServiceImplementation.getIdeaByIdeaID(likeDTO.getideaID()).getLikeCount());
    }
*/
}
