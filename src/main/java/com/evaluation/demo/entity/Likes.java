package com.evaluation.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Likes
{
    @Id
    @Column(name = "likeID")
    private int likeID;

    //@Column(name = "member")
    @OneToOne
    @JoinColumn(name = "memberID")
    private Member memberID;

    @NotNull
    @OneToOne
    @JoinColumn(name = "ideaID")
    private Idea ideaID;

    public int getLikeID() {
        return likeID;
    }

    public void setLikeID(int likeID) {
        this.likeID = likeID;
    }

    public Member getMemberID() {
        return memberID;
    }

    public void setMemberID(Member memberID) {
        this.memberID = memberID;
    }

    public Idea getIdeaID() {
        return ideaID;
    }

    public void setIdeaID(Idea ideaID) {
        this.ideaID = ideaID;
    }
}