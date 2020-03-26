package com.technopark.firsthw;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {
    private DetailsFragmentListener listener;

    private int num = 0;
    private int color = 0;
    private Button buttonBack;

    public interface DetailsFragmentListener {
        void onBackButtonClick();
    }

    public void setParameters(int num, int color) {
        this.num = num;
        this.color = color;
    }

    public void setListener(DetailsFragmentListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_details, null);
        TextView textView = v.findViewById(R.id.title_details);
        textView.setText(Integer.toString(num));
        textView.setTextColor(color);

        buttonBack = v.findViewById(R.id.add_button);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBackButtonClick();
            }
        });

        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
