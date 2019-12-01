package com.evaluation.demo.service;

import com.evaluation.demo.dto.HackathonDto;
import com.evaluation.demo.entity.Hackathon;

import com.evaluation.demo.repository.HackathonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HackathonServiceTest {

    private List<Hackathon> hackathonRecords;

    @InjectMocks
    HackathonServiceImplementation mockHackathonServiceImplementation;

    @Mock
    HackathonRepository mockHackathonRepository;

    @Before
    public void setup() {
        // mock database hackathon records
        hackathonRecords = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Hackathon hackathon = new Hackathon();
            hackathon.setEventName(i + "Dummy Event Name");
            hackathon.setDateConducted(LocalDate.of(9999, 12, 12));
            hackathon.setHackathonID(i);
            hackathon.setMoOffice(i + "Office");
            hackathon.setTotalIdeas(i * 4);

            hackathonRecords.add(hackathon);
        }
    }

    @Test
    public void getAllHackathons() {
        Mockito.when(mockHackathonRepository.findAll()).thenReturn(hackathonRecords);

        List<HackathonDto> hackathonDtoList = mockHackathonServiceImplementation.getAllHackathons();

        Assert.assertEquals(hackathonRecords.size(), hackathonDtoList.size());
    }

    @Test
    public void getHackathonName() {
        Mockito.when(mockHackathonRepository.getOne(1)).thenReturn(hackathonRecords.get(1));
        String hackathonName = mockHackathonServiceImplementation.getHackathonName(1);

        Assert.assertEquals(hackathonName, hackathonRecords.get(1).getEventName());
    }

    @Test
    public void getHackathonByID() {
        Mockito.when(mockHackathonRepository.getOne(1)).thenReturn(hackathonRecords.get(1));

        Assert.assertEquals(mockHackathonServiceImplementation.getHackathonByID(1), hackathonRecords.get(1));
    }
}