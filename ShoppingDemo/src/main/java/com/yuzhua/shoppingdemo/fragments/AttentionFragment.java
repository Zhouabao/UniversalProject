package com.yuzhua.shoppingdemo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yuzhua.shoppingdemo.R;
import com.yuzhua.shoppingdemo.activities.PersonDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionFragment extends Fragment {

    private static AttentionFragment fragment;
    @BindView(R.id.rv_reaction)
    RecyclerView rvReaction;
    Unbinder unbinder;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_set)
    TextView toolbarSet;
    @BindView(R.id.ly_main_actionbar)
    RelativeLayout lyMainActionbar;
    private ArrayList<Integer> ivs;

    public synchronized static AttentionFragment newInstance() {
        if (fragment == null)
            fragment = new AttentionFragment();
        return fragment;
    }

    private void initData() {
        lyMainActionbar.setVisibility(View.VISIBLE);
        toolbarTitle.setText("我的关注");
        toolbarSet.setVisibility(View.GONE);

        ivs = new ArrayList<>();
        ivs.add(R.drawable.attention);
        /*ivs.add(R.drawable.attention);
        ivs.add(R.drawable.attention);*/
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
        BaseQuickAdapter adapter = new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.item_attention, ivs) {
            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                helper.setImageResource(R.id.iv_item, item);
            }

        };
        rvReaction.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), PersonDetailActivity.class));
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
