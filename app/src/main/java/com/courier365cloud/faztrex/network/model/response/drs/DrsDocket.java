package com.courier365cloud.faztrex.network.model.response.drs;

import android.os.Parcel;
import android.os.Parcelable;

import com.courier365cloud.faztrex.network.model.response.docket.DocketDimension;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrsDocket implements Parcelable {

    public static final Creator<DrsDocket> CREATOR = new Creator<DrsDocket>() {
        @Override
        public DrsDocket createFromParcel(Parcel source) {
            return new DrsDocket(source);
        }

        @Override
        public DrsDocket[] newArray(int size) {
            return new DrsDocket[size];
        }
    };
    @SerializedName("RowNo")
    @Expose
    private String rowNo;
    @SerializedName("DRSDetailId")
    @Expose
    private String drsDetailId;
    @SerializedName("DocketId")
    @Expose
    private String docketId;
    @SerializedName("DocketNo")
    @Expose
    private String docketNo;
    @SerializedName("BookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("DestinationState")
    @Expose
    private String destinationState;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("ConsignorName")
    @Expose
    private String consignorName;
    @SerializedName("ConsigneeName")
    @Expose
    private String consigneeName;
    @SerializedName("ConsigneeMobileNo")
    @Expose
    private String consigneeMobileNo;
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
    @SerializedName("TotalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("CollectedAmount")
    @Expose
    private String collectedAmount;
    @SerializedName("PaymentTypeId")
    @Expose
    private String paymentTypeId;
    @SerializedName("IsDelete")
    @Expose
    private String isDelete;
    @SerializedName("DeliveryStatus")
    @Expose
    private String deliveryStatus;
    @SerializedName("DeliveryStatusName")
    @Expose
    private String deliveryStatusName;
    @SerializedName("ReasonId")
    @Expose
    private String reasonId;
    @SerializedName("ReasonName")
    @Expose
    private String reasonName;
    @SerializedName("InvoiceValue")
    @Expose
    private String invoiceValue;
    @SerializedName("EwayBillNo")
    @Expose
    private String ewayBillNo;
    @SerializedName("BalanceAmount")
    @Expose
    private String balanceAmount;
    @SerializedName("AmountStatus")
    @Expose
    private String amountStatus;
    @SerializedName("PODFilePath")
    @Expose
    private String podFilePath;
    @SerializedName("TDSAmount")
    @Expose
    private String tdsAmount;
    @SerializedName("BillId")
    @Expose
    private String billId;
    @SerializedName("DRSId")
    @Expose
    private String DRSId;
    @SerializedName("SubOrderNo")
    @Expose
    private String SubOrderNo;
    @SerializedName("ProductName")
    @Expose
    private String ProductName;
    @SerializedName("ConsigneeAddress")
    @Expose
    private String ConsigneeAddress;
    @SerializedName("ConsignorAddress")
    @Expose
    private String ConsignorAddress;
    @SerializedName("ConsignorMobileNo")
    @Expose
    private String ConsignorMobileNo;

    @SerializedName("DocketDimension")
    @Expose
    private DocketDimension docketDimension;

    public DrsDocket() {
    }

    protected DrsDocket(Parcel in) {
        this.rowNo = in.readString();
        this.drsDetailId = in.readString();
        this.docketId = in.readString();
        this.docketNo = in.readString();
        this.bookingDate = in.readString();
        this.source = in.readString();
        this.destinationState = in.readString();
        this.destination = in.readString();
        this.consignorName = in.readString();
        this.consigneeName = in.readString();
        this.consigneeMobileNo = in.readString();
        this.noOfPackages = in.readString();
        this.actualWeight = in.readString();
        this.chargeWeight = in.readString();
        this.customerType = in.readString();
        this.dispatchMode = in.readString();
        this.paymentType = in.readString();
        this.totalAmount = in.readString();
        this.collectedAmount = in.readString();
        this.paymentTypeId = in.readString();
        this.isDelete = in.readString();
        this.deliveryStatus = in.readString();
        this.deliveryStatusName = in.readString();
        this.reasonId = in.readString();
        this.reasonName = in.readString();
        this.invoiceValue = in.readString();
        this.ewayBillNo = in.readString();
        this.balanceAmount = in.readString();
        this.amountStatus = in.readString();
        this.podFilePath = in.readString();
        this.tdsAmount = in.readString();
        this.billId = in.readString();
        this.DRSId = in.readString();
        this.SubOrderNo = in.readString();
        this.ProductName = in.readString();
        this.ConsigneeAddress = in.readString();
        this.ConsignorAddress = in.readString();
    }

    public String getConsignorMobileNo() {
        return ConsignorMobileNo;
    }

    public void setConsignorMobileNo(String consignorMobileNo) {
        ConsignorMobileNo = consignorMobileNo;
    }

    public String getDRSId() {
        return DRSId;
    }

    public void setDRSId(String DRSId) {
        this.DRSId = DRSId;
    }

    public String getSubOrderNo() {
        return SubOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        SubOrderNo = subOrderNo;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getConsigneeAddress() {
        return ConsigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        ConsigneeAddress = consigneeAddress;
    }

    public String getConsignorAddress() {
        return ConsignorAddress;
    }

    public void setConsignorAddress(String consignorAddress) {
        ConsignorAddress = consignorAddress;
    }

    public DocketDimension getDocketDimension() {
        return docketDimension;
    }

    public void setDocketDimension(DocketDimension docketDimension) {
        this.docketDimension = docketDimension;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public String getDrsDetailId() {
        return drsDetailId;
    }

    public void setDrsDetailId(String drsDetailId) {
        this.drsDetailId = drsDetailId;
    }

    public String getDocketId() {
        return docketId;
    }

    public void setDocketId(String docketId) {
        this.docketId = docketId;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
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

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(String collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryStatusName() {
        return deliveryStatusName;
    }

    public void setDeliveryStatusName(String deliveryStatusName) {
        this.deliveryStatusName = deliveryStatusName;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public String getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(String invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public String getEwayBillNo() {
        return ewayBillNo;
    }

    public void setEwayBillNo(String ewayBillNo) {
        this.ewayBillNo = ewayBillNo;
    }

    public String getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(String amountStatus) {
        this.amountStatus = amountStatus;
    }

    public String getPodFilePath() {
        return podFilePath;
    }

    public void setPodFilePath(String podFilePath) {
        this.podFilePath = podFilePath;
    }

    public String getTdsAmount() {
        return tdsAmount;
    }

    public void setTdsAmount(String tdsAmount) {
        this.tdsAmount = tdsAmount;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getConsigneeMobileNo() {
        return consigneeMobileNo;
    }

    public void setConsigneeMobileNo(String consigneeMobileNo) {
        this.consigneeMobileNo = consigneeMobileNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rowNo);
        dest.writeString(this.drsDetailId);
        dest.writeString(this.docketId);
        dest.writeString(this.docketNo);
        dest.writeString(this.bookingDate);
        dest.writeString(this.source);
        dest.writeString(this.destinationState);
        dest.writeString(this.destination);
        dest.writeString(this.consignorName);
        dest.writeString(this.consigneeName);
        dest.writeString(this.consigneeMobileNo);
        dest.writeString(this.noOfPackages);
        dest.writeString(this.actualWeight);
        dest.writeString(this.chargeWeight);
        dest.writeString(this.customerType);
        dest.writeString(this.dispatchMode);
        dest.writeString(this.paymentType);
        dest.writeString(this.totalAmount);
        dest.writeString(this.collectedAmount);
        dest.writeString(this.paymentTypeId);
        dest.writeString(this.isDelete);
        dest.writeString(this.deliveryStatus);
        dest.writeString(this.deliveryStatusName);
        dest.writeString(this.reasonId);
        dest.writeString(this.reasonName);
        dest.writeString(this.invoiceValue);
        dest.writeString(this.ewayBillNo);
        dest.writeString(this.balanceAmount);
        dest.writeString(this.amountStatus);
        dest.writeString(this.podFilePath);
        dest.writeString(this.tdsAmount);
        dest.writeString(this.billId);
        dest.writeString(this.DRSId);
        dest.writeString(this.SubOrderNo);
        dest.writeString(this.ProductName);
        dest.writeString(this.ConsigneeAddress);
        dest.writeString(this.ConsignorAddress);
    }

    public void readFromParcel(Parcel source) {
        this.rowNo = source.readString();
        this.drsDetailId = source.readString();
        this.docketId = source.readString();
        this.docketNo = source.readString();
        this.bookingDate = source.readString();
        this.source = source.readString();
        this.destinationState = source.readString();
        this.destination = source.readString();
        this.consignorName = source.readString();
        this.consigneeName = source.readString();
        this.consigneeMobileNo = source.readString();
        this.noOfPackages = source.readString();
        this.actualWeight = source.readString();
        this.chargeWeight = source.readString();
        this.customerType = source.readString();
        this.dispatchMode = source.readString();
        this.paymentType = source.readString();
        this.totalAmount = source.readString();
        this.collectedAmount = source.readString();
        this.paymentTypeId = source.readString();
        this.isDelete = source.readString();
        this.deliveryStatus = source.readString();
        this.deliveryStatusName = source.readString();
        this.reasonId = source.readString();
        this.reasonName = source.readString();
        this.invoiceValue = source.readString();
        this.ewayBillNo = source.readString();
        this.balanceAmount = source.readString();
        this.amountStatus = source.readString();
        this.podFilePath = source.readString();
        this.tdsAmount = source.readString();
        this.billId = source.readString();
        this.DRSId = source.readString();
        this.SubOrderNo = source.readString();
        this.ProductName = source.readString();
        this.ConsigneeAddress = source.readString();
        this.ConsignorAddress = source.readString();
    }
}