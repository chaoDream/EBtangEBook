package com.ebtang.ebtangebook.db.read;

import com.yunqing.core.db.annotations.Id;
import com.yunqing.core.db.annotations.Table;

/**
 * Created by fengzongwei on 2016/8/30 0030.
 */
@Table(name="t_read_book_local_file")
public class LocalFile {

    @Id
    private int dbId;
    private String path;
    private String name;
    private long bookIdInFb;//本书在fb中生成的id
    private int flag = 1;
    private int type;//文件类型  1.txt  2.epub
    private long time;//保存、修改等操作的时间
    private int localBook;//是否是本地查找或搜索导入的书籍 0.不是 1.是

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public long getBookIdInFb() {
        return bookIdInFb;
    }

    public void setBookIdInFb(long bookIdInFb) {
        this.bookIdInFb = bookIdInFb;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getLocalBook() {
        return localBook;
    }

    public void setLocalBook(int localBook) {
        this.localBook = localBook;
    }
}
