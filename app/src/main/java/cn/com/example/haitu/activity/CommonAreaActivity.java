package cn.com.example.haitu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.example.haitu.R;
import cn.com.example.haitu.activity.interfaces.DialogListener;
import cn.com.example.haitu.http.ApiCallBack;
import cn.com.example.haitu.http.HttpHelper;
import cn.com.example.haitu.model.CityListRes;
import cn.com.example.haitu.utils.DialogUtils;
import cn.com.example.haitu.utils.ToastUtils;
import retrofit2.Call;

public class CommonAreaActivity extends BaseCompatActivity {

    @BindView(R.id.title_left)
    ImageView titleLeft;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_menu)
    ImageView titleMenu;
    @BindView(R.id.ll_city)
    LinearLayout llCity;
    @BindView(R.id.adress_text)
    TextView adressText;

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_area;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_menu, R.id.ll_city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_city:
                resCity();
                break;
                default:
                    break;
        }
    }

    private void resCity() {
        Call<CityListRes> call = HttpHelper.getApiService().postCityList();
        call.enqueue(new ApiCallBack<CityListRes>() {
            @Override
            public void onSuccess(CityListRes result) {
                ToastUtils.showLongToast(result.toString());
                ArrayList<String> cityList = new ArrayList<>();
                for (CityListRes.NumberDataBean numberDataBean : result.getNumberData()) {
                    cityList.add(numberDataBean.getRegionName());

                }

                DialogUtils.showSelectDialog(CommonAreaActivity.this, "银行", cityList.toArray(new String[cityList.size()]), new DialogListener() {
                    @Override
                    public void handle(String text) {
                        titleText.setText(text);
//                        lightBtn();
                    }
                });
            }

            @Override
            public void onFail(int code, String msg) {
                super.onFail(code, msg);
            }
        });
    }
        }



