package com.org.xiuer.androidorg.activity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public  class LoadActivity extends LauncherActivity {

        String []names={"一、图表的学习","二、zxing二维码的测试（第三方框架的学习）","三、webview测试（混合app的开发学习）",
                "四、ui组件的学习（绘制、组合、自定义）",
                "五、文件上传下载（网络的学习）",
                "六、深入理解Android"};

        Class<?>[]classes={ChartActivity.class, WebShellTestActivity.class,
                 WebViewActivity.class,
                GraphActivity.class,

                NetWorkActivity.class,
                DepthStudyActivity.class
        };
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