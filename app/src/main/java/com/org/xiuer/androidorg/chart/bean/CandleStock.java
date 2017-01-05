package com.org.xiuer.androidorg.chart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by xiuer on 16/12/5.
 */

public class CandleStock implements Parcelable{


    /**
     * Name : 烛形图,宏辉果蔬
     * PE : 41.790566570181355
     * code : 603336.SH
     * data : [{"date":20161202,"time":150000000,"open":23.76,"high":23.76,"low":23.76,"now":23.76,"amount":539352,"volume":22700},{"date":20161201,"time":150000000,"open":21.6,"high":21.6,"low":21.6,"now":21.6,"amount":434160,"volume":20100},{"date":20161130,"time":150000000,"open":19.64,"high":19.64,"low":19.64,"now":19.64,"amount":365304,"volume":18600},{"date":20161129,"time":150000000,"open":17.85,"high":17.85,"low":17.85,"now":17.85,"amount":132090,"volume":7400},{"date":20161128,"time":150000000,"open":16.23,"high":16.23,"low":16.23,"now":16.23,"amount":61674,"volume":3800}]
     */

    private String Name;
    private double PE;
    private String code;
    private List<DataBean> data;

    protected CandleStock(Parcel in) {
        Name = in.readString();
        PE = in.readDouble();
        code = in.readString();
    }

    public static final Creator<CandleStock> CREATOR = new Creator<CandleStock>() {
        @Override
        public CandleStock createFromParcel(Parcel in) {
            return new CandleStock(in);
        }

        @Override
        public CandleStock[] newArray(int size) {
            return new CandleStock[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPE() {
        return PE;
    }

    public void setPE(double PE) {
        this.PE = PE;
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
        parcel.writeString(Name);
        parcel.writeDouble(PE);
        parcel.writeString(code);
    }

    public static class DataBean  implements  Parcelable{
        /**
         * date : 20161202
         * time : 150000000
         * open : 23.76
         * high : 23.76
         * low : 23.76
         * now : 23.76
         * amount : 539352
         * volume : 22700
         */

        private int date;
        private int time;
        private double open;
        private double high;
        private double low;
        private double now;
        private int amount;
        private int volume;

        protected DataBean(Parcel in) {
            date = in.readInt();
            time = in.readInt();
            open = in.readDouble();
            high = in.readDouble();
            low = in.readDouble();
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

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getHigh() {
            return high;
        }

        public void setHigh(double high) {
            this.high = high;
        }

        public double getLow() {
            return low;
        }

        public void setLow(double low) {
            this.low = low;
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
            parcel.writeDouble(open);
            parcel.writeDouble(high);
            parcel.writeDouble(low);
            parcel.writeDouble(now);
            parcel.writeInt(amount);
            parcel.writeInt(volume);
        }
    }
}
