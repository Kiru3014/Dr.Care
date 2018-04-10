package com.ecmo.android.rest;

import com.ecmo.android.model.request.ChangepasswordRequest;
import com.ecmo.android.model.request.CommonRequest;
import com.ecmo.android.model.request.DrApproveRejectRequest;
import com.ecmo.android.model.request.EditProfileRequest;
import com.ecmo.android.model.request.ForgotPasswordRequest;
import com.ecmo.android.model.request.FormActionRequest;
import com.ecmo.android.model.request.FormviewRequest;
import com.ecmo.android.model.request.HospitalReq;
import com.ecmo.android.model.request.LoginRequest;
import com.ecmo.android.model.request.PatientFormRequest;
import com.ecmo.android.model.request.RegisterRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.model.response.DoctorList;
import com.ecmo.android.model.response.HospitalList;
import com.ecmo.android.model.response.LoginResponse;
import com.ecmo.android.model.response.PatReferalList;
import com.ecmo.android.model.response.Referalformdata;

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
    Call<CommonResponse> getRegisterRequest(@Body RegisterRequest registerRequest);

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
    Call<CommonResponse> editProfile(@Body EditProfileRequest editrequest);

    @POST("AddUpdateDoctor")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<CommonResponse> chnagepassword(@Body ChangepasswordRequest ChangepasswordReq);


    @POST("ForgotPassword")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<CommonResponse> getForgotpasswordRequest(@Body ForgotPasswordRequest forgotPasswordRequest);


    @POST("GetAllDoctorDetails")
     @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<DoctorList> GetAllDoctorDetails(@Body CommonRequest CommonReq);

    @POST("AddUpdateRefPatient")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<CommonResponse> getFormRequest(@Body PatientFormRequest patientFormRequest);


    @POST("GetAllRefPatientList")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<PatReferalList> GetAllRefPatientList(@Body CommonRequest CommonReq);


    @POST("AddUpdateDoctor")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<CommonResponse> docApproveRequest(@Body DrApproveRejectRequest Request);


    @POST("GetRefPatientDetails")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<Referalformdata> GetRefPatientDetails(@Body FormviewRequest Request);


    @POST("AddUpdatePatientStatus")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<CommonResponse> AddUpdatePatientStatus(@Body FormActionRequest Request);

}


