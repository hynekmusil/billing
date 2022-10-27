package com.phonecompany.billing;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.io.File;
import java.util.List;

@SpringBootTest
class CallReaderTest {

    public static List<Call> readInput(String name){
        CallReader callReader = new CallReader();
        ClassLoader classLoader = CallReaderTest.class.getClassLoader();
        File file = new File(classLoader.getResource(name).getFile());
        return callReader.run(file.getAbsolutePath());
    }

    @Test
    void readInputTest(){
        List<Call> calls = readInput("input.csv");
        Assert.isTrue(calls.size() == 2, "Count of call records");
    }

}
