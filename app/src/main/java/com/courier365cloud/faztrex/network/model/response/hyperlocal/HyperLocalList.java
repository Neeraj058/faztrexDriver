package com.courier365cloud.faztrex.network.model.response.hyperlocal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HyperLocalList implements Parcelable {

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private String Id;

    @SerializedName(value = "No", alternate = "no")
    @Expose
    private String No;

    @SerializedName(value = "BookingDateTime", alternate = "bookingDateTime")
    @Expose
    private String BookingDateTime;

    @SerializedName(value = "DeliveryDateTime", alternate = "deliveryDateTime")
    @Expose
    private String DeliveryDateTime;

    @SerializedName(value = "PickupPersonName", alternate = "pickupPersonName")
    @Expose
    private String PickupPersonName;

    @SerializedName(value = "PickupAddress", alternate = "pickupAddress")
    @Expose
    private String PickupAddress;

    @SerializedName(value = "PickupLatitude", alternate = "pickupLatitude")
    @Expose
    private String pickupLatitude;

    @SerializedName(value = "PickupLongitude", alternate = "pickupLongitude")
    @Expose
    private String pickupLongitude;

    @SerializedName(value = "PickupContactNo", alternate = "pickupContactNo")
    @Expose
    private String PickupContactNo;

    @SerializedName(value = "DeliveryPersonName", alternate = "deliveryPersonName")
    @Expose
    private String DeliveryPersonName;

    @SerializedName(value = "DeliveryContactNo", alternate = "deliveryContactNo")
    @Expose
    private String DeliveryContactNo;

    @SerializedName(value = "DeliveryAddress", alternate = "deliveryAddress")
    @Expose
    private String DeliveryAddress;

    @SerializedName(value = "DeliveryLatitude", alternate = "deliveryLatitude")
    @Expose
    private String deliveryLatitude;

    @SerializedName(value = "DeliveryLongitude", alternate = "deliveryLongitude")
    @Expose
    private String deliveryLongitude;

    @SerializedName(value = "Weight", alternate = "weight")
    @Expose
    private String Weight;

    @SerializedName(value = "Status", alternate = "status")
    @Expose
    private String Status;

    @SerializedName(value = "StatusName", alternate = "statusName")
    @Expose
    private String StatusName;

    @SerializedName(value = "PaymentType", alternate = "paymentType")
    @Expose
    private String PaymentType;

    @SerializedName(value = "TotalAmount", alternate = "totalAmount")
    @Expose
    private String TotalAmount;

    @SerializedName(value = "Product", alternate = "product")
    @Expose
    private String Product;

    @SerializedName(value = "AssignId", alternate = "assignId")
    @Expose
    private String AssignId;

    protected HyperLocalList(Parcel in) {
        Id = in.readString();
        No = in.readString();
        BookingDateTime = in.readString();
        DeliveryDateTime = in.readString();
        PickupPersonName = in.readString();
        PickupAddress = in.readString();
        pickupLatitude = in.readString();
        pickupLongitude = in.readString();
        PickupContactNo = in.readString();
        DeliveryPersonName = in.readString();
        DeliveryContactNo = in.readString();
        DeliveryAddress = in.readString();
        deliveryLatitude = in.readString();
        deliveryLongitude = in.readString();
        Weight = in.readString();
        Status = in.readString();
        StatusName = in.readString();
        PaymentType = in.readString();
        TotalAmount = in.readString();
        Product = in.readString();
        AssignId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(No);
        dest.writeString(BookingDateTime);
        dest.writeString(DeliveryDateTime);
        dest.writeString(PickupPersonName);
        dest.writeString(PickupAddress);
        dest.writeString(pickupLatitude);
        dest.writeString(pickupLongitude);
        dest.writeString(PickupContactNo);
        dest.writeString(DeliveryPersonName);
        dest.writeString(DeliveryContactNo);
        dest.writeString(DeliveryAddress);
        dest.writeString(deliveryLatitude);
        dest.writeString(deliveryLongitude);
        dest.writeString(Weight);
        dest.writeString(Status);
        dest.writeString(StatusName);
        dest.writeString(PaymentType);
        dest.writeString(TotalAmount);
        dest.writeString(Product);
        dest.writeString(AssignId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HyperLocalList> CREATOR = new Creator<HyperLocalList>() {
        @Override
        public HyperLocalList createFromParcel(Parcel in) {
            return new HyperLocalList(in);
        }

        @Override
        public HyperLocalList[] newArray(int size) {
            return new HyperLocalList[size];
        }
    };

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getBookingDateTime() {
        return BookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        BookingDateTime = bookingDateTime;
    }

    public String getDeliveryDateTime() {
        return DeliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        DeliveryDateTime = deliveryDateTime;
    }

    public String getPickupPersonName() {
        return PickupPersonName;
    }

    public void setPickupPersonName(String pickupPersonName) {
        PickupPersonName = pickupPersonName;
    }

    public String getPickupAddress() {
        return PickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        PickupAddress = pickupAddress;
    }

    public String getPickupContactNo() {
        return PickupContactNo;
    }

    public void setPickupContactNo(String pickupContactNo) {
        PickupContactNo = pickupContactNo;
    }

    public String getDeliveryPersonName() {
        return DeliveryPersonName;
    }

    public void setDeliveryPersonName(String deliveryPersonName) {
        DeliveryPersonName = deliveryPersonName;
    }

    public String getDeliveryContactNo() {
        return DeliveryContactNo;
    }

    public void setDeliveryContactNo(String deliveryContactNo) {
        DeliveryContactNo = deliveryContactNo;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        DeliveryAddress = deliveryAddress;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getAssignId() {
        return AssignId;
    }

    public void setAssignId(String assignId) {
        AssignId = assignId;
    }

    public String getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(String pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public String getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(String pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    public String getDeliveryLatitude() {
        return deliveryLatitude;
    }

    public void setDeliveryLatitude(String deliveryLatitude) {
        this.deliveryLatitude = deliveryLatitude;
    }

    public String getDeliveryLongitude() {
        return deliveryLongitude;
    }

    public void setDeliveryLongitude(String deliveryLongitude) {
        this.deliveryLongitude = deliveryLongitude;
    }

}
