package com.yuzhua.universalproject;

import android.os.Bundle;

import com.yuzhua.universalinvinciblesdk.base.BaseActivity;


public class TestBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_base);
        setBackBtn();
        setTitle("标题");
    }


    @Override
    protected int getFragmentContentId() {
        return 0;
    }
}
