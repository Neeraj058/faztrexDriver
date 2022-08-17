package com.courier365cloud.faztrex.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityResetPasswordBinding;
import com.courier365cloud.faztrex.network.model.request.AuthenticationRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class ResetPasswordActivity extends BaseActivity implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityResetPasswordBinding binding;

    private String userId = "";

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);

        // get intent values
        userId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_user_id));

        binding.btnProceed.setOnClickListener(v -> {

            if (checkValidation()) {

                // hide soft keyboard
                hideSoftKeyboard();

                if (NetworkUtils.isConnected(mContext)) {

                    // call method to set new account password
                    setAccountPassword();

                } else {
                    displayInternetToastMessage(mContext);
                }
            }
        });
    }

    private boolean checkValidation() {

        try {

            if (getTrimString(binding.tnlNewPassword).length() > 6) {

                if (getTrimString(binding.tnlConfirmPassword).length() > 6) {

                    if (getTrimString(binding.tnlNewPassword).equals(getTrimString(binding.tnlConfirmPassword))) {

                        return true;

                    } else {

                        binding.tnlNewPassword.setError(null);
                        binding.tnlNewPassword.setErrorEnabled(false);
                        binding.tnlConfirmPassword.setError("Password mismatch");
                        binding.tnlConfirmPassword.setErrorEnabled(true);
                        binding.tnlConfirmPassword.requestFocus();

                        return false;
                    }

                } else {

                    binding.tnlNewPassword.setError(null);
                    binding.tnlNewPassword.setErrorEnabled(false);
                    binding.tnlConfirmPassword.setError("Password should be minimum of 6 characters.");
                    binding.tnlConfirmPassword.setErrorEnabled(true);
                    binding.tnlConfirmPassword.requestFocus();

                    return false;
                }

            } else {

                binding.tnlNewPassword.setError("Password should be minimum of 6 characters.");
                binding.tnlNewPassword.setErrorEnabled(true);
                binding.tnlNewPassword.requestFocus();
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
     * Method to set new password
     *
     *
     * */
    private void setAccountPassword() {

        try {

            // start progress indicator
            startProgressDialog(currentActivity, false);

            // prepare request body
            AuthenticationRequestModel.ResetPasswordRequest resetPasswordRequest = new AuthenticationRequestModel().new ResetPasswordRequest();

            resetPasswordRequest.setUserId(userId);
            resetPasswordRequest.setNewPassword(getTrimString(binding.tnlConfirmPassword));

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse> call = apiService.resetPassword(resetPasswordRequest);

            // call API
            ApiManager.callRetrofit(call, ApiConstant.API_RESET_PASSWORD, this, false);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_RESET_PASSWORD:

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

                    displayShortToast(mContext, "Password reset successfully");
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

    @Override
    public void onBackPressed() {

        // show prompt dialog
        showPrompt();
    }

    /*
     * Method to show prompt dialog
     *
     *
     * */
    private void showPrompt() {

        simpleAlertDialog = new SimpleAlertDialog(mContext) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return "Are you sure you want to go back?";
            }

            @Override
            public String setDialogMessage() {
                return "Be aware, All your data will be lost! Press OK to continue, or Cancel to stay on this screen.";
            }

            @Override
            public Drawable setDialogIcon() {
                return mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return mContext.getResources().getString(R.string.btn_title_ok);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    // hide soft keyboard
                    hideSoftKeyboard();

                    dialog.dismiss();
                    finish();
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return mContext.getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {

                return (dialog, which) -> {

                    // hide soft keyboard
                    hideSoftKeyboard();

                    dialog.dismiss();
                };
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
