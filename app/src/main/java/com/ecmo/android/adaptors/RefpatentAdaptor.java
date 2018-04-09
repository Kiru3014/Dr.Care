package com.ecmo.android.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecmo.android.R;
import com.ecmo.android.model.response.PatReferalListitem;

import java.util.List;


/**
 * Created by Pankaj on 06-10-2016.
 */
public class RefpatentAdaptor extends RecyclerView.Adapter<RefpatentAdaptor.ItemRowHolder> {

    private List<PatReferalListitem> patReferalListdata;
    private Context mContext;
    private OnViewItemClickListener viewlistner;


    public RefpatentAdaptor(Context mContext, List<PatReferalListitem> patReferalListdata) {
        this.patReferalListdata = patReferalListdata;
        this.mContext = mContext;

    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.referal_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {
        final PatReferalListitem patdata=patReferalListdata.get(i);

        itemRowHolder.patStatus.setText(patdata.getStatus());
        itemRowHolder.patname.setText(patdata.getPat_name());
        itemRowHolder.hospitalname.setText(patdata.getPat_Hospital());
        itemRowHolder.refdate.setText(patdata.getRefdate());
        itemRowHolder.patage.setText(patdata.getPat_age());
        itemRowHolder.patdiag.setText(patdata.getDiagnosis());

        itemRowHolder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewlistner.onviewClick( patdata.getReferalformid());
            }
        });

        itemRowHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewlistner.onviewClick( patdata.getReferalformid());
            }
        });

    }

    public void setviewclickListner(OnViewItemClickListener listner) {
        viewlistner=listner;
    }


    public interface OnViewItemClickListener {
        void onviewClick(String formid);

    }


    @Override
    public int getItemCount() {
        return (null != patReferalListdata ? patReferalListdata.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView patStatus;
        protected TextView patname;
        protected TextView hospitalname;
        protected TextView refdate;
        protected TextView patage;
        protected TextView patdiag;
        protected RelativeLayout mainlayout;
        protected Button view;

        public ItemRowHolder(View view) {
            super(view);

            this.mainlayout=view.findViewById(R.id.mainlayout);
            this.patStatus = view.findViewById(R.id.ref_status);
            this.patname = view.findViewById(R.id.patname);

            this.hospitalname = view.findViewById(R.id.hospital_name);
            this.refdate = view.findViewById(R.id.ref_date);

            this.patage = view.findViewById(R.id.patage);
            this.patdiag = view.findViewById(R.id.diagnosis);
            this.view=view.findViewById(R.id.viewdetails);
        }

    }


}
