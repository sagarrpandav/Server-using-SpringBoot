package com.evaluation.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class HackathonCategoryDto
{
    private String hackathonName;
    private List<IdeaDTO> ideaDTOList;


    public String getHackathonName() {
        return hackathonName;
    }

    public void setHackathonName(String hackathonName) {
        this.hackathonName = hackathonName;
    }

    public List<IdeaDTO> getIdeaDTOList() {
        return ideaDTOList;
    }

    public void setIdeaDTOList(List<IdeaDTO> ideaDTOList) {
        this.ideaDTOList = ideaDTOList;
    }
}
