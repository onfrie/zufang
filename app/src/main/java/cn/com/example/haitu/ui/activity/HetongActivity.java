package cn.com.example.haitu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.model.TResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.example.haitu.R;
import cn.com.example.haitu.flexbox.interfaces.OnFlexboxSubscribeListener;
import cn.com.example.haitu.flexbox.widget.TagFlowLayout;
import cn.com.example.haitu.flexlayout.StringTagAdapter;
import cn.com.example.haitu.ui.widgets.PopupWindow.CommonPopupWindow;
import cn.com.example.haitu.utils.GridViewAdapter;

/**
 * Created by Dell on 2017/12/24.
 */

public class HetongActivity extends TakePhotoActivity implements AdapterView.OnItemClickListener {
//    @BindView(R.id.gridview)
//    GridView mGridview;f

    //    ArrayList<TImage> images = new ArrayList<>();
    ArrayList image = new ArrayList<>();
    public static final int MIN_CLICK_DELAY_TIME = 500;
    @BindView(R.id.incidentals_add)
    ImageView incidentalsAdd;
    private long lastClickTime = 0;
    private GridViewAdapter mGridViewAdapter;
    private GridView mViewById;
    private ImageView mIncidentalsAdd;

//    private GridView mViewById;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_details);
        ButterKnife.bind(this);
        initPhoto();
    }

    private void initPhoto() {
        mViewById = (GridView) findViewById(R.id.gridview);
        mIncidentalsAdd = (ImageView) findViewById(R.id.incidentals_add);
        mGridViewAdapter = new GridViewAdapter(this, image);
        mViewById.setAdapter(mGridViewAdapter);
        mViewById.setOnItemClickListener(this);
        mIncidentalsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop(view);
            }
        });
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
        Log.i("result", image.get(0).toString());
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


    private void showPop(View view) {
        CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
                //设置PopupWindow布局
                .setView(R.layout.view_flexlayout)
                //设置宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                //设置动画
                .setAnimationStyle(R.style.AnimDown)
                //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                .setBackGroundLevel(0.5f)

                //设置外部是否可点击 默认是true
                .setOutsideTouchable(true)
                //开始构建
                .create();


        View contentView = popupWindow.getContentView();
        TagFlowLayout flowLayout = contentView.findViewById(R.id.flow_layout);
        final Button btnCount = (Button) contentView.findViewById(R.id.btn_get_count);

        List<String> sourceData = new ArrayList<>();
        sourceData.add("程序员");
        sourceData.add("设计师");
        sourceData.add("产品经理");
        sourceData.add("运营");
        sourceData.add("商务");
        sourceData.add("人事经理");
        sourceData.add("项目经理");
        sourceData.add("客户代表");
        sourceData.add("技术主管");
        sourceData.add("测试工程师");
        sourceData.add("前端工程师");
        sourceData.add("Java工程师");
        sourceData.add("Android工程师");
        sourceData.add("iOS工程师");

        List<String> selectItems = new ArrayList<>();
        selectItems.add("客户代表");
        selectItems.add("Java工程师");


        final StringTagAdapter adapter = new StringTagAdapter(HetongActivity.this, sourceData, selectItems);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                btnCount.setText("已选择" + selectedItem.size() + "个");
            }
        });
        flowLayout.setAdapter(adapter);
        btnCount.setText("已选择" + adapter.getSelectedList().size() + "个");
        contentView.findViewById(R.id.btn_switch_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> data = new ArrayList<>();
                data.add("客户代表");
                data.add("Java工程师");

                List<String> selectList = new ArrayList<>();
                selectList.add("客户代表");
                adapter.setSource(data);
                adapter.setSelectItems(selectList);
                adapter.notifyDataSetChanged();
            }
        });


        popupWindow.showAsDropDown(view);
    }
}