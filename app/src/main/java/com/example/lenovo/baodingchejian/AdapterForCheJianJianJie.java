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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AdapterForCheJianJianJie extends ArrayAdapter<ProjectItemForCheJianJianJie> {
    private int resourceId;
    private Context context;
    public AdapterForCheJianJianJie(@NonNull Context context, int resource, @NonNull List<ProjectItemForCheJianJianJie> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ProjectItemForCheJianJianJie banZuXinXi = getItem(position);
        View viewForBanZuXinXiItem;
        context = parent.getContext();
        if (convertView == null) {
            viewForBanZuXinXiItem = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else {
            viewForBanZuXinXiItem = convertView;
        }

        ImageView banzuxinxiImage = (ImageView)viewForBanZuXinXiItem.findViewById(R.id.banzuxinxiImage);
        TextView banzuxinxiTextView = (TextView)viewForBanZuXinXiItem.findViewById(R.id.banzuxinxiTextView);
        //banzuxinxiImage.setImageResource(banZuXinXi.getImageId());
        Glide.with(context).load(banZuXinXi.getUrl()).dontAnimate().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(banzuxinxiImage);
        banzuxinxiTextView.setText(banZuXinXi.getName());

        return viewForBanZuXinXiItem;
    }
}
