package com.ebtang.ebtangebook.view.bookinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.constants.Constants;

import java.util.List;

/**
 * Created by dell on 2016/8/5 0005.
 */
public class BookLabelTypeAdapter extends BaseAdapter{

    private Context context;
    private List<Object> list;
    private int type;
    public BookLabelTypeAdapter(Context context,List<Object> list,int type){
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
        if(convertView == null){
            if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_MULU){
                convertView = LayoutInflater.from(context).inflate(R.layout.bookinfo_mulu_item,parent,false);
            }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN){
                convertView = LayoutInflater.from(context).inflate(R.layout.book_label_shuqian_item,parent,false);
            }else {
                convertView = LayoutInflater.from(context).inflate(R.layout.book_label_biji_item,parent,false);
            }
        }else{

        }
        return convertView;
    }
}
