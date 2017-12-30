package cn.com.example.haitu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.example.haitu.R;
import cn.com.example.haitu.utils.GridViewAdapter;

/**
 * Created by Dell on 2017/12/24.
 */

public class HetongActivity extends TakePhotoActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.gridview)
    GridView mGridview;

//    ArrayList<TImage> images = new ArrayList<>();
    ArrayList image = new ArrayList<>();
    public static final int MIN_CLICK_DELAY_TIME = 500;
    private long lastClickTime = 0;
    private GridViewAdapter mGridViewAdapter;
//    private GridView mViewById;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_details);
        ButterKnife.bind(this);
        initPhoto();
    }

    private void initPhoto() {
//        mViewById = (GridView) findViewById(R.id.gridview);
        mGridViewAdapter = new GridViewAdapter(this, image);
        mGridview.setAdapter(mGridViewAdapter);
        mGridview.setOnItemClickListener(this);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        image.add(result.getImage());
        Log.i("result",image.get(0).toString());
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TakePhoto takePhoto = getTakePhoto();
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            if (position == getDataSize()) {//点击“+”号位置添加图片
                takePhoto.onPickFromGallery();
            } else {//点击图片删除
                image.remove(position);
            }
            mGridViewAdapter.setList(image);
        }
    }
    private int getDataSize() {
        return image == null ? 0 : image.size();
    }
}