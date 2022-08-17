package com.courier365cloud.faztrex.network.model.response.drs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DrsDetail {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Cid")
    @Expose
    private String cid;

    @SerializedName("Bid")
    @Expose
    private String bid;

    @SerializedName("UserId")
    @Expose
    private String userId;

    @SerializedName("DRSNo")
    @Expose
    private String drsNo;

    @SerializedName("AutoNo")
    @Expose
    private String autoNo;

    @SerializedName("AutoNoDisplay")
    @Expose
    private String autoNoDisplay;

    @SerializedName("Prefix")
    @Expose
    private String prefix;

    @SerializedName("Suffix")
    @Expose
    private String suffix;

    @SerializedName("DRSDate")
    @Expose
    private String drsDate;

    @SerializedName("DRSTime")
    @Expose
    private String drsTime;

    @SerializedName("DRSStatus")
    @Expose
    private String drsStatus;

    @SerializedName("VehicleNo")
    @Expose
    private String vehicleNo;

    @SerializedName("DriverName")
    @Expose
    private String driverName;

    @SerializedName("DriverContactNo")
    @Expose
    private String driverContactNo;

    @SerializedName("OriginHubId")
    @Expose
    private String originHubId;

    @SerializedName("OriginHubName")
    @Expose
    private String originHubName;

    @SerializedName("OriginHubStateId")
    @Expose
    private String originHubStateId;

    @SerializedName("DestinationBranchId")
    @Expose
    private String destinationBranchId;

    @SerializedName("Remarks")
    @Expose
    private String remarks;

    @SerializedName("DocketIds")
    @Expose
    private String docketIds;

    @SerializedName("TotalDockets")
    @Expose
    private String totalDockets;

    @SerializedName("TotalPackages")
    @Expose
    private String totalPackages;

    @SerializedName("TotalActualWeight")
    @Expose
    private String totalActualWeight;

    @SerializedName("TotalPaidFreightAmount")
    @Expose
    private String totalPaidFreightAmount;

    @SerializedName("TotalToPayAmount")
    @Expose
    private String totalToPayAmount;

    @SerializedName("TotalBilledAmount")
    @Expose
    private String totalBilledAmount;

    @SerializedName("TotalAmount")
    @Expose
    private String totalAmount;

    @SerializedName("TotalCollectedAmount")
    @Expose
    private String totalCollectedAmount;

    @SerializedName("TotalBalanceAmount")
    @Expose
    private String totalBalanceAmount;

    @SerializedName("TotalTDSAmount")
    @Expose
    private String totalTdsAmount;

    @SerializedName("DRSClosedBy")
    @Expose
    private String drsClosedBy;

    @SerializedName("DRSClosedByUser")
    @Expose
    private String drsClosedByUser;

    @SerializedName("DRSClosedDate")
    @Expose
    private String drsClosedDate;

    @SerializedName("DRSCreatedBy")
    @Expose
    private String drsCreatedBy;

    @SerializedName("DRSCreatedByUser")
    @Expose
    private String drsCreatedByUser;

    @SerializedName("DRSCreatedDate")
    @Expose
    private String drsCreatedDate;

    @SerializedName("DocketSelectionList")
    @Expose
    private String docketSelectionList;

    @SerializedName("DocketDeliveryStatus")
    @Expose
    private String docketDeliveryStatus;

    @SerializedName("DocketDeliveryReasonId")
    @Expose
    private String docketDeliveryReasonId;

    @SerializedName("DocketCollectedAmount")
    @Expose
    private String docketCollectedAmount;

    @SerializedName("PageMode")
    @Expose
    private String pageMode;

    @SerializedName("AddNewRight")
    @Expose
    private String addNewRight;

    @SerializedName("SearchFromDate")
    @Expose
    private String searchFromDate;

    @SerializedName("SearchToDate")
    @Expose
    private String searchToDate;

    @SerializedName("DRSId")
    @Expose
    private String drsId;

    @SerializedName("DocketDetailId")
    @Expose
    private String docketDetailId;

    @SerializedName("DocketDetailNo")
    @Expose
    private String docketDetailNo;

    @SerializedName("PODDocument")
    @Expose
    private String podDocument;

    @SerializedName("DetailCollectedAmount")
    @Expose
    private String detailCollectedAmount;

    @SerializedName("DetailTDSAmount")
    @Expose
    private String detailTdsAmount;

    @SerializedName("PODDocumentFile")
    @Expose
    private String podDocumentFile;

    @SerializedName("DocketList")
    @Expose
    private ArrayList<DrsDocket> docketList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDrsNo() {
        return drsNo;
    }

    public void setDrsNo(String drsNo) {
        this.drsNo = drsNo;
    }

    public String getAutoNo() {
        return autoNo;
    }

    public void setAutoNo(String autoNo) {
        this.autoNo = autoNo;
    }

    public String getAutoNoDisplay() {
        return autoNoDisplay;
    }

    public void setAutoNoDisplay(String autoNoDisplay) {
        this.autoNoDisplay = autoNoDisplay;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDrsDate() {
        return drsDate;
    }

    public void setDrsDate(String drsDate) {
        this.drsDate = drsDate;
    }

    public String getDrsTime() {
        return drsTime;
    }

    public void setDrsTime(String drsTime) {
        this.drsTime = drsTime;
    }

    public String getDrsStatus() {
        return drsStatus;
    }

    public void setDrsStatus(String drsStatus) {
        this.drsStatus = drsStatus;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
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

    public String getOriginHubId() {
        return originHubId;
    }

    public void setOriginHubId(String originHubId) {
        this.originHubId = originHubId;
    }

    public String getOriginHubName() {
        return originHubName;
    }

    public void setOriginHubName(String originHubName) {
        this.originHubName = originHubName;
    }

    public String getOriginHubStateId() {
        return originHubStateId;
    }

    public void setOriginHubStateId(String originHubStateId) {
        this.originHubStateId = originHubStateId;
    }

    public String getDestinationBranchId() {
        return destinationBranchId;
    }

    public void setDestinationBranchId(String destinationBranchId) {
        this.destinationBranchId = destinationBranchId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDocketIds() {
        return docketIds;
    }

    public void setDocketIds(String docketIds) {
        this.docketIds = docketIds;
    }

    public String getTotalDockets() {
        return totalDockets;
    }

    public void setTotalDockets(String totalDockets) {
        this.totalDockets = totalDockets;
    }

    public String getTotalPackages() {
        return totalPackages;
    }

    public void setTotalPackages(String totalPackages) {
        this.totalPackages = totalPackages;
    }

    public String getTotalActualWeight() {
        return totalActualWeight;
    }

    public void setTotalActualWeight(String totalActualWeight) {
        this.totalActualWeight = totalActualWeight;
    }

    public String getTotalPaidFreightAmount() {
        return totalPaidFreightAmount;
    }

    public void setTotalPaidFreightAmount(String totalPaidFreightAmount) {
        this.totalPaidFreightAmount = totalPaidFreightAmount;
    }

    public String getTotalToPayAmount() {
        return totalToPayAmount;
    }

    public void setTotalToPayAmount(String totalToPayAmount) {
        this.totalToPayAmount = totalToPayAmount;
    }

    public String getTotalBilledAmount() {
        return totalBilledAmount;
    }

    public void setTotalBilledAmount(String totalBilledAmount) {
        this.totalBilledAmount = totalBilledAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalCollectedAmount() {
        return totalCollectedAmount;
    }

    public void setTotalCollectedAmount(String totalCollectedAmount) {
        this.totalCollectedAmount = totalCollectedAmount;
    }

    public String getTotalBalanceAmount() {
        return totalBalanceAmount;
    }

    public void setTotalBalanceAmount(String totalBalanceAmount) {
        this.totalBalanceAmount = totalBalanceAmount;
    }

    public String getTotalTdsAmount() {
        return totalTdsAmount;
    }

    public void setTotalTdsAmount(String totalTdsAmount) {
        this.totalTdsAmount = totalTdsAmount;
    }

    public String getDrsClosedBy() {
        return drsClosedBy;
    }

    public void setDrsClosedBy(String drsClosedBy) {
        this.drsClosedBy = drsClosedBy;
    }

    public String getDrsClosedByUser() {
        return drsClosedByUser;
    }

    public void setDrsClosedByUser(String drsClosedByUser) {
        this.drsClosedByUser = drsClosedByUser;
    }

    public String getDrsClosedDate() {
        return drsClosedDate;
    }

    public void setDrsClosedDate(String drsClosedDate) {
        this.drsClosedDate = drsClosedDate;
    }

    public String getDrsCreatedBy() {
        return drsCreatedBy;
    }

    public void setDrsCreatedBy(String drsCreatedBy) {
        this.drsCreatedBy = drsCreatedBy;
    }

    public String getDrsCreatedByUser() {
        return drsCreatedByUser;
    }

    public void setDrsCreatedByUser(String drsCreatedByUser) {
        this.drsCreatedByUser = drsCreatedByUser;
    }

    public String getDrsCreatedDate() {
        return drsCreatedDate;
    }

    public void setDrsCreatedDate(String drsCreatedDate) {
        this.drsCreatedDate = drsCreatedDate;
    }

    public String getDocketSelectionList() {
        return docketSelectionList;
    }

    public void setDocketSelectionList(String docketSelectionList) {
        this.docketSelectionList = docketSelectionList;
    }

    public String getDocketDeliveryStatus() {
        return docketDeliveryStatus;
    }

    public void setDocketDeliveryStatus(String docketDeliveryStatus) {
        this.docketDeliveryStatus = docketDeliveryStatus;
    }

    public String getDocketDeliveryReasonId() {
        return docketDeliveryReasonId;
    }

    public void setDocketDeliveryReasonId(String docketDeliveryReasonId) {
        this.docketDeliveryReasonId = docketDeliveryReasonId;
    }

    public String getDocketCollectedAmount() {
        return docketCollectedAmount;
    }

    public void setDocketCollectedAmount(String docketCollectedAmount) {
        this.docketCollectedAmount = docketCollectedAmount;
    }

    public String getPageMode() {
        return pageMode;
    }

    public void setPageMode(String pageMode) {
        this.pageMode = pageMode;
    }

    public String getAddNewRight() {
        return addNewRight;
    }

    public void setAddNewRight(String addNewRight) {
        this.addNewRight = addNewRight;
    }

    public String getSearchFromDate() {
        return searchFromDate;
    }

    public void setSearchFromDate(String searchFromDate) {
        this.searchFromDate = searchFromDate;
    }

    public String getSearchToDate() {
        return searchToDate;
    }

    public void setSearchToDate(String searchToDate) {
        this.searchToDate = searchToDate;
    }

    public String getDrsId() {
        return drsId;
    }

    public void setDrsId(String drsId) {
        this.drsId = drsId;
    }

    public String getDocketDetailId() {
        return docketDetailId;
    }

    public void setDocketDetailId(String docketDetailId) {
        this.docketDetailId = docketDetailId;
    }

    public String getDocketDetailNo() {
        return docketDetailNo;
    }

    public void setDocketDetailNo(String docketDetailNo) {
        this.docketDetailNo = docketDetailNo;
    }

    public String getPodDocument() {
        return podDocument;
    }

    public void setPodDocument(String podDocument) {
        this.podDocument = podDocument;
    }

    public String getDetailCollectedAmount() {
        return detailCollectedAmount;
    }

    public void setDetailCollectedAmount(String detailCollectedAmount) {
        this.detailCollectedAmount = detailCollectedAmount;
    }

    public String getDetailTdsAmount() {
        return detailTdsAmount;
    }

    public void setDetailTdsAmount(String detailTdsAmount) {
        this.detailTdsAmount = detailTdsAmount;
    }

    public String getPodDocumentFile() {
        return podDocumentFile;
    }

    public void setPodDocumentFile(String podDocumentFile) {
        this.podDocumentFile = podDocumentFile;
    }

    public ArrayList<DrsDocket> getDocketList() {
        return docketList;
    }

    public void setDocketList(ArrayList<DrsDocket> docketList) {
        this.docketList = docketList;
    }
}