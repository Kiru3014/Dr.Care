package com.ecmo.android.rest;

import com.ecmo.android.model.request.ChangepasswordRequest;
import com.ecmo.android.model.request.EditProfileRequest;
import com.ecmo.android.model.request.ForgotPasswordRequest;
import com.ecmo.android.model.request.HospitalReq;
import com.ecmo.android.model.request.LoginRequest;
import com.ecmo.android.model.request.RegisterRequest;
import com.ecmo.android.model.response.HospitalList;
import com.ecmo.android.model.response.LoginResponse;
import com.ecmo.android.model.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface
{

    @POST("GetHospitalLIst")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<HospitalList> getallhospitals(@Body HospitalReq hospreq);



    @POST("GetSpecialistLIst")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<HospitalList> getallSpeciality(@Body HospitalReq hospreq);


    @POST("AddUpdateDoctor")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<RegisterResponse> getRegisterRequest(@Body RegisterRequest registerRequest);

    @POST("DocLogin")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<LoginResponse> getLoginRequest(@Body LoginRequest loginRequest);


    @POST("AddUpdateDoctor")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<EditProfileRequest> editProfile(@Body EditProfileRequest editrequest);

    @POST("AddUpdateDoctor")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<ChangepasswordRequest> chnagepassword(@Body ChangepasswordRequest ChangepasswordReq);


    @POST("ForgotPassword")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<RegisterResponse> getForgotpasswordRequest(@Body ForgotPasswordRequest forgotPasswordRequest);
}


