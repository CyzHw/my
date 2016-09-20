package com.example.chenyangzhi.molisenews.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.View;

public abstract class BaseHolder<T> {
//	��ͼƬ��ȡ����һ������
//	LruCache�������ʹ��
	public View rootView;
	public Context context;
	public static LruCache<String,Bitmap> cache;
	public View getRootView(){
		return rootView;
	}
	public BaseHolder(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
		rootView=initView();
		rootView.setTag(this);
//		��ʼ������
		cache=new LruCache<String, Bitmap>(5*1024*1024){

			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getByteCount();//value.getByteCount()���ٴ��λͼ���ص��ֽڵĻ���Ĵ�С
			}
			
		};
	}
	public void put(String name,Bitmap value){
		cache.put(name, value);
	}
	public Bitmap get(String key){
		return cache.get(key);
	}
	public abstract View initView();
	public abstract void setData(T s,boolean isFling);
}
