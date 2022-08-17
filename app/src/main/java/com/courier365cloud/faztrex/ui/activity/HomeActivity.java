package com.courier365cloud.faztrex.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityHomeBinding;
import com.courier365cloud.faztrex.network.model.request.AuthenticationRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.user.User;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.ui.activity.user.ChangePasswordActivity;
import com.courier365cloud.faztrex.ui.activity.user.LoginActivity;
import com.courier365cloud.faztrex.ui.fragment.DrawerFragment;
import com.courier365cloud.faztrex.ui.fragment.HomeFragment;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

public class HomeActivity extends BaseActivity
        implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    // Change END_SCALE value if you want to show animation to main content view as small layout
    private static final float END_SCALE = 0.7f;

    private final Context mContext = this;

    private ActivityHomeBinding binding;

    private DrawerFragment drawerFragment;
    private HomeFragment homeFragment;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (!task.isSuccessful())
                    return;

                String token = task.getResult();
                printDebugLog("Firebase TOKEN", token);
                appPreference.setStringPreference(mContext, "token", token);

            }
        });

        setUpNavigationDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setUpNavigationDrawer() {

        try {

            binding.tvNavigationHeaderTitle.setText(getResources().getString(R.string.nav_header_title_home));

            setSupportActionBar(binding.toolbar);

            binding.toolbar.setNavigationIcon(new DrawerArrowDrawable(mContext));
            binding.toolbar.setNavigationOnClickListener(view -> {

                if (binding.drawerLayout.isDrawerOpen(binding.navigationViewContainer))
                    binding.drawerLayout.closeDrawer(binding.navigationViewContainer);
                else
                    binding.drawerLayout.openDrawer(binding.navigationViewContainer);
            });

            //homeBinding.drawerLayout.setScrimColor(Color.TRANSPARENT);
            binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {

                    // Scale the View based on current slide offset
                    /*final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                    final float offsetScale = 1 - diffScaledOffset;
                    binding.holder.setScaleX(offsetScale);
                    binding.holder.setScaleY(offsetScale);*/

                    // Translate the View, accounting for the scaled width
                    /*final float xOffset = drawerView.getWidth() * slideOffset;
                    final float xOffsetDiff = binding.holder.getWidth() * diffScaledOffset / 2;
                    final float xTranslation = xOffset - xOffsetDiff;
                    binding.holder.setTranslationX(xTranslation);*/
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    hideSoftKeyboard();
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    //binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                }
            });

            doYourWork();

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void doYourWork() {

        try {

            getPreferenceData();

            drawerFragment = new DrawerFragment();
            homeFragment = new HomeFragment();

            setFragment(drawerFragment, TAG, R.id.navigation_view_container);
            setFragment(homeFragment, TAG, R.id.content);

            boolean isFirstLogin = AppPreference.getInstance().getBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_first_login));

            if (!isFirstLogin)
                authenticateUser();
            else
                AppPreference.getInstance().setBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_first_login), false);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_change_password) {
            startActivity(new Intent(mContext, ChangePasswordActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean closeDrawer() {

        try {

            if (binding.drawerLayout != null && binding.drawerLayout.isDrawerOpen(binding.navigationViewContainer)) {
                binding.drawerLayout.closeDrawer(binding.navigationViewContainer);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if (closeDrawer()) {

            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content);

            if (currentFragment instanceof HomeFragment) {

                if (!doubleBackToExitPressedOnce) {

                    this.doubleBackToExitPressedOnce = true;
                    displayShortToast(mContext, getResources().getString(R.string.msg_back_pressed));

                    new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 1000);

                } else
                    finishAffinity();

            } else
                super.onBackPressed();
        }
    }

    private void authenticateUser() {

        try {

            startProgressDialog(currentActivity, false);

            AuthenticationRequestModel.LoginRequest loginRequest = new AuthenticationRequestModel().new LoginRequest();

            loginRequest.setUserName(getStringValue(prefUserModel.getUsername()));
            loginRequest.setPassword(getStringValue(prefUserModel.getPassword()));

            ApiService apiService = ApiClient.createService(ApiService.class, "", "");
            Call<CommonResponse<User>> call = apiService.authenticateUser(loginRequest);

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
        displayLongToast(mContext, errorMessage);
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        printErrorLog(TAG, failureMessage);
        displayLongToast(mContext, mContext.getResources().getString(R.string.err_msg_api_response_failure));
    }

    private void processLoginResponse(CommonResponse<User> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    User user = response.getData();

                    if (user != null)
                        AppPreference.getInstance().setStringPreference(mContext, mContext.getResources().getString(R.string.pref_user_data), new Gson().toJson(user));
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

            @SuppressLint("UseCompatLoadingForDrawables")
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

                    dialog.dismiss();

                    // clear all preference data
                    AppPreference.getInstance().clearPreferences(currentActivity);

                    startActivity(new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    finishAffinity();
                };
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
