package com.bawei.hujintaomouth2.model;

import com.bawei.hujintaomouth2.contract.IHomeContract;
import com.bawei.hujintaomouth2.model.bean.PhoneBean;
import com.bawei.hujintaomouth2.model.bean.ShopBean;
import com.bawei.hujintaomouth2.util.NetUtil;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 4:08
 */
public class HomeModel implements IHomeContract.IModel {
    @Override
    public void getHomeData(String param, final IModelCallback iModelCallback) {
        try {
            String st="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?&page=1&count=30&keyword="+ URLEncoder.encode(param,"UTF-8");
            NetUtil.getInstance().getJsonGet(st, new NetUtil.MyCallback() {
                @Override
                public void onGetJson(String json) {
                    //解析
                    ShopBean shopBean = new Gson().fromJson(json, ShopBean.class);
                    iModelCallback.onListSuccess(shopBean);
                }

                @Override
                public void onError(Throwable e) {
                    iModelCallback.onListFailure(e);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getFlowData(String param, final IModelCallback iModelCallback) {
        try {
            String st="http://blog.zhaoliang5156.cn/baweiapi/"+ URLEncoder.encode(param,"UTF-8");
            NetUtil.getInstance().getJsonGet(st, new NetUtil.MyCallback() {
                @Override
                public void onGetJson(String json) {
                    //解析
                    PhoneBean phoneBean = new Gson().fromJson(json, PhoneBean.class);
                    iModelCallback.onFlowSuccess(phoneBean);
                }

                @Override
                public void onError(Throwable e) {
                    iModelCallback.onFlowFailure(e);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
