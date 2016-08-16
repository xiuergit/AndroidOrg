package com.org.xiuer.androidorg.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.org.xiuer.androidorg.R;

public class ChartActivity extends AppCompatActivity {

    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        mChart=new LineChart(this);
       // mChart.setData();



    }
}
