package com.ecmo.android.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.ecmo.android.model.request.PatientFormRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientForm extends BaseActivity {
    //Sectionvisiablity
    LinearLayout llhospitalPatient, llpatientparameters, llventilationparameters, llcardiovascular, llagents, llInvestigation;
    UserPreferences userPreferences;


    //Doctor and Patient Section
    FloatingActionButton fabscreenonenext;
    Spinner mspinnerhospital, mspinnerSpeciallity;
    TextView mtvdate;
    EditText mtvadmdiagnosis;
    TextView mspinnerconcultant, mtvhospitaltitle, mtvSpeciallitytitle, mtvconcultanttitle, mtvadmdiagnosistitle, mtvdatetitle;
    String mdataSpeciallity = "", mdataconcultant, mdataadmdiagnosis, mdatahospital = "", mdatagender = "";
    RadioGroup radioSexGroup;
    EditText metfilenumber, metpacitentname, metunit, metward, metage, etcivilid, metbed, metextrainfo;
    String mdatafilenumber, mdatapacitentname, mdataunit, mdataward, mdataage, mdatacivilid, mdatabed;
    String formattedDate;
    TextView mtvfilename, mtvpatientname, mtvcivilid, mtvgender, mtvage, mtvunit, mtvward, mtvbed, mtvhistory;

    //Pateint Parmanetres
    FloatingActionButton fabscreentwonext, fabscreentwoback;
    RadioGroup radiofunctionstatusGroup, radioconsciousstatusgroup;
    String functionstatus = "", conscioussstatus = "";
    Spinner metgcsE, metgcsv, metgcsM;
    String mdatagcsE, mdatagcsv, mdatagcsM;
    TextView mtvtotoalgcs, mtvpremobridfunction, mtvpremobridconscious;
    int totalgcs, Egcs = 0, Vgcs = 0, Mgcs = 0;


    //Ventilation
    FloatingActionButton fabscreenthreenext, fabscreenthreeback;
    String spac02fio2 = "", speee = "", srr = "", slung = "", scrx = "";
    Spinner spinnerpafio2, spinnerpeep, spinnerrr, spinnerlungcompliance, spinnercxr;
    EditText etventdays, etspo2, etpo2, etfio2, etpipvalues, ettv;

    //cardiovascular
    FloatingActionButton fabscreenfournext, fabscreenfourback;
    Spinner spinnerhr, spinnerbp, spinnertemp, spinnercardiac, spinnerlvef;
    String shr, sbp, stemp, scardiac, slvef;
    EditText etcvp, etco;

    //Agents
    FloatingActionButton fabscreenfivenext, fabscreenfiveback;
    EditText etagentone, etagettwo, wtagentthree, etdoseone, etdosetwo, etdosethree;
    EditText etsedagentone, etsedagettwo, wtsedagentthree, etseddoseone, etseddosetwo, etseddosethree;
    EditText etmuscelagent, etmuscledose;


    //Investigation
    FloatingActionButton fabscreensixnext, fabscreensixback;
    RadioGroup radiodialysisGroup;
    String dialysis = "";
    EditText eturea, etcr, etlactate, etUo, etph, etinpo2, etpco2, ethco3, etbf, etrefphonenumber, erefddesignation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_form);
        userPreferences = new UserPreferences(getApplicationContext());
        getTodayDate();
        llhospitalPatient = (LinearLayout) findViewById(R.id.section_hospital_patient);
        llpatientparameters = (LinearLayout) findViewById(R.id.section_patient_parameters);
        llventilationparameters = (LinearLayout) findViewById(R.id.section_ventilation_parameters);
        llcardiovascular = (LinearLayout) findViewById(R.id.section_cardiovascular);
        llagents = (LinearLayout) findViewById(R.id.section_agents);
        llInvestigation = (LinearLayout) findViewById(R.id.section_investigation);

        initDoctorPatient();
        PatientParameters();
        Ventilation();
        Cardiovascular();
        Agents();
        Investigation();

    }


    /* Doctor and Patient Form begining*/
    private void getTodayDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        formattedDate = df.format(c);
    }

    private void initDoctorPatient() {

        //Doctor
        mspinnerhospital = (Spinner) findViewById(R.id.simpleSpinner_hospital);
        mspinnerSpeciallity = (Spinner) findViewById(R.id.simpleSpinner_speciality);


        mspinnerhospital.setAdapter(getAutosuggestion(getApplicationContext()));
        mspinnerhospital.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (pos != 0) {
                    mdatahospital = gethospitalvalue(adapterView.getItemAtPosition(pos).toString());

                } else {
                    mdatahospital = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        mspinnerSpeciallity.setAdapter(getSpeciality(getApplicationContext()));

        mspinnerSpeciallity.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (pos != 0) {
                    mdataSpeciallity = getSpecilityvalue(adapterView.getItemAtPosition(pos).toString());
                } else {
                    mdataSpeciallity = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        mtvdate = (TextView) findViewById(R.id.tv_date);
        mtvadmdiagnosis = (EditText) findViewById(R.id.et_diagnosis);
        mspinnerconcultant = (TextView) findViewById(R.id.tv_consultant);
        mspinnerconcultant.setText(userPreferences.getFirstName());
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

        etcivilid.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(s.length()>=10)
                {
                    metage.setText(getAgefromCivilId(etcivilid.getText().toString())+"");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

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
        mdataconcultant = userPreferences.getFirstName();

        if (mdatahospital.isEmpty() || mdataSpeciallity.isEmpty()) {
            commonToast("Please Select Hospital and Speciality");
        } else if (mdataadmdiagnosis.isEmpty()) {
            mtvadmdiagnosis.setError("Enter Details");

        } else if (mdatacivilid.isEmpty()) {
            etcivilid.setError("Enter Details");
        } else if (mdataage.isEmpty()) {
            metage.setError("Enter Details");
        } else if (mdatafilenumber.isEmpty()) {
            metfilenumber.setError("Enter Details");
        } else if (mdatagender.isEmpty()) {
            commonToast("Please Select Gender");
        } else {

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
    /* Doctor and Patient Form End*/


    /* Patient  Paramentes Form begining*/
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
                if (pos != 0) {
                    mdatagcsE = adapterView.getItemAtPosition(pos).toString();
                } else {
                    mdatagcsE = "";
                }
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
                if (pos != 0) {
                    mdatagcsv = adapterView.getItemAtPosition(pos).toString();
                } else {
                    mdatagcsv = "";
                }

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
                if (pos != 0) {
                    mdatagcsM = adapterView.getItemAtPosition(pos).toString();
                } else {
                    mdatagcsM = "";
                }


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
                ValidateParameters();
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

    private void ValidateParameters() {

        if (functionstatus.isEmpty() || conscioussstatus.isEmpty()) {
            commonToast("Enter Function and Conscious Status");
        } else if (mdatagcsE.isEmpty() || mdatagcsv.isEmpty() | mdatagcsM.isEmpty()) {
            commonToast("Please Select GCS Values");
        } else {
            Log.d("Patient Details \n",
                    "Function Status - " + functionstatus +
                            "\n Conscious Status - " + conscioussstatus +
                            "\n GCS E - " + mdatagcsE +
                            "\n GCS V - " + mdatagcsv +
                            "\n GCS M - " + mdatagcsM);
            llpatientparameters.setVisibility(View.GONE);
            llventilationparameters.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    private void calcuateGCS() {
        totalgcs = Egcs + Vgcs + Mgcs;
        mtvtotoalgcs.setText(totalgcs + " / 15");
        mtvtotoalgcs.setTypeface(Helper.getSharedHelper().getNormalFont());
    }
    /* Patient  Paramentes Form End*/


    /* Ventilation  Paramentes Form begining*/

    private void Ventilation() {
        etventdays = findViewById(R.id.et_number_ofdays);
        etspo2 = findViewById(R.id.et_spo2);
        etpo2 = findViewById(R.id.et_po2);
        etfio2 = findViewById(R.id.et_fio2);
        etpipvalues = findViewById(R.id.et_pip);
        ettv = findViewById(R.id.et_tv);

        etventdays.setTypeface(Helper.getSharedHelper().getNormalFont());
        etspo2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etpo2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etfio2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etpipvalues.setTypeface(Helper.getSharedHelper().getNormalFont());
        ettv.setTypeface(Helper.getSharedHelper().getNormalFont());

        // String spac02fio2="",speee="",srr="",slung="",scrx="";
        spinnerpafio2 = findViewById(R.id.et_fio2_paO2);
        spinnerpafio2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    spac02fio2 = adapterView.getItemAtPosition(pos).toString();
                } else {
                    spac02fio2 = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerpeep = findViewById(R.id.et_pieep);
        spinnerpeep.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    speee = adapterView.getItemAtPosition(pos).toString();
                } else {
                    speee = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerrr = findViewById(R.id.et_rr);
        spinnerrr.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    srr = adapterView.getItemAtPosition(pos).toString();
                } else {
                    srr = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerlungcompliance = findViewById(R.id.et_lung);
        spinnerlungcompliance.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    slung = adapterView.getItemAtPosition(pos).toString();
                } else {
                    slung = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        spinnercxr = findViewById(R.id.et_cxr);
        spinnercxr.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    scrx = adapterView.getItemAtPosition(pos).toString();
                } else {
                    scrx = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        fabscreenthreenext = (FloatingActionButton) findViewById(R.id.fag_ventilation_next);
        fabscreenthreenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spac02fio2="",speee="",srr="",slung="",scrx=""
                if (spac02fio2.isEmpty() || speee.isEmpty() || slung.isEmpty() || srr.isEmpty() || scrx.isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                } else if (etventdays.getText().toString().isEmpty() ||
                        etspo2.getText().toString().isEmpty() ||
                        etpo2.getText().toString().isEmpty() ||
                        etfio2.getText().toString().isEmpty() ||
                        etpipvalues.getText().toString().isEmpty() ||
                        ettv.getText().toString().isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                    etventdays.setError("Enter Details");
                    etspo2.setError("Enter Details");
                    etpo2.setError("Enter Details");
                    etfio2.setError("Enter Details");
                    etpipvalues.setError("Enter Details");
                    ettv.setError("Enter Details");
                } else {
                    Log.d("Ventil;ation Details\n",
                            "No dats in vent - " + etventdays.getText().toString() +
                                    "\n Spo2 - " + etspo2.getText().toString() +
                                    "\n po2 - " + etpo2.getText().toString() +
                                    "\n fio2 - " + etfio2.getText().toString() +
                                    "\n pao2/fio2 - " + spac02fio2 +
                                    "\n pip - " + etpipvalues.getText().toString() +
                                    "\n peep - " + speee +
                                    "\n TV - " + ettv.getText().toString() +
                                    "\n RR - " + srr +
                                    "\n lung - " + slung +
                                    "\n cxr - " + scrx);
                    llventilationparameters.setVisibility(View.GONE);
                    llcardiovascular.setVisibility(View.VISIBLE);
                }
            }
        });


        fabscreenthreeback = (FloatingActionButton) findViewById(R.id.fag_ventilation_previous);
        fabscreenthreeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llpatientparameters.setVisibility(View.VISIBLE);
                llventilationparameters.setVisibility(View.GONE);
            }
        });

    }

    /* Ventilation  Paramentes Form End*/



    /* Cardiovascular  Paramentes Form begining*/

    private void Cardiovascular() {

        /*
        String shr,sbp,stemp,scardiac,slvef;
        EditText etcvp,etco;*/

        etcvp = (EditText) findViewById(R.id.simpleSpinner_cvp);
        etco = (EditText) findViewById(R.id.et_co);
        etcvp.setTypeface(Helper.getSharedHelper().getNormalFont());
        etco.setTypeface(Helper.getSharedHelper().getNormalFont());

        spinnerhr = findViewById(R.id.simpleSpinner_hr);
        spinnerhr.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    shr = adapterView.getItemAtPosition(pos).toString();
                } else {
                    shr = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerbp = findViewById(R.id.simpleSpinner_Bp);
        spinnerbp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    sbp = adapterView.getItemAtPosition(pos).toString();
                } else {
                    sbp = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnertemp = findViewById(R.id.simpleSpinner_temp);
        spinnertemp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    stemp = adapterView.getItemAtPosition(pos).toString();
                } else {
                    stemp = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnercardiac = findViewById(R.id.simpleSpinner_cardiacindex);
        spinnercardiac.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    scardiac = adapterView.getItemAtPosition(pos).toString();
                } else {
                    scardiac = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerlvef = findViewById(R.id.simpleSpinner_lvef);
        spinnerlvef.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    slvef = adapterView.getItemAtPosition(pos).toString();
                } else {
                    slvef = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        fabscreenfournext = (FloatingActionButton) findViewById(R.id.fag_cardiovascular_next);
        fabscreenfournext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shr.isEmpty() || sbp.isEmpty() || stemp.isEmpty() || scardiac.isEmpty() || slvef.isEmpty()) {
                    commonToast("Please Select all Cardiovascular");
                } else if (etcvp.getText().toString().isEmpty() || etco.getText().toString().isEmpty()) {
                    etcvp.setError("Enter Details");
                    etco.setError("Enter Details");
                } else {
                    Log.d("Cardiovascular Details\n", "HR - " + shr +
                            "\n BP - " + sbp +
                            "\n Temp - " + stemp +
                            "\n Cardiac - " + scardiac +
                            "\n Left venticular - " + slvef +
                            "\n CVP - " + etcvp.getText().toString() +
                            "\n Co - " + etco.getText().toString());
                    llcardiovascular.setVisibility(View.GONE);
                    llagents.setVisibility(View.VISIBLE);
                }
            }
        });


        fabscreenfourback = (FloatingActionButton) findViewById(R.id.fag_cardiovascular_previous);
        fabscreenfourback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llcardiovascular.setVisibility(View.GONE);
                llventilationparameters.setVisibility(View.VISIBLE);
            }
        });
    }

    /* Cardiovascular  Paramentes Form End*/



    /* Agents  Paramentes Form begining*/

    private void Agents() {
        etagentone = findViewById(R.id.agentone);
        etagettwo = findViewById(R.id.agenttwo);
        wtagentthree = findViewById(R.id.agentthree);
        etdoseone = findViewById(R.id.doseone);
        etdosetwo = findViewById(R.id.dosetwo);
        etdosethree = findViewById(R.id.dosethree);
        etagentone.setTypeface(Helper.getSharedHelper().getNormalFont());
        etagettwo.setTypeface(Helper.getSharedHelper().getNormalFont());
        wtagentthree.setTypeface(Helper.getSharedHelper().getNormalFont());
        etdoseone.setTypeface(Helper.getSharedHelper().getNormalFont());
        etdosetwo.setTypeface(Helper.getSharedHelper().getNormalFont());
        etdosethree.setTypeface(Helper.getSharedHelper().getNormalFont());

        etsedagentone = findViewById(R.id.sedationagentone);
        etsedagettwo = findViewById(R.id.sedationagenttwo);
        wtsedagentthree = findViewById(R.id.sedationagentthree);
        etseddoseone = findViewById(R.id.sedationdoseone);
        etseddosetwo = findViewById(R.id.sedationdosetwo);
        etseddosethree = findViewById(R.id.sedationdosethree);

        etsedagentone.setTypeface(Helper.getSharedHelper().getNormalFont());
        etsedagettwo.setTypeface(Helper.getSharedHelper().getNormalFont());
        wtsedagentthree.setTypeface(Helper.getSharedHelper().getNormalFont());
        etseddoseone.setTypeface(Helper.getSharedHelper().getNormalFont());
        etseddosetwo.setTypeface(Helper.getSharedHelper().getNormalFont());
        etseddosethree.setTypeface(Helper.getSharedHelper().getNormalFont());


        etmuscelagent = findViewById(R.id.relaxantsagentone);
        etmuscledose = findViewById(R.id.relaxantsdoseone);
        etmuscelagent.setTypeface(Helper.getSharedHelper().getNormalFont());
        etmuscledose.setTypeface(Helper.getSharedHelper().getNormalFont());
        fabscreenfivenext = (FloatingActionButton) findViewById(R.id.fag_agent_next);
        fabscreenfivenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Agent Details", "" +
                        "\n Agent one - " + etagentone.getText().toString() +
                        "\n DOse One - " + etdoseone.getText().toString() +
                        "\n Agent two - " + etagettwo.getText().toString() +
                        "\n DOse two - " + etdosetwo.getText().toString() +
                        "\n Agent three - " + wtagentthree.getText().toString() +
                        "\n Dose three - " + etdosethree.getText().toString() +
                        "\n S Agent one - " + etsedagentone.getText().toString() +
                        "\n S DOse One - " + etseddoseone.getText().toString() +
                        "\n S Agent two - " + etsedagettwo.getText().toString() +
                        "\n S DOse two - " + etseddosetwo.getText().toString() +
                        "\n S Agent three - " + wtsedagentthree.getText().toString() +
                        "\n S Dose three - " + etseddosethree.getText().toString() +
                        "\n M Agent one - " + etmuscelagent.getText().toString() +
                        "\n M Dose one - " + etmuscledose.getText().toString() +
                        "\n");

                llagents.setVisibility(View.GONE);
                llInvestigation.setVisibility(View.VISIBLE);
            }
        });


        fabscreenfiveback = (FloatingActionButton) findViewById(R.id.fag_agent_previous);
        fabscreenfiveback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llagents.setVisibility(View.GONE);
                llcardiovascular.setVisibility(View.VISIBLE);
            }
        });
    }

    /* Agents  Paramentes Form End*/



    /* Investigation  Paramentes Form begining*/

    private void Investigation() {

        eturea = findViewById(R.id.et_urea);
        etcr = findViewById(R.id.et_cr);
        etlactate = findViewById(R.id.et_lactate);
        etUo = findViewById(R.id.et_uo);
        etph = findViewById(R.id.et_ph);
        etinpo2 = findViewById(R.id.et_ingpo2);
        etpco2 = findViewById(R.id.et_pco2);
        ethco3 = findViewById(R.id.et_hco3);
        etbf = findViewById(R.id.et_bf);
        etrefphonenumber = findViewById(R.id.et_refdoctelephone);
        etrefphonenumber.setText(userPreferences.getKeyUserMob());
        erefddesignation = findViewById(R.id.et_refdocdesignation);
        etrefphonenumber.setTypeface(Helper.getSharedHelper().getNormalFont());
        erefddesignation.setTypeface(Helper.getSharedHelper().getNormalFont());
        eturea.setTypeface(Helper.getSharedHelper().getNormalFont());
        etcr.setTypeface(Helper.getSharedHelper().getNormalFont());
        etlactate.setTypeface(Helper.getSharedHelper().getNormalFont());
        etUo.setTypeface(Helper.getSharedHelper().getNormalFont());
        etph.setTypeface(Helper.getSharedHelper().getNormalFont());
        etinpo2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etpco2.setTypeface(Helper.getSharedHelper().getNormalFont());
        ethco3.setTypeface(Helper.getSharedHelper().getNormalFont());
        etbf.setTypeface(Helper.getSharedHelper().getNormalFont());

        radiodialysisGroup = (RadioGroup) findViewById(R.id.radiodialysis);
        radiodialysisGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    dialysis = rb.getText().toString();
                }

            }
        });


        fabscreensixnext = (FloatingActionButton) findViewById(R.id.fag_investigation_next);
        fabscreensixnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eturea.getText().toString().isEmpty() || etcr.getText().toString().isEmpty() || etUo.getText().toString().isEmpty() || etpco2.getText().toString().isEmpty() || ethco3.getText().toString().isEmpty()) {
                    eturea.setError("Enter Details");
                    etcr.setError("Enter Details");
                    etUo.setError("Enter Details");
                    etpco2.setError("Enter Details");
                    ethco3.setError("Enter Details");
                } else if (dialysis.isEmpty()) {
                    commonToast("Please Select Dialysis");
                } else {

                    final PatientFormRequest patientFormRequest = new PatientFormRequest(
                            mdatahospital,
                            mdataSpeciallity,
                            userPreferences.getUserId(),
                            mdataconcultant,
                            mdataadmdiagnosis,
                            metpacitentname.getText().toString().trim(),
                            etcivilid.getText().toString().trim(),
                            mdatagender,
                            metunit.getText().toString().trim(),
                            metward.getText().toString().trim(),
                            metbed.getText().toString().trim(),
                            metfilenumber.getText().toString().trim(),
                            functionstatus,
                            conscioussstatus,
                            mdatagcsE,
                            mdatagcsv,
                            mdatagcsM,
                            mtvtotoalgcs.getText().toString(),
                            etventdays.getText().toString(),
                            etspo2.getText().toString(),
                            etpo2.getText().toString(),
                            etfio2.getText().toString(),
                            spac02fio2,
                            etpipvalues.getText().toString(),
                            speee,
                            ettv.getText().toString(),
                            srr,
                            slung,
                            scrx,
                            shr,
                            sbp,
                            etcvp.getText().toString(),
                            stemp,
                            etco.getText().toString(),
                            scardiac,
                            slvef,
                            etagentone.getText().toString(),
                            etdoseone.getText().toString(),
                            etagettwo.getText().toString(),
                            etdosetwo.getText().toString(),
                            wtagentthree.getText().toString(),
                            etdosethree.getText().toString(),
                            etsedagentone.getText().toString(),
                            etseddoseone.getText().toString(),
                            etsedagettwo.getText().toString(),
                            etseddosetwo.getText().toString(),
                            wtsedagentthree.getText().toString(),
                            etseddosethree.getText().toString(),
                            etmuscelagent.getText().toString(),
                            etmuscledose.getText().toString(),
                            eturea.getText().toString(),
                            etcr.getText().toString(),
                            etlactate.getText().toString(),
                            etUo.getText().toString(),
                            dialysis,
                            etph.getText().toString(),
                            etinpo2.getText().toString(),
                            etpco2.getText().toString(),
                            ethco3.getText().toString(),
                            etbf.getText().toString(),
                            erefddesignation.getText().toString(),
                            etrefphonenumber.getText().toString(),
                            metextrainfo.getText().toString(),
                            "insert",
                            userPreferences.getSession()
                    );

                    Submitalert(patientFormRequest);

                    Log.d("Investigation Details", "" +
                            "\n urea - " + eturea.getText().toString() +
                            "\n Cr - " + etcr.getText().toString() +
                            "\n Lactate - " + etlactate.getText().toString() +
                            "\n UO - " + etUo.getText().toString() +
                            "\n Dialysis - " + dialysis +
                            "\n ph - " + etph.getText().toString() +
                            "\n po2 - " + etinpo2.getText().toString() +
                            "\n pco2 - " + etpco2.getText().toString() +
                            "\n hco3 - " + ethco3.getText().toString() +
                            "\n bf - " + etbf.getText().toString());
                }

            }
        });


        fabscreensixback = (FloatingActionButton) findViewById(R.id.fag_investigation_previous);
        fabscreensixback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llInvestigation.setVisibility(View.GONE);
                llagents.setVisibility(View.VISIBLE);
            }
        });

    }

    /* Investigation  Paramentes Form End*/


    private void Submitalert(final PatientFormRequest patientFormRequest)
    {

        AlertDialog alertDialog = new AlertDialog.Builder(PatientForm.this).create();
        alertDialog.setTitle("YOU Want to Preview Application Or Submit Application");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SUBMIT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SubmitApplication(patientFormRequest);
                    }
                });


        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Preview",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), FormPreviewActivity.class);
                        intent.putExtra("MyClass", (Serializable) patientFormRequest);
                        startActivity(intent);
                    }
                });
        alertDialog.show();

    }


    private void SubmitApplication(PatientFormRequest patientFormRequest) {
        commonLoaderstart();
        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
    /*    final PatientFormRequest patientFormRequest = new PatientFormRequest(
                mdatahospital,
                mdataSpeciallity,
                userPreferences.getUserId(),
                mdataconcultant,
                mdataadmdiagnosis,
                metpacitentname.getText().toString().trim(),
                etcivilid.getText().toString().trim(),
                mdatagender,
                metunit.getText().toString().trim(),
                metward.getText().toString().trim(),
                metbed.getText().toString().trim(),
                metfilenumber.getText().toString().trim(),
                functionstatus,
                conscioussstatus,
                mdatagcsE,
                mdatagcsv,
                mdatagcsM,
                mtvtotoalgcs.getText().toString(),
                etventdays.getText().toString(),
                etspo2.getText().toString(),
                etpo2.getText().toString(),
                etfio2.getText().toString(),
                spac02fio2,
                etpipvalues.getText().toString(),
                speee,
                ettv.getText().toString(),
                srr,
                slung,
                scrx,
                shr,
                sbp,
                etcvp.getText().toString(),
                stemp,
                etco.getText().toString(),
                scardiac,
                slvef,
                etagentone.getText().toString(),
                etdoseone.getText().toString(),
                etagettwo.getText().toString(),
                etdosetwo.getText().toString(),
                wtagentthree.getText().toString(),
                etdosethree.getText().toString(),
                etsedagentone.getText().toString(),
                etseddoseone.getText().toString(),
                etsedagettwo.getText().toString(),
                etseddosetwo.getText().toString(),
                wtsedagentthree.getText().toString(),
                etseddosethree.getText().toString(),
                etmuscelagent.getText().toString(),
                etmuscledose.getText().toString(),
                eturea.getText().toString(),
                etcr.getText().toString(),
                etlactate.getText().toString(),
                etUo.getText().toString(),
                dialysis,
                etph.getText().toString(),
                etinpo2.getText().toString(),
                etpco2.getText().toString(),
                ethco3.getText().toString(),
                etbf.getText().toString(),
                erefddesignation.getText().toString(),
                etrefphonenumber.getText().toString(),
                metextrainfo.getText().toString(),
                "insert",
                userPreferences.getSession()
        );*/

        Call<CommonResponse> call = apiService.getFormRequest(patientFormRequest);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response)
            {
                commonLoaderstop();
                CommonResponse commonResponse = response.body();
                if (commonResponse != null)
                {
                    if (commonResponse.getResult().equalsIgnoreCase("Success"))
                    {
                        commonToast("Sucsess");
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
