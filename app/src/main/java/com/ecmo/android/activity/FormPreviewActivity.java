package com.ecmo.android.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.adaptors.GalleryAdapter;
import com.ecmo.android.model.request.PatientFormRequest;
import com.ecmo.android.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
    TextView mtvHr,mtvBp,mtvCVp,mtvTemp,mtvCo,mtvCardindex,mtvleftventri;


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
        mtvage.setText("--");
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

    private void Cardiovascular()
    {

        mtvHr=findViewById(R.id.tv_hr);
        mtvBp=findViewById(R.id.tv_Bp);
        mtvCVp=findViewById(R.id.tv_cvp);
        mtvTemp=findViewById(R.id.tv_temp);
        mtvCo=findViewById(R.id.tv_co);
        mtvCardindex=findViewById(R.id.tv_cardiacindex);
        mtvleftventri=findViewById(R.id.tv_lvef);

        mtvHr.setText(dene.getHr());
        mtvBp.setText(dene.getBp());
        mtvCVp.setText(dene.getCvp());
        mtvTemp.setText(dene.getTemp());
        mtvCo.setText(dene.getCo());
        mtvCardindex.setText(dene.getCardiacindex());
        mtvleftventri.setText(dene.getLeftventricularejectionfraction());
    }

    private void Investigation() {

    }

    private void Agents() {

    }
}
