package com.materialdoc.ui.activity.home;

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
import com.materialdoc.ui.activity.FloatingButtonActivity;
import com.materialdoc.ui.activity.BlankToolbarActivity;
import com.materialdoc.ui.activity.AlertDialogActivity;
import com.materialdoc.ui.activity.BackToolbarActivity;
import com.materialdoc.ui.activity.CardViewActivity;
import com.materialdoc.ui.activity.CheckBoxActivity;
import com.materialdoc.ui.activity.CircularProgressActivity;
import com.materialdoc.ui.activity.ConfirmationDialogActivity;
import com.materialdoc.ui.activity.DefaultToolbarActivity;
import com.materialdoc.ui.activity.FlatButtonActivity;
import com.materialdoc.ui.activity.IconsToolbarActivity;
import com.materialdoc.ui.activity.LinearProgressActivity;
import com.materialdoc.ui.activity.RadioButtonActivity;
import com.materialdoc.ui.activity.RaisedButtonActivity;
import com.materialdoc.ui.activity.RatingBarActivity;
import com.materialdoc.ui.activity.SwitchActivity;
import com.materialdoc.ui.activity.ToastActivity;
import com.materialdoc.ui.activity.StyledToolbarActivity;
import com.materialdoc.ui.activity.input.InputActivity;
import com.materialdoc.ui.activity.input.InputErrorLabelActivity;
import com.materialdoc.ui.activity.input.InputFloatingLabelActivity;
import com.materialdoc.ui.activity.input.InputFullWidthActivity;
import com.materialdoc.ui.activity.input.InputMultiLineActivity;
import com.materialdoc.ui.activity.input.InputSingleLineActivity;
import com.materialdoc.ui.activity.tab.TabActivity;
import com.materialdoc.ui.activity.tab.TabIconActivity;
import com.materialdoc.ui.activity.tab.TabMultiActivity;
import com.materialdoc.ui.activity.tab.TabStyledActivity;
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

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        initRecycleView();
        loadData();
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

    private void loadData() {
        Observable.create(loadDataFromAssets())
                .map(toDisplayableList())
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
    private Func1<List<ParentItem>, List<IViewType>> toDisplayableList() {
        return new Func1<List<ParentItem>, List<IViewType>>() {
            @Override
            public List<IViewType> call(List<ParentItem> parentItemList) {
                List<IViewType> typeList = new ArrayList<>();
                for (ParentItem parentItem : parentItemList) {
                    typeList.add(new TitleDisplayable(parentItem.title));
                    if (parentItem.itemsList == null) {
                        continue;
                    }

                    for (ChildItem item : parentItem.itemsList) {
                        typeList.add(toDisplayable(item));
                    }
                }

                return typeList;
            }
        };
    }

    @NonNull
    private ItemDisplayable toDisplayable(ChildItem item) {
        ItemDisplayable displayable = new ItemDisplayable();
        displayable.setId(item.id);
        displayable.setTitle(item.title);
        displayable.setDescription(item.description);
        return displayable;
    }

    private void handleDocumentClick(ItemDisplayable displayable) {
        int documentId = displayable.getId();
        switch (documentId) {

            //buttons
            case ItemID.RAISED_BUTTON:
                RaisedButtonActivity.start(this);
                break;
            case ItemID.FLAT_BUTTON:
                FlatButtonActivity.start(this);
                break;
            case ItemID.FLOATING_ACTION_BUTTONS:
                FloatingButtonActivity.start(this);
                break;

            //cards
            case ItemID.CARD_VIEW:
                CardViewActivity.start(this);
                break;

            //chips
            case ItemID.CHIP:
                break;

            //dialog
            case ItemID.ALERT:
                AlertDialogActivity.start(this);
                break;
            case ItemID.CONFIRMATION_DIALOGS:
                ConfirmationDialogActivity.start(this);
                break;
            case ItemID.FULL_SCREEN_DIALOGS:
                break;

            //menu
            case ItemID.MENU:
                break;

            //pickers
            case ItemID.PICKER:
                break;

            //progress
            case ItemID.CIRCULAR_PROGRESS:
                CircularProgressActivity.start(this);
                break;
            case ItemID.LINEAR_PROGRESS:
                LinearProgressActivity.start(this);
                break;
            case ItemID.SWIPE_DOWN_TO_REFRESH:
                break;
            case ItemID.SCROLL_UP_TO_LOAD_MORE:
                break;

            //selection controls
            case ItemID.CHECK_BOX:
                CheckBoxActivity.start(this);
                break;
            case ItemID.RADIO_BUTTON:
                RadioButtonActivity.start(this);
                break;
            case ItemID.SWITCH:
                SwitchActivity.start(this);
                break;

            //sliders
            case ItemID.CONTINUOUS_SLIDER:
                break;

            //message alerts
            case ItemID.SNACK_BAR:
//                SnackBarActivity.start(this);
                break;
            case ItemID.TOAST:
                ToastActivity.start(this);
                break;

            //tabs
            case ItemID.TABS_TEXT_ONLY:
                TabActivity.start(this);
                break;
            case ItemID.TABS_ICON_ONLY:
                TabIconActivity.start(this);
                break;
            case ItemID.TABS_ICON_AND_TEXT:
                TabMultiActivity.start(this);
                break;
            case ItemID.TABS_STYLED:
                TabStyledActivity.start(this);
                break;

            //text field
            case ItemID.INPUT_FIELD:
                InputActivity.start(this);
                break;
            case ItemID.INPUT_FIELD_SINGLE_LINE:
                InputSingleLineActivity.start(this);
                break;
            case ItemID.INPUT_FIELD_MULTI_LINE:
                InputMultiLineActivity.start(this);
                break;
            case ItemID.INPUT_FIELD_FULL_WIDTH:
                InputFullWidthActivity.start(this);
                break;
            case ItemID.INPUT_FIELD_FLOATING_LABEL:
                InputFloatingLabelActivity.start(this);
                break;
            case ItemID.INPUT_FIELD_ERROR_LABEL:
                InputErrorLabelActivity.start(this);
                break;

            //toolbars
            case ItemID.TOOLBAR_DEFAULT:
                DefaultToolbarActivity.start(this);
                break;
            case ItemID.TOOLBAR_STYLED:
                StyledToolbarActivity.start(this);
                break;
            case ItemID.TOOLBAR_ICONS:
                IconsToolbarActivity.start(this);
                break;
            case ItemID.TOOLBAR_BACK:
                BackToolbarActivity.start(this);
                break;
            case ItemID.TOOLBAR_BLANK:
                BlankToolbarActivity.start(this);
                break;

            //other
            case ItemID.RATING_BAR:
                RatingBarActivity.start(this);
                break;
        }
    }
}
