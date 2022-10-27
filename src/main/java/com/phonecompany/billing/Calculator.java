package com.phonecompany.billing;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private List<ITariff> tariffList;
    private List<Call> callList;

    public Calculator(List<Call> callList){
        this.callList = callList;
        tariffList = new ArrayList<>();
        tariffList.add(new BestNumberTariff(callList));
        tariffList.add(new ForLongerCallTariff());
        tariffList.add(new BaseTariff());
        tariffList.add(new ReducedTariff());
    }

    public double calc(){
        tariffList.forEach(tariff -> callList.stream().filter(
                tariff::isApplied
        ).forEach(call -> call.addPricePeriod(tariff.getPricePeriod(call))));

        return callList.stream()
                .flatMap(x -> x.getPricePeriodList().stream())
                .map(PricePeriod::getTotalPrice)
                .reduce(0.0, Double::sum);
    }


}
