package com.evaluation.demo.dto;

import com.evaluation.demo.entity.Member;

public class LikeDTO
{
    private int likeID;
    private int ideaID;
    private int memberID;

    public int getLikeID() {
        return likeID;
    }

    public void setLikeID(int likeID) {
        this.likeID = likeID;
    }

    public int getideaID() {
        return ideaID;
    }

    public void setideaID(int ideaID) {
        this.ideaID = ideaID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }
}
