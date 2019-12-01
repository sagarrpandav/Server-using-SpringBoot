package com.evaluation.demo.controller;

import com.evaluation.demo.customException.BlankFieldException;
import com.evaluation.demo.dto.HackathonCategoryDto;
import com.evaluation.demo.dto.IdeaDTO;
import com.evaluation.demo.dto.LikeDTO;
import com.evaluation.demo.dto.MemberDTO;
import com.evaluation.demo.dto.ResponseDTO;
import com.evaluation.demo.service.IdeaServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*",allowedHeaders = "*")
public class IdeaController {
    @Autowired
    private IdeaServiceImplementation ideaServiceImplementation;

    @GetMapping(value = "/hackathon/{hackathonID}/{memberID}/ideas")
    public HackathonCategoryDto getAllIdeasPerHackathonID(@PathVariable int hackathonID,@PathVariable int memberID) {
        return ideaServiceImplementation.getAllIdeasPerHackathon(hackathonID,memberID);
    }

    @PostMapping(value = "/hackathons/{hackathonID}/ideas")
    public ResponseDTO addIdeaToHackathon(@RequestBody IdeaDTO ideaDTO, @PathVariable int hackathonID) throws BlankFieldException {
        return ideaServiceImplementation.addIdea(ideaDTO, hackathonID);
    }

    @PutMapping(value = "/hackathons/ideas/{ideaID}")
    public ResponseDTO editIdeaByIdeaID(@RequestBody IdeaDTO ideaDTO, @PathVariable int ideaID) throws BlankFieldException {
        System.out.println(ideaDTO.getIdeaDetails());
        return ideaServiceImplementation.updateIdea(ideaDTO);
    }

    @PutMapping(value = "/hackathons/ideas/{ideaID}/like")
    public ResponseDTO likeIdeaByIdeaID(@RequestBody LikeDTO likeDTO, @PathVariable int ideaID) throws BlankFieldException {
        return ideaServiceImplementation.likeIdea(likeDTO);
    }


    @PutMapping(value = "/hackathons/ideas/{ideaID}/dislike")
    public ResponseDTO dislikeIdeaByIdeaID(@RequestBody LikeDTO likeDTO, @PathVariable int ideaID) throws BlankFieldException {
        return ideaServiceImplementation.dislikeIdea(likeDTO);
    }
}
