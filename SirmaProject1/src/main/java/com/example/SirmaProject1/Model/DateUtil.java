package com.example.SirmaProject1.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DateUtil {
    public  LocalDate parseDate(String date) {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"+ "[YY/MM/DD]"+"[YY/M/D]" ));
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
