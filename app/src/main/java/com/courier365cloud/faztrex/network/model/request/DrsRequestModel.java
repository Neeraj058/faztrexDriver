package com.courier365cloud.faztrex.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DrsRequestModel {

    // region DRS List
    public class DrsList {

        @SerializedName("SortDir")
        @Expose
        private String sortDir;

        @SerializedName("UserId")
        @Expose
        private String userId;

        @SerializedName("Keyword")
        @Expose
        private String keyword;

        @SerializedName("Cid")
        @Expose
        private String cid;

        @SerializedName("ToDate")
        @Expose
        private String toDate;

        @SerializedName("FromDate")
        @Expose
        private String fromDate;

        @SerializedName("FinFromDate")
        @Expose
        private String finFromDate;

        @SerializedName("PageSize")
        @Expose
        private String pageSize;

        @SerializedName("PageIndex")
        @Expose
        private String pageIndex;

        @SerializedName("Bid")
        @Expose
        private String bid;

        @SerializedName("SortCol")
        @Expose
        private String sortCol;

        @SerializedName("Filter")
        @Expose
        private String filter;

        @SerializedName("FinToDate")
        @Expose
        private String finToDate;

        public String getSortDir() {
            return sortDir;
        }

        public void setSortDir(String sortDir) {
            this.sortDir = sortDir;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getFinFromDate() {
            return finFromDate;
        }

        public void setFinFromDate(String finFromDate) {
            this.finFromDate = finFromDate;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(String pageIndex) {
            this.pageIndex = pageIndex;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getSortCol() {
            return sortCol;
        }

        public void setSortCol(String sortCol) {
            this.sortCol = sortCol;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public String getFinToDate() {
            return finToDate;
        }

        public void setFinToDate(String finToDate) {
            this.finToDate = finToDate;
        }
    }
    // endregion

    // region Get DRS Details
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
    }
    // endregion

    // region Update DRS Docket Details
    public class UpdateDrsDocketRequest {

        @SerializedName("UserId")
        @Expose
        private String userId;

        @SerializedName("Cid")
        @Expose
        private String companyId;

        @SerializedName("Bid")
        @Expose
        private String branchId;

        @SerializedName("DRSId")
        @Expose
        private String drsId;

        @SerializedName("DRSDetailId")
        @Expose
        private String drsDetailId;

        @SerializedName("CollectedAmount")
        @Expose
        private String collectedAmount;

        @SerializedName("TDSAmount")
        @Expose
        private String tdsAmount;

        @SerializedName("DeliveryStatus")
        @Expose
        private String deliveryStatus;

        @SerializedName("ReasonId")
        @Expose
        private String reasonId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getDrsId() {
            return drsId;
        }

        public void setDrsId(String drsId) {
            this.drsId = drsId;
        }

        public String getDrsDetailId() {
            return drsDetailId;
        }

        public void setDrsDetailId(String drsDetailId) {
            this.drsDetailId = drsDetailId;
        }

        public String getCollectedAmount() {
            return collectedAmount;
        }

        public void setCollectedAmount(String collectedAmount) {
            this.collectedAmount = collectedAmount;
        }

        public String getTdsAmount() {
            return tdsAmount;
        }

        public void setTdsAmount(String tdsAmount) {
            this.tdsAmount = tdsAmount;
        }

        public String getDeliveryStatus() {
            return deliveryStatus;
        }

        public void setDeliveryStatus(String deliveryStatus) {
            this.deliveryStatus = deliveryStatus;
        }

        public String getReasonId() {
            return reasonId;
        }

        public void setReasonId(String reasonId) {
            this.reasonId = reasonId;
        }
    }
    // endregion
}
