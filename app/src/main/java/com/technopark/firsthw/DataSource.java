package com.technopark.firsthw;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {

    private final List<MyData> mData;
    private static DataSource sInstance;

    public DataSource() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add(new MyData(i+1));
        }
    }

    public List<MyData> getData() {
        return mData;
    }

    public void updateData() {
        mData.add(new MyData(mData.size() + 1));
    }

    public synchronized static DataSource getInstance() {
        if (sInstance == null) {
            sInstance = new DataSource();
        }
        return sInstance;
    }

    public static class MyData {

        public MyData(int num) {
            mNum = num;
        }
        int mNum;
    }
}
