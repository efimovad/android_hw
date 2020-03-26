package com.technopark.firsthw;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListFragment.ListFragmentListener,
        DetailsFragment.DetailsFragmentListener {
    private ListFragment list;
    private DetailsFragment details;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ListFragment();
        list.setListener(this);

        details = new DetailsFragment();
        details.setListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, list)
                .commit();
    }

    @Override
    public void onDetailsItem(int num, int color) {
        details.setParameters(num, color);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, details)
                .commit();
    }

    @Override
    public void onBackButtonClick() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, list)
                .commit();
    }
}
