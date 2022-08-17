package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dimension {

    @SerializedName("Id")
    @Expose
    public String id;

    @SerializedName("DocketId")
    @Expose
    public String docketId;

    @SerializedName("Boxes")
    @Expose
    public String boxes;

    @SerializedName("Length")
    @Expose
    public String length;

    @SerializedName("Width")
    @Expose
    public String width;

    @SerializedName("Height")
    @Expose
    public String height;

    @SerializedName("ActualWeight")
    @Expose
    public String actualWeight;

    @SerializedName("VolumetricWeight")
    @Expose
    public String volumetricWeight;

    @SerializedName("IsActive")
    @Expose
    public String isActive;

    @SerializedName("IsDelete")
    @Expose
    public String isDelete;

    @SerializedName("TotalActualWeight")
    @Expose
    public String totalActualWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocketId() {
        return docketId;
    }

    public void setDocketId(String docketId) {
        this.docketId = docketId;
    }

    public String getBoxes() {
        return boxes;
    }

    public void setBoxes(String boxes) {
        this.boxes = boxes;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getVolumetricWeight() {
        return volumetricWeight;
    }

    public void setVolumetricWeight(String volumetricWeight) {
        this.volumetricWeight = volumetricWeight;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(String totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }
}
