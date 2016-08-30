package com.ebtang.ebtangebook.db.read;

import com.yunqing.core.db.annotations.Id;
import com.yunqing.core.db.annotations.Table;

/**
 * Created by fengzongwei on 2016/8/29 0029.
 * 书的章节
 */
@Table(name="t_read_book_chapter")
public class BookChapter {
    @Id
    private int bookId;//书id
    private String chapterId;//章节Id
    private String name;//章节名称
    private int ParagraphIndex ;//索引值

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParagraphIndex() {
        return ParagraphIndex;
    }

    public void setParagraphIndex(int paragraphIndex) {
        ParagraphIndex = paragraphIndex;
    }
}
