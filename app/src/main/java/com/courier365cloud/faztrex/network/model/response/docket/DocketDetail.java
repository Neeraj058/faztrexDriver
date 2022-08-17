package com.courier365cloud.faztrex.network.model.response.docket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DocketDetail {

    @SerializedName("BookingBranchHubGSTNo")
    @Expose
    private String bookingBranchHubGSTNo;

    @SerializedName("BookingBranchHubStateCode")
    @Expose
    private String bookingBranchHubStateCode;

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("CompanyID")
    @Expose
    private String companyID;

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
    private String bookingBranchGSTNo;

    @SerializedName("DestinationStateId")
    @Expose
    private String destinationStateId;

    @SerializedName("DestinationStateName")
    @Expose
    private String destinationStateName;

    @SerializedName("DestinationId")
    @Expose
    private String destinationId;

    @SerializedName("DestinationPostCodeId")
    @Expose
    private String destinationPostCodeId;

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
    private String consignorGSTNo;

    @SerializedName("IsConsignorSEZ")
    @Expose
    private String isConsignorSEZ;

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
    private String rConsignorGSTNo;

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
    private String consigneeGSTNo;

    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;

    @SerializedName("InvoiceValue")
    @Expose
    private String invoiceValue;

    @SerializedName("PONo")
    @Expose
    private String pONo;

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
    private String oDAChargesAmount;

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
    private String sGSTPercentage;

    @SerializedName("SGSTAmount")
    @Expose
    private String sGSTAmount;

    @SerializedName("CGSTPercentage")
    @Expose
    private String cGSTPercentage;

    @SerializedName("CGSTAmount")
    @Expose
    private String cGSTAmount;

    @SerializedName("IGSTPercentage")
    @Expose
    private String iGSTPercentage;

    @SerializedName("IGSTAmount")
    @Expose
    private String iGSTAmount;

    @SerializedName("TotalGSTAmount")
    @Expose
    private String totalGSTAmount;

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
    private String fMId;

    @SerializedName("HMId1")
    @Expose
    private String hMId1;

    @SerializedName("HMId2")
    @Expose
    private String hMId2;

    @SerializedName("HMId3")
    @Expose
    private String hMId3;

    @SerializedName("HMId4")
    @Expose
    private String hMId4;

    @SerializedName("HMId5")
    @Expose
    private String hMId5;

    @SerializedName("DRSId")
    @Expose
    private String dRSId;

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

    public String getBookingBranchHubGSTNo() {
        return bookingBranchHubGSTNo;
    }

    public void setBookingBranchHubGSTNo(String bookingBranchHubGSTNo) {
        this.bookingBranchHubGSTNo = bookingBranchHubGSTNo;
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

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
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

    public String getBookingBranchGSTNo() {
        return bookingBranchGSTNo;
    }

    public void setBookingBranchGSTNo(String bookingBranchGSTNo) {
        this.bookingBranchGSTNo = bookingBranchGSTNo;
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

    public String getDestinationPostCodeId() {
        return destinationPostCodeId;
    }

    public void setDestinationPostCodeId(String destinationPostCodeId) {
        this.destinationPostCodeId = destinationPostCodeId;
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

    public String getConsignorGSTNo() {
        return consignorGSTNo;
    }

    public void setConsignorGSTNo(String consignorGSTNo) {
        this.consignorGSTNo = consignorGSTNo;
    }

    public String getIsConsignorSEZ() {
        return isConsignorSEZ;
    }

    public void setIsConsignorSEZ(String isConsignorSEZ) {
        this.isConsignorSEZ = isConsignorSEZ;
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

    public String getrConsignorGSTNo() {
        return rConsignorGSTNo;
    }

    public void setrConsignorGSTNo(String rConsignorGSTNo) {
        this.rConsignorGSTNo = rConsignorGSTNo;
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

    public String getConsigneeGSTNo() {
        return consigneeGSTNo;
    }

    public void setConsigneeGSTNo(String consigneeGSTNo) {
        this.consigneeGSTNo = consigneeGSTNo;
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

    public String getpONo() {
        return pONo;
    }

    public void setpONo(String pONo) {
        this.pONo = pONo;
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

    public String getoDAChargesAmount() {
        return oDAChargesAmount;
    }

    public void setoDAChargesAmount(String oDAChargesAmount) {
        this.oDAChargesAmount = oDAChargesAmount;
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

    public String getsGSTPercentage() {
        return sGSTPercentage;
    }

    public void setsGSTPercentage(String sGSTPercentage) {
        this.sGSTPercentage = sGSTPercentage;
    }

    public String getsGSTAmount() {
        return sGSTAmount;
    }

    public void setsGSTAmount(String sGSTAmount) {
        this.sGSTAmount = sGSTAmount;
    }

    public String getcGSTPercentage() {
        return cGSTPercentage;
    }

    public void setcGSTPercentage(String cGSTPercentage) {
        this.cGSTPercentage = cGSTPercentage;
    }

    public String getcGSTAmount() {
        return cGSTAmount;
    }

    public void setcGSTAmount(String cGSTAmount) {
        this.cGSTAmount = cGSTAmount;
    }

    public String getiGSTPercentage() {
        return iGSTPercentage;
    }

    public void setiGSTPercentage(String iGSTPercentage) {
        this.iGSTPercentage = iGSTPercentage;
    }

    public String getiGSTAmount() {
        return iGSTAmount;
    }

    public void setiGSTAmount(String iGSTAmount) {
        this.iGSTAmount = iGSTAmount;
    }

    public String getTotalGSTAmount() {
        return totalGSTAmount;
    }

    public void setTotalGSTAmount(String totalGSTAmount) {
        this.totalGSTAmount = totalGSTAmount;
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

    public String getfMId() {
        return fMId;
    }

    public void setfMId(String fMId) {
        this.fMId = fMId;
    }

    public String gethMId1() {
        return hMId1;
    }

    public void sethMId1(String hMId1) {
        this.hMId1 = hMId1;
    }

    public String gethMId2() {
        return hMId2;
    }

    public void sethMId2(String hMId2) {
        this.hMId2 = hMId2;
    }

    public String gethMId3() {
        return hMId3;
    }

    public void sethMId3(String hMId3) {
        this.hMId3 = hMId3;
    }

    public String gethMId4() {
        return hMId4;
    }

    public void sethMId4(String hMId4) {
        this.hMId4 = hMId4;
    }

    public String gethMId5() {
        return hMId5;
    }

    public void sethMId5(String hMId5) {
        this.hMId5 = hMId5;
    }

    public String getdRSId() {
        return dRSId;
    }

    public void setdRSId(String dRSId) {
        this.dRSId = dRSId;
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