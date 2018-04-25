package com.yuzhua.shoppingdemo.activitys;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import com.yuzhua.shoppingdemo.R;
import com.yuzhua.universalinvinciblesdk.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.sv_searche)
    SearchView svSearche;
    @BindView(R.id.tv_search_no)
    TextView tvSearchNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        hideToolbar();
    }

    @OnClick(R.id.tv_search_no)
    public void onViewClicked() {
        finish();
    }
}
