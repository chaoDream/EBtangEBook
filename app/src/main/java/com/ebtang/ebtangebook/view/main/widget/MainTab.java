package com.ebtang.ebtangebook.view.main.widget;


import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.main.fragment.BookCityFragment;
import com.ebtang.ebtangebook.view.main.fragment.BookShelfFragment;
import com.ebtang.ebtangebook.view.main.fragment.FoundFragment;
import com.ebtang.ebtangebook.view.main.fragment.PersenalFragment;

public enum MainTab {

	/*
	NEWS(0, R.string.main_tab_name_news, R.drawable.tab_icon_new,
			NewsViewPagerFragment.class),
			*/

    NEWS(0,R.string.tab_bookshelf, R.drawable.tab_bookshelf,
            BookShelfFragment.class),

    TWEET(1,R.string.tab_bookcity, R.drawable.tab_bookcity,
            BookCityFragment.class),

    QUICK(2,R.string.tab_found, R.drawable.tab_found,
            FoundFragment.class),

    EXPLORE(3,R.string.tab_persenal, R.drawable.tab_persenal,
            PersenalFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
