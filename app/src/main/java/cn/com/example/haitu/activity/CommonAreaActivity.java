package cn.com.example.haitu.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.example.haitu.R;
import cn.com.example.haitu.flexbox.interfaces.OnFlexboxSubscribeListener;
import cn.com.example.haitu.flexbox.widget.TagFlowLayout;
import cn.com.example.haitu.flexlayout.StringTagAdapter;
import cn.com.example.haitu.http.ApiCallBack;
import cn.com.example.haitu.http.HttpHelper;
import cn.com.example.haitu.interfaces.DialogListener;
import cn.com.example.haitu.model.req.BaseDataQueryReq;
import cn.com.example.haitu.model.res.BaseDataQueryRes;
import cn.com.example.haitu.ui.widgets.PopupWindow.CommonPopupWindow;
import cn.com.example.haitu.utils.CommonUtil;
import cn.com.example.haitu.utils.DialogUtils;
import cn.com.example.haitu.utils.ToastUtils;
import retrofit2.Call;

public class CommonAreaActivity extends BaseCompatActivity {

    //    @BindView(R.id.title_left)
//    ImageView titleLeft;
//    @BindView(R.id.title_text)
//    TextView titleText;
//    @BindView(R.id.title_menu)
//    ImageView titleMenu;
//    @BindView(R.id.ll_city)
//    LinearLayout llCity;
    //    @BindView(R.id.adress_text)
//    TextView adressText;
//    @BindView(R.id.ll_features)
//    LinearLayout llFeatures;
    @BindView(R.id.gv_floor)
    GridView gvFloor;
    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.title_menu)
    ImageView mTitleMenu;
    @BindView(R.id.ll_city)
    LinearLayout mLlCity;
    @BindView(R.id.commonArea_city_tv)
    TextView mCommonAreaCityTv;
    private List<BaseDataQueryRes.NumberDataBean.CityBean> mCity;

    int floorInt = 11;
    private ArrayList<Boolean> mFloorSelect;

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.add_house_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        resBaseDataQuery();

        //新建List
        ArrayList<Map<String, String>> floorList = new ArrayList<>();
        mFloorSelect = new ArrayList<>();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("text", "地下室");
        floorList.add(stringStringHashMap);
        //获取数据
        for (int i = 1; i <= floorInt; i++) {
            
        Map<String, String> map = new HashMap<>();
        map.put("text", i + "楼");
        floorList.add(map);
            mFloorSelect.add(false);
        }
        //新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, floorList, R.layout.item_grid, from, to);
        //配置适配器
        gvFloor.setAdapter(simpleAdapter);
        gvFloor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LogUtils.e("onItemClick");
//                view.setBackgroundResource(R.color.bk_green);
                TextView text = ((TextView) view.findViewById(R.id.text));
                text.setSelected(!text.isSelected());
                mFloorSelect.add(text.isSelected());
                ToastUtils.showToast(i + "楼");
            }
        });
    }

    @OnClick({R.id.title_menu, R.id.ll_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_city:
                resCity();
                break;
//            case R.id.ll_features:
//                showPop(view);
//                resBaseDataQuery();
//                break;
            default:
                break;
        }
    }

    private void showPop(View view) {

        View upView = LayoutInflater.from(this).inflate(R.layout.view_flexlayout, null);
        CommonUtil.measureWidthAndHeight(upView);
        CommonPopupWindow popupWindow = new CommonPopupWindow.Builder(this)
                //设置PopupWindow布局
                .setView(R.layout.view_flexlayout)
                //设置宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
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


        final StringTagAdapter adapter = new StringTagAdapter(mContext, sourceData, selectItems);
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


        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }


    private void resCity() {
        ArrayList<String> cityList = new ArrayList<>();
        for (BaseDataQueryRes.NumberDataBean.CityBean numberDataBean : mCity) {
            cityList.add(numberDataBean.getRegionName());
        }
        DialogUtils.showSelectDialog(CommonAreaActivity.this, "城市", cityList.toArray(new String[cityList.size()]), new DialogListener() {
            @Override
            public void handle(String text) {
                mCommonAreaCityTv.setText(text);
//                        lightBtn();
            }
        });
    }

    private void resBaseDataQuery() {
        DialogUtils.showLoading(mContext, "加载中...");
        BaseDataQueryReq baseDataQueryReq = new BaseDataQueryReq();
        baseDataQueryReq.setTeseorpeibei(0);
        baseDataQueryReq.setType(0);
        Call<BaseDataQueryRes> call = HttpHelper.getApiService().postBaseDataQuery(baseDataQueryReq);
        call.enqueue(new ApiCallBack<BaseDataQueryRes>() {
            @Override
            public void onSuccess(BaseDataQueryRes result) {
                DialogUtils.dismissLoading();
                mCity = result.getNumberData().getCity();
            }

            @Override
            public void onFail(int code, String msg) {
                super.onFail(code, msg);
                DialogUtils.dismissLoading();
            }
        });
    }

}



