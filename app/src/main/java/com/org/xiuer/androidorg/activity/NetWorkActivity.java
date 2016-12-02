package com.org.xiuer.androidorg.activity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;


import com.org.orglib.Activity.BaseActivity;
import com.org.xiuer.androidorg.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@ContentView(R.layout.activity_net_work)
public class NetWorkActivity extends BaseActivity implements View.OnClickListener{
     private  static   String  TAG="NetWorkActivity";
     private  static   String url="http://ers.guosen.com.cn:8080/pc/v/7c1d416d-c999-4f3d-a2d8-61630cdfd1e7";
    @ViewInject(R.id.down)
      Button mDownView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDownView.setOnClickListener(this);



    }

    public  void  down(){
        new  Thread(){
            @Override
            public void run() {
                RequestParams params=new RequestParams(url);
                params.setAutoRename(true);


                File filename=new File(Environment.getDataDirectory().getPath()+"/123");
                Log.i(TAG, "run: "+filename);
                String  name= URLUtil.guessFileName(url,"contentDisposition","/pdf");
                params.setSaveFilePath("/mnt/sdcard/Download/"+name);
                x.http().get(params,new myCallBack());
            }
        }.start();

    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: ");
        down();
    }

    public  class  myCallBack implements Callback.CommonCallback<String> {

        @Override
        public void onSuccess(String result) {

            byte []bytes=result.getBytes();
            try {

                File file=new File(Environment.getExternalStorageDirectory().getPath()+"/123.pdf");

                if(!file.exists()){
                    file.createNewFile();
                }
                Log.i(TAG, "onSuccess: "+file.getName());
                FileOutputStream os=new FileOutputStream(file);
                os.write(bytes);
                os.flush();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            Log.i(TAG, "onError: "+ex.getMessage()+"------------"+ex.getLocalizedMessage());
        }

        @Override
        public void onCancelled(CancelledException cex) {
            Log.i(TAG, "onCancelled: "+ cex.getMessage());
        }

        @Override
        public void onFinished() {
            Log.i(TAG, "onFinished: ");
        }
    }
}
