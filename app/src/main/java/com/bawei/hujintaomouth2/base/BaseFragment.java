package com.bawei.hujintaomouth2.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 功能:  Fragment基类
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 3:55
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    protected P mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getContext(),layoutId(),null);
        initView(view);
        mPresenter=providePresenter();
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        return view;
    }

    protected abstract P providePresenter();

    protected abstract int layoutId();

    protected abstract void initView(View view);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
