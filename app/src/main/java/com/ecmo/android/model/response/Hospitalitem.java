package com.ecmo.android.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fairoze khazi on 08/04/2018.
 */

public class Hospitalitem {

    @SerializedName("Text")
    @Expose
    private String Text;

    @SerializedName("Value")
    @Expose
    private String Value;

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
