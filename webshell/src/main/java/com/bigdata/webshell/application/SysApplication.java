package com.bigdata.webshell.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.bigdata.webshell.utils.InitConfig;
import com.bigdata.webshell.utils.StringUtils;

import java.util.LinkedList;
import java.util.List;



/**
 * 全局Application
 */
public class SysApplication extends Application {

    private final String TAG = "SysApplication";


    private List<Activity> mList = new LinkedList<Activity>();


    /**
     * 友盟推送
     */
    //private PushAgent mPushAgent;

//
//    {
//        /**
//         * 各个平台的配置
//         */
//        //微信 appid appsecret
////        PlatformConfig.setWeixin("wx34e48a6ae1d5e92c", "3c437ebee3eba45e1ead56c75818de14");
////        //新浪微博 appkey appsecret
////        PlatformConfig.setSinaWeibo("1697497357", "91f9c35e7fd2dd0c0289a39eb2805403");
////        // QQ和Qzone appid appkey
////        PlatformConfig.setQQZone("1105239491", "kxrEEoysuPaFjZPU");
//
//    }
//
//
    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: APP创建");

    }

//        x.Ext.init(this);
//
//        x.Ext.init(this);}
//
//        /**
//         * 开启友盟推送
//         */
//        mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setDebugMode(false);
//
//        mPushAgent = PushAgent.getInstance(getApplicationContext());
//        mPushAgent.enable();
//        PushAgent.getInstance(getApplicationContext()).onAppStart();
//
//
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//
//
//        /**
//         * 对自己定义消息处理
//         */
//        UmengMessageHandler messageHandler = new UmengMessageHandler() {
//            /**
//             * 参考集成文档的1.6.3
//             * http://dev.umeng.com/push/android/integration#1_6_3
//             */
//            @Override
//            public void dealWithCustomMessage(final Context context, final UMessage msg) {
//                new Handler().post(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        // 对自定义消息的处理方式，点击或者忽略
//                        boolean isClickOrDismissed = true;
//                        if (isClickOrDismissed) {
//                            //自定义消息的点击统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//                        } else {
//                            //自定义消息的忽略统计
//                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//                        }
//                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//
//
//        };
//        mPushAgent.setMessageHandler(messageHandler);
//
//        /**
//         * 对自定义打开通知的处理方式
//         */
//        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
//
//            @Override
//            public void dealWithCustomAction(Context context, UMessage uMessage) {
//                String custom = uMessage.custom;
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("wed", custom);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//
//        };
//        mPushAgent.setNotificationClickHandler(notificationClickHandler);
//
//
//    }
//
    public InitConfig getConfig() {
        return config;
    }

    public void setConfig(InitConfig config) {
        this.config = config;
    }

    private InitConfig config;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public String fillToken(String url) {
        if (StringUtils.isBlank(url))
            return "";

        return url.replace("{0}", getToken());
    }
//
    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }


}
