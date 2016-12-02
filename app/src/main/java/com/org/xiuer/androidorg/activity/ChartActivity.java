package com.org.xiuer.androidorg.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

//import com.bigdata.marketsdk.activity.MarketStockActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.android.gms.auth.firstparty.shared.FACLConfig;
import com.org.orglib.Activity.BaseActivity;
import com.org.xiuer.androidorg.R;
import com.org.xiuer.androidorg.chart.bean.Stock;
import com.org.orglib.FileUtils.AssetsManger;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_chart)
public  class ChartActivity extends BaseActivity implements View.OnClickListener{

    private  static  String TAG="AndroidOrg";

//    AndroidOrg@ViewInject(R.id.chart_line)
//    private LineChart mLineChart;

    /**
     * frameLayout 容器
     */
    @ViewInject(R.id.fl_container)
    private  FrameLayout mContainer;

    /**
     * button 菜单
     */
    @ViewInject(R.id.bt_bar)
    private Button mBarChartButton;
    @ViewInject(R.id.bt_line)
    private Button mLineChartButton;
    @ViewInject(R.id.bt_candleStick)
    private Button mCandleStickChartButton;
    @ViewInject(R.id.bt_combined)
    private Button mCombinedChartButton;


    private BarLineChartBase mChart;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

           initUI();
        }

    public void  initUI(){
        mBarChartButton.setOnClickListener(this);
        mLineChartButton.setOnClickListener(this);
        mCandleStickChartButton.setOnClickListener(this);
        mCombinedChartButton.setOnClickListener(this);
    }

   public BarData getBarData(){

     // 股票数据
       Stock mStock= AssetsManger.readAssertToObject("json/fenshidata.json", Stock.class,this);

       ArrayList<BarEntry>barEntries=new ArrayList<>();
       for (Stock.DataBean  dataBean : mStock.getData()){
           BarEntry barEntry=new BarEntry((float) dataBean.getDate(),(float) dataBean.getNow());
           barEntries.add(barEntry);
       }

       BarDataSet barDataSet=new BarDataSet(barEntries,mStock.getName());

       barDataSet.setDrawValues(true);
       barDataSet.setBarBorderWidth(13);
       ArrayList<IBarDataSet>dataSets=new ArrayList<>();
       dataSets.add(barDataSet);

       BarData barData=new BarData(dataSets );
       barData.setBarWidth(12);
       barData.setDrawValues(true);
    //   barData.addDataSet(barDataSet);
       //barData.addDataSet(barDataSet);
       return  barData;

   }


    /**
     *
     * @return 折线统计图的数据
     */
    public  LineData getLineData( ){
        // 股票数据
        Stock mStock= AssetsManger.readAssertToObject("json/fenshidata.json", Stock.class,this);
        ArrayList<Entry>mEntry=new ArrayList<>();
        for(int i=0;i<mStock.getData().size();i++){
            Stock.DataBean dataBean=mStock.getData().get(i);
            Entry entry=new Entry(i, (float) dataBean.getNow());
            mEntry.add(i,entry);
        }
        LineDataSet mLineDataSet=new LineDataSet(mEntry,mStock.getName());
        mLineDataSet.setLineWidth(1);
        mLineDataSet.setDrawCircleHole(false);
        mLineDataSet.setDrawCircles(false);
        mLineDataSet.setDrawFilled(false);
        mLineDataSet.setDrawValues(true);
        mLineDataSet.setDrawHighlightIndicators(false);

        LineData mLineData=new LineData();
        mLineData.addDataSet(mLineDataSet);
        return  mLineData;

    }


    /**
     *
     * @param lineChartBase   barchart、linechart、candlechart。。父类
     * @param data     线性表的 数据
     */
    public void showChart(BarLineChartBase lineChartBase, BarLineScatterCandleBubbleData data){
        mContainer.removeAllViews();
        mContainer.addView(lineChartBase);

        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lineChartBase.setLayoutParams(params);

        lineChartBase.setData(data);
        lineChartBase.setDoubleTapToZoomEnabled(false);
        lineChartBase.setBackgroundColor(Color.argb(12,12,22,22));
       //横坐标
        XAxis xAxis= lineChartBase.getXAxis();
//        xAxis.setDrawAxisLine(false);
//        xAxis.setCenterAxisLabels(false);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

//        xAxis.setDrawAxisLine(false);
//        xAxis.setDrawGridLines(false);
        //xAxis.setDrawLabels(false);
        //左纵坐标
        YAxis yAxisL=lineChartBase.getAxisLeft();
//        yAxisL.setDrawAxisLine(false);
//        yAxisL.setDrawGridLines(false);
        //右纵坐标
        YAxis yAxisR=lineChartBase.getAxisRight();
//        yAxisR.setDrawGridLines(false);


        lineChartBase.setGridBackgroundColor(Color.argb(12,123,12,33));
        lineChartBase.setDrawGridBackground(true);




        lineChartBase.animateX(2500);




    }

    @Override
    public void onClick(View view) {
           String tag=(String) view.getTag();
           if(tag.equals( getResources().getString(R.string.button_tag_barChart))){
               Log.i(TAG, "onClick: "+tag);
               BarChart mChart=new BarChart(this);
               mChart.setDrawValueAboveBar(true);
               mChart.setData(getBarData());
               mContainer.removeAllViews();
               mContainer.addView(mChart);
               FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                       ViewGroup.LayoutParams.MATCH_PARENT);
               mChart.setLayoutParams(params);
               mChart.setDrawValueAboveBar(true);
               mChart.setDrawBorders(true);
               mChart.setDrawBarShadow(true);
               mChart.setMinimumWidth(12);

               XAxis xAxis=mChart.getXAxis();
               xAxis.setDrawAxisLine(true);

               xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
               xAxis.setLabelRotationAngle(30);
               xAxis.setLabelCount(6);
               AxisValueFormatter axisValueFormatter=new AxisValueFormatter() {
                  String [] test=new String[]{
                    "0","1","2","3","5"   ,"6"
                  };

                   @Override
                   public String getFormattedValue(float value, AxisBase axis) {
                       Log.i(TAG, "getFormattedValue: "+value);
                       return  "123";
                   }

                   @Override
                   public int getDecimalDigits() {
                       return 0;
                   }
               };

               xAxis.setValueFormatter(axisValueFormatter);
               //showChart(mChart,getBarData());
           }
            else if(tag.equals( getResources().getString(R.string.button_tag_candleStickChart))){
               Log.i(TAG, "onClick: "+tag);
           }else if(tag.equals( getResources().getString(R.string.button_tag_combinedChart))){
               Log.i(TAG, "onClick: "+tag);
           }else if (tag.equals( getResources().getString(R.string.button_tag_lineChart))){

                mChart=new LineChart(this);
               showChart(mChart,getLineData());

           }


    }
}