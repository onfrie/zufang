package cn.com.example.haitu.http;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.com.example.haitu.MyApp;
import okhttp3.Cache;

/**
 *
 * @author yorek
 * @date 10/23/17
 */
public class OkHttpCache {
    public static final String CACHE_DIR_NAME = "okhttp_cache";

    public static final List<String> URLS = new ArrayList<>();

    public static final String ALL_PLATFORM_URL = "/rest/bill/platform/all";
    public static final String ALL_AUTO_PLATFORM_URL = "/rest/bill/platform/crawl";

    static {
        URLS.add(ALL_PLATFORM_URL);
        URLS.add(ALL_AUTO_PLATFORM_URL);
    }

    public static Cache getCache() {
        File cacheFile = getCacheDir();
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);
        return cache;
    }

    public static void clearCache(String urlPath)  {
        try {
            Iterator<String> iterator = getCache().urls();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.contains(urlPath)) {
                    iterator.remove();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getCacheDir() {
        return new File(MyApp.getInstance().getApplicationContext().getCacheDir(), CACHE_DIR_NAME);
    }
}
