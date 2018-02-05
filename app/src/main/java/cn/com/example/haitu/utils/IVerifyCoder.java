package cn.com.example.haitu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.token.verifysdk.VerifyActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

/**
 * Created by zenghui on 2017/11/8.
 */

public class IVerifyCoder {
    IVerifyCoder.VerifyListener listener;
    private boolean showtitle;
    private String json;
    private static IVerifyCoder verifyCoder;
    private final WebChromeClient mChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
        }

        @Override
        public void onProgressChanged(WebView view, int progress) {
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            try {
                JSONObject json = new JSONObject(message);
                if(json.optInt("ret") == 0) {
                    String ticket = json.optString("ticket");
                    String randstr = json.optString("randstr");
                    if(IVerifyCoder.this.listener != null) {
                        IVerifyCoder.this.listener.onVerifySucc(ticket, randstr);
                    }
                } else if(IVerifyCoder.this.listener != null) {
                    IVerifyCoder.this.listener.onVerifyFail();
                }
            } catch (JSONException var9) {
                var9.printStackTrace();
            }

            result.cancel();
            return true;
        }
    };

    public IVerifyCoder() {
    }

    public boolean isShowtitle() {
        return this.showtitle;
    }

    public void setShowtitle(boolean showtitle) {
        this.showtitle = showtitle;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public static IVerifyCoder getVerifyCoder() {
        if(verifyCoder == null) {
            verifyCoder = new IVerifyCoder();
        }

        return verifyCoder;
    }

    public void release() {
        this.listener = null;
        this.json = null;
        this.showtitle = false;
        verifyCoder = null;
    }

    public void startVerifyActivityForResult(Context context, String jsurl, int requestCode) {
        if(context == null) {
            Log.e("verify_error", "context is null");
        } else if(jsurl == null) {
            Log.e("verify_error", "jsurl is null");
        } else {            Intent it = new Intent(context, VerifyActivity.class);
            it.putExtra("jsurl", jsurl);
            ((Activity)context).startActivityForResult(it, requestCode);
        }
    }

    public WebView getWebView(Context context, String jsurl, String ip, int port , String applicationName, IVerifyCoder.VerifyListener listener) {
        if(context == null) {
            Log.e("verify_error", "context is null");
            return null;
        } else if(jsurl == null) {
            Log.e("verify_error", "jsurl is null");
            return null;
        } else {
            this.listener = listener;
            WebView wv = new WebView(context);
            wv.getSettings().setDefaultTextEncodingName("UTF-8");
            wv.setWebChromeClient(this.mChromeClient);

            try {
                Method removeJavascriptInterface = wv.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                if(removeJavascriptInterface != null) {
                    removeJavascriptInterface.invoke(wv, new Object[]{"searchBoxJavaBridge_"});
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            WebSettings setting = wv.getSettings();
            setting.setUserAgentString("android");
            setting.setJavaScriptEnabled(true);
            setting.setNeedInitialFocus(false);
            setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
            setting.setBuiltInZoomControls(false);
            setting.setSupportZoom(false);
            WebviewProxy.setProxy(wv, ip, port, applicationName);
            wv.loadDataWithBaseURL((String)null, jsurl, "text/html", "UTF-8", (String)null);
            return wv;
        }
    }


    public interface VerifyListener {
        void onVerifySucc(String var1, String var2);

        void onVerifyFail();
    }

}
