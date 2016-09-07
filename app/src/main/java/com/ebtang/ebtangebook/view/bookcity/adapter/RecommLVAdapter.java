package com.ebtang.ebtangebook.view.bookcity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.setting.bean.MySubscribBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2016/7/19 0019.
 */
public class RecommLVAdapter extends BaseAdapter {

    private String imgHeadUrl = "http://www.ebtang.com/resources/images/book/bimgs/";
    private Context context;

    private ImageLoader imageLoader;

    private List<MySubscribBean> list;
    public RecommLVAdapter(Context context,List<MySubscribBean> list){
        this.context = context;
        this.list = list;
        imageLoader = ImageLoader.getInstance();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.bookcity_lv_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView_book = (ImageView)convertView.findViewById(R.id.book_item_bookImag);
            viewHolder.textView_bookName = (TextView)convertView.findViewById(R.id.book_item_bookName);
            viewHolder.textView_autherName = (TextView)convertView.findViewById(R.id.book_item_autherName);
            viewHolder.imageView_auther = (ImageView)convertView.findViewById(R.id.book_item_autherImg);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textView_bookName.setText(list.get(position).getBookName());
        viewHolder.textView_autherName.setText(list.get(position).getAuthorNick());
        imageLoader.displayImage(imgHeadUrl + list.get(position).getBookImage(), viewHolder.imageView_book);
        imageLoader.displayImage(list.get(position).getUserpic(),viewHolder.imageView_auther);

        return convertView;
    }

    class ViewHolder{
        TextView textView_bookName,textView_jieshao,textView_autherName;
        ImageView imageView_book,imageView_auther;
    }

}
