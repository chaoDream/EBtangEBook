package com.ebtang.ebtangebook.view.found.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.Bind;

/**
 * Created by fengzongwei on 16/7/14.
 */
public class FoundAdapter extends BaseAdapter {

    private Context context;
    private List<Object> list = new ArrayList<>();

    private String[] path = new String[]{
            "http://www.ebtang.com/resources/images/banner/1465983788878.jpg",
            "http://www.ebtang.com/resources/images/banner/1465370622721.jpg",
            "http://www.ebtang.com/resources/images/banner/1465983536213.jpg",
            "http://www.ebtang.com/resources/images/banner/1466997412649.jpg"};

    private DisplayImageOptions options;

    public FoundAdapter(Context context, List<Object> list){
        this.context = context;
        this.list = list;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_loading)
                .showImageOnFail(R.drawable.ic_loading)
                .showImageOnFail(R.drawable.back)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

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
            convertView = LayoutInflater.from(context).inflate(R.layout.main_found_list_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView_recomm = (TextView) convertView.findViewById(R.id.main_found_list_item_bookRecomm);
            viewHolder.textView_desc = (TextView)convertView.findViewById(R.id.main_found_list_item_bookdesc);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.main_found_list_item_img);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        ImageLoader.getInstance().displayImage(path[position],viewHolder.imageView,options);
        return convertView;
    }

    static class ViewHolder{
        TextView textView_recomm,textView_desc;
        ImageView imageView;
    }

}
