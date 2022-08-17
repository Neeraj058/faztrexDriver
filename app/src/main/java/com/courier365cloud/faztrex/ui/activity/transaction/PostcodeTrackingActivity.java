package com.courier365cloud.faztrex.ui.activity.transaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityPostcodeTrackingBinding;
import com.courier365cloud.faztrex.network.model.request.DocketRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.PostcodeTracking;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppValidation;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import retrofit2.Call;

public class PostcodeTrackingActivity extends BaseActivity implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityPostcodeTrackingBinding binding;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_postcode_tracking);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_postcode_tracking));

        // call method to start operational flow
        doYourWork();
    }

    private void doYourWork() {

        try {

            // call method to get preference values
            getPreferenceData();

            binding.btnTrack.setOnClickListener(v -> {

                if (AppValidation.getInstance().validateString(mContext, binding.tnlDocketNo, "Enter Postcode.")) {

                    // call method to get tracking data from server for docket number
                    getTrackingInformation();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void getTrackingInformation() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                // start progress indicator
                BaseActivity.startProgressDialog(this, false);

                // prepare request body
                DocketRequestModel.DocketTrackingRequest trackingRequest = new DocketRequestModel().new DocketTrackingRequest();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("PostCode", getTrimString(binding.tnlDocketNo));

                printInfoLog(TAG, new Gson().toJson(trackingRequest));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<PostcodeTracking>> call = apiService.getPostcodeTrackingDetail(jsonObject);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_POSTCODE_TRACKING, this, false);

            } else {
                displayInternetToastMessage(mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {
        super.onApiSuccess(endPointName, responseBody);

        switch (endPointName) {

            case ApiConstant.API_POSTCODE_TRACKING:
                binding.cvPostcodeDetails.setVisibility(View.GONE);
                CommonResponse<PostcodeTracking> postcodeTrackingCommonResponse = (CommonResponse<PostcodeTracking>) responseBody;
                processDocketTrackingResponse(postcodeTrackingCommonResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        super.onApiError(endPointName, errorMessage);

        printErrorLog(TAG, errorMessage);

        switch (endPointName) {

            case ApiConstant.API_POSTCODE_TRACKING:
                binding.cvPostcodeDetails.setVisibility(View.GONE);
                displayShortToast(mContext, getResources().getString(R.string.err_msg_api_response_failure));
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        super.onApiFailure(endPointName, failureMessage);

        printErrorLog(TAG, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_POSTCODE_TRACKING:
                binding.cvPostcodeDetails.setVisibility(View.GONE);
                displayShortToast(mContext, getResources().getString(R.string.err_msg_api_response_failure));
                break;
        }
    }

    private void processDocketTrackingResponse(CommonResponse<PostcodeTracking> response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:
                        binding.cvPostcodeDetails.setVisibility(View.VISIBLE);
                        binding.tvCityValue.setText(response.getData().getCityName());
                        binding.tvStateValue.setText(response.getData().getStateName());
                        binding.tvPostcodeTypeValue.setText(response.getData().getPostCodeTypeName());
                        break;

                    case AppConstant.STATUS_FAILURE:
                        binding.cvPostcodeDetails.setVisibility(View.GONE);
                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(mContext, "Postcode doesn't exist");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}