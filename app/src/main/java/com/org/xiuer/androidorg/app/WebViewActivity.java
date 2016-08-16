package com.org.xiuer.androidorg.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.bigdata.webshell.uiview.ZXWebChromeClient;
import com.bigdata.webshell.uiview.ZXWebClient;
import com.bigdata.webshell.uiview.ZXWebInterface;
import com.bigdata.webshell.uiview.ZXWebView;
import com.org.xiuer.androidorg.R;

//@ContentView(R.layout.activity_web_view)
public class WebViewActivity extends FragmentActivity {

  // @ViewInject(R.id.zx_webview)
    ZXWebView mWebview;

    private  String url="http://d5.chinabigdata.com/RongYi/index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebview=(ZXWebView)findViewById(R.id.zx_webview);

        mWebview.loadUrl(url);

        mWebview.setmWebClient(new ZXWebClient());

        mWebview.setWebChromeClient(new ZXWebChromeClient());

        mWebview.addJavascriptInterface(new ZXWebInterface(this),"ApplicationSelectImg");








    }
}
