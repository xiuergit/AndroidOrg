package com.org.xiuer.androidorg.app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.org.xiuer.androidorg.R;


public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    String  TAG="MainActivity";



    //String url = "http://d5.chinabigdata.com/RongYi/index";

  // String url="file:///android_asset/test.html";
   // String url="http://pasri.pa18.com/bdkm/j_acegi_security_check?j_username={0}&j_password={1}&officePlugin=1";
    String  url="file:///android_asset/login/login.html";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    /**
     *  WEBVIEW 调用js
     * @param view
     */
    public void showjs(View view){
        mWebView.loadUrl("javascript:show()");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);




        init();

        initWebview();

        mWebView.loadUrl(url);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * 对webView一些初始化
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    public void initWebview() {

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mWebView.getSettings().setSupportZoom(true);

        mWebView.getSettings().setTextZoom(100);

        /*
        支持缩放
         */
        mWebView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放

        //mWebView.setWebChromeClient(new MyWebChromeClient());

        /*
        webView自适应屏幕
         */
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.requestFocus();

        mWebView.addJavascriptInterface(new WebAppInterface(this), "demo");
        mWebView.addJavascriptInterface(new WebAppInterface(this),"demo2");

        //WebView对于文件下载的支持
        mWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {


                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        final ProgressDialog mDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        //点击链接继续在当前browser中响应，而不是新开Android的系统browser中响应该链接
        mWebView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.indexOf("sms:") >= 0) {
                    //sendSMS(url);


                } else {
                    view.loadUrl(url);

                }


                Log.d("tag", url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {


                //handler.proceed();//接受证书


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Log.d("pageFinished", "ee");

//                tvTitle.setText(view.getTitle());
                //LoadDialog.dismiss(MainActivity.this);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始加载
                super.onPageStarted(view, url, favicon);

                Log.d("pageFinished", "ee");
                //LoadDialog.show(MainActivity.this, "加载中", true);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {


                return true;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

              Log.d(TAG,message);

                return true;
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.org.xiuer.androidorg/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.org.xiuer.androidorg/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public class WebAppInterface {
          private String TAG="WebAppInterface";

        Context context;
        public WebAppInterface() {

        }
        public WebAppInterface(Context context){

            this.context=context;
        }


        public void ApplicationSelectImg()

        {

            Log.d(TAG
                    , "===========");
        }

        @JavascriptInterface
        public void show(){
            Log.d(TAG
                    , "JS 调用 android");
        }

    }


    public void init() {
        mWebView = (WebView) findViewById(R.id.webview);
    }


}
