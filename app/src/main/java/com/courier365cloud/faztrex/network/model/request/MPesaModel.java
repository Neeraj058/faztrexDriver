package com.courier365cloud.faztrex.network.model.request;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MPesaModel {

    public static class MPesaPaymentRequest implements Parcelable {

        @SerializedName(value = "CustomerMPesaId", alternate = "customerMPesaId")
        @Expose
        private String CustomerMPesaId;

        @SerializedName(value = "ToPayAmount", alternate = "toPayAmount")
        @Expose
        private String ToPayAmount;

        public String getCustomerMPesaId() {
            return CustomerMPesaId;
        }

        public void setCustomerMPesaId(String customerMPesaId) {
            CustomerMPesaId = customerMPesaId;
        }

        public String getToPayAmount() {
            return ToPayAmount;
        }

        public void setToPayAmount(String toPayAmount) {
            ToPayAmount = toPayAmount;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public MPesaPaymentRequest() {
        }

        public MPesaPaymentRequest(Parcel in) {
            this.CustomerMPesaId = in.readString();
            this.ToPayAmount = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.CustomerMPesaId);
            dest.writeString(this.ToPayAmount);
        }

        public static final Parcelable.Creator<MPesaPaymentRequest> CREATOR = new Parcelable.Creator<MPesaPaymentRequest>() {
            @Override
            public MPesaPaymentRequest createFromParcel(Parcel source) {
                return new MPesaPaymentRequest(source);
            }

            @Override
            public MPesaPaymentRequest[] newArray(int size) {
                return new MPesaPaymentRequest[size];
            }
        };
    }

    public static class MPesaPaymentResponse implements Parcelable {

        @SerializedName(value = "MerchantRequestID", alternate = "merchantRequestID")
        private String MerchantRequestID;

        @SerializedName(value = "CheckoutRequestID", alternate = "checkoutRequestID")
        private String CheckoutRequestID;

        @SerializedName(value = "ResponseCode", alternate = "responseCode")
        private String ResponseCode;

        @SerializedName(value = "ResponseDescription", alternate = "responseDescription")
        private String ResponseDescription;

        @SerializedName(value = "ResultDesc", alternate = "resultDesc")
        private String ResultDesc;

        @SerializedName(value = "ResultCode", alternate = "resultCode")
        private String ResultCode;

        public MPesaPaymentResponse() {
        }

        public String getMerchantRequestID() {
            return MerchantRequestID;
        }

        public void setMerchantRequestID(String merchantRequestID) {
            MerchantRequestID = merchantRequestID;
        }

        public String getCheckoutRequestID() {
            return CheckoutRequestID;
        }

        public void setCheckoutRequestID(String checkoutRequestID) {
            CheckoutRequestID = checkoutRequestID;
        }

        public String getResponseCode() {
            return ResponseCode;
        }

        public void setResponseCode(String responseCode) {
            ResponseCode = responseCode;
        }

        public String getResponseDescription() {
            return ResponseDescription;
        }

        public void setResponseDescription(String responseDescription) {
            ResponseDescription = responseDescription;
        }

        public String getResultDesc() {
            return ResultDesc;
        }

        public void setResultDesc(String resultDesc) {
            ResultDesc = resultDesc;
        }

        public String getResultCode() {
            return ResultCode;
        }

        public void setResultCode(String resultCode) {
            ResultCode = resultCode;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public MPesaPaymentResponse(Parcel in) {
            this.MerchantRequestID = in.readString();
            this.CheckoutRequestID = in.readString();
            this.ResponseCode = in.readString();
            this.ResponseDescription = in.readString();
            this.ResultDesc = in.readString();
            this.ResultCode = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.MerchantRequestID);
            dest.writeString(this.CheckoutRequestID);
            dest.writeString(this.ResponseCode);
            dest.writeString(this.ResponseDescription);
            dest.writeString(this.ResultDesc);
            dest.writeString(this.ResultCode);
        }

        public static final Parcelable.Creator<MPesaPaymentResponse> CREATOR = new Parcelable.Creator<MPesaPaymentResponse>() {
            @Override
            public MPesaPaymentResponse createFromParcel(Parcel source) {
                return new MPesaPaymentResponse(source);
            }

            @Override
            public MPesaPaymentResponse[] newArray(int size) {
                return new MPesaPaymentResponse[size];
            }
        };
    }
}
