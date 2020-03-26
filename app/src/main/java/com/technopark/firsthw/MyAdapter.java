package com.technopark.firsthw;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private AdapterListener listener;
    private final List<DataSource.MyData> datas = new ArrayList<>();

    public interface AdapterListener {
        void onItemClick(int num, int color);
    }

    public void setListener(AdapterListener listener) {
        this.listener = listener;
    }

    public void updateWith(List<DataSource.MyData> newData) {
        datas.clear();
        datas.addAll(newData);
        notifyItemInserted(datas.size()-1);
    }

    public void addItem() {
        DataSource.getInstance().updateData();
        updateWith(DataSource.getInstance().getData());
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutToInflate = R.layout.item;

        View view = LayoutInflater.from(parent.getContext())
                .inflate(layoutToInflate, parent, false);

        return new MyHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        DataSource.MyData data = datas.get(position);
        holder.mTitle.setText(Integer.toString(data.mNum));
        holder.mTitle.setTextColor(holder.mColor);
        holder.bindClickListener(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 1 ? 0 : 1;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private int mColor;

        public MyHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mColor = (viewType == 0) ? Color.RED : Color.BLUE;
        }

        void bindClickListener(int position) {
            itemView.findViewById(R.id.title).setOnClickListener(v -> {
                listener.onItemClick(datas.get(position).mNum, mColor);
            });
        }
    }
}

