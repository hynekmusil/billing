package com.phonecompany.billing;

public interface ITariff {
    boolean isApplied(Call call);
    PricePeriod getPricePeriod(Call call);
}
