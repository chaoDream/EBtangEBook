package com.ebtang.ebtangebook.view.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.widget.dragGridView.DragGridListener;

import java.util.List;

/**
 * Created by dell on 2016/8/8 0008.
 */
public class BookShelfAdapter extends BaseAdapter implements DragGridListener{

    private Context context;
    private List<Object> list;

    public BookShelfAdapter(Context context,List<Object> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shelfitem,parent,false);
        }else{

        }
        return convertView;
    }

    @Override
    public void reorderItems(int oldPosition, int newPosition) {

    }

    @Override
    public void setHideItem(int hidePosition) {

    }

    @Override
    public void removeItem(int deletePosition) {

    }

    @Override
    public void setItemToFirst(int openPosition) {

    }

    @Override
    public void nitifyDataRefresh() {

    }
}
