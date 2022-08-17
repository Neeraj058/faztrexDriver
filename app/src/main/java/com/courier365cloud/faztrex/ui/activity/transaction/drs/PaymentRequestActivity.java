package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityPaymentRequestBinding;
import com.courier365cloud.faztrex.network.model.request.MPesaModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppUtils;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.Objects;

import retrofit2.Call;

import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST;
import static com.courier365cloud.faztrex.utils.AppUtils.castToDouble;
import static com.courier365cloud.faztrex.utils.AppUtils.getFormattedString;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class PaymentRequestActivity extends BaseActivity
        implements ApiListener {

    private final Context mContext = this;

    private static final String TAG = PaymentRequestActivity.class.getSimpleName();

    private ActivityPaymentRequestBinding binding;

    private double toPayAmount;
    private String mPesaId;
    private String drsId = "";
    private DrsDocket drsDocket;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_payment_request);

        // get intent values
        drsId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_drs_id));
        drsDocket = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_drs_docket));

        if (drsDocket != null) {
            String invoiceAmount = getStringValue(drsDocket.getInvoiceValue()).contains(",") ? getStringValue(drsDocket.getInvoiceValue()).replace(",", "") : getStringValue(drsDocket.getInvoiceValue());
            String freightAmount = getStringValue(drsDocket.getTotalAmount()).contains(",") ? getStringValue(drsDocket.getTotalAmount()).replace(",", "") : getStringValue(drsDocket.getTotalAmount());
            String tdsAmount = getStringValue(drsDocket.getTdsAmount()).contains(",") ? getStringValue(drsDocket.getTdsAmount()).replace(",", "") : getStringValue(drsDocket.getTdsAmount());
            String balanceAmount = getStringValue(drsDocket.getBalanceAmount()).contains(",") ? getStringValue(drsDocket.getBalanceAmount()).replace(",", "") : getStringValue(drsDocket.getBalanceAmount());
            toPayAmount = castToDouble(invoiceAmount) + castToDouble(freightAmount);
        } else {
            toPayAmount = 0;
        }
        binding.edtAmt.setText(getFormattedString(String.valueOf(toPayAmount), AppConstant.FORMAT_2_F));

        doYourWork();
    }

    private void doYourWork() {

        binding.btnRequest.setOnClickListener(v -> {
            toPayAmount = AppUtils.castToDouble(Objects.requireNonNull(binding.edtAmt.getText()).toString());
            mPesaId = AppUtils.getStringValue(Objects.requireNonNull(binding.edtMPesaId.getText()).toString());

            if (toPayAmount > 0)
                makeRequestForPayment();
            else
                displayLongToast(mContext, "Enter valid ToPay Amount.");
        });
    }

    private void makeRequestForPayment() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                MPesaModel.MPesaPaymentRequest request = new MPesaModel.MPesaPaymentRequest();
                //request.setToPayAmount(String.valueOf(toPayAmount));
                request.setToPayAmount(String.valueOf(10));
                request.setCustomerMPesaId(String.valueOf(mPesaId));

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<MPesaModel.MPesaPaymentResponse>> call = apiService.sendMPesaPaymentRequest(request);

                ApiManager.callRetrofit(call, API_MPESA_PAYMENT_REQUEST, this, false);

            } else {

                displayInternetToastMessage(mContext);

            }

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case API_MPESA_PAYMENT_REQUEST:
                CommonResponse<MPesaModel.MPesaPaymentResponse> response = (CommonResponse<MPesaModel.MPesaPaymentResponse>) responseBody;
                processPaymentRequestResponse(response);
                break;

        }
    }

    private void processPaymentRequestResponse(CommonResponse<MPesaModel.MPesaPaymentResponse> response) {

        try {

            stopProgressDialog();

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    MPesaModel.MPesaPaymentResponse data = response.getData();

                    //Intent mIntent = new Intent(mContext, DrsDocketUpdateActivity.class);
                    Intent mIntent = new Intent(mContext, PaymentRequestStatusActivity.class);
                    mIntent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsId);
                    mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
                    mIntent.putExtra(mContext.getResources().getString(R.string.key_data_mpesa_request), data);
                    startActivity(mIntent);

                    //displayLongToast(mContext, data.getCheckoutRequestID());

                } else {

                    displayLongToast(mContext, response.getMessage());
                }
            } else
                displayLongToast(mContext, "Something went wrong!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        switch (endPointName) {

            case API_MPESA_PAYMENT_REQUEST:
                stopProgressDialog();
                displayLongToast(mContext, errorMessage);
                break;

        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        switch (endPointName) {

            case API_MPESA_PAYMENT_REQUEST:
                stopProgressDialog();
                displayLongToast(mContext, failureMessage);
                break;
        }
    }
}