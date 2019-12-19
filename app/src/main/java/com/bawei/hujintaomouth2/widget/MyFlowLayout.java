package com.bawei.hujintaomouth2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.hujintaomouth2.model.bean.ShopBean;

/**
 * 功能:  流式布局
 * 作者:  胡锦涛
 * 时间:  2019/12/19 0019 下午 7:15
 */
public class MyFlowLayout extends ViewGroup {

    private int widthPixels;

    public MyFlowLayout(Context context) {
        super(context);
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        widthPixels = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //间隙
        int wSpace=30;
        int hSpace=20;
        int left=wSpace;
        int top=hSpace;
        int right=0;
        int bottom=0;
        //获取子控件数量
        int childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            //获取子控件
            View child = getChildAt(i);
            //测量子控件
            child.measure(0,0);
            //子控件的宽高
            int measuredWidth = child.getMeasuredWidth();
            int measuredHeight = child.getMeasuredHeight();
            right=left+measuredWidth;
            if (right<widthPixels){
                bottom=top+measuredHeight;
            }else {
                left=wSpace;
                top=bottom+hSpace;
                right=left+measuredWidth;
                bottom=top+measuredHeight;
            }
            //子控件位置
            child.layout(left,top,right,bottom);
            //下个子控件的左位置
            left=right+wSpace;
        }
    }
    //添加文本控件
    public void addTag(String tag){
        final TextView textView = new TextView(getContext());
        textView.setText(tag);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTagClickListener != null) {
                    onTagClickListener.onTagClick(textView.getText().toString());
                }
            }
        });
        addView(textView);
    }
    OnTagClickListener onTagClickListener;

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }

    //点击接口
    public interface OnTagClickListener{
        void onTagClick(String tag);
    }
}
