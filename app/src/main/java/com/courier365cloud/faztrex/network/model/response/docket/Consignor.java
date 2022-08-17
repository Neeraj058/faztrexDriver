package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Consignor {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("CustomerCode")
    @Expose
    private String customerCode;

    @SerializedName("CustomerName")
    @Expose
    private String customerName;

    @SerializedName("Add1")
    @Expose
    private String add1;

    @SerializedName("Add2")
    @Expose
    private String add2;

    @SerializedName("Add3")
    @Expose
    private String add3;

    @SerializedName("Postcode")
    @Expose
    private String postcode;

    @SerializedName("GSTNo")
    @Expose
    private String gstNo;

    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;

    @SerializedName("StateId")
    @Expose
    private String stateId;

    @SerializedName("DestinationId")
    @Expose
    private String destinationId;

    @SerializedName("VerticleId")
    @Expose
    private String verticleId;

    @SerializedName("ProductName")
    @Expose
    private String productName;

    @SerializedName("PackingTypeId")
    @Expose
    private String packingTypeId;

    @SerializedName("RiskType")
    @Expose
    private String riskType;

    @SerializedName("BillSubmissionType")
    @Expose
    private String billSubmissionType;

    @SerializedName("IsSEZ")
    @Expose
    private String isSez;

    @SerializedName("VerticleName")
    @Expose
    private String verticleName;

    @SerializedName("PackingTypeName")
    @Expose
    private String packingTypeName;

    public Consignor(String id, String customerName) {
        this.id = id;
        this.customerName = customerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAdd1() {
        return add1;
    }

    public void setAdd1(String add1) {
        this.add1 = add1;
    }

    public String getAdd2() {
        return add2;
    }

    public void setAdd2(String add2) {
        this.add2 = add2;
    }

    public String getAdd3() {
        return add3;
    }

    public void setAdd3(String add3) {
        this.add3 = add3;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getVerticleId() {
        return verticleId;
    }

    public void setVerticleId(String verticleId) {
        this.verticleId = verticleId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackingTypeId() {
        return packingTypeId;
    }

    public void setPackingTypeId(String packingTypeId) {
        this.packingTypeId = packingTypeId;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getBillSubmissionType() {
        return billSubmissionType;
    }

    public void setBillSubmissionType(String billSubmissionType) {
        this.billSubmissionType = billSubmissionType;
    }

    public String getIsSez() {
        return isSez;
    }

    public void setIsSez(String isSez) {
        this.isSez = isSez;
    }

    public String getVerticleName() {
        return verticleName;
    }

    public void setVerticleName(String verticleName) {
        this.verticleName = verticleName;
    }

    public String getPackingTypeName() {
        return packingTypeName;
    }

    public void setPackingTypeName(String packingTypeName) {
        this.packingTypeName = packingTypeName;
    }
}
