package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_DOCKET_PAYMENT_DETAILS;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS;
import static com.courier365cloud.faztrex.utils.AppUtils.castToDouble;
import static com.courier365cloud.faztrex.utils.AppUtils.castToInteger;
import static com.courier365cloud.faztrex.utils.AppUtils.convertDateFormat;
import static com.courier365cloud.faztrex.utils.AppUtils.getCurrentDate;
import static com.courier365cloud.faztrex.utils.AppUtils.getFormattedString;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.PaymentTypeListAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityPaymentListBinding;
import com.courier365cloud.faztrex.databinding.DialogPaymentTypeDetailBinding;
import com.courier365cloud.faztrex.network.model.request.MPesaModel;
import com.courier365cloud.faztrex.network.model.request.PaymentTypeModel;
import com.courier365cloud.faztrex.network.model.response.CommonListResponse;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;

public class PaymentListActivity extends BaseActivity
        implements View.OnClickListener, PaymentTypeListAdapter.OnPaymentTypeClickListener,
        AdapterView.OnItemSelectedListener {

    private static final String TAG = PaymentListActivity.class.getSimpleName();

    private final Context mContext = this;
    private final ArrayList<PaymentTypeModel> paymentTypeModelArrayList = new ArrayList<>();
    private ActivityPaymentListBinding binding;
    private int itemPosition = -1;

    private String drsId = "";
    private DrsDocket drsDocket;
    private double toPayAmount;
    private String mPesaId;

    private DialogPaymentTypeDetailBinding dialogBinding;
    private Dialog dialog;

    private PaymentTypeListAdapter adapter;

    private int paymentTypeId, bankId;
    private String paymentType, bankName;
    private String checkoutRequestId;

    private boolean isMPesaApplicable, isMoneyReceived;

    private Thread thread;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_payment_list);

        binding.layoutCommonList.fabAdd.setVisibility(View.VISIBLE);
        binding.btnSaveItems.setVisibility(View.VISIBLE);

        binding.layoutCommonList.fabAdd.setOnClickListener(this);
        binding.btnSaveItems.setOnClickListener(this);

        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_payment_details));

        getPreferenceData();

        // get intent values
        drsId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_drs_id));
        drsDocket = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_drs_docket));

        if (isEmptyString(drsId) || drsDocket == null) {
            displayLongToast(mContext, "Something went wrong!");
            finish();
        }

        String invoiceAmount = getStringValue(drsDocket.getInvoiceValue()).contains(",") ? getStringValue(drsDocket.getInvoiceValue()).replace(",", "") : getStringValue(drsDocket.getInvoiceValue());
        String freightAmount = getStringValue(drsDocket.getTotalAmount()).contains(",") ? getStringValue(drsDocket.getTotalAmount()).replace(",", "") : getStringValue(drsDocket.getTotalAmount());
        String tdsAmount = getStringValue(drsDocket.getTdsAmount()).contains(",") ? getStringValue(drsDocket.getTdsAmount()).replace(",", "") : getStringValue(drsDocket.getTdsAmount());
        String balanceAmount = getStringValue(drsDocket.getBalanceAmount()).contains(",") ? getStringValue(drsDocket.getBalanceAmount()).replace(",", "") : getStringValue(drsDocket.getBalanceAmount());
        toPayAmount = castToDouble(invoiceAmount) + castToDouble(freightAmount);

        // set layout manager for dimension recycler view
        binding.layoutCommonList.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        binding.layoutCommonList.recyclerView.setItemAnimator(new DefaultItemAnimator());

        // set adapter for recycler view
        adapter = new PaymentTypeListAdapter(mContext, paymentTypeModelArrayList);
        binding.layoutCommonList.recyclerView.setAdapter(adapter);

        binding.btnSaveItems.setEnabled(paymentTypeModelArrayList.size() > 0);

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
    }

    @Override
    public void onUpdate(int itemPosition, PaymentTypeModel paymentTypeModel) {
        this.itemPosition = itemPosition;
        openPaymentTypeDialog(itemPosition);
    }

    @Override
    public void onDelete(int itemPosition, PaymentTypeModel paymentTypeModel) {
        displayConfirmationDialog(itemPosition);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fab_add:
                openPaymentTypeDialog(-1);
                break;

            case R.id.iv_close:
                dialog.dismiss();
                break;

            case R.id.btn_submit:
                setPaymentTypeDataToArrayList();
                break;

            case R.id.edtRefDate:
                openDatePickerDialog(dialogBinding.tnlRefDate, AppConstant.CALENDAR_DATE_FORMAT);
                break;

            case R.id.btnSaveItems:
                savePaymentItemsServer();

            case R.id.btnRequest:
                makeRequestForPayment();
                break;
        }
    }

    private void savePaymentItemsServer() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                for (PaymentTypeModel payment : paymentTypeModelArrayList) {
                    String convertedDate = convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.API_DATE_FORMAT, payment.getRefOrChequeDate());
                    payment.setRefOrChequeDate(convertedDate);
                }

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<String>> call = apiService.managePaymentDetails(paymentTypeModelArrayList);

                ApiManager.callRetrofit(call, API_DOCKET_PAYMENT_DETAILS, this, false);

            }

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
        }
    }

    private void setPaymentTypeDataToArrayList() {

        try {

            double amount = castToDouble(getTrimString(dialogBinding.tnlAmount));

            if (amount <= 0) {
                displayLongToast(mContext, "Enter valid amount");
                return;
            }

            PaymentTypeModel paymentTypeModel = itemPosition >= 0 ? paymentTypeModelArrayList.get(itemPosition) : new PaymentTypeModel();
            paymentTypeModel.setPaymentTypeId(paymentTypeId);
            paymentTypeModel.setPaymentType(paymentType);
            paymentTypeModel.setBankId(bankId);
            paymentTypeModel.setBank(bankName);
            paymentTypeModel.setDRSId(castToInteger(drsId));
            paymentTypeModel.setDocketId(castToInteger(drsDocket.getDocketId()));
            paymentTypeModel.setPayeeDetail(getTrimString(dialogBinding.tnlPayee));
            paymentTypeModel.setRefOrChequeNo(getTrimString(dialogBinding.tnlRefNo));
            paymentTypeModel.setRefOrChequeDate(getTrimString(dialogBinding.tnlRefDate));
            paymentTypeModel.setAmount(castToDouble(getTrimString(dialogBinding.tnlAmount)));
            paymentTypeModel.setRemarks(getTrimString(dialogBinding.tnlRemarks));
            paymentTypeModel.setCid(castToInteger(prefUserModel.getCid()));
            paymentTypeModel.setBid(castToInteger(prefUserModel.getBid()));
            paymentTypeModel.setUid(castToInteger(prefUserModel.getId()));
            paymentTypeModel.setSid(castToInteger(prefUserModel.getSid()));
            paymentTypeModel.setIsActive(1);
            paymentTypeModel.setIsDelete(0);
            paymentTypeModel.setLastModifyBy(castToInteger(prefUserModel.getId()));
            paymentTypeModel.setIsFrom(2);
            paymentTypeModel.setIsSync(0);

            if (itemPosition == -1)
                paymentTypeModelArrayList.add(paymentTypeModel);
            else
                paymentTypeModelArrayList.set(itemPosition, paymentTypeModel);

            adapter.notifyDataSetChanged();

            binding.btnSaveItems.setEnabled(paymentTypeModelArrayList.size() > 0);

            dialog.dismiss();

            paymentTypeId = 0;
            bankId = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPaymentTypeDialog(int itemPosition) {

        dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_payment_type_detail, null, false);
        dialog = new Dialog(mContext, R.style.full_screen_dialog);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.show();

        dialogBinding.btnSubmit.setOnClickListener(this);
        dialogBinding.ivClose.setOnClickListener(this);
        Objects.requireNonNull(dialogBinding.tnlRefDate.getEditText()).setOnClickListener(this);

        dialogBinding.spinnerPaymentType.setOnItemSelectedListener(this);
        dialogBinding.spinnerBank.setOnItemSelectedListener(this);

        dialogBinding.btnRequest.setOnClickListener(this);

        dialogBinding.tnlRefDate.getEditText().setText(getCurrentDate(AppConstant.CALENDAR_DATE_FORMAT));

        if (itemPosition >= 0) {

            PaymentTypeModel paymentTypeModel = paymentTypeModelArrayList.get(itemPosition);

            paymentTypeId = paymentTypeModel.getPaymentTypeId();
            paymentType = paymentTypeModel.getPaymentType();

            bankId = paymentTypeModel.getBankId();
            bankName = paymentTypeModel.getBank();

            Objects.requireNonNull(dialogBinding.tnlRefNo.getEditText()).setText(paymentTypeModel.getRefOrChequeNo());
            dialogBinding.tnlRefDate.getEditText().setText(paymentTypeModel.getRefOrChequeDate());
            Objects.requireNonNull(dialogBinding.tnlAmount.getEditText()).setText(getFormattedString(paymentTypeModel.getAmount().toString(), AppConstant.FORMAT_2_F));
            Objects.requireNonNull(dialogBinding.tnlRemarks.getEditText()).setText(paymentTypeModel.getRemarks());
        }

        getPaymentTypeList();

    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {

            case R.id.spinner_payment_type:
                paymentTypeId = castToInteger(paymentTypeMasterList.get(position).getId());
                paymentType = paymentTypeMasterList.get(position).getName();

                switch (paymentTypeId) {

                    case 1:
                        dialogBinding.spinnerBank.setVisibility(View.GONE);
                        dialogBinding.tvTitleBank.setVisibility(View.GONE);
                        dialogBinding.relativeBottom.setVisibility(View.GONE);
                        dialogBinding.btnSubmit.setVisibility(View.VISIBLE);
                        break;

                    case 2:
                        dialogBinding.spinnerBank.setVisibility(View.VISIBLE);
                        dialogBinding.tvTitleBank.setVisibility(View.VISIBLE);
                        dialogBinding.relativeBottom.setVisibility(View.GONE);
                        dialogBinding.btnSubmit.setVisibility(View.VISIBLE);
                        break;

                    case 3:
                        dialogBinding.spinnerBank.setVisibility(View.GONE);
                        dialogBinding.tvTitleBank.setVisibility(View.GONE);
                        dialogBinding.relativeBottom.setVisibility(View.VISIBLE);
                        dialogBinding.btnSubmit.setVisibility(View.GONE);
                        break;
                }
                break;

            case R.id.spinner_bank:
                bankId = castToInteger(paymentTypeMasterList.get(position).getId());
                if (bankId > 0)
                    bankName = paymentTypeMasterList.get(position).getName();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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

    private void getBankList() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                BaseActivity.startProgressDialog((Activity) mContext, false);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getBanks();

                ApiManager.callRetrofit(call, ApiConstant.API_BANK, this, false);

            } else
                displayInternetToastMessage(mContext);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        switch (endPointName) {

            case ApiConstant.API_PAYMENT_TYPE:
                CommonResponse<ArrayList<CommonListResponse>> paymentTypeListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processPaymentTypeListResponse(paymentTypeListResponse);
                break;

            case ApiConstant.API_BANK:
                CommonResponse<ArrayList<CommonListResponse>> referenceListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processReferenceListResponse(referenceListResponse);
                break;

            case API_DOCKET_PAYMENT_DETAILS:
                CommonResponse<String> paymentDetailItems = (CommonResponse<String>) responseBody;
                processPaymentDetailItems(paymentDetailItems);
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

        switch (endPointName) {

            case ApiConstant.API_PAYMENT_TYPE:
                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_PAYMENT_TYPE);
                break;

            case ApiConstant.API_BANK:
                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_BANK);
                break;

            case API_DOCKET_PAYMENT_DETAILS:
                printErrorLog(TAG, errorMessage);
                stopProgressDialog();
                break;

            case API_MPESA_PAYMENT_REQUEST:
                dialogBinding.lottieProgress.setVisibility(View.VISIBLE);
                displayLongToast(mContext, errorMessage);
                break;

            case API_MPESA_PAYMENT_REQUEST_STATUS:
                dialogBinding.lottieProgress.setVisibility(View.VISIBLE);
                displayLongToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        switch (endPointName) {

            case ApiConstant.API_PAYMENT_TYPE:
                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_PAYMENT_TYPE);
                break;

            case ApiConstant.API_BANK:
                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_BANK);
                break;

            case API_DOCKET_PAYMENT_DETAILS:
                printErrorLog(TAG, failureMessage);
                stopProgressDialog();
                break;

            case API_MPESA_PAYMENT_REQUEST:
                dialogBinding.lottieProgress.setVisibility(View.VISIBLE);
                displayLongToast(mContext, failureMessage);
                break;

            case API_MPESA_PAYMENT_REQUEST_STATUS:
                dialogBinding.lottieProgress.setVisibility(View.VISIBLE);
                displayLongToast(mContext, failureMessage);
                break;
        }
    }

    private void processPaymentDetailItems(CommonResponse<String> response) {

        if (response != null) {

            stopProgressDialog();

            if (response.getStatus().equalsIgnoreCase("1")) {
                displayLongToast(mContext, "Data saved successfully.");

//                Intent mIntent = new Intent(mContext, PaymentRequestActivity.class);
                Intent mIntent = new Intent(mContext, DrsDocketUpdateActivity.class);
                mIntent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsId);
                mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
                startActivity(mIntent);

            } else
                displayLongToast(mContext, response.getMessage());
        }
    }

    private void processPaymentTypeListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {
        paymentTypeMasterList = new ArrayList<>();
        paymentTypeMasterList = response.getData();
        setSpinnerHeader(AppConstant.SP_PAYMENT_TYPE);
        getBankList();
    }

    private void processReferenceListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {
        bankMasterList = new ArrayList<>();
        bankMasterList = response.getData();
        setSpinnerHeader(AppConstant.SP_BANK);
    }

    private void processPaymentRequestResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {

        try {

            dialogBinding.lottieProgress.setVisibility(View.VISIBLE);

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    checkoutRequestId = data.getCheckoutRequestID();

                    thread.start();

                    displayLongToast(mContext, "Please wait! \nWaiting for payment status.");

                } else
                    displayLongToast(mContext, response.getMessage());
            } else
                displayLongToast(mContext, "Something went wrong!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    private void processPaymentRequestStatusResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {

        try {

            stopProgressDialog();

            if (response != null) {

                dialogBinding.lottieProgress.setVisibility(View.VISIBLE);

                if (response.getStatus().equalsIgnoreCase("1")) {

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    if (data.getResponseCode().equals("0")) {

                        Objects.requireNonNull(dialogBinding.tnlRefNo.getEditText()).setText(checkoutRequestId);

                        isMoneyReceived = true;
                        dialogBinding.lottieProgress.setVisibility(View.GONE);
                        dialogBinding.btnSubmit.setVisibility(View.VISIBLE);
                        dialogBinding.relativeBottom.setVisibility(View.GONE);

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

                paymentTypeMasterList.add(0, new CommonListResponse("0", getResources().getString(R.string.spinner_payment_type_header_title)));
                bindSpinnerData(AppConstant.SP_PAYMENT_TYPE, dialogBinding.spinnerPaymentType, paymentTypeMasterList, paymentTypeId);
                break;

            case AppConstant.SP_BANK:

                if (bankMasterList == null)
                    bankMasterList = new ArrayList<>();

                bankMasterList.add(0, new CommonListResponse("0", getResources().getString(R.string.spinner_reference_title)));
                bindSpinnerData(AppConstant.SP_BANK, dialogBinding.spinnerBank, bankMasterList, bankId);
                break;
        }
    }

    private void makeRequestForPayment() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                double amount = castToDouble(getTrimString(dialogBinding.tnlAmount));

                if (amount <= 0) {
                    displayLongToast(mContext, "Enter valid amount");
                    return;
                }

                dialogBinding.lottieProgress.setVisibility(View.VISIBLE);

                mPesaId = Objects.requireNonNull(dialogBinding.edtMPesaId.getText()).toString();

                MPesaModel.MPesaPaymentRequest request = new MPesaModel.MPesaPaymentRequest();
                request.setToPayAmount(String.valueOf(amount));
                request.setCustomerMPesaId(String.valueOf(mPesaId));

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequest(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST, this, false);

                dialogBinding.relativeBottom.setVisibility(View.GONE);

            } else {

                displayInternetToastMessage(mContext);

            }

        } catch (Exception e) {
            dialogBinding.lottieProgress.setVisibility(View.VISIBLE);
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

    private void displayConfirmationDialog(int itemPosition) {

        simpleAlertDialog = new SimpleAlertDialog(mContext) {
            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return getResources().getString(R.string.dialog_title_confirmation);
            }

            @Override
            public String setDialogMessage() {
                return getResources().getString(R.string.dialog_msg_payment_delete_confirmation);
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public Drawable setDialogIcon() {
                return getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return Objects.requireNonNull(mContext).getResources().getString(R.string.btn_title_proceed);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {
                    paymentTypeModelArrayList.remove(itemPosition);
                    adapter.notifyItemRemoved(itemPosition);
                    adapter.notifyItemRangeChanged(itemPosition, paymentTypeModelArrayList.size());

                    binding.btnSaveItems.setEnabled(paymentTypeModelArrayList.size() > 0);
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {
                return (dialog, which) -> dialog.dismiss();
            }

            @Override
            public String setDialogNeutralButtonText() {
                return null;
            }

            @Override
            public DialogInterface.OnClickListener onDialogNeutralButtonClick() {
                return null;
            }

            @Override
            public DialogInterface.OnDismissListener onDialogDismissListener() {
                return null;
            }
        };
    }
}