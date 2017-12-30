package cn.com.example.haitu.ui.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.example.haitu.R;


/**
 * Created by Dell on 2017/12/23.
 */

public class AttrInputView extends LinearLayout {

    private String title;
    private View root = null;
    private EditText mTextEt;
    private OnTextchangedListener onTextchangedListener;

    public AttrInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        title = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "contract_input");
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.view_input_box, this);
        TextView describeTv = (TextView) root.findViewById(R.id.tv_describe);
        mTextEt = (EditText) root.findViewById(R.id.et_text);
        describeTv.setText(title);
        mTextEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (onTextchangedListener != null) {
                    onTextchangedListener.beforeTextChanged(charSequence, i, i1, i2);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (onTextchangedListener != null) {
                    onTextchangedListener.onTextChanged(charSequence, i, i1, i2);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (onTextchangedListener != null) {
                    onTextchangedListener.afterTextChanged(editable);
                }
            }
        });
    }

    public String getEditTextStr() {
        String text = mTextEt.getText().toString();
        return text;
    }

    public void setEditTextStr(String text){
        mTextEt.setText(text);
    }

    public void setOnTextchangedListener(OnTextchangedListener onTextchangedListener){
        this.onTextchangedListener = onTextchangedListener;
    }

    public interface OnTextchangedListener {
        void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2);

        void onTextChanged(CharSequence charSequence, int i, int i1, int i2);

        void afterTextChanged(Editable editable);
    }
}
