package com.org.xiuer.androidorg.activity;

import android.os.Bundle;
import android.view.View;

import com.bigdata.webshell.uiview.ZXWebChromeClient;
import com.bigdata.webshell.uiview.ZXWebClient;
import com.bigdata.webshell.uiview.ZXWebInterface;
import com.bigdata.webshell.uiview.ZXWebView;
import com.org.orglib.Activity.BaseActivity;
import com.org.xiuer.androidorg.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_web_view)
    public  class WebViewActivity extends BaseActivity {

       @ViewInject(R.id.zx_webview)
        ZXWebView mWebview;
        private  String url="http://d5.chinabigdata.com/RongYi/index";
         private  String url1="http://www.open-open.com";

    private String androidUrl = "http://www.jdzhao.com/userinterface/show_202_125.html";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


           // mWebview.loadUrl("fil.e:///android_asset/moban/index.html");
            mWebview.loadUrl(androidUrl);
            mWebview.setmWebClient(new ZXWebClient());
            mWebview.setWebChromeClient(new ZXWebChromeClient());
            mWebview.addJavascriptInterface(new ZXWebInterface(this),
                "AndroidMessage");
        }

        public void  opencv(View view){

            mWebview.loadUrl("file:///android_asset/h5study/mycv.html");

        }
         public void  study(View view){

             mWebview.loadUrl("file:///android_asset/androidhandbook/uistudy.html");

         }
          public void studyform(View view){

           mWebview.loadUrl("file:///android_asset/h5study/htmlform.html");

    }


    }