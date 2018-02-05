package cn.com.example.haitu.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.JsonSyntaxException;
import com.zyw.horrarndoo.sdk.utils.LogUtils;

import org.json.JSONObject;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLException;

import cn.com.example.haitu.MyApp;
import cn.com.example.haitu.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zoutongbin on 2017/6/26.
 */

public abstract class ApiCallBack<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        switch (response.code()) {
            case 200:
                onSuccess(response.body());
                break;
            case 400:
                String reqid1 = response.headers().get("WH_REQID");
                String msg = null;
                int errcode = 0;
                if (TextUtils.isEmpty(reqid1)) {
                    msg = "系统错误了，请联系客服";
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        msg = jsonObject.optString("errmsg");
                        errcode = jsonObject.optInt("errcode");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    StringBuilder stringBuilder = new StringBuilder(msg);
                    stringBuilder.append("\n错误id：").append(reqid1);
                    msg = stringBuilder.toString();
                    if (errcode < 0) {
                        errcode = 400;
                    }
                }
                ToastUtils.showLongToast(msg);
                onFail(errcode, msg);
                break;
            case 401:
                ToastUtils.showLongToast("登录失效，请重新登录");
                MyApp.getInstance().setWhtk("");
                SPUtils.getInstance().clear();

                onFail(401, "登录失效，请重新登录");
                break;
            case 404:
                ToastUtils.showLongToast("服务器报错,请联系客服");
                onFail(404, "服务器报错,请联系客服");
                break;
            case 500:
                String reqid = response.headers().get("WH_REQID");
                String errorMsg = null;
                if (TextUtils.isEmpty(reqid)) {
                    errorMsg = "系统错误，请联系客服";
                } else {
                    StringBuilder stringBuilder = new StringBuilder(getMsg(response));
                    stringBuilder.append("\n错误id：").append(reqid);
                    errorMsg = stringBuilder.toString();
                }
                ToastUtils.showLongToast(errorMsg);
                onFail(500, errorMsg);
                break;
            default:
                onFail(response.code(), response.message());
                ToastUtils.showLongToast("系统错误，请联系客服");
                break;
        }
    }

    private String getMsg(Response<T> response) {
        String msg = "";
        try {
            JSONObject jsonObject = new JSONObject(response.errorBody().string());
            msg = jsonObject.optString("errmsg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        String errorMessage = "获取数据失败，请重试";
        if (t instanceof SocketTimeoutException) {
            errorMessage = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            errorMessage = "服务器连接异常";
        } else if (t instanceof JsonSyntaxException) {
            errorMessage = "解析数据失败";
        } else if (t instanceof SSLException) {
            errorMessage = "网络错误，请重试";
        } else if (t instanceof EOFException) {
            errorMessage = "EOFException";
        } else if (t instanceof ConnectException) {
            errorMessage = "服务器连接失败";
        }
        LogUtils.e(t);
        ToastUtils.showLongToast(errorMessage);
        onFail(-1, errorMessage);
    }

    public abstract void onSuccess(T result);

    public void onFail(int code, String msg) {
    }
}