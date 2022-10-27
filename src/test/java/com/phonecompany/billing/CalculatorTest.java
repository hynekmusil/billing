package com.phonecompany.billing;

import org.junit.jupiter.api.Test;
import java.util.List;

class CalculatorTest {

    @Test
    void calc() {
        List<Call> calls = CallReaderTest.readInput("input.csv");
        Calculator calc = new Calculator(calls);
        double result = calc.calc();

    }

    @Test
    void minutesInBaseTarif() {


    }
}
