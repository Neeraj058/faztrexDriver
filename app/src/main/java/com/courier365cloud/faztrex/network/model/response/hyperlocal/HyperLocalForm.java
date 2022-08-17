package com.courier365cloud.faztrex.network.model.response.hyperlocal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HyperLocalForm implements Parcelable {

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private Integer Id;

    @SerializedName(value = "No", alternate = "no")
    @Expose
    private String No;

    @SerializedName(value = "AutoNo", alternate = "autoNo")
    @Expose
    private Integer AutoNo;

    @SerializedName(value = "Prefix", alternate = "prefix")
    @Expose
    private String Prefix;

    @SerializedName(value = "Suffix", alternate = "suffix")
    @Expose
    private String Suffix;

    @SerializedName(value = "BookingDateTime", alternate = "bookingDateTime")
    @Expose
    private String BookingDateTime;

    @SerializedName(value = "PaymentTypeId", alternate = "paymentTypeId")
    @Expose
    private Integer PaymentTypeId;

    @SerializedName(value = "PaymentTypeName", alternate = "paymentTypeName")
    @Expose
    private String PaymentTypeName;

    @SerializedName(value = "AddressTypeId", alternate = "addressTypeId")
    @Expose
    private Integer AddressTypeId;

    @SerializedName(value = "AddressTypeName", alternate = "addressTypeName")
    @Expose
    private String AddressTypeName;

    @SerializedName(value = "PickupPostcode", alternate = "pickupPostcode")
    @Expose
    private String PickupPostcode;

    @SerializedName(value = "PickupAddress1", alternate = "pickupAddress1")
    @Expose
    private String PickupAddress1;

    @SerializedName(value = "PickupAddress2", alternate = "pickupAddress2")
    @Expose
    private String PickupAddress2;

    @SerializedName(value = "PickupPersonName", alternate = "pickupPersonName")
    @Expose
    private String PickupPersonName;

    @SerializedName(value = "PickupContactNo", alternate = "pickupContactNo")
    @Expose
    private String PickupContactNo;

    @SerializedName(value = "PickupLatitude", alternate = "pickupLatitude")
    @Expose
    private String pickupLatitude;

    @SerializedName(value = "PickupLongitude", alternate = "pickupLongitude")
    @Expose
    private String pickupLongitude;

    @SerializedName(value = "DeliveryPostcode", alternate = "deliveryPostcode")
    @Expose
    private String DeliveryPostcode;

    @SerializedName(value = "DeliveryAddress1", alternate = "deliveryAddress1")
    @Expose
    private String DeliveryAddress1;

    @SerializedName(value = "DeliveryAddress2", alternate = "deliveryAddress2")
    @Expose
    private String DeliveryAddress2;

    @SerializedName(value = "DeliveryPersonName", alternate = "deliveryPersonName")
    @Expose
    private String DeliveryPersonName;

    @SerializedName(value = "DeliveryContactNo", alternate = "deliveryContactNo")
    @Expose
    private String DeliveryContactNo;

    @SerializedName(value = "DeliveryLatitude", alternate = "deliveryLatitude")
    @Expose
    private String deliveryLatitude;

    @SerializedName(value = "DeliveryLongitude", alternate = "deliveryLongitude")
    @Expose
    private String deliveryLongitude;

    @SerializedName(value = "IsDocument", alternate = "isDocument")
    @Expose
    private Integer IsDocument;

    @SerializedName(value = "IsElectronic", alternate = "isElectronic")
    @Expose
    private Integer IsElectronic;

    @SerializedName(value = "IsEssential", alternate = "isEssential")
    @Expose
    private Integer IsEssential;

    @SerializedName(value = "IsMedicine", alternate = "isMedicine")
    @Expose
    private Integer IsMedicine;

    @SerializedName(value = "IsOthers", alternate = "isOthers")
    @Expose
    private Integer IsOthers;

    @SerializedName(value = "WeightTypeId", alternate = "weightTypeId")
    @Expose
    private Integer WeightTypeId;

    @SerializedName(value = "WeightTypeName", alternate = "weightTypeName")
    @Expose
    private String WeightTypeName;

    @SerializedName(value = "SpecialInstruction", alternate = "specialInstruction")
    @Expose
    private String SpecialInstruction;

    @SerializedName(value = "DistancePaymentTypeId", alternate = "distancePaymentTypeId")
    @Expose
    private Integer DistancePaymentTypeId;

    @SerializedName(value = "TotalQuantity", alternate = "totalQuantity")
    @Expose
    private Integer TotalQuantity;

    @SerializedName(value = "TotalAmount", alternate = "totalAmount")
    @Expose
    private Double TotalAmount;

    @SerializedName(value = "Sid", alternate = "sid")
    @Expose
    private Integer Sid;

    @SerializedName(value = "Cid", alternate = "cid")
    @Expose
    private Integer Cid;

    @SerializedName(value = "Bid", alternate = "bid")
    @Expose
    private Integer Bid;

    @SerializedName(value = "Uid", alternate = "uid")
    @Expose
    private Integer Uid;

    @SerializedName(value = "Hid", alternate = "hid")
    @Expose
    private Integer Hid;

    @SerializedName(value = "IsActive", alternate = "isActive")
    @Expose
    private Integer IsActive;

    @SerializedName(value = "IsDelete", alternate = "isDelete")
    @Expose
    private Integer IsDelete;

    @SerializedName(value = "EntryDate", alternate = "entryDate")
    @Expose
    private String EntryDate;

    @SerializedName(value = "LastModifyDate", alternate = "lastModifyDate")
    @Expose
    private String LastModifyDate;

    @SerializedName(value = "LastModifyBy", alternate = "lastModifyBy")
    @Expose
    private Integer LastModifyBy;

    @SerializedName(value = "IsDefault", alternate = "isDefault")
    @Expose
    private Integer IsDefault;

    @SerializedName(value = "IsEnable", alternate = "isEnable")
    @Expose
    private Integer IsEnable;

    @SerializedName(value = "Status", alternate = "status")
    @Expose
    private Integer Status;

    @SerializedName(value = "IsSync", alternate = "isSync")
    @Expose
    private Integer IsSync;

    @SerializedName(value = "IsFrom", alternate = "isFrom")
    @Expose
    private Integer IsFrom;

    @SerializedName(value = "WildSearch", alternate = "wildSearch")
    @Expose
    private String WildSearch;

    @SerializedName(value = "Notes", alternate = "notes")
    @Expose
    private String Notes;

    @SerializedName(value = "AssignId", alternate = "assignId")
    @Expose
    private Integer AssignId;

    @SerializedName(value = "Details", alternate = "details")
    @Expose
    private ArrayList<HyperLocalDetailForm> Details;

    protected HyperLocalForm(Parcel in) {
        if (in.readByte() == 0) {
            Id = null;
        } else {
            Id = in.readInt();
        }
        No = in.readString();
        if (in.readByte() == 0) {
            AutoNo = null;
        } else {
            AutoNo = in.readInt();
        }
        Prefix = in.readString();
        Suffix = in.readString();
        BookingDateTime = in.readString();
        if (in.readByte() == 0) {
            PaymentTypeId = null;
        } else {
            PaymentTypeId = in.readInt();
        }
        PaymentTypeName = in.readString();
        if (in.readByte() == 0) {
            AddressTypeId = null;
        } else {
            AddressTypeId = in.readInt();
        }
        AddressTypeName = in.readString();
        PickupPostcode = in.readString();
        PickupAddress1 = in.readString();
        PickupAddress2 = in.readString();
        PickupPersonName = in.readString();
        PickupContactNo = in.readString();
        DeliveryPostcode = in.readString();
        DeliveryAddress1 = in.readString();
        DeliveryAddress2 = in.readString();
        DeliveryPersonName = in.readString();
        DeliveryContactNo = in.readString();
        if (in.readByte() == 0) {
            IsDocument = null;
        } else {
            IsDocument = in.readInt();
        }
        if (in.readByte() == 0) {
            IsElectronic = null;
        } else {
            IsElectronic = in.readInt();
        }
        if (in.readByte() == 0) {
            IsEssential = null;
        } else {
            IsEssential = in.readInt();
        }
        if (in.readByte() == 0) {
            IsMedicine = null;
        } else {
            IsMedicine = in.readInt();
        }
        if (in.readByte() == 0) {
            IsOthers = null;
        } else {
            IsOthers = in.readInt();
        }
        if (in.readByte() == 0) {
            WeightTypeId = null;
        } else {
            WeightTypeId = in.readInt();
        }
        WeightTypeName = in.readString();
        SpecialInstruction = in.readString();
        if (in.readByte() == 0) {
            DistancePaymentTypeId = null;
        } else {
            DistancePaymentTypeId = in.readInt();
        }
        if (in.readByte() == 0) {
            TotalQuantity = null;
        } else {
            TotalQuantity = in.readInt();
        }
        if (in.readByte() == 0) {
            TotalAmount = null;
        } else {
            TotalAmount = in.readDouble();
        }
        if (in.readByte() == 0) {
            Sid = null;
        } else {
            Sid = in.readInt();
        }
        if (in.readByte() == 0) {
            Cid = null;
        } else {
            Cid = in.readInt();
        }
        if (in.readByte() == 0) {
            Bid = null;
        } else {
            Bid = in.readInt();
        }
        if (in.readByte() == 0) {
            Uid = null;
        } else {
            Uid = in.readInt();
        }
        if (in.readByte() == 0) {
            Hid = null;
        } else {
            Hid = in.readInt();
        }
        if (in.readByte() == 0) {
            IsActive = null;
        } else {
            IsActive = in.readInt();
        }
        if (in.readByte() == 0) {
            IsDelete = null;
        } else {
            IsDelete = in.readInt();
        }
        EntryDate = in.readString();
        LastModifyDate = in.readString();
        if (in.readByte() == 0) {
            LastModifyBy = null;
        } else {
            LastModifyBy = in.readInt();
        }
        if (in.readByte() == 0) {
            IsDefault = null;
        } else {
            IsDefault = in.readInt();
        }
        if (in.readByte() == 0) {
            IsEnable = null;
        } else {
            IsEnable = in.readInt();
        }
        if (in.readByte() == 0) {
            Status = null;
        } else {
            Status = in.readInt();
        }
        if (in.readByte() == 0) {
            IsSync = null;
        } else {
            IsSync = in.readInt();
        }
        if (in.readByte() == 0) {
            IsFrom = null;
        } else {
            IsFrom = in.readInt();
        }
        WildSearch = in.readString();
        Notes = in.readString();
        if (in.readByte() == 0) {
            AssignId = null;
        } else {
            AssignId = in.readInt();
        }
        Details = in.createTypedArrayList(HyperLocalDetailForm.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (Id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Id);
        }
        dest.writeString(No);
        if (AutoNo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(AutoNo);
        }
        dest.writeString(Prefix);
        dest.writeString(Suffix);
        dest.writeString(BookingDateTime);
        if (PaymentTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(PaymentTypeId);
        }
        dest.writeString(PaymentTypeName);
        if (AddressTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(AddressTypeId);
        }
        dest.writeString(AddressTypeName);
        dest.writeString(PickupPostcode);
        dest.writeString(PickupAddress1);
        dest.writeString(PickupAddress2);
        dest.writeString(PickupPersonName);
        dest.writeString(PickupContactNo);
        dest.writeString(DeliveryPostcode);
        dest.writeString(DeliveryAddress1);
        dest.writeString(DeliveryAddress2);
        dest.writeString(DeliveryPersonName);
        dest.writeString(DeliveryContactNo);
        if (IsDocument == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsDocument);
        }
        if (IsElectronic == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsElectronic);
        }
        if (IsEssential == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsEssential);
        }
        if (IsMedicine == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsMedicine);
        }
        if (IsOthers == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsOthers);
        }
        if (WeightTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(WeightTypeId);
        }
        dest.writeString(WeightTypeName);
        dest.writeString(SpecialInstruction);
        if (DistancePaymentTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(DistancePaymentTypeId);
        }
        if (TotalQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(TotalQuantity);
        }
        if (TotalAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(TotalAmount);
        }
        if (Sid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Sid);
        }
        if (Cid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Cid);
        }
        if (Bid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Bid);
        }
        if (Uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Uid);
        }
        if (Hid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Hid);
        }
        if (IsActive == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsActive);
        }
        if (IsDelete == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsDelete);
        }
        dest.writeString(EntryDate);
        dest.writeString(LastModifyDate);
        if (LastModifyBy == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(LastModifyBy);
        }
        if (IsDefault == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsDefault);
        }
        if (IsEnable == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsEnable);
        }
        if (Status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Status);
        }
        if (IsSync == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsSync);
        }
        if (IsFrom == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(IsFrom);
        }
        dest.writeString(WildSearch);
        dest.writeString(Notes);
        if (AssignId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(AssignId);
        }
        dest.writeTypedList(Details);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HyperLocalForm> CREATOR = new Creator<HyperLocalForm>() {
        @Override
        public HyperLocalForm createFromParcel(Parcel in) {
            return new HyperLocalForm(in);
        }

        @Override
        public HyperLocalForm[] newArray(int size) {
            return new HyperLocalForm[size];
        }
    };

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public Integer getAutoNo() {
        return AutoNo;
    }

    public void setAutoNo(Integer autoNo) {
        AutoNo = autoNo;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }

    public String getSuffix() {
        return Suffix;
    }

    public void setSuffix(String suffix) {
        Suffix = suffix;
    }

    public String getBookingDateTime() {
        return BookingDateTime;
    }

    public void setBookingDateTime(String bookingDateTime) {
        BookingDateTime = bookingDateTime;
    }

    public Integer getPaymentTypeId() {
        return PaymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        PaymentTypeId = paymentTypeId;
    }

    public Integer getAddressTypeId() {
        return AddressTypeId;
    }

    public void setAddressTypeId(Integer addressTypeId) {
        AddressTypeId = addressTypeId;
    }

    public String getPickupPostcode() {
        return PickupPostcode;
    }

    public void setPickupPostcode(String pickupPostcode) {
        PickupPostcode = pickupPostcode;
    }

    public String getPickupAddress1() {
        return PickupAddress1;
    }

    public void setPickupAddress1(String pickupAddress1) {
        PickupAddress1 = pickupAddress1;
    }

    public String getPickupAddress2() {
        return PickupAddress2;
    }

    public void setPickupAddress2(String pickupAddress2) {
        PickupAddress2 = pickupAddress2;
    }

    public String getPickupPersonName() {
        return PickupPersonName;
    }

    public void setPickupPersonName(String pickupPersonName) {
        PickupPersonName = pickupPersonName;
    }

    public String getPickupContactNo() {
        return PickupContactNo;
    }

    public void setPickupContactNo(String pickupContactNo) {
        PickupContactNo = pickupContactNo;
    }

    public String getDeliveryPostcode() {
        return DeliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        DeliveryPostcode = deliveryPostcode;
    }

    public String getDeliveryAddress1() {
        return DeliveryAddress1;
    }

    public void setDeliveryAddress1(String deliveryAddress1) {
        DeliveryAddress1 = deliveryAddress1;
    }

    public String getDeliveryAddress2() {
        return DeliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        DeliveryAddress2 = deliveryAddress2;
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

    public Integer getIsDocument() {
        return IsDocument;
    }

    public void setIsDocument(Integer isDocument) {
        IsDocument = isDocument;
    }

    public Integer getIsElectronic() {
        return IsElectronic;
    }

    public void setIsElectronic(Integer isElectronic) {
        IsElectronic = isElectronic;
    }

    public Integer getIsEssential() {
        return IsEssential;
    }

    public void setIsEssential(Integer isEssential) {
        IsEssential = isEssential;
    }

    public Integer getIsMedicine() {
        return IsMedicine;
    }

    public void setIsMedicine(Integer isMedicine) {
        IsMedicine = isMedicine;
    }

    public Integer getIsOthers() {
        return IsOthers;
    }

    public void setIsOthers(Integer isOthers) {
        IsOthers = isOthers;
    }

    public Integer getWeightTypeId() {
        return WeightTypeId;
    }

    public void setWeightTypeId(Integer weightTypeId) {
        WeightTypeId = weightTypeId;
    }

    public String getSpecialInstruction() {
        return SpecialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        SpecialInstruction = specialInstruction;
    }

    public Integer getDistancePaymentTypeId() {
        return DistancePaymentTypeId;
    }

    public void setDistancePaymentTypeId(Integer distancePaymentTypeId) {
        DistancePaymentTypeId = distancePaymentTypeId;
    }

    public Integer getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public Integer getSid() {
        return Sid;
    }

    public void setSid(Integer sid) {
        Sid = sid;
    }

    public Integer getCid() {
        return Cid;
    }

    public void setCid(Integer cid) {
        Cid = cid;
    }

    public Integer getBid() {
        return Bid;
    }

    public void setBid(Integer bid) {
        Bid = bid;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

    public Integer getHid() {
        return Hid;
    }

    public void setHid(Integer hid) {
        Hid = hid;
    }

    public Integer getIsActive() {
        return IsActive;
    }

    public void setIsActive(Integer isActive) {
        IsActive = isActive;
    }

    public Integer getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Integer isDelete) {
        IsDelete = isDelete;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getLastModifyDate() {
        return LastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        LastModifyDate = lastModifyDate;
    }

    public Integer getLastModifyBy() {
        return LastModifyBy;
    }

    public void setLastModifyBy(Integer lastModifyBy) {
        LastModifyBy = lastModifyBy;
    }

    public Integer getIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(Integer isDefault) {
        IsDefault = isDefault;
    }

    public Integer getIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(Integer isEnable) {
        IsEnable = isEnable;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getIsSync() {
        return IsSync;
    }

    public void setIsSync(Integer isSync) {
        IsSync = isSync;
    }

    public Integer getIsFrom() {
        return IsFrom;
    }

    public void setIsFrom(Integer isFrom) {
        IsFrom = isFrom;
    }

    public String getWildSearch() {
        return WildSearch;
    }

    public void setWildSearch(String wildSearch) {
        WildSearch = wildSearch;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public Integer getAssignId() {
        return AssignId;
    }

    public void setAssignId(Integer assignId) {
        AssignId = assignId;
    }

    public String getPaymentTypeName() {
        return PaymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        PaymentTypeName = paymentTypeName;
    }

    public String getAddressTypeName() {
        return AddressTypeName;
    }

    public void setAddressTypeName(String addressTypeName) {
        AddressTypeName = addressTypeName;
    }

    public String getWeightTypeName() {
        return WeightTypeName;
    }

    public void setWeightTypeName(String weightTypeName) {
        WeightTypeName = weightTypeName;
    }

    public ArrayList<HyperLocalDetailForm> getDetails() {
        return Details;
    }

    public void setDetails(ArrayList<HyperLocalDetailForm> details) {
        Details = details;
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

    public HyperLocalForm() {
    }

}
