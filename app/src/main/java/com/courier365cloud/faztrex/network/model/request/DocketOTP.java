package com.courier365cloud.faztrex.network.model.request;

import com.google.gson.annotations.SerializedName;

public class DocketOTP {
    @SerializedName("id")
    private String id = "";

    @SerializedName("otp")
    private String otp = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
