package com.phonecompany.billing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Call {
    private final String phoneNumber;
    private final LocalDateTime startCall;
    private final LocalDateTime finishCall;
    private List<PricePeriod> pricePeriodList;

    public Call(String phoneNumber, LocalDateTime startCall, LocalDateTime finishCall){
        this.phoneNumber = phoneNumber;
        this.startCall = startCall;
        this.finishCall = finishCall;
        pricePeriodList = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getAccountedStart(){
        return pricePeriodList.stream().map(PricePeriod::getStart).min(LocalDateTime::compareTo).get();
    }

    public LocalDateTime getAccountedFinish(){
        return pricePeriodList.stream().map(PricePeriod::getFinish).max(LocalDateTime::compareTo).get();
    }

    public LocalDateTime getStartCall(){
        return startCall;
    }

    public LocalDateTime getFinishCall(){
        return  finishCall;
    }

    public void addPricePeriod(PricePeriod pricePeriod){

        if(pricePeriod == null) return;

        if(pricePeriodList.isEmpty()){
            pricePeriodList.add(pricePeriod);
            return;
        }
        LocalDateTime accStart = getAccountedStart();

        if (pricePeriod.getStart().isBefore(accStart) &&
                (pricePeriod.getFinish().isAfter(accStart) || pricePeriod.getFinish().equals(accStart))){
            pricePeriodList.add(new PricePeriod(pricePeriod.getStart(),accStart,pricePeriod.getPrice()));
        }

    }

    public List<PricePeriod> getPricePeriodList() {
        return pricePeriodList;
    }
}
