package com.mobile.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class TimeCalculatorExecutor extends BaseObservable {
    private String differenceInMins;

    @Bindable
    public String getDifferenceInMins() {
        return differenceInMins;
    }

    public void convertTimeIntoMins(Time startTime, Time endTime) {
        int difference = endTime.convertToMins() - startTime.convertToMins();
        differenceInMins = String.valueOf(difference);
        notifyPropertyChanged(BR.differenceInMins);
    }

}
