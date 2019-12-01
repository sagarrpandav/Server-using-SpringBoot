package com.evaluation.demo.service;

import com.evaluation.demo.dto.HackathonDto;
import com.evaluation.demo.entity.Hackathon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface HackathonService
{
    List<HackathonDto> getAllHackathons();
    String getHackathonName(int hackathonID);
    Hackathon getHackathonByID(int hackathonID);



    boolean incrementNumberOfIdeas(int hackathonID);
}
