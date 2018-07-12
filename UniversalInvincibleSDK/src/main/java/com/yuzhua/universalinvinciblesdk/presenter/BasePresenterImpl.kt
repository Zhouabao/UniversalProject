package com.yuzhua.baes.presenter

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
import com.yuzhua.baes.presenter.view.IBaseView

/**
 * @author Zhou Fengmei
 * @create 2018/6/15
 * @Describe
 */
open class BasePresenterImpl<T : IBaseView> : IBasePresenter {
    lateinit var mView: T
    lateinit var context: Context

    /**
     * 检查网络是否可用
     */
    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(context))
            return true
        mView.onError("网络不可用")
        return false
    }
}