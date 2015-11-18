package com.materialdoc.ui.data;

import android.graphics.drawable.Drawable;

public class ItemDisplayable extends IViewType {

    private int mId;
    private String mTitle;
    private String mDescription;
    private Drawable mDrawable;

    public ItemDisplayable(int id, String title, String description, Drawable drawable) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mDrawable = drawable;
    }

    public int getId() {
        return mId;
    }

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
