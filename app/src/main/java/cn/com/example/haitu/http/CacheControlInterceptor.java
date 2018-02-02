package cn.com.example.haitu.http;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author yorek
 * @date 10/23/17
 */

public class CacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (OkHttpCache.URLS.contains(chain.request().url().url().getPath())) {
            if (NetworkUtils.isConnected()) {
                int maxAge = 60 * 5;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            }
        }

        return response;
    }
}
