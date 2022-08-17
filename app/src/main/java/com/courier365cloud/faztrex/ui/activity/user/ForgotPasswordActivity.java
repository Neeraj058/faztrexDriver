package com.courier365cloud.faztrex.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityForgotPasswordBinding;
import com.courier365cloud.faztrex.network.model.request.AuthenticationRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.user.ForgotPassword;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import java.util.Objects;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

public class ForgotPasswordActivity extends BaseActivity implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityForgotPasswordBinding binding;

    private String verificationCode = "", userId = "";

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);

        binding.btnProceed.setAlpha(0.7f);
        disableView(binding.btnProceed);

        Objects.requireNonNull(binding.tnlEmailAddress.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String emailAddress = getTrimString(binding.tnlEmailAddress);

                if (AppConstant.PATTERN_EMAIL.matcher(emailAddress).matches()) {

                    binding.btnProceed.setAlpha(1f);
                    enableView(binding.btnProceed);
                    visibleView(binding.ivRightIcon);

                } else {

                    binding.btnProceed.setAlpha(0.7f);
                    disableView(binding.btnProceed);
                    hideView(binding.ivRightIcon);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnProceed.setOnClickListener(v -> {

            // hide soft keyboard
            hideSoftKeyboard();

            if (NetworkUtils.isConnected(mContext)) {

                // call method to send verification code on email address
                sendVerificationCode();

            } else {
                displayInternetToastMessage(mContext);
            }
        });
    }

    /*
     * Method to send verification code on email address
     *
     *
     * */
    private void sendVerificationCode() {

        try {

            // start progress indicator
            startProgressDialog(currentActivity, false);

            // prepare request body
            AuthenticationRequestModel.ForgotPasswordRequest forgotPasswordRequest = new AuthenticationRequestModel().new ForgotPasswordRequest();

            forgotPasswordRequest.setEmailAddress(getTrimString(binding.tnlEmailAddress));

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse<ForgotPassword>> call = apiService.sendVerificationCode(forgotPasswordRequest);

            // call API
            ApiManager.callRetrofit(call, ApiConstant.API_FORGOT_PASSWORD, this, false);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_FORGOT_PASSWORD:

                CommonResponse<ForgotPassword> forgotPasswordResponse = (CommonResponse<ForgotPassword>) responseBody;
                processForgotResponse(forgotPasswordResponse);
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

    private void processForgotResponse(CommonResponse<ForgotPassword> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    verificationCode = response.getData().getVerificationCode();
                    userId = response.getData().getUserId();

                    printInfoLog(TAG, "Verification Code is : " + verificationCode);

                    if (!isEmptyString(verificationCode)) {
                        startNavigation();
                    }

                    break;

                case AppConstant.STATUS_FAILURE:

                    showPrompt(getStringValue(response.getMessage()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void startNavigation() {

        Intent mIntent = new Intent(mContext, VerificationActivity.class);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_user_id), getStringValue(userId));
        mIntent.putExtra(mContext.getResources().getString(R.string.key_email_address), getTrimString(binding.tnlEmailAddress));
        mIntent.putExtra(mContext.getResources().getString(R.string.key_verification_code), getStringValue(verificationCode));
        startActivity(mIntent);
        finish();
    }

    private void showPrompt(String promptMessage) {

        simpleAlertDialog = new SimpleAlertDialog(mContext) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return "Uh Oh";
            }

            @Override
            public String setDialogMessage() {
                return promptMessage;
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
                return (dialog, which) -> dialog.dismiss();
            }

            @Override
            public String setDialogNegativeButtonText() {
                return null;
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {
                return null;
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
