package com.github.pyrinoff.utils;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public interface DateUtil {

    static String getDateAsString(@NotNull final Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date);
    }

    static String getDateAsString() {
        return getDateAsString(new Date());
    }

    static String getUTCDateTimeAsString() {
        return getDateAsString(Date.from(Instant.now()));
    }

    static Date setDateToDayStart(Date date) {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }

    static Date setDateToDayEnd(Date date) {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }


    static String getCurrentDateAtDayStart() {
        return getDateAsString(setDateToDayStart(new Date()));
    }

    static Date getDatePlusDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
