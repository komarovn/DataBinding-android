package com.mobile.databinding;

import java.util.ArrayList;
import java.util.List;

public abstract class DateTimeUtils {

    public static List<String> getHours() {
        List<String> hrs = new ArrayList<String>();

        for (int hour = 0; hour <= 24; hour++) {
            hrs.add(String.valueOf(hour));
        }

        return hrs;
    }

    public static List<String> getMinutes() {
        List<String> mins = new ArrayList<String>();

        for (int minute = 0; minute < 60; minute++) {
            mins.add(String.valueOf(minute));
        }

        return mins;
    }
}
