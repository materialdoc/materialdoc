package com.materialdoc.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.materialdoc.R;
import com.materialdoc.model.data.ChildItem;
import com.materialdoc.model.data.ParentItem;
import com.materialdoc.ui.adapter.ItemAdapter;
import com.materialdoc.ui.data.IViewType;
import com.materialdoc.ui.data.ItemDisplayable;
import com.materialdoc.ui.data.ItemID;
import com.materialdoc.ui.data.TitleDisplayable;
import com.materialdoc.utils.GsonUtils;
import com.materialdoc.utils.IOUtils;
import com.materialdoc.utils.L;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    private static final String EXTRAS_DATA = "EXTRAS_DATA";

    private ItemAdapter mAdapter;

    public static Intent createIntent(@NonNull Context context, @NonNull List<ItemDisplayable> displayableList) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(EXTRAS_DATA, GsonUtils.toJson(displayableList));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        initRecycleView();

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRAS_DATA)) {
            loadItemsFromExtras(extras);
        } else {
            loadRootItems();
        }
    }

    private void initRecycleView() {
        mAdapter = new ItemAdapter();
        mAdapter.setListener(new ItemAdapter.Listener() {
            @Override
            public void onDocumentClicked(ItemDisplayable displayable) {
                handleDocumentClick(displayable);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void loadItemsFromExtras(Bundle extras) {
        String json = extras.getString(EXTRAS_DATA);
        if (json != null) {
            mAdapter.setData(GsonUtils.fromJson(json));
        }
    }

    private void loadRootItems() {
        Observable.create(loadDataFromAssets())
                .map(toDisplayable())
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<IViewType>>() {
                    @Override
                    public void call(List<IViewType> dataList) {
                        mAdapter.setData(dataList);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable e) {
                        L.e("Error during loading json data list", e);
                    }
                });
    }

    @NonNull
    private Observable.OnSubscribe<List<ParentItem>> loadDataFromAssets() {
        return new Observable.OnSubscribe<List<ParentItem>>() {
            @Override
            public void call(Subscriber<? super List<ParentItem>> subscriber) {

                try {
                    InputStream inputStream = getAssets().open("json/data.json");
                    String json = IOUtils.toString(inputStream);
                    Gson gson = new Gson();
                    ParentItem[] parentItemArr = gson.fromJson(json, ParentItem[].class);

                    subscriber.onNext(Arrays.asList(parentItemArr));
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        };
    }

    @NonNull
    private Func1<List<ParentItem>, List<IViewType>> toDisplayable() {
        return new Func1<List<ParentItem>, List<IViewType>>() {
            @Override
            public List<IViewType> call(List<ParentItem> parentItemList) {
                List<IViewType> typeList = new ArrayList<>();

                for (ParentItem parentItem : parentItemList) {
                    typeList.add(new TitleDisplayable(parentItem.title));
                    if (parentItem.itemsList != null) {
                        for (ChildItem item : parentItem.itemsList) {
                            ItemDisplayable displayable = new ItemDisplayable(item.id, item.title, item.description, item.image);
                            if (item.mParentItemList != null) {
                                for (ChildItem child : item.mParentItemList) {
                                    displayable.getDisplayableList().add(new ItemDisplayable(child.id, child.title, child.description, child.image));
                                }
                            }

                            typeList.add(displayable);
                        }
                    }
                }

                return typeList;
            }
        };
    }

    private void handleDocumentClick(ItemDisplayable displayable) {
        int documentId = displayable.getId();
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

            //edit fields
            case ItemID.TEXT_FIELD:
                Intent intent = HomeActivity.createIntent(getApplicationContext(), displayable.getDisplayableList());
                startActivity(intent);
                break;

            //other
            case ItemID.RATING_BAR:
                RatingBarActivity.start(this);
                break;
        }
    }
}
