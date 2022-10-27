package com.phonecompany.billing;

import java.time.LocalDateTime;

public class ReducedTariff implements ITariff{
    private static final double PRICE = 0.5;

    private static final int MIN_HOUR = 8;
    private static final int MAX_HOUR = 16;
    public boolean isApplied(Call call) {
        LocalDateTime start = call.getStartCall();
        LocalDateTime finish = call.getFinishCall();
        return start.getHour() < MIN_HOUR || finish.getHour() > MAX_HOUR;
    }

    public PricePeriod getPricePeriod(Call call){
        if(!isApplied(call)) return null;
        LocalDateTime start = call.getStartCall();
        LocalDateTime finish = call.getFinishCall();

        LocalDateTime newFinish = finish;
        if(start.getHour() < MIN_HOUR && finish.getHour() > MIN_HOUR){
            newFinish = LocalDateTime.of(finish.getYear(), finish.getMonth(), finish.getDayOfMonth(), MIN_HOUR,0, start.getSecond());
        }

        LocalDateTime newStart = start;
        if(start.getHour() < MAX_HOUR && finish.getHour() > MAX_HOUR){
            newStart = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), MAX_HOUR,0, start.getSecond());
        }

        return new PricePeriod(newStart, newFinish, PRICE);
    }

}
