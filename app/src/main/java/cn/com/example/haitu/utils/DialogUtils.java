package cn.com.example.haitu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import cn.com.example.haitu.R;
import cn.com.example.haitu.interfaces.DialogListener;
import cn.com.example.haitu.view.CashbusDialog;

/**
 * Created by zenghui on 15/11/13.
 * wangfeng 添加注释
 */
public class DialogUtils {

    public static CashbusDialog cashbusDialog;
    //获取中,定位中

    public static void showLoading(Context context, String content) {
        if (cashbusDialog != null && cashbusDialog.isShowing()) {
            return;
        }
        cashbusDialog = new CashbusDialog(context, content);
        cashbusDialog.setCancelable(false);
        try {
            cashbusDialog.show();
        } catch (Exception e) {
        }
    }

    public static void dismissLoading() {
        if (cashbusDialog != null && cashbusDialog.isShowing()) {
            try {
                cashbusDialog.dismiss();
            } catch (Exception e) {
            }
        }
    }

    public static void showSelectDialog(Context context, String title, final String[] items, final DialogListener handleDialogListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(items, new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                handleDialogListener.handle(items[i]);
            }
        });
        AlertDialog alertDialog = builder.create();
        Window alertWindow = alertDialog.getWindow();
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lParams = alertWindow.getAttributes();
        if (items.length < 6) {
            lParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            lParams.height = (int) (display.getHeight() * 0.5);
        }
        lParams.width = (int) (display.getWidth() * 0.9);
        alertWindow.setAttributes(lParams);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_title, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.title);
        tvTitle.setText(title);
        alertDialog.setCustomTitle(view);
        alertDialog.show();

    }

}
