package cn.com.example.haitu.constant;

/**
 * Created by zoutongbin on 2017/6/26.
 */

public class Constants {
    public final static String USER_AGENT = "com.haorenhaoxin.android.v" ;
    public final static String FILE_CONTENT_FILEPROVIDER = "com.hrhx.android.app.fileprovider";
    /**
     * 桥接方法名
     */


    /**
     * 正则：身份证号码18位最后一位不区分大小写
     */
    public static final String REGEX_ID_CARD18 = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$";
    public static final String REGEX_NAME = "^[\\*\\u4E00-\\u9FA5]{1,8}(?:[·•]{1}[\\u4E00-\\u9FA5]{1,10})*$";

    /**
     * EventBus事件
     */
//    public static final int EVENT_1 = 0x0001;//加载手机认证
//    public static final int EVENT_2 = 0x0002;//手机认证完关闭webview
    /**
     * 接受新消息，首页消息图标加红点
     */
//    public static final int EVENT_3_SHOW_RED_DOT = 0x0003;
}
