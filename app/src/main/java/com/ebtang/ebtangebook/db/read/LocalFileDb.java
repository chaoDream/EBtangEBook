package com.ebtang.ebtangebook.db.read;

import android.content.Context;

import com.ebtang.ebtangebook.db.EbtDBHelper;
import com.yunqing.core.db.DBExecutor;
import com.yunqing.core.db.sql.Sql;
import com.yunqing.core.db.sql.SqlFactory;

import java.util.List;

/**
 * Created by dell on 2016/8/30 0030.
 */
public class LocalFileDb {

    Context mContext;
    DBExecutor dbExecutor = null;
    Sql sql = null;

    public LocalFileDb(Context mContext){
        this.mContext = mContext;
        dbExecutor =  DBExecutor.getInstance(EbtDBHelper.getInstance(mContext));
    }

    public LocalFile find(String path,String name){
        sql = SqlFactory.find(LocalFile.class).
                where("path=? and name=?", new Object[]{path, name});
        return dbExecutor.executeQueryGetFirstEntry(sql);
    }

    public void insert(LocalFile localFile){
        dbExecutor.insert(localFile);
    }

    /**
     * 查询所有本地书籍
     * @return
     */
    public List<LocalFile> findAllBook(){
        sql = SqlFactory.find(LocalFile.class).
                where("flag=?", new Object[]{1}).orderBy("time",true);
        return dbExecutor.executeQuery(sql);
    }

    public void update(LocalFile localFile){
        dbExecutor.updateById(localFile);
    }

}
