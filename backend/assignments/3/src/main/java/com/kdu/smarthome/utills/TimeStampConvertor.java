package com.kdu.smarthome.utills;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeStampConvertor {
    private TimeStampConvertor(){

    }

    /**
     * Converts a timestamp from "yyyy-MM-dd'T'HH:mm:ss.SSSSSS" to "yyyy-MM-dd HH:mm:ss.SSSSSS" format.
     *
     * @param originalTimestamp Original timestamp string.
     * @return Formatted timestamp string.
     */

    public static String convertTimestampFormat(String originalTimestamp) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS");
        LocalDateTime dateTime = LocalDateTime.parse(originalTimestamp, inputFormatter);
        return dateTime.format(outputFormatter);
    }
}
