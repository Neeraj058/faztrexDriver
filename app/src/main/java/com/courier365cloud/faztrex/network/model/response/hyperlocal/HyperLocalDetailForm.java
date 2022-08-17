package com.courier365cloud.faztrex.network.model.response.hyperlocal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HyperLocalDetailForm implements Parcelable {

    @SerializedName(value = "Id", alternate = "id")
    @Expose
    public Integer Id;

    @SerializedName(value = "HyperLocalRequestId", alternate = "hyperLocalRequestId")
    @Expose
    public Integer HyperLocalRequestId;

    @SerializedName(value = "ProductName", alternate = "productName")
    @Expose
    public String ProductName;

    @SerializedName(value = "ProductQuantity", alternate = "productQuantity")
    @Expose
    public Integer ProductQuantity;

    @SerializedName(value = "ProductAmount", alternate = "productAmount")
    @Expose
    public Double ProductAmount;

    @SerializedName(value = "Sid", alternate = "sid")
    @Expose
    public Integer Sid;

    @SerializedName(value = "Cid", alternate = "cid")
    @Expose
    public Integer Cid;

    @SerializedName(value = "Bid", alternate = "bid")
    @Expose
    public Integer Bid;

    @SerializedName(value = "Uid", alternate = "uid")
    @Expose
    public Integer Uid;

    @SerializedName(value = "Hid", alternate = "hid")
    @Expose
    public Integer Hid;

    @SerializedName(value = "IsActive", alternate = "isActive")
    @Expose
    public Integer IsActive;

    @SerializedName(value = "IsDelete", alternate = "isDelete")
    @Expose
    public Integer IsDelete;

    @SerializedName(value = "EntryDate", alternate = "entryDate")
    @Expose
    public String EntryDate;

    @SerializedName(value = "LastModifyDate", alternate = "lastModifyDate")
    @Expose
    public String LastModifyDate;

    @SerializedName(value = "LastModifyBy", alternate = "lastModifyBy")
    @Expose
    public Integer LastModifyBy;

    @SerializedName(value = "IsDefault", alternate = "isDefault")
    @Expose
    public Integer IsDefault;

    @SerializedName(value = "IsEnable", alternate = "isEnable")
    @Expose
    public Integer IsEnable;

    @SerializedName(value = "Status", alternate = "status")
    @Expose
    public Integer Status;

    @SerializedName(value = "IsSync", alternate = "isSync")
    @Expose
    public Integer IsSync;

    @SerializedName(value = "IsFrom", alternate = "isFrom")
    @Expose
    public Integer IsFrom;

    @SerializedName(value = "WildSearch", alternate = "wildSearch")
    @Expose
    public String WildSearch;

    @SerializedName(value = "Notes", alternate = "notes")
    @Expose
    public String Notes;

    @SerializedName(value = "PickupLatitude", alternate = "pickupLatitude")
    @Expose
    private String pickupLatitude;

    @SerializedName(value = "PickupLongitude", alternate = "pickupLongitude")
    @Expose
    private String pickupLongitude;

    @SerializedName(value = "DeliveryLatitude", alternate = "deliveryLatitude")
    @Expose
    private String deliveryLatitude;

    @SerializedName(value = "DeliveryLongitude", alternate = "deliveryLongitude")
    @Expose
    private String deliveryLongitude;

    public HyperLocalDetailForm() {
    }

    protected HyperLocalDetailForm(Parcel in) {
        if (in.readByte() == 0) {
            Id = null;
        } else {
            Id = in.readInt();
        }
        if (in.readByte() == 0) {
            HyperLocalRequestId = null;
        } else {
            HyperLocalRequestId = in.readInt();
        }
        ProductName = in.readString();
        if (in.readByte() == 0) {
            ProductQuantity = null;
        } else {
            ProductQuantity = in.readInt();
        }
        if (in.readByte() == 0) {
            ProductAmount = null;
        } else {
            ProductAmount = in.readDouble();
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
        pickupLatitude = in.readString();
        pickupLongitude = in.readString();
        deliveryLatitude = in.readString();
        deliveryLongitude = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (Id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Id);
        }
        if (HyperLocalRequestId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(HyperLocalRequestId);
        }
        dest.writeString(ProductName);
        if (ProductQuantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(ProductQuantity);
        }
        if (ProductAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(ProductAmount);
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
        dest.writeString(pickupLatitude);
        dest.writeString(pickupLongitude);
        dest.writeString(deliveryLatitude);
        dest.writeString(deliveryLongitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HyperLocalDetailForm> CREATOR = new Creator<HyperLocalDetailForm>() {
        @Override
        public HyperLocalDetailForm createFromParcel(Parcel in) {
            return new HyperLocalDetailForm(in);
        }

        @Override
        public HyperLocalDetailForm[] newArray(int size) {
            return new HyperLocalDetailForm[size];
        }
    };

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getHyperLocalRequestId() {
        return HyperLocalRequestId;
    }

    public void setHyperLocalRequestId(Integer hyperLocalRequestId) {
        HyperLocalRequestId = hyperLocalRequestId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        ProductQuantity = productQuantity;
    }

    public Double getProductAmount() {
        return ProductAmount;
    }

    public void setProductAmount(Double productAmount) {
        ProductAmount = productAmount;
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
