package com.ecmo.android.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.EditProfileRequest;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.UserPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fairoze khazi on 07/04/2018.
 */

public class EditprofileActivity extends BaseActivity {
    private EditText name, contactNO,hospitalname;
    private Button save_profile;
    private TextView id_close, emailID;
    private UserPreferences userSharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        userSharedPreferences = new UserPreferences(this);
        init();
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
        save_profile=findViewById(R.id.saveprofile);


        name.setText(userSharedPreferences.getFirstName());
        contactNO.setText(userSharedPreferences.getKeyUserMob());
        hospitalname.setText(userSharedPreferences.getKeyUserHospital());
        emailID.setText(userSharedPreferences.getEmailId());

        id_close.setOnClickListener(mClicked);
        save_profile.setOnClickListener(mClicked);
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

                case R.id.saveprofile:
                   save_profile();
                    break;

            }
        }
    };

    private void save_profile() {


        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final EditProfileRequest editProfileRequest = new EditProfileRequest(name.getText().toString(),emailID.getText().toString(),contactNO.getText().toString(),
                "1","2",userSharedPreferences.getUserId(),"updateuser", userSharedPreferences.getSession());
        Call<EditProfileRequest> call = apiService.editProfile(editProfileRequest);

        call.enqueue(new Callback<EditProfileRequest>() {
            @Override
            public void onResponse(Call<EditProfileRequest> call, Response<EditProfileRequest> response)
            {
                commonLoaderstop();
                if(response!=null&&response.message().equalsIgnoreCase("ok"))
                {
                    userSharedPreferences.setUserLoggedIn(true);
                    userSharedPreferences.setFirstName(name.getText().toString());
                    userSharedPreferences.setUserMob(contactNO.getText().toString());
                    userSharedPreferences.setUserHospital(hospitalname.getText().toString());
                    finish();
                }
                else
                {
                    commonToast("Profile Not saved, try again later.");

                }

            }

            @Override
            public void onFailure(Call<EditProfileRequest> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });


    }

}