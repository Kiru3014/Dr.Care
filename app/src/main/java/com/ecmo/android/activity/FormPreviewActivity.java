package com.ecmo.android.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.PatientFormRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormPreviewActivity extends BaseActivity {

    Intent i;
    PatientFormRequest dene;
    String formattedDate;

    //Hospital and Patient
    TextView mtvHospitalName, mtvHospitalSpecialty, mtvConcultant, mtvDate, mtvDiagnosis, mtvPatientfilNumber, mtvpatientName, mtvPatientcivilid, mtvgender, mtvage, mtvunit, mtvward, mtvbed, mtvhistory;

    //Patient Paramenter
    TextView mtvfunstatus, mtvconsciousstatus, mtvgcsE, mtvgcsV, mtvgcsM, mgcstotoal;

    //Ventilation
    TextView mtvnumberdayvent, mtvspo2, mtvpo2, mtvfio2, mtvpafi, mtvpip, mtvpeep, mtvtv, mtvrr, tvlung, mtvcxr;

    //Cardiovascular
    TextView mtvHr, mtvBp, mtvCVp, mtvTemp, mtvCo, mtvCardindex, mtvleftventri;

    //Inotropes
    TextView magentone, magenttwo, magentthree, msagentone, msagentwo, msagentthree, mmagent;
    TextView mdoseone, mdosetwo, mdosethree, msdoseone, msdosewo, msdosethree, mmdose;

    //Invegation
    TextView mtvurea, mtvcr, mtvlactate, mtvUo, mtvDialysis, mtvph, mtvinvpo2, mtvpco2, mtvHco3, mtvbe;
    Button button;

    Bitmap bitmapone, bitmaptwo, bitmapthree;
    ImageView imageview_one, imageview_two, imageview_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewform);
        i = getIntent();
        dene = (PatientFormRequest) i.getSerializableExtra("MyClass");
        getTodayDate();
        HospitalPatient();
        PatienParameters();
        Ventalation();
        Cardiovascular();
        Agents();
        Investigation();
        Camera();
    }

    private void Camera()
    {
        imageview_one = (ImageView) findViewById(R.id.img_one);
        imageview_two = (ImageView) findViewById(R.id.img_two);
        imageview_three = (ImageView) findViewById(R.id.img_three);

        byte[]  bytesone = this.getIntent().getByteArrayExtra("BitmapImageOne");
        if (bytesone != null)
            bitmapone = BitmapFactory.decodeByteArray(bytesone, 0, bytesone.length);
            imageview_one.setImageBitmap(bitmapone);
        byte[] bytestwo = this.getIntent().getByteArrayExtra("BitmapImageTwo");
        if (bytestwo != null)
            bitmaptwo = BitmapFactory.decodeByteArray(bytestwo, 0, bytestwo.length);
            imageview_two.setImageBitmap(bitmaptwo);
        byte[] bytesthree = this.getIntent().getByteArrayExtra("BitmapImageThree");
        if (bytesthree != null)
            bitmapthree = BitmapFactory.decodeByteArray(bytesthree, 0, bytesthree.length);
            imageview_three.setImageBitmap(bitmapthree);
    }


    private void getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        formattedDate = df.format(c);
    }

    private void HospitalPatient() {
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

        mtvHospitalName.setText(gethospitalname(dene.getReferHospitalId()));
        mtvHospitalSpecialty.setText(getSpecility(dene.getSpeciallistId()));
        mtvConcultant.setText(dene.getReferConsulutantName());
        mtvDate.setText(formattedDate);
        mtvDiagnosis.setText(dene.getDialysis());
        mtvPatientfilNumber.setText(dene.getFileNo());
        mtvpatientName.setText(dene.getPatientName());
        mtvPatientcivilid.setText(dene.getCivilId());
        mtvgender.setText(dene.getGender());
        mtvage.setText(getAgefromCivilId(dene.getCivilId()) + "");
        mtvunit.setText(dene.getUnit());
        mtvward.setText(dene.getWard());
        mtvbed.setText(dene.getBed());
        mtvhistory.setText(dene.getHistory());

    }

    private void PatienParameters() {
        mtvfunstatus = findViewById(R.id.tv__fun_status);
        mtvconsciousstatus = findViewById(R.id.tv__conscious_status);
        mtvgcsE = findViewById(R.id.tv__gcse);
        mtvgcsV = findViewById(R.id.tv__gcsv);
        mtvgcsM = findViewById(R.id.tv__gcsm);
        mgcstotoal = findViewById(R.id.tv_gcs_totola);

        mtvfunstatus.setText(dene.getPreMorbFunctionalStatus());
        mtvconsciousstatus.setText(dene.getPreMorbFunctionalConsciousStatus());
        mtvgcsE.setText(dene.getE());
        mtvgcsV.setText(dene.getV());
        mtvgcsM.setText(dene.getM());
        mgcstotoal.setText(dene.getTotalscore());


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

        mtvnumberdayvent.setText(dene.getDurOfConventianalMechanicalVentination());
        mtvspo2.setText(dene.getSpO2());
        mtvpo2.setText(dene.getPO2());
        mtvfio2.setText(dene.getFiO2());
        mtvpafi.setText(dene.getPao2fio2ratio());
        mtvpip.setText(dene.getPip());
        mtvpeep.setText(dene.getPeep());
        mtvtv.setText(dene.getTv());
        mtvrr.setText(dene.getRr());
        tvlung.setText(dene.getLungCompliance());
        mtvcxr.setText(dene.getCxrquadrants());

    }

    private void Cardiovascular() {

        mtvHr = findViewById(R.id.tv_hr);
        mtvBp = findViewById(R.id.tv_Bp);
        mtvCVp = findViewById(R.id.tv_cvp);
        mtvTemp = findViewById(R.id.tv_temp);
        mtvCo = findViewById(R.id.tv_co);
        mtvCardindex = findViewById(R.id.tv_cardiacindex);
        mtvleftventri = findViewById(R.id.tv_lvef);

        mtvHr.setText(dene.getHr());
        mtvBp.setText(dene.getBp());
        mtvCVp.setText(dene.getCvp());
        mtvTemp.setText(dene.getTemp());
        mtvCo.setText(dene.getCo());
        mtvCardindex.setText(dene.getCardiacindex());
        mtvleftventri.setText(dene.getLeftventricularejectionfraction());
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


        magentone.setText(dene.getInotropesagent1());
        magenttwo.setText(dene.getInotropesagent2());
        magentthree.setText(dene.getInotropesagent3());
        msagentone.setText(dene.getSedationagent1());
        msagentwo.setText(dene.getSedationagent2());
        msagentthree.setText(dene.getSedationagent3());
        mmagent.setText(dene.getMusclerelaxantsagent1());

        mdoseone.setText(dene.getInotropesdose1());
        mdosetwo.setText(dene.getInotropesdose2());
        mdosethree.setText(dene.getInotropesdose3());
        msdoseone.setText(dene.getSedationdose1());
        msdosewo.setText(dene.getSedationdose2());
        msdosethree.setText(dene.getSedationdose3());
        mmdose.setText(dene.getMusclerelaxantsdose1());

    }


    private void Investigation() {
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
        button = findViewById(R.id.fag_submit);


        mtvurea.setText(dene.getUrea());
        mtvcr.setText(dene.getCr());
        mtvlactate.setText(dene.getLactate());
        mtvUo.setText(dene.getUo());
        mtvDialysis.setText(dene.getDialysis());
        mtvph.setText(dene.getBloodgasPH());
        mtvinvpo2.setText(dene.getBloodgasPO2());
        mtvpco2.setText(dene.getBloodgasPCO2());
        mtvHco3.setText(dene.getBloodgasHCO3());
        mtvbe.setText(dene.getBloodgasBE());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitApplication(dene);
            }
        });
    }

    private void SubmitApplication(PatientFormRequest patientFormRequest) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
        Call<CommonResponse> call = apiService.getFormRequest(patientFormRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                commonLoaderstop();
                CommonResponse commonResponse = response.body();
                if (response != null && response.body().getResult().equalsIgnoreCase("FAILED") && response.message().contains(Constants.AUTH_FAIL)) {
                    LogoutSession();
                } else if (commonResponse != null) {
                    if (commonResponse.getResult().equalsIgnoreCase("Success")) {
                        commonToast("Sucsess");
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
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
