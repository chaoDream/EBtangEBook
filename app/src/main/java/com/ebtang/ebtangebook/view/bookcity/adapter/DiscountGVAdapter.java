package com.ebtang.ebtangebook.view.bookcity.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;

import java.util.List;

/**
 * Created by dell on 2016/8/11 0011.
 */
public class DiscountGVAdapter extends BaseAdapter{

    private Context context;
    private List<Object> list;

    public DiscountGVAdapter(Context context,List<Object> list){
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
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.discount_gv_item,parent,false);
            viewHolder.textView_old_price = (TextView)convertView.findViewById(R.id.discount_before_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.textView_old_price.getPaint().setAntiAlias(true);//抗锯齿
        viewHolder.textView_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        viewHolder.textView_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//设置中划线并加清晰
        return convertView;
    }

    static class ViewHolder{
        TextView textView_old_price;
    }

}
