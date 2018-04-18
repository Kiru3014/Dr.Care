package com.ecmo.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.ChangepasswordRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
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

        curPwd.setTypeface(Helper.getSharedHelper().getNormalFont());
        newPwd.setTypeface(Helper.getSharedHelper().getNormalFont());
        confPwd.setTypeface(Helper.getSharedHelper().getNormalFont());


        btnSave = findViewById(R.id.btn_savePwd);
        btnSave.setTypeface(Helper.getSharedHelper().getBoldFont());
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
        Call<CommonResponse> call = apiService.chnagepassword(chngpwdrequest);

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response)
            {
                commonLoaderstop();
                if(response!=null&&response.body().getResult().equalsIgnoreCase("FAILED") && response.message().contains(Constants.AUTH_FAIL)){
                    LogoutSession();
                }
                else if(response!=null&&response.body().getResult().equalsIgnoreCase("success"))
                {
                    commonToast("Password changed successfully.");
                }
                else if(response!=null&&response.body().getResult().contains("incorrect"))
                {
                    commonToast("Wrong Password entered.");
                }
                else
                {
                    commonToast("Try Again Later");

                }
                finish();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
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
