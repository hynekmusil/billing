package com.phonecompany.billing;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BestNumberTariff implements ITariff {

    private final Optional<Long> bestPhoneNumber;
    private static final double PRICE = 0;

    public BestNumberTariff(List<Call> callList) {

        bestPhoneNumber = callList.stream()
                .collect(
                        Collectors.groupingBy(
                                Call::getPhoneNumber, Collectors.counting()
                        ))
                .entrySet().stream()
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getValue,
                                Collectors.mapping(e -> Long.parseLong(e.getKey()), Collectors.maxBy(Long::compare))
                        )
                ).entrySet().stream().max(Map.Entry.comparingByKey()).flatMap(Map.Entry::getValue);
    }

    public boolean isApplied(Call call) {
        return Long.parseLong(call.getPhoneNumber()) == bestPhoneNumber.orElse(0L);
    }

    public PricePeriod getPricePeriod(Call call) {
        if(!isApplied(call)) return null;
        return new PricePeriod(call.getStartCall(), call.getFinishCall(), PRICE);
    }
}
