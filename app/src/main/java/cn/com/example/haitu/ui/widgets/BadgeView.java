package cn.com.example.haitu.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyw.horrarndoo.sdk.utils.DisplayUtils;

import cn.com.example.haitu.R;

/**
 *
 * 描述：自定义带有角标小红点数字的View
 */
public class BadgeView extends RelativeLayout {

    private int mImageIconResource = R.mipmap.ic_launcher;//默认icon图标
    private String mTextString = "";//默认文字为空
    private float mTextSize = 15;//默认字体大小
    private int mTextColor = 0xFFFFFFFF;//默认字体颜色

    private Context mContext;

    private TextView mTvBarNumber;//小红点数量
    private TextView mTvBar;//分类文字
    private ImageView mIvBar;//分类图标

    private int mMessageCount = 0;//消息数量

    public BadgeView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BadgeView);
        mTextColor = ta.getColor(R.styleable.BadgeView_badgeTextColor, mTextColor);
        mTextSize = ta.getDimension(R.styleable.BadgeView_badgeTextSize, mTextSize);
        mTextString = ta.getString(R.styleable.BadgeView_badgeText);
        mImageIconResource = ta.getResourceId(R.styleable.BadgeView_badgeImageIcon, R.mipmap.ic_launcher);

        setAttribute();
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.view_badge, this, true);
        mTvBarNumber = (TextView) relativeLayout.findViewById(R.id.tv_bar_num);
        mTvBar = (TextView) relativeLayout.findViewById(R.id.tv_bar);
        mIvBar = (ImageView) relativeLayout.findViewById(R.id.iv_bar);
        if (mMessageCount == 0) mTvBarNumber.setVisibility(View.GONE);
    }

    private void setAttribute() {
        mTvBar.setText(mTextString);
//        mTvBar.setTextColor(mTextColor);
        mTvBar.setTextSize(DisplayUtils.px2sp(mContext,mTextSize));
        mIvBar.setImageResource(mImageIconResource);
    }

    /**
     * 设置小红点提示的数量
     *
     * @param count
     */
    public void setRedCount(int count) {
        mMessageCount = count;
        if (count == 0) {
            mTvBarNumber.setVisibility(View.GONE);
        } else {
            mTvBarNumber.setVisibility(View.VISIBLE);
            if (count < 100) {
                mTvBarNumber.setText(count + "");
            } else {
                mTvBarNumber.setText("99+");
            }
        }
        invalidate();
    }

    /**
     * 增加小红点的数量
     *
     * @param increment 需要增加的数量
     */
    public void addRedCountNumber(int increment) {
        setRedCount(mMessageCount + increment);
    }

    /**
     * 设置分类文字
     *
     * @param text
     */
    public void setBadgeText(String text) {
        if (!TextUtils.isEmpty(text)) {
            mTvBar.setText(text);
        }
    }

    /**
     * 设置分类文字大小
     *
     * @param textSize
     */
    public void setBadgeTextSize(float textSize) {
        mTvBar.setTextSize(textSize);
    }

    /**
     * 设置分类文字颜色
     */
    public void setBadgeTextColor(int textColor) {
        mTvBar.setTextColor(textColor);
    }

    /**
     * 设置ImageView图标
     *
     * @param icon
     */
    public void setBadgeIcon(int icon) {
        mIvBar.setImageResource(icon);
    }

}
