package com.yuzhua.baes.model

/**
 * @author Zhou Fengmei
 * @create 2018/6/15
 * @Describe
 */
data class BaseResponse<out T>(val code: Int, val msg: String, val data: T)