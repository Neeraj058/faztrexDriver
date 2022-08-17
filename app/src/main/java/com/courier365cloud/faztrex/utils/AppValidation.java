package com.courier365cloud.faztrex.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputLayout;
import com.courier365cloud.faztrex.R;

import java.util.regex.Pattern;

import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

@SuppressLint("Registered")
public class AppValidation {

    @SuppressLint("StaticFieldLeak")
    private static AppValidation mValidation;
    private final String TAG = this.getClass().getSimpleName();
    private String mandatoryFieldMsg = "";

    public static AppValidation getInstance() {

        if (mValidation == null) {
            mValidation = new AppValidation();
        }

        return mValidation;
    }

    /*
     * Method to validate pattern
     *
     * */
    public boolean validatePattern(Context context, TextInputLayout inputLayout, Pattern inputPattern, String errorMsg1, String errorMsg2, boolean isOptional) {

        if (inputLayout != null) {

            String strName = AppUtils.getTrimString(inputLayout);

            if (isOptional && strName.length() == 0) {

                setError(context, inputLayout, null, false);
                return true;

            } else if (!isOptional && strName.length() == 0) {

                setError(context, inputLayout, errorMsg1, true);
                return false;

            } else if (!inputPattern.matcher(strName).matches()) {

                setError(context, inputLayout, errorMsg2, true);
                return false;

            } else {

                setError(context, inputLayout, null, false);
                return true;
            }
        }

        return false;
    }

    /*
     * Method to validate mobile number
     *
     * */
    public boolean validateMobile(Context context, TextInputLayout inputLayout, String errorMsg) {

        if (inputLayout != null) {

            String strName = AppUtils.getTrimString(inputLayout);

            mandatoryFieldMsg = context.getResources().getString(R.string.val_msg_mandatory_fields);

            if (strName.length() == 0) {

                setError(context, inputLayout, mandatoryFieldMsg, true);
                return false;

            } else if (strName.length() < 6 || strName.length() > 13) {

                setError(context, inputLayout, errorMsg, true);
                return false;

            } else {

                setError(context, inputLayout, null, false);
                return true;
            }
        }

        return false;
    }

    /*
     * Method to validate length
     *
     * */
    public boolean validateLength(Context context, TextInputLayout inputLayout, String errorMsg1, String errorMsg2, int minLength, int maxLength, boolean isOptional) {

        if (inputLayout != null) {

            String strName = AppUtils.getTrimString(inputLayout);

            if (isEmptyString(strName) && !isOptional) {

                setError(context, inputLayout, errorMsg1, true);
                return false;

            } else if (isEmptyString(strName) && isOptional) {

                setError(context, inputLayout, null, false);
                return true;

            } else if (!isEmptyString(strName) && strName.length() > 0) {

                if (strName.length() < minLength || strName.length() > maxLength) {

                    setError(context, inputLayout, errorMsg2, true);
                    return false;

                } else {

                    setError(context, inputLayout, null, false);
                    return true;
                }

            } else {

                setError(context, inputLayout, null, false);
                return true;
            }
        }

        return false;
    }

    /*
     * Method to validate amount
     *
     * */
    public boolean validateAmount(Context context, TextInputLayout inputLayout, String errorMsg1, String errorMsg2) {

        if (inputLayout != null) {

            String strName = AppUtils.getTrimString(inputLayout);

            if (isEmptyString(strName)) {

                inputLayout.requestFocus();
                setError(context, inputLayout, errorMsg1, true);
                return false;

            } else if (Double.parseDouble(strName) == 0) {

                inputLayout.requestFocus();
                setError(context, inputLayout, errorMsg2, true);
                return false;

            } else {

                setError(context, inputLayout, null, false);
                return true;
            }
        }

        return false;
    }

    /*
     * Method to validate string field
     *
     * */
    public boolean validateString(Context context, TextInputLayout inputLayout, String errorMsg) {

        if (inputLayout != null) {

            String strName = AppUtils.getTrimString(inputLayout);

            if (isEmptyString(strName)) {

                setError(context, inputLayout, errorMsg, true);
                return false;

            } else {

                setError(context, inputLayout, null, false);
                return true;
            }
        }

        return false;
    }

    /*
     * Method to Set Error in TextInputLayout
     *
     * */
    private void setError(Context context, TextInputLayout inputLayout, String errorMessage, boolean isEnabled) {

        try {

            ViewGroup.LayoutParams lp = inputLayout.getLayoutParams();

            if (isEnabled) {

                inputLayout.setErrorEnabled(true);
                inputLayout.setError(errorMessage);
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                inputLayout.setLayoutParams(lp);
                inputLayout.requestFocus();

            } else {

                inputLayout.setErrorEnabled(false);
                inputLayout.setError(null);
                lp.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, context.getResources().getDisplayMetrics());
                inputLayout.setLayoutParams(lp);
                inputLayout.clearFocus();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }
}
