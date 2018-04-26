package com.ecmo.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.ecmo.android.BaseActivity;
import com.ecmo.android.R;

public class HelpActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecmo);

    }

    public void generalone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/ELSO%20Guidelines%20General%20All%20ECLS%20Version%201_4.pdf"));
        startActivity(browserIntent);
    }

    public void generaltwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/ELSO%20GUIDELINES%20FOR%20ECMO%20TRANSPORT_May2015.pdf"));
        startActivity(browserIntent);
    }

    public void generalthree(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/elso_Ultrasoundguideance_ecmogeneral_guidelines_May2015.pdf"));
        startActivity(browserIntent);
    }

    public void respiratoryone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/6f129b235acusersshyerdocumentselsoguidelinesforpediatricrespiratoryfailure1.3.pdf"));
        startActivity(browserIntent);
    }

    public void respiratorytwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/ELSO%20Guidelines%20For%20Adult%20Respiratory%20Failure%201_4.pdf"));
        startActivity(browserIntent);
    }

    public void respiratorythree(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/ELSO_ExtubationGuidelines_May2015.pdf"));
        startActivity(browserIntent);
    }

    public void respiratoryfour(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/ELSO_Recirculation_guideline_May2015.pdf"));
        startActivity(browserIntent);
    }

    public void respiratoryfive(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/ELSO%20guidelines%20paeds%20resp_May2015.pdf"));
        startActivity(browserIntent);
    }

    public void respiratorysix(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/elso_Ultrasoundguidance_vvecmo_guidelines_MAY2015.pdf"));
        startActivity(browserIntent);
    }

    public void circulatoryone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/ELSO_Reformatted_2018.02.23.pdf"));
        startActivity(browserIntent);
    }

    public void circulatorytwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/e76ef78eabcusersshyerdocumentselsoguidelinesforadultcardiacfailure1.3.pdf"));
        startActivity(browserIntent);
    }

    public void circulatorythree(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/6713186745cusersshyerdocumentselsoguidelinesforecprcases1.3.pdf"));
        startActivity(browserIntent);
    }

    public void circulatoryfour(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/elso_Ultrasoundguidance_vaecmo_guidelines_May2015.pdf"));
        startActivity(browserIntent);
    }


    public void specialone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/elsoanticoagulationguideline8-2014-table-contents.pdf"));
        startActivity(browserIntent);
    }

    public void specialtwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/2440a82ecdcusersshyerdocumentselsorecommendationsforneonatalpediatricecmopatientfollowup.pdf"));
        startActivity(browserIntent);
    }

    public void specialthree(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/eb07e0ae08cusersshyerdocumentselsoh1n1specificguidelines.pdf"));
        startActivity(browserIntent);
    }


    public void centerone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/faf3f6a3c7cusersshyerdocumentselsoguidelinesecmocentersv1.8.pdf"));
        startActivity(browserIntent);
    }


    public void centertwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/97000963d6cusersshyerdocumentselsoguidelinesfortrainingandcontinuingeducationofecmospecialists.pdf"));
        startActivity(browserIntent);
    }


    public void spanishone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/Guideline/ELSO_GuiasExtubacion_Mayo2015.pdf"));
        startActivity(browserIntent);
    }

    public void spanishtwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/Guideline/ELSO%20Guias%20para%20la%20Insuficiencia%20Respiratoria%20Aguda%20Neonatal%201%203.pdf"));
        startActivity(browserIntent);
    }

    public void spanishthree(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/Guideline/ELSO%20Guias%20Para%20Insuficiencia%20Cardiaca%20Pediatrica%201.3.pdf"));
        startActivity(browserIntent);
    }


    public void spanishfour(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/Guideline/ELSO%20Guias%20para%20Insuficiencia%20Cardiaca%20del%20Adulto%201.3.pdf"));
        startActivity(browserIntent);
    }


    public void spanishfive(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/Guideline/ELSO%20Guias%20para%20Centros%20de%20ECMO%20Version%201.8.pdf"));
        startActivity(browserIntent);
    }


    public void spanishsix(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/Guideline/ELSO%20Gui%C2%B4as%20para%20Entrenamiento%20y%20Educacion%20Version%201.5.pdf"));
        startActivity(browserIntent);
    }


    public void japaneseshone(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/IGD/Archive/FileManager/3f4de7e28ecusersshyerdocumentselsoguidelinesgeneraljapanesetranslation.pdf"));
        startActivity(browserIntent);
    }

    public void japaneseshtwo(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.elso.org/Portals/0/Files/ELSOVersion1.3JapaneseTranslation.pdf"));
        startActivity(browserIntent);
    }
}




