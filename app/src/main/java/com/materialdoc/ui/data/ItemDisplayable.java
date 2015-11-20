package com.materialdoc.ui.data;

import java.util.ArrayList;
import java.util.List;

public class ItemDisplayable extends IViewType {

    private int id;
    private String title;
    private String description;
    private String imagePath;

    private List<ItemDisplayable> mDisplayableList;

    public ItemDisplayable(int id, String title, String description, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        mDisplayableList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<ItemDisplayable> getDisplayableList() {
        return mDisplayableList;
    }

    public void setDisplayableList(List<ItemDisplayable> displayableList) {
        mDisplayableList = displayableList;
    }
}
