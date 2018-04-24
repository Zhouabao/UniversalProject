package com.yuzhua.shoppingdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yuzhua.shoppingdemo.BaseFragment;
import com.yuzhua.shoppingdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingCartFragment extends BaseFragment {
    private static ShoppingCartFragment fragment;



    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
