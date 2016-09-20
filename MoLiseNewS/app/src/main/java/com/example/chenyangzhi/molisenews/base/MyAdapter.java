package com.example.chenyangzhi.molisenews.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MyAdapter<T> extends BaseAdapter {
	List<T> list;
	boolean isFling;
	public MyAdapter() {
		list=new ArrayList<T>();
	}
	public  void addData(T a) {
		// TODO Auto-generated method stub
		if(list==null){
			return;
		}
		list.add(a);
		notifyDataSetChanged();
	}
//	������Ļ������״̬
	public void setFling(boolean isFling){
		this.isFling=isFling;
	}
	public  void removeData(T a){
		if(list==null){
			return ;
		}
		list.remove(a);
		notifyDataSetChanged();
	}
	public void setList(List<T> ist){
		if(this.list==null)
			this.list=new ArrayList<>();
		this.list.clear();
		this.list.addAll(ist);
		notifyDataSetChanged();
	}
	public  void addList(List<T> list) {
		// TODO Auto-generated method stub
		if(list==null){
			return;
		}
		list.addAll(list);
		notifyDataSetChanged();
	}
	public void rmoverList(List<T> list){
		if(list==null){
			return;
		}
		list.removeAll(list);
		notifyDataSetChanged();
	}
	public  void clear() {
		// TODO Auto-generated method stub
		if(list==null){
			return;
		}
		list.clear();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list==null?0:list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list==null?null:list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		BaseHolder<T> holder=null;
		if(convertView==null){
			holder=getHolder(parent.getContext());
			convertView=holder.getRootView();
		}else{
			holder=(BaseHolder<T>)convertView.getTag();
		}
		holder.setData(list.get(position),isFling);
		return convertView;
	}
	public abstract BaseHolder<T> getHolder(Context context);
}
