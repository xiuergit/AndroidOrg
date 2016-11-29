package com.bigdata.webshell.uiview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.ClientCertRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by xiuer on 16/8/16.
 */
public class ZXWebClient  extends WebViewClient{
    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        super.onReceivedClientCertRequest(view, request);
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.indexOf("sms:") >= 0) {
            //  sendSMS(url);
        } else {
            view.loadUrl(url);
        }
        return true;
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();//接受证书
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        //页面开始加载
        super.onPageStarted(view, url, favicon);
        //LoadDialog.show(MainActivity.this, "加载中", true);
    }
}
