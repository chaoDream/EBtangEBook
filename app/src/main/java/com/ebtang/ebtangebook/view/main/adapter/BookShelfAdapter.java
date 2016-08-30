package com.ebtang.ebtangebook.view.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.db.read.LocalFile;
import com.ebtang.ebtangebook.db.read.LocalFileDb;
import com.ebtang.ebtangebook.widget.dragGridView.DragGridListener;

import java.util.List;

/**
 * Created by dell on 2016/8/8 0008.
 */
public class BookShelfAdapter extends BaseAdapter implements DragGridListener{

    private Context context;
    private List<LocalFile> list;

    public BookShelfAdapter(Context context,List<LocalFile> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shelfitem,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView_name = (TextView)convertView.findViewById(R.id.imageView1);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.textView_name.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder{
        TextView textView_name;
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
