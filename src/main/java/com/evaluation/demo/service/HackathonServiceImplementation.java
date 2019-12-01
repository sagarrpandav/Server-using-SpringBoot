package com.evaluation.demo.service;

import com.evaluation.demo.dto.HackathonDto;
import com.evaluation.demo.entity.Hackathon;
import com.evaluation.demo.repository.HackathonRepository;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HackathonServiceImplementation implements HackathonService {
    @Autowired
    private HackathonRepository hackathonRepository;

    @Override
    public List<HackathonDto> getAllHackathons() {
        List<HackathonDto> hackathonDtoList = new ArrayList<>();
        List<Hackathon> hackathonList = hackathonRepository.findAll();

        /*for(Hackathon hackathon:hackathonList)
        {
            HackathonDto hackathonDTO=hackathonToHackathonDTO(hackathon);
            hackathonDtoList.add(hackathonDTO);
        }*/

        hackathonList.stream().forEach(hackathon ->
        {
            hackathonDtoList.add(hackathonToHackathonDTO(hackathon));
        });

        return hackathonDtoList;
    }

    @Override
    public String getHackathonName(int hackathonID) {
        Hackathon hackathon = hackathonRepository.getOne(hackathonID);
        return hackathon.getEventName();
    }

    @Override
    public Hackathon getHackathonByID(int hackathonID) {
        return hackathonRepository.getOne(hackathonID);
    }

    HackathonDto hackathonToHackathonDTO(Hackathon hackathon) {
        HackathonDto hackathonDTO = new HackathonDto();

        hackathonDTO.setId(hackathon.getHackathonID());
        hackathonDTO.setEventName(hackathon.getEventName());
        hackathonDTO.setDateConducted(hackathon.getDateConducted());
        hackathonDTO.setMoOffice(hackathon.getMoOffice());
        hackathonDTO.setTotalIdeas(hackathon.getTotalIdeas());

        return hackathonDTO;
    }

    @Override
    public boolean incrementNumberOfIdeas(int hackathonID) {

        System.out.println("Inside increm");
        int cnt=hackathonRepository.incrementIdeaCount(hackathonID);
        System.out.println(cnt);
        if(cnt>0)
        {
            System.out.println("Success");
            return true;
        }
        System.out.println("Faill");
        return false;
    }
}
