package com.ecmo.android.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ecmo.android.R;
import com.ecmo.android.adaptors.RefpatentAdaptor;
import com.ecmo.android.model.response.PatReferalListdata;
import com.ecmo.android.utils.UserPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pankaj on 06-10-2016.
 */
public class RefPatientlistActivity extends AppCompatActivity {
    List<PatReferalListdata> refpatlist = new ArrayList<PatReferalListdata>();
    UserPreferences userSharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_list);
        userSharedPreferences = new UserPreferences(this);

        getDAta();
    }
    private void getDAta() {
// get data from api
        if(refpatlist != null) {

            setData();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "OrderHistory Not Available ", Toast.LENGTH_SHORT).show();
            finish();

        }
    }

    private void setData() {
        RecyclerView my_recycler_view = findViewById(R.id.recycler_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setItemViewCacheSize(20);
        my_recycler_view.setDrawingCacheEnabled(true);
        RefpatentAdaptor adapter = new RefpatentAdaptor(this, refpatlist);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
