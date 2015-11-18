package com.materialdoc.data;

public class DocTitle extends IViewType {

    private String mTitle;

    public DocTitle(String title) {
        mTitle = title;
    }

    public DocTitle() {/*empty*/}

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }
}
