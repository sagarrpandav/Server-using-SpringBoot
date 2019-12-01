package com.evaluation.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Idea
{
    @Id
    @Column(name = "ideaID")
    private int ideaID;

    //@Column(name = "hackathonID")
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hackathonID")
    private Hackathon hackathonID;

    @Column(name = "ideaSummary")
    private String ideaSummary;

    @Column(name = "ideaDetails")
    private String ideaDetails;

    //@Column(name = "ideaCategory")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryID")
    private Category ideaCategory;

    @Column(name = "teamCount")
    private int teamCount;

    @Column(name = "likeCount")
    private int likeCount;

    public int getIdeaID() {
        return ideaID;
    }

    public void setIdeaID(int ideaID) {
        this.ideaID = ideaID;
    }

    public Hackathon getHackathonID() {
        return hackathonID;
    }

    public void setHackathonID(Hackathon hackathonID) {
        this.hackathonID = hackathonID;
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

    public Category getIdeaCategory() {
        return ideaCategory;
    }

    public void setIdeaCategory(Category ideaCategory) {
        this.ideaCategory = ideaCategory;
    }

    public int getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
