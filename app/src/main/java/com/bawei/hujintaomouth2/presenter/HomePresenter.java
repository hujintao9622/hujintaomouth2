package com.bawei.hujintaomouth2.presenter;

import com.bawei.hujintaomouth2.base.BasePresenter;
import com.bawei.hujintaomouth2.contract.IHomeContract;
import com.bawei.hujintaomouth2.model.HomeModel;
import com.bawei.hujintaomouth2.model.bean.PhoneBean;
import com.bawei.hujintaomouth2.model.bean.ShopBean;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 7:09
 */
public class HomePresenter extends BasePresenter<IHomeContract.IView> implements IHomeContract.IPresenter {

    private HomeModel homeModel;

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }

    @Override
    public void getHomeData(String param) {
        homeModel.getHomeData(param, new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onListSuccess(ShopBean shopBean) {
                view.onListSuccess(shopBean);
            }

            @Override
            public void onListFailure(Throwable throwable) {
                view.onListFailure(throwable);
            }

            @Override
            public void onFlowSuccess(PhoneBean phoneBean) {

            }

            @Override
            public void onFlowFailure(Throwable throwable) {

            }
        });
    }

    @Override
    public void getFlowData(String param) {
        homeModel.getFlowData(param, new IHomeContract.IModel.IModelCallback() {
            @Override
            public void onListSuccess(ShopBean shopBean) {

            }

            @Override
            public void onListFailure(Throwable throwable) {

            }

            @Override
            public void onFlowSuccess(PhoneBean phoneBean) {
                view.onFlowSuccess(phoneBean);
            }

            @Override
            public void onFlowFailure(Throwable throwable) {
                view.onFlowFailure(throwable);
            }
        });
    }
}
