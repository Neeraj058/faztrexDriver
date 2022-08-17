package com.courier365cloud.faztrex.network.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostcodeTracking implements Parcelable {

    public static final Parcelable.Creator<PostcodeTracking> CREATOR = new Parcelable.Creator<PostcodeTracking>() {
        @Override
        public PostcodeTracking createFromParcel(Parcel source) {return new PostcodeTracking(source);}

        @Override
        public PostcodeTracking[] newArray(int size) {return new PostcodeTracking[size];}
    };
    @SerializedName("StateId")
    @Expose
    private String stateId;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("CityId")
    @Expose
    private String cityId;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("PostCodeTypeId")
    @Expose
    private String postCodeTypeId;
    @SerializedName("PostCodeTypeName")
    @Expose
    private String postCodeTypeName;

    public PostcodeTracking() {}

    private PostcodeTracking(Parcel in) {
        this.stateId = in.readString();
        this.stateName = in.readString();
        this.cityId = in.readString();
        this.cityName = in.readString();
        this.postCodeTypeId = in.readString();
        this.postCodeTypeName = in.readString();
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPostCodeTypeId() {
        return postCodeTypeId;
    }

    public void setPostCodeTypeId(String postCodeTypeId) {
        this.postCodeTypeId = postCodeTypeId;
    }

    public String getPostCodeTypeName() {
        return postCodeTypeName;
    }

    public void setPostCodeTypeName(String postCodeTypeName) {
        this.postCodeTypeName = postCodeTypeName;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stateId);
        dest.writeString(this.stateName);
        dest.writeString(this.cityId);
        dest.writeString(this.cityName);
        dest.writeString(this.postCodeTypeId);
        dest.writeString(this.postCodeTypeName);
    }
}
