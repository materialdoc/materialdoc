package com.materialdoc.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.materialdoc.ui.data.ItemDisplayable;

import java.util.Arrays;
import java.util.List;

public class GsonUtils {

    public static String toJson(@NonNull List<ItemDisplayable> displayableList) {
        Gson gson = new Gson();
        return gson.toJson(displayableList);
    }

    @NonNull
    public static List<ItemDisplayable> fromJson(@NonNull String json) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(json, ItemDisplayable[].class));
    }
}
