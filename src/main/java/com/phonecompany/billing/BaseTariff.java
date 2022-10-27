package com.phonecompany.billing;

import java.time.LocalDateTime;

public class BaseTariff implements ITariff {
    private static final double PRICE = 1;

    private static final int MIN_HOUR = 8;
    private static final int MAX_HOUR = 16;

    public boolean isApplied(Call call){
        LocalDateTime start = call.getStartCall();
        LocalDateTime finish = call.getFinishCall();
        if(start.getHour() < MIN_HOUR && finish.getHour() > MAX_HOUR) return true;
        if(start.getHour() >= MIN_HOUR && start.getHour() <= MAX_HOUR) return true;
        return finish.getHour() >= MIN_HOUR && finish.getHour() <= MAX_HOUR;
    }

    public PricePeriod getPricePeriod(Call call){
        if(!isApplied(call)) return null;
        LocalDateTime start = call.getStartCall();
        LocalDateTime finish = call.getFinishCall();
        if(start.getHour() < MIN_HOUR && finish.getHour() > MAX_HOUR)
            return new PricePeriod(start, finish, PRICE);

        LocalDateTime newStart = start;
        if(start.getHour() < MIN_HOUR){
            newStart = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), MIN_HOUR,0, start.getSecond());
        }
        LocalDateTime newFinish = finish;
        if(finish.getHour() > MAX_HOUR) {
            newFinish = LocalDateTime.of(finish.getYear(), finish.getMonth(), finish.getDayOfMonth(), MAX_HOUR,0, start.getSecond());
        }

        return new PricePeriod(newStart, newFinish, PRICE);
    }
}
