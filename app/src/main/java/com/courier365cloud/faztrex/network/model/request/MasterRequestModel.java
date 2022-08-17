package com.courier365cloud.faztrex.network.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterRequestModel {

    // region Get CommonListResponse
    public class GetStateRequest {

        @SerializedName("CountryId")
        @Expose
        private String countryId;

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }
    }
    // endregion

    // region Get City
    public class GetCityRequest {

        @SerializedName("StateId")
        @Expose
        private String stateId;

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }
    }
    // endregion

    // region Get Postcode
    public class GetPostcodeRequest {

        @SerializedName("CityId")
        @Expose
        private String cityId;

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }
    }
    // endregion
}
