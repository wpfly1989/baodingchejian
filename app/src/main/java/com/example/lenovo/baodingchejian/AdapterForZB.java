package com.example.lenovo.baodingchejian;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import q.rorbin.badgeview.QBadgeView;

public class AdapterForZB extends RecyclerView.Adapter<AdapterForZB.ViewHolder>{
    private List<ProjectItemForCheJian> myList;
    private Context context;
    private int where;
    private OnItemClickListener myClickListener = null;

    class ViewHolder extends RecyclerView.ViewHolder {

        View projectView;
        ImageView projectImage;
        TextView projectName;

        public ViewHolder(View itemView) {
            super(itemView);
            projectView = itemView;
            projectImage = (ImageView) itemView.findViewById(R.id.zhuguanbanzutupian);
            projectName = (TextView) itemView.findViewById(R.id.zhuguanbanzuwenzi);

        }
    }

    public AdapterForZB(List<ProjectItemForCheJian> mmyList) {
        myList = mmyList;
    }

    public AdapterForZB(Context context, List<ProjectItemForCheJian> mmyList) {
        this.context = context;
        myList = mmyList;
    }
    @NonNull
    @Override
    public AdapterForZB.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewForZB = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout2, parent, false);
        final ViewHolder holder = new ViewHolder(viewForZB);
        where = holder.getAdapterPosition();

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterForZB.ViewHolder holder, int position) {

        ProjectItemForCheJian item = myList.get(position);
        holder.projectImage.setImageResource(item.getImageId());
        holder.projectName.setText(item.getName());



        if (myClickListener != null) {
            holder.projectImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    myClickListener.OnClick(holder.itemView, position);
                }
            });
        }

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
