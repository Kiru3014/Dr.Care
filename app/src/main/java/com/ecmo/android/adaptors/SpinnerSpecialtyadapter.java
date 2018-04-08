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
import com.ecmo.android.model.SpecialityList;
import com.ecmo.android.utils.Helper;

import java.util.ArrayList;

public class SpinnerSpecialtyadapter extends BaseAdapter {
    private LayoutInflater inflter;
    private Context context;
    private ArrayList<SpecialityList> specialityLists;

    public SpinnerSpecialtyadapter(Context context, ArrayList<SpecialityList> hospital) {
        this.context = context;
        this.specialityLists = hospital;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return specialityLists.size();
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
        names.setText(specialityLists.get(i).getSpecialtyName());
        names.setTypeface(Helper.getSharedHelper().getNormalFont());
        return view;
    }
}
