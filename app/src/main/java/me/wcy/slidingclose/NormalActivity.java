package me.wcy.slidingclose;

import android.os.Bundle;

import com.babytree.sdk.slidingclose.SlidingActivity;

public class NormalActivity extends SlidingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
    }
}
