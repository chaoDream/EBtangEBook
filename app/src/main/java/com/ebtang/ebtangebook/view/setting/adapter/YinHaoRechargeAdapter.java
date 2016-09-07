package com.ebtang.ebtangebook.view.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoRechargeBean;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/7 0007.
 * 充值记录
 */
public class YinHaoRechargeAdapter extends BaseAdapter{

    private Context context;
    private List<MyYinHaoRechargeBean> list;

    public YinHaoRechargeAdapter(Context context,List<MyYinHaoRechargeBean> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.yinhao_chongzhijilu_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView_num = (TextView)convertView.findViewById(R.id.yinhao_recharge_item_num);
            viewHolder.textView_qudao = (TextView)convertView.findViewById(R.id.yinhao_recharge_item_qudao);
            viewHolder.textView_price = (TextView)convertView.findViewById(R.id.yinhao_recharge_item_price);
            viewHolder.textView_dou = (TextView)convertView.findViewById(R.id.yinhao_recharge_item_dou);
            viewHolder.textView_time = (TextView)convertView.findViewById(R.id.yinhao_recharge_item_time);
            viewHolder.textView_status = (TextView)convertView.findViewById(R.id.yinhao_recharge_item_status);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.textView_num.setText(list.get(position).getOrderNo());
        viewHolder.textView_qudao.setText(list.get(position).getRechargeWayCN());
        viewHolder.textView_price.setText(list.get(position).getRechargeValue()+"");
        viewHolder.textView_time.setText(list.get(position).getCreateTimeFormat());
        viewHolder.textView_status.setText(list.get(position).getStatusCN());
        return convertView;
    }
    class ViewHolder{
        TextView textView_num,textView_qudao,textView_price,textView_dou,textView_time,textView_status;
    }
}
