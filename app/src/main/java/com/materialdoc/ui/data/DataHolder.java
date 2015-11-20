package com.materialdoc.ui.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DataHolder {

    INSTANCE;

    private Map<TitleDisplayable, List<ItemDisplayable>> mMap;

    DataHolder() {
        mMap = new HashMap<>();
    }

    public Map<TitleDisplayable, List<ItemDisplayable>> getMap() {
        return mMap;
    }
}
