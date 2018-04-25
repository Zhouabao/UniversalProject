package com.yuzhua.shoppingdemo.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yuzhua.shoppingdemo.NoScrollViewPager;
import com.yuzhua.shoppingdemo.R;
import com.yuzhua.shoppingdemo.ViewPagerAdapter;
import com.yuzhua.shoppingdemo.fragments.AttentionFragment;
import com.yuzhua.shoppingdemo.fragments.IndexFragment;
import com.yuzhua.shoppingdemo.fragments.MyFragment;
import com.yuzhua.shoppingdemo.fragments.ShoppingCartFragment;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.viewPager)
    NoScrollViewPager mViewpage;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_nearby)
    RadioButton rbNearby;
    @BindView(R.id.rb_shoppingcart)
    RadioButton rbShoppingcart;
    @BindView(R.id.rb_me)
    RadioButton rbMe;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.main)
    LinearLayout main;
    private ArrayList<Fragment> fragments;
    // 保存用户按返回键的时间
    private long mExitTime = 0;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideToolbar();
        initView();
        setupViewPager(mViewpage);

    }

    protected void initView() {
        fragments = new ArrayList<>();
        fragments.add(IndexFragment.newInstance());
        fragments.add(AttentionFragment.newInstance());
        fragments.add(ShoppingCartFragment.newInstance());
        fragments.add(MyFragment.newInstance());

        rgGroup.check(R.id.rb_home);
        rgGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rb_home:
                mViewpage.setCurrentItem(0);
                break;
            case R.id.rb_nearby:
                mViewpage.setCurrentItem(1);
                break;
            case R.id.rb_shoppingcart:
                mViewpage.setCurrentItem(2);
                break;
            case R.id.rb_me:
                mViewpage.setCurrentItem(3);
                break;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgGroup.check(R.id.rb_home);
                        break;
                    case 1:
                        rgGroup.check(R.id.rb_nearby);
                        break;
                    case 2:
                        rgGroup.check(R.id.rb_shoppingcart);
                        break;
                    case 3:
                        rgGroup.check(R.id.rb_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
    }

    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再次点击退出程序哦", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }


}
