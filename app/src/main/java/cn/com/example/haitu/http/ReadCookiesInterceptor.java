package cn.com.example.haitu.http;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.Set;

import cn.com.example.haitu.MyApp;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zoutongbin on 2017/6/26.
 */

public class  ReadCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder builder = originalRequest.newBuilder();
        Set<String> preferences = SPUtils.getInstance().getStringSet("PREF_COOKIES");
        if (preferences != null && preferences.size() > 0) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
            }
        }
        HttpUrl modifiedUrl;
        String whtk = MyApp.getInstance().getWhtk();
//                        LogUtils.e("OkHttp", "whtk-------->" + MyApp.getInstance().getWhtk());
        if (!TextUtils.isEmpty(MyApp.getInstance().getWhtk())) {
            builder.addHeader("whtk", MyApp.getInstance().getWhtk());
            modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("whtk", whtk)
                    .build();
        } else {
            modifiedUrl = originalRequest.url().newBuilder()
                    .build();
        }
        StringBuilder userAgent = new StringBuilder();
        userAgent.append("com.haorenhaoxin.android.v").append("");
        builder.header("User-Agent", userAgent.toString());
        Request newRequest = builder.url(modifiedUrl).build();
        Response response = chain.proceed(newRequest);
        return response;
    }
}