package com.evaluation.demo.entity;

import javax.persistence.*;

@Entity
public class Member
{
    @Id
    @Column(name = "memberID")
    private int memberID;

    @Column(name = "memberName")
    private String memberName;

    //@Column(name = "memberIdea")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ideaID")
    private Idea memberIdea;

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Idea getMemberIdea() {
        return memberIdea;
    }

    public void setMemberIdea(Idea memberIdea) {
        this.memberIdea = memberIdea;
    }
}
