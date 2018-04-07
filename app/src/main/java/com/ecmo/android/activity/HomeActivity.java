package com.ecmo.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;
import com.ecmo.android.utils.Helper;


public class HomeActivity extends BaseActivity implements View.OnClickListener {
    TextView mtxtgallery, mtxthelp, mtxtreferpatient, mtxtreferstatus;
    LinearLayout mllgallery, mllhelp, mllreferpatient, mllreferstatus;
    Button mcall,logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initviews();
        initfonts();
    }

    private void initviews() {
        mtxtgallery = (TextView) findViewById(R.id.tv_gallery);
        mtxthelp = (TextView) findViewById(R.id.tv_help);
        mtxtreferpatient = (TextView) findViewById(R.id.tv_referpatient);
        mtxtreferstatus = (TextView) findViewById(R.id.tv_patientstatus);
        mcall = (Button) findViewById(R.id.btn_phonecall);
        logout_btn=findViewById(R.id.logout_btn);
        mllgallery = (LinearLayout) findViewById(R.id.rv_gallery);
        mllhelp = (LinearLayout) findViewById(R.id.rv_help);
        mllreferpatient = (LinearLayout) findViewById(R.id.rv_referpatient);
        mllreferstatus = (LinearLayout) findViewById(R.id.rv_patientstatus);

        mllgallery.setOnClickListener(this);
        mllhelp.setOnClickListener(this);
        mllreferpatient.setOnClickListener(this);
        mllreferstatus.setOnClickListener(this);
        mcall.setOnClickListener(this);
        logout_btn.setOnClickListener(this);

    }

    private void initfonts() {
        mtxtgallery.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtxthelp.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtxtreferpatient.setTypeface(Helper.getSharedHelper().getSemiBoldFont());
        mtxtreferstatus.setTypeface(Helper.getSharedHelper().getSemiBoldFont());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rv_gallery:
//                startActivity(new Intent(getApplicationContext(), GalleryActivity.class));
                startActivity(new Intent(getApplicationContext(), ProfileInfoActivity.class));
                break;
            case R.id.rv_help:
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                break;
            case R.id.rv_referpatient:
                commonToast("Refer Patient");
                break;
            case R.id.rv_patientstatus:
                commonToast("Patient Status");
                break;
            case R.id.btn_phonecall:
                commonToast("Call");
                break;

            case R.id.logout_btn:
                showConfirmDialog(HomeActivity.this);
                break;
        }

    }
}
