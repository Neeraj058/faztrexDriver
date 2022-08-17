package com.courier365cloud.faztrex.network.model.response.docket.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryDetail {

    @SerializedName("DRSTime")
    @Expose
    private String drsTime;

    @SerializedName("DRSNumber")
    @Expose
    private String drsNumber;

    @SerializedName("DeliveryStatus")
    @Expose
    private String deliveryStatus;

    @SerializedName("PODUrl")
    @Expose
    private String podUrl;

    @SerializedName("UndeliverReason")
    @Expose
    private String undeliveredReason;

    public String getDrsTime() {
        return drsTime;
    }

    public void setDrsTime(String drsTime) {
        this.drsTime = drsTime;
    }

    public String getDrsNumber() {
        return drsNumber;
    }

    public void setDrsNumber(String drsNumber) {
        this.drsNumber = drsNumber;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getPodUrl() {
        return podUrl;
    }

    public void setPodUrl(String podUrl) {
        this.podUrl = podUrl;
    }

    public String getUndeliveredReason() {
        return undeliveredReason;
    }

    public void setUndeliveredReason(String undeliveredReason) {
        this.undeliveredReason = undeliveredReason;
    }
}
