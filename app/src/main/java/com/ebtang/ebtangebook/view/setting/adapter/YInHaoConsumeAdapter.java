package com.ebtang.ebtangebook.view.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoConsumeBean;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoRechargeBean;

import java.util.List;

/**
 * Created by fengzongwei on 16/7/30.
 * 消费记录
 */
public class YInHaoConsumeAdapter extends BaseAdapter{

    private Context context;
    private List<MyYinHaoConsumeBean> list;

    public YInHaoConsumeAdapter(Context context, List<MyYinHaoConsumeBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.yinhao_xiaofeijilu_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView_type = (TextView)convertView.findViewById(R.id.yinhao_consume_item_type);
            viewHolder.textView_name = (TextView)convertView.findViewById(R.id.yinhao_consume_item_name);
            viewHolder.textView_money = (TextView)convertView.findViewById(R.id.yinhao_consume_item_money);
            viewHolder.textView_time = (TextView)convertView.findViewById(R.id.yinhao_consume_item_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if(list.get(position).getPayType() == 1){
            viewHolder.textView_type.setText("订阅");
        }else{
            viewHolder.textView_type.setText("打赏");
        }
        viewHolder.textView_name.setText(list.get(position).getBookName());
        viewHolder.textView_money.setText(list.get(position).getPayValue()+"");
        viewHolder.textView_time.setText(list.get(position).getCreateTimeFormat());
        return convertView;
    }

    class ViewHolder{
        TextView textView_type,textView_name,textView_money,textView_time;
    }

}
