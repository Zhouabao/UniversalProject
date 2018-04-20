package com.yuzhua.universalinvinciblesdk.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import es.dmoral.toasty.Toasty;

/**
 * Created by Zhou Fengmei on 2018/4/13.
 */

public class ToastUtil {

    public static void toastSuccess(Context context, String msg, int time, boolean withIcon) {
        Toasty.success(context, msg, time, withIcon).show();
    }

    public static void toastError(Context context, String msg, int time, boolean withIcon) {
        Toasty.error(context, msg, time, withIcon).show();
    }

    public static void toastInfo(Context context, String msg, int time, boolean withIcon) {
        Toasty.info(context, msg, time, withIcon).show();
    }

    public static void toastWarning(Context context, String msg, int time, boolean withIcon) {
        Toasty.warning(context, msg, time, withIcon).show();
    }

    public static void toastNormalNoIcon(Context context, String msg, int time) {
        Toasty.normal(context, msg, time).show();
    }

    public static void toastNormalIcon(Context context, String msg, int time, Drawable icon) {
        Toasty.normal(context, msg, time, icon, true).show();
    }

}
