package com.mobile.databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TimeCalculatorExecutor executor = new TimeCalculatorExecutor();

    private Time startTime = new Time(0, 0);
    private Time endTime = new Time(0, 0);

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.result);

        final ExpandableListView hoursStart = (ExpandableListView) findViewById(R.id.hoursStart);
        final ExpandableListAdapter hoursStartAdapter = new TimeListAdapter(this, DateTimeUtils.getHours());
        hoursStart.setAdapter(hoursStartAdapter);

        hoursStart.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                ((TimeListAdapter) hoursStartAdapter).setCurrentSelected(childPosition);
                startTime.setHours(childPosition);
                hoursStart.collapseGroup(0);
                int height = hoursStart.getHeight();
                int mHeight = hoursStart.getMeasuredHeight();
                processResult();
                return false;
            }
        });


    }

    public void processResult() {
        int result = executor.convertTimeIntoMins(startTime, endTime);
        resultTextView.setText(String.valueOf(result));
    }

}
