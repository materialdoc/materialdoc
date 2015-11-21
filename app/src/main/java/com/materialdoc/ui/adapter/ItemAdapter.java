package com.materialdoc.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.materialdoc.R;
import com.materialdoc.ui.data.IViewType;
import com.materialdoc.ui.data.ItemDisplayable;
import com.materialdoc.ui.data.TitleDisplayable;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_TITLE = 1;
    private static final int ITEM_TYPE_DOC = 2;

    private List<IViewType> mDataList;
    private Listener mListener;

    public ItemAdapter() {
        mDataList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_TITLE:
                View confessionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
                return new TitleViewHolder(confessionView);

            case ITEM_TYPE_DOC:
                View categoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_child, parent, false);
                return new DocumentViewHolder(categoryView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_TYPE_TITLE) {
            initTitle(holder, position);
        } else if (getItemViewType(position) == ITEM_TYPE_DOC) {
            initDoc(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        IViewType type = mDataList.get(position);
        if (type instanceof TitleDisplayable) {
            return ITEM_TYPE_TITLE;
        } else if (type instanceof ItemDisplayable) {
            return ITEM_TYPE_DOC;
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setListener(@NonNull Listener listener) {
        mListener = listener;
    }

    public void setData(@NonNull List<? extends IViewType> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
    }

    private void initTitle(@NonNull RecyclerView.ViewHolder holder, int position) {
        TitleViewHolder viewHolder = (TitleViewHolder) holder;
        TitleDisplayable title = (TitleDisplayable) mDataList.get(position);
        fillTextField(viewHolder.txtTitle, title.getTitle());
    }

    private void initDoc(@NonNull RecyclerView.ViewHolder holder, int position) {
        DocumentViewHolder viewHolder = (DocumentViewHolder) holder;
        final ItemDisplayable displayable = (ItemDisplayable) mDataList.get(position);

        fillTextField(viewHolder.txtTitle, displayable.getTitle());
        fillTextField(viewHolder.txtDescription, displayable.getDescription());

//        Context context = viewHolder.imgIcon.getContext().getApplicationContext();
//
//        if(TextUtils.isEmpty(displayable.getImagePath())) {
//            viewHolder.imageHolder.setVisibility(View.GONE);
//        } else {
//            String path = String.format("file:///android_asset/%s", displayable.getImagePath());
//            L.d(String.format("Loading image: %s", path));
//
//            Picasso.with(context)
//                    .load(path)
//                    .placeholder(R.drawable.ic_material)
//                    .error(R.drawable.ic_material)
//                    .into(viewHolder.imgIcon);
//            viewHolder.imageHolder.setVisibility(View.VISIBLE);
//        }

        viewHolder.layoutDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDocumentClicked(displayable);
                }
            }
        });
    }

    private void fillTextField(@NonNull TextView txtView, @Nullable String text) {
        if (!TextUtils.isEmpty(text)) {
            txtView.setText(text);
        }
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;

        public TitleViewHolder(View v) {
            super(v);

            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        }
    }

    private static class DocumentViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout layoutDoc;
        private TextView txtTitle;
        private TextView txtDescription;
//        private ImageView imgIcon;
//        private ViewGroup imageHolder;

        public DocumentViewHolder(View v) {
            super(v);

            layoutDoc = (LinearLayout) v.findViewById(R.id.layoutDoc);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtDescription = (TextView) v.findViewById(R.id.txtDescription);
//            imgIcon = (ImageView) v.findViewById(R.id.imgIcon);
//            imageHolder = (ViewGroup) v.findViewById(R.id.imageHolder);
        }
    }

    public interface Listener {
        void onDocumentClicked(ItemDisplayable displayable);
    }
}
