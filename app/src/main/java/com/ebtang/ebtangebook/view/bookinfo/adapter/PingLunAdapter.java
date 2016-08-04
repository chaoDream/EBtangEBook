package com.ebtang.ebtangebook.view.bookinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ebtang.ebtangebook.R;

import java.util.List;

/**
 * Created by dell on 2016/8/4 0004.
 */
public class PingLunAdapter extends BaseAdapter{
    private Context context;
    private List<Object> list;
    public PingLunAdapter(Context context,List<Object> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.book_detail_pinglun_item,parent,false);
        }else{

        }
        return convertView;
    }
}
