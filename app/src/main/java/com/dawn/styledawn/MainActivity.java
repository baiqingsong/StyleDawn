package com.dawn.styledawn;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void jumpToShape(View view){
        jumpToActivity(ShapeActivity.class);
    }
    public void jumpToSelector(View view){
        jumpToActivity(SelectorActivity.class);
    }
    public void jumpToLayerList(View view){
        jumpToActivity(LayerListActivity.class);
    }
}
