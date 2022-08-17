package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocketCalculation {

    @SerializedName("BookingBranchHubGSTNo")
    @Expose
    private String bookingBranchHubGstNo;

    @SerializedName("BookingBranchHubStateCode")
    @Expose
    private String bookingBranchHubStateCode;

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

    @SerializedName("EstimatedDeliveryDate")
    @Expose
    private String estimatedDeliveryDate;

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

    @SerializedName("DestinationCityName")
    @Expose
    private String destinationCityName;

    @SerializedName("VerticleTypeName")
    @Expose
    private String verticleTypeName;

    @SerializedName("PackingTypeName")
    @Expose
    private String packingTypeName;

    @SerializedName("listDocketDimension")
    @Expose
    private ArrayList<Dimension> listDocketDimension;

    public String getBookingBranchHubGstNo() {
        return bookingBranchHubGstNo;
    }

    public void setBookingBranchHubGstNo(String bookingBranchHubGstNo) {
        this.bookingBranchHubGstNo = bookingBranchHubGstNo;
    }

    public String getBookingBranchHubStateCode() {
        return bookingBranchHubStateCode;
    }

    public void setBookingBranchHubStateCode(String bookingBranchHubStateCode) {
        this.bookingBranchHubStateCode = bookingBranchHubStateCode;
    }

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

    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
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

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    public String getVerticleTypeName() {
        return verticleTypeName;
    }

    public void setVerticleTypeName(String verticleTypeName) {
        this.verticleTypeName = verticleTypeName;
    }

    public String getPackingTypeName() {
        return packingTypeName;
    }

    public void setPackingTypeName(String packingTypeName) {
        this.packingTypeName = packingTypeName;
    }

    public ArrayList<Dimension> getListDocketDimension() {
        return listDocketDimension;
    }

    public void setListDocketDimension(ArrayList<Dimension> listDocketDimension) {
        this.listDocketDimension = listDocketDimension;
    }
}
