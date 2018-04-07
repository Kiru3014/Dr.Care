package com.ecmo.android.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.adaptors.SpinnerHospitaladapter;
import com.ecmo.android.adaptors.SpinnerSpecialtyadapter;
import com.ecmo.android.model.HospitalList;
import com.ecmo.android.model.SpecialityList;
import com.ecmo.android.utils.Helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PatientForm extends BaseActivity {
    //Sectionvisiablity
    LinearLayout llhospitalPatient, llpatientparameters;


    //Doctor and Patient Section
    FloatingActionButton fabscreenonenext;
    Spinner mspinnerhospital, mspinnerSpeciallity;
    TextView mtvdate, mhospital, mspecialty;
    EditText mtvadmdiagnosis;
    TextView mspinnerconcultant, mtvhospitaltitle, mtvSpeciallitytitle, mtvconcultanttitle, mtvadmdiagnosistitle, mtvdatetitle;
    String mdataSpeciallity = "", mdataconcultant, mdataadmdiagnosis, mdatahospital = "", mdatagender = "";
    RadioGroup radioSexGroup;
    EditText metfilenumber, metpacitentname, metunit, metward, metage, etcivilid, metbed, metextrainfo;
    String mdatafilenumber, mdatapacitentname, mdataunit, mdataward, mdataage, mdatacivilid, mdatabed;
    ArrayList<HospitalList> hospital;
    ArrayList<SpecialityList> specialityLists;
    SpinnerHospitaladapter spinnerHospitaladapter;
    SpinnerSpecialtyadapter spinnerSpecialtyadapter;
    String formattedDate;
    TextView mtvfilename, mtvpatientname, mtvcivilid, mtvgender, mtvage, mtvunit, mtvward, mtvbed, mtvhistory;

    //Pateint Parmanetres
    FloatingActionButton fabscreentwonext, fabscreentwoback;
    RadioGroup radiofunctionstatusGroup, radioconsciousstatusgroup;
    String functionstatus = "", conscioussstatus = "";
    Spinner metgcsE, metgcsv, metgcsM;
    String mdatagcsE, mdatagcsv, mdatagcsM;
    TextView mtvtotoalgcs, mtvpremobridfunction, mtvpremobridconscious;
    int totalgcs, Egcs=0, Vgcs=0, Mgcs=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
        getTodayDate();
        hospital = new ArrayList<HospitalList>();
        specialityLists = new ArrayList<SpecialityList>();
        llhospitalPatient = (LinearLayout) findViewById(R.id.section_hospital_patient);
        llpatientparameters = (LinearLayout) findViewById(R.id.section_patient_parameters);

        initDoctorPatient();
        PatientParameters();

    }

    private void getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);
    }

    private void initDoctorPatient() {

        //Doctor
        mspinnerhospital = (Spinner) findViewById(R.id.simpleSpinner_hospital);
        mspinnerSpeciallity = (Spinner) findViewById(R.id.simpleSpinner_speciality);
        mhospital = (TextView) findViewById(R.id.hospital);
        mspecialty = (TextView) findViewById(R.id.specialty);
        mhospital.setTypeface(Helper.getSharedHelper().getNormalFont());
        mspecialty.setTypeface(Helper.getSharedHelper().getNormalFont());


        //Hospital
        for (int i = 1; i < 8; i++) {
            hospital.add(new HospitalList("Hospital Name" + i, i));

        }
        spinnerHospitaladapter = new SpinnerHospitaladapter(getApplicationContext(), hospital);
        mspinnerhospital.setAdapter(spinnerHospitaladapter);
        mspinnerhospital.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                mdatahospital = hospital.get(pos).getHospitalName();
                mhospital.setText(mdatahospital);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        for (int i = 1; i < 8; i++) {
            specialityLists.add(new SpecialityList("Specialty Name" + i, i));

        }
        spinnerSpecialtyadapter = new SpinnerSpecialtyadapter(getApplicationContext(), specialityLists);
        mspinnerSpeciallity.setAdapter(spinnerSpecialtyadapter);

        mspinnerSpeciallity.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                mdataSpeciallity = specialityLists.get(pos).getSpecialtyName();
                mspecialty.setText(mdataSpeciallity);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        mtvdate = (TextView) findViewById(R.id.tv_date);
        mtvadmdiagnosis = (EditText) findViewById(R.id.et_diagnosis);
        mspinnerconcultant = (TextView) findViewById(R.id.tv_consultant);
        //header title
        mtvhospitaltitle = (TextView) findViewById(R.id.refhospital_title);
        mtvSpeciallitytitle = (TextView) findViewById(R.id.refspecialty_title);
        mtvconcultanttitle = (TextView) findViewById(R.id.refconsultant_title);
        mtvadmdiagnosistitle = (TextView) findViewById(R.id.refadmisson_title);
        mtvdatetitle = (TextView) findViewById(R.id.refdate_title);
        mspinnerconcultant.setTypeface(Helper.getSharedHelper().getNormalFont());
        mtvhospitaltitle.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvSpeciallitytitle.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvconcultanttitle.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvadmdiagnosistitle.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvdatetitle.setTypeface(Helper.getSharedHelper().getBoldFont());

        //Patient
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        metfilenumber = (EditText) findViewById(R.id.et_patientfilenumber);
        metpacitentname = (EditText) findViewById(R.id.et_patientname);
        etcivilid = (EditText) findViewById(R.id.et_patientcivilid);
        metunit = (EditText) findViewById(R.id.et_unit);
        metward = (EditText) findViewById(R.id.et_ward);
        metage = (EditText) findViewById(R.id.et_age);
        metbed = (EditText) findViewById(R.id.et_bed);
        metextrainfo = (EditText) findViewById(R.id.et_extra_infor);
        mtvadmdiagnosis.setTypeface(Helper.getSharedHelper().getNormalFont());
        metfilenumber.setTypeface(Helper.getSharedHelper().getNormalFont());
        metpacitentname.setTypeface(Helper.getSharedHelper().getNormalFont());
        metunit.setTypeface(Helper.getSharedHelper().getNormalFont());
        metward.setTypeface(Helper.getSharedHelper().getNormalFont());
        metage.setTypeface(Helper.getSharedHelper().getNormalFont());
        etcivilid.setTypeface(Helper.getSharedHelper().getNormalFont());
        metbed.setTypeface(Helper.getSharedHelper().getNormalFont());
        mtvdate.setTypeface(Helper.getSharedHelper().getNormalFont());
        metextrainfo.setTypeface(Helper.getSharedHelper().getNormalFont());
        mtvdate.setText(formattedDate);

        fabscreenonenext = (FloatingActionButton) findViewById(R.id.fag_screenone_next);

        fabscreenonenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidatedHospital();

            }
        });

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    mdatagender = rb.getText().toString();
                }

            }
        });
        //Patient
        mtvfilename = (TextView) findViewById(R.id.tvfname);
        mtvpatientname = (TextView) findViewById(R.id.tvpname);
        mtvcivilid = (TextView) findViewById(R.id.tvcid);
        mtvgender = (TextView) findViewById(R.id.tvgrnder);
        mtvage = (TextView) findViewById(R.id.tvagetite);
        mtvunit = (TextView) findViewById(R.id.tvfunit);
        mtvward = (TextView) findViewById(R.id.tvfward);
        mtvbed = (TextView) findViewById(R.id.tvfbed);
        mtvhistory = (TextView) findViewById(R.id.tvfhistory);

        mtvfilename.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvpatientname.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvcivilid.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvgender.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvage.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvunit.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvward.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvbed.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvhistory.setTypeface(Helper.getSharedHelper().getBoldFont());


    }


    //Hospital and Patient info
    private void ValidatedHospital() {
        mdataadmdiagnosis = mtvadmdiagnosis.getText().toString().trim();
        //patient
        mdatafilenumber = metfilenumber.getText().toString().trim();
        mdatapacitentname = metpacitentname.getText().toString().trim();
        mdatacivilid = etcivilid.getText().toString().trim();
        mdataunit = metunit.getText().toString().trim();
        mdataward = metward.getText().toString().trim();
        mdataage = metage.getText().toString().trim();
        mdatabed = metbed.getText().toString().trim();


        if (mdatahospital.isEmpty() || mdataSpeciallity.isEmpty()) {
            commonToast("Please Select Hospital and Speciality");
        } else if (mdataadmdiagnosis.isEmpty()) {
            mtvadmdiagnosis.setError("Enter Details");

        } else if (mdatafilenumber.isEmpty() || mdatacivilid.isEmpty() || mdataage.isEmpty()) {
            metfilenumber.setError("Enter Details");
            etcivilid.setError("Enter Details");
            metage.setError("Enter Details");
        } else if (mdatagender.isEmpty()) {
            commonToast("Please Select Gender");
        } else {
            commonToast("Next Screen");

            Log.d("Doctor Details\n",
                    "\n Hospital Name - " + mdatahospital +
                            " \n Speciality - " + mdataSpeciallity +
                            "\n Consultant - " + mdataconcultant +
                            "\n Date - " + mtvdate.getText().toString().trim() +
                            "\n Diagnosis - " + mdataadmdiagnosis +
                            "\n ----------------------------------------------\n" +
                            "Pacient Details" +
                            "\n File No - " + metfilenumber.getText().toString().trim() +
                            "\n Patient Name - " + metpacitentname.getText().toString().trim() +
                            "\n Civil ID - " + etcivilid.getText().toString().trim() +
                            "\n Gender - " + mdatagender +
                            "\n Age - " + metage.getText().toString().trim() +
                            "\n Unit - " + metunit.getText().toString().trim() +
                            "\n Ward - " + metward.getText().toString().trim() +
                            "\n Bed - " + metbed.getText().toString().trim() +
                            "\n History - " + metextrainfo.getText().toString().trim());

            llhospitalPatient.setVisibility(View.GONE);
            llpatientparameters.setVisibility(View.VISIBLE);


        }
    }


    //Patient Paramentes
    private void PatientParameters() {

        metgcsE = (Spinner) findViewById(R.id.et_gcs_e);
        metgcsv = (Spinner) findViewById(R.id.et_gcs_v);
        metgcsM = (Spinner) findViewById(R.id.et_gcs_m);
        mtvtotoalgcs = (TextView) findViewById(R.id.et_gcs_totola);
        radiofunctionstatusGroup = (RadioGroup) findViewById(R.id.radiofunctionstatus);
        radioconsciousstatusgroup = (RadioGroup) findViewById(R.id.radioconsciousstatus);

        mtvpremobridfunction = (TextView) findViewById(R.id.rb_pre_morbid_fun);
        mtvpremobridconscious = (TextView) findViewById(R.id.rb_pre_morbid_consc);
        mtvpremobridfunction.setTypeface(Helper.getSharedHelper().getBoldFont());
        mtvpremobridconscious.setTypeface(Helper.getSharedHelper().getBoldFont());


        radiofunctionstatusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    functionstatus = rb.getText().toString();
                }

            }
        });
        radioconsciousstatusgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    conscioussstatus = rb.getText().toString();
                }
            }
        });

        //Value of GCS E
        metgcsE.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                Egcs = pos;
                calcuateGCS();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //Value of GCS V
        metgcsv.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                Vgcs = pos;
                calcuateGCS();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //Value of GCS M
        metgcsM.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                Mgcs = pos;
                calcuateGCS();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        fabscreentwonext = (FloatingActionButton) findViewById(R.id.fag_fab_next);
        fabscreentwonext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ValidateParameters();
            }
        });


        fabscreentwoback = (FloatingActionButton) findViewById(R.id.fag_fab_previous);
        fabscreentwoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llpatientparameters.setVisibility(View.GONE);
                llhospitalPatient.setVisibility(View.VISIBLE);
            }
        });


    }

    @SuppressLint("SetTextI18n")
    private void calcuateGCS() {
        totalgcs = Egcs + Vgcs + Mgcs;
        mtvtotoalgcs.setText(totalgcs + " / 15");
        mtvtotoalgcs.setTypeface(Helper.getSharedHelper().getNormalFont());
    }


}
