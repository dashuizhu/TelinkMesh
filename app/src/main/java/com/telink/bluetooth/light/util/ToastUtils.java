package com.telink.bluetooth.light.util;

import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.telink.bluetooth.light.TelinkLightApplication;

/**
 * toast 工具
 * @Author: zhujiang
 * @Date: 2019/10/29 15:25
 */
public class ToastUtils extends Toast {
    private volatile static Toast mToast;

    private ToastUtils(Context context) {
        super(context);
    }

    private static Toast getToast() {
        if (mToast == null) {
            synchronized (ToastUtils.class) {
                mToast = Toast.makeText(TelinkLightApplication.getApp(), "", Toast.LENGTH_LONG);
            }
        }
        return mToast;
    }

    public static void toast(@StringRes int message) {
        toast(TelinkLightApplication.getApp().getString(message));
    }

    public static void toast(String message) {
        toastRes(message);
    }

    private static void toastRes(String str) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //9.0上系统机制修改，重复toast 会自动调用隐藏上一个， 不能共用一个toast
            Toast.makeText(TelinkLightApplication.getApp(), str, Toast.LENGTH_LONG).show();
        } else {
            Toast toast = getToast();
            toast.setText(str);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }

}