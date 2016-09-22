package com.ebtang.ebtangebook.view.setting.bean;

/**
 * Created by fengzongwei on 2016/9/21 0021.
 * 具体消息
 */
public class MyMsgBean {

    private long id;
    private long replyId;
    private long userId;
    private long replyUserId;
    private long bookId;
    private long bookChapterId;
    private String content;
    private String replyContent;
    private int status;
    private long createTime;
    private int isTop;
    private int isHot;
    private String title;
    private String userNick;
    private int vipValues;
    private int fansValues;
    private int isWriter;
    private String userPic;
    private String createTimeFormat;
    private String statusCN;
    private String bookName;
    private String bookChapterName;
    private String shortContent;
    private boolean range;
    private String replyCommentList;
    private int replyComentCount;

    /**
     * 自增部分
     * @return
     */
    private boolean isShowEditText = false;//是否显示输入框
    private boolean isCanPsot = true;//是否可以提交回复

    public boolean isShowEditText() {
        return isShowEditText;
    }

    public void setIsShowEditText(boolean isShowEditText) {
        this.isShowEditText = isShowEditText;
    }

    public boolean isCanPsot() {
        return isCanPsot;
    }

    public void setIsCanPsot(boolean isCanPsot) {
        this.isCanPsot = isCanPsot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReplyId() {
        return replyId;
    }

    public void setReplyId(long replyId) {
        this.replyId = replyId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getBookChapterId() {
        return bookChapterId;
    }

    public void setBookChapterId(long bookChapterId) {
        this.bookChapterId = bookChapterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public int getVipValues() {
        return vipValues;
    }

    public void setVipValues(int vipValues) {
        this.vipValues = vipValues;
    }

    public int getFansValues() {
        return fansValues;
    }

    public void setFansValues(int fansValues) {
        this.fansValues = fansValues;
    }

    public int getIsWriter() {
        return isWriter;
    }

    public void setIsWriter(int isWriter) {
        this.isWriter = isWriter;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookChapterName() {
        return bookChapterName;
    }

    public void setBookChapterName(String bookChapterName) {
        this.bookChapterName = bookChapterName;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public boolean isRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public String getReplyCommentList() {
        return replyCommentList;
    }

    public void setReplyCommentList(String replyCommentList) {
        this.replyCommentList = replyCommentList;
    }

    public int getReplyComentCount() {
        return replyComentCount;
    }

    public void setReplyComentCount(int replyComentCount) {
        this.replyComentCount = replyComentCount;
    }
}
