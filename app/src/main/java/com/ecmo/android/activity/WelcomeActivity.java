package com.ecmo.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecmo.android.R;
import com.ecmo.android.adaptors.ExpandableListAdapter;
import com.ecmo.android.utils.Helper;
import com.ecmo.android.utils.UserPreferences;
import com.pushwoosh.fragment.PushEventListener;
import com.pushwoosh.fragment.PushFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WelcomeActivity extends FragmentActivity implements PushEventListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private int lastExpandedPosition = -1;
    TextView mtitle;
    LinearLayout bunlayout;
    Button btnlogin,btnregister;
    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        userPreferences = new UserPreferences(this);
        PushFragment.init(WelcomeActivity.this);

        mtitle = (TextView) findViewById(R.id.tv_title);
        mtitle.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        bunlayout=(LinearLayout)findViewById(R.id.btn_layout);
        bunlayout.setVisibility(View.VISIBLE);

        btnlogin=(Button)findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        btnregister=(Button)findViewById(R.id.btn_register);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {


            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                return false;
            }
        });


        //Notification

    }


    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("INDICATIONS FOR VV-ECMO");
        listDataHeader.add("VV-ECMO Should also be considered for");
        listDataHeader.add("ABSOLUTE CONTRAINDICATIONS TO VENO-VENOUS ECMO                          ");
        listDataHeader.add("MURRAY SCORE");

        // Adding child data
        List<String> ADULT = new ArrayList<String>();
        ADULT.add("Any reversible, life-threatening form of respiratory failure where the \trisk \tof mortality is 80% * or greater and where cardiac function is \tadequate.This includes the following(see table below):");
        ADULT.add("1. PaO2/FiO2 <100 despite optimal ventilator support.");
        ADULT.add("2. Murray score 3-4** despite optimal care for 6 hours or more.");
        ADULT.add("3. PH<7.2 due to CO2 retention on mechanical ventilation despite  optimal ventilation with high Pplat(>30 cm H2O).");
        ADULT.add("4. Severe air leak syndromes (e.g. pheumothorax secondary to trauma,   fistula).");
        ADULT.add("5. Need for intubation in a patient on lung transplant list.");


        List<String> INDICATIONS = new ArrayList<String>();
        INDICATIONS.add("1. PaO2/FiO2 <150 despite optimalventilator support.");
        INDICATIONS.add("2. Murray score 3-4** despite optimal care for 6 hours or more.");
        INDICATIONS.add("3. Immediate cardiac or respiratory collapse (PE, blocked airway,unresponsive to optimal care).");
        INDICATIONS.add("4. Duration of conventional mechanical ventilation>7 days,with single organ dysfunction.");

        List<String> ABSOLUTE = new ArrayList<String>();
        ABSOLUTE.add("1. Severe (medically unsupportable) heart failure/cardiogenic shock. ");
        ABSOLUTE.add("2. Severe chronic pulmonary hypertension and right ventricular failure (mean pulmonary artery pressure approaching systemic blood pressure) ");
        ABSOLUTE.add("3. Cardiac arrest(ongoing) ");
        ABSOLUTE.add("4. Advanced/terminal malignancy. ");
        ABSOLUTE.add("5. Graft versus host diseases. ");
        ABSOLUTE.add("6. Cachexia due to an underlying progressive chronic disease.");


        List<String> MURRAY = new ArrayList<String>();
        MURRAY.add("Each parameter scroes between 0 to 4 and an average of the 4 parameters scroring is calculated to get the final Murray score.A score of \u2265 3 is an indication of VV-ECMO.");


        listDataChild.put(listDataHeader.get(0), ADULT); // Header, Child data
        listDataChild.put(listDataHeader.get(1), INDICATIONS);
        listDataChild.put(listDataHeader.get(2), ABSOLUTE);
        listDataChild.put(listDataHeader.get(3), MURRAY);
    }


    @Override
    public void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        //Check if we've got new intent with a push notification
        PushFragment.onNewIntent(this, intent);
    }

    @Override
    public void doOnRegistered(String registrationId)
    {
        if(registrationId!=null && !registrationId.isEmpty())
            userPreferences.setPushwooshToken(registrationId);
    }


    @Override
    public void doOnUnregisteredError(String s) {

    }

    @Override
    public void doOnRegisteredError(String s) {

    }

    @Override
    public void doOnMessageReceive(String s) {

    }

    @Override
    public void doOnUnregistered(String s) {

    }
}