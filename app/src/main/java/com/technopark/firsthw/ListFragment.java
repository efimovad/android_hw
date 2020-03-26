package com.technopark.firsthw;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment implements MyAdapter.AdapterListener {
    private ListFragmentListener listener;

    private Button buttonAdd;
    private RecyclerView list;
    private MyAdapter adapter;

    @Override
    public void onItemClick(int num, int color) {
        listener.onDetailsItem(num, color);
    }

    public interface ListFragmentListener {
        void onDetailsItem(int num, int color);
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_list, null);

        list = v.findViewById(R.id.list);
        list.setLayoutManager(
                new GridLayoutManager(this.getContext(), Integer.parseInt(getString(R.string.col_num)))
        );
        adapter = new MyAdapter();
        adapter.setListener(this);
        list.setAdapter(adapter);
        new Handler().postDelayed(() -> adapter.updateWith(DataSource.getInstance().getData()), 2000);


        buttonAdd = v.findViewById(R.id.add_button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem();
            }
        });

        return v;
    }

    public void setListener(ListFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentListener) {
            listener = (ListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
