package com.yuzhua.shoppingdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yuzhua.shoppingdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingCartFragment extends Fragment {
    private static ShoppingCartFragment fragment;
    @BindView(R.id.lv_goods)
    ListView lvGoods;
    Unbinder unbinder;

    public static ShoppingCartFragment newInstance() {
        if (fragment == null)
            fragment = new ShoppingCartFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
