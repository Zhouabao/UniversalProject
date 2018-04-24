package com.yuzhua.shoppingdemo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.yuzhua.shoppingdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class IndexFragment extends Fragment {
    private static IndexFragment fragment;
    @BindView(R.id.rg_index)
    RadioGroup rgIndex;
    Unbinder unbinder;

    public static IndexFragment newInstance() {
        if (fragment == null)
            fragment = new IndexFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        unbinder = ButterKnife.bind(this, view);
        rgIndex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_zonghe) {

                } else if (checkedId == R.id.rb_fenlei) {

                } else if (checkedId == R.id.rb_zhuanti) {

                } else if (checkedId == R.id.rb_tuijian) {

                }
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
