package com.courier365cloud.faztrex.network.retrofit;

import com.courier365cloud.faztrex.model.DashboardCount;
import com.courier365cloud.faztrex.model.PodModel;
import com.courier365cloud.faztrex.network.model.request.AuthenticationRequestModel;
import com.courier365cloud.faztrex.network.model.request.CommonRequestModel;
import com.courier365cloud.faztrex.network.model.request.DocketOTP;
import com.courier365cloud.faztrex.network.model.request.DocketRequestModel;
import com.courier365cloud.faztrex.network.model.request.DriverTrackingForm;
import com.courier365cloud.faztrex.network.model.request.DrsRequestModel;
import com.courier365cloud.faztrex.network.model.request.MPesaModel;
import com.courier365cloud.faztrex.network.model.request.MasterRequestModel;
import com.courier365cloud.faztrex.network.model.request.PaymentTypeModel;
import com.courier365cloud.faztrex.network.model.response.CommonListResponse;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.ImageUploadResponse;
import com.courier365cloud.faztrex.network.model.response.PostcodeTracking;
import com.courier365cloud.faztrex.network.model.response.docket.Consignor;
import com.courier365cloud.faztrex.network.model.response.docket.DimensionDetail;
import com.courier365cloud.faztrex.network.model.response.docket.Docket;
import com.courier365cloud.faztrex.network.model.response.docket.DocketCalculation;
import com.courier365cloud.faztrex.network.model.response.docket.DocketDetail;
import com.courier365cloud.faztrex.network.model.response.docket.tracking.DocketTracking;
import com.courier365cloud.faztrex.network.model.response.drs.Drs;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDetail;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalForm;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalList;
import com.courier365cloud.faztrex.network.model.response.user.ForgotPassword;
import com.courier365cloud.faztrex.network.model.response.user.User;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    String CONTENT_TYPE_APPLICATION_JSON = "Content-Type: application/json";

    //region Authentication API

    // region Login
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_LOGIN)
    Call<CommonResponse<User>> authenticateUser(
            @Body AuthenticationRequestModel.LoginRequest requestBody
    );
    //endregion

    //region Forgot Password
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_FORGOT_PASSWORD)
    Call<CommonResponse<ForgotPassword>> sendVerificationCode(
            @Body AuthenticationRequestModel.ForgotPasswordRequest requestBody
    );
    //endregion

    //region Reset Password
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_RESET_PASSWORD)
    Call<CommonResponse> resetPassword(
            @Body AuthenticationRequestModel.ResetPasswordRequest requestBody
    );
    //endregion

    //region Change Password
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CHANGE_PASSWORD)
    Call<CommonResponse> changePassword(
            @Body AuthenticationRequestModel.ChangePasswordRequest requestBody
    );
    //endregion

    //endregion

    //region Masters API

    //region Get Country
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_COUNTRY)
    Call<JsonObject> getCountry();
    //endregion

    //region Get State
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_STATE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getState(
            @Body MasterRequestModel.GetStateRequest requestBody
    );
    //endregion

    //region Get City
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CITY)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getCity(
            @Body MasterRequestModel.GetCityRequest requestBody
    );
    //endregion

    //region Get Postcode
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_POST_CODE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPostcode(
            @Body MasterRequestModel.GetPostcodeRequest requestBody
    );
    //endregion

    //region Get Verticle
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_VERTICLE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getVerticle();

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PAYMENT_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPaymentType();

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_BANK)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getBanks();
    //endregion

    //region Get Packing Type
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_PACKING_TYPE)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getPackingType();
    //endregion

    //region Get Reason
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_REASON)
    Call<CommonResponse<ArrayList<CommonListResponse>>> getReason();
    //endregion

    //endregion

    //region Docket API

    //region Docket List
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_LIST)
    Call<CommonResponse<ArrayList<Docket>>> getDocketList(
            @Body DocketRequestModel.DocketList requestBody
    );
    //endregion

    //region Dimension Calculation API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DIMENSION_CALCULATION)
    Call<CommonResponse<DimensionDetail>> dimensionCalculation(
            @Body DocketRequestModel.DimensionCalculationRequest requestBody
    );
    //endregion

    //region EDD Calculation API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_EDD_CALCULATION)
    Call<CommonResponse> estimatedDeliveryDateCalculation(
            @Body DocketRequestModel.EDDCalculationRequest requestBody
    );
    //endregion

    //region Get Consignor List API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CONSIGNOR_LIST)
    Call<CommonResponse<ArrayList<Consignor>>> getConsignorList(
            @Body DocketRequestModel.ConsignorListRequest requestBody
    );
    //endregion

    //region Docket Calculation API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CALCULATE_DOCKET_CHARGES)
    Call<CommonResponse<DocketCalculation>> calculateDocketCharges(
            @Body DocketRequestModel.DocketCalculationRequest requestBody
    );
    //endregion

    //region Get Auto Generated Docket Number API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_AUTO_DOCKET_NUMBER)
    Call<CommonResponse> getDocketAutoNo(
            @Body DocketRequestModel.GetDocketAutoNo requestBody
    );
    //endregion

    //region Docket Operation [Save / Update] API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_OPERATION)
    Call<CommonResponse> docketOperation(
            @Body DocketRequestModel.DocketCalculationRequest requestBody
    );
    //endregion

    // region Check Duplicate Docket Number API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_CHECK_DUPLICATE_DOCKET_NO)
    Call<CommonResponse> checkDocketNo(
            @Body DocketRequestModel.CheckDocketNoRequest requestBody
    );
    // endregion

    //region Get Docket Details By Docket ID API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_GET_DOCKET_DETAIL)
    Call<CommonResponse<DocketDetail>> getDocketById(
            @Body DocketRequestModel.GetDocketDetailRequest requestBody

    );
    //endregion

    //region M Pesa Payment Request
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_MPESA_PAYMENT_REQUEST)
    Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> sendMPesaPaymentRequest(@Body MPesaModel.MPesaPaymentRequest paymentRequest);

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS)
    Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> sendMPesaPaymentRequestStatus(@Body MPesaModel.MPesaPaymentResponse paymentResponse);
    //endregion

    // region Docket Tracking API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_TRACKING)
    Call<CommonResponse<ArrayList<DocketTracking>>> getDocketTrackingDetail(
            @Body DocketRequestModel.DocketTrackingRequest requestBody
    );
    // endregion

    //region Docket Payment Operation
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DOCKET_PAYMENT_DETAILS)
    Call<CommonResponse<String>> managePaymentDetails(@Body ArrayList<PaymentTypeModel> paymentTypeModelArrayList);
    //endregion

    //endregion

    // region DRS API

    //region DRS List
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DRS_LIST)
    Call<CommonResponse<ArrayList<Drs>>> getDrsList(
            @Body DrsRequestModel.DrsList requestBody
    );
    //endregion

    //region DRS Detail [DRS Docket List]
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DRS_DETAIL)
    Call<CommonResponse<DrsDetail>> getDrsDetail(
            @Body DrsRequestModel.DrsDetail requestBody
    );
    //endregion

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_GENERATE_OTP)
    Call<CommonResponse<String>> generateOTP(@Body DocketOTP docketOTP);

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_VERIFY_OTP)
    Call<CommonResponse<String>> verifyOTP(@Body DocketOTP docketOTP);

    //region Update DRS Docket Detail
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_UPDATE_DRS_DOCKET)
    Call<CommonResponse<String>> updateDrsDocketStatus(
            @Body DrsRequestModel.UpdateDrsDocketRequest requestBody
    );
    //endregion

    // region Update DRS Docket Detail
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_UPDATE_DELIVERY_STATUS)
    Call<CommonResponse<String>> updateDeliveyStatus(@Body PodModel podModel);
    //endregion

    //region Upload POD Document
    /*@Multipart
    @POST(ApiConstant.API_UPLOAD_POD)
    Call<ImageUploadResponse> uploadPod(
            @Part MultipartBody.Part selectedFile,
            @Query("DRSId") String drsId,
            @Query("DRSDetailId") String drsDetailId,
            @Query("CollectedAmount") double collectedAmount,
            @Query("TDSAmount") double tdsAmount,
            @Query("DeliveryStatus") int deliveryStatus,
            @Query("ReasonId") String reasonId,
            @Query("UserId") String userId
    );*/

 /*   @Multipart
    @POST(ApiConstant.API_UPLOAD_POD)
    Call<ImageUploadResponse> uploadPod(
            @Part MultipartBody.Part selectedFile,
            @Part("request") RequestBody podResquestString
    );*/

    @Headers({"Accept: application/json"})
    @Multipart
    @POST(ApiConstant.API_UPLOAD_POD)
    Call<ImageUploadResponse> uploadPod(
            @Part MultipartBody.Part selectedFile,
            @Query("request") String podRequestString
    );
    // endregion

    // endregion

    //region PostCode Tracking API

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_POSTCODE_TRACKING)
    Call<CommonResponse<PostcodeTracking>> getPostcodeTrackingDetail(
            @Body JsonObject jsonObject
    );
    //endregion

    //region PostCode Tracking API
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DRIVER_TRACKING)
    Call<CommonResponse<String>> manageDriverTrackingDetails(@Body DriverTrackingForm driverTrackingForm);
    //endregion

    @Multipart
    @POST(ApiConstant.API_UPLOAD_SIGNATURE)
    Call<ImageUploadResponse> uploadSignature(
            @Part MultipartBody.Part selectedFile,
            @Query("request") String podRequestString
    );

    //region Hyper Local Request

    //region Hyper Local Request List
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_2_0})
    @POST(ApiConstant.API_HLR_LIST)
    Call<CommonResponse<ArrayList<HyperLocalList>>> getHyperLocalList(
            @Body CommonRequestModel requestBody
    );
    //endregion

    // region Hyper Local Request Edit
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_HLR_EDIT)
    Call<CommonResponse<HyperLocalForm>> getHyperLocalDetailBtyId(
            @Body CommonRequestModel requestBody
    );
    //endregion

    // region Hyper Local Picked
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_HLR_PICKED)
    Call<CommonResponse<String>> markAsPicked(
            @Body CommonRequestModel requestBody
    );
    //endregion

    // region Hyper Local Picked
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_HLR_DELIVERED)
    Call<CommonResponse<String>> markAsDelivered(
            @Body CommonRequestModel requestBody
    );
    //endregion

    //region Dashboard Counts
    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_DRIVER_DASHBOARD)
    Call<CommonResponse<DashboardCount>> getDashboardCounts(@Body CommonRequestModel commonRequestModel);
    //endregion

    @Headers({CONTENT_TYPE_APPLICATION_JSON, ApiConstant.API_VERSION_1_0})
    @POST(ApiConstant.API_NEW_DOCKET_DETAIL)
    Call<CommonResponse<ArrayList<DrsDocket>>> getNewDriverDocket(@Body HashMap<String, String> hashMap);

}