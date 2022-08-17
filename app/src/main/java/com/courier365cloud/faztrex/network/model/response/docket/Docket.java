package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Docket {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("DocketNo")
    @Expose
    private String docketNo;

    @SerializedName("BookingDate")
    @Expose
    private String bookingDate;

    @SerializedName("Origin")
    @Expose
    private String origin;

    @SerializedName("Destination")
    @Expose
    private String destination;

    @SerializedName("ConsignorName")
    @Expose
    private String consignorName;

    @SerializedName("ConsigneeName")
    @Expose
    private String consigneeName;

    @SerializedName("NoOfPackages")
    @Expose
    private String noOfPackages;

    @SerializedName("ActualWeight")
    @Expose
    private String actualWeight;

    @SerializedName("ChargeWeight")
    @Expose
    private String chargeWeight;

    @SerializedName("CustomerType")
    @Expose
    private String customerType;

    @SerializedName("DispatchMode")
    @Expose
    private String dispatchMode;

    @SerializedName("PaymentType")
    @Expose
    private String paymentType;

    @SerializedName("NetAmount")
    @Expose
    private String netAmount;

    @SerializedName("EwayBillNo")
    @Expose
    private String ewayBillNo;

    @SerializedName("InvoiceValue")
    @Expose
    private String invoiceValue;

    @SerializedName("DeliveryStatus")
    @Expose
    private String deliveryStatus;

    @SerializedName("ModifiedOn")
    @Expose
    private String modifiedOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocketNo() {
        return docketNo;
    }

    public void setDocketNo(String docketNo) {
        this.docketNo = docketNo;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDispatchMode() {
        return dispatchMode;
    }

    public void setDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(String ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
    }

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}