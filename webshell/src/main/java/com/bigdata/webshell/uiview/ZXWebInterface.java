package com.bigdata.webshell.uiview;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by xiuer on 16/8/16.
 */
public class ZXWebInterface  {
    private  static  String  TAG="ZXWebInterface ";
    Context mContext;

    Handler mHandler;


    public  ZXWebInterface(Context c) {
        mContext = c;
    }
   public  ZXWebInterface(Context c,Handler mHandler) {
        mContext = c;
        mHandler=mHandler;
    }

//    @JavascriptInterface
//    public void exit() {
//        mHandler.post(new Runnable() {
//            public void run() {
//               // app.exit();
//            }
//        });
//    }

//    @JavascriptInterface
//    public void SelectImg(String uid) {
//        selectImage();
//        UID = uid;
//        Log.d(TAG, "SelectImg: UID=====" + UID);
//    }

//    @JavascriptInterface
//    public String localImg() {
//        mHandler.post(new Runnable() {
//            public void run() {
//                str = localImg;
//            }
//        });
//        return str;
//    }

    /*
    设置手势密码
     */
    @JavascriptInterface
    public void gestureAndroid(final String num, final String uid) {
        Log.i(TAG, "gestureAndroid: num===\" + num + \":::::::uid====\" + uid");

//        //showLog("num===" + num + ":::::::uid====" + uid);
//        Log.i(TAG, "gestureAndroid: num===\" + num + \":::::::uid====\" + uid");
//
//        if (num.equals("0")) {
//            UploadUtil.uploadFile(null, "http://app.loyalwm.com/editGesturePass?uid=" + uid + "&gesturePass=" + num);
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mWebView.loadUrl(mWebView.getUrl());//刷新
//                }
//            });
//            DataCleanManager.cleanSharedPreference(MainActivity.this);
//        } else if (num.equals("1")) {
//            UploadUtil.uploadFile(null, "http://app.loyalwm.com/editGesturePass?uid=" + uid + "&gesturePass=" + num);
//            startActivity(new Intent().setClass(MainActivity.this, GestureEditActivity.class));
//            mHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    mWebView.loadUrl(mWebView.getUrl());//刷新
//                }
//            });
//        }
    }

//    /*
//    重置密码
//     */
//    @JavascriptInterface
//    public void ResetGestureAndroid() {
//        startActivity(new Intent().setClass(MainActivity.this, GestureEditActivity.class));
//    }
//
//    @JavascriptInterface
//    public void QRCodeImgUrl(String srt) {
//        Intent intent = new Intent();
//        intent.putExtra("orCodeUrl", srt);
//        intent.setClass(MainActivity.this, OcordActivity.class);
//        startActivity(intent);
//    }

}
