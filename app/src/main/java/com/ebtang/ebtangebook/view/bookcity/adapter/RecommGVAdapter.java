package com.ebtang.ebtangebook.view.bookcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.ebtang.ebtangebook.R;

import java.util.List;

/**
 * Created by dell on 2016/7/19 0019.
 */
public class RecommGVAdapter extends BaseAdapter {
    private Context context;
    private List<Object> list;
    public RecommGVAdapter(Context context,List<Object> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.bookcity_gv_item,parent,false);
        }else{

        }
        return convertView;
    }

    static class ViewHolder{
    }

}
