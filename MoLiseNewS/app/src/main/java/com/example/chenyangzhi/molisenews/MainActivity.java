package com.example.chenyangzhi.molisenews;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.chenyangzhi.molisenews.Fragment.FirstpageFragement;
import com.example.chenyangzhi.molisenews.adapter.MainTabAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    /**
     * 1.闪屏页
     * 2.页面加载项
     * 3.ToolBar
     * 4.Tablayout
     * 5.viewPager+fragment
     * 6.图片缓冲框架Fresco
     * 7.SwipeRefreshLayout
     * 8.RecycleView
     * 9.OKHTTP网络请求框架，网络接口数据解析
     * 10.JSON数据解析，数据获取方式
     * 11.WebView的使用
     * 12.Bannar实现轮播图
     * 13.接口回调
     * 14.Activity的动画切换
     * @author chenyangzhi
     *
     */
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private int position;
    private List<FirstpageFragement> fragmnetList;
    private  String[] listTitle;

    private FirstpageFragement mFirstFragment;
    private MainTabAdapter mMaintabAaapter;//标题适配器
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化fresco必须初始化后才能使用SimpleDraweeView组件 用Jsoup抓取网页
        Fresco.initialize(this);
        initView();
        initData();
        initListener();
    }
//    初始化布局
    private void initView(){
        mTabLayout=(TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager) findViewById(R.id.viewPager);
    }
//    初始化数据
    private void initData(){
        listTitle= getResources().getStringArray(R.array.title);
        fragmnetList=new ArrayList<>();
        for (int i = 0; i <listTitle.length ; i++) {
            mFirstFragment=new FirstpageFragement();
            fragmnetList.add(mFirstFragment);
        }
        mFragmentManager=getSupportFragmentManager();
        mMaintabAaapter=new MainTabAdapter(mFragmentManager,fragmnetList,listTitle);
        viewPager.setAdapter(mMaintabAaapter);
        mTabLayout.setupWithViewPager(viewPager);
    }
    private void initListener(){
        //    初始化监听事件
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                Log.d("","position=_______________"+position);
                FirstpageFragement fragment=fragmnetList.get(position);
               if(fragment!=null){
                   fragment.setPosition(position);
               }
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
