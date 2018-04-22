package com.yuzhua.universalproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.yuzhua.universalinvinciblesdk.glide.GlideUtil;
import com.yuzhua.universalinvinciblesdk.util.ui.MyLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.image2)
    ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        ButterKnife.bind(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLoader.showLoading(MainActivity.this);
                GlideUtil.loadRoundImg(MainActivity.this, "https://ws1.sinaimg.cn/large/610dc034ly1fir1jbpod5j20ip0newh3.jpg", image, R.mipmap.ic_launcher, R.mipmap.ic_launcher, 180);
//                GlideUtil.loadRoundImg(MainActivity.this,"https://ws1.sinaimg.cn/large/610dc034ly1fis7dvesn6j20u00u0jt4.jpg",image2);
                GlideUtil.loadImg(MainActivity.this,"https://ws1.sinaimg.cn/large/610dc034ly1fir1jbpod5j20ip0newh3.jpg",image2);
            }
        });
    }
}
