package com.mobile.databinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

public class TimeListAdapter extends BaseExpandableListAdapter {

    private Context owner;
    private List<String> data;
    private int currentSelected;

    public TimeListAdapter(Context owner, List<String> data) {
        this.owner = owner;
        this.data = data;
        this.currentSelected = 0;
    }

    public int getCurrentSelected() {
        return currentSelected;
    }

    public void setCurrentSelected(int currentSelected) {
        this.currentSelected = currentSelected;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return 1;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String currentValue = data.get(currentSelected);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) owner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.time_list, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.timeListTitle);
        title.setText(currentValue);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String textItem = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) owner.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.time_list_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.timeListItem);
        item.setText(textItem);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
