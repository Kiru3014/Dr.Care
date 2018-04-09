package com.ecmo.android.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.adaptors.RefpatentAdaptor;
import com.ecmo.android.model.request.CommonRequest;
import com.ecmo.android.model.response.PatReferalList;
import com.ecmo.android.model.response.PatReferalListitem;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.UserPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pankaj on 06-10-2016.
 */
public class RefPatientlistActivity extends BaseActivity {
    List<PatReferalListitem> refpatlist = new ArrayList<PatReferalListitem>();
    UserPreferences userSharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_list);
        userSharedPreferences = new UserPreferences(this);

        getDAta();
    }
    private void getDAta() {

        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
        final CommonRequest commonreq = new CommonRequest("getpatientdetail",userSharedPreferences.getSession());
        Call<PatReferalList> call = apiService.GetAllRefPatientList(commonreq);
        call.enqueue(new Callback<PatReferalList>() {
            @Override
            public void onResponse(Call<PatReferalList> call, Response<PatReferalList> response)
            {
                commonLoaderstop();
                PatReferalList Response= response.body();
                if(Response!=null&&Response.getResult().equals("SUCCESS"))
                {
                    refpatlist=Response.getData();
                    if(refpatlist != null) {

                        setData();
                    }
                    else {
                        commonToast("No Record Found.");
                        finish();
                    }
                }
                else if(Response!=null&&Response.getResult().equalsIgnoreCase("FAIL"))
                {
                    commonToast("No Record Found.");
                    finish();
                }
                else
                {
                    commonToast("Invalid Credentials. try again.");
                    finish();

                }

            }

            @Override
            public void onFailure(Call<PatReferalList> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });






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
