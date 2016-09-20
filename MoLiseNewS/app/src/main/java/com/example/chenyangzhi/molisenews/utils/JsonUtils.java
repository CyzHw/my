package com.example.chenyangzhi.molisenews.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.chenyangzhi.molisenews.entity.ItemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenyangzhi on 2016/9/5.
 */
//获取网络数据的类
//    使用okhttp请求数据

public  class JsonUtils {
    private CallbackListener listener;
    public JsonUtils(CallbackListener listener){
        this.listener=listener;
    }
    //    建立网络框架，获取网络数据
    public  void  getResult(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //                创建一个OkHttpClient对象，一个APP里最好只实例化一个
                OkHttpClient client=new OkHttpClient();
                //               新建一个请求
                Request request=new Request
                        .Builder().url("http://news.ifeng.com/").build();
                //                执行请求或者响应结果
                Call call= client.newCall(request);
                //
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("okhttp请求","请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            //                            执行请求成功的操作
                            String str=response.body().string();
                            Message msg=new Message();
                            msg.what=1;
                            msg.obj=str;
                            handler.sendMessage(msg);
                        }else{
                            handler.sendEmptyMessage(0);
                            throw new IOException("");
                        }
                    }
                });
            }
        }).start();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                   return;
                case 1:
                    getJson(msg.obj.toString());
                    break;
            }
        }
    };
    //    获得json数据
    private  void getJson(String json) {
        String result=null;
        if(json!=null){
            //                使用substring不包含结束的位置
            result=json.substring(json.indexOf("[[{"),json.indexOf("}]]")+3);
            //            截取后进行解析
            initMessageList(result);
         }
    }
    public   List<ItemData>[] mDataList;
    //解析json数据
    private  void initMessageList(String result) {
        //        数据的分割方式
        JSONArray arr=null;
        JSONArray array=null;
        JSONObject obj=null;
        try {
            arr=new JSONArray(result);
            mDataList=new ArrayList[arr.length()];
            ItemData data;
            for (int i = 0; i <arr.length() ; i++) {
                //                在取到每个位置对应的数据
                array=arr.getJSONArray(i);
                mDataList[i]=new ArrayList<>();
                for (int j = 0; j < array.length(); j++) {
                    obj=array.getJSONObject(j);
                    data = new ItemData();
                    data.setIconUrl(obj.getString("thumbnail"));
                    data.setContentUrl(obj.getString("url"));
                    data.setTitle(obj.getString("title"));
                    mDataList[i].add(data);
                }
            }
            listener.upData(mDataList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
//   接口回调
    public interface CallbackListener{
       void upData(List<ItemData>[] msg_list);
    }
}
