package cn.com.example.haitu.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import app.hrhx.android.com.haoren.application.MyApp;


/**
 * Toast工具类
 */
public class ToastUtils {
    private static final String TAG = "ToastUtils";
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    public static void showLongToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showToast(MyApp.getInstance(), msg, Toast.LENGTH_LONG);
        }
    }

    public static void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            showToast(MyApp.getInstance(), msg, Toast.LENGTH_SHORT);
        }
    }

    private static void showToast(Context context, String s, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, s, duration);
            toast.setGravity(Gravity.CENTER, 0, 20);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > duration) {
                    toast.show();
                }
            } else {
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
