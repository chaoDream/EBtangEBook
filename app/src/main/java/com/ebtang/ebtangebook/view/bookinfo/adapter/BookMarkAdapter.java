package com.ebtang.ebtangebook.view.bookinfo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.fbreader.book.Book;
import org.geometerplus.fbreader.book.Bookmark;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2016/8/29 0029.
 */
public class BookMarkAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private List<Bookmark> myBookmarksList;
    private Activity activity;
    private ListView listView;
    private Book book;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public BookMarkAdapter(ListView listView,List<Bookmark> myBookmarksList,Activity activity,Book book){
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
        ViewHoler viewHoler = null;
        if(convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.book_label_shuqian_item,parent,false);
            viewHoler = new ViewHoler();
            viewHoler.textView_title = (TextView)convertView.findViewById(R.id.book_label_shuqian_title);
            viewHoler.textView_progress = (TextView)convertView.findViewById(R.id.book_label_shuqian_progress);
            viewHoler.textView_time = (TextView)convertView.findViewById(R.id.book_label_shuqian_time);
            viewHoler.textView_content = (TextView)convertView.findViewById(R.id.book_label_shuqian_content);
            convertView.setTag(viewHoler);
        }else{
            viewHoler = (ViewHoler)convertView.getTag();
        }

        viewHoler.textView_time.setText(dateFormat.format(new Date(myBookmarksList.get(position).CreationTimestamp)));
        viewHoler.textView_progress.setText(""+myBookmarksList.get(position).getProgress());
        viewHoler.textView_content.setText(myBookmarksList.get(position).getText());

        return convertView;
    }

    static class ViewHoler{
        TextView textView_title,textView_time,textView_progress,textView_content;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Bookmark bookmark = myBookmarksList.get(position);
        if (bookmark != null) {
            gotoBookmark(bookmark);
        }
    }

    private void gotoBookmark(Bookmark bookmark) {
        if (book != null) {
            FBReader.openBookActivity(activity, book, bookmark);
            activity.finish();
        }
    }

}
