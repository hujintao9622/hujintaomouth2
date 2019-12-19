package com.bawei.hujintaomouth2.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.hujintaomouth2.R;
import com.bawei.hujintaomouth2.widget.App;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.Map;

/**
 * 功能:  页面
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 6:50
 */
public class NetUtil {
    private static NetUtil netUtil;
    private final RequestQueue requestQueue;

    private NetUtil(){
        requestQueue = Volley.newRequestQueue(App.app);
    }

    public static NetUtil getInstance() {
        if (netUtil==null){
            synchronized (NetUtil.class){
                if (netUtil== null){
                    netUtil=new NetUtil();
                }
            }
        }
        return netUtil;
    }
    //Get
    public void getJsonGet(String httpUrl, final MyCallback myCallback){
        StringRequest stringRequest=new StringRequest(StringRequest.Method.GET, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallback.onGetJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallback.onError(error);
            }
        });
        requestQueue.add(stringRequest);
    };
    //Post
    public void getJsonPost(String httpUrl, final Map<String,String> map, final MyCallback myCallback){
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, httpUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                myCallback.onGetJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                myCallback.onError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        requestQueue.add(stringRequest);
    };
    //获取图片
    public void getPhoto(String url, ImageView img){
        Glide.with(img).load(url)
                .placeholder(R.mipmap.s1)
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img);
    }
    //是否有网
    public boolean hasNet(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null&&networkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }
    }
    //是否是Wifi
    public boolean isWifi(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null&&networkInfo.isAvailable()&&networkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
            return true;
        }else {
            return false;
        }
    }
    //是否是Mobile
    public boolean isMobile(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null&&networkInfo.isAvailable()&&networkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
            return true;
        }else {
            return false;
        }
    }
    public interface MyCallback{
        void onGetJson(String json);
        void onError(Throwable e);
    }
}
