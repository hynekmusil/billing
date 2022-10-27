package com.phonecompany.billing;

import java.time.Duration;

public class ForLongerCallTariff implements ITariff {
    private static final double PRICE = 0.2;
    private static final int MINUTES = 5;

    public boolean isApplied(Call call){
        Duration duration = Duration.between(call.getStartCall(), call.getFinishCall());
        return duration.toMinutes() > MINUTES;
    }

    public PricePeriod getPricePeriod(Call call){
        if(!isApplied(call)) return null;
        return new PricePeriod(call.getStartCall().plusMinutes(5), call.getFinishCall(), PRICE);
    }

}
