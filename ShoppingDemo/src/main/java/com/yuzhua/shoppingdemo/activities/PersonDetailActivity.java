package com.yuzhua.shoppingdemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yuzhua.shoppingdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
