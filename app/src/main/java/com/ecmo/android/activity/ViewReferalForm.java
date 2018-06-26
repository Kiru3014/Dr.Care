package com.ecmo.android.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.FormActionRequest;
import com.ecmo.android.model.request.FormviewRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.model.response.Referalformdata;
import com.ecmo.android.model.response.referalformResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.UserPreferences;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewReferalForm extends BaseActivity {

    Intent i;
    referalformResponse pat_data;

    //Hospital and Patient
    TextView status, mtvHospitalName, mtvHospitalSpecialty, mtvConcultant, mtvDate, mtvDiagnosis, mtvPatientfilNumber, mtvpatientName, mtvPatientcivilid, mtvgender, mtvage, mtvunit, mtvward, mtvbed, mtvhistory;

    //Patient Paramenter
    TextView mtvfunstatus, mtvconsciousstatus, mtvgcsE, mtvgcsV, mtvgcsM, mgcstotoal;

    //Ventilation
    TextView mtvnumberdayvent, mtvspo2, mtvpo2, mtvfio2, mtvpafi, mtvpip, mtvpeep, mtvtv, mtvrr, tvlung, mtvcxr,mmurryscore;

    //Cardiovascular
    TextView mtvHr, mtvBp, mtvCVp, mtvTemp, mtvCo, mtvCardindex, mtvleftventri;

    //Inotropes
    TextView magentone, magenttwo, magentthree, msagentone, msagentwo, msagentthree, mmagent;
    TextView mdoseone, mdosetwo, mdosethree, msdoseone, msdosewo, msdosethree, mmdose;

    //Invegation
    TextView mtvurea, mtvcr, mtvlactate, mtvUo, mtvDialysis, mtvph, mtvinvpo2, mtvpco2, mtvHco3, mtvbe;
    Button approve,reject,hold,resend;
    String formid,mresend;
    RelativeLayout formaction;
    UserPreferences userPreferences;
    EditText ref_comment;
    ImageView mfileone,mfiletwo,mfilethree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.referalformview);
        userPreferences=new UserPreferences(getApplicationContext());
        formaction=findViewById(R.id.formaction);
        approve=findViewById(R.id.form_approve);
        reject=findViewById(R.id.form_reject);
        hold=findViewById(R.id.form_hold);
        ref_comment=findViewById(R.id.ref_comment);
        resend=findViewById(R.id.resend_form);
        i = getIntent();
        formid=i.getStringExtra("formid");
        mresend = i.getStringExtra("resend");
        getformdata(formid);
    }

    private void getformdata(String formid) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
        final FormviewRequest Request = new FormviewRequest("getpatientinfo",userPreferences.getSession(),formid);
        Call<Referalformdata> call = apiService.GetRefPatientDetails(Request);
        call.enqueue(new Callback<Referalformdata>() {
            @Override
            public void onResponse(Call<Referalformdata> call, Response<Referalformdata> response) {
                commonLoaderstop();
                Referalformdata Referalformdata = response.body();
                if(response!=null&&response.body().getResult().equalsIgnoreCase("FAILED") && response.message().contains(Constants.AUTH_FAIL)){
                    LogoutSession();
                }
                else if (Referalformdata != null && Referalformdata.getResult().equalsIgnoreCase("success")) {
                    pat_data=Referalformdata.getData();
                    if(pat_data!=null ) {
                        HospitalPatient();
                        PatienParameters();
                        Ventalation();
                        Cardiovascular();
                        Agents();
                        Investigation();
                        vailidateActionView();
                    }

                } else {
                    commonToast("Error");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Referalformdata> call, Throwable t) {
                commonLoaderstop();
                commonToast("Network Issue Please Try Again");

            }
        });
    }

    private void vailidateActionView() {
        if(userPreferences.getDocType().equalsIgnoreCase("3")){
            formaction.setVisibility(View.GONE);
            approve.setVisibility(View.GONE);
            reject.setVisibility(View.GONE);
            hold.setVisibility(View.GONE);
            ref_comment.setVisibility(View.VISIBLE);
            ref_comment.setEnabled(false);
            if(pat_data.getStatus().equalsIgnoreCase("NEW")){
                ref_comment.setVisibility(View.GONE);
            }
            if(pat_data.getStatus().equalsIgnoreCase("Reject")|| pat_data.getStatus().equalsIgnoreCase("4")
                    || pat_data.getStatus().equalsIgnoreCase("Hold")|| pat_data.getStatus().equalsIgnoreCase("2"))
            {
                if(mresend.equalsIgnoreCase("0"))
                {
                    resend.setVisibility(View.VISIBLE);
                }

            }
        }
        else if(userPreferences.getDocType().equalsIgnoreCase("1") &&( pat_data.getStatus().equalsIgnoreCase("New")|| pat_data.getStatus().equalsIgnoreCase("0")
                || pat_data.getStatus().equalsIgnoreCase("Hold")|| pat_data.getStatus().equalsIgnoreCase("2"))){
            formaction.setVisibility(View.VISIBLE);
            approve.setVisibility(View.VISIBLE);
            reject.setVisibility(View.VISIBLE);
            hold.setVisibility(View.VISIBLE);
            resend.setVisibility(View.GONE);
            ref_comment.setVisibility(View.VISIBLE);
            if(pat_data.getStatus().equalsIgnoreCase("Hold")|| pat_data.getStatus().equalsIgnoreCase("2"))
                hold.setVisibility(View.GONE);
        }



    }


    private void HospitalPatient() {
        status=findViewById(R.id.ref_status);
        mtvHospitalName = findViewById(R.id.tv__hospital);
        mtvHospitalSpecialty = findViewById(R.id.tv__speciality);
        mtvConcultant = findViewById(R.id.tv_consultant);
        mtvDate = findViewById(R.id.tv_date);
        mtvDiagnosis = findViewById(R.id.tv_diagnosis);
        mtvPatientfilNumber = findViewById(R.id.tv_patientfilenumber);
        mtvpatientName = findViewById(R.id.tv_patientname);
        mtvPatientcivilid = findViewById(R.id.tv_patientcivilid);
        mtvgender = findViewById(R.id.gender);
        mtvage = findViewById(R.id.tv_age);
        mtvunit = findViewById(R.id.tv_unit);
        mtvward = findViewById(R.id.tv_ward);
        mtvbed = findViewById(R.id.tv_bed);
        mtvhistory = findViewById(R.id.tv_extra_infor);

        status.setText(pat_data.getStatus());
        mtvHospitalName.setText(pat_data.getReferHospital());
        mtvHospitalSpecialty.setText(pat_data.getSpeciallist());
        mtvConcultant.setText(pat_data.getReferConsulutantName());
        mtvDate.setText(pat_data.getRefDate());
        mtvDiagnosis.setText(pat_data.getAdmissionDiagnosis());
        mtvPatientfilNumber.setText(pat_data.getFileNo());
        mtvpatientName.setText(pat_data.getPatientName());
        mtvPatientcivilid.setText(pat_data.getCivilId());
        mtvgender.setText(pat_data.getGender());
        mtvage.setText(pat_data.getAge());
        mtvunit.setText(pat_data.getUnit());
        mtvward.setText(pat_data.getWard());
        mtvbed.setText(pat_data.getBed());
        mtvhistory.setText(pat_data.getHistory());

    }

    private void PatienParameters() {
        mtvfunstatus = findViewById(R.id.tv__fun_status);
        mtvconsciousstatus = findViewById(R.id.tv__conscious_status);
        mtvgcsE = findViewById(R.id.tv__gcse);
        mtvgcsV = findViewById(R.id.tv__gcsv);
        mtvgcsM = findViewById(R.id.tv__gcsm);
        mgcstotoal = findViewById(R.id.tv_gcs_totola);

        mtvfunstatus.setText(pat_data.getPreMorbFunctionalStatus());
        mtvconsciousstatus.setText(pat_data.getPreMorbFunctionalConsciousStatus());
        mtvgcsE.setText(pat_data.getE());
        mtvgcsV.setText(pat_data.getV());
        mtvgcsM.setText(pat_data.getM());
        mgcstotoal.setText(pat_data.getTotalscore());


    }

    private void Ventalation() {
        mtvnumberdayvent = findViewById(R.id.tv_number_ofdays);
        mtvspo2 = findViewById(R.id.tv_spo2);
        mtvpo2 = findViewById(R.id.tv_po2);
        mtvfio2 = findViewById(R.id.tv_fio2);
        mtvpafi = findViewById(R.id.tv_fio2_paO2);
        mtvpip = findViewById(R.id.tv_pip);
        mtvpeep = findViewById(R.id.tv_peep);
        mtvtv = findViewById(R.id.tv_tv);
        mtvrr = findViewById(R.id.tv_rr);
        tvlung = findViewById(R.id.tv_lungcomplice);
        mtvcxr = findViewById(R.id.tv_cxr);
        mmurryscore=findViewById(R.id.et_murryscore);

        mtvnumberdayvent.setText(pat_data.getDurOfConventianalMechanicalVentination());
        mtvspo2.setText(pat_data.getSpO2());
        mtvpo2.setText(pat_data.getPO2());
        mtvfio2.setText(pat_data.getFiO2());
        mtvpafi.setText(pat_data.getPao2fio2ratio());
        mtvpip.setText(pat_data.getPip());
        mtvpeep.setText(pat_data.getPeep());
        mtvtv.setText(pat_data.getTv());
        mtvrr.setText(pat_data.getRr());
        tvlung.setText(pat_data.getLungCompliance());
        mtvcxr.setText(pat_data.getCxrquadrants());
        mmurryscore.setText(pat_data.getMurrayscore());

    }

    private void Cardiovascular() {

        mtvHr = findViewById(R.id.tv_hr);
        mtvBp = findViewById(R.id.tv_Bp);
        mtvCVp = findViewById(R.id.tv_cvp);
        mtvTemp = findViewById(R.id.tv_temp);
        mtvCo = findViewById(R.id.tv_co);
        mtvCardindex = findViewById(R.id.tv_cardiacindex);
        mtvleftventri = findViewById(R.id.tv_lvef);

        mtvHr.setText(pat_data.getHr());
        mtvBp.setText(pat_data.getBp());
        mtvCVp.setText(pat_data.getCvp());
        mtvTemp.setText(pat_data.getTemp());
        mtvCo.setText(pat_data.getCo());
        mtvCardindex.setText(pat_data.getCardiacindex());
        mtvleftventri.setText(pat_data.getLeftventricularejectionfraction());
    }


    private void Agents() {
        magentone = findViewById(R.id.agentone);
        magenttwo = findViewById(R.id.agenttwo);
        magentthree = findViewById(R.id.agentthree);
        msagentone = findViewById(R.id.sedationagentone);
        msagentwo = findViewById(R.id.sedationagenttwo);
        msagentthree = findViewById(R.id.view_sedationagentthree);
        mmagent = findViewById(R.id.relaxantsagentone);

        mdoseone = findViewById(R.id.view_doseone);
        mdosetwo = findViewById(R.id.view_dostvwo);
        mdosethree = findViewById(R.id.view_dostvhree);
        msdoseone = findViewById(R.id.view_sedationdoseone);
        msdosewo = findViewById(R.id.view_sedationdostvwo);
        msdosethree = findViewById(R.id.view_sedationdostvhree);
        mmdose = findViewById(R.id.relaxantsdoseone);


        magentone.setText(pat_data.getInotropesagent1());
        magenttwo.setText(pat_data.getInotropesagent2());
        magentthree.setText(pat_data.getInotropesagent3());
        msagentone.setText(pat_data.getSedationagent1());
        msagentwo.setText(pat_data.getSedationagent2());
        msagentthree.setText(pat_data.getSedationagent3());
        mmagent.setText(pat_data.getMusclerelaxantsagent1());

        mdoseone.setText(pat_data.getInotropesdose1());
        mdosetwo.setText(pat_data.getInotropesdose2());
        mdosethree.setText(pat_data.getInotropesdose3());
        msdoseone.setText(pat_data.getSedationdose1());
        msdosewo.setText(pat_data.getSedationdose2());
        msdosethree.setText(pat_data.getSedationdose3());
        mmdose.setText(pat_data.getMusclerelaxantsdose1());

    }


    private void Investigation()
    {
        mtvurea = findViewById(R.id.tv_urea);
        mtvcr = findViewById(R.id.tv_cr);
        mtvlactate = findViewById(R.id.tv_lactate);
        mtvUo = findViewById(R.id.tv_uo);
        mtvDialysis = findViewById(R.id.tv_dialysis);
        mtvph = findViewById(R.id.tv_ph);
        mtvinvpo2 = findViewById(R.id.tv_ingpo2);
        mtvpco2 = findViewById(R.id.tv_pco2);
        mtvHco3 = findViewById(R.id.tv_hco3);
        mtvbe = findViewById(R.id.tv_bf);

        mfileone= findViewById(R.id.image_one);
        mfiletwo= findViewById(R.id.image_two);
        mfilethree= findViewById(R.id.image_three);

        if(pat_data.getFile_one()!=null&!pat_data.getFile_one().isEmpty())
        Glide.with(getApplicationContext())
                .load(pat_data.getFile_one())
                .into(mfileone);

        if(pat_data.getFile_two()!=null&!pat_data.getFile_two().isEmpty())
        Glide.with(getApplicationContext())
                .load(pat_data.getFile_two())
                .into(mfiletwo);

        if(pat_data.getFile_three()!=null&!pat_data.getFile_three().isEmpty())
        Glide.with(getApplicationContext())

                .load(pat_data.getFile_three())
                .into(mfilethree);

        mtvurea.setText(pat_data.getUrea());
        mtvcr.setText(pat_data.getCr());
        mtvlactate.setText(pat_data.getLactate());
        mtvUo.setText(pat_data.getUo());
        mtvDialysis.setText(pat_data.getDialysis());
        mtvph.setText(pat_data.getBloodgasPH());
        mtvinvpo2.setText(pat_data.getBloodgasPO2());
        mtvpco2.setText(pat_data.getBloodgasPCO2());
        mtvHco3.setText(pat_data.getBloodgasHCO3());
        mtvbe.setText(pat_data.getBloodgasBE());
        if(!pat_data.getComment().isEmpty())
            ref_comment.setText(pat_data.getComment());

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showconfirmation("REJECTED",ViewReferalForm.this);
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                showconfirmation("HOLD",ViewReferalForm.this);
            }
        });

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               showconfirmation("APPROVE",ViewReferalForm.this);
            }
        });



        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     pat_data  send to patient form.

                Intent intent = new Intent(getApplicationContext(), PatientForm.class);
                intent.putExtra("resendform", (Serializable) pat_data);
                startActivity(intent);
            }
        });

    }


        public void showconfirmation(final String action, Context c) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

            alertDialogBuilder.setTitle(action+" REFERRAL FORM ?");

            alertDialogBuilder.setPositiveButton("NO",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.dismiss();
                        }
                    });

            alertDialogBuilder.setNegativeButton("YES",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SubmitApplication(action);
                        }
                    });

            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }

    private void SubmitApplication(final String action) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
        String act="0";
        if(action.equalsIgnoreCase("APPROVE"))
            act="3";
        else  if(action.equalsIgnoreCase("HOLD"))
            act="2";
        else if(action.equalsIgnoreCase("REJECTED"))
            act="4";
        FormActionRequest fm= new FormActionRequest(act,formid,ref_comment.getText().toString(),"insertaction",userPreferences.getSession());
        Call<CommonResponse> call = apiService.AddUpdatePatientStatus(fm);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response)
            {
                commonLoaderstop();
                CommonResponse Referalformdata = response.body();
                if (Referalformdata != null)
                {
                    if (Referalformdata.getResult().equalsIgnoreCase("Success"))
                    {
                        commonToast("Referral Form "+ action);
                        finish();
                    } else {
                        commonToast("Fail");

                    }

                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonLoaderstop();

            }
        });

    }



}
