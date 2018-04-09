package com.ecmo.android.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.adaptors.DocListAdaptor;
import com.ecmo.android.model.request.CommonRequest;
import com.ecmo.android.model.request.DrApproveRejectRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.model.response.DocListItem;
import com.ecmo.android.model.response.DoctorList;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.UserPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocListActivity  extends BaseActivity {
    List<DocListItem> doclist = new ArrayList<>();
    UserPreferences userSharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_list);
        TextView heading= findViewById(R.id.img_login_logo);
        heading.setText("Doctors Registration");
        userSharedPreferences = new UserPreferences(this);

        getDAta();
    }
    private void getDAta() {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final CommonRequest commonreq = new CommonRequest("getdrdetail",userSharedPreferences.getSession());
        Call<DoctorList> call = apiService.GetAllDoctorDetails(commonreq);
        call.enqueue(new Callback<DoctorList>() {
            @Override
            public void onResponse(Call<DoctorList> call, Response<DoctorList> response)
            {
                commonLoaderstop();
                DoctorList Response= response.body();
                if(Response!=null&&Response.getResult().equals("SUCCESS"))
                {
                    doclist=Response.getData();
                    if(doclist != null) {
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
            public void onFailure(Call<DoctorList> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });
    }

    private void setData() {
        RecyclerView my_recycler_view = findViewById(R.id.recycler_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setItemViewCacheSize(20);
        my_recycler_view.setDrawingCacheEnabled(true);
        DocListAdaptor adapter = new DocListAdaptor(this, doclist);
        adapter.setactionListner(actionClickListener);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);
    }



    private DocListAdaptor.OndocItemClickListener actionClickListener= new DocListAdaptor.OndocItemClickListener() {
        @Override
        public void onApproveClick(String docid) {
            approveDoc(docid);
        }

        @Override
        public void onRejectClick(String docid) {
            rejectDoc(docid);
        }
    };

    private void rejectDoc(String docid) {
        showRejectConfirm(docid,DocListActivity.this);

    }


    private void approveDoc(String docid) {
        showApproveConfirm(docid,DocListActivity.this);
    }

    public void showRejectConfirm(final String docid, Context c) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        alertDialogBuilder.setTitle("REJECT DOCTOR REGISTRATION ?");

        alertDialogBuilder.setPositiveButton("NO",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });

        alertDialogBuilder.setNegativeButton("YES",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reject(docid);
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void showApproveConfirm(final String docid, Context c) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        alertDialogBuilder.setTitle("APPROVE DOCTOR LOGIN ?");

        alertDialogBuilder.setPositiveButton("NO",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });

        alertDialogBuilder.setNegativeButton("YES",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        approve(docid);
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void approve(String docid) {
            commonLoaderstart();
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            final DrApproveRejectRequest request = new DrApproveRejectRequest(docid,userSharedPreferences.getUserId(),"Approved", userSharedPreferences.getSession());
            Call<CommonResponse> call = apiService.docApproveRequest(request);

            call.enqueue(new Callback<CommonResponse>() {
                @Override
                public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response)
                {
                    commonLoaderstop();
                    if(response!=null&&response.body().getResult().equalsIgnoreCase("success"))
                    {
                        commonToast("Doctor Registration Approved.");
                    }
                    else
                    {
                        commonToast("Try Again Later");

                    }
                }

                @Override
                public void onFailure(Call<CommonResponse> call, Throwable t) {
                    commonLoaderstop();
                    commonToast("Network Issue Please Try Again");

                }
            });
    }

    private void reject(String docid) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final DrApproveRejectRequest request = new DrApproveRejectRequest(docid,userSharedPreferences.getUserId(),"Disapproved", userSharedPreferences.getSession());
        Call<CommonResponse> call = apiService.docApproveRequest(request);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response)
            {
                commonLoaderstop();
                if(response!=null&&response.body().getResult().equalsIgnoreCase("success"))
                {
                    commonToast("Doctor Registration Rejected.");
                }
                else
                {
                    commonToast("Try Again Later");

                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });
    }

}
