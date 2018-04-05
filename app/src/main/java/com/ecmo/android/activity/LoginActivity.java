package com.ecmo.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecmo.android.R;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;
import com.pushwoosh.fragment.PushEventListener;
import com.pushwoosh.fragment.PushFragment;


public class LoginActivity extends FragmentActivity implements View.OnClickListener, PushEventListener {

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
        PushFragment.init(LoginActivity.this);

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

    public void commonToast(String msg) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
        TextView textView = (TextView) layout.findViewById(R.id.custom_toast_message);
        textView.setTypeface(Helper.getSharedHelper().getLightFont());
        textView.setText(msg);
        final Toast toast = new Toast(getApplicationContext());
        toast.setDuration((int) 1);
        toast.setGravity(Gravity.BOTTOM, 0, 170);
        toast.setView(layout);
        toast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 1000);
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

    @Override
    public void doOnUnregisteredError(String s) {

    }

    @Override
    public void doOnRegisteredError(String s) {

    }
    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        //Check if we've got new intent with a push notification
        PushFragment.onNewIntent(this, intent);
    }

    @Override
    public void doOnRegistered(String registrationId)
    {
        if(registrationId!=null && !registrationId.isEmpty())
            userPreferences.setPushwooshToken(registrationId);
    }


    @Override
    public void doOnMessageReceive(String s) {

    }

    @Override
    public void doOnUnregistered(String s) {

    }
}
