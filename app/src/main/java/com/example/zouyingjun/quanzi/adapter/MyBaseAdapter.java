package com.example.zouyingjun.quanzi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by j on 2016/11/15.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    List<T> data;

    public MyBaseAdapter(Context context, List<T> data){
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<T> data,boolean isClear){
        if(isClear){
            this.data.clear();
        }
        this.data.addAll(data);
        notifyDataSetChanged();
//        Log.i("TAG","--------------------数据源："+data.size());
    }
    public void add(T t){
        this.data.add(t);
        notifyDataSetChanged();
    }
    public void remove(T t){
        this.data.remove(t);
        notifyDataSetChanged();
    }
    public void clear(List<T> data){
        this.data.clear();
        notifyDataSetChanged();
    }
}
