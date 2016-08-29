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
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookMarkAdapter;
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

import java.text.DecimalFormat;
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
    private BookMuluAdapter bookMuluAdapter;//目录适配器
    private BookBijiAdapter bookBijiAdapter;//笔记适配器
    private BookMarkAdapter bookMarkAdapter;//书签适配器

    private List<Bookmark> bijiList = new ArrayList<>(),//笔记集合
            markList = new ArrayList<>();//书签集合

    //笔记相关的model
    private List<Bookmark> myBookMark = new ArrayList<>();
    private Book myBook;
    private final BookCollectionShadow myCollection = new BookCollectionShadow();

    private int type;

    private TOCTree root;//当前书的章节
    private DecimalFormat df = new DecimalFormat("0.00");

    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI:
                    bookBijiAdapter.notifyDataSetChanged();
                    break;
                case Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN:
                    bookMarkAdapter.notifyDataSetChanged();
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
        if(type == Constants.MY_LIST_FRAGMENT_TYPE_MSG || type == Constants.MY_LIST_FRAGMENT_TYPE_ZHANDUAN){//消息中心
            messageAdapter = new MessageAdapter(getActivity(),list,type);
            listView.setAdapter(messageAdapter);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_CHONGZHI_MSG ||
                type == Constants.MY_LIST_FRAGMENT_TYPE_XIAOFEI_MSG  ){//消费记录
            yInHaoDataAdapter = new YInHaoDataAdapter(getActivity(),list,type);
            listView.setAdapter(yInHaoDataAdapter);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_MULU){//书籍目录
            final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
            root = fbreader.Model.TOCTree;
            TOCTree treeToSelect = fbreader.getCurrentTOCElement();
            bookMuluAdapter = new BookMuluAdapter(getActivity(),listView,root,treeToSelect);
            bookMuluAdapter.selectItem(treeToSelect);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI){//书籍笔记
            final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
            root = fbreader.Model.TOCTree;
            myBook  = FBReaderIntents.getBookExtra(getActivity().getIntent(), myCollection);
            bookBijiAdapter = new BookBijiAdapter(listView,bijiList,getActivity(),myBook);
            listView.setAdapter(bookBijiAdapter);
//            loadBookmarks();
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN ){//书籍书签
            final FBReaderApp fbreader = (FBReaderApp) ZLApplication.Instance();
            root = fbreader.Model.TOCTree;
            myBook  = FBReaderIntents.getBookExtra(getActivity().getIntent(), myCollection);
            bookMarkAdapter = new BookMarkAdapter(listView,markList,getActivity(),myBook);
            listView.setAdapter(bookMarkAdapter);
        }
        return listView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI ||
                type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN ){
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
                        for(int i=0;i<myBookMark.size();i++){
                            calculatePorgress(myBookMark.get(i));
                            if(myBookMark.get(i).getOriginalText()==null || myBookMark.get(i).getOriginalText().equals("")){
                                markList.add(myBookMark.get(i));
                            }else {
                                bijiList.add(myBookMark.get(i));
                            }
                        }
                        handler.sendEmptyMessage(type);
                    }
                }
            }
        }).start();
    }

    private void calculatePorgress(Bookmark bookmark){
        double all = root.subtrees().size();
        for(int i=0;i<root.subtrees().size();i++){
            if(bookmark.ParagraphIndex>root.subtrees().get(i).getReference().ParagraphIndex){
                if(bookmark.ParagraphIndex<root.subtrees().get(i+1).getReference().ParagraphIndex){
                    double progress = (i+1)/all * 100;
                    bookmark.setProgress(df.format(progress) + "%");
                    break;
                }
            }
        }
    }

}
