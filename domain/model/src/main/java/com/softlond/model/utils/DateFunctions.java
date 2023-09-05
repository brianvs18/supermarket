package com.softlond.model.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateFunctions {

    private DateFunctions() {
    }

    public static long getActualTime() {
        var zdt = ZoneId.systemDefault();
        Instant date = LocalDate.now().atStartOfDay(zdt).toInstant();
        return date.toEpochMilli();
    }
}
