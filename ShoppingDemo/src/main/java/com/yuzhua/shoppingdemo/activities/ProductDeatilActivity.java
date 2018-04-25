package com.yuzhua.shoppingdemo.activities;

import android.os.Bundle;
import android.widget.ImageView;

import com.yuzhua.shoppingdemo.R;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDeatilActivity extends BaseActivity {

    @BindView(R.id.iv_detail)
    ImageView ivDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_deatil);
        ButterKnife.bind(this);
        setTitle("商品详情");
        setBackBtn();
        if (getIntent() != null && getIntent().getIntExtra("image", -1) != -1) {
            ivDetail.setImageResource(getIntent().getIntExtra("image", -1));
        }


    }
}
