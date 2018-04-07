package com.ecmo.android.rest;

import com.ecmo.android.model.request.LoginRequest;
import com.ecmo.android.model.request.RegisterRequest;
import com.ecmo.android.model.response.LoginResponse;
import com.ecmo.android.model.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface
{
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
}


