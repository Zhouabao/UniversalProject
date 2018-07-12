package com.yuzhua.baes.common

import android.app.Application
import android.content.Context
import android.support.annotation.NonNull
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * @author Zhou Fengmei
 * @create 2018/6/15
 * @Describe
 */
open class BaseApplication : Application() {


    companion object {
        lateinit var context: Context
        val header: Unit = SmartRefreshLayout.setDefaultRefreshHeaderCreater(object : DefaultRefreshHeaderCreater {
            override fun createRefreshHeader(context: Context?, layout: RefreshLayout?): RefreshHeader {
                layout?.setDisableContentWhenRefresh(true)
                layout?.isEnableAutoLoadmore = false
                ClassicsHeader.REFRESH_HEADER_LASTTIME = "上次更新：MM-dd HH:mm"
                return ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate).setFinishDuration(100)
            }
        })

        val footer: Unit = SmartRefreshLayout.setDefaultRefreshFooterCreater(object : DefaultRefreshFooterCreater {
            override fun createRefreshFooter(context: Context?, layout: RefreshLayout?): RefreshFooter {
                layout?.setDisableContentWhenLoading(true)
                layout?.isEnableAutoLoadmore = false
                ClassicsFooter.REFRESH_FOOTER_LOADING = "加载中..."
                return ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate).setFinishDuration(100)


            }

        })
    }

    override fun onCreate() {
        super.onCreate()
        context = this

    }

}