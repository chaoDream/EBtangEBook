package com.ebtang.ebtangebook.view.bookinfo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.android.util.UIMessageUtil;
import org.geometerplus.fbreader.book.Book;
import org.geometerplus.fbreader.book.Bookmark;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fengzongwei on 16/8/27.
 */
public class BookBijiAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private List<Bookmark> myBookmarksList;
    private Activity activity;
    private ListView listView;
    private Book book;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public BookBijiAdapter(ListView listView,List<Bookmark> myBookmarksList,Activity activity,Book book){
        this.listView = listView;
        this.myBookmarksList = myBookmarksList;
        this.activity = activity;
        this.book = book;
        listView.setOnItemClickListener(this);
    }

    @Override
    public int getCount() {
        return myBookmarksList.size();
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
        ViewHolder viewHolder=null;
        if(convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.book_label_biji_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView_circle = (ImageView) convertView.findViewById(R.id.book_label_biji_color_circle);
            viewHolder.textView_bili = (TextView) convertView.findViewById(R.id.book_label_biji_bili);
            viewHolder.textView_time = (TextView)convertView.findViewById(R.id.book_label_biji_time);
            viewHolder.textView_bookcontent = (TextView)convertView.findViewById(R.id.book_label_biji_bookcontent);
            viewHolder.textView_content = (TextView)convertView.findViewById(R.id.book_label_biji_content);
            viewHolder.imageView_biji = (ImageView) convertView.findViewById(R.id.book_label_biji_color);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(myBookmarksList.get(position).getStyleId() == 1){
            viewHolder.imageView_biji.setImageResource(R.drawable.bookinfo_label_pen_yellow);
            viewHolder.imageView_circle.setImageResource(R.drawable.bookinfo_label_point_yellow);
        }else if(myBookmarksList.get(position).getStyleId() == 2){
            viewHolder.imageView_biji.setImageResource(R.drawable.bookinfo_label_pen_red);
            viewHolder.imageView_circle.setImageResource(R.drawable.bookinfo_label_point_red);
        }else{
            viewHolder.imageView_biji.setImageResource(R.drawable.bookinfo_label_pen_zi);
            viewHolder.imageView_circle.setImageResource(R.drawable.bookinfo_label_point_zi);
        }

        viewHolder.textView_time.setText(dateFormat.format(new Date(myBookmarksList.get(position).CreationTimestamp)));
        if(myBookmarksList.get(position).getOriginalText()==null ||
                myBookmarksList.get(position).getOriginalText().equals("")){
            viewHolder.textView_bookcontent.setText(myBookmarksList.get(position).getText());
        }else{
            viewHolder.textView_bookcontent.setText(myBookmarksList.get(position).getOriginalText());
        }
        viewHolder.textView_content.setText(myBookmarksList.get(position).getText());

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView_circle,imageView_biji;
        TextView textView_bili,textView_time,textView_bookcontent,textView_content;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Bookmark bookmark = myBookmarksList.get(position);
        if (bookmark != null) {
            gotoBookmark(bookmark);
        }
    }

    private void gotoBookmark(Bookmark bookmark) {
//        bookmark.markAsAccessed();
//        myCollection.saveBookmark(bookmark);
        if (book != null) {
            FBReader.openBookActivity(activity, book, bookmark);
            activity.finish();
        }
    }

}
