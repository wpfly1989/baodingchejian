package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import q.rorbin.badgeview.QBadgeView;

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
        if (commonListViewItem.getName().contains("截止日期")) {
            QBadgeView qBadgeView = new QBadgeView(parent.getContext());
            qBadgeView.bindTarget(textView);
            String msg = commonListViewItem.getName().substring(commonListViewItem.getName().indexOf("截止日期") + 5);

            Date datejiezhi = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                datejiezhi = simpleDateFormat.parse(msg);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date datenow = new Date();

            long diff = datejiezhi.getTime() - datenow.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            long plusdays = diff % (1000 * 60 * 60 * 24);
            long hours = plusdays / (1000 * 60 * 60);
            long plushours = plusdays % (1000 * 60 * 60);
            long min = plushours / (1000 * 60);
            String showmsg = "剩余" + Long.toString(days) + "天" + Long.toString(hours) + "小时" + Long.toString(min) + "分";

            if (days <= 0 && hours <= 0 && min <= 0) {
                showmsg = "已过截止日期";
            }

            qBadgeView.setBadgeText(showmsg);
        }
        return view;
    }
}
