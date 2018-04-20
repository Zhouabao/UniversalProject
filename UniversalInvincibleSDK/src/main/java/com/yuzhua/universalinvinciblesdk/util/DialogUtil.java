package com.yuzhua.universalinvinciblesdk.util;

import android.app.Dialog;

/**
 * @author Zhou Fengmei
 * @create 2018/4/20
 * @Describe
 */
public class DialogUtil {
    /**
     * 展示dialog
     */
    public static Dialog MakeLoadingDialog() {
        return new Dialog(AppManager.getAppManager().currentActivity());
    }


    /**
     * @param dialog 展示中的dialog
     */
    public static void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void showDialog(Dialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }
}

