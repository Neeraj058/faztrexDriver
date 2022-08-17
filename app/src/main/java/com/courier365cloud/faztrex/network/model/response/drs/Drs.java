package com.courier365cloud.faztrex.network.model.response.drs;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Drs implements Parcelable {

    @SerializedName("RowNo")
    @Expose
    private String rowNo;

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("DRSNo")
    @Expose
    private String drsNo;

    @SerializedName("DRSDate")
    @Expose
    private String drsDate;

    @SerializedName("Source")
    @Expose
    private String source;

    @SerializedName("Destination")
    @Expose
    private String destination;

    @SerializedName("DriverName")
    @Expose
    private String driverName;

    @SerializedName("DriverContactNo")
    @Expose
    private String driverContactNo;

    @SerializedName("VehicleNo")
    @Expose
    private String vehicleNo;

    @SerializedName("TotalPackages")
    @Expose
    private String totalPackages;

    @SerializedName("TotalDockets")
    @Expose
    private String totalDockets;

    @SerializedName("TotalActualWeight")
    @Expose
    private String totalActualWeight;

    @SerializedName("DRSStatus")
    @Expose
    private String drsStatus;

    @SerializedName("DRSCreatedBy")
    @Expose
    private String drsCreatedBy;

    @SerializedName("DRSClosedBy")
    @Expose
    private String drsClosedBy;

    @SerializedName("TotalAmount")
    @Expose
    private String totalAmount;

    @SerializedName("isClosedDrs")
    @Expose
    private Boolean isClosedDrs;

    @SerializedName("SignaturePath")
    @Expose
    private String SignaturePath;


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

    public String getDrsNo() {
        return drsNo;
    }

    public void setDrsNo(String drsNo) {
        this.drsNo = drsNo;
    }

    public String getDrsDate() {
        return drsDate;
    }

    public void setDrsDate(String drsDate) {
        this.drsDate = drsDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContactNo() {
        return driverContactNo;
    }

    public void setDriverContactNo(String driverContactNo) {
        this.driverContactNo = driverContactNo;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getTotalPackages() {
        return totalPackages;
    }

    public void setTotalPackages(String totalPackages) {
        this.totalPackages = totalPackages;
    }

    public String getTotalDockets() {
        return totalDockets;
    }

    public void setTotalDockets(String totalDockets) {
        this.totalDockets = totalDockets;
    }

    public String getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(String totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public String getDrsStatus() {
        return drsStatus;
    }

    public void setDrsStatus(String drsStatus) {
        this.drsStatus = drsStatus;
    }

    public String getDrsCreatedBy() {
        return drsCreatedBy;
    }

    public void setDrsCreatedBy(String drsCreatedBy) {
        this.drsCreatedBy = drsCreatedBy;
    }

    public String getDrsClosedBy() {
        return drsClosedBy;
    }

    public void setDrsClosedBy(String drsClosedBy) {
        this.drsClosedBy = drsClosedBy;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getClosedDrs() {
        return isClosedDrs;
    }

    public void setClosedDrs(Boolean closedDrs) {
        isClosedDrs = closedDrs;
    }

    public String getSignaturePath() {
        return SignaturePath;
    }

    public void setSignaturePath(String signaturePath) {
        SignaturePath = signaturePath;
    }

    public Drs() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rowNo);
        dest.writeString(this.id);
        dest.writeString(this.drsNo);
        dest.writeString(this.drsDate);
        dest.writeString(this.source);
        dest.writeString(this.destination);
        dest.writeString(this.driverName);
        dest.writeString(this.driverContactNo);
        dest.writeString(this.vehicleNo);
        dest.writeString(this.totalPackages);
        dest.writeString(this.totalDockets);
        dest.writeString(this.totalActualWeight);
        dest.writeString(this.drsStatus);
        dest.writeString(this.drsCreatedBy);
        dest.writeString(this.drsClosedBy);
        dest.writeString(this.totalAmount);
        dest.writeValue(this.isClosedDrs);
        dest.writeString(this.SignaturePath);
    }

    public void readFromParcel(Parcel source) {
        this.rowNo = source.readString();
        this.id = source.readString();
        this.drsNo = source.readString();
        this.drsDate = source.readString();
        this.source = source.readString();
        this.destination = source.readString();
        this.driverName = source.readString();
        this.driverContactNo = source.readString();
        this.vehicleNo = source.readString();
        this.totalPackages = source.readString();
        this.totalDockets = source.readString();
        this.totalActualWeight = source.readString();
        this.drsStatus = source.readString();
        this.drsCreatedBy = source.readString();
        this.drsClosedBy = source.readString();
        this.totalAmount = source.readString();
        this.isClosedDrs = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.SignaturePath = source.readString();
    }

    protected Drs(Parcel in) {
        this.rowNo = in.readString();
        this.id = in.readString();
        this.drsNo = in.readString();
        this.drsDate = in.readString();
        this.source = in.readString();
        this.destination = in.readString();
        this.driverName = in.readString();
        this.driverContactNo = in.readString();
        this.vehicleNo = in.readString();
        this.totalPackages = in.readString();
        this.totalDockets = in.readString();
        this.totalActualWeight = in.readString();
        this.drsStatus = in.readString();
        this.drsCreatedBy = in.readString();
        this.drsClosedBy = in.readString();
        this.totalAmount = in.readString();
        this.isClosedDrs = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.SignaturePath = in.readString();
    }

    public static final Creator<Drs> CREATOR = new Creator<Drs>() {
        @Override
        public Drs createFromParcel(Parcel source) {
            return new Drs(source);
        }

        @Override
        public Drs[] newArray(int size) {
            return new Drs[size];
        }
    };
}