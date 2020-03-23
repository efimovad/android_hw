package com.technopark.firsthw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView list = findViewById(R.id.list);

        list.setLayoutManager(
                new GridLayoutManager(this, Integer.parseInt(getString(R.string.col_num)))
        );
        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        new Handler().postDelayed(() -> adapter.updateWith(DataSource.getInstance().getData()), 2000);

        Button button = (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                adapter.addItem();
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        private final List<DataSource.MyData> datas = new ArrayList<>();

        public void updateWith(List<DataSource.MyData> newData) {
            datas.clear();
            datas.addAll(newData);
            notifyDataSetChanged();
        }

        public void addItem() {
            DataSource.getInstance().updateData();
            updateWith(DataSource.getInstance().getData());
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            int layoutToInflate = R.layout.list_item;

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
                    Intent intent = new Intent(ListActivity.this, DetailedActivity.class);
                    intent.putExtra("NUM", Integer.toString(datas.get(position).mNum));
                    intent.putExtra("COLOR", Integer.toString(mColor));
                    startActivity(intent);
                });
            }
        }
    }
}
