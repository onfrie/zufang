package cn.com.example.haitu.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.com.example.haitu.R;
import cn.com.example.haitu.flexbox.interfaces.OnFlexboxSubscribeListener;
import cn.com.example.haitu.flexbox.widget.TagFlowLayout;
import cn.com.example.haitu.flexlayout.StringTagAdapter;
import cn.com.example.haitu.model.res.BaseDataQueryRes;
import cn.com.example.haitu.utils.ToastUtils;

public class CommonFacilityActivity extends BaseCompatActivity {


    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_menu)
    ImageView titleMenu;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.ll_device)
    TagFlowLayout llDevice;
    private ArrayList<BaseDataQueryRes.NumberDataBean.PeipeiBean> mPeipei;

    int floorInt = 0;
    private ArrayList<Boolean> mFloorSelect;
    private BaseDataQueryRes mBaseDataQueryRes;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mPeipei = getIntent().getParcelableArrayListExtra("peipei");

        floorInt = mPeipei.size();

        List<String> sourceData = new ArrayList<>();
        for (BaseDataQueryRes.NumberDataBean.PeipeiBean peipeiBean : mPeipei) {
            sourceData.add(peipeiBean.getName());
        }

        List<String> selectItems = new ArrayList<>();
        selectItems.add("客户代表");
        selectItems.add("Java工程师");


        final StringTagAdapter adapter = new StringTagAdapter(mContext, sourceData, selectItems);
        adapter.setOnSubscribeListener(new OnFlexboxSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {
                ToastUtils.showToast("已选择" + selectedItem.size() + "个");
            }
        });
        llDevice.setAdapter(adapter);
        ToastUtils.showToast("已选择" + adapter.getSelectedList().size() + "个");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_facility;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
