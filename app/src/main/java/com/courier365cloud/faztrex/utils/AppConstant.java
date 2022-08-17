package com.courier365cloud.faztrex.utils;

import java.util.regex.Pattern;

public class AppConstant {

    // Constant for Preference File Name
    public static final String PREFERENCE_FILE_NAME = "SMCS_PREFERENCE";

    public static final String DIR_NAME = "/FazTrex/Media/Images";


    // Constant for API Response
    public static final String STATUS_FAILURE = "0";
    public static final String STATUS_SUCCESS = "1";

    // Docket Delivery Status
    public static final String STATUS_DELIVERED = "DELIVERED";

    // DRS Status
    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_CLOSED = "CLOSED";

    // DRS Docket Amount Status
    public static final String STATUS_PAID = "PAID";
    public static final String STATUS_UNPAID = "UNPAID";
    public static final String STATUS_PARTIALLY_PAID = "PARTIALLY PAID";
    public static final String STATUS_PENDING = "PENDING";

    // Display Page Record Count
    public static final int DISPLAY_RECORD_COUNT = 10;

    public static final int REQUEST_CODE_GPS_ENABLE = 2001;

    // Date Format
    public static final String API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String TRACKING_API_DATE_FORMAT = "MM/dd/yyyy hh:mm:ss aa";
    public static final String CALENDAR_DATE_FORMAT = "dd/MM/yyyy";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String IMAGE_DATE_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String DISPLAY_DATE_FORMAT_1 = "dd/MM/yyyy";
    public static final String DISPLAY_DATE_FORMAT_2 = "EEEE, MMMM dd, yyyy hh:mm aa";
    public static final String DISPLAY_DATE_FORMAT_3 = "MMMM dd, yyyy hh:mm aa";

    // String Format Pattern
    public static final String FORMAT_0_F = "%.0f";
    public static final String FORMAT_1_F = "%.1f";
    public static final String FORMAT_2_F = "%.2f";

    // Patterns
    public static final Pattern PATTERN_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PATTERN_GST = Pattern.compile("\\d{2}[A-Z]{5}\\d{4}[A-Z][A-Z\\d][Z][A-Z\\d]", Pattern.CASE_INSENSITIVE);

    // Navigation Header Title
    public static final String NAV_TITLE_DOCKET_BOOKING = "Docket Booking";
    public static final String NAV_TITLE_DOCKET = "Docket";
    public static final String NAV_TITLE_DRS = "Delivery Run Sheet";
    public static final String NAV_TITLE_DOCKET_TRACKING = "Docket Tracking";
    public static final String NAV_TITLE_POSTCODE_TRACKING = "Postcode Tracking";
    public static final String NAV_TITLE_HYPER_LOCAL_REQUEST = "Hyper Local Request";

    // Customer Type
    public static final String CUSTOMER_TYPE_CREDIT = "CREDIT";
    public static final String CUSTOMER_TYPE_RETAIL = "RETAIL";

    // Payment Type
    public static final String PAY_TYPE_PAID = "PAID";
    public static final String PAY_TYPE_TO_PAY = "TO PAY";
    public static final String PAY_TYPE_TO_BE_BILLED = "TO BE BILLED";
    public static final String PAY_TYPE_COD = "COD";
    public static final String PAY_TYPE_M_PESA = "M-PESA";

    // Dispatch Mode
    public static final String MODE_SURFACE = "SURFACE";
    public static final String MODE_AIR = "AIR";

    // Common Status
    public static final String STATUS_ACTIVE = "1";
    public static final String STATUS_DELETE = "0";
    public static final String STATUS_IS_FROM = "2";

    // Prefix and Suffix for Auto Generated Numbers
    public static final String PREFIX_DOCKET_NO = "SMCS/DKT";
    public static final String SUFFIX_DOCKET_NO = "DKT";

    // Country ID
    public static final String COUNTRY_ID = "1";

    // Spinner Type
    public static final String SP_STATE = "SP_STATE";
    public static final String SP_CITY = "SP_CITY";
    public static final String SP_POSTCODE = "SP_POSTCODE";
    public static final String SP_VERTICLE = "SP_VERTICLE";
    public static final String SP_PACKING_TYPE = "SP_PACKING_TYPE";
    public static final String SP_REASON = "SP_REASON";
    public static final String SP_DELIVERY_TYPE = "SP_DELIVERY_TYPE";
    public static final String SP_DELIVERY_STATUS = "SP_DELIVERY_STATUS";
    public static final String SP_CONSIGNOR = "SP_CONSIGNOR";
    public static final String SP_PAYMENT_TYPE = "SP_PAYMENT_TYPE";
    public static final String SP_BANK = "SP_BANK";

    // Map constants to get common API response
    public static final String KEY_STATE_ID = "KEY_STATE_ID";
    public static final String KEY_CITY_ID = "KEY_CITY_ID";

    // Docket Booking Table Name
    public static final String TABLE_NAME_DOCKET = "DocketBooking";

    // Operation Type for Access Rights
    public static final int R_VIEW = 1;
    public static final int R_EDIT = 2;
    public static final int R_DELETE = 3;
    public static final int R_ADD = 4;
    public static final int R_PRINT = 5;

    // Page Value Constant for Access Rights
    public static String PAGE_DOCKET_BOOKING = "DocketBooking";
    public static String PAGE_FRANCHISEE_MANIFEST = "FranchiseManifest";
    public static String PAGE_HUB_MANIFEST = "HubManifest";
    public static String PAGE_MANIFEST_INWARD_HUB = "ManifestInwardHub";
    public static String PAGE_LORRY_HIRE = "LorryHire";
    public static String PAGE_DRS = "DRS";
    public static String PAGE_BILL = "Bill";
    public static String PAGE_MONEY_RECEIPT = "MoneyReceipt";
    public static String PAGE_ACCOUNT_MASTER = "AccountMaster";
    public static String PAGE_FRANCHISEE_MASTER = "FranchiseMaster";
    public static String PAGE_HUB_MASTER = "HubMaster";
    public static String PAGE_RETAIL_RATE_MASTER = "RetailRateMaster";
    public static String PAGE_COMPANY_MASTER = "CompanyMaster";
    public static String PAGE_EMPLOYEE_MASTER = "EmployeeMaster";
    public static String PAGE_STATE_MASTER = "StateMaster";
    public static String PAGE_CITY_MASTER = "CityMaster";
    public static String PAGE_POSTCODE_MASTER = "PostCodeMaster";
    public static String PAGE_VEHICLE_TYPE_MASTER = "VehicleTypeMaster";
    public static String PAGE_PACKING_TYPE_MASTER = "PackingTypeMaster";
    public static String PAGE_VERTICAL_MASTER = "VerticalMaster";
    public static String PAGE_REASON_MASTER = "ReasonMaster";
    public static String PAGE_SUBGROUP_MASTER = "SubGroupMaster";
    public static String PAGE_TRANSPORTER_MASTER = "TransporterMaster";
    public static String PAGE_RIGHTSGROUP_MASTER = "RightsGroupMaster";
    public static String PAGE_SALES_DASHBOARD = "SalesDashboard";
    public static String PAGE_HUB_DASHBOARD = "HubDashboard";
    public static String PAGE_FRANCHISEE_DASHBOARD = "FranchiseDashboard";
    public static String PAGE_BILLING_DASHBOARD = "BillingDashboard";
    public static String PAGE_ROLE_MASTER = "RoleMaster";
    public static String PAGE_GRADE_MASTER = "GradeMaster";
    public static String PAGE_DEPARTMENT_MASTER = "DepartmentMaster";
    public static String PAGE_DESIGNATION_MASTER = "DesignationMaster";
    public static String PAGE_BANK_MASTER = "BankMaster";
    public static String PAGE_DOCKET_TRACKING = "DocketTracking";
    public static String PAGE_ZONE_GROUPING = "ZoneGrouping";
    public static String PAGE_ZONE_MATRIX = "ZoneMatrix";
    public static String PAGE_ZONE_TAT = "ZoneTAT";
    public static String PAGE_HYPER_LOCAL_REQUEST = "HyperLocalRequest";

    public static String POD_STATUS = "podstatus";
}
