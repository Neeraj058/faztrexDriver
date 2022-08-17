package com.courier365cloud.faztrex.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityLoginBinding;
import com.courier365cloud.faztrex.network.model.request.AuthenticationRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.user.User;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.ui.activity.HomeActivity;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppPreference;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import java.util.Objects;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

public class LoginActivity extends BaseActivity implements
        ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityLoginBinding binding;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        // set text view click listener for footer content
        binding.tvFooterContent.setMovementMethod(LinkMovementMethod.getInstance());

        binding.btnLogin.setAlpha(0.7f);
        binding.btnLogin.setEnabled(false);

        Objects.requireNonNull(binding.tnlUsername.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (getTrimString(binding.tnlUsername).length() > 0 && getTrimString(binding.tnlPassword).length() > 0) {

                    binding.btnLogin.setAlpha(1f);
                    enableView(binding.btnLogin);

                } else {

                    binding.btnLogin.setAlpha(0.7f);
                    disableView(binding.btnLogin);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(binding.tnlPassword.getEditText()).addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (getTrimString(binding.tnlUsername).length() > 0 && getTrimString(binding.tnlPassword).length() > 0) {

                    binding.btnLogin.setAlpha(1f);
                    enableView(binding.btnLogin);

                } else {

                    binding.btnLogin.setAlpha(0.7f);
                    disableView(binding.btnLogin);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.btnLogin.setOnClickListener(v -> {

            // hide soft keyboard
            hideSoftKeyboard();

            if (NetworkUtils.isConnected(mContext)) {

                // call method to perform authentication
                authenticateUser();

            } else {
                displayInternetToastMessage(mContext);
            }
        });

        binding.tvForgotPassword.setOnClickListener(v -> startActivity(new Intent(mContext, ForgotPasswordActivity.class)));
    }

    /*
     * Method to perform authentication
     *
     *
     * */
    private void authenticateUser() {

        try {

            // start progress indicator
            startProgressDialog(currentActivity, false);

            // prepare request body
            AuthenticationRequestModel.LoginRequest loginRequest = new AuthenticationRequestModel().new LoginRequest();

            loginRequest.setUserName(getTrimString(binding.tnlUsername));
            loginRequest.setPassword(getTrimString(binding.tnlPassword));

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse<User>> call = apiService.authenticateUser(loginRequest);

            // call API
            ApiManager.callRetrofit(call, ApiConstant.API_LOGIN, this, false);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_LOGIN:

                CommonResponse<User> loginResponse = (CommonResponse<User>) responseBody;
                processLoginResponse(loginResponse);
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

    private void processLoginResponse(CommonResponse<User> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    User user = response.getData();

                    if (user != null) {

                        AppPreference.getInstance().setBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_login), true);
                        AppPreference.getInstance().setBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_first_login), true);
                        AppPreference.getInstance().setStringPreference(mContext, mContext.getResources().getString(R.string.pref_user_data), new Gson().toJson(user));

                        // call method to start navigation
                        startNavigation();
                    }

                    break;

                case AppConstant.STATUS_FAILURE:

                    displayErrorDialog(getStringValue(response.getMessage()));
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void startNavigation() {

        startActivity(new Intent(mContext, HomeActivity.class));
        finish();
    }

    /*
     * Method to display error dialog
     *
     *
     * */
    private void displayErrorDialog(String errorMessage) {

        String dialogTitle, dialogMessage;

        if (!isEmptyString(errorMessage) && errorMessage.contains(":")) {

            dialogTitle = errorMessage.split(":")[0];
            dialogMessage = errorMessage.split(":")[1];

        } else {

            dialogTitle = mContext.getResources().getString(R.string.dialog_title_authentication_failed);
            dialogMessage = errorMessage;
        }

        simpleAlertDialog = new SimpleAlertDialog(mContext) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return dialogTitle;
            }

            @Override
            public String setDialogMessage() {
                return dialogMessage;
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


    /*private void setFunnelChart() {

        try {

            activityLoginBinding.anyChartView.setProgressBar(activityLoginBinding.progressBar);

            Funnel funnel = AnyChart.funnel();

            ChartCredits credits = funnel.credits();
            credits.enabled(false);

            List<DataEntry> data = new ArrayList<>();
            data.add(new ValueDataEntry("Website Visits", 528756));
            data.add(new ValueDataEntry("Downloads", 164052));
            data.add(new ValueDataEntry("Valid Contacts", 112167));
            data.add(new ValueDataEntry("Interested to Buy", 79128));
            data.add(new ValueDataEntry("Purchased", 79128));

            funnel.data(data);

            funnel.margin(new String[]{"10", "20%", "10", "20%"});
            funnel.baseWidth("70%")
                    .neckWidth("17%");

            funnel.labels()
                    .position("outsideleft")
                    .format("{%X} - {%Value}");

            funnel.animation(true);

            activityLoginBinding.anyChartView.setChart(funnel);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }*/
}
