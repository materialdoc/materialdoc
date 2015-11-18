package com.materialdoc.data;

import android.graphics.drawable.Drawable;

public class DocDescription extends IViewType {

    public int getId() {
        return mId;
    }

    private int mId;
    private String mTitle;
    private String mDescription;
    private Drawable mDrawable;

    public DocDescription(int id, String title, String description, Drawable drawable) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDrawable = drawable;
    }

    public DocDescription() {/*empty*/}

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
    }
}
