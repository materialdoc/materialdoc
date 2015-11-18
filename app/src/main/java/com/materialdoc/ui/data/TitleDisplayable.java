package com.materialdoc.ui.data;

public class TitleDisplayable extends IViewType {

    private String mTitle;

    public TitleDisplayable(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
