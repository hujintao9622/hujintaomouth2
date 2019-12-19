package com.bawei.hujintaomouth2.contract;

import com.bawei.hujintaomouth2.model.bean.PhoneBean;
import com.bawei.hujintaomouth2.model.bean.ShopBean;

/**
 * 功能:  契约
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 4:02
 */
public interface IHomeContract {
    interface IView{
        void onListSuccess(ShopBean shopBean);
        void onListFailure(Throwable throwable);
        void onFlowSuccess(PhoneBean phoneBean);
        void onFlowFailure(Throwable throwable);
    }
    interface IPresenter{
        void getHomeData(String param);
        void getFlowData(String param);
    }
    interface IModel{
        void getHomeData(String param,IModelCallback iModelCallback);
        void getFlowData(String param,IModelCallback iModelCallback);
        interface IModelCallback{
            void onListSuccess(ShopBean shopBean);
            void onListFailure(Throwable throwable);
            void onFlowSuccess(PhoneBean phoneBean);
            void onFlowFailure(Throwable throwable);
        }
    }
}
