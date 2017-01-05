package com.org.xiuer.androidorg.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.org.orglib.Activity.BaseActivity;
import com.org.xiuer.androidorg.R;
import com.org.xiuer.androidorg.activity.graph.FlowerActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_graph)
public class GraphActivity extends BaseActivity {

    @ViewInject(R.id.bt_flower)
    private Button mCastFlower;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //撒福
        mCastFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GraphActivity.this, FlowerActivity.class));
            }
        });
    }


}
