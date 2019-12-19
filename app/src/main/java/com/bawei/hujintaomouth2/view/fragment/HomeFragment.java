package com.bawei.hujintaomouth2.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.hujintaomouth2.R;
import com.bawei.hujintaomouth2.base.BaseFragment;
import com.bawei.hujintaomouth2.contract.IHomeContract;
import com.bawei.hujintaomouth2.model.bean.PhoneBean;
import com.bawei.hujintaomouth2.model.bean.ShopBean;
import com.bawei.hujintaomouth2.presenter.HomePresenter;
import com.bawei.hujintaomouth2.view.activity.SecondActivity;
import com.bawei.hujintaomouth2.view.adapter.MyAdapter;
import com.bawei.hujintaomouth2.widget.MyFlowLayout;

import java.util.List;

/**
 * 功能:  首页
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 4:02
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements IHomeContract.IView {

    private EditText ed;
    private View view;
    private Button bt;
    private MyFlowLayout fl;
    private RecyclerView rc;

    @Override
    protected HomePresenter providePresenter() {
        return new HomePresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        //子控件
        ed = view.findViewById(R.id.home_ed);
        bt = view.findViewById(R.id.home_bt);
        fl = view.findViewById(R.id.home_fl);
        rc = view.findViewById(R.id.home_rc);
        //点击搜索
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = ed.getText().toString().trim();
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(getContext(), "搜索框为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.getHomeData(s);
                fl.addTag(s);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getFlowData("手机");
        mPresenter.getHomeData("手机");
    }

    @Override
    public void onListSuccess(ShopBean shopBean) {
        List<ShopBean.ResultBean> result = shopBean.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rc.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(result);
        //条目点击事件
        myAdapter.setOnItemClicListener(new MyAdapter.OnItemClicListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        rc.setAdapter(myAdapter);
    }

    @Override
    public void onListFailure(Throwable throwable) {
        Log.e("tag",throwable.getMessage());
    }

    @Override
    public void onFlowSuccess(PhoneBean phoneBean) {
        List<String> tags = phoneBean.getTags();
        for (int i=0;i<tags.size();i++){
            fl.addTag(tags.get(i));
        }
    }

    @Override
    public void onFlowFailure(Throwable throwable) {
        Log.e("tag",throwable.getMessage());
    }
}
