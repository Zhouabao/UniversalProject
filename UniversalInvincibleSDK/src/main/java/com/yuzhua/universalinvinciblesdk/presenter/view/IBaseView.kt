package com.yuzhua.baes.presenter.view

/**
 * @author Zhou Fengmei
 * @create 2018/6/14
 * @Describe
 */
interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(msg: String, code: Int? = 0)
}