package com.courier365cloud.faztrex.network.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonListResponse {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Postcode")
    @Expose
    private String postcode;

    @SerializedName("PostcodeTypeId")
    @Expose
    private String postcodeTypeId;

    @SerializedName("PostcodeTypeName")
    @Expose
    private String postcodeTypeName;

    public CommonListResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CommonListResponse(String id, String name, String postcode) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcodeTypeId() {
        return postcodeTypeId;
    }

    public void setPostcodeTypeId(String postcodeTypeId) {
        this.postcodeTypeId = postcodeTypeId;
    }

    public String getPostcodeTypeName() {
        return postcodeTypeName;
    }

    public void setPostcodeTypeName(String postcodeTypeName) {
        this.postcodeTypeName = postcodeTypeName;
    }
}
