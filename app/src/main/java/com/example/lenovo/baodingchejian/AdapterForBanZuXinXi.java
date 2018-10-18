package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterForBanZuXinXi extends ArrayAdapter<ProjectItemForBanZuXinXi>{
    private int resourceId;
    public AdapterForBanZuXinXi(@NonNull Context context, int resource, @NonNull List<ProjectItemForBanZuXinXi> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProjectItemForBanZuXinXi banZuXinXi = getItem(position);
        View viewForBanZuXinXiItem;
        if (convertView == null) {
            viewForBanZuXinXiItem = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else {
            viewForBanZuXinXiItem = convertView;
        }

        ImageView banzuxinxiImage = (ImageView)viewForBanZuXinXiItem.findViewById(R.id.banzuxinxiImage);
        TextView banzuxinxiTextView = (TextView)viewForBanZuXinXiItem.findViewById(R.id.banzuxinxiTextView);
        banzuxinxiImage.setImageResource(banZuXinXi.getImageId());
        banzuxinxiTextView.setText(banZuXinXi.getName());

        return viewForBanZuXinXiItem;
    }
}
