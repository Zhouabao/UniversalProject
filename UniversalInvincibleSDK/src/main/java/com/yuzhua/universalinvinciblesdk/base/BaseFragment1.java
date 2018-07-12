package com.yuzhua.universalinvinciblesdk.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Gu Zhongcai
 * @create 2018/4/26
 * @Describe basefragment
 */
public abstract class BaseFragment1 extends Fragment {
    private Unbinder unbinder;
    protected View mRootView;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean hasLoaded;  //是否已经加载过一次


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        if (!isPrepared || !isVisible || hasLoaded) {
            return;
        }
        init(mRootView, new Bundle());
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    /**
     * 获取布局ID
     */
    protected abstract int getContentViewLayoutID();

    /**
     * 界面初始化
     * 延迟加载
     * 子类必须重写此方法
     */
    protected void init(View view, Bundle savedInstanceState) {
        hasLoaded = true;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        //ViewPage + Fragment 防止Fragment 重复加载问题
        if (mRootView == null) {
            if (getContentViewLayoutID() != 0) {
                mRootView = inflater.inflate(getContentViewLayoutID(), container, false);
                unbinder = ButterKnife.bind(this, mRootView);
                onVisible();
            } else {
                return super.onCreateView(inflater, container, savedInstanceState);
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        unbinder = ButterKnife.bind(this, mRootView);
        if (parent != null) {
            parent.removeView(mRootView);//把当前root从其父控件中移除
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
