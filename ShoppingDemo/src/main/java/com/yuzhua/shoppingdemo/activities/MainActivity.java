package com.yuzhua.shoppingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yuzhua.shoppingdemo.activitys.SearchActivity;
import com.yuzhua.shoppingdemo.NoScrollViewPager;
import com.yuzhua.shoppingdemo.R;
import com.yuzhua.shoppingdemo.ViewPagerAdapter;
import com.yuzhua.shoppingdemo.fragments.AttentionFragment;
import com.yuzhua.shoppingdemo.fragments.IndexFragment;
import com.yuzhua.shoppingdemo.fragments.MyFragment;
import com.yuzhua.shoppingdemo.fragments.ShoppingCartFragment;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;
import com.yuzhua.universalinvinciblesdk.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.ll_navigation_uis)
    LinearLayout llNavigationUis;
    @BindView(R.id.ll_navigation_news)
    LinearLayout llNavigationNews;
    @BindView(R.id.ll_navigation_inform)
    LinearLayout llNavigationInform;
    @BindView(R.id.ll_navigation_more)
    LinearLayout llNavigationMore;
    @BindView(R.id.ll_navigation_out)
    LinearLayout llNavigationOut;
    @BindView(R.id.nav)
    NavigationView nav;
    @BindView(R.id.activity_na)
    DrawerLayout activityNa;
    private ArrayList<Fragment> fragments;
    // 保存用户按返回键的时间
    private long mExitTime = 0;
    private ViewPagerAdapter adapter;
    int chose = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setTitle("商店");
        SetToolbarColor(R.color.black);
        setBackBtnImg(R.mipmap.cehua);
        initView();
        setSettingBtnImg(R.mipmap.sousuo);
        setSettingBtn(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });

        setupViewPager(mViewpage);
        setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityNa.openDrawer(Gravity.LEFT);
            }
        });

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
                setTollbar(0);
                break;
            case R.id.rb_nearby:
                mViewpage.setCurrentItem(1);
                setTollbar(1);
                break;
            case R.id.rb_shoppingcart:
                mViewpage.setCurrentItem(2);
                setTollbar(2);
                break;
            case R.id.rb_me:
                mViewpage.setCurrentItem(3);
                setTollbar(3);
                break;
        }
    }

    public void setTollbar(int postion){
       if (postion==0){
           ShowToolbar();

        }else {
           hideToolbar();
       }


    }

    private void setupViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onCheckedChanged: " + "  " + position);
                setTollbar(position);
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


    @OnClick({R.id.ll_navigation_uis, R.id.ll_navigation_news, R.id.ll_navigation_inform, R.id.ll_navigation_more, R.id.ll_navigation_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_navigation_uis:
                ToastUtil.toastInfo(this, "UIS", false);
                break;
            case R.id.ll_navigation_news:
                ToastUtil.toastInfo(this, "NEWS", false);
                break;
            case R.id.ll_navigation_inform:
                ToastUtil.toastInfo(this, "INFORM", false);
                break;
            case R.id.ll_navigation_more:
                ToastUtil.toastInfo(this, "MORE", false);
                break;
            case R.id.ll_navigation_out:
                ToastUtil.toastInfo(this, "OUT", false);
                break;
        }
    }
}
