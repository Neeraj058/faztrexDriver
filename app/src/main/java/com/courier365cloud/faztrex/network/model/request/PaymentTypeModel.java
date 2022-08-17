package com.courier365cloud.faztrex.network.model.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentTypeModel implements Parcelable {

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    private Integer Id;

    @SerializedName(value = "DRSId", alternate = "dRSId")
    @Expose
    private Integer DRSId;

    @SerializedName(value = "DocketId", alternate = "docketId")
    @Expose
    private Integer DocketId;

    @SerializedName(value = "PaymentTypeId", alternate = "paymentTypeId")
    @Expose
    private Integer PaymentTypeId;

    @SerializedName(value = "PaymentType", alternate = "paymentType")
    @Expose
    private String PaymentType;

    @SerializedName(value = "Amount", alternate = "amount")
    @Expose
    private Double Amount;

    @SerializedName(value = "Remarks", alternate = "remarks")
    @Expose
    private String Remarks;

    @SerializedName(value = "BankId", alternate = "bankId")
    @Expose
    private Integer BankId;

    @SerializedName(value = "Bank", alternate = "bank")
    @Expose
    private String Bank;

    @SerializedName(value = "RefOrChequeNo", alternate = "refOrChequeNo")
    @Expose
    private String RefOrChequeNo;

    @SerializedName(value = "RefOrChequeDate", alternate = "refOrChequeDate")
    @Expose
    private String RefOrChequeDate;

    @SerializedName(value = "PayeeDetail", alternate = "payeeDetail")
    @Expose
    private String PayeeDetail;

    @SerializedName(value = "Sid", alternate = "sid")
    @Expose
    private Integer Sid;

    @SerializedName(value = "Cid", alternate = "cid")
    @Expose
    private Integer Cid;

    @SerializedName(value = "Bid", alternate = "bid")
    @Expose
    private Integer Bid;

    @SerializedName(value = "Hid", alternate = "hid")
    @Expose
    private Integer Hid;

    @SerializedName(value = "Uid", alternate = "uid")
    @Expose
    private Integer Uid;

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

    public PaymentTypeModel() {
    }

    protected PaymentTypeModel(Parcel in) {
        if (in.readByte() == 0) {
            Id = null;
        } else {
            Id = in.readInt();
        }
        if (in.readByte() == 0) {
            DRSId = null;
        } else {
            DRSId = in.readInt();
        }
        if (in.readByte() == 0) {
            DocketId = null;
        } else {
            DocketId = in.readInt();
        }
        if (in.readByte() == 0) {
            PaymentTypeId = null;
        } else {
            PaymentTypeId = in.readInt();
        }
        if (in.readByte() == 0) {
            Amount = null;
        } else {
            Amount = in.readDouble();
        }
        Remarks = in.readString();
        if (in.readByte() == 0) {
            BankId = null;
        } else {
            BankId = in.readInt();
        }
        Bank = in.readString();
        PaymentType = in.readString();
        RefOrChequeNo = in.readString();
        RefOrChequeDate = in.readString();
        PayeeDetail = in.readString();
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
            Hid = null;
        } else {
            Hid = in.readInt();
        }
        if (in.readByte() == 0) {
            Uid = null;
        } else {
            Uid = in.readInt();
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
    }

    public static final Creator<PaymentTypeModel> CREATOR = new Creator<PaymentTypeModel>() {
        @Override
        public PaymentTypeModel createFromParcel(Parcel in) {
            return new PaymentTypeModel(in);
        }

        @Override
        public PaymentTypeModel[] newArray(int size) {
            return new PaymentTypeModel[size];
        }
    };

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getDRSId() {
        return DRSId;
    }

    public void setDRSId(Integer DRSId) {
        this.DRSId = DRSId;
    }

    public Integer getDocketId() {
        return DocketId;
    }

    public void setDocketId(Integer docketId) {
        DocketId = docketId;
    }

    public Integer getPaymentTypeId() {
        return PaymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        PaymentTypeId = paymentTypeId;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public Integer getBankId() {
        return BankId;
    }

    public void setBankId(Integer bankId) {
        BankId = bankId;
    }

    public String getRefOrChequeNo() {
        return RefOrChequeNo;
    }

    public void setRefOrChequeNo(String refOrChequeNo) {
        RefOrChequeNo = refOrChequeNo;
    }

    public String getRefOrChequeDate() {
        return RefOrChequeDate;
    }

    public void setRefOrChequeDate(String refOrChequeDate) {
        RefOrChequeDate = refOrChequeDate;
    }

    public String getPayeeDetail() {
        return PayeeDetail;
    }

    public void setPayeeDetail(String payeeDetail) {
        PayeeDetail = payeeDetail;
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

    public Integer getHid() {
        return Hid;
    }

    public void setHid(Integer hid) {
        Hid = hid;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
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

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (Id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Id);
        }
        if (DRSId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(DRSId);
        }
        if (DocketId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(DocketId);
        }
        if (PaymentTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(PaymentTypeId);
        }
        if (Amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Amount);
        }
        dest.writeString(Bank);
        dest.writeString(PaymentType);
        dest.writeString(Remarks);
        if (BankId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(BankId);
        }
        dest.writeString(RefOrChequeNo);
        dest.writeString(RefOrChequeDate);
        dest.writeString(PayeeDetail);
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
        if (Hid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Hid);
        }
        if (Uid == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Uid);
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
    }
}
