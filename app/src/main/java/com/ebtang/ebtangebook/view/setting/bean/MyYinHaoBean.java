package com.ebtang.ebtangebook.view.setting.bean;

/**
 * Created by fengzongwei on 2016/9/5 0005.
 */
public class MyYinHaoBean {

    private long userId;
    private String nick;
    private int vipValue;//vip等级
    private int vipValues;
    private long fansValue;//粉丝值
    private int goldenValue;//保底金票
    private int balanceValue;//余额（单位：分
    private long totalGoldenValue;//投出的所有金票数量
    private int fansValues;
    private int isWriter;
    private long pointValue;//积分
    private long id;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getVipValue() {
        return vipValue;
    }

    public void setVipValue(int vipValue) {
        this.vipValue = vipValue;
    }

    public int getVipValues() {
        return vipValues;
    }

    public void setVipValues(int vipValues) {
        this.vipValues = vipValues;
    }

    public long getFansValue() {
        return fansValue;
    }

    public void setFansValue(long fansValue) {
        this.fansValue = fansValue;
    }

    public int getGoldenValue() {
        return goldenValue;
    }

    public void setGoldenValue(int goldenValue) {
        this.goldenValue = goldenValue;
    }

    public int getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(int balanceValue) {
        this.balanceValue = balanceValue;
    }

    public long getTotalGoldenValue() {
        return totalGoldenValue;
    }

    public void setTotalGoldenValue(long totalGoldenValue) {
        this.totalGoldenValue = totalGoldenValue;
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

    public long getPointValue() {
        return pointValue;
    }

    public void setPointValue(long pointValue) {
        this.pointValue = pointValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
