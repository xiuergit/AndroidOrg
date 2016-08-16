package com.bigdata.webshell.app;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.webshell.third.lock.GestureVerifyActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";

    /**
     * webview 控件
     */
    //@ViewInject(R.id.)
    private WebView mWebView;
    //标题title
    //@ViewInject(R.id.title)
    private TextView tvTitle;

    //返回image_back;
    //@ViewInject(R.id.btn_back)
    private ImageView back_image;

    //分享
    //@ViewInject(R.id.fen_xiang)
    private ImageView share;


    /*
    东兴
     */
//    private String stringurl = "http://ty.dxzq.net/bdkm/mbl/login.jsp";
    /*
    公司OA
     */

       // private String stringUrl;
//        private String stringUrl="http://d5.chinabigdata.com/RongYi/index";
//    private String stringUrl = "https://yjs.ghzq.com.cn/bdkm/mbl/login.jsp";

    private String woQIng;

  // private String stringUrl ;


   // private String stringurl = "http://oa.chinabigdata.com/km/mbl/login.jsp";
   private String stringUrl = "http://oa.chinabigdata.com/km/mbl/login.jsp";
    //handler线程
    private Handler mHandler = new Handler();


    private int selectImgMax = 1;//选取图片最大数量

    private int photosType = 0;//图库类型


    private final static int FILECHOOSER_RESULTCODE = 1;


    private static final int REQ_CAMERA = FILECHOOSER_RESULTCODE + 1;


    private static final int REQ_CHOOSE = REQ_CAMERA + 1;


    private static final int LOCAL_IMAGE_CODE = 1;


    //图片路径
    private static final String IMAGE_TYPE = "image/*";


    private String curFormatDateStr = null;


    private String rootUrl = null;

    private RelativeLayout layout;  //标题栏

    private static final int TAKE_PHOTO = 2;

    private static String UID;

    //图片地址
    private Uri imageUri;

    private long exitTime = 0;

    private String forgetUrl;

    //本地图片地址
    private String localImg;


    //
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String macAddress = getLocalMacAddress();

//        showTost(getApplicationMetaValue("UMENG_CHANNEL"));

        showLog(macAddress);

        forgetUrl = getIntent().getStringExtra("forget");
                /*
                 加载手势密码
                  */
        if (forgetUrl == null) {
            SharedPreferences prePassWord = getSharedPreferences("passWord", MODE_PRIVATE);
            String passWord = prePassWord.getString("passWord", null);
            if (passWord != null) {
                startActivity(new Intent(MainActivity.this,
                        GestureVerifyActivity.class));
            }
        }
        /*
        推送
         */


        //stringUrl = getIntent().getStringExtra("wed");

        stringUrl = getIntent().getStringExtra("wed");

        app.addActivity(this);


//        share.setOnClickListener(this);
//        back_image.setOnClickListener(this);
        /**
         * 初次获取测试设备的Device Token
         */
        if (forgetUrl != null) {
            mWebView.loadUrl(forgetUrl);
        } else if (stringUrl == null) {
           // openDefaultUrl();
        } else {
            mWebView.loadUrl(stringUrl);
        }

       // String device_token = UmengRegistrar.getRegistrationId(getApplicationContext());

        //Log.d(TAG, "onCreate: " + device_token);

        initWebView();

    }


    long l;

    @Override
    protected void onStop() {
        super.onStop();
        l = System.currentTimeMillis();
        showLog("l=====" + l);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if ((System.currentTimeMillis() - l) >= 600 * 1000) {
            showLog("相差===" + (System.currentTimeMillis() - l));
            //解锁触发
            SharedPreferences prePassWord = getSharedPreferences("passWord", MODE_PRIVATE);
            String passWord = prePassWord.getString("passWord", null);
            if (passWord != null) {
                startActivity(new Intent(MainActivity.this, GestureVerifyActivity.class));
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_back:  //返回
//                mWebView.goBack();
//                break;
//            case R.id.fen_xiang: //分享
//
//                PackageInfo pkg = null;
//                try {
//                    pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
//                } catch (PackageManager.NameNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//                String appName = pkg.applicationInfo.loadLabel(getPackageManager()).toString();
//
//
//                new ShareAction(MainActivity.this).setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
//                        .withText(appName)  //标题
//                        .withMedia(new UMImage(MainActivity.this, myShot(MainActivity.this)))//图片
//                        .withTargetUrl(mWebView.getUrl())// 分享的地址
//                        .setCallback(new UMShareListener() { //分享完成之后的回调
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//                                Toast.makeText(MainActivity.this, share_media + "分享成功啦", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                                Toast.makeText(MainActivity.this, share_media + "分享失败啦", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//                                Toast.makeText(MainActivity.this, share_media + "分享取消了", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .open();
//                break;
        }
    }

    /*
    加载默认地址
     */

//    private void openDefaultUrl() {
//        try {
//            String url = app.getConfig().getDefaulturl();
//            String token = app.getToken();
//            if (StringUtils.isNotBlank(token)) {
//                url = app.fillToken(url);
//            }
//
//            if (StringUtils.isBlank(url)) {
//                throw new Exception("程序配置错误：url为空。");
//            }
//            mWebView.loadUrl(url);
//
//        } catch (Exception ex) {
//            try {
//                ActivityUtil.toast(this, getString(R.string.web_config_err));
//            } catch (Exception e) {
//
//            }
//        }
//    }

    private void HttpThread(final String murl, final String fileName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                try {
                    url = new URL(murl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    String cookies = CookieManager.getInstance().getCookie(murl);
                    conn.addRequestProperty("cookie", cookies);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.connect();
                    InputStream in = conn.getInputStream();
                    String path = Environment.getExternalStorageDirectory() + "/guohai/";
                    File file = new File(path);
                    file.mkdirs();
                    File outputFile = new File(file, fileName);
                    FileOutputStream fos = new FileOutputStream(outputFile);
                    //buffer 4k
                    byte[] buffer = new byte[1024 * 4];
                    int len = 0;
                    while ((len = in.read(buffer)) != -1) {
                        if (fos != null)
                            fos.write(buffer, 0, len);
                    }
                    fos.flush();
                    String pathFile = path + fileName;
                    boolean exists = new File(pathFile).exists();
                    if (exists) {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = pathFile;
                        mHandler.sendMessage(message);
                    } else {
                        return;
                    }
                    if (fos != null)
                        fos.close();
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //浏览的网 页回退而不是推出浏览器，需要在当前Activity中处理并消费掉该Back事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
               // app.exit();
                System.exit(0);
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       // UMShareAPI.get(MainActivity.this).onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LOCAL_IMAGE_CODE:  //从图库返回的图片
                    startPhotoZoom(data.getData());
                    break;
                case 3:  //裁剪返回的图片
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap bitmap = extras.getParcelable("data");
                            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
                           /*
                           从这里上传服务器
                            */
//                            String realFilePath = UrlUtils.getRealFilePath(MainActivity.this, uri);
//                            final File file = new File(realFilePath);
//                            boolean mkdirs = file.exists();
//                            Log.d(TAG, "onActivityResult: 文件名：" + file);
//                            if (mkdirs) {
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        UploadUtil.uploadFile(file, "http://app.loyalwm.com/uploadImg?uid=" + UID);
//                                        mHandler.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                mWebView.loadUrl(mWebView.getUrl());//刷新
//                                            }
//                                        });
//
//                                    }
//                                }).start();
//                            }
                            Log.d(TAG, "onActivityResult: " + mWebView.getUrl());
                        }
                    }
                    break;
                case TAKE_PHOTO://从照相机返回的图片
                    if (imageUri != null) {
                        startPhotoZoom(imageUri);
                    }
                    break;
            }

        }


    }


    /**
     * 对webView一些初始化
     */
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebView() {

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mWebView.getSettings().setSupportZoom(true);

        mWebView.getSettings().setTextZoom(100);

        /*
        支持缩放
         */
        mWebView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放

        mWebView.setWebChromeClient(new MyWebChromeClient());

        /*
        webView自适应屏幕
         */
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.requestFocus();
        mWebView.addJavascriptInterface(new WebAppInterface(this), "ApplicationSelectImg");

        //WebView对于文件下载的支持
        mWebView.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
//                SimpleDateFormat formatter    =   new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//                Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
//                String fileName =    formatter.format(curDate);
                final String fileName = URLUtil.guessFileName(url, contentDisposition, mimetype);
                final String path = Environment.getExternalStorageDirectory() + "/guohai/" + fileName;
                boolean exists = new File(path).exists();
                if (exists) {
                    //打开
                    startActivity(getPdfFileIntent(path));

                } else {
                    showTost("开始下载，请稍等");
                    HttpThread(url, fileName);
                    mHandler = new Handler() {
                        @Override
                        public void handleMessage(Message msg) {
                            switch (msg.what) {
                                case 1:
                                    showTost("下载完成");
                                    String file = (String) msg.obj;
                                    startActivity(getPdfFileIntent(file));
                                    break;
                            }
                            super.handleMessage(msg);
                        }
                    };
                }

            }
        });
        final ProgressDialog mDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        //点击链接继续在当前browser中响应，而不是新开Android的系统browser中响应该链接
        mWebView.setWebViewClient(new WebViewClient() {
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
//                tvTitle.setText(view.getTitle());
               // LoadDialog.dismiss(MainActivity.this);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //页面开始加载
                super.onPageStarted(view, url, favicon);
                //LoadDialog.show(MainActivity.this, "加载中", true);
            }
        });

        //new MobclickAgentJSInterface(getApplicationContext(), mWebView);
    }

//    private void sendSMS(String url) {
//        //发送短信息
//        String sendTo = UrlUtils.getQueryParameter(url, "sendto");
//        String body = UrlUtils.getQueryParameter(url, "body");
//        try {
//            body = URLDecoder.decode(body, "utf-8");
//        } catch (Exception ex) {
//        }
//
//        Uri uri = Uri.parse("smsto:" + sendTo);
//        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//        it.putExtra("sms_body", body);
//        startActivity(it);
//    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public final boolean checkSDcard() {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            Toast.makeText(MainActivity.this, "请插入手机存储卡再使用本功能", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    /**
     * 打开照相机或者图库的方法
     */

    protected final void selectImage() {
        if (!checkSDcard())
            return;
        String[] selectPicTypeStr = {"照相机", "本地图片"};

        new AlertDialog.Builder(MainActivity.this)
                .setItems(selectPicTypeStr,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    // 相机拍摄
                                    case 0:
                                        getImageFromCamera();
                                        break;
                                    // 手机相册
                                    case 1:
                                        getImageFromAlbum();
                                        break;
                                }
                            }
                        }).show();
    }

    /**
     * 打开照相机
     */
    /**
     * 从照相机获取图片
     */
    public void getImageFromCamera() {

        imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg"));
        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //调用相机拍照后照片保存的路径
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            Log.d(TAG, "getImageFromCamera: 照相机图片：" + imageUri);
            startActivityForResult(getImageByCamera, TAKE_PHOTO);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 直接从图库获取
     */
    public void getImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, LOCAL_IMAGE_CODE);
    }


    protected void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 120);
        intent.putExtra("outputY", 120);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    //用于JS调用的接口
    public class WebAppInterface {
        Context mContext;

        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void exit() {
            mHandler.post(new Runnable() {
                public void run() {
                    app.exit();
                }
            });
        }

        @JavascriptInterface
        public void SelectImg(String uid) {
            selectImage();
            UID = uid;
            Log.d(TAG, "SelectImg: UID=====" + UID);
        }

        @JavascriptInterface
        public String localImg() {
            mHandler.post(new Runnable() {
                public void run() {
                    str = localImg;
                }
            });
            return str;
        }

        /*
        设置手势密码
         */
        @JavascriptInterface
        public void gestureAndroid(final String num, final String uid) {

            showLog("num===" + num + ":::::::uid====" + uid);

            if (num.equals("0")) {
                //UploadUtil.uploadFile(null, "http://app.loyalwm.com/editGesturePass?uid=" + uid + "&gesturePass=" + num);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl(mWebView.getUrl());//刷新
                    }
                });
                //DataCleanManager.cleanSharedPreference(MainActivity.this);
            } else if (num.equals("1")) {
                //UploadUtil.uploadFile(null, "http://app.loyalwm.com/editGesturePass?uid=" + uid + "&gesturePass=" + num);
                //startActivity(new Intent().setClass(MainActivity.this, GestureEditActivity.class));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl(mWebView.getUrl());//刷新
                    }
                });
            }
        }

        /*
        重置密码
         */
        @JavascriptInterface
        public void ResetGestureAndroid() {
           // startActivity(new Intent().setClass(MainActivity.this, GestureEditActivity.class));
        }

        @JavascriptInterface
        public void QRCodeImgUrl(String srt) {
//            Intent intent = new Intent();
//            intent.putExtra("orCodeUrl", srt);
//            intent.setClass(MainActivity.this, OcordActivity.class);
//            startActivity(intent);
        }


        /**
         * 下载并保存图片
         */

//        @JavascriptInterface
//        public void downLoadQRCodeImg() {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    final Bitmap bitmapByUrl = HttpUtils.getBitmapFromURL("http://img4.imgtn.bdimg.com/it/u=1270641523,992229015&fm=21&gp=0.jpg");
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            saveImageToGallery(MainActivity.this, bitmapByUrl);
//                            showTost("下载保存完成");
//                        }
//                    });
//                }
//            }).start();
//
//        }

    }

    /**
     * WebView对于Alert、Confirm、Prompt的支持内部类
     */

    class MyWebChromeClient extends WebChromeClient {


        /**
         * 覆盖默认的window.alert展示界面，避免title里显示为“：来自file:////”
         */
        public boolean onJsAlert(WebView view, String url, String message,
                                 JsResult result) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("对话框")
                    .setMessage(message)
                    .setPositiveButton("确定", null);

            // 不需要绑定按键事件
            // 屏蔽keycode等于84之类的按键
            builder.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    Log.v("onJsAlert", "keyCode==" + keyCode + "event=" + event);
                    return true;
                }
            });
            // 禁止响应按back键的事件
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
            return true;
            // return super.onJsAlert(view, url, message, result);
        }

        public boolean onJsBeforeUnload(WebView view, String url,
                                        String message, JsResult result) {
            return super.onJsBeforeUnload(view, url, message, result);
        }

        /**
         * 覆盖默认的window.confirm展示界面，避免title里显示为“：来自file:////”
         */
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final JsResult result) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("对话框")
                    .setMessage(message)
                    .setPositiveButton("确定", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    })
                    .setNeutralButton("取消", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    });
            builder.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    result.cancel();
                }
            });

            // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
            builder.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                    return true;
                }
            });
            // 禁止响应按back键的事件
            // builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
            // return super.onJsConfirm(view, url, message, result);
        }

        /**
         * 覆盖默认的window.prompt展示界面，避免title里显示为“：来自file:////”
         * window.prompt('请输入您的域名地址', '618119.com');
         */
        public boolean onJsPrompt(WebView view, String url, String message,
                                  String defaultValue, final JsPromptResult result) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

            builder.setTitle("对话框").setMessage(message);

            final EditText et = new EditText(view.getContext());
            et.setSingleLine();
            et.setText(defaultValue);
            builder.setView(et)
                    .setPositiveButton("确定", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm(et.getText().toString());
                        }

                    })
                    .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    });

            // 屏蔽keycode等于84之类的按键，避免按键后导致对话框消息而页面无法再弹出对话框的问题
            builder.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    Log.v("onJsPrompt", "keyCode==" + keyCode + "event=" + event);
                    return true;
                }
            });

            // 禁止响应按back键的事件
            // builder.setCancelable(false);
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
            // return super.onJsPrompt(view, url, message, defaultValue,
            // result);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }

        @Override
        public void onRequestFocus(WebView view) {
            super.onRequestFocus(view);
        }
    }


}


