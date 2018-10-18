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

public class AdapterForDingWeiListView extends ArrayAdapter<DingWeiListViewItem>{
    private int resourceId;
    public AdapterForDingWeiListView(@NonNull Context context, int resource, @NonNull List<DingWeiListViewItem> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DingWeiListViewItem commonListViewItem = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else {
            view = convertView;
        }

        TextView biaoti = (TextView)view.findViewById(R.id.dingweibiaotiListViewTextView);
        TextView gongli = (TextView)view.findViewById(R.id.dingweigongliListViewTextView);
        biaoti.setText(commonListViewItem.getBiaoti());
        gongli.setText(commonListViewItem.getGongli());
        return view;
    }
}
