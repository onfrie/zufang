package cn.com.example.haitu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.zyw.horrarndoo.sdk.base.fragment.BaseCompatFragment;

import cn.com.example.haitu.R;
import cn.com.example.haitu.ui.activity.HetongActivity;

public class FragmentHome extends BaseCompatFragment {


	private TextView mHomeZuke;

	@Override
	public int getLayoutId() {
		return R.layout.home_fragment;
	}

	@Override
	public void initUI(View view, @Nullable Bundle savedInstanceState) {
		mHomeZuke = (TextView) view.findViewById(R.id.home_zuke);
		mHomeZuke.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getContext(), HetongActivity.class));
			}
		});
	}


}
