package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;
import com.ebtang.ebtangebook.view.read.bean.EbtangBook;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/8 0008.
 */
public interface ShizishanView extends MvpView{
    void showBannerData(List<EbtangBook> bannerBookList);//显示banner数据
    void showTopSixBookData(List<EbtangBook> topSixBookList);//显示最上的六本书
    void showMaleOneBook(EbtangBook maleOneBook);//显示男频第一本书
    void showMaleSixBook(List<EbtangBook> maleSixBookList);//显示男频六本推荐书
    void showMaleEightBook(List<EbtangBook> maleEightBookList);//显示男频八本书名
    void showAdBook(EbtangBook abBook);//显示男频和女频之间的书
    void showFemalOneBook(EbtangBook femalOneBook);//女频第一本书
    void showFemalSixBook(List<EbtangBook> femalSixBookList);//女频六本书
    void showFemalEightBook(List<EbtangBook> femalEightBookList);//女频八本书名
    void showRecommListBook(List<EbtangBook> recommList);//最底部推荐的书列表
}
