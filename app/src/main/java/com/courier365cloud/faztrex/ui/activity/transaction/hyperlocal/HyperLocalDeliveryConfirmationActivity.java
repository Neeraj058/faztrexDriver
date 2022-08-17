package com.courier365cloud.faztrex.ui.activity.transaction.hyperlocal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityHyperLocalDeliveryConfirmationBinding;
import com.courier365cloud.faztrex.helper.ConfirmationDialogManager;
import com.courier365cloud.faztrex.listener.general.ConfirmationDialogClickListener;
import com.courier365cloud.faztrex.network.model.request.CommonRequestModel;
import com.courier365cloud.faztrex.network.model.request.MPesaModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalList;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.Objects;

import retrofit2.Call;

import static com.cittasolutions.cittalibrary.utils.AppUtils.isValidString;
import static com.cittasolutions.cittalibrary.utils.AppUtils.toDouble;
import static com.cittasolutions.cittalibrary.utils.AppUtils.toInt;
import static com.cittasolutions.cittalibrary.utils.AppUtils.toStr;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_HLR_DELIVERED;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS;
import static com.courier365cloud.faztrex.utils.AppUtils.getFormattedString;

public class HyperLocalDeliveryConfirmationActivity extends BaseActivity implements
        View.OnClickListener, ConfirmationDialogClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private String hyperLocalId;

    private ActivityHyperLocalDeliveryConfirmationBinding binding;

    private String hlrId;
    private String payableAmount;
    private String paymentType;
    private String mPesaRequestContactNo;
    private String checkoutRequestId, refNo;

    private HyperLocalList hyperLocalList;

    private boolean isMoneyReceived;
    private Thread thread;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_hyper_local_delivery_confirmation);

        binding.toolbarMain.tvHeaderTitle.setText("Hyper Local Request Delivery");
        binding.toolbarMain.ivBack.setOnClickListener(this);
        binding.btnDelivered.setOnClickListener(this);
        binding.btnMPesaRequest.setOnClickListener(this);

        hyperLocalList = getIntent().getParcelableExtra("hlr");

        hyperLocalRequestId = toInt(hyperLocalList.getId());
        appPreference.setIntegerPreference(mContext, "hyperLocalRequestId", hyperLocalRequestId);

        hlrId = hyperLocalList.getId();
        payableAmount = hyperLocalList.getTotalAmount();
        paymentType = hyperLocalList.getPaymentType();
        mPesaRequestContactNo = hyperLocalList.getDeliveryContactNo();

        if (paymentType.equalsIgnoreCase("MPESA")) {
            binding.tnlRefNo.setVisibility(View.VISIBLE);
            binding.btnMPesaRequest.setVisibility(View.VISIBLE);
            binding.btnDelivered.setVisibility(View.GONE);
        } else {
            binding.tnlRefNo.setVisibility(View.GONE);
            binding.btnMPesaRequest.setVisibility(View.GONE);
            binding.btnDelivered.setVisibility(View.VISIBLE);
        }

        Objects.requireNonNull(binding.tnlPaymentType.getEditText()).setText(paymentType);
        Objects.requireNonNull(binding.tnlPayableAmount.getEditText()).setText(payableAmount);

        Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).addTextChangedListener(textWatcher);
        Objects.requireNonNull(binding.tnlRefNo.getEditText()).addTextChangedListener(textWatcher);

        thread = new Thread(() -> {
            while (!isMoneyReceived) {
                try {
                    checkStatusOfPayment();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        if (paymentType.equalsIgnoreCase("To Be Billed")) {
            binding.tnlCollectedAmount.getEditText().setText("0");
        }
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Double payableAmount = toDouble(toStr(binding.tnlPayableAmount));
            Double collectedAmount = toDouble(toStr(binding.tnlCollectedAmount));

            Double balanceAmount = payableAmount - collectedAmount;
            Objects.requireNonNull(binding.tnlBalanceAmount.getEditText()).setText(getFormattedString(String.valueOf(balanceAmount), AppConstant.FORMAT_2_F));

            if (collectedAmount > payableAmount) {
                collectedAmount = payableAmount;
                Objects.requireNonNull(binding.tnlCollectedAmount.getEditText()).setText(toStr(collectedAmount));
            }

            if (isValidString(toStr(binding.tnlRefNo))) {
                binding.btnMPesaRequest.setVisibility(View.GONE);
                binding.btnDelivered.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_back:
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
                break;

            case R.id.btnDelivered:
                ConfirmationDialogManager.getInstance(mContext, this).askConsent("Are you sure to update this as Delivered ?");
                break;

            case R.id.btnMPesaRequest:
                makeRequestForPayment();
                break;
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case API_HLR_DELIVERED:
                CommonResponse<String> statusUpdateResponse = (CommonResponse<String>) responseBody;
                processStatusUpdateResponse(statusUpdateResponse);
                break;

            case API_MPESA_PAYMENT_REQUEST:
                CommonResponse<MPesaModel.MPesaPaymentResponse> paymentRequest = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processPaymentRequestResponse(paymentRequest);
                break;

            case API_MPESA_PAYMENT_REQUEST_STATUS:
                CommonResponse<MPesaModel.MPesaPaymentResponse> paymentResponse = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processPaymentRequestStatusResponse(paymentResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        stopProgressDialog();

        displayShortToast(mContext, errorMessage);

        switch (endPointName) {

            case API_HLR_DELIVERED:
                break;

            case API_MPESA_PAYMENT_REQUEST:
            case API_MPESA_PAYMENT_REQUEST_STATUS:
                binding.lottieProgress.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        stopProgressDialog();

        displayShortToast(mContext, getString(R.string.err_msg_api_response_failure));

        switch (endPointName) {
            case API_HLR_DELIVERED:
                break;

            case API_MPESA_PAYMENT_REQUEST:
            case API_MPESA_PAYMENT_REQUEST_STATUS:
                binding.lottieProgress.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onConfirmationPositiveClick() {
        markAsDelivered();
    }

    @Override
    public void onConfirmationNegativeClick() {
    }

    private void processPaymentRequestResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {

        try {

            binding.lottieProgress.setVisibility(View.VISIBLE);

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    checkoutRequestId = data.getCheckoutRequestID();

                    thread.start();

                    displayLongToast(mContext, "Please wait! \nWaiting for payment status.");

                } else {
                    binding.lottieProgress.setVisibility(View.GONE);
                    displayLongToast(mContext, response.getMessage());
                }

            } else {
                binding.lottieProgress.setVisibility(View.GONE);
                displayLongToast(mContext, getString(R.string.err_msg_api_response_failure));
            }

        } catch (Exception e) {
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

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    if (data.getResponseCode().equals("0")) {
                        Objects.requireNonNull(binding.tnlRefNo.getEditText()).setText(checkoutRequestId);
                        isMoneyReceived = true;
                        binding.lottieProgress.setVisibility(View.GONE);
                    }
                }

            } else
                displayLongToast(mContext, getString(R.string.err_msg_api_response_failure));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processStatusUpdateResponse(CommonResponse<String> response) {

        if (response != null) {

            if (response.getStatus().equals("1")) {
                Intent intent = new Intent();
                setResult(RESULT_OK);
                finish();
            } else
                displayShortToast(mContext, response.getMessage());
        } else
            displayShortToast(mContext, getString(R.string.err_msg_api_response_failure));

        stopProgressDialog();
    }

    private void markAsDelivered() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                getPreferenceData();

                CommonRequestModel commonRequestModel = new CommonRequestModel();
                commonRequestModel.setUserid(prefUserModel.getId());
                commonRequestModel.setId(hlrId);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                startProgressDialog((Activity) mContext, false);

                Call<CommonResponse<String>> call = apiService.markAsDelivered(commonRequestModel);

                ApiManager.callRetrofit(call, API_HLR_DELIVERED, this, false);
            }

        } catch (Exception e) {
            displayShortToast(mContext, getString(R.string.err_msg_api_response_failure));
            printErrorLog(TAG, e.getLocalizedMessage());
            stopProgressDialog();
        }
    }

    private void makeRequestForPayment() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                double amount = toDouble(toStr(binding.tnlCollectedAmount));

                if (amount <= 0) {
                    displayLongToast(mContext, "Enter valid amount");
                    return;
                }

                if (mPesaRequestContactNo.startsWith("+"))
                    mPesaRequestContactNo = mPesaRequestContactNo.replace("+", "");
//                if (!mPesaId.startsWith("254"))
//                    mPesaId = "254" + mPesaId;

                MPesaModel.MPesaPaymentRequest request = new MPesaModel.MPesaPaymentRequest();
                request.setToPayAmount(String.valueOf(Math.round(amount)));
                request.setCustomerMPesaId(String.valueOf(mPesaRequestContactNo));

                String requestString = new Gson().toJson(request);

                binding.lottieProgress.setVisibility(View.VISIBLE);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequest(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST, this, false);

            } else
                displayInternetToastMessage(mContext);

        } catch (Exception e) {
            binding.lottieProgress.setVisibility(View.GONE);
            stopProgressDialog();
            e.printStackTrace();
        }
    }

    private void checkStatusOfPayment() {

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
}