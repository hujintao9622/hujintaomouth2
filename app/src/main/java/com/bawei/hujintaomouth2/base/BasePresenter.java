package com.bawei.hujintaomouth2.base;

/**
 * 功能:  presenter基类
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 4:04
 */
public abstract class BasePresenter<V> {
    protected V view;

    public void attach(V view) {
        this.view = view;
    }
    public void detach(){
        view=null;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
