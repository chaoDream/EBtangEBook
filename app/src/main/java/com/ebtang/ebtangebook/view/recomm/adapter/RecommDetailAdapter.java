package com.ebtang.ebtangebook.view.recomm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ebtang.ebtangebook.R;

import org.w3c.dom.ls.LSInput;

import java.util.List;

/**
 * Created by dell on 2016/8/8 0008.
 */
public class RecommDetailAdapter extends BaseAdapter{

    private Context context;
    private List<Object> list;

    public RecommDetailAdapter(Context context,List<Object> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.recomm_detail_lv_item,parent,false);
        }else{

        }
        return convertView;
    }
}
