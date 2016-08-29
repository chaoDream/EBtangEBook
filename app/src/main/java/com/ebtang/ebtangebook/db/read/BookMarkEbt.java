package com.ebtang.ebtangebook.db.read;

import com.yunqing.core.db.annotations.Id;
import com.yunqing.core.db.annotations.Table;

/**
 * Created by fengzongwei on 2016/8/29 0029.
 * 书签本地存储
 */
@Table(name="t_read_book_mark")
public class BookMarkEbt {
    @Id
    private int dbId;
    private long bookId;
    private int ParagraphIndex;
    private int ElementIndex;

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getParagraphIndex() {
        return ParagraphIndex;
    }

    public void setParagraphIndex(int paragraphIndex) {
        ParagraphIndex = paragraphIndex;
    }

    public int getElementIndex() {
        return ElementIndex;
    }

    public void setElementIndex(int elementIndex) {
        ElementIndex = elementIndex;
    }
}
