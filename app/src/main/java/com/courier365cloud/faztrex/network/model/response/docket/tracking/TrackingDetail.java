package com.courier365cloud.faztrex.network.model.response.docket.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackingDetail {

    @SerializedName("TrackingTime")
    @Expose
    private String trackingTime;

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("Location")
    @Expose
    private String location;

    public String getTrackingTime() {
        return trackingTime;
    }

    public void setTrackingTime(String trackingTime) {
        this.trackingTime = trackingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
