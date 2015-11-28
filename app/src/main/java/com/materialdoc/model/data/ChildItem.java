package com.materialdoc.model.data;

import com.google.gson.annotations.SerializedName;

public class ChildItem {

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

}
