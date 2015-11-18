package com.materialdoc.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.materialdoc.R;
import com.materialdoc.adapter.DocAdapter;
import com.materialdoc.data.DocDescription;
import com.materialdoc.data.DocInfo;
import com.materialdoc.data.DocTitle;
import com.materialdoc.data.IViewType;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_home);
        initRecycleView();
    }

    private void initRecycleView() {
        DocAdapter adapter = new DocAdapter();
        adapter.setData(getTypeList());
        adapter.setListener(new DocAdapter.Listener() {
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
    private List<IViewType> getTypeList() {
        List<IViewType> typeList = new ArrayList<>();
        Drawable icon = getResources().getDrawable(R.drawable.ic_material);

        //Buttons
        typeList.add(new DocTitle(getString(R.string.buttons)));
        typeList.add(new DocDescription(DocInfo.ID_RAISED_BUTTON, getString(R.string.raised_button), getString(R.string.raised_button_desc_short), icon));
        typeList.add(new DocDescription(DocInfo.ID_FLAT_BUTTON, getString(R.string.flat_button), getString(R.string.flat_button_desc_short), icon));

        //Progress
        typeList.add(new DocTitle(getString(R.string.progress)));
        typeList.add(new DocDescription(DocInfo.ID_CIRCULAR_PROGRESS, getString(R.string.circular_progress), getString(R.string.circular_progress_desc_short), icon));
        typeList.add(new DocDescription(DocInfo.ID_LINEAR_PROGRESS, getString(R.string.linear_progress), getString(R.string.linear_progress_desc_short), icon));

        //Selection Controls
        typeList.add(new DocTitle(getString(R.string.selectrion_controls)));
        typeList.add(new DocDescription(DocInfo.ID_CHECK_BOX, getString(R.string.check_box), getString(R.string.check_box_desc_short), icon));
        typeList.add(new DocDescription(DocInfo.ID_RADIO_BUTTON, getString(R.string.radio_button), getString(R.string.radio_button_desc_short), icon));
        typeList.add(new DocDescription(DocInfo.ID_SWITCH, getString(R.string.Switch), getString(R.string.switch_desc_short), icon));

        //Text Fields
        typeList.add(new DocTitle(getString(R.string.text_fields)));
        typeList.add(new DocDescription(DocInfo.ID_TEXT_FIELD, getString(R.string.text_field), getString(R.string.text_field_desc_short), icon));

        return typeList;
    }

    private void handleDocumentClick(int documentId) {
        switch (documentId) {

            //Buttons
            case DocInfo.ID_RAISED_BUTTON:
                RaisedButtonActivity.start(this);
                break;
            case DocInfo.ID_FLAT_BUTTON:
                FlatButtonActivity.start(this);
                break;

            //Progress
            case DocInfo.ID_CIRCULAR_PROGRESS:
                CircularProgressActivity.start(this);
                break;
            case DocInfo.ID_LINEAR_PROGRESS:
                LinearProgressActivity.start(this);
                break;
        }
    }
}
