package com.materialdoc.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentItem {

    @SerializedName("title")
    public String title;

    @SerializedName("itemsArr")
    public List<ChildItem> itemsList;

}
