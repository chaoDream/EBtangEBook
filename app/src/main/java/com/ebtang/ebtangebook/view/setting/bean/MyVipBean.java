package com.ebtang.ebtangebook.view.setting.bean;

/**
 * Created by fengzongwei on 16/9/20.
 */
public class MyVipBean {

    private long id;
    private long userId;
    private long balanceValue;
    private long pointValue;
    private int vipValue;
    private int goldenValue;
    private int totalGoldenValue;
    private long fansValue;
    private String nick;
    private int isWriter;
    private String fansValues;
    private String  vipValues;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(long balanceValue) {
        this.balanceValue = balanceValue;
    }

    public long getPointValue() {
        return pointValue;
    }

    public void setPointValue(long pointValue) {
        this.pointValue = pointValue;
    }

    public int getVipValue() {
        return vipValue;
    }

    public void setVipValue(int vipValue) {
        this.vipValue = vipValue;
    }

    public int getGoldenValue() {
        return goldenValue;
    }

    public void setGoldenValue(int goldenValue) {
        this.goldenValue = goldenValue;
    }

    public int getTotalGoldenValue() {
        return totalGoldenValue;
    }

    public void setTotalGoldenValue(int totalGoldenValue) {
        this.totalGoldenValue = totalGoldenValue;
    }

    public long getFansValue() {
        return fansValue;
    }

    public void setFansValue(long fansValue) {
        this.fansValue = fansValue;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getIsWriter() {
        return isWriter;
    }

    public void setIsWriter(int isWriter) {
        this.isWriter = isWriter;
    }

    public String getFansValues() {
        return fansValues;
    }

    public void setFansValues(String fansValues) {
        this.fansValues = fansValues;
    }

    public String getVipValues() {
        return vipValues;
    }

    public void setVipValues(String vipValues) {
        this.vipValues = vipValues;
    }
}
