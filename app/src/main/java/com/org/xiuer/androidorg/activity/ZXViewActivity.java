package com.org.xiuer.androidorg.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.org.orglib.Activity.BaseActivity;
import com.org.xiuer.androidorg.R;
import com.org.xiuer.androidorg.view.ZXView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_zxview)
public class ZXViewActivity extends BaseActivity {

    private  String TAG="ZXViewActivity";
    @ViewInject(R.id.myView)
    ZXView mTestView;
    @ViewInject(R.id.edit_auth_code)
    EditText mEditText;
    InputMethodManager manager;


    @ViewInject(R.id.ll_view_group)
    LinearLayout mLinearLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView= getWindow().getDecorView();
        decorView.setBackgroundColor(Color.CYAN);
        decorView.setAlpha(0.3f);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLUE);
        }


        ViewParent parent= mLinearLayout.getParent();
        Log.i(TAG, "onCreate: "+parent.getClass());


        studyZXView();
        studyEditText();




    }

    /**
     * 自定义view 的学习
     */
    public  void  studyZXView(){
        mTestView.setAlpha(0.9f);

        mTestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i(TAG, "onClick: ");
                String title= (int)(Math.random()*10000+
                        Math.random()*1000+Math.random()*100+
                        Math.random()*10)+"";
                Log.i(TAG, "onClick: "+title);

                mTestView.setmTitleText(title);
            }
        });

    }


    /**
     * EditText 的学习
     */
    public  void studyEditText(){
        mEditText.setFocusable(true);
        mEditText.setFocusableInTouchMode(true);
        mEditText.requestFocus();
        manager =(InputMethodManager) mEditText.getContext().
                getSystemService(INPUT_METHOD_SERVICE);
        manager.showSoftInput(mEditText,0);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i(TAG, "onTextChanged: ");
            }

            @Override
            public void afterTextChanged(Editable editable) {

                Log.i(TAG, "afterTextChanged: ");
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        manager.hideSoftInputFromWindow(mEditText.getWindowToken(),0);
        return true;
    }
}
