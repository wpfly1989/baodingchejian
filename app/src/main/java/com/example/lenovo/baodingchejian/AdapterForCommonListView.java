package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterForCommonListView extends ArrayAdapter<CommonListViewItem>{
    private int resourceId;
    public AdapterForCommonListView(@NonNull Context context, int resource, @NonNull List<CommonListViewItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CommonListViewItem commonListViewItem = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else {
            view = convertView;
        }

        TextView textView = (TextView)view.findViewById(R.id.commonListViewTextView);
        textView.setText(commonListViewItem.getName());
        return view;
    }
}
