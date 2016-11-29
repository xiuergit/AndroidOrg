package com.org.xiuer.androidorg;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

public class JNIActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {



        return true;
    }
}
