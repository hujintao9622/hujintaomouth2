package com.bawei.hujintaomouth2.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.bawei.hujintaomouth2.R;
import com.bawei.hujintaomouth2.base.BaseActivity;
import com.bawei.hujintaomouth2.view.fragment.HomeFragment;
import com.bawei.hujintaomouth2.view.fragment.OtherFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager vp;
    private RadioGroup rg;
    private List<Fragment> list=new ArrayList<>();
    @Override
    protected void initData() {
        //设置适配器数据
        HomeFragment home = new HomeFragment();
        OtherFragment bj = OtherFragment.getInstance("北京");
        OtherFragment my = OtherFragment.getInstance("我的");
        list.add(home);
        list.add(bj);
        list.add(my);
        //设置适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    @Override
    protected void initView() {
        //找控件
        vp = findViewById(R.id.ma_vp);
        rg = findViewById(R.id.ma_rg);
        //关联ViewPager和RadioGroup
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rg.check(rg.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ma_rb_home:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.ma_rb_bei:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.ma_rb_my:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
        //设置默认页面
        rg.check(rg.getChildAt(0).getId());
        vp.setCurrentItem(0);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }
}
