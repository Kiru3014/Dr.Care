package com.ecmo.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.ChangepasswordRequest;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.UserPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fairoze khazi on 07/04/2018.
 */

public class Changepassword extends BaseActivity{
    private EditText curPwd, newPwd, confPwd;
    private Button btnSave;
    private TextView id_close;
    UserPreferences userSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);
        userSharedPreferences=new UserPreferences(this);
        init();
    }

    private void init() {

        curPwd = findViewById(R.id.et_curPwd);
        newPwd = findViewById(R.id.et_newPwd);
        confPwd = findViewById(R.id.et_confPwd);

        btnSave = findViewById(R.id.btn_savePwd);
        btnSave.setOnClickListener(mClicked);

        id_close = findViewById(R.id.id_close);
        id_close.setOnClickListener(mClicked);
    }

    private View.OnClickListener mClicked = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            switch(v.getId()) {

                case R.id.btn_savePwd:

                    if (curPwd.getText().toString().length() == 0  ||newPwd.getText().toString().length() == 0 || confPwd.getText().toString().length() == 0 )
                    {
                        commonToast( "please enter current/new password");
                    }

                    else if (curPwd.getText().toString().length() < 5  ||newPwd.getText().toString().length() < 5 || confPwd.getText().toString().length() < 5 ) {

                        commonToast( "Minimun password length is 5");
                    }
                    else if(!newPwd.getText().toString().equals(confPwd.getText().toString()))
                    {
                        commonToast(" New Password Mismatch");
                    }
                    else if(newPwd.getText().toString().equals(curPwd.getText().toString()))
                    {
                        commonToast( "Current and new password entered is same");
                    }
                    else
                    {
                        changePassword();
                    }

                    break;


                case R.id.id_close:
                    onBackPressed();
                    break;
            }
        }
    };



    public void changePassword() {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final ChangepasswordRequest chngpwdrequest = new ChangepasswordRequest(curPwd.getText().toString(),newPwd.getText().toString(),userSharedPreferences.getUserId(),"updateuser", userSharedPreferences.getSession());
        Call<ChangepasswordRequest> call = apiService.chnagepassword(chngpwdrequest);

        call.enqueue(new Callback<ChangepasswordRequest>() {
            @Override
            public void onResponse(Call<ChangepasswordRequest> call, Response<ChangepasswordRequest> response)
            {
                commonLoaderstop();
//                ChangepasswordRequest loginResponse= response.body();
//                if(loginResponse!=null&&loginResponse.getResult().equals("SUCCESS"))
//                {
//                    userPreferences.setUserLoggedIn(true);
//                    userPreferences.setUserId(loginResponse.getData().getDocid().toString());
//                    userPreferences.setEmailId(loginResponse.getData().getEmail());
//                    userPreferences.setSession(loginResponse.getData().getSessid());
//                    userPreferences.setFirstName(loginResponse.getData().getName());
//                    userPreferences.setUserMob(loginResponse.getData().getPhone());
//                    userPreferences.setUserHospital(loginResponse.getData().getHospitalname());
//                    zEmail.setText("");
//                    zPassword.setText("");
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else
//                {
//                    commonToast("Doctor Not Yet Approved");
//
//                }

            }

            @Override
            public void onFailure(Call<ChangepasswordRequest> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });

    }

    private void clearText() {
        curPwd.setText("");
        newPwd.setText("");
        confPwd.setText("");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
