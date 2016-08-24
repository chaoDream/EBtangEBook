package com.ebtang.ebtangebook.event;

/**
 * Created by fengzongwei on 2016/8/24 0024.
 * 音量键切换页面
 */
public class VolumeChangePage {
    boolean isOpen = true;//是否启用
    public VolumeChangePage(boolean isOpen){
        this.isOpen = isOpen;
    }
    public boolean getIsOpen(){
        return isOpen;
    }
}
