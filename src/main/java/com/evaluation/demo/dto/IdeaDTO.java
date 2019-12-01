package com.evaluation.demo.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdeaDTO
{
    private int ideaID;
    private String ideaSummary;
    private String ideaDetails;
    private String categoryName;
    private String teamMemberNames;
    private int totalLikes;
    private LikeDTO likeDTO;
    private Boolean liked;

    public int getIdeaID() {
        return ideaID;
    }

    public void setIdeaID(int ideaID) {
        this.ideaID = ideaID;
    }

    public String getIdeaSummary() {
        return ideaSummary;
    }

    public void setIdeaSummary(String ideaSummary) {
        this.ideaSummary = ideaSummary;
    }

    public String getIdeaDetails() {
        return ideaDetails;
    }

    public void setIdeaDetails(String ideaDetails) {
        this.ideaDetails = ideaDetails;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTeamMemberNames() {
        return teamMemberNames;
    }

    public void setTeamMemberNames(String teamMemberNames) {
        this.teamMemberNames = teamMemberNames;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public LikeDTO getLikeDTO() {
        return likeDTO;
    }

    public void setLikeDTO(LikeDTO likeDTO) {
        this.likeDTO = likeDTO;
    }

    public int getTeamCount()
    {
        String[] strings=teamMemberNames.split("\n");
        return strings.length;
    }

    public List<String> calculateTeamMemberNamesList()
    {
        List<String> memberNames=new ArrayList<>();
        String[] strings=teamMemberNames.split("\n");
        for(String names : strings)
        {
            String[] tmp=names.split(",");
            Arrays.stream(tmp).forEach(memberNames :: add);
        }

        return memberNames;
    }
}
