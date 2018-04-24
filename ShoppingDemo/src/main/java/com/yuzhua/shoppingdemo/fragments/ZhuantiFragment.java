package com.yuzhua.shoppingdemo.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuzhua.shoppingdemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhuantiFragment extends Fragment {

    private static ZhuantiFragment fragment;
    @BindView(R.id.rv_reaction)
    RecyclerView rvReaction;
    Unbinder unbinder;
    private ArrayList<Object> ivs;

    public static ZhuantiFragment newInstance() {
        if (fragment == null)
            fragment = new ZhuantiFragment();
        return fragment;
    }

    private void initData() {
        ivs = new ArrayList<>();
        ivs.add(R.drawable.attention);
        ivs.add(R.drawable.attention);
        ivs.add(R.drawable.attention);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        unbinder = ButterKnife.bind(this, view);
        BaseQuickAdapter adapter = new BaseQuickAdapter(R.layout.item_attention, ivs) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                helper.setImageResource(R.id.iv_item, (Integer) item);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }
        };
        rvReaction.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                
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
