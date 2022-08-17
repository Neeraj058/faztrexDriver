package com.courier365cloud.faztrex.baseclass;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.customviews.CustomDialog;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.network.model.response.user.User;
import com.courier365cloud.faztrex.utils.AppPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.courier365cloud.faztrex.utils.AppUtils.getTrimString;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

public abstract class BaseFragment extends Fragment {

    private final String TAG = this.getClass().getSimpleName();

    public SimpleAlertDialog simpleAlertDialog = null;

    public CustomDialog customDialog = null;

    public View view;

    public static Fragment currentFragment;

    public boolean prefIsLogin;

    public User prefUserModel;

    /*
     * Method to get preference data
     *
     * */
    public void getPreferenceData() {

        try {

            // get data from preference
            String userDataJson = AppPreference.getInstance().getStringPreference(Objects.requireNonNull(getActivity()), getActivity().getResources().getString(R.string.pref_user_data));
            User user = new Gson().fromJson(userDataJson, User.class);

            prefIsLogin = AppPreference.getInstance().getBooleanPreference(Objects.requireNonNull(getActivity()), getResources().getString(R.string.pref_is_login));

            if (user != null) {
                prefUserModel = user;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to finish fragment
     *
     * */
    public void finishFragment() {

        try {

            Fragment currentFragment = Objects.requireNonNull(getActivity()).getSupportFragmentManager().findFragmentById(R.id.content);

            /*if (!(currentFragment instanceof DashboardFragment)) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }

            if (currentFragment == null) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }*/

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to display short toast message
     *
     * */
    public void displayShortToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_SHORT).show();
    }

    /*
     * Method to display long toast message
     *
     * */
    public void displayLongToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    /*
     * Method to display no internet toast messages
     *
     * */
    public void displayInternetToastMessage(Context context) {

        Toast.makeText(context, getResources().getString(R.string.msg_no_internet), Toast.LENGTH_SHORT).show();
    }

    public void printErrorLog(String tag, String msg) {
        Log.e(tag, msg);
    }

    public void printInfoLog(String tag, String msg) {
        Log.i(tag, msg);
    }

    public void printVerboseLog(String tag, String msg) {
        Log.v(tag, msg);
    }

    public void printDebugLog(String tag, String msg) {
        Log.d(tag, msg);
    }

    /*
     * Method to open DatePickerDialog
     *
     *
     * */
    protected void openDatePickerDialog(final TextInputLayout textInputLayout, final String outputDateFormat) {

        try {

            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputDateFormat, Locale.ENGLISH);

            if (textInputLayout != null) {

                final Calendar myCalendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {

                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, month);
                    myCalendar.set(Calendar.DAY_OF_MONTH, day);

                    Objects.requireNonNull(textInputLayout.getEditText()).setText(simpleDateFormat.format(myCalendar.getTime()));
                };

                if (!isEmptyString(getTrimString(textInputLayout))) {

                    Calendar cal = Calendar.getInstance();

                    cal.setTime(simpleDateFormat.parse(getTrimString(textInputLayout)));

                    DatePickerDialog mDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), R.style.CustomCalendar, date, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                    mDialog.getDatePicker().setMinDate(new Date().getTime() - (1000 * 60 * 60 * 24));
                    mDialog.getDatePicker().setMaxDate(new Date().getTime());
                    mDialog.show();

                } else {

                    DatePickerDialog mDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), R.style.CustomCalendar, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                    mDialog.getDatePicker().setMinDate(new Date().getTime() - (1000 * 60 * 60 * 24));
                    mDialog.getDatePicker().setMaxDate(new Date().getTime());
                    mDialog.show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}
