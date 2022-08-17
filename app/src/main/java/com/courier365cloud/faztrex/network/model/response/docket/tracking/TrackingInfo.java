package com.courier365cloud.faztrex.network.model.response.docket.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrackingInfo {

    @SerializedName("TrackingDate")
    @Expose
    private String trackingDate;

    @SerializedName("trackingDetails")
    @Expose
    private ArrayList<TrackingDetail> trackingDetails;

    public String getTrackingDate() {
        return trackingDate;
    }

    public void setTrackingDate(String trackingDate) {
        this.trackingDate = trackingDate;
    }

    public ArrayList<TrackingDetail> getTrackingDetails() {
        return trackingDetails;
    }

    public void setTrackingDetails(ArrayList<TrackingDetail> trackingDetails) {
        this.trackingDetails = trackingDetails;
    }
}
