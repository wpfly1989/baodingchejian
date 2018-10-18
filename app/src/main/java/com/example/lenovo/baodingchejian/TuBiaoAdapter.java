package com.example.lenovo.baodingchejian;

import android.annotation.SuppressLint;
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

public class TuBiaoAdapter extends ArrayAdapter<ProjectItemForBanZuTuBiao>{
    private int resourceId;
    public TuBiaoAdapter(@NonNull Context context, int resource, @NonNull List<ProjectItemForBanZuTuBiao> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ProjectItemForBanZuTuBiao tuBiao = getItem(position);
        @SuppressLint("ViewHolder")
        View viewForBanZuTuBiao;
        //ViewHolder viewHolder;
        if(convertView == null) {
            viewForBanZuTuBiao = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            //viewHolder = new ViewHolder();
            /*tubiaoImage = (ImageView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoImageView);
            tubiaoDa = (TextView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoDaBiaoTi);
            tubiaoxiao = (TextView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoXiaoBiaoTi);
            tubiaozhong = (TextView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoZhongBiaoTi);*/
        } else {
            viewForBanZuTuBiao = convertView;
            //viewHolder = (ViewHolder) viewForBanZuTuBiao.getTag();
        }
        ImageView tubiaoImage = (ImageView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoImageView);
        TextView tubiaoda = (TextView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoDaBiaoTi);
        TextView tubiaoxiao = (TextView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoXiaoBiaoTi);
        TextView tubiaozhong = (TextView)viewForBanZuTuBiao.findViewById(R.id.banzutubiaoZhongBiaoTi);

        assert tuBiao != null;
        tubiaoImage.setImageResource(tuBiao.getImageId());
        tubiaoda.setText(tuBiao.getTuBiaoDa());
        tubiaoxiao.setText(tuBiao.getTuBiaoXiao());
        tubiaozhong.setText(tuBiao.getTuBiaoZhong());
        return viewForBanZuTuBiao;
    }

    /*class ViewHolder{
        ImageView tubiaoImage;
        TextView tubiaoDa;
        TextView tubiaoxiao;
        TextView tubiaozhong;
    }*/
}
