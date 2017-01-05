package com.org.xiuer.androidorg.activity.graph;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.org.orglib.Activity.BaseActivity;
import com.org.orglib.View.FlowerSurfaceView;
import com.org.xiuer.androidorg.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


@ContentView(R.layout.activity_flower)
public class FlowerActivity extends BaseActivity {

    private String TAG = "AndroidOrg";
    @ViewInject(R.id.bt_castFlower)
    private Button mCastFlower;


    @ViewInject(R.id.flower_view)
    private FlowerSurfaceView mFlowerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(screenRect()[0] / 3, screenRect()[1] / 3);
        mFlowerView.setLayoutParams(params);

        mFlowerView.initValue();
        Log.i(TAG, "onCreate: " + mFlowerView.getLayoutParams().width);


        mCastFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlowerView.startAnimation();

            }
        });
    }
}
