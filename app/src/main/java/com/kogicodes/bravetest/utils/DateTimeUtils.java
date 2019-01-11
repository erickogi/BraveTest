package com.kogicodes.bravetest.utils;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

    public long getExpiryDateInMilliseconds(int secondsToExpiry) {
        DateTime now = DateTime.now();
        DateTime then = now.plusSeconds(secondsToExpiry);
        return then.getMillis();
    }

    public boolean isTokenValid(long expiryDateTime) {
        if (expiryDateTime == 0) {
            return false;
        }
        DateTime now = DateTime.now();

        return now.getMillis() < expiryDateTime;
    }

    public String getToday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        return dateFormat.format(date);

    }

    public String getDateAndTime(String date1q) {

        // 2019-01-10T10:10
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Date d = null;
        try {
            d = input.parse(date1q);
            return output.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return "";

    }
}
