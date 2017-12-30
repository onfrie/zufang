package cn.com.example.haitu.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filterable;

import java.util.List;

/** 
 * description: RecyclerView通用adapter
 * autour: 谭祖爱
 * date: 2017/7/15 15:23
 * update: 2017/7/15
 * version: 1.0.1
*/
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements
        Filterable
{
    private Context mContext;
    private int mLayoutId;
    private List<T> mDatas;
    private LayoutInflater mInflater;

    public CommonAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }
    public void upDateList(List<T> datas){
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        ViewHolder viewHolder = ViewHolder.createViewHolder(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        convert(holder, mDatas.get(position),position);
    }

    public abstract void convert(ViewHolder holder, T t, int i);

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

}
