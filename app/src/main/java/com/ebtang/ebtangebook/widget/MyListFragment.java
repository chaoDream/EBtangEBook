package com.ebtang.ebtangebook.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookLabelTypeAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.MessageAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.YInHaoDataAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/28 0028.
 */
public class MyListFragment extends BaseFragment{

    private ListView listView;

    private List<Object> list = new ArrayList<>();

    private MessageAdapter messageAdapter;
    private YInHaoDataAdapter yInHaoDataAdapter;
    private BookLabelTypeAdapter bookLabelTypeAdapter;
    private int type;

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
        }else if(type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_MULU ||
                type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_SHUQIAN ||
                type == Constants.MY_LIST_FRAGMENT_TYPE_BOOK_LABEL_BIJI){
            bookLabelTypeAdapter = new BookLabelTypeAdapter(getActivity(),list,type);
            listView.setAdapter(bookLabelTypeAdapter);
        }
        return listView;
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
}
