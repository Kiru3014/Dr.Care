package com.drcare.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.drcare.BaseActivity;
import com.drcare.utils.Constants;
import com.drcare.utils.Helper;
import com.drcare.utils.UserPreferences;
import com.scanning.drcare.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    EditText zFirstName, zEmail, zPassword, zMobile, ztvcivilId;
    Button zRegister;
    AutoCompleteTextView zHospital;
    ImageView zBack;
    String sFirstName, sHospital, sEmail, sPassword, sMobile, scivilid;
    UserPreferences userPreferences;


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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initRegister(String FirstName, String LastName, String Email, String Password, String Mobile, String Nationality) {
        commonToast(Constants.REFERAL_MSG);
        addNotification(Constants.REGISTER_MSG);
        finish();
    }


    private ArrayAdapter<String> getAutosuggestion(Context context) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i<7;i++)
        {
            list.add("Hospital "+ i);
        }


        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, list);
    }
}