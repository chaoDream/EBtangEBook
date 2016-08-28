package com.ebtang.ebtangebook.widget;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookBijiAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookLabelTypeAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookMuluAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.MessageAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.YInHaoDataAdapter;

import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.libraryService.BookCollectionShadow;
import org.geometerplus.fbreader.book.Book;
import org.geometerplus.fbreader.book.Bookmark;
import org.geometerplus.fbreader.book.BookmarkQuery;
import org.geometerplus.fbreader.bookmodel.TOCTree;
import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.application.ZLApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Created by dell on 2016/7/28 0028.
 */
public class MyListFragment extends BaseFragment{

    private ListView listView;

    private List<Object> list = new ArrayList<>();

    private MessageAdapter messageAdapter;
    private YInHaoDataAdapter yInHaoDataAdapter;
    private BookLabelTypeAdapter bookLabelTypeAdapter;
    private BookMuluAdapter bookMuluAdapter;
    private BookBijiAdapter bookBijiAdapter;

    //笔记相关的model
    private List<Bookmark> myBookMark = new ArrayList<>();
    private Book myBook;
    private final BookCollectionShadow myCollection = new BookCollectionShadow();

    private int type;

    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    bookBijiAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new ListView(getActivity());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        type = getArguments().getInt("type");
        if(type == Constants.MY_LIST_FRAGMENT_TYPE_MSG || type == Constants.MY_LIST_FRAGMENT_TYPE_ZHANDUAN){
            messageAdapter = new MessageAdapter(getActivity(),list,type);
            listView.setAdapter(messageAdapter);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_CHONGZHI_MSG ||
                type == Constants.MY_LIST_FRAGMENT_TYPE_XIAOFEI_MSG  ){
            yInHaoDataAdapter = new YInHaoDataAdapter(getActivity(),list,type);
            listView.setAdapter(yInHaoDataAdapter);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_MULU){
            final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
            final TOCTree root = fbreader.Model.TOCTree;
            TOCTree treeToSelect = fbreader.getCurrentTOCElement();
            bookMuluAdapter = new BookMuluAdapter(getActivity(),listView,root,treeToSelect);
            bookMuluAdapter.selectItem(treeToSelect);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI){
            myBook  = FBReaderIntents.getBookExtra(getActivity().getIntent(), myCollection);
            bookBijiAdapter = new BookBijiAdapter(listView,myBookMark,getActivity(),myBook);
            listView.setAdapter(bookBijiAdapter);
//            loadBookmarks();
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN ){
            bookLabelTypeAdapter = new BookLabelTypeAdapter(getActivity(),list,type);
            listView.setAdapter(bookLabelTypeAdapter);
        }
        return listView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI){
            myCollection.bindToService(getActivity(), new Runnable() {
                public void run() {
                    loadBookmarks();
                }
            });
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 加载本书的笔记
     */
    private void loadBookmarks() {
        new Thread(new Runnable() {
            public void run() {
                synchronized (myBookMark) {
                    for (BookmarkQuery query = new BookmarkQuery(myBook, 50); ; query = query.next()) {
                        final List<Bookmark> thisBookBookmarks = myCollection.bookmarks(query);
                        if (thisBookBookmarks.isEmpty()) {
                            break;
                        }
                        myBookMark.addAll(thisBookBookmarks);
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        }).start();
    }

}
