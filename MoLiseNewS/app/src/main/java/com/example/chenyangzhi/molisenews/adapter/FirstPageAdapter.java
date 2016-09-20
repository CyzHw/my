package com.example.chenyangzhi.molisenews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenyangzhi.molisenews.R;
import com.example.chenyangzhi.molisenews.entity.BannerBean;
import com.example.chenyangzhi.molisenews.entity.ItemData;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

public class FirstPageAdapter extends RecyclerView.Adapter {

    private final int TYPE_HEAD=0;//表示首个位置直接显示轮播图片banner
    private final int TYPE_NORMAL=1;//表示正常的item布局
    private final int TYPE_BOTTOM=2;//上拉加载
    private Context context;
    private List<ItemData> data;//新闻列表
    private BannerBean mBannerBean;//轮播图
    private MyItemClickListener listener;
    public FirstPageAdapter(Context context,BannerBean bannerBean,List<ItemData> data) {
        this.context = context;
       this.mBannerBean=bannerBean;
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       RecyclerView.ViewHolder holder=null;
        if(viewType==TYPE_HEAD){
//            是顶部banner的类型
            holder=new BanerViewHoler(LayoutInflater.from(context)
            .inflate(R.layout.aty_header_holder,null));
        }else if(viewType==TYPE_NORMAL){
//            listview的
            holder=new ItemViewHOlder(LayoutInflater.from(context).
                    inflate(R.layout.aty_item_holder,null));
        }else if(viewType==TYPE_BOTTOM){
            holder=new BottomLoad(LayoutInflater.from(context)
                    .inflate(R.layout.aty_load,null));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        判断数据
        if(holder instanceof BanerViewHoler){
            BanerViewHoler bannerViewHolder=(BanerViewHoler)holder;
            bannerViewHolder.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            bannerViewHolder.banner.setBannerTitle(mBannerBean.getTitle());
            bannerViewHolder.banner.setImages(mBannerBean.getUrl());
        }else if(holder instanceof ItemViewHOlder){
            ItemViewHOlder itemHolder=(ItemViewHOlder)holder;
            itemHolder.textView.setText(data.get(position-1).getTitle());
            itemHolder.mSimpleDraweeView.setImageURI(data.get(position-1).getIconUrl());
            itemHolder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(v,position-1);
                }
            });
        }else if(holder instanceof BottomLoad){
            BottomLoad loadHolder= (BottomLoad) holder;
            loadHolder.lin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if(data!=null&&data.size()>0)
        return data.size()+1+1;
        else
            return 0;
    }
//告诉创建什么类型的viewHolder
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }else if(position+1==getItemCount()){
            return TYPE_BOTTOM;
        }
        else{
            return TYPE_NORMAL;
        }
    }
//    正常的item的布局管理
    class ItemViewHOlder extends RecyclerView.ViewHolder{
       SimpleDraweeView mSimpleDraweeView;
         // ImageView iv;
        TextView textView;
        public ItemViewHOlder(View itemView) {
            super(itemView);
            mSimpleDraweeView=(SimpleDraweeView) itemView.findViewById(R.id.news_text);
           // iv=(ImageView) itemView.findViewById(R.id.news_text);
            textView=(TextView)itemView.findViewById(R.id.textView);
        }
    }
//    首位的banner的viewholder
    class BanerViewHoler extends RecyclerView.ViewHolder{
        Banner banner;
    public BanerViewHoler(View itemView) {
        super(itemView);
        banner=(Banner)itemView.findViewById(R.id.banner);
    }
}
//    上拉加载
    class BottomLoad extends RecyclerView.ViewHolder{
    LinearLayout lin;
    public BottomLoad(View itemView) {
        super(itemView);
        lin= (LinearLayout) itemView.findViewById(R.id.ProgressLin);
    }
}
//    接口回调点击事件
    public interface  MyItemClickListener{
        void OnClick(View v,int postion);
    }
//    设置回调接口
    public void setMyItemClickListener(MyItemClickListener listener){
        if(listener!=null){
            this.listener=listener;
        }
    }

}
