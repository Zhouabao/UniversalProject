package com.yuzhua.shoppingdemo.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
public class FenleiFragment extends Fragment {

    private static FenleiFragment fragment;
    @BindView(R.id.rv_reaction)
    RecyclerView rvReaction;
    Unbinder unbinder;
    private ArrayList<Integer> ivs;

    public synchronized static FenleiFragment newInstance() {
        if (fragment == null)
            fragment = new FenleiFragment();
        return fragment;
    }

    private void initData() {
        ivs = new ArrayList<>();
        ivs.add(R.drawable.fnlei_1);
        ivs.add(R.drawable.fenlei_2);
        ivs.add(R.drawable.fenlei_3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvReaction.setLayoutManager(layoutManager);
        BaseQuickAdapter adapter = new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.item_fenlei, ivs) {
            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                helper.setImageResource(R.id.iv_item, item);
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
