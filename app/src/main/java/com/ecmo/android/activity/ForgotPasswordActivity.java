package com.ecmo.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.ForgotPasswordRequest;
import com.ecmo.android.model.request.LoginRequest;
import com.ecmo.android.model.response.RegisterResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    EditText zEmail;
    Button zEnter;
    TextView zForgotPassword;
    String bEmail;
    ImageView zBack;
    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        userPreferences = new UserPreferences(getApplicationContext());
        //initilizise views
        initViews();
        //initilizise Fonts
        initfonts();
    }

    private void initfonts() {
        zEmail.setTypeface(Helper.getSharedHelper().getNormalFont());
        zEnter.setTypeface(Helper.getSharedHelper().getBoldFont());
        zForgotPassword.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
    }

    private void initViews() {

        zEmail = (EditText) findViewById(R.id.et_email);
        zEnter = (Button) findViewById(R.id.btn_enter);
        zForgotPassword = (TextView) findViewById(R.id.img_login_logo);
        zBack = (ImageView) findViewById(R.id.img_back);
        zBack.setOnClickListener(this);
        zEnter.setOnClickListener(this);


    }

    //View Listners
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_enter:
                userValidate();
                break;
            case R.id.img_back:
                onBackPressed();
                finish();
                break;
        }
    }

    private void userValidate() {

        bEmail = zEmail.getText().toString().trim();


        if (bEmail.isEmpty()) {
            commonToast(Constants.REG_EMAIL);
        } else if (!Helper.isValidEmail(bEmail)) {
            commonToast(Constants.REG_VALID_EMAIL);
        } else {
            initForgotPassword(bEmail);
        }
    }

    private void initForgotPassword(final String bEmail) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest(zEmail.getText().toString(), "forgotpassword", Constants.SESSIONID);
        Call<RegisterResponse> call = apiService.getForgotpasswordRequest(forgotPasswordRequest);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                commonLoaderstop();
                RegisterResponse registerResponse = response.body();
                if (registerResponse != null && registerResponse.getResult().equalsIgnoreCase("success")) {
                    commonToast("Please Check Email we have sent a Rest Link!");
                    zEmail.setText("");
                } else {
                    commonToast("Email not Exist");
                }


            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });


        commonLoaderstop();
    }
}
