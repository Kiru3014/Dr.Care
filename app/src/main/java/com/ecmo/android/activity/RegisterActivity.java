package com.ecmo.android.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.HospitalReq;
import com.ecmo.android.model.request.RegisterRequest;
import com.ecmo.android.model.response.HospitalList;
import com.ecmo.android.model.response.Hospitalitem;
import com.ecmo.android.model.response.RegisterResponse;
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

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    EditText zFirstName, zEmail, zPassword, zMobile, ztvcivilId;
    Button zRegister;
    Spinner zHospital,dr_speciality;
    ImageView zBack;
    String sFirstName, sHospital, sEmail, sPassword, sMobile, scivilid, specilaity;
    UserPreferences userPreferences;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userPreferences = new UserPreferences(this);
        initViews();
        getHospitallistfromapi();
        getSpecialitylistfromapi();

        //initilizise Fonts
        initfonts();


    }

    private void getHospitallistfromapi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<HospitalList> call = apiService.getallhospitals(new HospitalReq("getgospital"));
        call.enqueue(new Callback<HospitalList>() {
            @Override
            public void onResponse(Call<HospitalList> call, Response<HospitalList> response)
            {
                HospitalList hosplist= response.body();
                if(hosplist!=null&&hosplist.getResult().equals("SUCCESS") && hosplist.getData()!=null)
                {
                    setHospitallist(hosplist.getData());
                    zHospital.setAdapter(getAutosuggestion(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<HospitalList> call, Throwable t) {
                zHospital.setAdapter(getAutosuggestion(getApplicationContext()));
            }
        });
    }

    private void getSpecialitylistfromapi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<HospitalList> call = apiService.getallSpeciality(new HospitalReq("getspesialist"));
        call.enqueue(new Callback<HospitalList>() {
            @Override
            public void onResponse(Call<HospitalList> call, Response<HospitalList> response)
            {
                HospitalList hosplist= response.body();
                if(hosplist!=null&&hosplist.getResult().equals("SUCCESS") && hosplist.getData()!=null)
                {
                    setSpecialitylist(hosplist.getData());
                    dr_speciality.setAdapter(getSpeciality(getApplicationContext()));
                }
            }

            @Override
            public void onFailure(Call<HospitalList> call, Throwable t) {
                dr_speciality.setAdapter(getSpeciality(getApplicationContext()));
            }
        });
    }

    private void initViews() {
        zFirstName = (EditText) findViewById(R.id.et_first_name);
        zHospital = (Spinner) findViewById(R.id.et_hospitalname);
        dr_speciality= (Spinner) findViewById(R.id.dr_speacility);
        zEmail = (EditText) findViewById(R.id.et_email);
        zPassword = (EditText) findViewById(R.id.et_password);
        zMobile = (EditText) findViewById(R.id.et_mobile);
        zRegister = (Button) findViewById(R.id.btn_register);
        zBack = (ImageView) findViewById(R.id.img_back);
        ztvcivilId = (EditText) findViewById(R.id.et_civilid);

        zRegister.setOnClickListener(this);
        zBack.setOnClickListener(this);
    }

    private void initfonts() {
        zFirstName.setTypeface(Helper.getSharedHelper().getNormalFont());
//        zHospital.setTypeface(Helper.getSharedHelper().getNormalFont());
        zEmail.setTypeface(Helper.getSharedHelper().getNormalFont());
        zPassword.setTypeface(Helper.getSharedHelper().getNormalFont());
        zMobile.setTypeface(Helper.getSharedHelper().getNormalFont());
        ztvcivilId.setTypeface(Helper.getSharedHelper().getNormalFont());
        zRegister.setTypeface(Helper.getSharedHelper().getSemiBoldFont());

    }

    @Override
    public void onClick(View view) {
        switch ((view.getId())) {
            case R.id.btn_register:
                valiData();
                break;
            case R.id.img_back:
                onBackPressed();
                finish();
                break;

        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void valiData() {
        sFirstName = zFirstName.getText().toString().trim();
        sHospital = gethospitalvalue(zHospital.getSelectedItem().toString());
        specilaity=getSpecilityvalue(dr_speciality.getSelectedItem().toString());
        sEmail = zEmail.getText().toString().trim();
        sPassword = zPassword.getText().toString().trim();
        sMobile = zMobile.getText().toString().trim();
        scivilid = ztvcivilId.getText().toString().trim();
        if (sFirstName == null || sFirstName.isEmpty()) {
            commonToast(Constants.REG_FIRST_NAME);
        } else if (sHospital == null || sHospital.isEmpty() || sHospital.equals("0")) {
            commonToast(Constants.REG_HOSPITAL_NAME);
        }else if (specilaity == null || specilaity.isEmpty() || specilaity.equals("0")) {
            commonToast(Constants.REG_SPECIALITY);
        }
        else if (!Helper.isValidEmail(sEmail) || sEmail.isEmpty()) {
            commonToast(Constants.REG_VALID_EMAIL);
        } else if (sPassword == null || sPassword.isEmpty()) {
            commonToast(Constants.REG_PASSOWRD);
        } else if (sMobile == null || sMobile.isEmpty()) {
            commonToast(Constants.REG_NUMBER);
        } else if (scivilid == null || scivilid.isEmpty()) {
            commonToast(Constants.REG_CIVILID);
        } else {
            initRegister(sFirstName, sHospital,specilaity, sEmail, sPassword, sMobile, scivilid);
        }
    }

    @SuppressLint("HardwareIds")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initRegister(String FirstName, String Hospitalname,String speciality, String Email, String Password, String Mobile, String civilid) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final RegisterRequest registerRequest = new RegisterRequest(
                civilid,
                FirstName,
                Email,
                Mobile,
                Password,Integer.parseInt(Hospitalname),Integer.parseInt(speciality), 1, userPreferences.getPushwooshToken(),
                "android", 1, "insert", Constants.SESSIONID);
        Call<RegisterResponse> call = apiService.getRegisterRequest(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                commonLoaderstop();
                RegisterResponse registerResponse = response.body();
                if (registerResponse != null) {
                    if (registerResponse.getResult().equalsIgnoreCase("Success")) {
                        zFirstName.setText("");
//                        zHospital.setText("");
                        zEmail.setText("");
                        zPassword.setText("");
                        zMobile.setText("");
                        ztvcivilId.setText("");
                        zFirstName.requestFocus();
                        commonToast(Constants.REFERAL_MSG);
                    } else {
                        commonToast("Please check email id , it might have registered already.");
                    }
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });
    }



}