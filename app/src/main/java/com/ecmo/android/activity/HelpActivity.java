package com.ecmo.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.adaptors.ExpandableListAdapter;
import com.ecmo.android.utils.Helper;
import com.scanning.drcare.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends BaseActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private int lastExpandedPosition = -1;
    TextView mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        mtitle = (TextView) findViewById(R.id.tv_title);
        mtitle.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

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
        listDataHeader.add("ADAN ADULT ICU ECMO REFERAL FORM");
        listDataHeader.add("INDICATIONS FOR VV-ECMO");
        listDataHeader.add("ABSOLUTE CONTRAINDICATIONS TO VENO-VENOUS ECMO");
        listDataHeader.add("MURRAY SCORE");

        // Adding child data
        List<String> ADULT = new ArrayList<String>();
        ADULT.add("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");


        List<String> INDICATIONS = new ArrayList<String>();
        INDICATIONS.add("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");

        List<String> ABSOLUTE = new ArrayList<String>();
        ABSOLUTE.add("1) Severe (medically unsupportable) heart failure/cardiogenic shock. ");
        ABSOLUTE.add("2) Severe chronic pulmonary hypertension and right ventricular failure (mean pulmonary artery pressure approaching systemic blood pressure) ");
        ABSOLUTE.add("3) Cardiac arrest(ongoing) ");
        ABSOLUTE.add("4) Advanced/terminal malignancy. ");
        ABSOLUTE.add("5) Graft versus host diseases. ");
        ABSOLUTE.add("6) Cachexia due to an underlying progressive chronic disease.");


        List<String> MURRAY = new ArrayList<String>();
        MURRAY.add("");


        listDataChild.put(listDataHeader.get(0), ADULT); // Header, Child data
        listDataChild.put(listDataHeader.get(1), INDICATIONS);
        listDataChild.put(listDataHeader.get(2), ABSOLUTE);
        listDataChild.put(listDataHeader.get(3), MURRAY);
    }
}