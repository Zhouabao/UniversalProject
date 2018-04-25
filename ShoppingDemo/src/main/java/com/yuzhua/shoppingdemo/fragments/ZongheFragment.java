package com.yuzhua.shoppingdemo.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yuzhua.shoppingdemo.activities.ProductDeatilActivity;
import com.yuzhua.shoppingdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZongheFragment extends Fragment {

    private static ZongheFragment fragment;
    Unbinder unbinder;

    public synchronized static ZongheFragment newInstance() {
        if (fragment == null)
            fragment = new ZongheFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zonghe, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                getActivity().startActivity(new Intent(getActivity(), ProductDeatilActivity.class).putExtra("image", R.drawable.detail_1));
                break;
            case R.id.btn_2:
                getActivity().startActivity(new Intent(getActivity(), ProductDeatilActivity.class).putExtra("image", R.drawable.detail_2));

                break;
            case R.id.btn_3:
                getActivity().startActivity(new Intent(getActivity(), ProductDeatilActivity.class).putExtra("image", R.drawable.detail_2));

                break;
            case R.id.btn_4:
                getActivity().startActivity(new Intent(getActivity(), ProductDeatilActivity.class).putExtra("image", R.drawable.detail_3));
                break;
            case R.id.btn_5:
                getActivity().startActivity(new Intent(getActivity(), ProductDeatilActivity.class).putExtra("image", R.drawable.detail_3));
                break;
        }
    }
}
