package cn.com.example.haitu.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;

import java.util.ArrayList;
import java.util.List;

import cn.com.example.haitu.R;

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<TImage> list = new ArrayList<TImage>();

    public GridViewAdapter() {
        super();
    }

    /**
     * 获取列表数据
     *
     * @param list
     */
    public void setList(List list) {
//        Log.e(" 4444 ", list.size() + "");
        //this.list.clear();
        //this.list.addAll(list);
        this.list = (ArrayList<TImage>) list;
        this.notifyDataSetChanged();
//        Log.e(" 3333 ", this.list.size() + "");
    }

    public GridViewAdapter(Context mContext, ArrayList<TImage> list) {
        super();
        this.mContext = mContext;
        this.list =  list;
//        Log.e(" 2222 ", list.size() + "");
    }

    @Override
    public int getCount() {
        Log.e("  ", list.size() + "");
        if (list == null) {
            return 1;
        } else if (list.size() == 3) {
            return 3;
        } else {
            return list.size() + 1;
        }
    }

    @Override
    public Object getItem(int position) {
        if (list != null
                && list.size() == 3) {
            return list.get(position);
        } else if (list == null || position - 1 < 0
                || position > list.size()) {
            return null;
        } else {
            return list.get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_publish, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.item_grid_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (isShowAddItem(position)) {
//            Glide.with(mContext).load(R.drawable.btn_add_pic).asBitmap().into(holder.iv);
			holder.iv.setImageResource(R.drawable.ic_addition);
//			holder.iv.setBackgroundResource(R.color.bg_gray);

        } else {
            TImage tImage = list.get(position);
            Glide.with(mContext).load(tImage.getOriginalPath()).asBitmap().into(holder.iv);
//            Glide.with(this).load(new File(images.get(i).getCompressPath())).into(imageView1);
//			holder.iv.setImageResource(list.get(position));  //这里用gild加载
//			holder.iv.setBackgroundResource(R.color.bg_gray);
            notifyDataSetChanged();

        }
        return convertView;
    }

    private boolean isShowAddItem(int position) {
        int size = list == null ? 0 : list.size();
        return position == size;
    }

    class ViewHolder {
        ImageView iv;
    }

}
