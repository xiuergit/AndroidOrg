package com.bigdata.webshell.third.lock;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.webshell.R;
import com.bigdata.webshell.app.MainActivity;
import com.bigdata.webshell.third.lock.widget.GestureContentView;
import com.bigdata.webshell.third.lock.widget.GestureDrawline;
import com.bigdata.webshell.utils.AllURL;


/**
 * 手势绘制/校验界面
 */
public class GestureVerifyActivity extends FragmentActivity implements View.OnClickListener {
    /**
     * 手机号码
     */
    public static final String PARAM_PHONE_NUMBER = "PARAM_PHONE_NUMBER";
    /**
     * 意图
     */
    public static final String PARAM_INTENT_CODE = "PARAM_INTENT_CODE";
    private RelativeLayout mTopLayout;
    private TextView mTextTitle;
    private TextView mTextCancel;

    private String passWord;
    private TextView mTextTip;
    private TextView frooget;
    private FrameLayout mGestureContainer;
    private GestureContentView mGestureContentView;
    private String mParamPhoneNumber;
    private long mExitTime = 0;
    private int mParamIntentCode;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SharedPreferences preferences = getSharedPreferences("passWord", MODE_PRIVATE);
        passWord = preferences.getString("passWord", null);

        setContentView(R.layout.activity_gesture_verify);
        ObtainExtraData();
        setUpViews();
        setUpListeners();
    }

    private void ObtainExtraData() {
        mParamPhoneNumber = getIntent().getStringExtra(PARAM_PHONE_NUMBER);
        mParamIntentCode = getIntent().getIntExtra(PARAM_INTENT_CODE, 0);
    }

    private void setUpViews() {
        mTopLayout = (RelativeLayout) findViewById(R.id.top_layout);
        mTextTitle = (TextView) findViewById(R.id.text_title);
        mTextCancel = (TextView) findViewById(R.id.text_cancel);
        frooget = (TextView) findViewById(R.id.text_forget_gesture);
        mTextTip = (TextView) findViewById(R.id.text_tip);
        mGestureContainer = (FrameLayout) findViewById(R.id.gesture_container);
        frooget.setOnClickListener(this);

        // 初始化一个显示各个点的viewGroup
        mGestureContentView = new GestureContentView(this, true, passWord,
                new GestureDrawline.GestureCallBack() {

                    @Override
                    public void onGestureCodeInput(String inputCode) {

                    }

                    @Override
                    public void checkedSuccess() {
                        mGestureContentView.clearDrawlineState(0L);
                        Toast.makeText(GestureVerifyActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
                        GestureVerifyActivity.this.finish();
                    }

                    @Override
                    public void checkedFail() {
                        mGestureContentView.clearDrawlineState(1300L);
                        mTextTip.setVisibility(View.VISIBLE);
                        mTextTip.setText(Html
                                .fromHtml("<font color='#c70c1e'>密码错误</font>"));
                        // 左右移动动画
                        Animation shakeAnimation = AnimationUtils.loadAnimation(GestureVerifyActivity.this, R.anim.shake);
                        mTextTip.startAnimation(shakeAnimation);
                    }
                });
        // 设置手势解锁显示到哪个布局里面
        mGestureContentView.setParentView(mGestureContainer);
    }

    private void setUpListeners() {
        mTextCancel.setOnClickListener(this);
    }

    private String getProtectedMobile(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 11) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(phoneNumber.subSequence(0, 3));
        builder.append("****");
        builder.append(phoneNumber.subSequence(7, 11));
        return builder.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {


            if(v.getId()==R.id.text_cancel){
                this.finish();
            }else  if(v.getId()==R.id.text_forget_gesture){

                 Intent intent=new Intent();
               intent.putExtra("forget", AllURL.FORGET);
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                this.finish();
            }

    //    switch (v.getId()) {
//            case R.id.text_cancel:
//                this.finish();
//                break;
//            case R.id.text_forget_gesture:
//                Intent intent=new Intent();
//                //intent.putExtra("forget", AllURL.FORGET);
//                //intent.setClass(this, MainActivity.class);
//                startActivity(intent);
//                this.finish();
//                break;
//            default:
//                break;
        }
   // }

}