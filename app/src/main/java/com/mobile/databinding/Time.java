package com.mobile.databinding;

public class Time {
    private int hours;
    private int mins;

    public Time(int hours, int mins) {
        this.hours = hours;
        this.mins = mins;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    /**
     * Converts specified time into minutes.
     * @return count of minutes from 00:00 till specified.
     */
    public int convertToMins() {
        return hours * 60 + mins;
    }
}
