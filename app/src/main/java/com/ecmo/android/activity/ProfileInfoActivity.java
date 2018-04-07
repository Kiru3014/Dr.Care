package com.ecmo.android.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.utils.UserPreferences;

public class ProfileInfoActivity extends BaseActivity {
    private TextView name, contactNO, emailID;
    private Button edit_profile,chnagepwd;
    private TextView id_close, hospitalname;
    private UserPreferences userSharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_info);
        userSharedPreferences = new UserPreferences(this);
        init();
    }


    @Override
    protected void onResume() {
        super.onResume();


        name.setText(userSharedPreferences.getFirstName());
        contactNO.setText(userSharedPreferences.getKeyUserMob());
        hospitalname.setText(userSharedPreferences.getKeyUserHospital());
        emailID.setText(userSharedPreferences.getEmailId());
    }

    /**
     *  get user details from shared preference.
     */
    private void init() {
        name = findViewById(R.id.fullname);
        contactNO = findViewById(R.id.mobile_no_value);
        emailID = findViewById(R.id.email_ID_value);
        hospitalname=findViewById(R.id.hospitalname);
        id_close = findViewById(R.id.id_close);
        edit_profile=findViewById(R.id.btn_edit_profile);
        chnagepwd=findViewById(R.id.btn_change_pwd);


        id_close.setOnClickListener(mClicked);
        edit_profile.setOnClickListener(mClicked);
        chnagepwd.setOnClickListener(mClicked);
    }

    /**
     *  view click listner
     */
    private final View.OnClickListener mClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.id_close:
                    onBackPressed();
                    break;

                case R.id.btn_edit_profile:
                    startActivity(new Intent(getApplicationContext(), EditprofileActivity.class));
                    break;

                case R.id.btn_change_pwd:
                    startActivity(new Intent(getApplicationContext(), Changepassword.class));
                    break;

            }
        }
    };

   /**
     *  on back pressed.
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}