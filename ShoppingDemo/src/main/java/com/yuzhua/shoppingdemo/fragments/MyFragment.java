package com.yuzhua.shoppingdemo.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzhua.shoppingdemo.R;


public class MyFragment extends Fragment {

    private static MyFragment fragment;

    public static MyFragment newInstance() {
        if (fragment == null)
            fragment = new MyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

}
