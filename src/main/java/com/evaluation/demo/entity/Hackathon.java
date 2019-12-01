package com.evaluation.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Hackathon
{
    @Id
    @Column(name = "hackathonID")
    private int hackathonID;

    @Column(name = "eventName")
    private String eventName;

    @Column(name = "moOffice")
    private String moOffice;

    @Column(name = "dateConducted")
    private LocalDate dateConducted;

    @Column(name = "totalIdeas")
    private int totalIdeas;

    public int getHackathonID() {
        return hackathonID;
    }

    public void setHackathonID(int hackathonID) {
        this.hackathonID = hackathonID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getMoOffice() {
        return moOffice;
    }

    public void setMoOffice(String moOffice) {
        this.moOffice = moOffice;
    }

    public LocalDate getDateConducted() {
        return dateConducted;
    }

    public void setDateConducted(LocalDate dateConducted) {
        this.dateConducted = dateConducted;
    }

    public int getTotalIdeas() {
        return totalIdeas;
    }

    public void setTotalIdeas(int totalIdeas) {
        this.totalIdeas = totalIdeas;
    }
}
