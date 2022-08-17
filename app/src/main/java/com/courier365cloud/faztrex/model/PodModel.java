package com.courier365cloud.faztrex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PodModel {

    @SerializedName("DRSId")
    @Expose
    public Integer DRSId;

    @SerializedName("DRSDetailId")
    @Expose
    public Integer DRSDetailId;

    @SerializedName("DocketId")
    @Expose
    public Integer DocketId;

    @SerializedName("DeliveryStatus")
    @Expose
    public Integer DeliveryStatus;

    @SerializedName("ReasonId")
    @Expose
    public Integer ReasonId;

    @SerializedName("UserId")
    @Expose
    public Integer UserId;

    @SerializedName("CollectedAmount")
    @Expose
    public Double CollectedAmount;

    @SerializedName("TDSAmount")
    @Expose
    public Double TDSAmount;

    @SerializedName("PaymentTypeId")
    @Expose
    public Integer PaymentTypeId;

    @SerializedName("ReferenceNo")
    @Expose
    public String ReferenceNo;

    @SerializedName("DrsClosedById")
    @Expose
    public String DrsClosedById;


    public Integer getDRSId() {
        return DRSId;
    }

    public void setDRSId(Integer DRSId) {
        this.DRSId = DRSId;
    }

    public Integer getDRSDetailId() {
        return DRSDetailId;
    }

    public void setDRSDetailId(Integer DRSDetailId) {
        this.DRSDetailId = DRSDetailId;
    }

    public Integer getDocketId() {
        return DocketId;
    }

    public void setDocketId(Integer docketId) {
        DocketId = docketId;
    }

    public Integer getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        DeliveryStatus = deliveryStatus;
    }

    public Integer getReasonId() {
        return ReasonId;
    }

    public void setReasonId(Integer reasonId) {
        ReasonId = reasonId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Double getCollectedAmount() {
        return CollectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
        CollectedAmount = collectedAmount;
    }

    public Double getTDSAmount() {
        return TDSAmount;
    }

    public void setTDSAmount(Double TDSAmount) {
        this.TDSAmount = TDSAmount;
    }

    public Integer getPaymentTypeId() {
        return PaymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        PaymentTypeId = paymentTypeId;
    }

    public String getReferenceNo() {
        return ReferenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        ReferenceNo = referenceNo;
    }

    public String getDrsClosedById() {
        return DrsClosedById;
    }

    public void setDrsClosedById(String drsClosedById) {
        DrsClosedById = drsClosedById;
    }
}
