package com.phonecompany.billing;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

class BestNumberTariffTest {

    @Test
    void isApplied() {
        CallReader callReader = new CallReader();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("input.csv").getFile());
        List<Call> calls = callReader.run(file.getAbsolutePath());

        BestNumberTariff tariff = new BestNumberTariff(calls);
        Assert.isTrue(tariff.isApplied(calls.get(1)), "best phone");

    }
}
