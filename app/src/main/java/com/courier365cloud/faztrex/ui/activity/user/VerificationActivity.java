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
import com.courier365cloud.faztrex.databinding.ActivityVerificationBinding;

import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;

public class VerificationActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivityVerificationBinding binding;

    private String userId = "", userEmailAddress = "", verificationCode = "";

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_verification);

        // get intent values
        userId = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_user_id));
        userEmailAddress = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_email_address));
        verificationCode = getIntent().getStringExtra(mContext.getResources().getString(R.string.key_verification_code));

        binding.otpView.setOtpCompletionListener(otp -> {

            if (otp.equals(verificationCode)) {

                startNavigation();

            } else {

                displayShortToast(mContext, "It seems that you have entered incorrect OTP. Please check your OTP.");
            }
        });
    }

    private void startNavigation() {

        Intent mIntent = new Intent(mContext, ResetPasswordActivity.class);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_user_id), getStringValue(userId));
        startActivity(mIntent);
        finish();
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
