package com.courier365cloud.faztrex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardCount {

    @SerializedName(value = "DRSCount", alternate = "dRSCount")
    @Expose
    private String drsCounts;

    @SerializedName(value = "HyperLocalRequestCount", alternate = "hyperLocalRequestCount")
    @Expose
    private String hyperLocalCounts;

    @SerializedName(value = "DocketCount", alternate = "docketCount")
    @Expose
    private String docketCount;

    public String getDrsCounts() {
        return drsCounts;
    }

    public void setDrsCounts(String drsCounts) {
        this.drsCounts = drsCounts;
    }

    public String getHyperLocalCounts() {
        return hyperLocalCounts;
    }

    public void setHyperLocalCounts(String hyperLocalCounts) {
        this.hyperLocalCounts = hyperLocalCounts;
    }

    public String getDocketCount() {
        return docketCount;
    }

    public void setDocketCount(String docketCount) {
        this.docketCount = docketCount;
    }
}
