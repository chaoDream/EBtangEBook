package com.ebtang.ebtangebook.event;

/**
 * Created by fengzongwei on 2016/8/26 0026.
 * 修改笔记内容
 */
public class BookMarkModify {
    private String content;//笔记内容
    public BookMarkModify(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }
}
