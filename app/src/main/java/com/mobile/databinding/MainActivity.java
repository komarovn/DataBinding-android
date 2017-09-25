package com.mobile.databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
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

        addOnChildClickHandler(hoursStart, (TimeListAdapter) hoursStartAdapter, startTime, true);
        addOnGroupExpandHandler(hoursStart, (TimeListAdapter) hoursStartAdapter);
        addOnGroupCollapseHandler(hoursStart, (TimeListAdapter) hoursStartAdapter);

        final ExpandableListView minsStart = (ExpandableListView) findViewById(R.id.minsStart);
        final ExpandableListAdapter minsStartAdapter = new TimeListAdapter(this, DateTimeUtils.getMinutes());
        minsStart.setAdapter(minsStartAdapter);

        addOnChildClickHandler(minsStart, (TimeListAdapter) minsStartAdapter, startTime, false);
        addOnGroupExpandHandler(minsStart, (TimeListAdapter) minsStartAdapter);
        addOnGroupCollapseHandler(minsStart, (TimeListAdapter) minsStartAdapter);

    }

    public void addOnChildClickHandler(final ExpandableListView listView,
                                       final TimeListAdapter adapter,
                                       final Time time,
                                       final boolean isHours) {
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                adapter.setCurrentSelected(childPosition);
                if (isHours) {
                    time.setHours(childPosition);
                } else {
                    time.setMins(childPosition);
                }
                listView.collapseGroup(0);
                processResult();
                return false;
            }
        });
    }

    public void addOnGroupExpandHandler(final ExpandableListView listView,
                                        final TimeListAdapter adapter) {
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                listView.getLayoutParams().height = listView.getHeight() * 5;
                adapter.getTitle().getLayoutParams().height = 0;
                /*listView.scrollTo(0, convertDpToPx(26) * adapter.getCurrentSelected());*/
            }
        });
    }

    public void addOnGroupCollapseHandler(final ExpandableListView listView,
                                        final TimeListAdapter adapter) {
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                listView.getLayoutParams().height = listView.getHeight() / 5;
                adapter.getTitle().getLayoutParams().height = convertDpToPx(56);
            }
        });
    }

    public void processResult() {
        int result = executor.convertTimeIntoMins(startTime, endTime);
        resultTextView.setText(String.valueOf(result));
    }

    public int convertDpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}
