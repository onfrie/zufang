package cn.com.example.haitu.utils;

import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

/**
 * Created by ricky on 15-10-9.
 */
public class RegularCheckUtils {

    /**
     * 校验电话号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneValid(String phone) {

        if (TextUtils.isEmpty(phone)) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[1][3,4,6,7,5,8,9][0-9]{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * 校验密码
     *
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password) {
        return !TextUtils.isEmpty(password);
    }

    /**
     * 校验验证码
     *
     * @param captcha
     * @return
     */
    public static boolean isCaptchaValid(String captcha) {
        return !TextUtils.isEmpty(captcha) && captcha.length() == 5;
    }

    /**
     * 校验短信验证码
     *
     * @param message
     * @return
     */
    public static boolean isMessageValid(String message) {
        return message.length() >= 4;
    }


    public static void setWebViewSettings(final WebView webView) {

        webView.getSettings().setJavaScriptEnabled(true);           // 设置支持javascript脚本
        webView.getSettings().setUserAgentString("android");     //webview的浏览器标识 User-Agent
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webView.getSettings().setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
            }
/*            MIXED_CONTENT_NEVER_ALLOW：Webview不允许一个安全的站点（https）去加载非安全的站点内容（http）,比如，https网页内容的图片
            是http链接。强烈建议App使用这种模式，因为这样更安全。
            MIXED_CONTENT_ALWAYS_ALLOW：在这种模式下，WebView是可以在一个安全的站点（Https）里加载非安全的站点内容（Http）,这
            是WebView最不安全的操作模式，尽可能地不要使用这种模式。
            MIXED_CONTENT_COMPATIBILITY_MODE：在这种模式下，当涉及到混合式内容时，WebView会尝试去兼容最新Web浏览器的风格。一些
            不安全的内容（Http）能被加载到一个安全的站点上（Https），而其他类型的内容将会被阻塞。这些内容的类型是被允许加载还是被阻塞可能会随着版本的不同而改变，并没有明确的定义。这种模式主
            要用于在App里面不能控制内容的渲染，但是又希望在一个安全的环境下运行。*/
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);           //开启硬件加速
        } else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);           //开启软件加速
        }

        //设置此属性，可任意比例缩放,自适应屏幕：
//        webView.getSettings().setUseWideViewPort(true);                   //将图片调整到适合webview的大小
//        webView.getSettings().setLoadWithOverviewMode(true);              // // 缩放至屏幕的大小

        webView.getSettings().setLightTouchEnabled(true);                   //设置用鼠标激活被选项
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        //在API18以上已废弃。不建议调整线程优先级，未来版本不会支持这样做。设置绘制（Render，很多书上翻译成渲染，貌似很专业，
        // 但是不易懂，不敢苟同）线程的优先级。不像其他设置，同一进程中只需调用一次，默认值NORMAL。

        webView.getSettings().setBlockNetworkImage(false);              //是否禁止从网络（通过http和https URI schemes访问的资源）下载图片资源，默认值为false。
        webView.getSettings().setDomStorageEnabled(true);               //DOM存储API是否可用，默认false。H5要在手机上存储一些东西
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //重写使用缓存的方式，默认值LOAD_DEFAULT。缓存的使用方式基于导航类型，正常的页面加载，检测缓存，需要时缓存内容复现。导航返回时，
        // 内容不会复现，只有内容会从缓存盘中恢复。该方法允许客户端通过指定LOAD_DEFAULT, LOAD_CACHE_ELSE_NETWORK,
        // LOAD_NO_CACHE or LOAD_CACHE_ONLY的其中一项来重写其行为。
        webView.setFocusable(true);
        webView.setClickable(true);
        webView.setHapticFeedbackEnabled(true);         //是否启用触摸反馈，启用后就是在点击等操作时会有震动等反馈效果
        webView.setFocusableInTouchMode(true);          //设置在Touch模式下View是否能取得焦点。
        webView.getSettings().setUseWideViewPort(true);
        //WebView是否支持HTML的“viewport”标签或者使用wide viewport。设置值为true时，布局的宽度总是与WebView控件上的设备无关像素
        // （device-dependent pixels）宽度一致。当值为true且页面包含viewport标记，将使用标签指定的宽度。如果页面不包含标签或者标签没
        // 有提供宽度，那就使用wide viewport。
        webView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!webView.hasFocus()) {
                            v.requestFocusFromTouch();      //对应的View支持Focus，但是不支持在Touch模式下的Focus;Touch模式下不支持焦点，也能够获得焦点使用的。
                        }
                        break;
                }
                return false;
            }
        });
    }

    //姓名校验
    public static boolean checkName(String name) {
        String regx = "^[\\*\\u4E00-\\u9FA5]{1,8}(?:[·•]{1}[\\u4E00-\\u9FA5]{1,10})*$";
        return name.matches(regx);
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {

        if (TextUtils.isEmpty(email)) {
            return false;
        }

        boolean flag = false;
        try {
            String check = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";//"^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证邮箱
     *
     * @param QQ
     * @return
     */
    public static boolean checkQQ(String QQ) {
        if (TextUtils.isEmpty(QQ)) {
            return false;
        }

        if (QQ.length() > 14) {
            return false;
        }
        boolean flag = false;
        long x = Long.parseLong(QQ);
        if (x >= 10000 && x <= 9999999999L) {
            flag = true;
        }
        return flag;

    }

    /**
     * 校验银行卡卡号
     *
     * @param cardId
     * @return
     */
    public static boolean checkBankCard(String cardId) {
        if (TextUtils.isEmpty(cardId)) {
            return false;
        }

        if (cardId.length() < 16) {
            return false;
        }
        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        if (bit == 'N') {
            return false;
        }
        return cardId.charAt(cardId.length() - 1) == bit;
    }


    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     *
     * @param nonCheckCodeCardId
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

}
