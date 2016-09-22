package com.ebtang.ebtangebook.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvpView.YinHaoView;
import com.ebtang.ebtangebook.persenter.YinHaoConsumePersenter;
import com.ebtang.ebtangebook.persenter.YinHaoRechargePersenter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookBijiAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookLabelTypeAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookMarkAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookMuluAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.MessageAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.YInHaoConsumeAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.YinHaoRechargeAdapter;
import com.ebtang.ebtangebook.view.setting.bean.MyMsgBean;
import com.ebtang.ebtangebook.view.setting.bean.MyMsgListBean;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoConsumeBean;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoRechargeBean;
import com.ebtang.ebtangebook.widget.pullToRefresh.XListView;

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

/**
 * Created by fengzongwei on 2016/7/28 0028.
 * 列表数据展示页面
 */
public class MyListFragment extends BaseFragment implements YinHaoView,XListView.IXListViewListener{

    private XListView listView;

    private List<Object> list = new ArrayList<>();

    private MessageAdapter messageAdapter;//消息中心
    private YInHaoConsumeAdapter yInHaoDataAdapter;//消费记录
    private YinHaoRechargeAdapter yinHaoRechargeAdapter;//充值记录
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


    private YinHaoConsumePersenter yinHaoConsumePersenter;//消费记录
    private YinHaoRechargePersenter yinHaoRechargePersenter;//充值记录

    private int pageSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new XListView(getActivity());
        listView.setPullLoadEnable(false);
        listView.setPullRefreshEnable(false);
        listView.setXListViewListener(this);
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
            listView.setPullLoadEnable(true);
            listView.setPullRefreshEnable(true);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_XIAOFEI_MSG  ){//消费记录
            yinHaoConsumePersenter = new YinHaoConsumePersenter();
            yinHaoConsumePersenter.attachView(this);
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_CHONGZHI_MSG ){//充值记录
            yinHaoRechargePersenter = new YinHaoRechargePersenter();
            yinHaoRechargePersenter.attachView(this);
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
        initData();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI ||
                type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN ){
            myCollection.bindToService(getActivity(), new Runnable() {
                public void run() {
                    loadBookmarks();
                }
            });
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_CHONGZHI_MSG){//充值记录
            yinHaoRechargePersenter.getData();
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_XIAOFEI_MSG){//消费记录
            yinHaoConsumePersenter.getData();
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_MSG ){

        }
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

    /**
     * 计算标签的进度
     * @param bookmark
     */
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public void showConsumeData(List<MyYinHaoConsumeBean> myYinHaoConsumeBeanList) {
        yInHaoDataAdapter = new YInHaoConsumeAdapter(getActivity(),myYinHaoConsumeBeanList);
        listView.setAdapter(yInHaoDataAdapter);
    }

    @Override
    public void showRechargeData(List<MyYinHaoRechargeBean> myYinHaoRechargeBeanList) {
        yinHaoRechargeAdapter = new YinHaoRechargeAdapter(getActivity(),myYinHaoRechargeBeanList);
        listView.setAdapter(yinHaoRechargeAdapter);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void showMsgList(MyMsgListBean myMsgListBean) {
        pageSize = myMsgListBean.getPageSize();
        if(myMsgListBean.getList().size()<pageSize){
            listView.setPullLoadEnable(false);
        }
    }
}
