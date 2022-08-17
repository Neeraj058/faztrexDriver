package com.courier365cloud.faztrex.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityChangePasswordBinding;
import com.courier365cloud.faztrex.network.model.request.AuthenticationRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppPreference;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class ChangePasswordActivity extends BaseActivity implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityChangePasswordBinding binding;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_change_password));

        // call method to start operational flow
        doYourWork();
    }

    private void doYourWork() {

        try {

            // get preference data
            getPreferenceData();

            binding.btnProceed.setOnClickListener(v -> {

                if (checkFormValidation()) {

                    if (NetworkUtils.isConnected(mContext)) {

                        // call method to update password
                        webCallChangePassword();

                    } else {
                        displayInternetToastMessage(mContext);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to check form validations
     *
     *
     * */
    private boolean checkFormValidation() {

        try {

            if (getTrimString(binding.tnlOldPassword).length() > 0) {

                if (getTrimString(binding.tnlNewPassword).length() > 6) {

                    if (getTrimString(binding.tnlConfirmPassword).length() > 6) {

                        if (getTrimString(binding.tnlNewPassword).equals(getTrimString(binding.tnlConfirmPassword))) {

                            if (!(getTrimString(binding.tnlOldPassword).equals(getTrimString(binding.tnlConfirmPassword)))) {

                                return true;

                            } else {

                                binding.tnlOldPassword.setError(null);
                                binding.tnlOldPassword.setErrorEnabled(false);
                                binding.tnlNewPassword.setError(null);
                                binding.tnlNewPassword.setErrorEnabled(false);
                                binding.tnlConfirmPassword.setError("Password should be different from current password");
                                binding.tnlConfirmPassword.setErrorEnabled(true);
                                binding.tnlConfirmPassword.requestFocus();

                                return false;
                            }

                        } else {

                            binding.tnlOldPassword.setError(null);
                            binding.tnlOldPassword.setErrorEnabled(false);
                            binding.tnlNewPassword.setError(null);
                            binding.tnlNewPassword.setErrorEnabled(false);
                            binding.tnlConfirmPassword.setError("Password mismatch");
                            binding.tnlConfirmPassword.setErrorEnabled(true);
                            binding.tnlConfirmPassword.requestFocus();

                            return false;
                        }

                    } else {

                        binding.tnlOldPassword.setError(null);
                        binding.tnlOldPassword.setErrorEnabled(false);
                        binding.tnlNewPassword.setError(null);
                        binding.tnlNewPassword.setErrorEnabled(false);
                        binding.tnlConfirmPassword.setError("Password should be minimum of 6 characters.");
                        binding.tnlConfirmPassword.setErrorEnabled(true);
                        binding.tnlConfirmPassword.requestFocus();

                        return false;
                    }

                } else {

                    binding.tnlOldPassword.setError(null);
                    binding.tnlOldPassword.setErrorEnabled(false);
                    binding.tnlNewPassword.setError("Password should be minimum of 6 characters.");
                    binding.tnlNewPassword.setErrorEnabled(true);
                    binding.tnlNewPassword.requestFocus();
                    binding.tnlConfirmPassword.setError(null);
                    binding.tnlConfirmPassword.setErrorEnabled(false);

                    return false;
                }

            } else {

                binding.tnlOldPassword.setError("Enter current password");
                binding.tnlOldPassword.setErrorEnabled(true);
                binding.tnlOldPassword.requestFocus();
                binding.tnlNewPassword.setError(null);
                binding.tnlNewPassword.setErrorEnabled(false);
                binding.tnlConfirmPassword.setError(null);
                binding.tnlConfirmPassword.setErrorEnabled(false);

                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

        return false;
    }

    /*
     * Method to make API call to change password
     *
     *
     * */
    private void webCallChangePassword() {

        try {

            // start progress indicator
            startProgressDialog(currentActivity, false);

            // prepare request body
            AuthenticationRequestModel.ChangePasswordRequest changePasswordRequest = new AuthenticationRequestModel().new ChangePasswordRequest();

            changePasswordRequest.setUserId(prefUserModel.getId());
            changePasswordRequest.setCurrentPassword(getTrimString(binding.tnlOldPassword));
            changePasswordRequest.setNewPassword(getTrimString(binding.tnlConfirmPassword));

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse> call = apiService.changePassword(changePasswordRequest);

            // call API
            ApiManager.callRetrofit(call, ApiConstant.API_CHANGE_PASSWORD, this, false);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_CHANGE_PASSWORD:

                CommonResponse commonResponse = (CommonResponse) responseBody;
                processResponse(commonResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        printErrorLog(TAG, errorMessage);

        // show error toast message
        displayLongToast(mContext, errorMessage);
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, failureMessage);

        // show error toast message
        displayLongToast(mContext, mContext.getResources().getString(R.string.err_msg_api_response_failure));
    }

    private void processResponse(CommonResponse response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    displayShortToast(mContext, "Password changed successfully");

                    // clear all preference data
                    AppPreference.getInstance().clearPreferences(this);

                    startActivity(new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finishAffinity();
                    break;

                case AppConstant.STATUS_FAILURE:

                    displayShortToast(mContext, getStringValue(response.getMessage()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}
