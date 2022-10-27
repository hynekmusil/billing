package com.phonecompany.billing;

import java.time.Duration;
import java.time.LocalDateTime;

public class PricePeriod {
    private final LocalDateTime start;
    private final LocalDateTime finish;
    private final double price;

    public PricePeriod(LocalDateTime start, LocalDateTime finish, double price) {
        this.start = start;
        this.finish = finish;
        this.price = price;
    }

    public LocalDateTime getStart(){
        return start;
    }

    public LocalDateTime getFinish(){
        return finish;
    }

    public double getPrice(){
        return price;
    }

    private long getMinutes(){
        Duration duration = Duration.between(this.start, this.finish);
        return duration.toMinutes();
    }

    public double getTotalPrice(){
        return price * getMinutes();
    }
}
