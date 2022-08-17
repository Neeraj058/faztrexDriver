package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityPaymentRequestStatusBinding;
import com.courier365cloud.faztrex.network.model.request.MPesaModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.Objects;

import retrofit2.Call;

import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_MPESA_PAYMENT_REQUEST_STATUS;
import static com.courier365cloud.faztrex.utils.AppUtils.castToDouble;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class PaymentRequestStatusActivity extends BaseActivity {

    private static final String TAG = PaymentRequestStatusActivity.class.getSimpleName();

    private final Context mContext = this;

    private ActivityPaymentRequestStatusBinding binding;

    private MPesaModel.MPesaPaymentResponse responseModel;
    private String drsId = "";
    private DrsDocket drsDocket;

    private String checkoutRequestId;

    private Handler handler;
    private Runnable runnable;

    private Thread thread;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_payment_request_status);

        // get intent values
        responseModel = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_mpesa_request));
        drsId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_drs_id));
        drsDocket = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_drs_docket));

        if (responseModel != null) {

            checkoutRequestId = responseModel.getCheckoutRequestID();

            double collectedAmount = castToDouble(getStringValue(drsDocket.getInvoiceValue()).contains(",") ? getStringValue(drsDocket.getInvoiceValue()).replace(",", "") : getStringValue(drsDocket.getInvoiceValue()))
                    + castToDouble(getStringValue(drsDocket.getTotalAmount()).contains(",") ? getStringValue(drsDocket.getTotalAmount()).replace(",", "") : getStringValue(drsDocket.getTotalAmount()));

            String msg = binding.tvMessage.getText().toString();
            msg = msg.replace("{requestNo}", checkoutRequestId);
            msg = msg.replace("{amount}", String.valueOf(collectedAmount));
            binding.tvMessage.setText(msg);

            thread = new Thread(() -> {
                try {
                    while (!Thread.interrupted())
                        checkStatusOfPayment();
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        binding.btnUploadPod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, DrsDocketUpdateActivity.class);
                mIntent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsId);
                mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
                //mIntent.putExtra(mContext.getResources().getString(R.string.key_data_mpesa_request), data);
                startActivity(mIntent);
            }
        });
    }

    private void checkStatusOfPayment() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                startProgressDialog(this, false);

                MPesaModel.MPesaPaymentResponse request = new MPesaModel.MPesaPaymentResponse();
                request.setCheckoutRequestID(checkoutRequestId);

                String requestString = new Gson().toJson(request);

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
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case API_MPESA_PAYMENT_REQUEST_STATUS:
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

                    if (data.getResponseCode().equals("0")) {

                        thread.interrupt();

                        binding.lottieProgress.setAnimation(R.raw.success);
                        binding.lottieProgress.setRepeatCount(1);

                        displayLongToast(mContext, data.getResponseDescription());

                        binding.btnUploadPod.setVisibility(View.VISIBLE);
                    }
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

            case API_MPESA_PAYMENT_REQUEST_STATUS:
                stopProgressDialog();
                displayLongToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        switch (endPointName) {
            case API_MPESA_PAYMENT_REQUEST_STATUS:
                stopProgressDialog();
                displayLongToast(mContext, failureMessage);
                break;
        }
    }
}