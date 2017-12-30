package cn.com.example.haitu.ui.mvp.contract;

import com.zyw.horrarndoo.sdk.base.BasePresenter;
import com.zyw.horrarndoo.sdk.base.IBaseActivity;
import com.zyw.horrarndoo.sdk.base.IBaseModel;

/**
 * Created by yt on 2017/12/26.
 */

public interface HetongContract {

    abstract class HetongPresenter extends BasePresenter<HetongPresenterModel, HetongPresenterView> {

    }

    interface HetongPresenterModel extends IBaseModel {
        /**
         * 相机功能
         */
        public abstract void getPhoto();
    }

    interface HetongPresenterView extends IBaseActivity {

    }
}
