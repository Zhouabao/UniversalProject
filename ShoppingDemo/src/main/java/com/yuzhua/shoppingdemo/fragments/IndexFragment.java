package com.yuzhua.shoppingdemo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzhua.shoppingdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class IndexFragment extends Fragment {
    private static IndexFragment fragment;
    Unbinder unbinder;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] mTitle = new String[]{"综合", "分类", "专题", "推荐"};
    private Fragment[] fragments = new Fragment[]{ZongheFragment.newInstance(), FenleiFragment.newInstance(), ZhuantiFragment.newInstance(), TuijianFragment.newInstance()};

    public synchronized static IndexFragment newInstance() {
        if (fragment == null)
            fragment = new IndexFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        unbinder = ButterKnife.bind(this, view);

        viewpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }
        });
        tab.setupWithViewPager(viewpager);
        viewpager.setOffscreenPageLimit(fragments.length);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
