package com.dawn.styledawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by 90449 on 2017/7/4.
 */

public class ShapeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
    }
    public void jumpToRectangle(View view){
        jumpToActivity(ShapeRectangleActivity.class);
    }
    public void jumpToOval(View view){
        jumpToActivity(ShapeOvalActivity.class);
    }
    public void jumpToLine(View view){
        jumpToActivity(ShapeLineActivity.class);
    }
    public void jumpToRing(View view){
        jumpToActivity(ShapeRingActivity.class);
    }
}
