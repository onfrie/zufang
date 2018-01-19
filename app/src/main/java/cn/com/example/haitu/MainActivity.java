package cn.com.example.haitu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyw.horrarndoo.sdk.base.activity.BaseCompatActivity;
import com.zyw.horrarndoo.sdk.utils.ToastUtils;

import cn.com.example.haitu.ui.fragment.FragmentHome;
import cn.com.example.haitu.ui.fragment.FragmentHot;
import cn.com.example.haitu.ui.fragment.FragmentMe;
import cn.com.example.haitu.ui.fragment.FragmentTalk;

public class MainActivity extends BaseCompatActivity {


    private FragmentManager fManager;

    private FragmentHome fragment_home;
    private FragmentHot fragment_hot;
    private FragmentTalk fragment_talk;
    private FragmentMe fragment_me;
    private ImageView iv_menu_home;
    private TextView tv_menu_home;
    private ImageView iv_menu_hot;
    private TextView tv_menu_hot;
    private ImageView iv_menu_talk;
    private TextView tv_menu_talk;
    private ImageView iv_menu_me;
    private TextView tv_menu_me;
    private ImageView mIv_center;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        clickMenu(findViewById(R.id.ll_menu_home));
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initViews();
        clickMenu(findViewById(R.id.ll_menu_home));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private void initViews() {
        fManager = getSupportFragmentManager();

        iv_menu_home = (ImageView)findViewById(R.id.iv_menu_home);
        tv_menu_home = (TextView)findViewById(R.id.tv_menu_home);

        iv_menu_hot = (ImageView)findViewById(R.id.iv_menu_hot);
        tv_menu_hot = (TextView)findViewById(R.id.tv_menu_hot);

        iv_menu_talk = (ImageView)findViewById(R.id.iv_menu_talk);
        tv_menu_talk = (TextView)findViewById(R.id.tv_menu_talk);

        iv_menu_me = (ImageView)findViewById(R.id.iv_menu_me);
        tv_menu_me = (TextView)findViewById(R.id.tv_menu_me);
        mIv_center = (ImageView) findViewById(R.id.iv_menu_center);
        mIv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast("哈哈");
            }
        });
    }

    /**
     *
     * @param v
     */
    public void clickMenu(View v){
        FragmentTransaction trans = fManager.beginTransaction();
        int vID = v.getId();

        setMenuStyle(vID);

        hideFrament(trans);

        setFragment(vID,trans);
        trans.commit();
    }

    /**
     *
     * @param trans
     */
    private void hideFrament(FragmentTransaction trans) {
        if(fragment_home!=null){
            trans.hide(fragment_home);
        }
        if(fragment_hot!=null){
            trans.hide(fragment_hot);
        }
        if(fragment_talk!=null){
            trans.hide(fragment_talk);
        }
        if(fragment_me!=null){
            trans.hide(fragment_me);
        }
    }

    /**
     *
     * @param vID
     * @param
     */
    private void setMenuStyle(int vID) {
//
//        if(vID==R.id.ll_menu_center){
//            ToastUtils.showToast("哈哈");
//        }

        if(vID==R.id.ll_menu_home){
            iv_menu_home.setImageDrawable(getResources().getDrawable(R.drawable.home_blue_icn));
            tv_menu_home.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            iv_menu_home.setImageDrawable(getResources().getDrawable(R.drawable.home_white_icn));
            tv_menu_home.setTextColor(getResources().getColor(R.color.ff444444));
        }

        if(vID==R.id.ll_menu_hot){
            iv_menu_hot.setImageDrawable(getResources().getDrawable(R.drawable.house_blue_icn));
            tv_menu_hot.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            iv_menu_hot.setImageDrawable(getResources().getDrawable(R.drawable.house_white_icn));
            tv_menu_hot.setTextColor(getResources().getColor(R.color.ff444444));
        }

        if(vID==R.id.ll_menu_talk){
            iv_menu_talk.setImageDrawable(getResources().getDrawable(R.drawable.rental_blue_icn));
            tv_menu_talk.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            iv_menu_talk.setImageDrawable(getResources().getDrawable(R.drawable.rental_white_icn));
            tv_menu_talk.setTextColor(getResources().getColor(R.color.ff444444));
        }

        if(vID==R.id.ll_menu_me){
            iv_menu_me.setImageDrawable(getResources().getDrawable(R.drawable.my_blue_icn_));
            tv_menu_me.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {
            iv_menu_me.setImageDrawable(getResources().getDrawable(R.drawable.my_white_icn));
            tv_menu_me.setTextColor(getResources().getColor(R.color.ff444444));
        }
    }

    /**
     * ����Fragment
     * @param vID
     * @param trans
     */
    private void setFragment(int vID,FragmentTransaction trans) {
        switch (vID) {
            case R.id.ll_menu_home:
                if(fragment_home==null){
                    fragment_home = new FragmentHome();
                    trans.add(R.id.content, fragment_home);
                }else{
                    trans.show(fragment_home);
                }
                break;
            case R.id.ll_menu_hot:
                if(fragment_hot==null){
                    fragment_hot = new FragmentHot();
                    trans.add(R.id.content, fragment_hot);
                }else{
                    trans.show(fragment_hot);
                }
                break;
            case R.id.ll_menu_talk:
                if(fragment_talk==null){
                    fragment_talk = new FragmentTalk();
                    trans.add(R.id.content, fragment_talk);
                }else{
                    trans.show(fragment_talk);
                }
                break;
            case R.id.ll_menu_me:
                if(fragment_me==null){
                    fragment_me = new FragmentMe();
                    trans.add(R.id.content, fragment_me);
                }else{
                    trans.show(fragment_me);
                }
                break;
            default:
                break;
        }
    }
}
