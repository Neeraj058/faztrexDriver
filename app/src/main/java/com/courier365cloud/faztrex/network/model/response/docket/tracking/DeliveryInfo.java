package com.courier365cloud.faztrex.network.model.response.docket.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DeliveryInfo {

    @SerializedName("DRSDate")
    @Expose
    private String drsDate;

    @SerializedName("drsDetails")
    @Expose
    private ArrayList<DeliveryDetail> deliveryDetails;

    public String getDrsDate() {
        return drsDate;
    }

    public void setDrsDate(String drsDate) {
        this.drsDate = drsDate;
    }

    public ArrayList<DeliveryDetail> getDeliveryDetails() {
        return deliveryDetails;
    }

    public void setDeliveryDetails(ArrayList<DeliveryDetail> deliveryDetails) {
        this.deliveryDetails = deliveryDetails;
    }
}
