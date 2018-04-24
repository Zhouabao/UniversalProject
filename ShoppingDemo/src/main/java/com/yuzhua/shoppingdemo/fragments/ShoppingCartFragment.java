package com.yuzhua.shoppingdemo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuzhua.shoppingdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShoppingCartFragment extends Fragment {
    private static ShoppingCartFragment fragment;
    @BindView(R.id.lv_goods)
    RecyclerView lvGoods;
    Unbinder unbinder;
    private List<Integer> ivs;

    public static ShoppingCartFragment newInstance() {
        if (fragment == null)
            fragment = new ShoppingCartFragment();
        return fragment;
    }


    private void initData() {
        ivs = new ArrayList<>();
        ivs.add(R.drawable.cart_1);
        ivs.add(R.drawable.cart_2);
        ivs.add(R.drawable.cart_3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        unbinder = ButterKnife.bind(this, view);
        lvGoods.setAdapter(new BaseQuickAdapter(R.layout.item_shoppingcart, ivs) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                helper.setImageResource(R.id.iv_item, (Integer) item);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
