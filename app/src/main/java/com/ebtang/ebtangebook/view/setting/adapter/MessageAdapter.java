package com.ebtang.ebtangebook.view.setting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.setting.bean.MyMsgBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fengzongwei on 2016/7/27 0027.
 */
public class MessageAdapter extends BaseAdapter {

    private Context context;
    private List<MyMsgBean> msglist;
    private List<Object> zhanduanList;
    private int type;//消息或者站短  1消息  2站短

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MessageAdapter(Context context,List<MyMsgBean> msglist,List<Object> zhanduanList,int type){
        this.context = context;
        this.msglist = msglist;
        this.zhanduanList = zhanduanList;
        this.type = type;
    }

    @Override
    public int getCount(){
        if(type == 1){
            return msglist.size();
        }else{
            return zhanduanList.size();
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        MsgViewHolder msgViewHolder = null;
        if(type == 1){
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.persenal_message_item,parent,false);
                msgViewHolder = new MsgViewHolder();
                msgViewHolder.imageView = (ImageView)convertView.findViewById(R.id.persenal_msg_item_img);
                msgViewHolder.textView_name = (TextView)convertView.findViewById(R.id.persenal_message_item_name);
                msgViewHolder.textView_title = (TextView)convertView.findViewById(R.id.persenal_message_item_title);
                msgViewHolder.textView_bt = (TextView)convertView.findViewById(R.id.persenal_message_item_confirm);
                msgViewHolder.textViewtime = (TextView)convertView.findViewById(R.id.persenal_message_item_time);
                msgViewHolder.textView_content = (TextView)convertView.findViewById(R.id.persenal_message_item_content);
                msgViewHolder.editText = (EditText)convertView.findViewById(R.id.persenal_message_item_et);
                convertView.setTag(msgViewHolder);
            }
        }else{
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.persenal_zhanduan_item,parent,false);
            }
        }

        if(type == 1){
            msgViewHolder = (MsgViewHolder)convertView.getTag();
            ImageLoader.getInstance().displayImage(msglist.get(position).getUserPic(), msgViewHolder.imageView);
            msgViewHolder.textView_name.setText(msglist.get(position).getUserNick());
            msgViewHolder.textView_title.setText(msglist.get(position).getReplyContent());
            msgViewHolder.textView_content.setText(msglist.get(position).getContent());
            msgViewHolder.textViewtime.setText(format.format(new Date(msglist.get(position).getCreateTime())));
            if(msglist.get(position).isShowEditText()){
                msgViewHolder.editText.setVisibility(View.VISIBLE);
            }else {
                msgViewHolder.editText.setVisibility(View.GONE);
            }
            msgViewHolder.textView_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!msglist.get(position).isShowEditText()) {
                        msglist.get(position).setIsShowEditText(true);
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(context,"提交回复",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return convertView;
    }

    class MsgViewHolder{
        ImageView imageView;
        TextView textView_name,textView_title,textView_content,textViewtime,textView_bt;
        EditText editText;
    }

}
