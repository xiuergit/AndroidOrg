package com.org.xiuer.androidorg.chart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zhangxiu on 2016/12/1.
 */

public class Stock implements Parcelable{


    /**
     * name : 金科股份
     * code : 000656.SZ
     * data : [{"date":20161201,"time":91554000,"now":5.9000000953674325,"amount":0,"volume":0},{"date":20161201,"time":91657000,"now":5.9000000953674325,"amount":0,"volume":0},{"date":20161201,"time":91751000,"now":5.9000000953674325,"amount":0,"volume":0},{"date":20161201,"time":91854000,"now":5.9000000953674325,"amount":0,"volume":0},{"date":20161201,"time":91957000,"now":5.9000000953674325,"amount":0,"volume":0},{"date":20161201,"time":92054000,"now":5.9000000953674325,"amount":0,"volume":0},{"date":20161201,"time":92157000,"now":5.889999866485597,"amount":0,"volume":0},{"date":20161201,"time":92251000,"now":5.880000114440919,"amount":0,"volume":0},{"date":20161201,"time":92354000,"now":5.800000190734863,"amount":0,"volume":0},{"date":20161201,"time":92457000,"now":5.489999771118164,"amount":0,"volume":0},{"date":20161201,"time":92504000,"now":5.489999771118164,"amount":59537952,"volume":10844800},{"date":20161201,"time":93057000,"now":5.489999771118164,"amount":198193904,"volume":36103100},{"date":20161201,"time":93157000,"now":5.4600000381469735,"amount":251930336,"volume":45938300},{"date":20161201,"time":93251000,"now":5.559999942779541,"amount":297247776,"volume":54154800},{"date":20161201,"time":93357000,"now":5.510000228881837,"amount":376914336,"volume":68583200},{"date":20161201,"time":93455000,"now":5.579999923706056,"amount":449978880,"volume":81754300},{"date":20161201,"time":93557000,"now":5.579999923706056,"amount":539753344,"volume":97762100},{"date":20161201,"time":93657000,"now":5.630000114440919,"amount":585070080,"volume":105850300},{"date":20161201,"time":93748000,"now":5.570000171661378,"amount":634237440,"volume":114611800},{"date":20161201,"time":93854000,"now":5.610000133514404,"amount":660977408,"volume":119411200},{"date":20161201,"time":93958000,"now":5.570000171661378,"amount":698400576,"volume":126089900},{"date":20161201,"time":94057000,"now":5.5900001525878915,"amount":718045504,"volume":129608600},{"date":20161201,"time":94157000,"now":5.619999885559082,"amount":752065664,"volume":135687700},{"date":20161201,"time":94251000,"now":5.619999885559082,"amount":776722944,"volume":140073300},{"date":20161201,"time":94358000,"now":5.5900001525878915,"amount":801795328,"volume":144547600},{"date":20161201,"time":94448000,"now":5.579999923706056,"amount":813002176,"volume":146556000},{"date":20161201,"time":94554000,"now":5.599999904632568,"amount":824224384,"volume":148565100},{"date":20161201,"time":94656000,"now":5.5900001525878915,"amount":842258624,"volume":151794500},{"date":20161201,"time":94758000,"now":5.579999923706056,"amount":853333632,"volume":153778800},{"date":20161201,"time":94857000,"now":5.570000171661378,"amount":867169664,"volume":156263300},{"date":20161201,"time":94954000,"now":5.539999961853027,"amount":887179648,"volume":159866000},{"date":20161201,"time":95057000,"now":5.489999771118164,"amount":917894976,"volume":165448600},{"date":20161201,"time":95157000,"now":5.510000228881837,"amount":936405760,"volume":168821400},{"date":20161201,"time":95257000,"now":5.53000020980835,"amount":955334592,"volume":172244400},{"date":20161201,"time":95357000,"now":5.539999961853027,"amount":969456320,"volume":174795100},{"date":20161201,"time":95457000,"now":5.519999980926515,"amount":988666624,"volume":178279100},{"date":20161201,"time":95557000,"now":5.53000020980835,"amount":997601536,"volume":179898700},{"date":20161201,"time":95657000,"now":5.539999961853027,"amount":1008484608,"volume":181866600},{"date":20161201,"time":95757000,"now":5.539999961853027,"amount":1021980864,"volume":184305000},{"date":20161201,"time":95857000,"now":5.539999961853027,"amount":1029199872,"volume":185609000},{"date":20161201,"time":95954000,"now":5.570000171661378,"amount":1039297664,"volume":187428000},{"date":20161201,"time":100057000,"now":5.579999923706056,"amount":1055741184,"volume":190376600},{"date":20161201,"time":100157000,"now":5.579999923706056,"amount":1068414720,"volume":192645900},{"date":20161201,"time":100257000,"now":5.570000171661378,"amount":1079565824,"volume":194648600},{"date":20161201,"time":100357000,"now":5.570000171661378,"amount":1088601088,"volume":196269300},{"date":20161201,"time":100457000,"now":5.570000171661378,"amount":1097781760,"volume":197916500},{"date":20161201,"time":100557000,"now":5.559999942779541,"amount":1107921664,"volume":199738300},{"date":20161201,"time":100659000,"now":5.559999942779541,"amount":1115140608,"volume":201036500},{"date":20161201,"time":100757000,"now":5.559999942779541,"amount":1122499200,"volume":202360100},{"date":20161201,"time":100854000,"now":5.570000171661378,"amount":1133783808,"volume":204387700},{"date":20161201,"time":100954000,"now":5.559999942779541,"amount":1142291072,"volume":205916900},{"date":20161201,"time":101057000,"now":5.550000190734863,"amount":1153554304,"volume":207945300},{"date":20161201,"time":101157000,"now":5.53000020980835,"amount":1159389184,"volume":208998600},{"date":20161201,"time":101257000,"now":5.519999980926515,"amount":1164968960,"volume":210008400},{"date":20161201,"time":101354000,"now":5.53000020980835,"amount":1174820352,"volume":211794200},{"date":20161201,"time":101457000,"now":5.519999980926515,"amount":1180711552,"volume":212861200},{"date":20161201,"time":101558000,"now":5.519999980926515,"amount":1185025536,"volume":213643300},{"date":20161201,"time":101657000,"now":5.519999980926515,"amount":1190488064,"volume":214632200},{"date":20161201,"time":101757000,"now":5.519999980926515,"amount":1195742720,"volume":215584200},{"date":20161201,"time":101854000,"now":5.519999980926515,"amount":1200437504,"volume":216434500},{"date":20161201,"time":101954000,"now":5.519999980926515,"amount":1204316928,"volume":217136800},{"date":20161201,"time":102057000,"now":5.53000020980835,"amount":1208770432,"volume":217942500},{"date":20161201,"time":102157000,"now":5.53000020980835,"amount":1212922624,"volume":218693700},{"date":20161201,"time":102257000,"now":5.53000020980835,"amount":1216410112,"volume":219324500},{"date":20161201,"time":102358000,"now":5.539999961853027,"amount":1219444352,"volume":219872800},{"date":20161201,"time":102457000,"now":5.539999961853027,"amount":1222699904,"volume":220460700},{"date":20161201,"time":102557000,"now":5.53000020980835,"amount":1226026496,"volume":221061900},{"date":20161201,"time":102657000,"now":5.519999980926515,"amount":1228256256,"volume":221465300},{"date":20161201,"time":102757000,"now":5.519999980926515,"amount":1231141632,"volume":221987300},{"date":20161201,"time":102857000,"now":5.53000020980835,"amount":1233504256,"volume":222414700},{"date":20161201,"time":102957000,"now":5.53000020980835,"amount":1236352000,"volume":222929900},{"date":20161201,"time":103057000,"now":5.46999979019165,"amount":1267524352,"volume":228596500},{"date":20161201,"time":103157000,"now":5.480000019073486,"amount":1273049856,"volume":229606100},{"date":20161201,"time":103221000,"now":5.489999771118164,"amount":1274978304,"volume":229957400}]
     */

    private String name;
    private String code;
    private List<DataBean> data;

    protected Stock(Parcel in) {
        name = in.readString();
        code = in.readString();
    }

    public static final Creator<Stock> CREATOR = new Creator<Stock>() {
        @Override
        public Stock createFromParcel(Parcel in) {
            return new Stock(in);
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(code);
    }

    public static class DataBean implements Parcelable {
        /**
         * date : 20161201
         * time : 91554000
         * now : 5.9000000953674325
         * amount : 0
         * volume : 0
         */

        private int date;
        private int time;
        private double now;
        private int amount;
        private int volume;

        protected DataBean(Parcel in) {
            date = in.readInt();
            time = in.readInt();
            now = in.readDouble();
            amount = in.readInt();
            volume = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public int getDate() {
            return date;
        }

        public void setDate(int date) {
            this.date = date;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public double getNow() {
            return now;
        }

        public void setNow(double now) {
            this.now = now;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(date);
            parcel.writeInt(time);
            parcel.writeDouble(now);
            parcel.writeInt(amount);
            parcel.writeInt(volume);
        }
    }
}
