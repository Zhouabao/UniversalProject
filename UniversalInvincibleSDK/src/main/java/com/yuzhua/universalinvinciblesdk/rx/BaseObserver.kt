package com.yuzhua.baes.rx

import com.yuzhua.baes.model.BaseResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @author Zhou Fengmei
 * @create 2018/7/5
 * @Describe  observer的base类
 */
class BaseObserver<T> : Observer<BaseResponse<T>> {
    override fun onComplete() {

    }

    override fun onNext(t: BaseResponse<T>) {

    }

    override fun onError(e: Throwable) {

    }

    override fun onSubscribe(d: Disposable) {

    }
}