package cn.com.example.haitu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.zyw.horrarndoo.sdk.global.GlobalApplication;

import cn.com.example.haitu.constant.SpKey;
import cn.com.example.haitu.utils.Logs;

/**
 * Created by yt on 2017/12/27.
 */

public class MyApp extends GlobalApplication {
    private static MyApp mApp;
    private int count = 0;
    private static boolean isBack = false;

    private String whtk;
    private String phone;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//        Bugly.setIsDevelopmentDevice(this,true);
//        Bugly.init(this, "1105619979", false);

        Utils.init(this);
//        GreenDaoManager.getInstance();
        getPhone();
        getWhtk();
        if(BuildConfig.DEBUG){

        }else {
            Logs.closeLogs();
        }

//        initX5();

//        JPushInterface.init(this);
//        JPushInterface.setDebugMode(BuildConfig.DEBUG);
//        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//            }
//        });
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                count--;
                if (count == 0) {
                    isBack = true;
                    Logs.v("viclee", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Logs.v("viclee", activity + "onActivityStarted");
                if (count == 0) {
                    isBack = false;
//                    EventBus.getDefault().post(new MessageEvent.Builder(CommonUtilities.EVENT_GET_SYSTEM_FIX).build());
                    Logs.v("viclee", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
                    if (activity instanceof MainActivity){
                        Logs.v("viclee", ">>>>>>>>>>>>>>>>>>> MainActivity  lifecycle");
                    }
                }
                count++;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Logs.v("viclee", activity + "onActivityCreated");
            }
        });
    }

//    private void initX5() {
//        QbSdk.initX5Environment(this,  new QbSdk.PreInitCallback() {
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//            }
//            @Override
//            public void onCoreInitFinished() {
//            }
//        });
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
//        Beta.installTinker();
    }

    public static MyApp getInstance() {
        return mApp;
    }

    public String getPhone() {
        if (TextUtils.isEmpty(phone)) {
            phone = SPUtils.getInstance().getString(SpKey.PHONE);
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(whtk);
    }

    public String getWhtk() {
        if (TextUtils.isEmpty(whtk)) {
            whtk = SPUtils.getInstance().getString(SpKey.WHTK);
        }
        return whtk;
    }

    public void setWhtk(String whtk) {
        this.whtk = whtk;
    }


    public void logout() {
        SPUtils.getInstance().put(SpKey.PHONE, "");
        SPUtils.getInstance().put(SpKey.WHTK, "");
        setPhone("");
        setWhtk("");
    }

}
