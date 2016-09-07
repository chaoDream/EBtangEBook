package com.ebtang.ebtangebook.view.setting.bean;

/**
 * Created by fengzongwei on 2016/9/7 0007.
 * 消费记录bean
 */
public class MyYinHaoConsumeBean {

    private String userNick;
    private long bookId;
    private long createTime;
    private String bookName;
    private long toUserId;
    private int isPay;
    private long fromUserId;
    private long bookChapterId;
    private long rewardTypeCount;
    private String reward;
    private int payValue;
    private String fromUserNick;
    private String createTimeFormat;
    private int payType;//	支付类型，1 订阅 2 打赏
    private int subType;//订阅类型，1 整本书 2 单章订阅
    private long payTime;
    private int status;
    private long id;

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getBookChapterId() {
        return bookChapterId;
    }

    public void setBookChapterId(long bookChapterId) {
        this.bookChapterId = bookChapterId;
    }

    public long getRewardTypeCount() {
        return rewardTypeCount;
    }

    public void setRewardTypeCount(long rewardTypeCount) {
        this.rewardTypeCount = rewardTypeCount;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getPayValue() {
        return payValue;
    }

    public void setPayValue(int payValue) {
        this.payValue = payValue;
    }

    public String getFromUserNick() {
        return fromUserNick;
    }

    public void setFromUserNick(String fromUserNick) {
        this.fromUserNick = fromUserNick;
    }

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
