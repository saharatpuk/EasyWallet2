package com.example.anonymous.easywallet2.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anonymous.easywallet2.R;
import com.example.anonymous.easywallet2.model.MoneyItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Anonymous on 10/12/2560.
 */

public class MoneyItemLists extends ArrayAdapter<MoneyItem> {
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<MoneyItem> mTaleItemList;

    public MoneyItemLists(@NonNull Context context, int layoutResId, @NonNull ArrayList<MoneyItem> taleItemList) {
        super(context, layoutResId, taleItemList);

        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mTaleItemList = taleItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);

        MoneyItem item = mTaleItemList.get(position);

        ImageView phoneImageView = itemLayout.findViewById(R.id.tale_image_view);
        TextView phoneTitleTextView = itemLayout.findViewById(R.id.tale_title_text_view);
        TextView phoneNumberTextView = itemLayout.findViewById(R.id.value);

        phoneTitleTextView.setText(item.title);
        phoneNumberTextView.setText(item.number);

        String pictureFileName = item.picture;

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            phoneImageView.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();

            File pictureFile = new File(mContext.getFilesDir(), pictureFileName);
            Drawable drawable = Drawable.createFromPath(pictureFile.getAbsolutePath());
            phoneImageView.setImageDrawable(drawable);
        }

        return itemLayout;
    }
}
