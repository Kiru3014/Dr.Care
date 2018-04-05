package com.ecmo.android.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.ecmo.android.BaseActivity;
import com.ecmo.android.adaptors.GalleryAdapter;
import com.ecmo.android.utils.Constants;
import com.scanning.drcare.R;

public class GalleryActivity extends BaseActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    GalleryAdapter galeryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = (RecyclerView) findViewById(R.id.gridview);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        galeryAdapter = new GalleryAdapter(this, Constants.IMAGES);
        recyclerView.setAdapter(galeryAdapter);
        galeryAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void galleryclick(int position) {
                commonToast("Click" + position);
            }
        });
    }
}
