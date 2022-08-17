package com.courier365cloud.faztrex.network.model.response.docket.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocketTracking {

    @SerializedName("DocketId")
    @Expose
    private String docketId;

    @SerializedName("DocketNumber")
    @Expose
    private String docketNumber;

    @SerializedName("BookingDate")
    @Expose
    private String bookingDate;

    @SerializedName("CustomerType")
    @Expose
    private String customerType;

    @SerializedName("DispatchMode")
    @Expose
    private String dispatchMode;

    @SerializedName("PaymentType")
    @Expose
    private String paymentType;

    @SerializedName("Origin")
    @Expose
    private String origin;

    @SerializedName("Destination")
    @Expose
    private String destination;

    @SerializedName("EstimatedDeliveryDate")
    @Expose
    private String estimatedDeliveryDate;

    @SerializedName("Consignor")
    @Expose
    private String consignor;

    @SerializedName("Consignee")
    @Expose
    private String consignee;

    @SerializedName("DeliveryType")
    @Expose
    private String deliveryType;

    @SerializedName("NoOfPackages")
    @Expose
    private String noOfPackages;

    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;

    @SerializedName("EwayBillNo")
    @Expose
    private String ewayBillNo;

    @SerializedName("ActualWeight")
    @Expose
    private String actualWeight;

    @SerializedName("ChargeWeight")
    @Expose
    private String chargeWeight;

    @SerializedName("NetAmount")
    @Expose
    private String netAmount;

    @SerializedName("ActualDeliveryDate")
    @Expose
    private String actualDeliveryDate;

    @SerializedName("DeliveryStatus")
    @Expose
    private String deliveryStatus;

    @SerializedName("trackingInfo")
    @Expose
    private ArrayList<TrackingInfo> trackingInfo;

    @SerializedName("deliveryInfo")
    @Expose
    private ArrayList<DeliveryInfo> deliveryInfo;

    public String getDocketId() {
        return docketId;
    }

    public void setDocketId(String docketId) {
        this.docketId = docketId;
    }

    public String getDocketNumber() {
        return docketNumber;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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

    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getConsignor() {
        return consignor;
    }

    public void setConsignor(String consignor) {
        this.consignor = consignor;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getNoOfPackages() {
        return noOfPackages;
    }

    public void setNoOfPackages(String noOfPackages) {
        this.noOfPackages = noOfPackages;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(String ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
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

    public String getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(String netAmount) {
        this.netAmount = netAmount;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public ArrayList<TrackingInfo> getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(ArrayList<TrackingInfo> trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    public ArrayList<DeliveryInfo> getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(ArrayList<DeliveryInfo> deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }
}
