package com.ecmo.android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.DecimalFormat;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.model.request.PatientFormRequest;
import com.ecmo.android.model.response.CommonResponse;
import com.ecmo.android.model.response.referalformResponse;
import com.ecmo.android.rest.ApiClient;
import com.ecmo.android.rest.ApiInterface;
import com.ecmo.android.utils.Constants;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    String sdays = "", sfio2 = "", spac02fio2 = "", speee = "", srr = "", slung = "", scrx = "";
    Spinner spinnercxr, etfio2, etventdays;
    EditText etspo2, etpo2, etpipvalues, ettv, spinnerpafio2, spinnerpeep, spinnerrr, spinnerlungcompliance, etmurrayscore;
    int scorepao2fio2;
    int scorecxr;
    int scorepeep;
    int scorelung;


    //cardiovascular
    FloatingActionButton fabscreenfournext, fabscreenfourback;
    Spinner spinnerhr, spinnercardiac;
    String shr, sbp, stemp, scardiac, slvef;
    EditText etcvp, etco, etbps, etbpp, spinnertemp, spinnerlvef;

    //Agents
    FloatingActionButton fabscreenfivenext, fabscreenfiveback;

    Spinner etagentone, etagettwo, wtagentthree, etagentfour, etagetfive;
    EditText etdoseone, etdosetwo, etdosethree, etdosefour, etdosefive;
    Spinner spindoseone, spindosetwo, spindosethree, spindosefour, spindosefive;
    EditText etothersdose, etothersdosetwo, etothersdosehree, etothersdosefour, etothersdosefive;

    String sagentone = "", sagettwo = "", sagentthree = "", sagentfour = "", sagetfive = "";
    String unitdoseone = "", unitdosetwo = "", unitdosethree = "", unitdosefour = "", unitdosefive = "";

    EditText etseddoseone, etseddosetwo, etseddosethree, etseddosefour, etseddosefive;
    Spinner etsedagentone, etsedagettwo, wtsedagentthree, etsedagentfour, etsedagetfive;
    EditText etsedagenothersdose, etsedagenothersdosetwo, etsedagenothersdosehree, etsedagenothersdosefour, etsedagenothersdosefive;
    Spinner sedagenspindoseone, sedagenspindosetwo, sedagenspindosethree, sedagenspindosefour, sedagenspindosefive;

    String ssedagentone = "", ssedagettwo = "", ssedagentthree = "", ssedagentfour = "", ssedagetfive = "";
    String unitsedadoseone = "", unitsedadosetwo = "", unitsedadosethree = "", unitsedadosefour = "", unitsedadosefive = "";


    //Investigation
    FloatingActionButton fabscreensixnext, fabscreensixback;
    RadioGroup radiodialysisGroup, rbpronposing, rbpronposingimp, rbniticacid, rbniticacidimp, rbplasmaphersis, rbplasmaphersisimp, rbtherapuethyp, rbtherapuethypimp, rbothersimp;
    EditText rbothers;
    String dialysis = "";
    String spronposing = "", spronposingimp = "", sniticacid = "", sniticacidimp = "", splasmaphersis = "", splasmaphersisimp = "", stherapuethyp = "", stherapuethypimp = "", sothersimp = "";
    EditText eturea, etcr, etlactate, etUo, etph, etinpo2, etpco2, ethco3, etbf, etrefphonenumber, erefddesignation;
    EditText etabglac, etsao2, etspo2bg;

    referalformResponse formdata;

    //camera
    private Button btn_one, btn_two, btn_three;
    private ImageView imageview_one, imageview_two, imageview_three;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    String attachment = "null";
    String filetype = "0";
    byte[] bitmapone, bitmaptwo, bitmapthree;
    private ImageView imageviewclose_one, imageviewclose_two, imageviewclose_three;


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

        Intent i = getIntent();
        formdata = (referalformResponse) i.getSerializableExtra("resendform");

        initDoctorPatient();
        PatientParameters();
        Ventilation();
        Cardiovascular();
        Agents();
        Investigation();
        camera();


        if (formdata != null) {
            ResendDataFill();
        }


    }


    @Override
    public void onBackPressed() {
        showConfirmdialog(PatientForm.this);
    }

    public void showConfirmdialog(Context c) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);

        alertDialogBuilder.setTitle("Entered referral data will be lost, you want to close form?");

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
                        dialog.dismiss();
                        finish();

                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
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

        mspinnerhospital.setSelection(getspinnerIndexvalue(mspinnerhospital, userPreferences.getKeyUserHospital()));
        mspinnerSpeciallity.setSelection(getspinnerIndexvalue(mspinnerSpeciallity, userPreferences.getKeyUserSpeciality()));


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
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 10) {
                    metage.setText(getAgefromCivilId(etcivilid.getText().toString()) + "");
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
            commonToast("Please Enter all Details");

        } else if (mdatacivilid.isEmpty() || mdatacivilid.length() < 12) {
            etcivilid.setError("Enter Details");
            commonToast("Please Enter Correct CivilID");
        } else if (mdataage.isEmpty()) {
            metage.setError("Enter Details");
            commonToast("Please Enter all Details");

        } else if (mdatafilenumber.isEmpty()) {
            metfilenumber.setError("Enter Details");
            commonToast("Please Enter all Details");

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
        etmurrayscore = findViewById(R.id.et_murryscore);

        etspo2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etpo2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etpipvalues.setTypeface(Helper.getSharedHelper().getNormalFont());
        ettv.setTypeface(Helper.getSharedHelper().getNormalFont());
        etmurrayscore.setTypeface(Helper.getSharedHelper().getNormalFont());

        // String spac02fio2="",speee="",srr="",slung="",scrx="";
        spinnerpafio2 = findViewById(R.id.et_fio2_paO2);

        //pao2
        etpo2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Your query to fetch Data
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    //commonToast(s+"");
                    calacualtepao2fio2();
                }
            }
        });

        //fio2
        etfio2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                if (pos != 0) {
                    sfio2 = adapterView.getItemAtPosition(pos).toString();
                    calacualtepao2fio2();
                } else {
                    sfio2 = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //number of days
        etventdays.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                if (pos != 0) {
                    sdays = adapterView.getItemAtPosition(pos).toString();

                } else {
                    sdays = "";
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinnerpeep = findViewById(R.id.et_pieep);
      /*  spinnerpeep.setOnItemSelectedListener(new OnItemSelectedListener() {

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
*/


        spinnerrr = findViewById(R.id.et_rr);
       /* spinnerrr.setOnItemSelectedListener(new OnItemSelectedListener() {

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
        });*/

        spinnerlungcompliance = findViewById(R.id.et_lung);
       /* spinnerlungcompliance.setOnItemSelectedListener(new OnItemSelectedListener() {

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
        });*/


        spinnercxr = findViewById(R.id.et_cxr);
        spinnercxr.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {


                if (pos != 0) {
                    scrx = adapterView.getItemAtPosition(pos).toString();
                    scorecxr = Integer.parseInt(scrx);
                    murryscore();
                } else {
                    scrx = "";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //lung comple calaculation

        //tv
        ettv.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Your query to fetch Data
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    //  commonToast(s+"");
                    calculationlung();
                }
            }
        });

        //pip
        etpipvalues.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Your query to fetch Data
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    //  commonToast(s+"");
                    calculationlung();
                }
            }
        });

        //peep
        spinnerpeep.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //Your query to fetch Data
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    //  commonToast(s+"");
                    calculationlung();
                }
            }
        });


        fabscreenthreenext = (FloatingActionButton) findViewById(R.id.fag_ventilation_next);
        fabscreenthreenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speee = spinnerpeep.getText().toString();
                srr = spinnerrr.getText().toString();
                slung = spinnerlungcompliance.getText().toString();
                spac02fio2 = spinnerpafio2.getText().toString();

                if (spac02fio2.isEmpty() || speee.isEmpty() || slung.isEmpty() || srr.isEmpty() || scrx.isEmpty() || sfio2.isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                } else if (sdays.isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                } else if (etspo2.getText().toString().isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                    etspo2.setError("Enter Details");
                } else if (Float.valueOf(etspo2.getText().toString()) > 100 || Float.valueOf(etspo2.getText().toString()) < 0) {
                    etspo2.setError("SpO2 Value Should be between 0-100");
                    commonToast("SpO2 Value Should be between 0-100");
                } else if (etpo2.getText().toString().isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                    etpo2.setError("Enter Details");
                } else if (Float.valueOf(etpo2.getText().toString()) > 100 || Float.valueOf(etpo2.getText().toString()) < 1) {
                    commonToast("PaO2 Value Should be between 1-100");
                    etpo2.setError("PaO2 Value Should be between 1-100");
                } else if (etpipvalues.getText().toString().isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                    etpipvalues.setError("Enter Details");
                } else if (ettv.getText().toString().isEmpty()) {
                    commonToast("Please enter all Ventilation Parameters");
                    ettv.setError("Enter Details");
                } else if (Float.valueOf(ettv.getText().toString()) > 600 || Float.valueOf(ettv.getText().toString()) < 0) {
                    commonToast("TV Value Should be between 0-600");
                    ettv.setError("TV Value Should be between 0-600");
                } else if (Float.valueOf(spinnerrr.getText().toString()) > 600 || Float.valueOf(spinnerrr.getText().toString()) < 0) {
                    commonToast("RR Value Should be between 0-600");
                    spinnerrr.setError("RR Value Should be between 0-600");
                } else {
                    Log.d("Ventil;ation Details\n",
                            "No dats in vent - " + sdays +
                                    "\n Spo2 - " + etspo2.getText().toString() +
                                    "\n po2 - " + etpo2.getText().toString() +
                                    "\n fio2 - " + sfio2 +
                                    "\n pao2/fio2 - " + spac02fio2 +
                                    "\n pip - " + etpipvalues.getText().toString() +
                                    "\n peep - " + speee +
                                    "\n TV - " + ettv.getText().toString() +
                                    "\n RR - " + srr +
                                    "\n lung - " + slung +
                                    "\n cxr - " + scrx);

                    Log.d("SCORE pao2/fio2", "" + scorepao2fio2);
                    Log.d("SCORE cxr", "" + scorecxr);
                    Log.d("SCORE peep", "" + scorepeep);
                    Log.d("SCORE lung", "" + scorelung);

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

    private void calculationlung() {

        if (ettv.getText().toString().trim().isEmpty() || etpipvalues.getText().toString().trim().isEmpty() || spinnerpeep.getText().toString().isEmpty()) {
            spinnerlungcompliance.setText("");
        } else {

            Float peepval = Float.valueOf(spinnerpeep.getText().toString());

            if (peepval <= 5.9) {
                scorepeep = 0;
            } else if (peepval >= 6 && peepval <= 8.9) {
                scorepeep = 1;
            } else if (peepval >= 9 && peepval <= 11.9) {
                scorepeep = 2;
            } else if (peepval >= 12 && peepval <= 14.9) {
                scorepeep = 3;
            } else if (peepval >= 15) {
                scorepeep = 4;
            }


            String ettvtemp = ettv.getText().toString();
            if (ettv.getText().toString().startsWith(".")) {
                ettvtemp = "0" + ettv.getText().toString();
            }

            String etpiptemp = etpipvalues.getText().toString();
            if (etpipvalues.getText().toString().startsWith(".")) {
                etpiptemp = "0" + etpipvalues.getText().toString();
            }
            String etpeeptemp = spinnerpeep.getText().toString();
            if (spinnerpeep.getText().toString().startsWith(".")) {
                etpeeptemp = "0" + spinnerpeep.getText().toString();
            }
            Float tv = Float.valueOf(ettvtemp);
            Float pip = Float.valueOf(etpiptemp);
            Float peep = Float.valueOf(etpeeptemp);
            Float pippee = pip - peep;
            Float lungcomp = tv / pippee;
            slung = lungcomp.toString();
            spinnerlungcompliance.setText(slung);

            int scrlungval = (int) Math.round(lungcomp);
            if (scrlungval >= 80) {
                scorelung = 0;
            } else if (scrlungval >= 60 && scrlungval <= 79.9) {
                scorelung = 1;
            } else if (scrlungval >= 40 && scrlungval <= 59.9) {
                scorelung = 2;
            } else if (scrlungval >= 20 && scrlungval <= 39.9) {
                scorelung = 3;
            } else if (scrlungval <= 19.9) {
                scorelung = 4;
            }

            murryscore();
        }

    }

    private void calacualtepao2fio2() {
        if (etpo2.getText().toString().trim().isEmpty() || sfio2.isEmpty()) {
            spinnerpafio2.setText("");
        } else {
            String etfio2temp = sfio2;

            String etpao2temp = etpo2.getText().toString();
            if (etpo2.getText().toString().startsWith(".")) {
                etpao2temp = "0" + etpo2.getText().toString();
            }

            Float pao2 = Float.valueOf(etpao2temp);
            Float fio2 = Float.valueOf(etfio2temp);
            Float pao2fao2 = pao2 / fio2;
            String pao2faotmmhg = "";

            if (pao2fao2 < 100.9) {
                pao2faotmmhg = "<100";
                scorepao2fio2 = 4;
            } else if (pao2fao2 >= 100 && pao2fao2 <= 174.9) {
                pao2faotmmhg = "100-174";
                scorepao2fio2 = 3;

            } else if (pao2fao2 >= 175 && pao2fao2 <= 224.9) {
                pao2faotmmhg = "175-224";
                scorepao2fio2 = 2;

            } else if (pao2fao2 >= 225 && pao2fao2 <= 229.9) {
                pao2faotmmhg = "225-229";
                scorepao2fio2 = 1;

            } else if (pao2fao2 > 300) {
                pao2faotmmhg = "300";
                scorepao2fio2 = 0;

            }
            spac02fio2 = pao2fao2.toString();
            spinnerpafio2.setText(spac02fio2);
            murryscore();
        }

    }

    /* Ventilation  Paramentes Form End*/



    /* Cardiovascular  Paramentes Form begining*/

    private void Cardiovascular() {

        /*
        String shr,sbp,stemp,scardiac,slvef;
        EditText etcvp,etco;*/

        etcvp = (EditText) findViewById(R.id.simpleSpinner_cvp);
        etco = (EditText) findViewById(R.id.et_co);
        etbps = (EditText) findViewById(R.id.bp_s);
        etbpp = (EditText) findViewById(R.id.bp_p);

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


        spinnertemp = findViewById(R.id.simpleSpinner_temp);
        /*spinnertemp.setOnItemSelectedListener(new OnItemSelectedListener() {

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
*/
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
        /*spinnerlvef.setOnItemSelectedListener(new OnItemSelectedListener() {

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
*/

        fabscreenfournext = (FloatingActionButton) findViewById(R.id.fag_cardiovascular_next);
        fabscreenfournext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sbp = etbps.getText().toString() + "-" + etbpp.getText().toString();
                stemp = spinnertemp.getText().toString();
                slvef = spinnerlvef.getText().toString();

                if (shr.isEmpty() || sbp.isEmpty() || stemp.isEmpty() || scardiac.isEmpty() || slvef.isEmpty()) {
                    commonToast("Please Select all Cardiovascular");
                } else if (etbps.getText().toString().isEmpty() || etbpp.getText().toString().isEmpty()) {
                    commonToast("Please Enter BP Values");
                } else if (Float.valueOf(spinnerlvef.getText().toString().trim()) < 0 || Integer.parseInt(spinnerlvef.getText().toString().trim()) > 100) {
                    commonToast("Please Enter correct Left Ventricular Ejection Fraction ");
                    spinnerlvef.setError("Please Enter correct Left Ventricular Ejection Fraction ");
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
        //agent
        etagentone = (Spinner) findViewById(R.id.agentone);
        etagettwo = (Spinner) findViewById(R.id.agenttwo);
        wtagentthree = (Spinner) findViewById(R.id.agentthree);
        etagentfour = (Spinner) findViewById(R.id.agentfour);
        etagetfive = (Spinner) findViewById(R.id.agentfive);

        etdoseone = (EditText) findViewById(R.id.doseone);
        etdosetwo = (EditText) findViewById(R.id.dosetwo);
        etdosethree = (EditText) findViewById(R.id.dosethree);
        etdosefour = (EditText) findViewById(R.id.dosefour);
        etdosefive = (EditText) findViewById(R.id.dosefive);

        spindoseone = (Spinner) findViewById(R.id.doseoneunit);
        spindosetwo = (Spinner) findViewById(R.id.dosetwounit);
        spindosethree = (Spinner) findViewById(R.id.dosethreeunit);
        spindosefour = (Spinner) findViewById(R.id.dosefourunit);
        spindosefive = (Spinner) findViewById(R.id.dosefiveunit);

        etothersdose = (EditText) findViewById(R.id.otheragentone);
        etothersdosetwo = (EditText) findViewById(R.id.otheragenttwo);
        etothersdosehree = (EditText) findViewById(R.id.otheragentthree);
        etothersdosefour = (EditText) findViewById(R.id.otheragentfour);
        etothersdosefive = (EditText) findViewById(R.id.otheragentfive);

        etagentone.setAdapter(getAgents(getApplicationContext()));
        spindoseone.setAdapter(getUnits(getApplicationContext()));
        etagettwo.setAdapter(getAgents(getApplicationContext()));
        spindosetwo.setAdapter(getUnits(getApplicationContext()));
        wtagentthree.setAdapter(getAgents(getApplicationContext()));
        spindosethree.setAdapter(getUnits(getApplicationContext()));
        etagentfour.setAdapter(getAgents(getApplicationContext()));
        spindosefour.setAdapter(getUnits(getApplicationContext()));
        etagetfive.setAdapter(getAgents(getApplicationContext()));
        spindosefive.setAdapter(getUnits(getApplicationContext()));

        //agent one
        etagentone.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {
                    sagentone = adapterView.getItemAtPosition(pos).toString();
                    etothersdose.setVisibility(View.VISIBLE);
                } else {
                    sagentone = adapterView.getItemAtPosition(pos).toString();
                    etothersdose.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spindoseone.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitdoseone = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //agentwo
        etagettwo.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {
                    sagettwo = adapterView.getItemAtPosition(pos).toString();
                    etothersdosetwo.setVisibility(View.VISIBLE);
                } else {
                    sagettwo = adapterView.getItemAtPosition(pos).toString();
                    etothersdosetwo.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spindosetwo.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitdosetwo = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        //agenthree
        wtagentthree.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {
                    sagentthree = adapterView.getItemAtPosition(pos).toString();
                    etothersdosehree.setVisibility(View.VISIBLE);
                } else {
                    sagentthree = adapterView.getItemAtPosition(pos).toString();
                    etothersdosehree.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spindosethree.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitdosethree = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //agenfour
        etagentfour.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {
                    sagentfour = adapterView.getItemAtPosition(pos).toString();
                    etothersdosefour.setVisibility(View.VISIBLE);
                } else {
                    sagentfour = adapterView.getItemAtPosition(pos).toString();
                    etothersdosefour.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spindosefour.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitdosefour = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        //agenfive
        etagetfive.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {
                    sagetfive = adapterView.getItemAtPosition(pos).toString();
                    etothersdosefive.setVisibility(View.VISIBLE);
                } else {
                    sagetfive = adapterView.getItemAtPosition(pos).toString();
                    etothersdosefive.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spindosefive.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitdosefive = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        //seduction

        etsedagentone = (Spinner) findViewById(R.id.sedationagentone);
        etsedagettwo = (Spinner) findViewById(R.id.sedationagenttwo);
        wtsedagentthree = (Spinner) findViewById(R.id.sedationagentthree);
        etsedagentfour = (Spinner) findViewById(R.id.sedationagentfour);
        etsedagetfive = (Spinner) findViewById(R.id.sedationagentfive);

        etseddoseone = (EditText) findViewById(R.id.sedationdoseone);
        etseddosetwo = (EditText) findViewById(R.id.sedationdosetwo);
        etseddosethree = (EditText) findViewById(R.id.sedationdosethree);
        etseddosefour = (EditText) findViewById(R.id.sedationdosefour);
        etseddosefive = (EditText) findViewById(R.id.sedationdosefive);


        etsedagenothersdose = (EditText) findViewById(R.id.sedationotheragentone);
        etsedagenothersdosetwo = (EditText) findViewById(R.id.sedationotheragenttwo);
        etsedagenothersdosehree = (EditText) findViewById(R.id.sedationotheragentthree);
        etsedagenothersdosefour = (EditText) findViewById(R.id.sedationotheragentfour);
        etsedagenothersdosefive = (EditText) findViewById(R.id.sedationotheragentfive);


        sedagenspindoseone = (Spinner) findViewById(R.id.sedationdoseoneunit);
        sedagenspindosetwo = (Spinner) findViewById(R.id.sedationdosetwounit);
        sedagenspindosethree = (Spinner) findViewById(R.id.sedationdosethreeunit);
        sedagenspindosefour = (Spinner) findViewById(R.id.sedationdosefourunit);
        sedagenspindosefive = (Spinner) findViewById(R.id.sedationdosefiveunit);


        etsedagentone.setAdapter(getSedation(getApplicationContext()));
        sedagenspindoseone.setAdapter(getUnits(getApplicationContext()));
        etsedagettwo.setAdapter(getSedation(getApplicationContext()));
        sedagenspindosetwo.setAdapter(getUnits(getApplicationContext()));
        wtsedagentthree.setAdapter(getSedation(getApplicationContext()));
        sedagenspindosethree.setAdapter(getUnits(getApplicationContext()));
        etsedagentfour.setAdapter(getSedation(getApplicationContext()));
        sedagenspindosefour.setAdapter(getUnits(getApplicationContext()));
        etsedagetfive.setAdapter(getSedation(getApplicationContext()));
        sedagenspindosefive.setAdapter(getUnits(getApplicationContext()));


        //sedaone
        etsedagentone.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {

                    ssedagentone = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdose.setVisibility(View.VISIBLE);
                } else {

                    ssedagentone = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdose.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sedagenspindoseone.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitsedadoseone = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //sedatwo
        etsedagettwo.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {

                    ssedagettwo = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosetwo.setVisibility(View.VISIBLE);
                } else {

                    ssedagettwo = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosetwo.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sedagenspindosetwo.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitsedadosetwo = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        //sedathree
        wtsedagentthree.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {

                    ssedagentthree = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosehree.setVisibility(View.VISIBLE);
                } else {

                    ssedagentthree = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosehree.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sedagenspindosethree.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitsedadosethree = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //sedafour
        etsedagentfour.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {

                    ssedagentfour = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosefour.setVisibility(View.VISIBLE);
                } else {

                    ssedagentfour = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosefour.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sedagenspindosefour.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitsedadosefour = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        //sedafive
        etsedagetfive.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {

                if (adapterView.getItemAtPosition(pos).toString().contains("Others")) {

                    ssedagetfive = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosefive.setVisibility(View.VISIBLE);
                } else {

                    ssedagetfive = adapterView.getItemAtPosition(pos).toString();
                    etsedagenothersdosefive.setVisibility(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        sedagenspindosefive.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View arg1,
                                       int pos, long arg3) {
                unitsedadosefive = adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        fabscreenfivenext = (FloatingActionButton) findViewById(R.id.fag_agent_next);
        fabscreenfivenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("AGENTS", "Agetone " + sagentone + "\n" +
                        "Agettwo " + sagettwo + "\n" +
                        "Agetthree " + sagentthree + "\n" +
                        "Agetfour " + sagentfour + "\n" +
                        "Agetfive " + sagetfive + "\n" +
                        "Agetdoseone " + ssedagentone + "\n" +
                        "Agetdosetwo " + ssedagettwo + "\n" +
                        "Agetdosethree " + ssedagentthree + "\n" +
                        "Agetdosefour " + ssedagentfour + "\n" +
                        "Agetdosefive " + ssedagetfive + "\n" +
                        "doseunittone " + unitdoseone + "\n" +
                        "doseunitttwo " + unitdosetwo + "\n" +
                        "doseunitthree " + unitdosethree + "\n" +
                        "doseunitfour " + unitdosefour + "\n" +
                        "doseunitfive " + unitdosefive + "\n");


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
        etabglac = findViewById(R.id.et_abg);
        etsao2 = findViewById(R.id.et_sao2);
        etspo2bg = findViewById(R.id.et_spso2);


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
        etabglac.setTypeface(Helper.getSharedHelper().getNormalFont());
        etsao2.setTypeface(Helper.getSharedHelper().getNormalFont());
        etspo2bg.setTypeface(Helper.getSharedHelper().getNormalFont());

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

        rbpronposing = (RadioGroup) findViewById(R.id.rbprone);
        rbpronposingimp = (RadioGroup) findViewById(R.id.rbproneimp);
        rbniticacid = (RadioGroup) findViewById(R.id.rbniticacid);
        rbniticacidimp = (RadioGroup) findViewById(R.id.rbniticacidimp);
        rbplasmaphersis = (RadioGroup) findViewById(R.id.rbplasmaphersis);
        rbplasmaphersisimp = (RadioGroup) findViewById(R.id.rbplasmaphersisimp);
        rbtherapuethyp = (RadioGroup) findViewById(R.id.rbtheraphy);
        rbtherapuethypimp = (RadioGroup) findViewById(R.id.rbtheraphyimp);
        rbothers = (EditText) findViewById(R.id.rbothers);
        rbothersimp = (RadioGroup) findViewById(R.id.rbothersimp);

        rbpronposing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    spronposing = rb.getText().toString();
                }

            }
        });
        rbpronposingimp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    spronposingimp = rb.getText().toString();
                }

            }
        });
        rbniticacid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    sniticacid = rb.getText().toString();
                }

            }
        });
        rbniticacidimp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    sniticacidimp = rb.getText().toString();
                }

            }
        });
        rbplasmaphersis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    splasmaphersis = rb.getText().toString();
                }

            }
        });
        rbplasmaphersisimp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    splasmaphersisimp = rb.getText().toString();
                }

            }
        });
        rbtherapuethyp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    stherapuethyp = rb.getText().toString();
                }

            }
        });
        rbtherapuethypimp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    stherapuethypimp = rb.getText().toString();
                }

            }
        });


        rbothersimp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    sothersimp = rb.getText().toString();
                }

            }
        });

        fabscreensixnext = (FloatingActionButton) findViewById(R.id.fag_investigation_next);
        fabscreensixnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eturea.getText().toString().isEmpty()) {
                    eturea.setError("Enter Details");
                    commonToast("Please enter required details");
                } else if (etcr.getText().toString().isEmpty()) {
                    etcr.setError("Enter Details");
                    commonToast("Please enter required details");
                } else if (etUo.getText().toString().isEmpty()) {
                    etUo.setError("Enter Details");
                    commonToast("Please enter required details");
                } else if (dialysis.isEmpty()) {
                    commonToast("Please Select Dialysis");
                } else if (etpco2.getText().toString().isEmpty()) {
                    etpco2.setError("Enter Details");
                    commonToast("Please enter required details");
                } else if (Float.valueOf(etpco2.getText().toString()) > 600 || Float.valueOf(etpco2.getText().toString()) < 0) {
                    etpco2.setError("Enter Details");
                    commonToast("PCO2 should be 0-600");
                } else if (ethco3.getText().toString().isEmpty()) {
                    ethco3.setError("Enter Details");
                    commonToast("Please enter required details");
                } else {
                    String resend;

                    if (formdata != null) {
                        resend = formdata.getRefral_id();
                    } else {
                        resend = "0";
                    }


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
                            sdays,
                            etspo2.getText().toString(),
                            etpo2.getText().toString(),
                            sfio2,
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
                            sagentone,
                            unitdoseone,
                            sagettwo,
                            unitdosetwo,
                            sagentthree,
                            unitdosethree,
                            sagentfour,
                            unitdosefour,
                            sagetfive,
                            unitdosefive,
                            ssedagentone,
                            unitsedadoseone,
                            ssedagettwo,
                            unitsedadosetwo,
                            ssedagentthree,
                            unitsedadosethree,
                            ssedagentfour,
                            unitsedadosefour,
                            ssedagetfive,
                            unitsedadosefive,
                            "",
                            "",
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
                            userPreferences.getSession(),
                            metage.getText().toString().trim(),
                            resend,
                            etmurrayscore.getText().toString(),
                            spronposing,
                            spronposingimp,
                            sniticacid,
                            sniticacidimp,
                            splasmaphersis,
                            splasmaphersisimp,
                            stherapuethyp,
                            stherapuethypimp,
                            rbothers.getText().toString(),
                            sothersimp,
                            etabglac.getText().toString(),
                            etsao2.getText().toString(),
                            etspo2bg.getText().toString(),
                            etdoseone.getText().toString(),
                            etdosetwo.getText().toString(),
                            etdosethree.getText().toString(),
                            etdosefour.getText().toString(),
                            etdosefive.getText().toString(),
                            etseddoseone.getText().toString(),
                            etseddosetwo.getText().toString(),
                            etseddosethree.getText().toString(),
                            etseddosefour.getText().toString(),
                            etseddosefive.getText().toString(),
                            etothersdose.getText().toString(),
                            etothersdosetwo.getText().toString(),
                            etothersdosehree.getText().toString(),
                            etothersdosefour.getText().toString(),
                            etothersdosefive.getText().toString(),
                            etsedagenothersdose.getText().toString(),
                            etsedagenothersdosetwo.getText().toString(),
                            etsedagenothersdosehree.getText().toString(),
                            etsedagenothersdosefour.getText().toString(),
                            etsedagenothersdosefive.getText().toString(),
                            rbothers.getText().toString()

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

    private void Submitalert(final PatientFormRequest patientFormRequest) {

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
                        intent.putExtra("BitmapImageOne", bitmapone);
                        intent.putExtra("BitmapImageTwo", bitmaptwo);
                        intent.putExtra("BitmapImageThree", bitmapthree);
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
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                commonLoaderstop();
                CommonResponse commonResponse = response.body();
                if (commonResponse != null) {
                    if (response != null && response.body().getResult().equalsIgnoreCase("FAILED") && response.message().contains(Constants.AUTH_FAIL)) {
                        LogoutSession();
                    } else if (commonResponse.getResult().equalsIgnoreCase("Success")) {
                        commonToast("Referral form submitted Successfully");
                        finish();
                    } else {
                        commonToast("Error , plz check data entered.");

                    }

                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonLoaderstop();
            }
        });

    }


    //resend filling
    private void ResendDataFill() {
        //Docotor form
        //Radiobutton
        RadioButton male, female;
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        if (formdata.getGender().equalsIgnoreCase("Male")) {
            male.setChecked(true);
            mdatagender = "MALE";
        } else {
            female.setChecked(true);
            mdatagender = "FEMALE";
        }


        mtvadmdiagnosis.setText(formdata.getAdmissionDiagnosis());
        metfilenumber.setText(formdata.getFileNo());
        metpacitentname.setText(formdata.getPatientName());
        etcivilid.setText(formdata.getCivilId());
        metage.setText(formdata.getAge());
        metunit.setText(formdata.getUnit());
        metward.setText(formdata.getWard());
        metbed.setText(formdata.getBed());
        metextrainfo.setText(formdata.getHistory());
        mspinnerhospital.setSelection(getspinnerIndexvalue(mspinnerhospital, formdata.getReferHospital()));
        mspinnerSpeciallity.setSelection(getspinnerIndexvalue(mspinnerSpeciallity, formdata.getSpeciallist()));

        //Need to set spinner value;


        //Patient
        switch (formdata.getPreMorbFunctionalStatus()) {
            case "bed bound":
                radiofunctionstatusGroup.check(R.id.bed_bound);
                functionstatus = "bed bound";
                break;
            case "chair bound":
                radiofunctionstatusGroup.check(R.id.chair_bound);
                functionstatus = "chair bound";

                break;
            case "mobile":
                radiofunctionstatusGroup.check(R.id.mobile);
                functionstatus = "mobile";
                break;
        }


        switch (formdata.getPreMorbFunctionalConsciousStatus()) {
            case "Alert and Oriented":
                radioconsciousstatusgroup.check(R.id.alertandorientd);
                conscioussstatus = "Alert and Oriented";
                break;
            case "Demented":
                radioconsciousstatusgroup.check(R.id.demented);
                conscioussstatus = "Demented";

                break;
            case "Coma/vegetative Status":
                radioconsciousstatusgroup.check(R.id.coma);
                conscioussstatus = "Coma/vegetative Status";
                break;
        }


        metgcsE.setSelection(getspinnerIndexvalue(metgcsE, formdata.getE()));
        metgcsv.setSelection(getspinnerIndexvalue(metgcsv, formdata.getV()));
        metgcsM.setSelection(getspinnerIndexvalue(metgcsM, formdata.getM()));
        mtvtotoalgcs.setText(formdata.getTotalscore());


        //Ventilation

        etspo2.setText(formdata.getSpO2());
        etpo2.setText(formdata.getPO2());
        ettv.setText(formdata.getTv());
        etpipvalues.setText(formdata.getPip());

        spinnerpafio2.setText(formdata.getPao2fio2ratio());
        spinnerpeep.setText(formdata.getPeep());
        spinnerrr.setText(formdata.getRr());
        spinnerlungcompliance.setText(formdata.getLungCompliance());
        spinnercxr.setSelection(getspinnerIndexvalue(spinnercxr, formdata.getCxrquadrants()));
        etfio2.setSelection(getspinnerIndexvalue(etfio2, formdata.getFiO2()));
        etventdays.setSelection(getspinnerIndexvalue(etventdays, formdata.getDurOfConventianalMechanicalVentination()));

        //cardiovascular

        etcvp.setText(formdata.getCardiacindex());
        etco.setText(formdata.getLeftventricularejectionfraction());
        spinnerhr.setSelection(getspinnerIndexvalue(spinnerhr, formdata.getHr()));

        String currentString = formdata.getBp();
        String[] separated = currentString.split("-");
        if (separated.length > 1 && separated[0] != null && separated[1] != null) {
            etbps.setText(separated[0]);
            etbpp.setText(separated[1]);
        }
        spinnertemp.setText(formdata.getTemp());
        spinnercardiac.setSelection(getspinnerIndexvalue(spinnercardiac, formdata.getCardiacindex()));
        spinnerlvef.setText(formdata.getLeftventricularejectionfraction());

        //agents
        etagentone.setSelection(getspinnerIndexvalue(etagentone, formdata.getInotropesagent1()));
        etagettwo.setSelection(getspinnerIndexvalue(etagettwo, formdata.getInotropesagent2()));
        wtagentthree.setSelection(getspinnerIndexvalue(wtagentthree, formdata.getInotropesagent3()));
        etagentfour.setSelection(getspinnerIndexvalue(etagentfour, formdata.getInotropesagent4()));
        etagetfive.setSelection(getspinnerIndexvalue(etagetfive, formdata.getInotropesagent5()));

        etdoseone.setText(formdata.getInotropesDose1Value());
        etdosetwo.setText(formdata.getInotropesDose2Value());
        etdosethree.setText(formdata.getInotropesDose3Value());
        etdosefour.setText(formdata.getInotropesDose4Value());
        etdosefive.setText(formdata.getInotropesDose5Value());

        etothersdose.setText(formdata.getInotropesAgent1Others());
        etothersdosetwo.setText(formdata.getInotropesAgent2Others());
        etothersdosehree.setText(formdata.getInotropesAgent3Others());
        etothersdosefour.setText(formdata.getInotropesAgent4Others());
        etothersdosefive.setText(formdata.getInotropesAgent5Others());

        spindoseone.setSelection(getspinnerIndexvalue(spindoseone, formdata.getInotropesdose1()));
        spindosetwo.setSelection(getspinnerIndexvalue(spindosetwo, formdata.getInotropesdose2()));
        spindosethree.setSelection(getspinnerIndexvalue(spindosethree, formdata.getInotropesdose3()));
        spindosefour.setSelection(getspinnerIndexvalue(spindosefour, formdata.getInotropesdose4()));
        spindosefive.setSelection(getspinnerIndexvalue(spindosefive, formdata.getInotropesdose5()));


        //seducation spinner
        etsedagentone.setSelection(getspinnerIndexvalue(etsedagentone, formdata.getSedationagent1()));
        etsedagettwo.setSelection(getspinnerIndexvalue(etsedagettwo, formdata.getSedationagent2()));
        wtsedagentthree.setSelection(getspinnerIndexvalue(wtsedagentthree, formdata.getSedationagent3()));
        etsedagentfour.setSelection(getspinnerIndexvalue(etsedagentfour, formdata.getSedationagent4()));
        etsedagetfive.setSelection(getspinnerIndexvalue(etsedagetfive, formdata.getSedationagent5()));

        etseddoseone.setText(formdata.getSedationDose1Value());
        etseddosetwo.setText(formdata.getSedationDose2Value());
        etseddosethree.setText(formdata.getSedationDose3Value());
        etseddosefour.setText(formdata.getSedationDose4Value());
        etseddosefive.setText(formdata.getSedationDose5Value());

        etsedagenothersdose.setText(formdata.getSedationAgent1Others());
        etsedagenothersdosetwo.setText(formdata.getSedationAgent2Others());
        etsedagenothersdosehree.setText(formdata.getSedationAgent3Others());
        etsedagenothersdosefour.setText(formdata.getSedationAgent4Others());
        etsedagenothersdosefive.setText(formdata.getSedationAgent5Others());


        sedagenspindoseone.setSelection(getspinnerIndexvalue(sedagenspindoseone, formdata.getSedationdose1()));
        sedagenspindosetwo.setSelection(getspinnerIndexvalue(sedagenspindosetwo, formdata.getSedationdose2()));
        sedagenspindosethree.setSelection(getspinnerIndexvalue(sedagenspindosethree, formdata.getSedationdose3()));
        sedagenspindosefour.setSelection(getspinnerIndexvalue(sedagenspindosefour, formdata.getSedationdose4()));
        sedagenspindosefive.setSelection(getspinnerIndexvalue(sedagenspindosefive, formdata.getSedationdose5()));

        //investigation

        eturea.setText(formdata.getUrea());
        etcr.setText(formdata.getCr());
        etlactate.setText(formdata.getLactate());
        etUo.setText(formdata.getUo());
        etph.setText(formdata.getBloodgasPH());
        etinpo2.setText(formdata.getBloodgasPO2());
        etpco2.setText(formdata.getBloodgasPCO2());
        ethco3.setText(formdata.getBloodgasHCO3());
        etbf.setText(formdata.getBloodgasBE());

        if (formdata.getDialysis().equalsIgnoreCase("YES")) {
            radiodialysisGroup.check(R.id.yes);
            dialysis = "yes";
        } else {
            radiodialysisGroup.check(R.id.no);
            dialysis = "no";
        }
        if (formdata.getPronePositioning().equalsIgnoreCase("YES")) {
            rbpronposing.check(R.id.rbproneyes);
            spronposing = "yes";
        } else {
            rbpronposing.check(R.id.rbproneno);
            spronposing = "no";
        }

        if (formdata.getPronePositioningImprov().equalsIgnoreCase("YES")) {
            rbpronposingimp.check(R.id.rbproneimpyes);
            spronposingimp = "yes";
        } else {
            rbpronposingimp.check(R.id.rbproneimpno);
            spronposingimp = "no";
        }
        if (formdata.getNitricAcid().equalsIgnoreCase("YES")) {
            rbniticacid.check(R.id.rbnitrciyes);
            sniticacid = "yes";
        } else {
            rbniticacid.check(R.id.rbnitrcino);
            sniticacid = "no";
        }
        if (formdata.getNitricAcidImprov().equalsIgnoreCase("YES")) {
            rbniticacidimp.check(R.id.rbnitrciimpyes);
            sniticacidimp = "yes";
        } else {
            rbniticacidimp.check(R.id.rbnitrciimpno);
            sniticacidimp = "no";

        }

        if (formdata.getPlasmaphersis().equalsIgnoreCase("YES")) {
            rbplasmaphersis.check(R.id.rbplasmyes);
            splasmaphersis = "yes";
        } else {
            rbplasmaphersis.check(R.id.rbplasmno);
            splasmaphersis = "no";
        }
        if (formdata.getPlasmaphersisImprov().equalsIgnoreCase("YES")) {
            rbplasmaphersisimp.check(R.id.rbplasmimpyes);
            splasmaphersisimp = "yes";
        } else {
            rbplasmaphersisimp.check(R.id.rbplasmimpno);
            splasmaphersisimp = "no";
        }


        if (formdata.getTherapueticHypothermia().equalsIgnoreCase("YES")) {
            rbtherapuethyp.check(R.id.rbtheryes);
            stherapuethyp = "yes";
        } else {
            rbtherapuethyp.check(R.id.rbtherno);
            stherapuethyp = "no";
        }

        if (formdata.getTherapueticHypothermiaImprov().equalsIgnoreCase("YES")) {
            rbtherapuethypimp.check(R.id.rbtherimpyes);
            stherapuethypimp = "yes";
        } else {
            rbtherapuethypimp.check(R.id.rbtherimpno);
            stherapuethypimp = "no";
        }
        if (formdata.getOthersImprov().equalsIgnoreCase("YES")) {
            rbothersimp.check(R.id.rbotherteyes);
            sothersimp = "yes";
        } else {
            rbothersimp.check(R.id.rbotherteno);
            sothersimp = "no";
        }

        rbothers.setText(formdata.getImprovothers());
    }


    //camrea

    private void camera() {
        //camera file
        btn_one = (Button) findViewById(R.id.btn_one);
        imageview_one = (ImageView) findViewById(R.id.iv_one);

        imageviewclose_one = (ImageView) findViewById(R.id.crose_one);
        imageviewclose_two = (ImageView) findViewById(R.id.crose_two);
        imageviewclose_three = (ImageView) findViewById(R.id.crose_three);


        btn_two = (Button) findViewById(R.id.btn_two);
        imageview_two = (ImageView) findViewById(R.id.iv_two);

        btn_three = (Button) findViewById(R.id.btn_three);
        imageview_three = (ImageView) findViewById(R.id.iv_three);


        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager packageManager = getApplicationContext().getPackageManager();
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            callPermission();
                        }
                    } else
                        showPictureDialog("one");
                }
            }
        });

        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager packageManager = getApplicationContext().getPackageManager();
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            callPermission();
                        }
                    } else
                        showPictureDialog("two");
                }
            }
        });

        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager packageManager = getApplicationContext().getPackageManager();
                if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            callPermission();
                        }
                    } else
                        showPictureDialog("three");
                }
            }
        });


        ///clear
        imageviewclose_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog("one");
            }
        });
        imageviewclose_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog("two");

            }
        });
        imageviewclose_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog("three");
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void callPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }


        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 3) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                   /* PackageManager packageManager = getApplicationContext().getPackageManager();
                    if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                    {*/
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }

                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        callPermission();
                    }
                    // }

                    //Displaying a toast
                    //
                }  //Displaying another toast if permission is not granted //Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
                /*else {
                  //  Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
                }*/
            }

        }
    }

    private void showPictureDialog(String attachhment_section) {
        attachment = attachhment_section;
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            attachment = "null";
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    switch (attachment) {
                        case "one":
                            filetype = "1";
                            imageview_one.setImageBitmap(bitmap);
                            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream1);
                            bitmapone = stream1.toByteArray();
                            imageviewclose_one.setVisibility(View.VISIBLE);
                            break;
                        case "two":
                            filetype = "2";
                            imageview_two.setImageBitmap(bitmap);
                            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream2);
                            bitmaptwo = stream2.toByteArray();
                            imageviewclose_two.setVisibility(View.VISIBLE);
                            break;
                        case "three":
                            filetype = "3";
                            imageview_three.setImageBitmap(bitmap);
                            ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream3);
                            bitmapthree = stream3.toByteArray();
                            imageviewclose_three.setVisibility(View.VISIBLE);
                            break;
                    }

                    saveImage(bitmap, filetype);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(PatientForm.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                switch (attachment) {
                    case "one":
                        filetype = "1";
                        imageview_one.setImageBitmap(thumbnail);
                        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                        assert thumbnail != null;
                        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, stream1);
                        bitmapone = stream1.toByteArray();
                        imageviewclose_one.setVisibility(View.VISIBLE);
                        break;
                    case "two":
                        filetype = "2";
                        imageview_two.setImageBitmap(thumbnail);
                        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                        assert thumbnail != null;
                        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, stream2);
                        bitmaptwo = stream2.toByteArray();
                        imageviewclose_two.setVisibility(View.VISIBLE);
                        break;
                    case "three":
                        filetype = "3";
                        imageview_three.setImageBitmap(thumbnail);
                        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                        assert thumbnail != null;
                        thumbnail.compress(Bitmap.CompressFormat.JPEG, 50, stream3);
                        bitmapthree = stream3.toByteArray();
                        imageviewclose_three.setVisibility(View.VISIBLE);
                        break;

                }
                saveImage(thumbnail, filetype);
            } catch (Exception e) {
                Log.d("UPLOAD", e.getLocalizedMessage() + "");
                Toast.makeText(PatientForm.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void UploadFile(File finalFile, String filetypenumber) {
        commonLoaderstart();
        //    RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpeg"), finalFile);
        RequestBody temp = RequestBody.create(MediaType.parse("image/*"), finalFile);
        RequestBody guid = RequestBody.create(MediaType.parse("text/plain"), userPreferences.getSession());
        RequestBody filetype = RequestBody.create(MediaType.parse("text/plain"), filetypenumber);
        RequestBody docid = RequestBody.create(MediaType.parse("text/plain"), userPreferences.getUserId());

        ApiInterface apiService = ApiClient.getClientrefpatient().create(ApiInterface.class);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", finalFile.getName(), temp);

        Call<CommonResponse> call = apiService.postFile(body, guid, filetype, docid);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

                CommonResponse commonResponse = response.body();
                if (commonResponse != null) {
                    if (response != null && response.body().getResult().equalsIgnoreCase("FAILED")) {
                        commonToast("Invalid File");
                    } else if (commonResponse.getResult().equalsIgnoreCase("Success")) {
                        commonToast("Attachment Uploaded sucessfully");
                    } else {
                        commonToast("Error , plz check data entered.");
                    }
                }
                commonLoaderstop();
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                commonToast("Fail Upload");
                commonLoaderstop();
            }
        });
    }


    public void saveImage(Bitmap myBitmap, String filetype) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpeg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.length());
            // commonToast(f.length()+"");
            f.getAbsolutePath();

            UploadFile(new File(f.getAbsolutePath()), filetype);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    public void murryscore() {
        if (spinnerpafio2.getText().toString().isEmpty() || spinnerpeep.getText().toString().isEmpty() || spinnerlungcompliance.getText().toString().isEmpty() || scrx.isEmpty()) {
            Log.d("score caluation", "pending");
        } else {
            double score = scorepao2fio2 + scorecxr + scorepeep + scorelung;
            score = score * 0.250;
            etmurrayscore.setText(score + "");
        }


        //commonToast(score+"");

    }

}

