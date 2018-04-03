package com.drcare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.drcare.BaseActivity;
import com.drcare.utils.Constants;
import com.drcare.utils.Helper;
import com.drcare.utils.UserPreferences;
import com.scanning.drcare.R;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    EditText zEmail, zPassword;
    Button zLogin, zRegister;
    TextView zForgotPassword;
    String bEmail, bPassword, EncPassword;
    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userPreferences = new UserPreferences(this);
        //initilizise views
        initViews();
        //initilizise Fonts
        initfonts();
    }

    private void initfonts() {
        zEmail.setTypeface(Helper.getSharedHelper().getNormalFont());
        zPassword.setTypeface(Helper.getSharedHelper().getNormalFont());
        zLogin.setTypeface(Helper.getSharedHelper().getBoldFont());
        zRegister.setTypeface(Helper.getSharedHelper().getBoldFont());
        zForgotPassword.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
    }

    private void initViews() {

        zEmail = (EditText) findViewById(R.id.et_email);
        zPassword = (EditText) findViewById(R.id.et_password);
        zLogin = (Button) findViewById(R.id.btn_login);
        zRegister = (Button) findViewById(R.id.btn_register);
        zForgotPassword = (TextView) findViewById(R.id.et_forgotpassword);

        zLogin.setOnClickListener(this);
        zRegister.setOnClickListener(this);
        zForgotPassword.setOnClickListener(this);

    }

    //View Listners
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userValidate();
                break;
            case R.id.btn_register:
                zEmail.setText("");
                zPassword.setText("");
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.et_forgotpassword:
                zEmail.setText("");
                zPassword.setText("");
                Intent forogotpassword = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(forogotpassword);
                break;
        }
    }

    private void userValidate() {

        bEmail = zEmail.getText().toString().trim();
        bPassword = zPassword.getText().toString().trim();

        if (bEmail == null || bEmail.isEmpty()) {
            commonToast(Constants.REG_EMAIL);
        } else if (!Helper.isValidEmail(bEmail)) {
            commonToast(Constants.REG_VALID_EMAIL);
        } else if (bPassword == null || bPassword.isEmpty()) {
            commonToast(Constants.REG_PASSOWRD);
        } else {
            initLogin(bEmail, bPassword);
        }
    }

    private void initLogin(final String bEmail, String bPassword)
    {
       /* userPreferences.setUserLoggedIn(true);
        userPreferences.setUserId("1");
        userPreferences.setEmailId(bEmail);
        userPreferences.setPassword(bPassword);*/
        zEmail.setText("");
        zPassword.setText("");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
