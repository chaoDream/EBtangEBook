package com.ebtang.ebtangebook.view.bookcity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.bookcity.PaiHangActivity;

import java.util.List;

/**
 * Created by fengzongwei on 16/7/20.
 */
public class FreeImgListAdapter extends BaseAdapter {

    private Context context;
    private List<Object> list;

    public FreeImgListAdapter(Context context,List<Object> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.bookcity_free_item_top,parent,false);
            viewHolder.linearLayout_more = (LinearLayout)convertView.findViewById(R.id.bookcity_free_item_more);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.linearLayout_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PaiHangActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder{
        LinearLayout linearLayout_more;
    }

}
