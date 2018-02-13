package cn.com.example.haitu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.com.example.haitu.R;
import cn.com.example.haitu.adapter.FloorAdapter;
import cn.com.example.haitu.flexbox.interfaces.OnFlexboxSubscribeListener;
import cn.com.example.haitu.flexbox.widget.TagFlowLayout;
import cn.com.example.haitu.flexlayout.StringTagAdapter;
import cn.com.example.haitu.http.ApiCallBack;
import cn.com.example.haitu.http.HttpHelper;
import cn.com.example.haitu.interfaces.DialogListener;
import cn.com.example.haitu.model.req.BaseDataQueryReq;
import cn.com.example.haitu.model.req.SaveHouseReq;
import cn.com.example.haitu.model.res.BaseDataQueryRes;
import cn.com.example.haitu.model.res.FloorRes;
import cn.com.example.haitu.ui.activity.CommonFacilityActivity;
import cn.com.example.haitu.ui.widgets.PopupWindow.CommonPopupWindow;
import cn.com.example.haitu.utils.AppUtil;
import cn.com.example.haitu.utils.CommonUtil;
import cn.com.example.haitu.utils.DialogUtils;
import cn.com.example.haitu.utils.ToastUtils;
import retrofit2.Call;

public class CommonAreaActivity extends BaseCompatActivity implements View.OnClickListener {

    @BindView(R.id.title_text)
    TextView mTitleText;
    @BindView(R.id.title_menu)
    ImageView mTitleMenu;
    @BindView(R.id.ll_city)
    LinearLayout mLlCity;
    @BindView(R.id.commonArea_city_tv)
    TextView mCommonAreaCityTv;
    @BindView(R.id.ll_view)
    LinearLayout llView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_title)
    LinearLayout mLlTitle;
    @BindView(R.id.shangquan_text)
    TextView mShangquanText;
    @BindView(R.id.adress_text)
    TextView mAdressText;
    @BindView(R.id.add_house_tvfloor)
    TextView mAddHouseTvfloor;
    @BindView(R.id.add_house_floor)
    LinearLayout mAddHouseFloor;
    @BindView(R.id.commmon_area_next)
    TextView mCommmonAreaNext;

    private List<BaseDataQueryRes.NumberDataBean.CityBean> mCity;
    private BaseDataQueryRes mBaseDataQueryRes;
    FloorAdapter mFloorAdapter;
    private ArrayList<FloorRes> mFloorList;
    private EditText mAddFloorAll;
    private EditText mAddFloorNum;
    private SaveHouseReq mSaveHouseReq;
    private CommonPopupWindow mPopupWindow;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mSaveHouseReq = new SaveHouseReq();
        resBaseDataQuery();
        //        initDatas(10);
        initAdapter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.add_house_activity;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        resBaseDataQuery();
////        initDatas(10);
//        initAdapter();
//
//    }

    private void initDatas(int floor) {
        mFloorList = new ArrayList<>();
        for (int i = 0; i <= floor; i++) {
            FloorRes floorRes = new FloorRes();
            if (i == 0) {
                floorRes.setFloor("地下室");
            } else {
                floorRes.setFloor(i + "楼");
            }
            floorRes.setSelect(true);
            mFloorList.add(floorRes);
        }
    }

    private void initAdapter() {
        mFloorAdapter = new FloorAdapter(R.layout.item_grid, mFloorList);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        recyclerView.setAdapter(mFloorAdapter);
        mFloorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tv = (TextView) view.findViewById(R.id.text);
                tv.setSelected(!tv.isSelected());
                mFloorList.get(position).setSelect(tv.isSelected());
                String s = "";
                for (FloorRes floorRes : mFloorList) {
                    s = s + floorRes.toString();
                }
                ToastUtils.showToast(s + "");
            }
        });
    }

    @OnClick({R.id.title_menu, R.id.ll_city, R.id.commmon_area_next, R.id.add_house_floor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_city:
                resCity();
//                showPop(view);
                break;
            case R.id.commmon_area_next:
                List<BaseDataQueryRes.NumberDataBean.PeipeiBean> peipei = mBaseDataQueryRes.getNumberData().getPeipei();
                Intent intent = new Intent(mContext, CommonFacilityActivity.class);
                intent.putParcelableArrayListExtra("peipei", (ArrayList<? extends Parcelable>) peipei);
                intent.putExtra("bean", mSaveHouseReq);
                startActivity(intent);
                break;
//            case R.id.ll_features:
//                showPop(view);
//                resBaseDataQuery();
//                break;
            case R.id.add_house_floor:
                showFloorPop();
                break;
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
                for (BaseDataQueryRes.NumberDataBean.CityBean numberDataBean : mCity) {
                    if (numberDataBean.getRegionName().equals(text)){
                        mSaveHouseReq.setCity(String.valueOf(numberDataBean.getId()));
                        ToastUtils.showToast(String.valueOf(numberDataBean.getId()));
                    }
                }

                mSaveHouseReq.setCityName(text);
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
                mBaseDataQueryRes = result;
                mCity = result.getNumberData().getCity();
            }

            @Override
            public void onFail(int code, String msg) {
                super.onFail(code, msg);
                DialogUtils.dismissLoading();
            }
        });
    }

    private void showFloorPop() {

        View upView = LayoutInflater.from(this).inflate(R.layout.view_flexlayout, null);
        CommonUtil.measureWidthAndHeight(upView);
        mPopupWindow = new CommonPopupWindow.Builder(this)
                //设置PopupWindow布局
                .setView(R.layout.view_add_floor)
                //设置宽高
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.AnimUp)
                .create();


        View contentView = mPopupWindow.getContentView();
        contentView.findViewById(R.id.add_floor_save).setOnClickListener(this);
        contentView.findViewById(R.id.add_floor_back).setOnClickListener(this);
        mAddFloorAll = (EditText) contentView.findViewById(R.id.add_floor_all);
        AppUtil.showSoftInput(this);
        mAddFloorNum = (EditText) contentView.findViewById(R.id.add_floor_num);
        mPopupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_floor_save:
                if (!mAddFloorAll.getText().toString().isEmpty()){
                    mSaveHouseReq.setAllFloor(String.valueOf(mAddFloorAll.getText()));
                }else {
                    ToastUtils.showToast("请输入总楼层");
                    return;
                }

                if (!mAddFloorNum.getText().toString().isEmpty()){
                    mSaveHouseReq.setNowFloor(String.valueOf(mAddFloorNum.getText()));
                }else {
                    ToastUtils.showToast("请输入当前楼层");
                    return;
                }
                mAddHouseTvfloor.setText(String.valueOf(mAddFloorNum.getText())+"/"+String.valueOf(mAddFloorAll.getText()));
                mPopupWindow.dismiss();
                break;
            case R.id.add_floor_back:
                mPopupWindow.dismiss();
                break;
        }
    }
}



