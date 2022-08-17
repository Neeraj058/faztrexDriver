package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import static com.cittasolutions.cittalibrary.utils.AppUtils.infoLog;
import static com.cittasolutions.cittalibrary.utils.AppUtils.toDouble;
import static com.cittasolutions.cittalibrary.utils.AppUtils.toStr;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_UPDATE_DELIVERY_STATUS;
import static com.courier365cloud.faztrex.utils.AppUtils.castToDouble;
import static com.courier365cloud.faztrex.utils.AppUtils.castToInteger;
import static com.courier365cloud.faztrex.utils.AppUtils.getCurrentDate;
import static com.courier365cloud.faztrex.utils.AppUtils.getFormattedString;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.PaymentTypeListAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityDrsdocketUpdateBinding;
import com.courier365cloud.faztrex.databinding.DialogSecureDeliveryBinding;
import com.courier365cloud.faztrex.helper.PermissionManager;
import com.courier365cloud.faztrex.listener.PermissionGrantedListener;
import com.courier365cloud.faztrex.model.PodModel;
import com.courier365cloud.faztrex.network.model.request.DocketOTP;
import com.courier365cloud.faztrex.network.model.request.DrsRequestModel;
import com.courier365cloud.faztrex.network.model.request.MPesaModel;
import com.courier365cloud.faztrex.network.model.request.PaymentTypeModel;
import com.courier365cloud.faztrex.network.model.response.CommonListResponse;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.ImageUploadResponse;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.network.retrofit.ImageUploadApiClient;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrsDocketUpdateActivity extends BaseActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        ApiListener, PaymentTypeListAdapter.OnPaymentTypeClickListener,
        PermissionGrantedListener {

    private final String TAG = this.getClass().getSimpleName();

    private final int REQUEST_CODE_CAMERA = 1001;
    private final int REQUEST_CODE_IMAGE = 1002;

    private final Context mContext = this;

    private String checkoutRequestId, mPesaId;

    //MyThread thread;

    private ActivityDrsdocketUpdateBinding binding;
    private DrsDocket drsDocket;
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            calculateAmount();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    private String drsId = "";
    private String podFilePath = "";
    private int deliveryStatusId = 0;
    private int reasonId = 0;
    private int paymentTypeId;
    private String paymentType;
    private int itemPosition = -1;

    private boolean isMoneyReceived;
    private Thread thread;
    private boolean podStatus;
    private DialogSecureDeliveryBinding dialogSecureDeliveryBinding;
    private Dialog mDialog;
    private boolean isPOD = false;
    private boolean isImageSelected = false;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drsdocket_update);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_drs_docket_delivery_status));

        // call method to start operation
        doYourWork();
        getPaymentTypeList();

        podStatus = getIntent().getBooleanExtra(AppConstant.POD_STATUS, false);

        if (podStatus) {

            hideView(binding.tvTitleDeliveryStatus);
            hideView(binding.spinnerDeliveryStatus);
            hideView(binding.tvTitlePaymentType);
            hideView(binding.spinnerPaymentType);
            hideView(binding.tnlCardNumber);
            hideView(binding.lvSubContainer1);
            hideView(binding.tnlPayee);
            hideView(binding.tvTitleUndeliveredReason);
            hideView(binding.spinnerUndeliveredReason);
            hideView(binding.containerSection1);
            hideView(binding.containerSection2);
            hideView(binding.radioGroup);
            hideView(binding.relativeMpShah);
            hideView(binding.lottieProgress);
            hideView(binding.radioUploadStatus);

            visibleView(binding.relativePodImage);
            visibleView(binding.btnUpdatePod);

        }
    }

    private void doYourWork() {

        try {

            // get intent values
            drsId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_drs_id));
            drsDocket = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_drs_docket));

            if (isEmptyString(drsId) || drsDocket == null) {
                displayLongToast(mContext, "Something went wrong!");
                finish();
            }

            // get preference data
            getPreferenceData();

            binding.edtRefDate.setText(getCurrentDate(AppConstant.CALENDAR_DATE_FORMAT));
            binding.spinnerDeliveryStatus.setOnItemSelectedListener(this);
            binding.spinnerUndeliveredReason.setOnItemSelectedListener(this);
            binding.spinnerPaymentType.setOnItemSelectedListener(this);
            binding.btnRequest.setOnClickListener(this);

            // call method to call API to get spinner data
            getSpinnerList(AppConstant.SP_DELIVERY_STATUS, null, binding.spinnerDeliveryStatus);
            getSpinnerList(AppConstant.SP_REASON, null, binding.spinnerUndeliveredReason);
            getSpinnerList(AppConstant.SP_PAYMENT_TYPE, null, binding.spinnerPaymentType);

            Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).addTextChangedListener(textWatcher);

            // set data
            setDocketData();

            //pload only POD
            binding.ivUploadPod.setOnClickListener(v -> {
                isPOD = true;
                PermissionManager permissionManager = new PermissionManager(mContext);
                permissionManager.checkMultiplePermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});
            });

            //Update Status of Docket along with POD
            binding.btnUpdatePod.setOnClickListener(v -> updateDocketStatus());

            //Only Update Status of Docket
            binding.btnUpdateDelivry.setOnClickListener(v -> {
                if (isImageSelected)
                    uploadImage();
                else UpdateDeliveryStatus();
            });

//            Upload image for Undelivered
            binding.relativeUploadImage.setOnClickListener(v -> {
                isPOD = false;
                PermissionManager permissionManager = new PermissionManager(mContext);
                permissionManager.checkMultiplePermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE});
            });

            thread = new Thread(() -> {
                while (!isMoneyReceived) {
                    try {
                        checkStatusOfPayment(checkoutRequestId);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton rb = group.findViewById(checkedId);

            if (rb.getText().toString().equals("POD Upload")) {

                hideView(binding.btnUpdateDelivry);
                visibleView(binding.btnUpdatePod);
                visibleView(binding.relativePodImage);

            } else {

                hideView(binding.btnUpdatePod);
                hideView(binding.relativePodImage);
                visibleView(binding.btnUpdateDelivry);

            }
        });
    }

    private void uploadImage() {
        try {
            if (NetworkUtils.isConnected(mContext))
                uploadPod();
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           /* case R.id.btnSaveItems:
                savePaymentItemsServer();*/

            case R.id.btnRequest:
                makeRequestForPayment();
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void setDocketData() {

        try {

            if (drsDocket != null) {

                String freightAmount = getStringValue(drsDocket.getTotalAmount());
                String tdsAmount = getStringValue(drsDocket.getTdsAmount());
                String invoiceAmount = (toDouble(freightAmount) + toDouble(tdsAmount)) + "";

                Objects.requireNonNull(binding.tnlFreightAmount.getEditText()).setText(getStringValue(drsDocket.getTotalAmount()).contains(",") ? getStringValue(drsDocket.getTotalAmount()).replace(",", "") : getStringValue(drsDocket.getTotalAmount()));
                Objects.requireNonNull(binding.tnlTdsAmount.getEditText()).setText(getStringValue(drsDocket.getTdsAmount()).contains(",") ? getStringValue(drsDocket.getTdsAmount()).replace(",", "") : getStringValue(drsDocket.getTdsAmount()));

                Objects.requireNonNull(binding.tnlBalanceAmount.getEditText()).setText(getStringValue(drsDocket.getBalanceAmount()).contains(",") ? getStringValue(drsDocket.getBalanceAmount()).replace(",", "") : getStringValue(drsDocket.getBalanceAmount()));

                Objects.requireNonNull(binding.tnlAmountStatus.getEditText()).setText(getStringValue(/*drsDocket.getAmountStatus()*/drsDocket.getPaymentType()).toUpperCase());

                Objects.requireNonNull(binding.tnlInvoiceAmount.getEditText()).setText(invoiceAmount);
                Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).setText(getStringValue(drsDocket.getCollectedAmount()).contains(",") ? getStringValue(drsDocket.getCollectedAmount()).replace(",", "") : getStringValue(drsDocket.getCollectedAmount()));

                /*if (!drsDocket.getCustomerType().equalsIgnoreCase("CREDIT")) {
                    if (toDouble(drsDocket.getCollectedAmount()) <= 0) {
                        double collectedAmount = castToDouble(binding.tnlFreightAmount.getEditText().getText().toString()) + castToDouble(binding.tnlInvoiceAmount.getEditText().getText().toString());
                        Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).setText(String.valueOf(collectedAmount));
                    } else {
                        Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).setText(getStringValue(drsDocket.getCollectedAmount()).contains(",") ? getStringValue(drsDocket.getCollectedAmount()).replace(",", "") : getStringValue(drsDocket.getCollectedAmount()));
                    }
                } else*/

//                TODO: Its commented based on client feedback
//                binding.tnlCollectedAmount.getEditText().setText("0.00");

                calculateAmount();

                mPesaId = getStringValue(drsDocket.getConsigneeMobileNo());
                if (mPesaId.startsWith("+"))
                    mPesaId = mPesaId.replace("+", "");
//                if (!mPesaId.startsWith("254"))
//                    mPesaId = "254" + mPesaId;

                binding.edtMPesaId.setText(mPesaId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.spinner_delivery_status:

                deliveryStatusId = castToInteger(deliveryStatusMasterList.get(position).getId());
//                Log.d(TAG, "deliveryStatusId: " + deliveryStatusId);
                reasonId = 0;
                binding.spinnerUndeliveredReason.setSelection(reasonId);
                hideView(binding.relativeMpShah);

                /*if (deliveryStatusId == 2) {

                    visibleView(binding.spinnerUndeliveredReason);
                    visibleView(binding.tvTitleUndeliveredReason);
                    hideView(binding.ivUploadPod);
                    hideView(binding.ivPod);
                    hideView(binding.tvTitleUploadMessage);

                } else {

                    hideView(binding.spinnerUndeliveredReason);
                    hideView(binding.tvTitleUndeliveredReason);
                    if (isEmptyString(podFilePath)) {
                        visibleView(binding.ivUploadPod);
                        visibleView(binding.tvTitleUploadMessage);
                        hideView(binding.ivPod);
                    } else {
                        visibleView(binding.ivPod);
                        hideView(binding.ivUploadPod);
                        hideView(binding.tvTitleUploadMessage);
                    }
                }*/

                if (deliveryStatusId == 2) {

                    //Delivered -1 & UnDelivered - 2
                    //hideView(binding.ivUploadPod);
                    //hideView(binding.ivPod);
                    //hideView(binding.tvTitleUploadMessage);

                    hideView(binding.relativePodImage);
                    hideView(binding.tvTitlePaymentType);
                    hideView(binding.spinnerPaymentType);
                    hideView(binding.lvSubContainer1);
                    hideView(binding.tnlPayee);
                    hideView(binding.radioGroup);

                    visibleView(binding.tvTitleUndeliveredReason);
                    visibleView(binding.spinnerUndeliveredReason);
                    visibleView(binding.btnUpdateDelivry);
                    visibleView(binding.relativeUploadImage);
                } else if (deliveryStatusId == 1 && (drsDocket.getPaymentType().equalsIgnoreCase(AppConstant.PAY_TYPE_COD) |
                        drsDocket.getPaymentType().equalsIgnoreCase(AppConstant.PAY_TYPE_M_PESA))) {
                    isImageSelected = false;
                    // visibleView(binding.radioGroup);
                    //visibleView(binding.btnUpdate);

                    hideView(binding.tvTitleUndeliveredReason);
                    hideView(binding.spinnerUndeliveredReason);
                    hideView(binding.relativePodImage);
                    hideView(binding.relativeUploadImage);

                    //visibleView(binding.lvSubContainer1);

                    visibleView(binding.tnlPayee);
                    visibleView(binding.tvTitlePaymentType);
                    visibleView(binding.spinnerPaymentType);

                } else if (deliveryStatusId == 1 && !drsDocket.getPaymentType().equalsIgnoreCase(AppConstant.PAY_TYPE_COD)) {
//              Delivery Status 'Delivered' selected
                    hideView(binding.tvTitleUndeliveredReason);
                    hideView(binding.spinnerUndeliveredReason);

                    isImageSelected = false;
                    hideView(binding.relativeUploadImage);
                    hideView(binding.tvTitlePaymentType);
                    hideView(binding.spinnerPaymentType);

                    //visibleView(binding.lvSubContainer1);
                    hideView(binding.tnlPayee);

                    visibleView(binding.relativePodImage);
                    visibleView(binding.radioGroup);
                    visibleView(binding.btnUpdatePod);

                    if (binding.radioUploadStatus.isChecked()) {
                        hideView(binding.btnUpdatePod);
                        hideView(binding.relativePodImage);
                        visibleView(binding.btnUpdateDelivry);
                    }
                } else {
                    isImageSelected = false;
                    hideView(binding.relativeUploadImage);
                    hideView(binding.tvTitleUndeliveredReason);
                    hideView(binding.spinnerUndeliveredReason);
                    hideView(binding.tvTitlePaymentType);
                    hideView(binding.spinnerPaymentType);
                    hideView(binding.lvSubContainer1);
                    hideView(binding.tnlPayee);
                    hideView(binding.relativePodImage);
                    hideView(binding.radioGroup);
                }

//                if (deliveryStatusId == 1) showSecureDeliveryDialog();

                if (isEmptyString(podFilePath)) {
                    visibleView(binding.ivUploadPod);
                    visibleView(binding.tvTitleUploadMessage);
                    hideView(binding.ivPod);
                } else {
                    visibleView(binding.ivPod);
                    hideView(binding.ivUploadPod);
                    hideView(binding.tvTitleUploadMessage);
                }
                break;

            case R.id.spinner_undelivered_reason:
                reasonId = castToInteger(reasonMasterList.get(position).getId());
                break;

            case R.id.spinner_payment_type:
                paymentTypeId = castToInteger(paymentTypeMasterList.get(position).getId());
                paymentType = paymentTypeMasterList.get(position).getName();

                switch (paymentTypeId) {

                    case 1:// Cash
                        visibleView(binding.relativePodImage);
                        visibleView(binding.btnUpdatePod);
                        hideView(binding.lvSubContainer1);
                        hideView(binding.relativeMpShah);

                        visibleView(binding.radioGroup);
                        visibleView(binding.btnUpdatePod);

                        if (binding.radioUploadStatus.isChecked()) {
                            hideView(binding.btnUpdatePod);
                            hideView(binding.relativePodImage);
                            visibleView(binding.btnUpdateDelivry);
                        }
                        break;

                    case 2://Debit/Credit
                    case 4:

                        hideView(binding.relativeMpShah);

                        visibleView(binding.lvSubContainer1);
                        visibleView(binding.radioGroup);
                        visibleView(binding.relativePodImage);
                        visibleView(binding.btnUpdatePod);

                        binding.edtRefNo.setEnabled(true);

                        if (binding.radioUploadStatus.isChecked()) {
                            hideView(binding.btnUpdatePod);
                            hideView(binding.relativePodImage);
                            visibleView(binding.btnUpdateDelivry);
                        }
                        break;

                    case 3: //MP Shah

                        hideView(binding.radioGroup);
                        hideView(binding.relativePodImage);
                        hideView(binding.btnUpdatePod);
                        hideView(binding.btnUpdateDelivry);

                        //  binding.btnSubmit.setVisibility(View.GONE);
                        binding.lottieProgress.setVisibility(View.GONE);
                        binding.relativePodImage.setVisibility(View.GONE);

                        visibleView(binding.lvSubContainer1);
                        binding.edtRefNo.setEnabled(false);
                        visibleView(binding.relativeMpShah);
                        break;
                }
                break;
        }
    }

//    private void showSecureDeliveryDialog() {
//        dialogSecureDeliveryBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_secure_delivery, null, false);
//        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
//
//        mDialog = new Dialog(mContext);
//        mDialog.setCancelable(false);
//        mDialog.setCanceledOnTouchOutside(false);
//        mDialog.setContentView(dialogSecureDeliveryBinding.getRoot());
//        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        mDialog.show();
//        mDialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        dialogSecureDeliveryBinding.docketNumber.setText(mContext.getString(R.string.docket_number_string, drsDocket.getDocketNo()));
//        dialogSecureDeliveryBinding.btnGenerateOtp.setOnClickListener(v -> {
//            try {
//                startProgressDialog(this, false);
//                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
////                DocketOTP docketOTP = new DocketOTP();
////                docketOTP.setId(drsDocket.getDocketId());
//          //      Call<CommonResponse<String>> call = apiService.generateOTP(docketOTP);
//             //   ApiManager.callRetrofit(call, ApiConstant.API_GENERATE_OTP, this, false);
//            } catch (Exception e) {
//                e.printStackTrace();
//                printErrorLog(TAG, e.getLocalizedMessage());
//            }
//        });
//
//        dialogSecureDeliveryBinding.tnlOtp.getEditText().addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                dialogSecureDeliveryBinding.btnVerifyOtp.setEnabled(s.length() > 0);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        dialogSecureDeliveryBinding.btnVerifyOtp.setOnClickListener(v -> {
//            String otp = dialogSecureDeliveryBinding.tnlOtp.getEditText().getText().toString();
//            if (otp.isEmpty()) {
//                dialogSecureDeliveryBinding.tnlOtp.setError("Please enter OTP");
//                return;
//            }
//            hideKeyboard();
//
//            dialogSecureDeliveryBinding.tnlOtp.setError(null);
//            try {
//                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
//                DocketOTP docketOTP = new DocketOTP();
//                docketOTP.setId(drsDocket.getDocketId());
//                docketOTP.setOtp(otp);
//                Call<CommonResponse<String>> call = apiService.verifyOTP(docketOTP);
//                ApiManager.callRetrofit(call, ApiConstant.API_VERIFY_OTP, this, false);
//            } catch (Exception e) {
//                e.printStackTrace();
//                printErrorLog(TAG, e.getLocalizedMessage());
//            }
//        });
//
//        dialogSecureDeliveryBinding.ivClose.setOnClickListener(v -> {
//            hideKeyboard();
//            mDialog.dismiss();
//            binding.spinnerDeliveryStatus.setSelection(0);
//        });
//    }

    private void hideKeyboard() {
        Objects.requireNonNull(dialogSecureDeliveryBinding.tnlOtp.getEditText()).clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(dialogSecureDeliveryBinding.tnlOtp.getEditText().getWindowToken(), 0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onSinglePermissionGranted(String permissionName) {
    }

    @Override
    public void onMultiplePermissionGranted(String[] permissions) {
        openCameraIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
                    if (!isEmptyString(podFilePath)) {

                        binding.ivUploadPod.setVisibility(View.GONE);
                        binding.tvTitleUploadMessage.setVisibility(View.GONE);
                        binding.ivPod.setVisibility(View.VISIBLE);
                    }

                    Glide.with(mContext)
                            .load(podFilePath)
                            .into(binding.ivPod);
                }
                break;

            case REQUEST_CODE_IMAGE:
                if (resultCode == RESULT_OK) {
                    if (!isEmptyString(podFilePath)) {
                        binding.ivImagePod.setVisibility(View.GONE);
                        binding.tvTitleImageMessage.setVisibility(View.GONE);
                        binding.ivImage.setVisibility(View.VISIBLE);
                    }
                    isImageSelected = true;
                    Glide.with(mContext)
                            .load(podFilePath)
                            .into(binding.ivImage);
                }
                break;

        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        super.onApiSuccess(endPointName, responseBody);

        switch (endPointName) {

            case ApiConstant.API_UPDATE_DRS_DOCKET:

                CommonResponse<String> commonResponse = (CommonResponse<String>) responseBody;
                processDrsResponse(commonResponse);
                break;

            case ApiConstant.API_PAYMENT_TYPE:
                CommonResponse<ArrayList<CommonListResponse>> paymentTypeListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processPaymentTypeListResponse(paymentTypeListResponse);
                break;

            case API_MPESA_PAYMENT_REQUEST:
                CommonResponse<MPesaModel.MPesaPaymentResponse> paymentRequest = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processPaymentRequestResponse(paymentRequest);
                break;

            case API_MPESA_PAYMENT_REQUEST_STATUS:
                CommonResponse<MPesaModel.MPesaPaymentResponse> paymentResponse = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processPaymentRequestStatusResponse(paymentResponse);
                break;

            case API_UPDATE_DELIVERY_STATUS:
                CommonResponse<String> deliveryStatusUpdateRes = (CommonResponse<String>) responseBody;
                processDeliveryStatus(deliveryStatusUpdateRes);
                break;

            case ApiConstant.API_GENERATE_OTP:
                CommonResponse<String> generateOTPResponse = (CommonResponse<String>) responseBody;
                displayShortToast(this, generateOTPResponse.getMessage());
                if (generateOTPResponse.getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                    dialogSecureDeliveryBinding.btnVerifyOtp.setEnabled(true);
                }
                break;

            case ApiConstant.API_VERIFY_OTP:
                CommonResponse<String> verifyOTPResponse = (CommonResponse<String>) responseBody;
                displayShortToast(this, verifyOTPResponse.getMessage());
                if (verifyOTPResponse.getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                    mDialog.dismiss();
                }
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        super.onApiError(endPointName, errorMessage);

        printErrorLog(TAG, errorMessage);

        switch (endPointName) {

            case ApiConstant.API_UPDATE_DRS_DOCKET:
            case ApiConstant.API_PAYMENT_TYPE:
            case API_MPESA_PAYMENT_REQUEST:
            case API_UPDATE_DELIVERY_STATUS:
            case API_MPESA_PAYMENT_REQUEST_STATUS:
            case ApiConstant.API_GENERATE_OTP:
            case ApiConstant.API_VERIFY_OTP:
                displayShortToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        super.onApiFailure(endPointName, failureMessage);

        printErrorLog(TAG, failureMessage);

        switch (endPointName) {
            case ApiConstant.API_UPDATE_DRS_DOCKET:
            case ApiConstant.API_PAYMENT_TYPE:
            case API_MPESA_PAYMENT_REQUEST:
            case API_UPDATE_DELIVERY_STATUS:
            case API_MPESA_PAYMENT_REQUEST_STATUS:
            case ApiConstant.API_GENERATE_OTP:
            case ApiConstant.API_VERIFY_OTP:
                displayShortToast(mContext, failureMessage);
                break;
        }
    }

    private void processPaymentTypeListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {
        paymentTypeMasterList = new ArrayList<>();
        paymentTypeMasterList = response.getData();
        setSpinnerHeader(AppConstant.SP_PAYMENT_TYPE);
    }

    private void processDeliveryStatus(CommonResponse<String> response) {

        //displayLongToast(mContext,podModel.getDeliveryStatus().toString());
        try {

            /*binding.lottieProgress.setVisibility(View.VISIBLE);*/

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    //String dataMsg = response.getData();
                    //  displayLongToast(mContext, dataMsg);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Status updated!");
                    builder.setMessage("Status updated successfully");
                    builder.setPositiveButton("OK", (dialog, which) -> {
                        dialog.dismiss();
                       /* Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();*/
                        startDriverDocketAgain();
                    });
                    builder.show();
                }
            } else
                displayLongToast(mContext, "Something went wrong!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processPaymentRequestResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {

        try {

            binding.lottieProgress.setVisibility(View.VISIBLE);

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    checkoutRequestId = data.getCheckoutRequestID();

                    //thread = new MyThread(this, checkoutRequestId);

                    thread.interrupt();
                    thread.start();

                    displayLongToast(mContext, "Please wait! \nWaiting for payment status.");
                    hideView(binding.relativeMpShah);

                } else {
                    binding.lottieProgress.setVisibility(View.GONE);
                    displayLongToast(mContext, response.getMessage());
                }

            } else {
                binding.lottieProgress.setVisibility(View.GONE);
                displayLongToast(mContext, getString(R.string.err_msg_api_response_failure));
            }

        } catch (Exception e) {
            visibleView(binding.relativeMpShah);
            binding.lottieProgress.setVisibility(View.GONE);
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void processPaymentRequestStatusResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {

        try {

            stopProgressDialog();

            if (response != null) {

                binding.lottieProgress.setVisibility(View.VISIBLE);

                if (response.getStatus().equalsIgnoreCase("1")) {

                    //thread.t.interrupt();

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    isMoneyReceived = true;

                    /*if (data.getResultCode().equalsIgnoreCase("0")) {

                        Objects.requireNonNull(binding.tnlRefNo.getEditText()).setText(checkoutRequestId);

                        binding.lottieProgress.setVisibility(View.GONE);
                        hideView(binding.relativeMpShah);

                        visibleView(binding.radioGroup);
                        visibleView(binding.relativePodImage);
                        visibleView(binding.btnUpdatePod);

                        if (binding.radioUploadStatus.isChecked()) {

                            hideView(binding.btnUpdatePod);
                            hideView(binding.relativePodImage);
                            visibleView(binding.btnUpdateDelivry);
                        }

                    } else {

                        visibleView(binding.relativeMpShah);
                        binding.lottieProgress.setVisibility(View.GONE);

                        hideView(binding.radioGroup);
                        hideView(binding.relativePodImage);
                        hideView(binding.btnUpdatePod);

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("Transaction error!");
                        builder.setMessage(data.getResultDesc());
                        builder.setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        });
                        builder.show();
                    }*/
                    if (data.getResponseCode().equals("0")) {

                        Objects.requireNonNull(binding.tnlRefNo.getEditText()).setText(checkoutRequestId);

                        isMoneyReceived = true;
                        binding.lottieProgress.setVisibility(View.GONE);
                        hideView(binding.relativeMpShah);

                        visibleView(binding.radioGroup);
                        visibleView(binding.relativePodImage);
                        visibleView(binding.btnUpdatePod);

                        if (binding.radioUploadStatus.isChecked()) {

                            hideView(binding.btnUpdatePod);
                            hideView(binding.relativePodImage);
                            visibleView(binding.btnUpdateDelivry);
                        }

                        //displayLongToast(mContext, data.getResponseDescription());
                    }
                }

            } else
                displayLongToast(mContext, "Something went wrong!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerHeader(String spinnerType) {

        switch (spinnerType) {

            case AppConstant.SP_PAYMENT_TYPE:

                if (paymentTypeMasterList == null)
                    paymentTypeMasterList = new ArrayList<>();

                // paymentTypeMasterList.add(0, new CommonListResponse("0", getResources().getString(R.string.spinner_payment_type_header_title)));
                bindSpinnerData(AppConstant.SP_PAYMENT_TYPE, binding.spinnerPaymentType, paymentTypeMasterList, paymentTypeId);
                break;

        }
    }

    private void getPaymentTypeList() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                BaseActivity.startProgressDialog((Activity) mContext, false);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getPaymentType();

                ApiManager.callRetrofit(call, ApiConstant.API_PAYMENT_TYPE, this, false);

            } else
                displayInternetToastMessage(mContext);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openCameraIntent() {

        try {

            Intent capturePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (capturePhotoIntent.resolveActivity(getPackageManager()) != null) {

                File imageFile = getImageFile();

                if (imageFile != null) {
                    Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", imageFile);
                    capturePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(capturePhotoIntent, isPOD ? REQUEST_CODE_CAMERA : REQUEST_CODE_IMAGE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private File getImageFile() {

        File tmpFile = null;

        try {

            String fileName = getCurrentDate(AppConstant.IMAGE_DATE_FORMAT) + ".jpg";

            //File tmpDir = new File(Environment.getExternalStorageDirectory() + dirname);
            File tmpDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

            tmpFile = new File(tmpDir, fileName);
            podFilePath = tmpFile.getAbsolutePath();

            return tmpFile;

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

        return tmpFile;
    }

    @SuppressLint("SetTextI18n")
    private void calculateAmount() {
        try {
            double totalInvoiceAmount = castToDouble(getTrimString(binding.tnlInvoiceAmount));
            double collectedAmount = castToDouble(getTrimString(binding.tnlCollectedAmount));

            double balanceAmount = totalInvoiceAmount - collectedAmount;

            Objects.requireNonNull(binding.tnlBalanceAmount.getEditText()).setText(getFormattedString(String.valueOf(balanceAmount), AppConstant.FORMAT_2_F));

            if (collectedAmount > totalInvoiceAmount) {
                collectedAmount = 0;
                Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).setText(toStr(collectedAmount));
                displayShortToast(mContext, "Collected amount can not be more than Invoice amount");
            }

            if (!drsDocket.getCustomerType().equalsIgnoreCase("CREDIT"))
                Objects.requireNonNull(binding.tnlAmountStatus.getEditText()).setText((balanceAmount /*==*/ >= 0 && collectedAmount == totalInvoiceAmount) ? AppConstant.STATUS_PAID : AppConstant.STATUS_UNPAID);
            else
                Objects.requireNonNull(binding.tnlAmountStatus.getEditText()).setText(AppConstant.STATUS_PENDING);

            /*if (balanceAmount < 0) {
                collectedAmount = (totalFreightAmount + totalInvoiceAmount);
                tdsAmount = 0;
                Objects.requireNonNull(binding.tnlTdsAmount.getEditText()).setText(toStr(tdsAmount));
                Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).setText(toStr(collectedAmount));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void updateDocketStatus() {

        try {

            if (podStatus) {
                uploadImage();
            } else {

                try {
                   // Remove Docket update OTP POPUP
                    //changed by Neeraj

//                    if (checkFormValidation() {

                        if (NetworkUtils.isConnected(mContext)) {

                            if (toDouble(Objects.requireNonNull(binding.tnlBalanceAmount.getEditText()).getText().toString()) < 0) {
                                displayShortToast(mContext, "Balance amount can not be negative");
                                return;
                            }

                            if (deliveryStatusId == 2) {

                                if (reasonId <= 0) {
                                    displayShortToast(mContext, "Select Undelivered Reason");
                                    return;
                                }

                                // start progress indicator
                                startProgressDialog(this, false);

                                // prepare request body
                                DrsRequestModel.UpdateDrsDocketRequest request = new DrsRequestModel().new UpdateDrsDocketRequest();

                                request.setCompanyId(prefUserModel.getCompanyId());
                                request.setBranchId(prefUserModel.getBranchId());
                                request.setUserId(prefUserModel.getId());
                                request.setDrsId(drsId);
                                request.setDrsDetailId(drsDocket.getDrsDetailId());
                                request.setCollectedAmount(getTrimString(binding.tnlCollectedAmount));
                                request.setTdsAmount(getTrimString(binding.tnlTdsAmount));
                                request.setDeliveryStatus(String.valueOf(deliveryStatusId));
                                request.setReasonId(String.valueOf(reasonId));

                                String requestString = new Gson().toJson(request);
                                infoLog(requestString);

                                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                                Call<CommonResponse<String>> call = apiService.updateDrsDocketStatus(request);

                                // call API
                                ApiManager.callRetrofit(call, ApiConstant.API_UPDATE_DRS_DOCKET, this, false);

                            } else
                                uploadPod();
                        } else
                            displayInternetToastMessage(mContext);
                //    }

                } catch (Exception e) {
                    e.printStackTrace();
                    printErrorLog(TAG, e.getLocalizedMessage());
                }
            }

            /*if (checkFormValidation()) {

                if (NetworkUtils.isConnected(mContext)) {

                    if (deliveryStatusId == 2) {

                        // start progress indicator
                        startProgressDialog(this, false);

                        // prepare request body
                        DrsRequestModel.UpdateDrsDocketRequest request = new DrsRequestModel().new UpdateDrsDocketRequest();

                        request.setCompanyId(prefUserModel.getCompanyId());
                        request.setBranchId(prefUserModel.getBranchId());
                        request.setUserId(prefUserModel.getId());
                        request.setDrsId(drsId);
                        request.setDrsDetailId(drsDocket.getDrsDetailId());
                        request.setCollectedAmount(getTrimString(binding.tnlCollectedAmount));
                        request.setTdsAmount(getTrimString(binding.tnlTdsAmount));
                        request.setDeliveryStatus(String.valueOf(deliveryStatusId));
                        request.setReasonId(String.valueOf(reasonId));

                        ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                        Call<CommonResponse> call = apiService.updateDrsDocketStatus(request);

                        // call API
                        ApiManager.callRetrofit(call, ApiConstant.API_UPDATE_DRS_DOCKET, this, false);

                    } else {

                        uploadPod();

                    }

                } else {
                    displayInternetToastMessage(mContext);
                }
            }*/

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void UpdateDeliveryStatus() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                if (toDouble(Objects.requireNonNull(binding.tnlBalanceAmount.getEditText()).getText().toString()) < 0) {
                    displayShortToast(mContext, "Balance amount can not be negative");
                    return;
                }

                if (deliveryStatusId == 2 && reasonId <= 0) {
                    displayShortToast(mContext, "Select Undelivered Reason");
                    return;
                }

                // start progress indicator
                startProgressDialog(this, false);

                PodModel podModel = new PodModel();
                podModel.setDRSId(castToInteger(drsId));
                podModel.setDRSDetailId(castToInteger(drsDocket.getDrsDetailId()));
                podModel.setCollectedAmount(castToDouble(Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).getText().toString()));
                podModel.setTDSAmount(castToDouble(Objects.requireNonNull(binding.tnlTdsAmount.getEditText()).getText().toString()));
                podModel.setDeliveryStatus(deliveryStatusId);//undelivered
                if (deliveryStatusId != 1)
                    podModel.setReasonId(reasonId);
                podModel.setReferenceNo(Objects.requireNonNull(binding.tnlRefNo.getEditText()).getText().toString());
                podModel.setUserId(castToInteger(prefUserModel.getId()));
                podModel.setPaymentTypeId(paymentTypeId);
                podModel.setDocketId(castToInteger(drsDocket.getDocketId()));

                String request = new Gson().toJson(podModel);
                infoLog(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<String>> call = apiService.updateDeliveyStatus(podModel);

                // call API
                ApiManager.callRetrofit(call, API_UPDATE_DELIVERY_STATUS, this, false);

               /* if (deliveryStatusId == 1) {


                } else {

                    PodModel podModel = new PodModel();
                    podModel.setDRSId(castToInteger(drsId));
                    podModel.setDRSDetailId(castToInteger(drsDocket.getDrsDetailId()));
                    podModel.setCollectedAmount(castToDouble(Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).getText().toString()));
                    podModel.setTDSAmount(castToDouble(Objects.requireNonNull(binding.tnlTdsAmount.getEditText()).getText().toString()));
                    podModel.setDeliveryStatus(2);//undelivered
                    podModel.setReasonId(reasonId);
                    podModel.setReferenceNo(Objects.requireNonNull(binding.tnlRefNo.getEditText()).getText().toString());
                    podModel.setUserId(castToInteger(prefUserModel.getId()));
                    podModel.setPaymentTypeId(paymentTypeId);
                    podModel.setDocketId(castToInteger(drsDocket.getDocketId()));

                    String request = new Gson().toJson(podModel);

                    Log.d("TAG", "UpdateDeliveryStatus: " + request);

                    ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                    Call<CommonResponse<String>> call = apiService.updateDeliveyStatus(podModel);

                    // call API
                    ApiManager.callRetrofit(call, API_UPDATE_DELIVERY_STATUS, this, false);
                }*/

            } else
                displayInternetToastMessage(mContext);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
//
//    private boolean checkFormValidation() {
//
//        try {
//
//            if (deliveryStatusId == 1) {
//
//                if (!isEmptyString(podFilePath)) {
//                    return true;
//                } else {
//                    displayShortToast(mContext, "Please upload POD file");
//                    return false;
//                }
//
//            } else if (deliveryStatusId == 2) {
//
//                if (reasonId > 0) {
//                    return true;
//                } else {
//                    displayShortToast(mContext, "Please select undelivered reason");
//                    return false;
//                }
//
//            } else {
//                displayShortToast(mContext, "Please select delivery status");
//                return false;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            printErrorLog(TAG, e.getLocalizedMessage());
//        }
//
//        return false;
//    }

    private void processDrsResponse(CommonResponse<String> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:
                    uploadPod();
                    break;

                case AppConstant.STATUS_FAILURE:
                    displayShortToast(mContext, response.getMessage());
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void uploadPod() {

        try {

//            if (deliveryStatusId == 1) {

            File propertyFile = new File(podFilePath);
            propertyFile = saveBitmapToFile(propertyFile);
            if (propertyFile == null) {
                displayShortToast(mContext, "Capture new image.");
                return;
            }

            RequestBody fileBody = RequestBody.create(MediaType.parse("text/*, video/*, audio/*, image/*, application/*"), propertyFile);
            MultipartBody.Part selectedFile = MultipartBody.Part.createFormData("file[]", propertyFile.getName(), fileBody);

            // start progress dialog
            startProgressDialog(this, false);

            PodModel podModel = new PodModel();
            podModel.setDRSId(castToInteger(drsId));
            podModel.setDRSDetailId(castToInteger(drsDocket.getDrsDetailId()));
            podModel.setCollectedAmount(castToDouble(Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).getText().toString()));
            podModel.setTDSAmount(castToDouble(Objects.requireNonNull(binding.tnlTdsAmount.getEditText()).getText().toString()));

            if (podStatus) {
                podModel.setDeliveryStatus(1);
            } else {
                podModel.setDeliveryStatus(deliveryStatusId);
            }

            podModel.setReasonId(reasonId);
            podModel.setReferenceNo(Objects.requireNonNull(binding.tnlRefNo.getEditText()).getText().toString());
            podModel.setUserId(castToInteger(prefUserModel.getId()));
            podModel.setPaymentTypeId(paymentTypeId);
            podModel.setDocketId(castToInteger(drsDocket.getDocketId()));

            String requestString = new Gson().toJson(podModel);
            Log.d("TAG", "uploadPod: " + requestString);
//            RequestBody requestBodyString = RequestBody.create(MediaType.parse("multipart/form-data"), requestString);

            ApiService apiService = ImageUploadApiClient.createService(ApiService.class, "", "");
            /*MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[1];
            surveyImagesParts[0] = selectedFile;*/
            Call<ImageUploadResponse> call = apiService.uploadPod(selectedFile, requestString);

            call.enqueue(new Callback<ImageUploadResponse>() {

                @Override
                public void onResponse(@NonNull Call<ImageUploadResponse> call, @NonNull Response<ImageUploadResponse> response) {

                    // stop progress dialog
                    stopProgressDialog();

                    // call method to delete files from directory
//                    deleteRecursive(new File(Environment.getExternalStorageDirectory() + AppConstant.DIR_NAME));

                    if (response.isSuccessful()) {

                        printInfoLog(TAG, new Gson().toJson(response.body()));

                        ImageUploadResponse uploadResponse = response.body();

                        if (Objects.requireNonNull(uploadResponse).getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                            String message = TextUtils.isEmpty(uploadResponse.getMessage()) ? "Status updated successfully" : uploadResponse.getMessage();
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("Delivered!");
                            builder.setMessage(message);
                            builder.setPositiveButton("OK", (dialog, which) -> {
                                dialog.dismiss();
                                /*Intent intent = new Intent();
                                setResult(RESULT_OK, intent);
                                finish();*/
                                startDriverDocketAgain();
                            });
                            builder.show();
                        }

                    } else {

                        printInfoLog(TAG, new Gson().toJson(response.errorBody()));

                        displayShortToast(mContext, response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ImageUploadResponse> call, @NonNull Throwable t) {

                    printErrorLog(TAG, t.getLocalizedMessage());

                    // stop progress dialog
                    stopProgressDialog();

                    displayShortToast(mContext, t.getMessage());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void startDriverDocketAgain() {
        /*Intent intent = new Intent(this, DriverDocketListActivity.class);
        startActivity(intent);
        finish();*/

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    private void makeRequestForPayment() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                Double amount = castToDouble(getTrimString(binding.tnlCollectedAmount));

                if (amount <= 0) {

                    displayLongToast(mContext, "Enter valid amount");

                    return;
                }

                int payableAmount = amount.intValue();

                binding.lottieProgress.setVisibility(View.VISIBLE);

                mPesaId = Objects.requireNonNull(binding.edtMPesaId.getText()).toString();

               /* if (mPesaId.startsWith("+"))
                    mPesaId = mPesaId.replace("+", "");*/
//                if (!mPesaId.startsWith("254"))
//                    mPesaId = "254" + mPesaId;

                MPesaModel.MPesaPaymentRequest request = new MPesaModel.MPesaPaymentRequest();
                request.setToPayAmount(toStr(payableAmount));
                request.setCustomerMPesaId(String.valueOf(mPesaId));

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequest(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST, this, false);


            } else {

                displayInternetToastMessage(mContext);

            }

        } catch (Exception e) {

            binding.lottieProgress.setVisibility(View.GONE);
            stopProgressDialog();
            e.printStackTrace();

        }
    }

    protected void checkStatusOfPayment(String checkoutRequestId) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                MPesaModel.MPesaPaymentResponse request = new MPesaModel.MPesaPaymentResponse();
                request.setCheckoutRequestID(checkoutRequestId);

                String requestString = new Gson().toJson(request);
                printDebugLog(TAG, requestString);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequestStatus(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST_STATUS, this, false);

            } else
                displayInternetToastMessage(mContext);

        } catch (Exception e) {
            e.printStackTrace();
        }

        stopProgressDialog();
    }

    @Override
    public void onUpdate(int itemPosition, PaymentTypeModel paymentTypeModel) {
        this.itemPosition = itemPosition;
    }

    @Override
    public void onDelete(int itemPosition, PaymentTypeModel paymentTypeModel) {
    }
}

class MyThread implements Runnable {

    Thread t;
    String checkOutRequestId;
    private Activity activity;

    MyThread(Activity activity, String checkOutRequestId) {
        this.activity = activity;
        this.checkOutRequestId = checkOutRequestId;
        t = new Thread(this);
        t.start();
    }

    public void run() {
        while (!Thread.interrupted()) {
            activity.runOnUiThread(() -> {
                try {
                    ((DrsDocketUpdateActivity) activity).checkStatusOfPayment(checkOutRequestId);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}