package com.evaluation.demo.dto;

import java.time.LocalDate;

public class HackathonDto
{
    private int id;
    private String eventName;
    private String moOffice;
    private LocalDate dateConducted;
    private int totalIdeas;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
