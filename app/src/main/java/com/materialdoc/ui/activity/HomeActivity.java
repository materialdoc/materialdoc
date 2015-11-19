package com.materialdoc.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.materialdoc.R;
import com.materialdoc.model.data.ParentItem;
import com.materialdoc.ui.adapter.ItemAdapter;
import com.materialdoc.ui.data.IViewType;
import com.materialdoc.ui.data.ItemDisplayable;
import com.materialdoc.ui.data.ItemID;
import com.materialdoc.ui.data.TitleDisplayable;
import com.materialdoc.utils.IOUtils;
import com.materialdoc.utils.L;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        initRecycleView();
        loadDataFromAssets();
    }

    private void initRecycleView() {
        ItemAdapter adapter = new ItemAdapter();
        adapter.setData(toDisplayableList(loadDataFromAssets()));
        adapter.setListener(new ItemAdapter.Listener() {
            @Override
            public void onDocumentClicked(int documentId) {
                handleDocumentClick(documentId);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private List<IViewType> toDisplayableList(@NonNull List<ParentItem> parentItemList) {
        List<IViewType> typeList = new ArrayList<>();

        for (ParentItem parentItemItem : parentItemList) {
            typeList.add(new TitleDisplayable(parentItemItem.title));
            if (parentItemItem.itemsList != null) {
                for (ParentItem.ChildItem item : parentItemItem.itemsList) {
                    ItemDisplayable description = new ItemDisplayable(item.id, item.title, item.description, item.image);
                    typeList.add(description);
                }
            }
        }

        return typeList;
    }

    @NonNull
    private List<ParentItem> loadDataFromAssets() {
        List<ParentItem> parentItemList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("json/data.json");
            String json = IOUtils.toString(inputStream);
            Gson gson = new Gson();
            ParentItem[] parentItemArr = gson.fromJson(json, ParentItem[].class);
            parentItemList.addAll(Arrays.asList(parentItemArr));
        } catch (IOException e) {
            L.e("Error during loading json data list", e);
        }

        return parentItemList;
    }

    private void handleDocumentClick(int documentId) {
        switch (documentId) {

            //Buttons
            case ItemID.RAISED_BUTTON:
                RaisedButtonActivity.start(this);
                break;
            case ItemID.FLAT_BUTTON:
                FlatButtonActivity.start(this);
                break;

            //Progress
            case ItemID.CIRCULAR_PROGRESS:
                CircularProgressActivity.start(this);
                break;
            case ItemID.LINEAR_PROGRESS:
                LinearProgressActivity.start(this);
                break;

            //Selection controls
            case ItemID.CHECK_BOX:
                CheckBoxActivity.start(this);
                break;
            case ItemID.RADIO_BUTTON:
                RadioButtonActivity.start(this);
                break;
            case ItemID.SWITCH:
                SwitchActivity.start(this);
                break;
        }
    }
}
