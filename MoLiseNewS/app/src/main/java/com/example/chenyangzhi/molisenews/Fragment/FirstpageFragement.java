package com.example.chenyangzhi.molisenews.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chenyangzhi.molisenews.MyDetailActivity;
import com.example.chenyangzhi.molisenews.R;
import com.example.chenyangzhi.molisenews.adapter.FirstPageAdapter;
import com.example.chenyangzhi.molisenews.entity.BannerBean;
import com.example.chenyangzhi.molisenews.entity.ItemData;
import com.example.chenyangzhi.molisenews.utils.JsonUtils;
import com.example.chenyangzhi.molisenews.utils.ProgressDialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
public class FirstpageFragement extends Fragment {
    private  ProgressDialogUtils p;
    private RecyclerView mRecyclerView;
    private List<ItemData>[] data;//保存了所有信息的数组
    private List<ItemData> itemDate;//显示列表加载的数据
    private JsonUtils  jS;
    private FirstPageAdapter pageAdapter;
    private BannerBean banerBean;
    private int max_size =8;//每页最多显示的数量
    private SwipeRefreshLayout swip;//下拉刷新控件
    private int lastitem;
    private int mPosition=0;
    private boolean isReFresh=false;
    private boolean isFirst=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=getActivity().getLayoutInflater().inflate(R.layout.aty_first_fragment
                , (ViewGroup) getActivity().findViewById(R.id.viewPager),false);
        init(v);
        p.showProgressDialog(getActivity(),null);
        jS.getResult();
        return v;
    }
//    初始化所有事物
    private void init(View v){
        p = ProgressDialogUtils.getIntence();
        banerBean=new BannerBean();
        itemDate=new ArrayList<>();
        jS=new JsonUtils(listener);
        mRecyclerView= (RecyclerView)v.findViewById(R.id.recycle);
        swip=(SwipeRefreshLayout) v.findViewById(R.id.swip);
        swip.setColorSchemeColors(Color.RED,Color.GREEN);//设置下拉刷新
        swip.setOnRefreshListener(refreshListener);
        pageAdapter=new FirstPageAdapter(getActivity(),banerBean,itemDate);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()) {
        });
        mRecyclerView.setAdapter(pageAdapter);
        initListener();
        pageAdapter.setMyItemClickListener(new FirstPageAdapter.MyItemClickListener() {
            @Override
            public void OnClick(View v, int postion) {
                Intent intent=new Intent(getActivity(), MyDetailActivity.class);
                intent.putExtra("url",itemDate.get(postion).getContentUrl());
                intent.putExtra("number",mPosition);
                startActivity(intent);
            }
        });
    }
//    回调函数得到数据
    private JsonUtils.CallbackListener listener=new JsonUtils.CallbackListener() {
        @Override
        public void upData(List<ItemData>[] msg_list) {
            p.closeProgressDialog();
            data=new ArrayList[msg_list.length];
            data=msg_list;
            String[] title=new String[3];
            String[] url=new String[3];
            String[] content=new String[3];
            for (int i = 0; i < title.length; i++) {
                title[i]=data[0].get(i).getTitle();
                url[i]=data[0].get(i).getIconUrl();
                content[i]=data[0].get(i).getContentUrl();
            }
            banerBean.setTitle(title);
            banerBean.setContent(content);
            banerBean.setUrl(url);
            initData();
        }
    };
    private void initData(){
        isFirst=false;
        if(itemDate!=null)
        itemDate.clear();
        for (int i =3; i < max_size; i++) {
            itemDate.add(data[mPosition].get(i));
        }
        pageAdapter.notifyDataSetChanged();
    }
    //下拉刷新回调
    private SwipeRefreshLayout.OnRefreshListener refreshListener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            max_size+=4;//刷新操作执行后多显示几条数据
            jS.getResult();
            swip.setRefreshing(false);
        }
    };
//    添加recycleerView的事件监听
    private void initListener(){
         mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(lastitem==pageAdapter.getItemCount()-1
                        &&newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(isReFresh){
                        return;
                    }
                    isReFresh=true;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            max_size+=4;
                            initData();
                            isReFresh=false;
                        }
                    },2000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               LinearLayoutManager ly=(LinearLayoutManager)mRecyclerView.getLayoutManager();
                lastitem=ly.findLastVisibleItemPosition();
            }
        });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public void setPosition(int position) {
        /*if(isFirst && position-mPosition>1){
            jS.getResult();
        }*/
        mPosition = position;
        initData();
    }
}
