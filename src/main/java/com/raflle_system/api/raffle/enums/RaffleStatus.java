package com.raflle_system.api.raffle.enums;

public enum RaffleStatus {
    DRAFT("draft"),
    OPEN("open"),
    CLOSED("closed"),
    FINISHED("finished"),
    DRAWN("drawn"),
    CANCELLED("cancelled");

    private String raffleStatus;

    RaffleStatus(String raffleStatus){
        this.raffleStatus = raffleStatus;
    }

    public String getRaffleStatus(){
        return raffleStatus;
    }
}
