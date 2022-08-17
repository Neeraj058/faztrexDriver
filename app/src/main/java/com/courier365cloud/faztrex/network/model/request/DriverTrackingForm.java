package com.courier365cloud.faztrex.network.model.request;

import android.os.Parcel;
import android.os.Parcelable;

public class DriverTrackingForm implements Parcelable {

    private Integer Id;
    private String Employeeid;
    private String Employeename;
    private String Mobileno;
    private Double Latitude;
    private Double Longitude;
    private String Addressfromlatlong;
    private String Devicename;
    private String Deviceos;
    private String Deviceosversion;
    private String Deviceipaddress;
    private Integer Createdby;
    private String Createddate;
    private Integer Lastmodifiedby;
    private String Lastmodifieddate;
    private Integer Cid;
    private Integer Bid;
    private Integer Uid;
    private Integer Status;
    private Boolean Isactive;
    private Boolean Isdelete;
    private Integer Issync;
    private String Notes;
    private String Entrydate;
    private Integer Extraint;
    private String Extravarchar;
    private Integer Extrabit;
    private String Extradatetime;
    private Integer Isdefault;
    private Integer Isfromapp;
    private Integer HyperLocalRequestId;

    public DriverTrackingForm() {
    }

    protected DriverTrackingForm(Parcel in) {
        if (in.readByte() == 0) {
            Id = null;
        } else {
            Id = in.readInt();
        }
        Employeeid = in.readString();
        Employeename = in.readString();
        Mobileno = in.readString();
        if (in.readByte() == 0) {
            Latitude = null;
        } else {
            Latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            Longitude = null;
        } else {
            Longitude = in.readDouble();
        }
        Addressfromlatlong = in.readString();
        Devicename = in.readString();
        Deviceos = in.readString();
        Deviceosversion = in.readString();
        Deviceipaddress = in.readString();
        if (in.readByte() == 0) {
            Createdby = null;
        } else {
            Createdby = in.readInt();
        }
        Createddate = in.readString();
        if (in.readByte() == 0) {
            Lastmodifiedby = null;
        } else {
            Lastmodifiedby = in.readInt();
        }
        Lastmodifieddate = in.readString();
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
            Status = null;
        } else {
            Status = in.readInt();
        }
        byte tmpIsactive = in.readByte();
        Isactive = tmpIsactive == 0 ? null : tmpIsactive == 1;
        byte tmpIsdelete = in.readByte();
        Isdelete = tmpIsdelete == 0 ? null : tmpIsdelete == 1;
        if (in.readByte() == 0) {
            Issync = null;
        } else {
            Issync = in.readInt();
        }
        Notes = in.readString();
        Entrydate = in.readString();
        if (in.readByte() == 0) {
            Extraint = null;
        } else {
            Extraint = in.readInt();
        }
        Extravarchar = in.readString();
        if (in.readByte() == 0) {
            Extrabit = null;
        } else {
            Extrabit = in.readInt();
        }
        Extradatetime = in.readString();
        if (in.readByte() == 0) {
            Isdefault = null;
        } else {
            Isdefault = in.readInt();
        }
        if (in.readByte() == 0) {
            Isfromapp = null;
        } else {
            Isfromapp = in.readInt();
        }
    }

    public static final Creator<DriverTrackingForm> CREATOR = new Creator<DriverTrackingForm>() {
        @Override
        public DriverTrackingForm createFromParcel(Parcel in) {
            return new DriverTrackingForm(in);
        }

        @Override
        public DriverTrackingForm[] newArray(int size) {
            return new DriverTrackingForm[size];
        }
    };

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getEmployeeid() {
        return Employeeid;
    }

    public void setEmployeeid(String employeeid) {
        Employeeid = employeeid;
    }

    public String getEmployeename() {
        return Employeename;
    }

    public void setEmployeename(String employeename) {
        Employeename = employeename;
    }

    public String getMobileno() {
        return Mobileno;
    }

    public void setMobileno(String mobileno) {
        Mobileno = mobileno;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public String getAddressfromlatlong() {
        return Addressfromlatlong;
    }

    public void setAddressfromlatlong(String addressfromlatlong) {
        Addressfromlatlong = addressfromlatlong;
    }

    public String getDevicename() {
        return Devicename;
    }

    public void setDevicename(String devicename) {
        Devicename = devicename;
    }

    public String getDeviceos() {
        return Deviceos;
    }

    public void setDeviceos(String deviceos) {
        Deviceos = deviceos;
    }

    public String getDeviceosversion() {
        return Deviceosversion;
    }

    public void setDeviceosversion(String deviceosversion) {
        Deviceosversion = deviceosversion;
    }

    public String getDeviceipaddress() {
        return Deviceipaddress;
    }

    public void setDeviceipaddress(String deviceipaddress) {
        Deviceipaddress = deviceipaddress;
    }

    public Integer getCreatedby() {
        return Createdby;
    }

    public void setCreatedby(Integer createdby) {
        Createdby = createdby;
    }

    public String getCreateddate() {
        return Createddate;
    }

    public void setCreateddate(String createddate) {
        Createddate = createddate;
    }

    public Integer getLastmodifiedby() {
        return Lastmodifiedby;
    }

    public void setLastmodifiedby(Integer lastmodifiedby) {
        Lastmodifiedby = lastmodifiedby;
    }

    public String getLastmodifieddate() {
        return Lastmodifieddate;
    }

    public void setLastmodifieddate(String lastmodifieddate) {
        Lastmodifieddate = lastmodifieddate;
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

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Boolean getIsactive() {
        return Isactive;
    }

    public void setIsactive(Boolean isactive) {
        Isactive = isactive;
    }

    public Boolean getIsdelete() {
        return Isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        Isdelete = isdelete;
    }

    public Integer getIssync() {
        return Issync;
    }

    public void setIssync(Integer issync) {
        Issync = issync;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getEntrydate() {
        return Entrydate;
    }

    public void setEntrydate(String entrydate) {
        Entrydate = entrydate;
    }

    public Integer getExtraint() {
        return Extraint;
    }

    public void setExtraint(Integer extraint) {
        Extraint = extraint;
    }

    public String getExtravarchar() {
        return Extravarchar;
    }

    public void setExtravarchar(String extravarchar) {
        Extravarchar = extravarchar;
    }

    public Integer getExtrabit() {
        return Extrabit;
    }

    public void setExtrabit(Integer extrabit) {
        Extrabit = extrabit;
    }

    public String getExtradatetime() {
        return Extradatetime;
    }

    public void setExtradatetime(String extradatetime) {
        Extradatetime = extradatetime;
    }

    public Integer getIsdefault() {
        return Isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        Isdefault = isdefault;
    }

    public Integer getIsfromapp() {
        return Isfromapp;
    }

    public void setIsfromapp(Integer isfromapp) {
        Isfromapp = isfromapp;
    }

    public Integer getHyperLocalRequestId() {
        return HyperLocalRequestId;
    }

    public void setHyperLocalRequestId(Integer hyperLocalRequestId) {
        HyperLocalRequestId = hyperLocalRequestId;
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
        dest.writeString(Employeeid);
        dest.writeString(Employeename);
        dest.writeString(Mobileno);
        if (Latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Latitude);
        }
        if (Longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(Longitude);
        }
        dest.writeString(Addressfromlatlong);
        dest.writeString(Devicename);
        dest.writeString(Deviceos);
        dest.writeString(Deviceosversion);
        dest.writeString(Deviceipaddress);
        if (Createdby == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Createdby);
        }
        dest.writeString(Createddate);
        if (Lastmodifiedby == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Lastmodifiedby);
        }
        dest.writeString(Lastmodifieddate);
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
        if (Status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Status);
        }
        dest.writeByte((byte) (Isactive == null ? 0 : Isactive ? 1 : 2));
        dest.writeByte((byte) (Isdelete == null ? 0 : Isdelete ? 1 : 2));
        if (Issync == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Issync);
        }
        dest.writeString(Notes);
        dest.writeString(Entrydate);
        if (Extraint == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Extraint);
        }
        dest.writeString(Extravarchar);
        if (Extrabit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Extrabit);
        }
        dest.writeString(Extradatetime);
        if (Isdefault == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Isdefault);
        }
        if (Isfromapp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(Isfromapp);
        }
    }
}
