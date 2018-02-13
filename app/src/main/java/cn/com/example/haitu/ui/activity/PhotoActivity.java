package cn.com.example.haitu.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.example.haitu.R;
import cn.com.example.haitu.ui.widgets.PopupWindow.CommonPopupWindow;
import cn.com.example.haitu.utils.CommonUtil;
import cn.com.example.haitu.utils.GridViewAdapter;
import cn.com.example.haitu.utils.ToastUtils;

/**
 * Created by yt on 2018/2/12.
 */

public class PhotoActivity extends TakePhotoActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    @BindView(R.id.title_left)
    ImageView mTitleLeft;
    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.title_menu)
    ImageView mTitleMenu;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    @BindView(R.id.gridview)
    GridView mGridview;
    @BindView(R.id.pthoto_ll)
    LinearLayout mPthotoLl;



    ArrayList image = new ArrayList<>();
    private GridViewAdapter mGridViewAdapter;
    private long lastClickTime = 0;
    public static final int MIN_CLICK_DELAY_TIME = 500;
    private TakePhoto mTakePhoto;
    private CommonPopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_layout);
        ButterKnife.bind(this);
        getPhotoData();
    }

    private void getPhotoData() {
        mTakePhoto = getTakePhoto();
        mGridViewAdapter = new GridViewAdapter(this, image);
        mGridview.setAdapter(mGridViewAdapter);
        mGridview.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_camera:
                File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists())file.getParentFile().mkdirs();
                Uri imageUri = Uri.fromFile(file);
                mTakePhoto.onPickFromCapture(imageUri);
                mPopupWindow.dismiss();
                break;
            case R.id.photo_ima:
                mTakePhoto.onPickFromGallery();
                mPopupWindow.dismiss();
                break;
            case R.id.photo_back:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            if (position == getDataSize()) {//点击“+”号位置添加图片
//                mTakePhoto.onPickFromGallery();
                showPhoto();
            } else {//点击图片删除
                image.remove(position);
            }
            mGridViewAdapter.setList(image);
        }
    }

    private void showPhoto() {
        View upView = LayoutInflater.from(this).inflate(R.layout.popup_down, null);
        CommonUtil.measureWidthAndHeight(upView);
        mPopupWindow = new CommonPopupWindow.Builder(this)
                //设置PopupWindow布局
                .setView(R.layout.popup_down)
                //设置宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                        upView.getMeasuredHeight())
                //设置动画
                .setAnimationStyle(R.style.AnimUp)
                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setBackGroundLevel(0.5f)
                //设置PopupWindow里的子View及点击事件
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {
                        view.findViewById(R.id.photo_camera).setOnClickListener(PhotoActivity.this);
                        view.findViewById(R.id.photo_ima).setOnClickListener(PhotoActivity.this);
                        view.findViewById(R.id.photo_back).setOnClickListener(PhotoActivity.this);
                        ToastUtils.showToast("121212");
//                        switch (layoutResId) {
//                            case R.id.photo_camera:
//                                File file=new File(Environment.getExternalStorageDirectory(), "/temp/"+System.currentTimeMillis() + ".jpg");
//                                if (!file.getParentFile().exists())file.getParentFile().mkdirs();
//                                Uri imageUri = Uri.fromFile(file);
//                                mTakePhoto.onPickFromCapture(imageUri);
//                                break;
//                            case R.id.photo_ima:
//                                mTakePhoto.onPickFromGallery();
//                                break;
//                            case R.id.photo_back:
//                                break;
//                        }
                    }
                })
                //设置外部是否可点击 默认是true
                .setOutsideTouchable(true)
                //开始构建
                .create();
//弹出PopupWindow
        mPopupWindow.showAsDropDown(mPthotoLl);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        Log.i("takeCancel", image.get(0).toString());
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        Log.i("takeFail", image.get(0).toString());
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        image.add(result.getImage());
        mGridViewAdapter.notifyDataSetChanged();
        Log.i("result", image.get(0).toString());
    }

    private int getDataSize() {
        return image == null ? 0 : image.size();
    }

}
