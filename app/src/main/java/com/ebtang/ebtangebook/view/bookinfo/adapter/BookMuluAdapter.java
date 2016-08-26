package com.ebtang.ebtangebook.view.bookinfo.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.event.OpenBookDone;

import org.geometerplus.android.fbreader.ZLTreeAdapter;
import org.geometerplus.fbreader.bookmodel.TOCTree;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.core.tree.ZLTree;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by fengzongwei on 2016/8/26 0026.
 */
public class BookMuluAdapter extends ZLTreeAdapter {

    private Activity activity;

    private ZLTree<?> mySelectedItem;

    public BookMuluAdapter(Activity activity,ListView listView,TOCTree root,ZLTree<?> mySelectedItem) {
        super(listView,root);
        this.activity = activity;
        this.mySelectedItem = mySelectedItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.bookinfo_mulu_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.textView_number = (TextView)convertView.findViewById(R.id.bookinfo_mulu_item_num);
            viewHolder.textView_name = (TextView)convertView.findViewById(R.id.bookinfo_mulu_item_name);
            viewHolder.imageView_free = (ImageView)convertView.findViewById(R.id.bookinfo_mulu_item_free);
            viewHolder.layout_body = (LinearLayout)convertView.findViewById(R.id.bookinfo_mulu_item_body);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if((TOCTree)getItem(position) == mySelectedItem){
            viewHolder.layout_body.setBackgroundColor(Color.parseColor("#DBDBDB"));
        }else{
            viewHolder.layout_body.setBackgroundColor(Color.WHITE);
        }
        viewHolder.textView_name.setText(((TOCTree)getItem(position)).getText());

        return convertView;
    }

    static class ViewHolder{
        TextView textView_number,textView_name;
        ImageView imageView_free;
        LinearLayout layout_body;
    }

    void openBookText(TOCTree tree) {
        final TOCTree.Reference reference = tree.getReference();
        if (reference != null) {
            activity.finish();
            final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
            fbreader.addInvisibleBookmark();
            fbreader.BookTextView.gotoPosition(reference.ParagraphIndex, 0, 0);
            fbreader.showBookTextView();
            fbreader.storePosition();
            EventBus.getDefault().post(new OpenBookDone());
        }
    }

    @Override
    protected boolean runTreeItem(ZLTree<?> tree) {
        if (super.runTreeItem(tree)) {
            return true;
        }
        openBookText((TOCTree)tree);
        return true;
    }

}
