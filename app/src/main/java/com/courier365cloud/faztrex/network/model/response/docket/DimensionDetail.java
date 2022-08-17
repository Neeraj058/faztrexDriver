package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DimensionDetail {

    @SerializedName("NoOfPackages")
    @Expose
    public String noOfPackages;

    @SerializedName("ActualWeight")
    @Expose
    public String actualWeight;

    @SerializedName("ChargeWeight")
    @Expose
    public String chargeWeight;

    @SerializedName("VolumetricWeight")
    @Expose
    public String volumetricWeight;

    @SerializedName("ChargeWeightPercentage")
    @Expose
    public String chargeWeightPercentage;

    @SerializedName("listDocketDimension")
    @Expose
    public ArrayList<Dimension> dimensionList;

    public String getNoOfPackages() {
        return noOfPackages;
    }

    public void setNoOfPackages(String noOfPackages) {
        this.noOfPackages = noOfPackages;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getChargeWeight() {
        return chargeWeight;
    }

    public void setChargeWeight(String chargeWeight) {
        this.chargeWeight = chargeWeight;
    }

    public String getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(String volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    public String getChargeWeightPercentage() {
        return chargeWeightPercentage;
    }

    public void setChargeWeightPercentage(String chargeWeightPercentage) {
        this.chargeWeightPercentage = chargeWeightPercentage;
    }

    public ArrayList<Dimension> getDimensionList() {
        return dimensionList;
    }

    public void setDimensionList(ArrayList<Dimension> dimensionList) {
        this.dimensionList = dimensionList;
    }
}
