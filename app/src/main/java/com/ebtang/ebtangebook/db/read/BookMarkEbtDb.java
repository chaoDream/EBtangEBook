package com.ebtang.ebtangebook.db.read;

import android.content.Context;

import com.ebtang.ebtangebook.db.EbtDBHelper;
import com.yunqing.core.db.DBExecutor;
import com.yunqing.core.db.sql.Sql;
import com.yunqing.core.db.sql.SqlFactory;

import group.pals.android.lib.ui.filechooser.utils.E;

/**
 * Created by fengzongwei on 2016/8/29 0029.
 * 书签数据库
 */
public class BookMarkEbtDb {

    Context mContext;
    DBExecutor dbExecutor = null;
    Sql sql = null;

    public BookMarkEbtDb(Context mContext){
        this.mContext = mContext;
        dbExecutor =  DBExecutor.getInstance(EbtDBHelper.getInstance(mContext));
    }

    public void insert(BookMarkEbt bookMarkEbt){
        dbExecutor.insert(bookMarkEbt);
    }

    public BookMarkEbt find(long bookId,long ParagraphIndex,long ElementIndex){
        sql = SqlFactory.find(BookMarkEbt.class).
                where("bookId=? and ParagraphIndex=? and ElementIndex=?", new Object[]{bookId, ParagraphIndex, ElementIndex});
        return dbExecutor.executeQueryGetFirstEntry(sql);
    }

    public void delete(long bookId,long ParagraphIndex,long ElementIndex){
        BookMarkEbt bookMarkEbt = find(bookId,ParagraphIndex, ElementIndex);
        if(bookMarkEbt!=null){
            dbExecutor.deleteById(BookMarkEbt.class,bookMarkEbt.getDbId());
        }
    }

}
