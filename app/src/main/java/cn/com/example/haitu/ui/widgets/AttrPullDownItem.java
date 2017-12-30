package cn.com.example.haitu.ui.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.example.haitu.R;


/**
 * Created by Dell on 2017/12/23.
 */

public class AttrPullDownItem extends LinearLayout {

    private String text;
    private String itemText;
    private View root = null;
    private TextView mSelectTextTv;
    private OnClickListeners mOnClickListeners;

    public AttrPullDownItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        text = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "contract_pull_text");
        itemText = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "contract_item_text");
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.view_pull_down_item, this);
        TextView informationTv = (TextView) root.findViewById(R.id.tv_information);
        TextView selectTv = (TextView) root.findViewById(R.id.tv_select_text);
        informationTv.setText(text);
        selectTv.setText(itemText);
        LinearLayout openListLL = (LinearLayout) root.findViewById(R.id.ll_open_list);
        mSelectTextTv = (TextView) root.findViewById(R.id.tv_select_text);
        openListLL.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnClickListeners != null) {
                    mOnClickListeners.onClick(view);
                }
            }
        });
    }
    public void setText(String text){
        mSelectTextTv.setText(text);
    }

    public void setOnclickListeners(OnClickListeners onclickListeners){
        this.mOnClickListeners = onclickListeners;
    }

    private interface OnClickListeners{
        void onClick(View view);
    }
}
