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
 * Created by Dell on 2017/12/22.
 */

public class AttrContractTitleView extends LinearLayout {

    private String title;
    private View root = null;

    public AttrContractTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        title = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "contract_title");
//                Log.e("title",title+"--");
//                attrs.getAttributeValue()
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.view_contract_title, this);
        TextView contractTitleTv = (TextView) root.findViewById(R.id.tv_contract_title);
        contractTitleTv.setText(title);


    }
}

//获取属性三种方式
//1.用命名空间取获取

//        String name=attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_name");
//        String bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_bg");
//参数1.是指在xml文件中写入自定义的语句时，报错时按得Alt+Enter快捷键时生成的语句
//参数2.是在创建attrs.xml文件中，相对应的属性

//        Log.e("tag",age+"---"+name+"---"+bg);

//2.遍历属性集合
//        for (int i=0;i<attrs.getAttributeCount();i++) {
//            System.out.println(attrs.getAttributeName(i)+"=="+attrs.getAttributeValue(i));
//        }
//3.使用系统控件
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAttribute);
//        for (int i = 0; i < typedArray.getIndexCount(); i++) {
//            int index = typedArray.getIndex(i);
//
//            switch (index) {
//                case R.styleable.MyAttribute_my_age:
//                    myAge = typedArray.getInt(index, 0);
//                    break;
//                case R.styleable.MyAttribute_my_name:
//                    myName = typedArray.getString(index);
//                    break;
//                case R.styleable.MyAttribute_my_bg:
//                    Drawable drawable = typedArray.getDrawable(index);
//                    BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//                    myBg = bitmapDrawable.getBitmap();
//                    break;
//            }
//        }
//        typedArray.recycle();//回收

