package com.bigdata.webshell.uiview;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;

/**
 * Created by xiuer on 16/8/15.
 */
public class ZXWebView extends WebView {

    private String mJsmessage;//javascriptInterface 名称例如"ApplicationSelectImg"

    private ZXWebChromeClient mWebChromeClient;

    private ZXWebClient mWebClient;

    private  ZXWebInterface mWebInterface;//

    private  Context mContext;



    public String getmJsmessage() {
        return mJsmessage;
    }

    public void setmJsmessage(String mJsmessage) {
        this.mJsmessage = mJsmessage;
    }

    public ZXWebChromeClient getmWebChromeClient() {
        return mWebChromeClient;
    }

    public void setmWebChromeClient(ZXWebChromeClient mWebChromeClient) {
        this.mWebChromeClient = mWebChromeClient;
        this.setWebChromeClient(mWebChromeClient);
    }

    public ZXWebClient getmWebClient() {
        return mWebClient;
    }

    public void setmWebClient(ZXWebClient mWebClient) {
        this.mWebClient = mWebClient;
        this.setWebViewClient(mWebClient);

    }

    public ZXWebView(Context context) {

        super(context);
        this.mContext=context;
        initWebView();
    }

    public ZXWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        initWebView();
        //this.addJavascriptInterface(mWebInterface, mJsmessage);
    }

    public ZXWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initWebView();

    }
    


    /**
     * 对webView一些初始化
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebView() {

        this.getSettings().setJavaScriptEnabled(true);

        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        this.getSettings().setSupportZoom(true);

        this.getSettings().setTextZoom(100);

        /*
        支持缩放
         */
        this.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放

         this.setWebChromeClient(mWebChromeClient);

        /*
        webView自适应屏幕
         */
        this.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.getSettings().setLoadWithOverviewMode(true);

        this.requestFocus();


        //WebView对于文件下载的支持
        this.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//
                final String fileName = URLUtil.guessFileName(url, contentDisposition, mimetype);
                final String path = Environment.getExternalStorageDirectory() + "/guohai/" + fileName;
                boolean exists = new File(path).exists();
                if (exists) {
                    //打开
                 //   mContext.startActivity(getPdfFileIntent(path));

                } else {
//                    showTost("开始下载，请稍等");
//                    HttpThread(url, fileName);
//                    mHandler = new Handler() {
//                        @Override
//                        public void handleMessage(Message msg) {
//                            switch (msg.what) {
//                                case 1:
//                                    showTost("下载完成");
//                                    String file = (String) msg.obj;
//                                    startActivity(getPdfFileIntent(file));
//                                    break;
//                            }
//                            super.handleMessage(msg);
//                        }
//                    };
                }

            }
        });
        final ProgressDialog mDialog = new ProgressDialog(
                mContext, ProgressDialog.STYLE_SPINNER);
        //点击链接继续在当前browser中响应，而不是新开Android的系统browser中响应该链接
        //this.setWebViewClient(mWebClient);

        //this.setWebChromeClient(mWebChromeClient);


    }

}
