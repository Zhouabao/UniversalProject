package com.yuzhua.universalinvinciblesdk.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuzhua.universalinvinciblesdk.R;
import com.yuzhua.universalinvinciblesdk.util.AppManager;
import com.yuzhua.universalinvinciblesdk.util.LogUtils;


/**
 * Created by Zhou Fengmei on 2018/4/12.
 */

public class BaseActivity extends AppCompatActivity {
    private TextView title;
    private ImageView back;
    private ImageView set;
    private LinearLayout rootLayout;
    private LinearLayout myRootLayout;
    private Toolbar toolbar;
    protected final String TAG = this.getClass().getSimpleName();

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

    protected void setSettingBtn(View.OnClickListener l) {
        if (set != null) {
            set.setVisibility(View.VISIBLE);
            set.setOnClickListener(l);
        } else {
            LogUtils.e(TAG, "set is null ,please check out");
        }
    }

    protected void hideToolbar() {
        if (toolbar != null) {
            toolbar.setVisibility(View.GONE);
        } else {
            LogUtils.e(TAG, "toolbar is null ,please check out");
        }
    }

    protected void ShowToolbar() {
        if (toolbar != null) {
                toolbar.setVisibility(View.VISIBLE);
        } else {
            LogUtils.e(TAG, "toolbar is null ,please check out");
        }
    }
    protected void SetToolbarColor(int color) {
        if (myRootLayout != null) {
            myRootLayout.setBackgroundColor(getResources().getColor(color));
//            toolbar.setBackgroundColor(getResources().getColor(color));
        } else {
            LogUtils.e(TAG, "toolbar is null ,please check out");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        AppManager.getAppManager().addActivity(this);
        //initToolbar();
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
        myRootLayout = findViewById(R.id.ly_main_actionbar);
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
        initToolbar();
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
