package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.SerializedName;

public class DocketDimension {
    @SerializedName("RowNo")
    private String rowNo;
    @SerializedName("Id")
    private String id;
    @SerializedName("DocketId")
    private String docketId;
    @SerializedName("CustomerType")
    private String customerType;
    @SerializedName("Boxes")
    private String boxes;
    @SerializedName("Length")
    private String length;
    @SerializedName("Width")
    private String width;
    @SerializedName("Height")
    private String height;
    @SerializedName("ActualWeight")
    private String actualWeight;
    @SerializedName("VolumetricWeight")
    private String volumetricWeight;
    @SerializedName("TotalActualWeight")
    private String totalActualWeight;
    @SerializedName("BarcodeNumber")
    private String barcodeNumber;

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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

    public String getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(String totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }
}
