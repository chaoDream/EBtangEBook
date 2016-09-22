package com.ebtang.ebtangebook.view.setting.bean;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/21 0021.
 * 消息中心数据集合
 */
public class MyMsgListBean {

    private int pageSize;
    private int totalPage;
    private int totalCount;
    private int currPage;
    private List<MyMsgBean> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<MyMsgBean> getList() {
        return list;
    }

    public void setList(List<MyMsgBean> list) {
        this.list = list;
    }
}
