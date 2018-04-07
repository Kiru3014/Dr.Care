package com.ecmo.android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.model.request.RegisterRequest;
import com.ecmo.android.model.response.RegisterResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;
import com.ecmo.android.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    EditText zFirstName, zEmail, zPassword, zMobile, ztvcivilId;
    Button zRegister;
    AutoCompleteTextView zHospital;
    ImageView zBack;
    String sFirstName, sHospital, sEmail, sPassword, sMobile, scivilid, deviceid;
    UserPreferences userPreferences;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userPreferences = new UserPreferences(this);

        //initilizise views
        initViews();
        //initilizise Fonts
        initfonts();


    }

    private void initViews() {
        zFirstName = (EditText) findViewById(R.id.et_first_name);
        zHospital = (AutoCompleteTextView) findViewById(R.id.et_hospitalname);
        zEmail = (EditText) findViewById(R.id.et_email);
        zPassword = (EditText) findViewById(R.id.et_password);
        zMobile = (EditText) findViewById(R.id.et_mobile);
        zRegister = (Button) findViewById(R.id.btn_register);
        zBack = (ImageView) findViewById(R.id.img_back);
        ztvcivilId = (EditText) findViewById(R.id.et_civilid);
        zRegister.setOnClickListener(this);
        zBack.setOnClickListener(this);
        zHospital.setThreshold(1);
        zHospital.setAdapter(getAutosuggestion(this));


    }

    private void initfonts() {
        zFirstName.setTypeface(Helper.getSharedHelper().getNormalFont());
        zHospital.setTypeface(Helper.getSharedHelper().getNormalFont());
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
        sHospital = zHospital.getText().toString().trim();
        sEmail = zEmail.getText().toString().trim();
        sPassword = zPassword.getText().toString().trim();
        sMobile = zMobile.getText().toString().trim();
        scivilid = ztvcivilId.getText().toString().trim();
        if (sFirstName == null || sFirstName.isEmpty()) {
            commonToast(Constants.REG_FIRST_NAME);
        } else if (sHospital == null || sHospital.isEmpty()) {
            commonToast(Constants.REG_HOSPITAL_NAME);
        } else if (!Helper.isValidEmail(sEmail) || sEmail.isEmpty()) {
            commonToast(Constants.REG_VALID_EMAIL);
        } else if (sPassword == null || sPassword.isEmpty()) {
            commonToast(Constants.REG_PASSOWRD);
        } else if (sMobile == null || sMobile.isEmpty()) {
            commonToast(Constants.REG_NUMBER);
        } else if (scivilid == null || scivilid.isEmpty()) {
            commonToast(Constants.REG_CIVILID);
        } else {
            initRegister(sFirstName, sHospital, sEmail, sPassword, sMobile, scivilid);
        }
    }

    @SuppressLint("HardwareIds")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initRegister(String FirstName, String Hospitalname, String Email, String Password, String Mobile, String civilid) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final RegisterRequest registerRequest = new RegisterRequest(
                civilid,
                FirstName,
                Email,
                Mobile,
                Password, 6, 7, 1, userPreferences.getPushwooshToken(),
                "android", 1, "insert", Constants.SESSIONID);
        Call<RegisterResponse> call = apiService.getRegisterRequest(registerRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                commonLoaderstop();
                RegisterResponse registerResponse = response.body();
                if (registerResponse != null) {
                    if (registerResponse.getResult().equals("Success")) {
                        zFirstName.setText("");
                        zHospital.setText("");
                        zEmail.setText("");
                        zPassword.setText("");
                        zMobile.setText("");
                        ztvcivilId.setText("");
                        zFirstName.requestFocus();
                        commonToast(Constants.REFERAL_MSG);
                    } else {
                        commonToast("Please Enter All the Details Correctly");
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


    private ArrayAdapter<String> getAutosuggestion(Context context) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            list.add("Hospital " + i);
        }


        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list);
    }
}