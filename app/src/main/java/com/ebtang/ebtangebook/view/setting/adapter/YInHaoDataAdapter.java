package com.ebtang.ebtangebook.view.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ebtang.ebtangebook.R;

import java.util.List;

/**
 * Created by fengzongwei on 16/7/30.
 */
public class YInHaoDataAdapter extends BaseAdapter{

    private Context context;
    private List<Object> list;
    private int type;

    public YInHaoDataAdapter(Context context, List<Object> list,int type){
        this.context = context;
        this.list = list;
        this.type = type;
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
        if(type == 3){
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.yinhao_chongzhijilu_item,parent,false);
            }
        }else {
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.yinhao_xiaofeijilu_item,parent,false);
            }
        }
        return convertView;
    }
}
