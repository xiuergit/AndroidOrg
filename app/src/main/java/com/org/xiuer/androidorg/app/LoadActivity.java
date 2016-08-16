package com.org.xiuer.androidorg.app;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;


public class LoadActivity extends LauncherActivity {

    String []names={" 图表的学习","webshell测试",
            "手势解锁","向导页","webview测试"};

    Class<?>[]classes={ChartActivity.class, WebShellTestActivity.class,
            GestureActivity.class, ZXGuideActivity.class,
             WebViewActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,names));

    }

    @Override
    protected Intent intentForPosition(int position) {
       return new Intent(this,classes[position]);
    }
}
