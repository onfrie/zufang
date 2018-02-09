package cn.com.example.haitu.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.com.example.haitu.R;
import cn.com.example.haitu.model.res.FloorRes;

/**
 * Created by Dell on 2018/2/8.
 */

public class FloorAdapter extends BaseQuickAdapter<FloorRes, BaseViewHolder> {

    public FloorAdapter(int layoutResId, @Nullable List<FloorRes> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FloorRes item) {
        helper.setText(R.id.text,item.getFloor());
        helper.getView(R.id.text).setSelected(item.isSelect());
    }
}
