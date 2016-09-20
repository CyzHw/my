package com.example.chenyangzhi.molisenews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chenyangzhi.molisenews.Fragment.FirstpageFragement;

import java.util.List;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
//Jsoup去抓取网页
public class MainTabAdapter extends FragmentPagerAdapter {
//    tabLayout的管理适配器
    private String[] listTitle;
    private List<FirstpageFragement>fragmentList;
    public MainTabAdapter(FragmentManager fm,List<FirstpageFragement>fragmentList,String[] listTitle) {
        super(fm);
        this.fragmentList=fragmentList;
        this.listTitle=listTitle;
    }
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle[position];
    }
}
