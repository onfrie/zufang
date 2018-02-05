package cn.com.example.haitu.http;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import cn.com.example.haitu.MyApp;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author zoutongbin
 * @date 2017/6/26
 */

public class HttpHelper {
    public final static String DOMAIN_SIT = "https://app.aoren.com/";
    public final static String DOMAIN_UAT = "https://weixinten.com/";
    public final static String DOMAIN = false ? DOMAIN_UAT : DOMAIN_SIT;

    public final static String DOMAIN_H5_SIT = "https://wxin-xhed.ren.com/";
    public final static String DOMAIN_H5_UAT = "https://weixst.haon.com/";
    public final static String DOMAIN_H5 = false ? DOMAIN_H5_UAT : DOMAIN_H5_SIT;

    public static ApiService getApiService(final String apiUrl) {
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService task = mRetrofit.create(ApiService.class);
        return task;
    }

    public static ApiService getApiService() {
        return getApiService(DOMAIN);
    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(getHttpLoggingInterceptor())
                .addInterceptor(new ReadCookiesInterceptor())
                .addInterceptor(new SaveCookiesInterceptor())
                .addNetworkInterceptor(new CacheControlInterceptor())
                .cache(OkHttpCache.getCache())
                .build();
        return builder.build();
    }

    public static Interceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(false ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    public static RequestBody toRequestBody(String value) {
        if (TextUtils.isEmpty(value)){
            return null;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }

    public static String getHtmlUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            StringBuilder stringBuilder = new StringBuilder(HttpHelper.DOMAIN_H5);
            if (url.startsWith("/")) {
                stringBuilder.append(url.substring(1)).append("?whtk=").append(MyApp.getInstance().getWhtk());
            } else {
                stringBuilder.append(url).append("?whtk=").append(MyApp.getInstance().getWhtk());
            }
            return stringBuilder.toString();
        }
        return url;
    }
}
