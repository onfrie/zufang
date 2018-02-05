package cn.com.example.haitu.http;

import com.blankj.utilcode.util.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by zoutongbin on 2017/6/26.
 */

public class SaveCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if ("/rest/auth".equals(originalResponse.request().url().url().getPath()) && !originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();
            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SPUtils.getInstance().put("PREF_COOKIES", cookies);
        }
        return originalResponse;
    }
}