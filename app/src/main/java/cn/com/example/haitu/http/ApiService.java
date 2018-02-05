package cn.com.example.haitu.http;

import cn.com.example.haitu.model.CityListRes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by zoutongbin on 2017/6/26.
 */

public interface ApiService {

    /**
     * 获取特定类型账单平台列表
     *
     * @param type type=0 信用卡, 1 消费分期, 2 现金贷
     * @return
     */
    @GET("rest/bill/platform/{type}")
    Call<Object> getBillPlatform(@Path("type") int type);


    @POST("api/BaseData/QueryCity")
    Call<CityListRes> postCityList();

}
