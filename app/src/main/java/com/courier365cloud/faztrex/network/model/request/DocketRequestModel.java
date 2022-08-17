package com.courier365cloud.faztrex.network.model.request;

import com.courier365cloud.faztrex.network.model.response.docket.Dimension;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocketRequestModel {

    // region Get Consignor List By Branch
    public class GetConsignorByBranchRequest {

        @SerializedName("BranchId")
        @Expose
        private String branchId;

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }
    }
    // endregion

    // region Get Consignor Information
    public class GetConsignorInfoRequest {

        @SerializedName("BranchId")
        @Expose
        private String branchId;

        @SerializedName("ConsignorId")
        @Expose
        private String consignorId;

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }

        public String getConsignorId() {
            return consignorId;
        }

        public void setConsignorId(String consignorId) {
            this.consignorId = consignorId;
        }
    }
    // endregion

    // region Get EDD
    public class EDDCalculationRequest {

        @SerializedName("CustomerType")
        @Expose
        private String customerType;

        @SerializedName("DispatchMode")
        @Expose
        private String dispatchMode;

        @SerializedName("BookingBranchStateId")
        @Expose
        private String bookingBranchStateId;

        @SerializedName("DestinationStateId")
        @Expose
        private String destinationStateId;

        @SerializedName("DestinationId")
        @Expose
        private String destinationId;

        @SerializedName("DestinationPostCodeType")
        @Expose
        private String destinationPostcodeType;

        @SerializedName("BookingDate")
        @Expose
        private String bookingDate;

        @SerializedName("CompanyID")
        @Expose
        private String companyId;

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

        public String getBookingBranchStateId() {
            return bookingBranchStateId;
        }

        public void setBookingBranchStateId(String bookingBranchStateId) {
            this.bookingBranchStateId = bookingBranchStateId;
        }

        public String getDestinationStateId() {
            return destinationStateId;
        }

        public void setDestinationStateId(String destinationStateId) {
            this.destinationStateId = destinationStateId;
        }

        public String getDestinationId() {
            return destinationId;
        }

        public void setDestinationId(String destinationId) {
            this.destinationId = destinationId;
        }

        public String getDestinationPostcodeType() {
            return destinationPostcodeType;
        }

        public void setDestinationPostcodeType(String destinationPostcodeType) {
            this.destinationPostcodeType = destinationPostcodeType;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }
    }
    // endregion

    // region Docket List
    public class DocketList {

        @SerializedName("FromDate")
        @Expose
        private String fromDate;

        @SerializedName("Filter")
        @Expose
        private String filter;

        @SerializedName("Keyword")
        @Expose
        private String keyword;

        @SerializedName("FinToDate")
        @Expose
        private String finToDate;

        @SerializedName("ToDate")
        @Expose
        private String toDate;

        @SerializedName("PageSize")
        @Expose
        private String pageSize;

        @SerializedName("Cid")
        @Expose
        private String cid;

        @SerializedName("FinFromDate")
        @Expose
        private String finFromDate;

        @SerializedName("PageIndex")
        @Expose
        private String pageIndex;

        @SerializedName("UserId")
        @Expose
        private String userId;

        @SerializedName("Bid")
        @Expose
        private String bid;

        @SerializedName("Customertype")
        @Expose
        private String customerType;

        @SerializedName("Dispatchmode")
        @Expose
        private String dispatchMode;

        @SerializedName("Paymenttype")
        @Expose
        private String paymentType;

        @SerializedName("Originid")
        @Expose
        private String originId;

        @SerializedName("Destinatonstateid")
        @Expose
        private String destinatonStateId;

        @SerializedName("Destinationcityid")
        @Expose
        private String destinationCityId;

        @SerializedName("FilterBranchId")
        @Expose
        private String filterBranchId;

        @SerializedName("FilterSignedByEmployee")
        @Expose
        private String filterSignedByEmployee;

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getFinToDate() {
            return finToDate;
        }

        public void setFinToDate(String finToDate) {
            this.finToDate = finToDate;
        }

        public String getToDate() {
            return toDate;
        }

        public void setToDate(String toDate) {
            this.toDate = toDate;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getFinFromDate() {
            return finFromDate;
        }

        public void setFinFromDate(String finFromDate) {
            this.finFromDate = finFromDate;
        }

        public String getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(String pageIndex) {
            this.pageIndex = pageIndex;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
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

        public String getOriginId() {
            return originId;
        }

        public void setOriginId(String originId) {
            this.originId = originId;
        }

        public String getDestinatonStateId() {
            return destinatonStateId;
        }

        public void setDestinatonStateId(String destinatonStateId) {
            this.destinatonStateId = destinatonStateId;
        }

        public String getDestinationCityId() {
            return destinationCityId;
        }

        public void setDestinationCityId(String destinationCityId) {
            this.destinationCityId = destinationCityId;
        }

        public String getFilterBranchId() {
            return filterBranchId;
        }

        public void setFilterBranchId(String filterBranchId) {
            this.filterBranchId = filterBranchId;
        }

        public String getFilterSignedByEmployee() {
            return filterSignedByEmployee;
        }

        public void setFilterSignedByEmployee(String filterSignedByEmployee) {
            this.filterSignedByEmployee = filterSignedByEmployee;
        }
    }
    // endregion

    // region Dimension Calculation
    public class DimensionCalculationRequest {

        @SerializedName("CompanyID")
        @Expose
        private String companyId;

        @SerializedName("CustomerType")
        @Expose
        private String customerType;

        @SerializedName("DispatchMode")
        @Expose
        private String dispatchMode;

        @SerializedName("ConsignorId")
        @Expose
        private String consignorId;

        @SerializedName("listDocketDimension")
        @Expose
        private ArrayList<Dimension> dimensionList;

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
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

        public String getConsignorId() {
            return consignorId;
        }

        public void setConsignorId(String consignorId) {
            this.consignorId = consignorId;
        }

        public ArrayList<Dimension> getDimensionList() {
            return dimensionList;
        }

        public void setDimensionList(ArrayList<Dimension> dimensionList) {
            this.dimensionList = dimensionList;
        }
    }
    // endregion

    // region Get Consignor List
    public class ConsignorListRequest {

        @SerializedName("BranchId")
        @Expose
        private String branchId;

        public String getBranchId() {
            return branchId;
        }

        public void setBranchId(String branchId) {
            this.branchId = branchId;
        }
    }
    // endregion

    // region Docket Calculation Request
    public class DocketCalculationRequest {

        @SerializedName("Id")
        @Expose
        private String id;

        @SerializedName("CompanyID")
        @Expose
        private String companyId;

        @SerializedName("CustomerType")
        @Expose
        private String customerType;

        @SerializedName("DispatchMode")
        @Expose
        private String dispatchMode;

        @SerializedName("Prefix")
        @Expose
        private String prefix;

        @SerializedName("Suffix")
        @Expose
        private String suffix;

        @SerializedName("AutoNo")
        @Expose
        private String autoNo;

        @SerializedName("BarcodeNo")
        @Expose
        private String barcodeNo;

        @SerializedName("DocketNo")
        @Expose
        private String docketNo;

        @SerializedName("BookingDate")
        @Expose
        private String bookingDate;

        @SerializedName("PaymentType")
        @Expose
        private String paymentType;

        @SerializedName("BookingBranchId")
        @Expose
        private String bookingBranchId;

        @SerializedName("BookingBranchName")
        @Expose
        private String bookingBranchName;

        @SerializedName("BookingBranchStateId")
        @Expose
        private String bookingBranchStateId;

        @SerializedName("BookingBranchGSTNo")
        @Expose
        private String bookingBranchGstNo;

        @SerializedName("BookingBranchHubGSTNo")
        @Expose
        private String bookingBranchHubGSTNo;

        @SerializedName("DestinationStateId")
        @Expose
        private String destinationStateId;

        @SerializedName("DestinationStateName")
        @Expose
        private String destinationStateName;

        @SerializedName("DestinationId")
        @Expose
        private String destinationId;

        @SerializedName("DestinationPostCode")
        @Expose
        private String destinationPostCode;

        @SerializedName("DestinationPostCodeType")
        @Expose
        private String destinationPostCodeType;

        @SerializedName("ConsignorId")
        @Expose
        private String consignorId;

        @SerializedName("ConsignorCode")
        @Expose
        private String consignorCode;

        @SerializedName("ConsignorName")
        @Expose
        private String consignorName;

        @SerializedName("ConsignorAddress")
        @Expose
        private String consignorAddress;

        @SerializedName("ConsignorPostCode")
        @Expose
        private String consignorPostCode;

        @SerializedName("ConsignorMobileNo")
        @Expose
        private String consignorMobileNo;

        @SerializedName("ConsignorGSTNo")
        @Expose
        private String consignorGstNo;

        @SerializedName("IsConsignorSEZ")
        @Expose
        private String isConsignorSez;

        @SerializedName("RConsignorCode")
        @Expose
        private String rConsignorCode;

        @SerializedName("RConsignorName")
        @Expose
        private String rConsignorName;

        @SerializedName("RConsignorAddress")
        @Expose
        private String rConsignorAddress;

        @SerializedName("RConsignorPostCode")
        @Expose
        private String rConsignorPostCode;

        @SerializedName("RConsignorMobileNo")
        @Expose
        private String rConsignorMobileNo;

        @SerializedName("RConsignorGSTNo")
        @Expose
        private String rConsignorGstNo;

        @SerializedName("ConsigneeCode")
        @Expose
        private String consigneeCode;

        @SerializedName("ConsigneeName")
        @Expose
        private String consigneeName;

        @SerializedName("ConsigneeAddress")
        @Expose
        private String consigneeAddress;

        @SerializedName("ConsigneePostCode")
        @Expose
        private String consigneePostCode;

        @SerializedName("ConsigneeMobileNo")
        @Expose
        private String consigneeMobileNo;

        @SerializedName("ConsigneeGSTNo")
        @Expose
        private String consigneeGstNo;

        @SerializedName("InvoiceNo")
        @Expose
        private String invoiceNo;

        @SerializedName("InvoiceValue")
        @Expose
        private String invoiceValue;

        @SerializedName("PONo")
        @Expose
        private String poNo;

        @SerializedName("EwayBillNo")
        @Expose
        private String ewayBillNo;

        @SerializedName("RiskType")
        @Expose
        private String riskType;

        @SerializedName("DeliveryType")
        @Expose
        private String deliveryType;

        @SerializedName("VerticleTypeId")
        @Expose
        private String verticleTypeId;

        @SerializedName("ProductName")
        @Expose
        private String productName;

        @SerializedName("PackingTypeId")
        @Expose
        private String packingTypeId;

        @SerializedName("NoOfPackages")
        @Expose
        private String noOfPackages;

        @SerializedName("ActualWeight")
        @Expose
        private String actualWeight;

        @SerializedName("ChargeWeight")
        @Expose
        private String chargeWeight;

        @SerializedName("VolumetricWeight")
        @Expose
        private String volumetricWeight;

        @SerializedName("ChargeWeightPercentage")
        @Expose
        private String chargeWeightPercentage;

        @SerializedName("BasicChargeAmount")
        @Expose
        private String basicChargeAmount;

        @SerializedName("ValueSurchargeAmount")
        @Expose
        private String valueSurchargeAmount;

        @SerializedName("GreenChargeAmount")
        @Expose
        private String greenChargeAmount;

        @SerializedName("DeliveryChargeAmount")
        @Expose
        private String deliveryChargeAmount;

        @SerializedName("ODAChargesAmount")
        @Expose
        private String odaChargesAmount;

        @SerializedName("ToPayChargesAmount")
        @Expose
        private String toPayChargesAmount;

        @SerializedName("PkgHandlingChargeAmount")
        @Expose
        private String pkgHandlingChargeAmount;

        @SerializedName("SubTotalAmount")
        @Expose
        private String subTotalAmount;

        @SerializedName("SurchargeAmount")
        @Expose
        private String surchargeAmount;

        @SerializedName("TotalFreightAmount")
        @Expose
        private String totalFreightAmount;

        @SerializedName("SGSTPercentage")
        @Expose
        private String sgstPercentage;

        @SerializedName("SGSTAmount")
        @Expose
        private String sgstAmount;

        @SerializedName("CGSTPercentage")
        @Expose
        private String cgstPercentage;

        @SerializedName("CGSTAmount")
        @Expose
        private String cgstAmount;

        @SerializedName("IGSTPercentage")
        @Expose
        private String igstPercentage;

        @SerializedName("IGSTAmount")
        @Expose
        private String igstAmount;

        @SerializedName("TotalGSTAmount")
        @Expose
        private String totalGstAmount;

        @SerializedName("NetAmount")
        @Expose
        private String netAmount;

        @SerializedName("DocketChargeAmount")
        @Expose
        private String docketChargeAmount;

        @SerializedName("HardCopyChargeAmount")
        @Expose
        private String hardCopyChargeAmount;

        @SerializedName("FMId")
        @Expose
        private String fmId;

        @SerializedName("HMId1")
        @Expose
        private String hmId1;

        @SerializedName("HMId2")
        @Expose
        private String hmId2;

        @SerializedName("HMId3")
        @Expose
        private String hmId3;

        @SerializedName("HMId4")
        @Expose
        private String hmId4;

        @SerializedName("HMId5")
        @Expose
        private String hmId5;

        @SerializedName("DRSId")
        @Expose
        private String drsId;

        @SerializedName("BillId")
        @Expose
        private String billId;

        @SerializedName("IsDelivered")
        @Expose
        private String isDelivered;

        @SerializedName("ActualDeliveryDate")
        @Expose
        private String actualDeliveryDate;

        @SerializedName("FinalDeliveryDate")
        @Expose
        private String finalDeliveryDate;

        @SerializedName("DocketDimensionDetailId")
        @Expose
        private String docketDimensionDetailId;

        @SerializedName("PageMode")
        @Expose
        private String pageMode;

        @SerializedName("AddNewRight")
        @Expose
        private String addNewRight;

        @SerializedName("Boxes")
        @Expose
        private String boxes;

        @SerializedName("Length")
        @Expose
        private String length;

        @SerializedName("Width")
        @Expose
        private String width;

        @SerializedName("Height")
        @Expose
        private String height;

        @SerializedName("DimensionActualWeight")
        @Expose
        private String dimensionActualWeight;

        @SerializedName("DimensionVolumetricWeight")
        @Expose
        private String dimensionVolumetricWeight;

        @SerializedName("DimensionTotalActualWeight")
        @Expose
        private String dimensionTotalActualWeight;

        @SerializedName("DocketDimensionDetail")
        @Expose
        private String docketDimensionDetail;

        @SerializedName("BLDocketDimensionDetails")
        @Expose
        private String blDocketDimensionDetails;

        @SerializedName("Filter")
        @Expose
        private String filter;

        @SerializedName("FinancialYear")
        @Expose
        private String financialYear;

        @SerializedName("Search")
        @Expose
        private String search;

        @SerializedName("RowNo_List")
        @Expose
        private String rowNo_List;

        @SerializedName("Id_List")
        @Expose
        private String id_List;

        @SerializedName("DocketNo_List")
        @Expose
        private String docketNo_List;

        @SerializedName("BookingDate_List")
        @Expose
        private String bookingDate_List;

        @SerializedName("CustomerType_List")
        @Expose
        private String customerType_List;

        @SerializedName("DispatchMode_List")
        @Expose
        private String dispatchMode_List;

        @SerializedName("PaymentType_List")
        @Expose
        private String paymentType_List;

        @SerializedName("DestinationState_List")
        @Expose
        private String destinationState_List;

        @SerializedName("Destination_List")
        @Expose
        private String destination_List;

        @SerializedName("ConsignorName_List")
        @Expose
        private String consignorName_List;

        @SerializedName("ConsigneeName_List")
        @Expose
        private String consigneeName_List;

        @SerializedName("NoOfPackages_List")
        @Expose
        private String noOfPackages_List;

        @SerializedName("ActualWeight_List")
        @Expose
        private String actualWeight_List;

        @SerializedName("ChargeWeight_List")
        @Expose
        private String chargeWeight_List;

        @SerializedName("NetAmount_List")
        @Expose
        private String netAmount_List;

        @SerializedName("EwayBillNo_List")
        @Expose
        private String ewayBillNo_List;

        @SerializedName("InvoiceValue_List")
        @Expose
        private String invoiceValue_List;

        @SerializedName("Sid")
        @Expose
        private String sid;

        @SerializedName("Cid")
        @Expose
        private String cid;

        @SerializedName("Bid")
        @Expose
        private String bid;

        @SerializedName("Uid")
        @Expose
        private String uid;

        @SerializedName("IsActive")
        @Expose
        private String isActive;

        @SerializedName("IsDelete")
        @Expose
        private String isDelete;

        @SerializedName("EntryDate")
        @Expose
        private String entryDate;

        @SerializedName("LastModifyDate")
        @Expose
        private String lastModifyDate;

        @SerializedName("LastModifyBy")
        @Expose
        private String lastModifyBy;

        @SerializedName("IsDefault")
        @Expose
        private String isDefault;

        @SerializedName("IsEnable")
        @Expose
        private String isEnable;

        @SerializedName("Status")
        @Expose
        private String status;

        @SerializedName("IsSync")
        @Expose
        private String isSync;

        @SerializedName("IsFrom")
        @Expose
        private String isFrom;

        @SerializedName("WildSearch")
        @Expose
        private String wildSearch;

        @SerializedName("Notes")
        @Expose
        private String notes;

        @SerializedName("ExtraInt1")
        @Expose
        private String extraInt1;

        @SerializedName("ExtraInt2")
        @Expose
        private String extraInt2;

        @SerializedName("ExtraVarChar1")
        @Expose
        private String extraVarChar1;

        @SerializedName("ExtraVarChar2")
        @Expose
        private String extraVarChar2;

        @SerializedName("ExtraDecimal1")
        @Expose
        private String extraDecimal1;

        @SerializedName("ExtraDecimal2")
        @Expose
        private String extraDecimal2;

        @SerializedName("ExtraDateTime1")
        @Expose
        private String extraDateTime1;

        @SerializedName("ExtraDateTime2")
        @Expose
        private String extraDateTime2;

        @SerializedName("ExtraBit1")
        @Expose
        private String extraBit1;

        @SerializedName("ExtraBit2")
        @Expose
        private String extraBit2;

        @SerializedName("listDocketDimension")
        @Expose
        private ArrayList<Dimension> dimensionList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
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

        public String getAutoNo() {
            return autoNo;
        }

        public void setAutoNo(String autoNo) {
            this.autoNo = autoNo;
        }

        public String getBarcodeNo() {
            return barcodeNo;
        }

        public void setBarcodeNo(String barcodeNo) {
            this.barcodeNo = barcodeNo;
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

        public String getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }

        public String getBookingBranchId() {
            return bookingBranchId;
        }

        public void setBookingBranchId(String bookingBranchId) {
            this.bookingBranchId = bookingBranchId;
        }

        public String getBookingBranchName() {
            return bookingBranchName;
        }

        public void setBookingBranchName(String bookingBranchName) {
            this.bookingBranchName = bookingBranchName;
        }

        public String getBookingBranchStateId() {
            return bookingBranchStateId;
        }

        public void setBookingBranchStateId(String bookingBranchStateId) {
            this.bookingBranchStateId = bookingBranchStateId;
        }

        public String getBookingBranchGstNo() {
            return bookingBranchGstNo;
        }

        public void setBookingBranchGstNo(String bookingBranchGstNo) {
            this.bookingBranchGstNo = bookingBranchGstNo;
        }

        public String getDestinationStateId() {
            return destinationStateId;
        }

        public void setDestinationStateId(String destinationStateId) {
            this.destinationStateId = destinationStateId;
        }

        public String getDestinationStateName() {
            return destinationStateName;
        }

        public void setDestinationStateName(String destinationStateName) {
            this.destinationStateName = destinationStateName;
        }

        public String getDestinationId() {
            return destinationId;
        }

        public void setDestinationId(String destinationId) {
            this.destinationId = destinationId;
        }

        public String getDestinationPostCode() {
            return destinationPostCode;
        }

        public void setDestinationPostCode(String destinationPostCode) {
            this.destinationPostCode = destinationPostCode;
        }

        public String getDestinationPostCodeType() {
            return destinationPostCodeType;
        }

        public void setDestinationPostCodeType(String destinationPostCodeType) {
            this.destinationPostCodeType = destinationPostCodeType;
        }

        public String getConsignorId() {
            return consignorId;
        }

        public void setConsignorId(String consignorId) {
            this.consignorId = consignorId;
        }

        public String getConsignorCode() {
            return consignorCode;
        }

        public void setConsignorCode(String consignorCode) {
            this.consignorCode = consignorCode;
        }

        public String getConsignorName() {
            return consignorName;
        }

        public void setConsignorName(String consignorName) {
            this.consignorName = consignorName;
        }

        public String getConsignorAddress() {
            return consignorAddress;
        }

        public void setConsignorAddress(String consignorAddress) {
            this.consignorAddress = consignorAddress;
        }

        public String getConsignorPostCode() {
            return consignorPostCode;
        }

        public void setConsignorPostCode(String consignorPostCode) {
            this.consignorPostCode = consignorPostCode;
        }

        public String getConsignorMobileNo() {
            return consignorMobileNo;
        }

        public void setConsignorMobileNo(String consignorMobileNo) {
            this.consignorMobileNo = consignorMobileNo;
        }

        public String getConsignorGstNo() {
            return consignorGstNo;
        }

        public void setConsignorGstNo(String consignorGstNo) {
            this.consignorGstNo = consignorGstNo;
        }

        public String getIsConsignorSez() {
            return isConsignorSez;
        }

        public void setIsConsignorSez(String isConsignorSez) {
            this.isConsignorSez = isConsignorSez;
        }

        public String getrConsignorCode() {
            return rConsignorCode;
        }

        public void setrConsignorCode(String rConsignorCode) {
            this.rConsignorCode = rConsignorCode;
        }

        public String getrConsignorName() {
            return rConsignorName;
        }

        public void setrConsignorName(String rConsignorName) {
            this.rConsignorName = rConsignorName;
        }

        public String getrConsignorAddress() {
            return rConsignorAddress;
        }

        public void setrConsignorAddress(String rConsignorAddress) {
            this.rConsignorAddress = rConsignorAddress;
        }

        public String getrConsignorPostCode() {
            return rConsignorPostCode;
        }

        public void setrConsignorPostCode(String rConsignorPostCode) {
            this.rConsignorPostCode = rConsignorPostCode;
        }

        public String getrConsignorMobileNo() {
            return rConsignorMobileNo;
        }

        public void setrConsignorMobileNo(String rConsignorMobileNo) {
            this.rConsignorMobileNo = rConsignorMobileNo;
        }

        public String getrConsignorGstNo() {
            return rConsignorGstNo;
        }

        public void setrConsignorGstNo(String rConsignorGstNo) {
            this.rConsignorGstNo = rConsignorGstNo;
        }

        public String getConsigneeCode() {
            return consigneeCode;
        }

        public void setConsigneeCode(String consigneeCode) {
            this.consigneeCode = consigneeCode;
        }

        public String getConsigneeName() {
            return consigneeName;
        }

        public void setConsigneeName(String consigneeName) {
            this.consigneeName = consigneeName;
        }

        public String getConsigneeAddress() {
            return consigneeAddress;
        }

        public void setConsigneeAddress(String consigneeAddress) {
            this.consigneeAddress = consigneeAddress;
        }

        public String getConsigneePostCode() {
            return consigneePostCode;
        }

        public void setConsigneePostCode(String consigneePostCode) {
            this.consigneePostCode = consigneePostCode;
        }

        public String getConsigneeMobileNo() {
            return consigneeMobileNo;
        }

        public void setConsigneeMobileNo(String consigneeMobileNo) {
            this.consigneeMobileNo = consigneeMobileNo;
        }

        public String getConsigneeGstNo() {
            return consigneeGstNo;
        }

        public void setConsigneeGstNo(String consigneeGstNo) {
            this.consigneeGstNo = consigneeGstNo;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getInvoiceValue() {
            return invoiceValue;
        }

        public void setInvoiceValue(String invoiceValue) {
            this.invoiceValue = invoiceValue;
        }

        public String getPoNo() {
            return poNo;
        }

        public void setPoNo(String poNo) {
            this.poNo = poNo;
        }

        public String getEwayBillNo() {
            return ewayBillNo;
        }

        public void setEwayBillNo(String ewayBillNo) {
            this.ewayBillNo = ewayBillNo;
        }

        public String getRiskType() {
            return riskType;
        }

        public void setRiskType(String riskType) {
            this.riskType = riskType;
        }

        public String getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
        }

        public String getVerticleTypeId() {
            return verticleTypeId;
        }

        public void setVerticleTypeId(String verticleTypeId) {
            this.verticleTypeId = verticleTypeId;
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

        public String getVolumetricWeight() {
            return volumetricWeight;
        }

        public void setVolumetricWeight(String volumetricWeight) {
            this.volumetricWeight = volumetricWeight;
        }

        public String getChargeWeightPercentage() {
            return chargeWeightPercentage;
        }

        public void setChargeWeightPercentage(String chargeWeightPercentage) {
            this.chargeWeightPercentage = chargeWeightPercentage;
        }

        public String getBasicChargeAmount() {
            return basicChargeAmount;
        }

        public void setBasicChargeAmount(String basicChargeAmount) {
            this.basicChargeAmount = basicChargeAmount;
        }

        public String getValueSurchargeAmount() {
            return valueSurchargeAmount;
        }

        public void setValueSurchargeAmount(String valueSurchargeAmount) {
            this.valueSurchargeAmount = valueSurchargeAmount;
        }

        public String getGreenChargeAmount() {
            return greenChargeAmount;
        }

        public void setGreenChargeAmount(String greenChargeAmount) {
            this.greenChargeAmount = greenChargeAmount;
        }

        public String getDeliveryChargeAmount() {
            return deliveryChargeAmount;
        }

        public void setDeliveryChargeAmount(String deliveryChargeAmount) {
            this.deliveryChargeAmount = deliveryChargeAmount;
        }

        public String getOdaChargesAmount() {
            return odaChargesAmount;
        }

        public void setOdaChargesAmount(String odaChargesAmount) {
            this.odaChargesAmount = odaChargesAmount;
        }

        public String getToPayChargesAmount() {
            return toPayChargesAmount;
        }

        public void setToPayChargesAmount(String toPayChargesAmount) {
            this.toPayChargesAmount = toPayChargesAmount;
        }

        public String getPkgHandlingChargeAmount() {
            return pkgHandlingChargeAmount;
        }

        public void setPkgHandlingChargeAmount(String pkgHandlingChargeAmount) {
            this.pkgHandlingChargeAmount = pkgHandlingChargeAmount;
        }

        public String getSubTotalAmount() {
            return subTotalAmount;
        }

        public void setSubTotalAmount(String subTotalAmount) {
            this.subTotalAmount = subTotalAmount;
        }

        public String getSurchargeAmount() {
            return surchargeAmount;
        }

        public void setSurchargeAmount(String surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
        }

        public String getTotalFreightAmount() {
            return totalFreightAmount;
        }

        public void setTotalFreightAmount(String totalFreightAmount) {
            this.totalFreightAmount = totalFreightAmount;
        }

        public String getSgstPercentage() {
            return sgstPercentage;
        }

        public void setSgstPercentage(String sgstPercentage) {
            this.sgstPercentage = sgstPercentage;
        }

        public String getSgstAmount() {
            return sgstAmount;
        }

        public void setSgstAmount(String sgstAmount) {
            this.sgstAmount = sgstAmount;
        }

        public String getCgstPercentage() {
            return cgstPercentage;
        }

        public void setCgstPercentage(String cgstPercentage) {
            this.cgstPercentage = cgstPercentage;
        }

        public String getCgstAmount() {
            return cgstAmount;
        }

        public void setCgstAmount(String cgstAmount) {
            this.cgstAmount = cgstAmount;
        }

        public String getIgstPercentage() {
            return igstPercentage;
        }

        public void setIgstPercentage(String igstPercentage) {
            this.igstPercentage = igstPercentage;
        }

        public String getIgstAmount() {
            return igstAmount;
        }

        public void setIgstAmount(String igstAmount) {
            this.igstAmount = igstAmount;
        }

        public String getTotalGstAmount() {
            return totalGstAmount;
        }

        public void setTotalGstAmount(String totalGstAmount) {
            this.totalGstAmount = totalGstAmount;
        }

        public String getNetAmount() {
            return netAmount;
        }

        public void setNetAmount(String netAmount) {
            this.netAmount = netAmount;
        }

        public String getDocketChargeAmount() {
            return docketChargeAmount;
        }

        public void setDocketChargeAmount(String docketChargeAmount) {
            this.docketChargeAmount = docketChargeAmount;
        }

        public String getHardCopyChargeAmount() {
            return hardCopyChargeAmount;
        }

        public void setHardCopyChargeAmount(String hardCopyChargeAmount) {
            this.hardCopyChargeAmount = hardCopyChargeAmount;
        }

        public String getFmId() {
            return fmId;
        }

        public void setFmId(String fmId) {
            this.fmId = fmId;
        }

        public String getHmId1() {
            return hmId1;
        }

        public void setHmId1(String hmId1) {
            this.hmId1 = hmId1;
        }

        public String getHmId2() {
            return hmId2;
        }

        public void setHmId2(String hmId2) {
            this.hmId2 = hmId2;
        }

        public String getHmId3() {
            return hmId3;
        }

        public void setHmId3(String hmId3) {
            this.hmId3 = hmId3;
        }

        public String getHmId4() {
            return hmId4;
        }

        public void setHmId4(String hmId4) {
            this.hmId4 = hmId4;
        }

        public String getHmId5() {
            return hmId5;
        }

        public void setHmId5(String hmId5) {
            this.hmId5 = hmId5;
        }

        public String getDrsId() {
            return drsId;
        }

        public void setDrsId(String drsId) {
            this.drsId = drsId;
        }

        public String getBillId() {
            return billId;
        }

        public void setBillId(String billId) {
            this.billId = billId;
        }

        public String getIsDelivered() {
            return isDelivered;
        }

        public void setIsDelivered(String isDelivered) {
            this.isDelivered = isDelivered;
        }

        public String getActualDeliveryDate() {
            return actualDeliveryDate;
        }

        public void setActualDeliveryDate(String actualDeliveryDate) {
            this.actualDeliveryDate = actualDeliveryDate;
        }

        public String getFinalDeliveryDate() {
            return finalDeliveryDate;
        }

        public void setFinalDeliveryDate(String finalDeliveryDate) {
            this.finalDeliveryDate = finalDeliveryDate;
        }

        public String getDocketDimensionDetailId() {
            return docketDimensionDetailId;
        }

        public void setDocketDimensionDetailId(String docketDimensionDetailId) {
            this.docketDimensionDetailId = docketDimensionDetailId;
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

        public String getDimensionActualWeight() {
            return dimensionActualWeight;
        }

        public void setDimensionActualWeight(String dimensionActualWeight) {
            this.dimensionActualWeight = dimensionActualWeight;
        }

        public String getDimensionVolumetricWeight() {
            return dimensionVolumetricWeight;
        }

        public void setDimensionVolumetricWeight(String dimensionVolumetricWeight) {
            this.dimensionVolumetricWeight = dimensionVolumetricWeight;
        }

        public String getDimensionTotalActualWeight() {
            return dimensionTotalActualWeight;
        }

        public void setDimensionTotalActualWeight(String dimensionTotalActualWeight) {
            this.dimensionTotalActualWeight = dimensionTotalActualWeight;
        }

        public String getDocketDimensionDetail() {
            return docketDimensionDetail;
        }

        public void setDocketDimensionDetail(String docketDimensionDetail) {
            this.docketDimensionDetail = docketDimensionDetail;
        }

        public String getBlDocketDimensionDetails() {
            return blDocketDimensionDetails;
        }

        public void setBlDocketDimensionDetails(String blDocketDimensionDetails) {
            this.blDocketDimensionDetails = blDocketDimensionDetails;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public String getFinancialYear() {
            return financialYear;
        }

        public void setFinancialYear(String financialYear) {
            this.financialYear = financialYear;
        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }

        public String getRowNo_List() {
            return rowNo_List;
        }

        public void setRowNo_List(String rowNo_List) {
            this.rowNo_List = rowNo_List;
        }

        public String getId_List() {
            return id_List;
        }

        public void setId_List(String id_List) {
            this.id_List = id_List;
        }

        public String getDocketNo_List() {
            return docketNo_List;
        }

        public void setDocketNo_List(String docketNo_List) {
            this.docketNo_List = docketNo_List;
        }

        public String getBookingDate_List() {
            return bookingDate_List;
        }

        public void setBookingDate_List(String bookingDate_List) {
            this.bookingDate_List = bookingDate_List;
        }

        public String getCustomerType_List() {
            return customerType_List;
        }

        public void setCustomerType_List(String customerType_List) {
            this.customerType_List = customerType_List;
        }

        public String getDispatchMode_List() {
            return dispatchMode_List;
        }

        public void setDispatchMode_List(String dispatchMode_List) {
            this.dispatchMode_List = dispatchMode_List;
        }

        public String getPaymentType_List() {
            return paymentType_List;
        }

        public void setPaymentType_List(String paymentType_List) {
            this.paymentType_List = paymentType_List;
        }

        public String getDestinationState_List() {
            return destinationState_List;
        }

        public void setDestinationState_List(String destinationState_List) {
            this.destinationState_List = destinationState_List;
        }

        public String getDestination_List() {
            return destination_List;
        }

        public void setDestination_List(String destination_List) {
            this.destination_List = destination_List;
        }

        public String getConsignorName_List() {
            return consignorName_List;
        }

        public void setConsignorName_List(String consignorName_List) {
            this.consignorName_List = consignorName_List;
        }

        public String getConsigneeName_List() {
            return consigneeName_List;
        }

        public void setConsigneeName_List(String consigneeName_List) {
            this.consigneeName_List = consigneeName_List;
        }

        public String getNoOfPackages_List() {
            return noOfPackages_List;
        }

        public void setNoOfPackages_List(String noOfPackages_List) {
            this.noOfPackages_List = noOfPackages_List;
        }

        public String getActualWeight_List() {
            return actualWeight_List;
        }

        public void setActualWeight_List(String actualWeight_List) {
            this.actualWeight_List = actualWeight_List;
        }

        public String getChargeWeight_List() {
            return chargeWeight_List;
        }

        public void setChargeWeight_List(String chargeWeight_List) {
            this.chargeWeight_List = chargeWeight_List;
        }

        public String getNetAmount_List() {
            return netAmount_List;
        }

        public void setNetAmount_List(String netAmount_List) {
            this.netAmount_List = netAmount_List;
        }

        public String getEwayBillNo_List() {
            return ewayBillNo_List;
        }

        public void setEwayBillNo_List(String ewayBillNo_List) {
            this.ewayBillNo_List = ewayBillNo_List;
        }

        public String getInvoiceValue_List() {
            return invoiceValue_List;
        }

        public void setInvoiceValue_List(String invoiceValue_List) {
            this.invoiceValue_List = invoiceValue_List;
        }

        public String getBookingBranchHubGSTNo() {
            return bookingBranchHubGSTNo;
        }

        public void setBookingBranchHubGSTNo(String bookingBranchHubGSTNo) {
            this.bookingBranchHubGSTNo = bookingBranchHubGSTNo;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
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

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIsActive() {
            return isActive;
        }

        public void setIsActive(String isActive) {
            this.isActive = isActive;
        }

        public String getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getEntryDate() {
            return entryDate;
        }

        public void setEntryDate(String entryDate) {
            this.entryDate = entryDate;
        }

        public String getLastModifyDate() {
            return lastModifyDate;
        }

        public void setLastModifyDate(String lastModifyDate) {
            this.lastModifyDate = lastModifyDate;
        }

        public String getLastModifyBy() {
            return lastModifyBy;
        }

        public void setLastModifyBy(String lastModifyBy) {
            this.lastModifyBy = lastModifyBy;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(String isEnable) {
            this.isEnable = isEnable;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsSync() {
            return isSync;
        }

        public void setIsSync(String isSync) {
            this.isSync = isSync;
        }

        public String getIsFrom() {
            return isFrom;
        }

        public void setIsFrom(String isFrom) {
            this.isFrom = isFrom;
        }

        public String getWildSearch() {
            return wildSearch;
        }

        public void setWildSearch(String wildSearch) {
            this.wildSearch = wildSearch;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getExtraInt1() {
            return extraInt1;
        }

        public void setExtraInt1(String extraInt1) {
            this.extraInt1 = extraInt1;
        }

        public String getExtraInt2() {
            return extraInt2;
        }

        public void setExtraInt2(String extraInt2) {
            this.extraInt2 = extraInt2;
        }

        public String getExtraVarChar1() {
            return extraVarChar1;
        }

        public void setExtraVarChar1(String extraVarChar1) {
            this.extraVarChar1 = extraVarChar1;
        }

        public String getExtraVarChar2() {
            return extraVarChar2;
        }

        public void setExtraVarChar2(String extraVarChar2) {
            this.extraVarChar2 = extraVarChar2;
        }

        public String getExtraDecimal1() {
            return extraDecimal1;
        }

        public void setExtraDecimal1(String extraDecimal1) {
            this.extraDecimal1 = extraDecimal1;
        }

        public String getExtraDecimal2() {
            return extraDecimal2;
        }

        public void setExtraDecimal2(String extraDecimal2) {
            this.extraDecimal2 = extraDecimal2;
        }

        public String getExtraDateTime1() {
            return extraDateTime1;
        }

        public void setExtraDateTime1(String extraDateTime1) {
            this.extraDateTime1 = extraDateTime1;
        }

        public String getExtraDateTime2() {
            return extraDateTime2;
        }

        public void setExtraDateTime2(String extraDateTime2) {
            this.extraDateTime2 = extraDateTime2;
        }

        public String getExtraBit1() {
            return extraBit1;
        }

        public void setExtraBit1(String extraBit1) {
            this.extraBit1 = extraBit1;
        }

        public String getExtraBit2() {
            return extraBit2;
        }

        public void setExtraBit2(String extraBit2) {
            this.extraBit2 = extraBit2;
        }

        public ArrayList<Dimension> getDimensionList() {
            return dimensionList;
        }

        public void setDimensionList(ArrayList<Dimension> dimensionList) {
            this.dimensionList = dimensionList;
        }
    }
    // endregion

    // region Get Docket Auto Generated Number
    public class GetDocketAutoNo {

        @SerializedName("Cid")
        @Expose
        private String companyId;

        @SerializedName("Bid")
        @Expose
        private String branchId;

        @SerializedName("Tablename")
        @Expose
        private String tableName;

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

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }
    }
    // endregion

    // region Check Duplicate Docket Number
    public class CheckDocketNoRequest {

        @SerializedName("Cid")
        @Expose
        private String companyId;

        @SerializedName("Bid")
        @Expose
        private String branchId;

        @SerializedName("DocketNo")
        @Expose
        private String docketNo;

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

        public String getDocketNo() {
            return docketNo;
        }

        public void setDocketNo(String docketNo) {
            this.docketNo = docketNo;
        }
    }
    // endregion

    // region Docket Tracking
    public class DocketTrackingRequest {

        @SerializedName("Docketno")
        @Expose
        private String docketNo;

        public String getDocketNo() {
            return docketNo;
        }

        public void setDocketNo(String docketNo) {
            this.docketNo = docketNo;
        }
    }
    // endregion

    // region Docket Tracking
    public class GetDocketDetailRequest {

        @SerializedName("Id")
        @Expose
        private String docketId;

        @SerializedName("Cid")
        @Expose
        private String companyId;

        @SerializedName("Bid")
        @Expose
        private String branchId;

        public String getDocketId() {
            return docketId;
        }

        public void setDocketId(String docketId) {
            this.docketId = docketId;
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
    }
    // endregion
}
