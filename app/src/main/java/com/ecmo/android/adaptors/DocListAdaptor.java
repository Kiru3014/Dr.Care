package com.ecmo.android.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ecmo.android.R;
import com.ecmo.android.model.response.DocListItem;

import java.util.List;

public class DocListAdaptor extends RecyclerView.Adapter<DocListAdaptor.ItemRowHolder> {

    private List<DocListItem> patReferalListdata;
    private Context mContext;
    public OndocItemClickListener mOnItemClickListener;


    public DocListAdaptor(Context mContext, List<DocListItem> patReferalListdata) {
        this.patReferalListdata = patReferalListdata;
        this.mContext = mContext;

        }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doc_reg_item, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
        }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {
        final DocListItem docdata=patReferalListdata.get(i);

        itemRowHolder.docname.setText(docdata.getName());
        itemRowHolder.dochospital.setText(docdata.getHospital());
        itemRowHolder.docSpeclity.setText(docdata.getSpecialist());
        itemRowHolder.docmob.setText(docdata.getPhone());
        itemRowHolder.docemail.setText(docdata.getEmail());

        if(docdata.getStatus().equalsIgnoreCase("1")){
            itemRowHolder.approve.setVisibility(View.GONE);
            itemRowHolder.reject.setVisibility(View.GONE);
            itemRowHolder.approveddoc.setVisibility(View.VISIBLE);
        }
        else{
            itemRowHolder.approve.setVisibility(View.VISIBLE);
            itemRowHolder.reject.setVisibility(View.VISIBLE);
            itemRowHolder.approveddoc.setVisibility(View.GONE);
        }


        itemRowHolder.approve.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
            mOnItemClickListener.onApproveClick(docdata.getDocid());

        }
        });


        itemRowHolder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onRejectClick(docdata.getDocid());
            }
        });
    }

    public void setactionListner(OndocItemClickListener actionClickListener) {
        this.mOnItemClickListener=actionClickListener;
    }

    public interface OndocItemClickListener {
        void onApproveClick(String docid);
        void onRejectClick(String docid);
    }

    @Override
    public int getItemCount() {
        return (null != patReferalListdata ? patReferalListdata.size() : 0);
        }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

    protected TextView docStatus;
    protected TextView docname;
    protected TextView dochospital;
    protected TextView approveddoc;
    protected TextView docSpeclity;
    protected TextView docmob;
    protected TextView docemail;
    protected Button approve, reject;

    public ItemRowHolder(View view) {
        super(view);
//        this.patStatus = view.findViewById(R.id.ref_status);
        this.docname = view.findViewById(R.id.doc_name);
        this.dochospital = view.findViewById(R.id.doc_hospital);
        this.docSpeclity = view.findViewById(R.id.doc_speciality);
        this.docmob = view.findViewById(R.id.doc_mob);
        this.docemail = view.findViewById(R.id.doc_email);
        this.approve=view.findViewById(R.id.approve);
        this.reject=view.findViewById(R.id.reject);
        this.approveddoc=view.findViewById(R.id.approveddoc);
    }

}


}
