package com.org.xiuer.androidorg.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.AxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.org.orglib.Activity.BaseActivity;
import com.org.orglib.FileUtils.AssetsManger;
import com.org.xiuer.androidorg.R;
import com.org.xiuer.androidorg.chart.bean.CandleStock;
import com.org.xiuer.androidorg.chart.bean.Stock;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

//import com.bigdata.marketsdk.activity.MarketStockActivity;

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

    /**
     * 初始化ui
     */
    public void  initUI(){
        mBarChartButton.setOnClickListener(this);
        mLineChartButton.setOnClickListener(this);
        mCandleStickChartButton.setOnClickListener(this);
        mCombinedChartButton.setOnClickListener(this);
    }


    /**
     * 返回组合图 数据
     */
    public CombinedData getCombinedData() {

        CombinedData data = new CombinedData();
        data.setData(getCandleData());
        data.setData(getLineData());
        data.setData(getBarData());


        return data;


    }


    /**
     * 返回烛形图 数据
     *
     * @return
     */
    public CandleData getCandleData() {
        // 股票数据
        CandleStock mStock = AssetsManger.
                readAssertToObject("json/marketcandle.json", CandleStock.class, this);
        ArrayList<CandleEntry> candleEntrys = new ArrayList<>();
        for (int i = 0; i < mStock.getData().size(); i++) {

            CandleStock.DataBean databean = mStock.getData().get(i);

            CandleEntry entry = new CandleEntry(i,
                    (float) databean.getHigh(),
                    (float) databean.getLow(),
                    (float) databean.getOpen(),
                    (float) databean.getNow());
            candleEntrys.add(entry);

        }

        ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();

        for (int i = 0; i < 20; i++) {
            //float mult = (mSeekBarY.get + 1);
            float val = (float) (Math.random() * 40) + 20;

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

            yVals1.add(new CandleEntry(i, val + high, val - low, even ? val + open : val - open,
                    even ? val - close : val + close));
        }


        CandleDataSet candledataset = new CandleDataSet(yVals1, mStock.getName());

        candledataset.setAxisDependency(YAxis.AxisDependency.LEFT);

        candledataset.setBarSpace(1f);
        candledataset.setDecreasingColor(Color.BLUE);
        candledataset.setShadowWidth(12f);
        candledataset.setShowCandleBar(true);

        candledataset.setAxisDependency(YAxis.AxisDependency.LEFT);
        //candledataset.setShadowColor(Color.BLACK);
        candledataset.setShadowColorSameAsCandle(true);
        candledataset.setShadowWidth(0.7f);
        candledataset.setDecreasingColor(Color.GREEN);
        candledataset.setDecreasingPaintStyle(Paint.Style.FILL);
        candledataset.setIncreasingColor(Color.RED);
        candledataset.setIncreasingPaintStyle(Paint.Style.FILL);


        candledataset.setColors(ColorTemplate.LIBERTY_COLORS);


        CandleData candleData = new CandleData();
        candleData.addDataSet(candledataset);

        return candleData;


    }

    /**
     * 返回柱形统计图的数据
     *
     * @return
     */
   public BarData getBarData(){

     // 股票数据
       Stock mStock= AssetsManger.readAssertToObject("json/fenshidata.json", Stock.class,this);

       ArrayList<BarEntry>barEntries=new ArrayList<>();

       for (int i = 0; i < mStock.getData().size()
               ; i++) {

           // int margin=20;
           Stock.DataBean dataBean = mStock.getData().get(i);
           BarEntry barEntry =
                   new BarEntry((float) i + 1f,
                           (float) dataBean.getNow());
           barEntries.add(barEntry);

       }


       BarDataSet barDataSet = new BarDataSet
               (barEntries, mStock.getName());

       barDataSet.setDrawValues(true);
       barDataSet.setBarBorderWidth(0.9f);

       //barDataSet.setBarBorderColor(ColorTemplate.MATERIAL_COLORS);


       barDataSet.setColors(ColorTemplate.COLORFUL_COLORS
       );
       ArrayList<IBarDataSet>dataSets=new ArrayList<>();
       dataSets.add(barDataSet);
       BarData barData=new BarData(dataSets);
       barData.setBarWidth(0.9f);

       barData.setDrawValues(true);

       //设置值的格式
       barData.setValueFormatter(new ValueFormatter() {
           @Override
           public String getFormattedValue(float value,
                                           Entry entry, int dataSetIndex,
                                           ViewPortHandler viewPortHandler) {
               Log.i(TAG, "getFormattedValue: " + value);
               return entry.getY() + "test";
           }
       });
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

        mLineDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
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
        xAxis.setDrawAxisLine(false);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setValueFormatter(new AxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return value + "";
            }

            @Override
            public int getDecimalDigits() {
                return 0;
            }
        });

        xAxis.setDrawAxisLine(true);

        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);
        //左纵坐标
        YAxis yAxisL=lineChartBase.getAxisLeft();
        yAxisL.setDrawAxisLine(false);
        yAxisL.setDrawGridLines(false);
        //右纵坐标
        YAxis yAxisR=lineChartBase.getAxisRight();
        yAxisR.setDrawGridLines(false);



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
               showChart(mChart, getBarData());
           }
            else if(tag.equals( getResources().getString(R.string.button_tag_candleStickChart))){
               Log.i(TAG, "onClick: " + tag);
               CandleStickChart mChart = new CandleStickChart(this);
               showChart(mChart, getCandleData());
           }else if(tag.equals( getResources().getString(R.string.button_tag_combinedChart))){
               Log.i(TAG, "onClick: " + tag);
               CombinedChart mChart = new CombinedChart(this);
               showChart(mChart, getCombinedData());

           }else if (tag.equals( getResources().getString(R.string.button_tag_lineChart))){

                mChart=new LineChart(this);
               showChart(mChart,getLineData());

           }


    }




}