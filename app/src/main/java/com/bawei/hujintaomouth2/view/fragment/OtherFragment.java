package com.bawei.hujintaomouth2.view.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bawei.hujintaomouth2.R;
import com.bawei.hujintaomouth2.base.BaseFragment;
import com.bawei.hujintaomouth2.base.BasePresenter;

/**
 * 功能:  其他页面
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 4:02
 */
public class OtherFragment extends BaseFragment{

    private TextView other;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initView(View view) {
        //找控件
        other = view.findViewById(R.id.other);
    }

    @Override
    protected void initData() {
        String key = getArguments().getString("key");
        other.setText(key);
    }

    public static OtherFragment getInstance(String value) {
        OtherFragment otherFragment = new OtherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key",value);
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
