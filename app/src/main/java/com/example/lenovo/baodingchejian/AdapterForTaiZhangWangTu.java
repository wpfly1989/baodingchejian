package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterForTaiZhangWangTu extends RecyclerView.Adapter<AdapterForTaiZhangWangTu.ViewHolder> {
    private List<ProjectItemForTaiZhangWangTu> myList;
    private OnItemClickListener myClickListener = null;
    private Context context;

    class ViewHolder extends RecyclerView.ViewHolder {

        View projectView;
        ImageView projectImage;
        TextView projectName;

        public ViewHolder(View itemView) {
            super(itemView);
            projectView = itemView;
            projectImage = (ImageView) itemView.findViewById(R.id.taizhangwangtuimageview);
            projectName = (TextView) itemView.findViewById(R.id.taizhangwangtutextview);

        }
    }

    public AdapterForTaiZhangWangTu(List<ProjectItemForTaiZhangWangTu> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public AdapterForTaiZhangWangTu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout7,parent,false);
        context = parent.getContext();
        final ViewHolder  holder = new ViewHolder(view);

        if (myClickListener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    ProjectItemForTaiZhangWangTu projectItemForTaiZhangWangTu = myList.get(position);
                    myClickListener.OnClick(holder.itemView, position);
                }
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForTaiZhangWangTu.ViewHolder holder, int position) {
        ProjectItemForTaiZhangWangTu item = myList.get(position);
        Glide.with(context).load(item.getUrl()).dontAnimate().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(holder.projectImage);
        //holder.projectImage.setImageURI(Uri.parse(item.getUrl()));
        holder.projectName.setText(item.getName());


    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.myClickListener = listener;
    }

    public interface OnItemClickListener {
        void OnClick(View view ,int position);
    }
}
