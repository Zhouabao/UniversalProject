package com.yuzhua.universalinvinciblesdk.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by Zhou Fengmei on 2018/4/13.
 */

public class ToastUtil {
    public static void toastSuccess(Context context, String msg,  boolean withIcon) {
            Toasty.success(context, msg, Toast.LENGTH_SHORT, withIcon).show();
    }

    public static void toastError(Context context, String msg,boolean withIcon) {
            Toasty.error(context, msg,  Toast.LENGTH_SHORT, withIcon).show();
    }

    public static void toastInfo(Context context, String msg, boolean withIcon) {
            Toasty.info(context, msg,  Toast.LENGTH_SHORT, withIcon).show();
    }

    public static void toastWarning(Context context, String msg, boolean withIcon) {
            Toasty.warning(context, msg,  Toast.LENGTH_SHORT, withIcon).show();
    }

    public static void toastNormalNoIcon(Context context, String msg) {
            Toasty.normal(context, msg,  Toast.LENGTH_SHORT).show();
    }

    public static void toastNormalIcon(Context context, String msg,  Drawable icon) {
            Toasty.normal(context, msg,  Toast.LENGTH_SHORT, icon, true).show();
    }
}
