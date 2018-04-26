package com.ecmo.android.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.HospitalReq;
import com.ecmo.android.model.response.HospitalList;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends BaseActivity implements View.OnClickListener {
    TextView mtxtgallery, mtxthelp, mtxtreferpatient, mtxtreferstatus;
    LinearLayout mllgallery, mllhelp, mllreferpatient, mllreferstatus;
    ImageView img_help;
    Button mcall, logout_btn;
    UserPreferences usp;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        usp = new UserPreferences(getApplicationContext());

        initviews();
        initfonts();
        getHospitallistfromapi();
        getSpecialitylistfromapi();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initviews() {
        mtxtgallery = (TextView) findViewById(R.id.tv_gallery);
        mtxthelp = (TextView) findViewById(R.id.tv_help);
        mtxtreferpatient = (TextView) findViewById(R.id.tv_referpatient);
        mtxtreferstatus = (TextView) findViewById(R.id.tv_patientstatus);
        mcall = (Button) findViewById(R.id.btn_phonecall);
        logout_btn = findViewById(R.id.logout_btn);
        mllgallery = (LinearLayout) findViewById(R.id.rv_gallery);
        mllhelp = (LinearLayout) findViewById(R.id.rv_help);
        mllreferpatient = (LinearLayout) findViewById(R.id.rv_referpatient);
        mllreferstatus = (LinearLayout) findViewById(R.id.rv_patientstatus);
        img_help = (ImageView) findViewById(R.id.img_help);

        if (usp.getDocType().equalsIgnoreCase("1")) {
            img_help.setImageDrawable(getApplicationContext().getDrawable(R.drawable.correct));
            mtxthelp.setText("DOCTOR APPROVAL");
        }

        mllgallery.setOnClickListener(this);
        mllhelp.setOnClickListener(this);
        mllreferpatient.setOnClickListener(this);
        mllreferstatus.setOnClickListener(this);
        mcall.setOnClickListener(this);
        logout_btn.setOnClickListener(this);

    }

    private void getHospitallistfromapi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<HospitalList> call = apiService.getallhospitals(new HospitalReq("getgospital"));
        call.enqueue(new Callback<HospitalList>() {
            @Override
            public void onResponse(Call<HospitalList> call, Response<HospitalList> response) {
                HospitalList hosplist = response.body();
                if (hosplist != null && hosplist.getResult().equals("SUCCESS") && hosplist.getData() != null) {
                    setHospitallist(hosplist.getData());
                }
            }

            @Override
            public void onFailure(Call<HospitalList> call, Throwable t) {

            }
        });
    }

    private void getSpecialitylistfromapi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<HospitalList> call = apiService.getallSpeciality(new HospitalReq("getspesialist"));
        call.enqueue(new Callback<HospitalList>() {
            @Override
            public void onResponse(Call<HospitalList> call, Response<HospitalList> response) {
                HospitalList hosplist = response.body();
                if (hosplist != null && hosplist.getResult().equals("SUCCESS") && hosplist.getData() != null) {
                    setSpecialitylist(hosplist.getData());
                }
            }

            @Override
            public void onFailure(Call<HospitalList> call, Throwable t) {

            }
        });
    }

    private void initfonts() {
        mtxtgallery.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtxthelp.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtxtreferpatient.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtxtreferstatus.setTypeface(Helper.getSharedHelper().getSemiBoldFont());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_gallery:
//                startActivity(new Intent(getApplicationContext(), GalleryActivity.class));
                startActivity(new Intent(getApplicationContext(), ProfileInfoActivity.class));
                break;
            case R.id.rv_help:
                if (usp.getDocType().equalsIgnoreCase("1"))
                    startActivity(new Intent(getApplicationContext(), DocListActivity.class));
                else
                    startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                   // webview();
                break;
            case R.id.rv_referpatient:
                startActivity(new Intent(getApplicationContext(), PatientForm.class));
                break;
            case R.id.rv_patientstatus:
                startActivity(new Intent(getApplicationContext(), RefPatientlistActivity.class));
                break;
            case R.id.btn_phonecall:
                Calling();
                break;

            case R.id.logout_btn:
                showConfirmDialog(HomeActivity.this);
                break;
        }

    }

    private void Calling()
    {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(Constants.TEL + Constants.PHONE_NUMBER));
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                callPermission();
            }else
                startActivity(intent);
        }
    }

    private void webview() {
        // open rest of URLS in default browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/resources/guidelines.aspx"));
        startActivity(intent);

    }


    private void callPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},3);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 3) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                PackageManager packageManager = getApplicationContext().getPackageManager();
                String phoneNumber = Constants.PHONE_NUMBER;
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intent);
                }

                //Displaying a toast
                //Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                //Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }


    }

}
