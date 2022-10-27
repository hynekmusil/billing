package com.phonecompany.billing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CallReader {

    private static final String COMMA_DELIMITER = ",";

    private static final DateTimeFormatter dateTimeFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public List<Call> run(String filePath){
        List<Call> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null){
                String[] values = line.split(COMMA_DELIMITER);
                String phoneNumber = values[0];
                LocalDateTime startCall = LocalDateTime.parse(values[1], dateTimeFormater);
                LocalDateTime finishCall = LocalDateTime.parse(values[2], dateTimeFormater);
                result.add(new Call(phoneNumber, startCall, finishCall));
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
