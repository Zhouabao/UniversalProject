package com.yuzhua.universalinvinciblesdk.base;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuzhua.universalinvinciblesdk.R;
import com.yuzhua.universalinvinciblesdk.util.AppManager;
import com.yuzhua.universalinvinciblesdk.util.LogUtils;


/**
 * Created by Zhou Fengmei on 2018/4/12.
 */

public class BaseActivity1 extends AppCompatActivity {
    private TextView title;
    private ImageView back;
    protected ImageView set;
    private LinearLayout rootLayout;
    private Toolbar toolbar;
    protected final String TAG = this.getClass().getSimpleName();
    protected boolean useDarkStatusBarColor = true;//是否使用暗色系文字
    protected int statusBarColor = Color.TRANSPARENT;//默认为透明色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
        setContentView(R.layout.activity_base);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        initStatusBar();
        AppManager.getAppManager().addActivity(this);
    }

    private void initStatusBar() {
        //沉浸式状态栏，版本 >= Android 4.4
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //状态栏 @ 顶部
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//A
            //导航栏 @ 底部
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//B
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useDarkStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    protected TextView getTitleView() {
        return title;
    }

    protected void setTitle(String msg) {
        if (title != null) {
            title.setText(msg);
        }
    }

    protected void setBackBtn() {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            LogUtils.e(TAG, "back is null ,please check out ");
        }

    }

    protected void setBackBtnImg(int id) {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setImageDrawable(getResources().getDrawable(id));
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            LogUtils.e(TAG, "back is null ,please check out ");
        }

    }

    protected void setBackClickListener(View.OnClickListener l) {
        if (back != null) {
            back.setVisibility(View.VISIBLE);
            back.setOnClickListener(l);
        } else {
            LogUtils.e(TAG, "back is null ,please check out ");
        }
    }

    protected void setSettingBtnImg(int id) {
        if (set != null) {
            set.setVisibility(View.VISIBLE);
            set.setImageDrawable(getResources().getDrawable(id));
        } else {
            LogUtils.e(TAG, "set is null ,please check out");
        }
    }

    protected void hideSettingBtn() {
        if (set != null) {
            set.setVisibility(View.GONE);
        }
    }

    protected void setSettingBtn(View.OnClickListener l) {
        if (set != null) {
            set.setVisibility(View.VISIBLE);
            set.setOnClickListener(l);
        } else {
            LogUtils.e(TAG, "set is null ,please check out");
        }
    }

    public void hideToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        } else {
            LogUtils.e(TAG, "toolbar is null ,please check out");
        }
    }

    public void ShowToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            LogUtils.e(TAG, "toolbar is null ,please check out");
        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        if (getSupportActionBar() != null) {
            // Enable the Up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        back = (ImageView) findViewById(R.id.toolbar_back);
        title = (TextView) findViewById(R.id.toolbar_title);
        set = (ImageView) findViewById(R.id.toolbar_set);
    }

    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setStatusBarColor(statusBarColor);
        initToolbar();
        setToolbarColor(getResources().getColor(R.color.white));
    }

    protected void setToolbarColor(int color) {
        if (toolbar == null) return;
        toolbar.setBackgroundColor(color);
    }

    protected void setTitleTextColor(int color) {
        if (title == null) return;
        title.setTextColor(color);
    }

    public void setStatusBarColor(int resource) {
        if (rootLayout == null) return;
        rootLayout.setBackgroundResource(resource);
    }

    //布局中Fragment的ID
    protected int getFragmentContentId() {
        return 0;
    }

    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
}
