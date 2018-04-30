package com.ecmo.android.activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.adaptors.RefpatentAdaptor;
import com.ecmo.android.model.PatReferalListitemArray;
import com.ecmo.android.model.request.CommonRequest;
import com.ecmo.android.model.response.PatReferalList;
import com.ecmo.android.model.response.PatReferalListitem;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pankaj on 06-10-2016.
 */
public class RefPatientlistActivity extends BaseActivity implements View.OnClickListener {
    List<PatReferalListitem> refpatlist = new ArrayList<PatReferalListitem>();
    UserPreferences userSharedPreferences;
    RecyclerView my_recycler_view;
    TextView mtvnew, mtvapprovel, mtvreject, mtvhold;
    ArrayList<PatReferalListitemArray> patReferalListitems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_list);
        userSharedPreferences = new UserPreferences(this);
        my_recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mtvnew = (TextView) findViewById(R.id.tv_new);
        mtvapprovel = (TextView) findViewById(R.id.tv_approved);
        mtvreject = (TextView) findViewById(R.id.tv_reject);
        mtvhold = (TextView) findViewById(R.id.tv_hold);
        patReferalListitems = new ArrayList<PatReferalListitemArray>();

        mtvnew.setOnClickListener(this);
        mtvapprovel.setOnClickListener(this);
        mtvreject.setOnClickListener(this);
        mtvhold.setOnClickListener(this);

        mtvnew.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtvapprovel.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtvreject.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtvhold.setTypeface(Helper.getSharedHelper().getSemiBoldFont());



    }


    @Override
    protected void onResume() {
        super.onResume();
        getDAta();
    }

    private void getDAta()
    {
        mtvnew.setTextColor(Color.parseColor("#FF4081"));
        mtvapprovel.setTextColor(Color.parseColor("#00c373"));
        mtvreject.setTextColor(Color.parseColor("#ff0040"));
        mtvhold.setTextColor(Color.parseColor("#ffc461"));
        mtvnew.setBackgroundResource(R.drawable.put_away);
        mtvapprovel.setBackgroundResource(R.drawable.put_away);
        mtvreject.setBackgroundResource(R.drawable.put_away);
        mtvhold.setBackgroundResource(R.drawable.put_away);

        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
        final CommonRequest commonreq = new CommonRequest("getpatientdetail", userSharedPreferences.getSession());
        Call<PatReferalList> call = apiService.GetAllRefPatientList(commonreq);
        call.enqueue(new Callback<PatReferalList>() {
            @Override
            public void onResponse(Call<PatReferalList> call, Response<PatReferalList> response) {
                commonLoaderstop();
                PatReferalList Response = response.body();
                if (response != null && response.body().getResult().equalsIgnoreCase("FAILED") && response.message().contains(Constants.AUTH_FAIL)) {
                    LogoutSession();
                } else if (Response != null && Response.getResult().equals("SUCCESS")) {
                    refpatlist = Response.getData();
                    if (refpatlist != null && refpatlist.size() > 0) {
                        for (int i = 0; i < refpatlist.size(); i++) {
                            patReferalListitems.add(new PatReferalListitemArray(
                                    refpatlist.get(i).getReferalformid(),
                                    refpatlist.get(i).getStatus(),
                                    refpatlist.get(i).getPat_name(),
                                    refpatlist.get(i).getPat_Hospital(),
                                    refpatlist.get(i).getDiagnosis(),
                                    refpatlist.get(i).getPat_age(),
                                    refpatlist.get(i).getRefdate()
                            ));
                        }

                        setData(patReferalListitems);
                    } else {
                        commonToast("No Record Found.");
                        finish();
                    }
                } else if (Response != null && Response.getResult().equalsIgnoreCase("FAIL")) {
                    commonToast("No Record Found.");
                    finish();
                } else {
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

    }

    private void setData(ArrayList<PatReferalListitemArray> refpatlist) {
        commonLoaderstart();
        if (refpatlist != null && refpatlist.size() > 0) {
            my_recycler_view.setHasFixedSize(true);
            my_recycler_view.setItemViewCacheSize(20);
            my_recycler_view.setDrawingCacheEnabled(true);
            RefpatentAdaptor adapter = new RefpatentAdaptor(this, refpatlist);
            adapter.setviewclickListner(listner);
            my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            my_recycler_view.setAdapter(adapter);
            commonLoaderstop();
        } else {
            commonToast("No Application Found");
        }
    }

    RefpatentAdaptor.OnViewItemClickListener listner = new RefpatentAdaptor.OnViewItemClickListener() {
        @Override
        public void onviewClick(String formid) {
            Intent intent = new Intent(getApplicationContext(), ViewReferalForm.class);
            intent.putExtra("formid", formid);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_new:
                FilterResult("NEW");
                mtvnew.setTextColor(Color.parseColor("#FFFFFF"));
                mtvapprovel.setTextColor(Color.parseColor("#00c373"));
                mtvreject.setTextColor(Color.parseColor("#ff0040"));
                mtvhold.setTextColor(Color.parseColor("#ffc461"));
                mtvnew.setBackgroundColor(Color.parseColor("#FF4081"));
                mtvapprovel.setBackgroundResource(R.drawable.put_away);
                mtvreject.setBackgroundResource(R.drawable.put_away);
                mtvhold.setBackgroundResource(R.drawable.put_away);
                break;
            case R.id.tv_approved:
                FilterResult("APPROVED");
                mtvnew.setTextColor(Color.parseColor("#FF4081"));
                mtvapprovel.setTextColor(Color.parseColor("#FFFFFF"));
                mtvreject.setTextColor(Color.parseColor("#ff0040"));
                mtvhold.setTextColor(Color.parseColor("#ffc461"));
                mtvnew.setBackgroundResource(R.drawable.put_away);
                mtvapprovel.setBackgroundColor(Color.parseColor("#00c373"));
                mtvreject.setBackgroundResource(R.drawable.put_away);
                mtvhold.setBackgroundResource(R.drawable.put_away);
                break;
            case R.id.tv_reject:
                FilterResult("REJECT");
                mtvnew.setTextColor(Color.parseColor("#FF4081"));
                mtvapprovel.setTextColor(Color.parseColor("#00c373"));
                mtvreject.setTextColor(Color.parseColor("#FFFFFF"));
                mtvhold.setTextColor(Color.parseColor("#ffc461"));
                mtvnew.setBackgroundResource(R.drawable.put_away);
                mtvapprovel.setBackgroundResource(R.drawable.put_away);
                mtvreject.setBackgroundColor(Color.parseColor("#ff0040"));
                mtvhold.setBackgroundResource(R.drawable.put_away);
                break;
            case R.id.tv_hold:
                FilterResult("HOLD");
                mtvnew.setTextColor(Color.parseColor("#FF4081"));
                mtvapprovel.setTextColor(Color.parseColor("#00c373"));
                mtvreject.setTextColor(Color.parseColor("#ff0040"));
                mtvhold.setTextColor(Color.parseColor("#FFFFFF"));
                mtvnew.setBackgroundResource(R.drawable.put_away);
                mtvapprovel.setBackgroundResource(R.drawable.put_away);
                mtvreject.setBackgroundResource(R.drawable.put_away);
                mtvhold.setBackgroundColor(Color.parseColor("#ffc461"));
                break;
        }
    }

    private void FilterResult(String filtertype) {
        final ArrayList<PatReferalListitemArray> tempArrayList = new ArrayList<PatReferalListitemArray>();

        for (PatReferalListitemArray patReferalListitem : patReferalListitems) {
            if (filtertype.equalsIgnoreCase("APPROVED")) {
                if (patReferalListitem.getStatus().toUpperCase().equalsIgnoreCase("APPROVED")) {
                    tempArrayList.add(patReferalListitem);
                }
            } else if (filtertype.equalsIgnoreCase("REJECT")) {
                if (patReferalListitem.getStatus().toUpperCase().equalsIgnoreCase("REJECT")) {
                    tempArrayList.add(patReferalListitem);
                }
            } else if (filtertype.equalsIgnoreCase("HOLD")) {
                if (patReferalListitem.getStatus().toUpperCase().equalsIgnoreCase("HOLD")) {
                    tempArrayList.add(patReferalListitem);
                }
            } else if (filtertype.equalsIgnoreCase("NEW")) {
                if (patReferalListitem.getStatus().toUpperCase().equalsIgnoreCase("NEW")) {
                    tempArrayList.add(patReferalListitem);
                }
            }

        }

        setData(tempArrayList);

    }
}
