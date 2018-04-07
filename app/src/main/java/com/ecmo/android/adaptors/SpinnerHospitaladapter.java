package com.ecmo.android.adaptors;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ecmo.android.R;
import com.ecmo.android.model.HospitalList;
import com.ecmo.android.utils.Helper;

import java.util.ArrayList;

public class SpinnerHospitaladapter extends BaseAdapter {
    private LayoutInflater inflter;
    private Context context;
    private ArrayList<HospitalList> hospital;

    public SpinnerHospitaladapter(Context context, ArrayList<HospitalList> hospital) {
        this.context = context;
        this.hospital = hospital;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return hospital.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.textView);
        names.setText(hospital.get(i).getHospitalName());
        names.setTypeface(Helper.getSharedHelper().getNormalFont());
        return view;
    }
}
