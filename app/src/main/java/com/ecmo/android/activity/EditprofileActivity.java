package com.ecmo.android.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.EditProfileRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.UserPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fairoze khazi on 07/04/2018.
 */

public class EditprofileActivity extends BaseActivity {
    private EditText name, contactNO;
    Spinner hospitalname,speciality;
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


    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    /**
     *  get user details from shared preference.
     */
    private void init() {
        name = findViewById(R.id.fullname);
        contactNO = findViewById(R.id.mobile_no_value);
        emailID = findViewById(R.id.email_ID_value);
        hospitalname=findViewById(R.id.hospitalname);
        speciality=findViewById(R.id.speacility);
        id_close = findViewById(R.id.id_close);
        save_profile=findViewById(R.id.saveprofile);

        hospitalname.setAdapter(getAutosuggestion(getApplicationContext()));
        speciality.setAdapter(getSpeciality(getApplicationContext()));

        name.setText(userSharedPreferences.getFirstName());
        contactNO.setText(userSharedPreferences.getKeyUserMob());
        hospitalname.setSelection(getIndex(hospitalname, userSharedPreferences.getKeyUserHospital()));
        speciality.setSelection(getIndex(speciality, userSharedPreferences.getKeyUserSpeciality()));
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
                   save_profile(gethospitalvalue(hospitalname.getSelectedItem().toString()),getSpecilityvalue(speciality.getSelectedItem().toString()));
                    break;

            }
        }
    };

    private void save_profile(String hospid,String specilatyid) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final EditProfileRequest editProfileRequest = new EditProfileRequest(name.getText().toString(),emailID.getText().toString(),contactNO.getText().toString(),
                hospid,specilatyid,userSharedPreferences.getUserId(),"updateuser", userSharedPreferences.getSession());
        Call<CommonResponse> call = apiService.editProfile(editProfileRequest);

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
                    userSharedPreferences.setUserLoggedIn(true);
                    userSharedPreferences.setFirstName(name.getText().toString());
                    userSharedPreferences.setUserMob(contactNO.getText().toString());
                    userSharedPreferences.setUserHospital(hospitalname.getSelectedItem().toString());
                    userSharedPreferences.setUserSpeciality(speciality.getSelectedItem().toString());
                    commonToast("Profile Saved Successfully");
                    finish();
                }
                else
                {
                    commonToast("Profile Not saved, try again later.");
                }

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });


    }

}