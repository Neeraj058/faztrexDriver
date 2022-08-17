package com.courier365cloud.faztrex.network.retrofit;

public class ApiConstant {

    // API Version
    static final String API_VERSION_1_0 = "api-version: 1.0";
    static final String API_VERSION_2_0 = "api-version: 2.0";
    static final String API_VERSION_3_0 = "api-version: 3.0";

    //region Authentication
    public static final String API_LOGIN = "api/login";

    public static final String API_FORGOT_PASSWORD = "api/forgotpassword";

    public static final String API_RESET_PASSWORD = "api/resetpassword";

    public static final String API_CHANGE_PASSWORD = "api/changepassword";
    //endregion

    //region Masters
    static final String API_COUNTRY = "api/master/country";

    public static final String API_STATE = "api/master/state";

    public static final String API_CITY = "api/master/city";

    public static final String API_POST_CODE = "api/master/postcode";

    public static final String API_VERTICLE = "api/master/verticle";

    public static final String API_PACKING_TYPE = "api/master/packingtype";

    public static final String API_REASON = "api/master/reason";

    public static final String API_PAYMENT_TYPE = "api/master/paymenttype";

    public static final String API_BANK = "api/master/bank";

    public static final String API_GENERATE_OTP = "api/DRS/GetOTP";

    public static final String API_VERIFY_OTP = "api/DRS/VerifyOTP";
    //endregion

    //region Docket
    public static final String API_DOCKET_LIST = "api/docket/list";

    public static final String API_DIMENSION_CALCULATION = "api/docket/dimensiondetailcalculation";

    public static final String API_EDD_CALCULATION = "api/docket/eddcalculation";

    public static final String API_CONSIGNOR_LIST = "api/docket/consignorlistwithdetail";

    public static final String API_CALCULATE_DOCKET_CHARGES = "api/docket/docketcalculation";

    public static final String API_DOCKET_OPERATION = "api/docket/operation";

    public static final String API_AUTO_DOCKET_NUMBER = "api/docket/autono";

    public static final String API_CHECK_DUPLICATE_DOCKET_NO = "api/docket/checkduplicatedocketno";

    public static final String API_GET_DOCKET_DETAIL = "api/docket/docketdetailbyid";

    public static final String API_DOCKET_TRACKING = "api/docket/dockettracking";

    public static final String API_DOCKET_PAYMENT_DETAILS = "api/docket/paymentdetails";

    public static final String API_MPESA_PAYMENT_REQUEST = "api/docket/mpesarequest";

    public static final String API_MPESA_PAYMENT_REQUEST_STATUS = "api/docket/mpesarequeststatus";

    public static final String API_POSTCODE_TRACKING = "api/master/postcodedetail";

    public static final String API_UPDATE_DELIVERY_STATUS = "api/docket/updateddeliverystatus";

    public static final String API_NEW_DOCKET_DETAIL = "api/DRS/NewGetDocketDetail";
    //endregion

    //region DRS
    public static final String API_DRS_LIST = "api/drs/list";

    public static final String API_DRS_DETAIL = "api/drs/drsdetailfromid";

    public static final String API_UPDATE_DRS_DOCKET = "api/drs/updatedocketdetail";

    static final String API_UPLOAD_POD = "uploadpodforapp/uploaddocs";
    static final String API_UPLOAD_SIGNATURE = "uploadpodforapp/UploadSignature";
    //endregion

    //region Hyper Local Request
    private final static String API_HYPER_LOCAL_REQUEST = "api/hyperlocal/";
    public final static String API_HLR_LIST = API_HYPER_LOCAL_REQUEST + "list";
    public final static String API_HLR_MANAGE = API_HYPER_LOCAL_REQUEST + "manage";
    public final static String API_HLR_EDIT = API_HYPER_LOCAL_REQUEST + "edit";
    public final static String API_HLR_PICKED = API_HYPER_LOCAL_REQUEST + "picked";
    public final static String API_HLR_DELIVERED = API_HYPER_LOCAL_REQUEST + "delivered";
    //endregion

    //region Driver Tracking
    public static final String API_DRIVER_TRACKING = "api/tracker/manage";
    //endregion

    // region Driver Tracking
    public static final String API_DRIVER_DASHBOARD = "api/driver/dashboard/counts";
    //endregion
}
