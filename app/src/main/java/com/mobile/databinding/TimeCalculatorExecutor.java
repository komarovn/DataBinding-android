package com.mobile.databinding;

public class TimeCalculatorExecutor {

    public int convertTimeIntoMins(Time startTime, Time endTime) {
        return endTime.convertToMins() - startTime.convertToMins();
    }

}
