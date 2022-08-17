package com.courier365cloud.faztrex.baseclass;

import static com.cittasolutions.cittalibrary.utils.AppUtils.isValidString;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_DRIVER_TRACKING;
import static com.courier365cloud.faztrex.utils.AppConstant.REQUEST_CODE_GPS_ENABLE;
import static com.courier365cloud.faztrex.utils.AppUtils.castToInteger;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.customviews.CustomDialog;
import com.courier365cloud.faztrex.customviews.CustomProgressDialog;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ToolbarMainBinding;
import com.courier365cloud.faztrex.helper.DeviceDetailsManager;
import com.courier365cloud.faztrex.helper.GPSManager;
import com.courier365cloud.faztrex.helper.PermissionManager;
import com.courier365cloud.faztrex.listener.GPSLocationListener;
import com.courier365cloud.faztrex.listener.PermissionGrantedListener;
import com.courier365cloud.faztrex.network.model.request.DriverTrackingForm;
import com.courier365cloud.faztrex.network.model.request.MasterRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonListResponse;
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
import com.firebase.client.Firebase;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;

public abstract class BaseActivity extends AppCompatActivity
        implements ApiListener, GPSLocationListener, PermissionGrantedListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 2;
    @SuppressLint("StaticFieldLeak")
    public static Activity currentActivity;
    //region Location
    @SuppressLint("StaticFieldLeak")
    public static GPSManager gpsManager;
    @SuppressLint("StaticFieldLeak")
    public static FusedLocationProviderClient fusedLocationClient;
    public static int hyperLocalRequestId;
    // common array list to hold spinner values
    public static ArrayList<CommonListResponse> stateMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> cityMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> postcodeMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> verticleMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> packingTypeMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> reasonMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> deliveryTypeMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> deliveryStatusMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> paymentTypeMasterList = new ArrayList<>();
    public static ArrayList<CommonListResponse> bankMasterList = new ArrayList<>();
    private static CustomProgressDialog myProgressDialog = null;
    private static LocationCallback locationCallback;
    private final String TAG = this.getClass().getSimpleName();
    //endregion
    private final Context mContext = BaseActivity.this;
    private final List<WeakReference<Fragment>> weakReferenceFragmentList = new ArrayList<>();
    public FragmentManager fragmentManager;
    public SimpleAlertDialog simpleAlertDialog = null;
    public CustomDialog customDialog = null;
    public User prefUserModel;
    public boolean prefIsLogin;
    public PermissionManager permissionManager;
    public AppPreference appPreference;
    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentsList = new ArrayList<>();
    private Location currentLocation;
    // params to set spinner values
    private String spinnerType;
    private Map<String, Integer> inputParams;
    private AppCompatSpinner spinner;

    public static void startProgressDialog(Activity activity, boolean cancelable) {
        try {
            if (myProgressDialog == null)
                myProgressDialog = CustomProgressDialog.show(activity, cancelable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopProgressDialog() {
        try {
            if (myProgressDialog != null && myProgressDialog.isShowing()) {
                myProgressDialog.dismiss();
                myProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideSoftKeyboard() {

        try {

            InputMethodManager imm = (InputMethodManager) currentActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);

            //Find the currently focused view, so we can grab the correct window token from it.
            View view = currentActivity.getCurrentFocus();

            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(currentActivity);
            }

            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean hasPermissions(@Nullable Context context, @Nullable String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {

            for (String permission : permissions) {

                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                    return true;
            }
        }

        return false;
    }

    public static void visibleView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hideView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    public static void enableView(View view) {
        if (view != null) {
            view.setEnabled(true);
        }
    }

    public static void disableView(View view) {
        if (view != null) {
            view.setEnabled(false);
        }
    }

    public static void disableMultipleViews(ViewGroup view) {

        try {

            if (view != null) {

                for (int i = 0; i < view.getChildCount(); i++) {

                    View child = view.getChildAt(i);

                    if (child instanceof LinearLayoutCompat) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof RelativeLayout) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof ScrollView) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof NestedScrollView) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof MaterialCardView) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof RadioGroup) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof AppCompatSpinner) {
                        disableMultipleViews((ViewGroup) child);
                    }

                    if (child instanceof TextInputLayout) {

                        Objects.requireNonNull(((TextInputLayout) child).getEditText()).setEnabled(false);
                        Objects.requireNonNull(((TextInputLayout) child).getEditText()).setFocusable(false);

                    } else if (child instanceof AppCompatRadioButton) {

                        child.setClickable(false);

                    } else {

                        child.setEnabled(false);
                        child.setFocusable(false);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // method to set current activity
    public abstract Activity setCurrentActivity();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = setCurrentActivity();

        // hide keyboard
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Firebase.setAndroidContext(mContext);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        appPreference = AppPreference.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentActivity instanceof HomeActivity) {
            permissionManager = new PermissionManager(mContext, this);
            permissionManager.checkSinglePermission(Manifest.permission.ACCESS_FINE_LOCATION);
        } else
            startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (fusedLocationClient != null && locationCallback != null)
            fusedLocationClient.removeLocationUpdates(locationCallback);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void initToolbar(ToolbarMainBinding toolbarMainBinding, String headerTitle) {

        try {

            setSupportActionBar(toolbarMainBinding.toolbarMain);

            toolbarMainBinding.tvHeaderTitle.setText(headerTitle);

            toolbarMainBinding.ivBack.setOnClickListener(view -> onBackPressed());

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public void displayShortToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(Context context, String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    public void displayInternetToastMessage(Context context) {

        Toast.makeText(context, getResources().getString(R.string.msg_no_internet), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("WrongConstant")
    public void setFragment(Fragment fragment, final String backStateName, int containerId) {

        try {

            boolean isCalled = false;
            fragmentsList = new ArrayList<>();
            fragmentManager = getSupportFragmentManager();
            fragmentsList = getActiveFragments();

            for (Fragment fragments : fragmentsList) {

                if (fragments.getClass().getName().equalsIgnoreCase(backStateName))
                    isCalled = true;
            }

            if (!isCalled) {

                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(containerId, fragment);
                fragmentTransaction.setTransition(R.style.WindowAnimationTransition);
                fragmentTransaction.addToBackStack(backStateName);
                weakReferenceFragmentList.add(new WeakReference(fragment));
                fragmentTransaction.commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public void replaceFragments(Class fragmentClass, int containerId) {

        try {

            Fragment fragment = (Fragment) fragmentClass.newInstance();
            // Insert the fragment by replacing any existing fragment
            fragmentManager.beginTransaction().replace(containerId, fragment).commit();

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void clearBackStack() {

        fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {

            FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
            fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public List<Fragment> getActiveFragments() {

        ArrayList<Fragment> returnFragments = new ArrayList<>();

        for (WeakReference<Fragment> weakReference : weakReferenceFragmentList) {

            Fragment weakFragments = weakReference.get();

            if (weakFragments != null) {
                if (weakFragments.isVisible())
                    returnFragments.add(weakFragments);
            }
        }

        return returnFragments;
    }

    public void getPreferenceData() {

        try {

            // get data from preference
            String userDataJson = AppPreference.getInstance().getStringPreference(mContext, mContext.getResources().getString(R.string.pref_user_data));
            User user = new Gson().fromJson(userDataJson, User.class);

            prefIsLogin = AppPreference.getInstance().getBooleanPreference(mContext, getResources().getString(R.string.pref_is_login));

            if (user != null)
                prefUserModel = user;

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public void openDatePickerDialog(final TextInputLayout textInputLayout, final String outputDateFormat) {

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

                    new DatePickerDialog(mContext, R.style.CustomCalendar, date, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();

                } else
                    new DatePickerDialog(mContext, R.style.CustomCalendar, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public String getTrimString(TextInputLayout inputLayout) {
        return Objects.requireNonNull(inputLayout.getEditText()).getText().toString().trim();
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
    // endregion

    // region method to handle API response

    // region get spinner data from server
    public void getSpinnerList(String spinnerType, Map<String, Integer> inputParams, AppCompatSpinner spinner) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                this.spinnerType = spinnerType;
                this.inputParams = inputParams;
                this.spinner = spinner;

                // call method to get preference data
                getPreferenceData();

                // start progress indicator
                startProgressDialog(this, false);

                Call<CommonResponse<ArrayList<CommonListResponse>>> call;

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                switch (spinnerType) {

                    case AppConstant.SP_STATE:

                        MasterRequestModel.GetStateRequest stateRequest = new MasterRequestModel().new GetStateRequest();
                        stateRequest.setCountryId(AppConstant.COUNTRY_ID);
                        call = apiService.getState(stateRequest);
                        ApiManager.callRetrofit(call, ApiConstant.API_STATE, this, false);
                        break;

                    case AppConstant.SP_CITY:

                        MasterRequestModel.GetCityRequest cityRequest = new MasterRequestModel().new GetCityRequest();
                        cityRequest.setStateId(String.valueOf(inputParams.get(AppConstant.KEY_STATE_ID)));
                        call = apiService.getCity(cityRequest);
                        ApiManager.callRetrofit(call, ApiConstant.API_CITY, this, false);
                        break;

                    case AppConstant.SP_POSTCODE:

                        MasterRequestModel.GetPostcodeRequest postcodeRequest = new MasterRequestModel().new GetPostcodeRequest();
                        postcodeRequest.setCityId(String.valueOf(inputParams.get(AppConstant.KEY_CITY_ID)));
                        call = apiService.getPostcode(postcodeRequest);
                        ApiManager.callRetrofit(call, ApiConstant.API_POST_CODE, this, false);
                        break;

                    case AppConstant.SP_PACKING_TYPE:

                        call = apiService.getPackingType();
                        ApiManager.callRetrofit(call, ApiConstant.API_PACKING_TYPE, this, false);
                        break;

                    case AppConstant.SP_REASON:

                        call = apiService.getReason();
                        ApiManager.callRetrofit(call, ApiConstant.API_REASON, this, false);
                        break;

                    case AppConstant.SP_VERTICLE:

                        call = apiService.getVerticle();
                        ApiManager.callRetrofit(call, ApiConstant.API_VERTICLE, this, false);
                        break;

                    case AppConstant.SP_DELIVERY_TYPE:

                        stopProgressDialog();
                        setSpinnerHeader(AppConstant.SP_DELIVERY_TYPE);
                        break;

                    case AppConstant.SP_DELIVERY_STATUS:

                        stopProgressDialog();
                        setSpinnerHeader(AppConstant.SP_DELIVERY_STATUS);
                        break;

                    case AppConstant.SP_PAYMENT_TYPE:

                        call = apiService.getPaymentType();
                        ApiManager.callRetrofit(call, ApiConstant.API_PAYMENT_TYPE, this, false);
                        break;

                    case AppConstant.SP_BANK:

                        call = apiService.getPaymentType();
                        ApiManager.callRetrofit(call, ApiConstant.API_BANK, this, false);
                        break;
                }

            } else {
                displayInternetToastMessage(mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // region success
    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        CommonResponse<ArrayList<CommonListResponse>> commonResponse;

        switch (endPointName) {

            case ApiConstant.API_STATE:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_CITY:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_POST_CODE:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_PACKING_TYPE:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_REASON:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_VERTICLE:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_PAYMENT_TYPE:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case ApiConstant.API_BANK:

                commonResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processResponse(endPointName, commonResponse);
                break;

            case API_DRIVER_TRACKING:
                CommonResponse<String> manageResponse = (CommonResponse<String>) responseBody;
                processTrackingResponse(manageResponse);
                break;

        }
    }

    // endregion

    // region error

    private void processTrackingResponse(CommonResponse<String> response) {

        if (response != null) {

            if (response.getStatus().equalsIgnoreCase("1")) {
                printInfoLog(TAG, "Location Added Successfully.");
                //displayLongToast(mContext, "Location Added Successfully");
            }
        }
    }

    // endregion

    // region failure

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        printErrorLog(TAG, "Something went wrong in : " + endPointName + " " + errorMessage);

        switch (endPointName) {

            case ApiConstant.API_STATE:

                stateMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_CITY:

                cityMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_POST_CODE:

                postcodeMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_PACKING_TYPE:

                packingTypeMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_REASON:

                reasonMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_VERTICLE:

                verticleMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_PAYMENT_TYPE:

                paymentTypeMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_BANK:

                bankMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case API_DRIVER_TRACKING:
                break;
        }
    }

    // endregion

    // endregion

    // region process API response

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, "Something went wrong in : " + endPointName + " " + failureMessage);

        switch (endPointName) {

            case ApiConstant.API_STATE:

                stateMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_CITY:

                cityMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_POST_CODE:

                postcodeMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_PACKING_TYPE:

                packingTypeMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_REASON:

                reasonMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_VERTICLE:

                verticleMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_PAYMENT_TYPE:

                paymentTypeMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case ApiConstant.API_BANK:

                bankMasterList = new ArrayList<>();
                setSpinnerHeader(endPointName);
                break;

            case API_DRIVER_TRACKING:
                break;
        }
    }

    // endregion

    // region bind spinner data

    /*
     * Method to process API response
     *
     *
     * */
    private void processResponse(String endPointName, CommonResponse<ArrayList<CommonListResponse>> commonResponse) {

        try {

            switch (endPointName) {

                case ApiConstant.API_STATE:

                    stateMasterList = new ArrayList<>();
                    stateMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_CITY:

                    cityMasterList = new ArrayList<>();
                    cityMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_POST_CODE:

                    postcodeMasterList = new ArrayList<>();
                    postcodeMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_PACKING_TYPE:

                    packingTypeMasterList = new ArrayList<>();
                    packingTypeMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_REASON:

                    reasonMasterList = new ArrayList<>();
                    reasonMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_VERTICLE:

                    verticleMasterList = new ArrayList<>();
                    verticleMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_PAYMENT_TYPE:

                    paymentTypeMasterList = new ArrayList<>();
                    paymentTypeMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;

                case ApiConstant.API_BANK:

                    bankMasterList = new ArrayList<>();
                    bankMasterList = commonResponse.getData();
                    setSpinnerHeader(endPointName);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // endregion

    // region set spinner header

    /*
     * Method to bind spinner data from API response
     *
     *
     * */
    public void bindSpinnerData(String spinnerType, AppCompatSpinner spinner, ArrayList<CommonListResponse> dataList, int refId) {

        try {

            ArrayList<String> itemSet = new ArrayList<>();

            int selectedPosition = 0, currentItemPosition = -1;

            for (CommonListResponse data : dataList) {

                currentItemPosition = currentItemPosition + 1;

                if (spinnerType.equalsIgnoreCase(AppConstant.SP_POSTCODE))
                    itemSet.add(getStringValue(data.getPostcode()));
                else
                    itemSet.add(getStringValue(data.getName()));

                if (refId > 0 && data.getId().equals(String.valueOf(refId))) {
                    selectedPosition = currentItemPosition;
                    printInfoLog(TAG, "Selected Item Position : " + selectedPosition);
                }
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.custom_spinner_item, itemSet);
            arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_popup);
            spinner.setAdapter(arrayAdapter);

            if (selectedPosition > 0) {
                spinner.setSelection(selectedPosition);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // endregion

    /*
     * Method to set spinner header at first position of spinner
     *
     *
     * */
    private void setSpinnerHeader(String endPointName) {

        try {

            switch (endPointName) {

                case ApiConstant.API_STATE:

                    // set spinner type
                    spinnerType = AppConstant.SP_STATE;
                    spinner = findViewById(R.id.spinner_destination_state);

                    if (stateMasterList == null)
                        stateMasterList = new ArrayList<>();

                    stateMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_state_header_title)));
                    bindSpinnerData(spinnerType, spinner, stateMasterList, 0);

                    break;

                case ApiConstant.API_CITY:

                    // set spinner type
                    spinnerType = AppConstant.SP_CITY;
                    spinner = findViewById(R.id.spinner_destination_city);

                    if (cityMasterList == null)
                        cityMasterList = new ArrayList<>();

                    cityMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_city_header_title)));
                    bindSpinnerData(spinnerType, spinner, cityMasterList, 0);

                    break;

                case ApiConstant.API_POST_CODE:

                    // set spinner type
                    spinnerType = AppConstant.SP_POSTCODE;
                    spinner = findViewById(R.id.spinner_postcode);

                    if (postcodeMasterList == null)
                        postcodeMasterList = new ArrayList<>();

                    postcodeMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_postcode_header_title), mContext.getResources().getString(R.string.spinner_postcode_header_title)));
                    bindSpinnerData(spinnerType, spinner, postcodeMasterList, 0);

                    break;

                case ApiConstant.API_PACKING_TYPE:

                    // set spinner type
                    spinnerType = AppConstant.SP_PACKING_TYPE;
                    spinner = findViewById(R.id.spinner_packing_type);

                    if (packingTypeMasterList == null)
                        packingTypeMasterList = new ArrayList<>();

                    packingTypeMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_packing_type_header_title)));
                    bindSpinnerData(spinnerType, spinner, packingTypeMasterList, 0);

                    break;

                case ApiConstant.API_REASON:

                    // set spinner type
                    spinnerType = AppConstant.SP_REASON;
                    spinner = findViewById(R.id.spinner_undelivered_reason);

                    if (reasonMasterList == null)
                        reasonMasterList = new ArrayList<>();

                    reasonMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_reason_header_title)));
                    bindSpinnerData(spinnerType, spinner, reasonMasterList, 0);

                    break;

                case ApiConstant.API_VERTICLE:

                    // set spinner type
                    spinnerType = AppConstant.SP_VERTICLE;
                    spinner = findViewById(R.id.spinner_vertical);

                    if (verticleMasterList == null)
                        verticleMasterList = new ArrayList<>();

                    verticleMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_verticle_header_title)));
                    bindSpinnerData(spinnerType, spinner, verticleMasterList, 0);

                    break;

                case AppConstant.SP_DELIVERY_TYPE:

                    // set spinner type
                    spinnerType = AppConstant.SP_DELIVERY_TYPE;
                    //spinner = findViewById(R.id.spinner_delivery_type);

                    deliveryTypeMasterList = new ArrayList<>();

                    deliveryTypeMasterList.add(new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_delivery_type_header_title)));
                    deliveryTypeMasterList.add(new CommonListResponse("1", mContext.getResources().getString(R.string.spinner_delivery_type_cod)));
                    deliveryTypeMasterList.add(new CommonListResponse("2", mContext.getResources().getString(R.string.spinner_delivery_type_hub_delivery)));
                    deliveryTypeMasterList.add(new CommonListResponse("3", mContext.getResources().getString(R.string.spinner_delivery_type_dacc)));
                    bindSpinnerData(spinnerType, spinner, deliveryTypeMasterList, 0);

                    break;

                case AppConstant.SP_DELIVERY_STATUS:

                    // set spinner type
                    spinnerType = AppConstant.SP_DELIVERY_STATUS;
                    spinner = findViewById(R.id.spinner_delivery_status);

                    deliveryStatusMasterList = new ArrayList<>();

                    deliveryStatusMasterList.add(new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_delivery_status_header_title)));
                    deliveryStatusMasterList.add(new CommonListResponse("1", mContext.getResources().getString(R.string.spinner_delivery_status_delivered)));
                    deliveryStatusMasterList.add(new CommonListResponse("2", mContext.getResources().getString(R.string.spinner_delivery_status_undelivered)));
                    bindSpinnerData(spinnerType, spinner, deliveryStatusMasterList, 0);

                    break;

                case ApiConstant.API_PAYMENT_TYPE:

                    // set spinner type
                    spinnerType = AppConstant.SP_PAYMENT_TYPE;
                    spinner = findViewById(R.id.spinner_payment_type);

                    if (paymentTypeMasterList == null)
                        paymentTypeMasterList = new ArrayList<>();

                    paymentTypeMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_payment_type_header_title)));
                    bindSpinnerData(spinnerType, spinner, paymentTypeMasterList, 0);

                    break;

                case ApiConstant.API_BANK:

                    // set spinner type
                    spinnerType = AppConstant.SP_BANK;
                    //spinner = findViewById(R.id.spinner_reference);

                    if (bankMasterList == null)
                        bankMasterList = new ArrayList<>();

                    bankMasterList.add(0, new CommonListResponse("0", mContext.getResources().getString(R.string.spinner_reference_title)));
                    bindSpinnerData(spinnerType, spinner, bankMasterList, 0);

                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    public void deleteRecursive(File fileOrDirectory) {

        try {

            if (fileOrDirectory.isDirectory()) {

                for (File child : fileOrDirectory.listFiles())
                    deleteRecursive(child);
            }

            fileOrDirectory.delete();

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    //region methods to check access rights
    public String getAccessRights(String pageName) {

        String appRights = "";

        try {

            appRights = prefUserModel.getGroupEmpWebRights();

            //rights = "leadevent-V-E-D-A,lead-V-E-D-A,leadsource-V-E-D-A";

            String[] appRightsList = appRights.split(",");

            for (String pageRights : appRightsList) {

                if (pageRights.contains(pageName)) {

                    if (pageRights.contains("-")) {

                        String actualPageName = pageRights.split("-")[0];

                        if (pageName.equals(actualPageName)) {

                            appRights = pageRights.substring(actualPageName.length() + 1);
                            return appRights;
                        }

                    } else {
                        appRights = "";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }

        return appRights;
    }

    public boolean checkAccessRights(String pageRights, int operationType) {

        try {

            if (!isEmptyString(pageRights)) {

                switch (operationType) {

                    case AppConstant.R_VIEW:
                        return pageRights.contains("V");

                    case AppConstant.R_EDIT:
                        return pageRights.contains("E");

                    case AppConstant.R_DELETE:
                        return pageRights.contains("D");

                    case AppConstant.R_ADD:
                        return pageRights.contains("A");

                    case AppConstant.R_PRINT:
                        return pageRights.contains("P");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    //endregion

    @Override
    public void onGPSAlreadyEnabled() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                currentLocation = locationResult.getLocations().get(0);
                saveLatLongToServer();
            }
        };
        startLocationUpdates();
    }

    @SuppressWarnings("MissingPermission")
    public void startLocationUpdates() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(15000);
        locationRequest.setFastestInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (fusedLocationClient != null)
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {

            case REQUEST_CODE_GPS_ENABLE:
                switch (resultCode) {

                    case RESULT_OK:
                        locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                currentLocation = locationResult.getLocations().get(0);
                                saveLatLongToServer();
                            }
                        };
                        startLocationUpdates();
                        break;

                    case RESULT_CANCELED:
                        gpsManager.start(this);
                        break;
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveLatLongToServer() {

        try {

            getPreferenceData();

            if (prefUserModel != null && currentLocation != null) {

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> addresses;

                addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1);
                String addressDetails = "";
                if (addresses != null && addresses.size() > 0) {
                    Address address = addresses.get(0);
                    addressDetails = address.getSubLocality() + ", " + address.getSubAdminArea() + " - " + address.getPostalCode() + ", " + address.getAdminArea();
                    //address.getLocality() + ", " +
                }

                DeviceDetailsManager manager = new DeviceDetailsManager(mContext);

                DriverTrackingForm form = new DriverTrackingForm();
                form.setEmployeeid(prefUserModel.getId());
                form.setEmployeename(prefUserModel.getFirstName());
                form.setMobileno(prefUserModel.getMobileNo());

                form.setLatitude(currentLocation.getLatitude());
                form.setLongitude(currentLocation.getLongitude());

                form.setAddressfromlatlong(addressDetails);

                form.setDevicename(android.os.Build.MANUFACTURER + android.os.Build.MODEL);
                form.setDeviceos("Android");
                form.setDeviceosversion(Build.VERSION.RELEASE);
                form.setDeviceipaddress(manager.getDeviceIpAddress());

                form.setCid(castToInteger(prefUserModel.getCid()));
                form.setBid(castToInteger(prefUserModel.getSid()));

                hyperLocalRequestId = appPreference.getIntegerPreference(mContext, "hyperLocalRequestId");

                if (hyperLocalRequestId > 0)
                    form.setHyperLocalRequestId(hyperLocalRequestId);

                String requestString = new Gson().toJson(form);

                if (NetworkUtils.isConnected(mContext)) {

                    ApiService apiService = ApiClient.createServiceWithoutLogging(ApiService.class, null);

                    Call<CommonResponse<String>> call = apiService.manageDriverTrackingDetails(form);

                    ApiManager.callRetrofit(call, API_DRIVER_TRACKING, this, false);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onSinglePermissionGranted(String permissionName) {

        switch (permissionName) {

            case Manifest.permission.ACCESS_FINE_LOCATION:
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                if (gpsManager == null)
                    gpsManager = new GPSManager(this);
                gpsManager.start(this);
                break;
        }
    }

    @Override
    public void onMultiplePermissionGranted(String[] permissions) {

    }

    public void openMapWithLatLong(String latLong) {

        try {

            String strUri = "http://maps.google.com/maps?q=loc:" + latLong;

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));

            intent.setComponent(new ComponentName(
                    "com.google.android.apps.maps",
                    "com.google.android.maps.MapsActivity"));
            startActivity(intent);

        } catch (ActivityNotFoundException e) {

            try {

                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.apps.maps")));
            } catch (android.content.ActivityNotFoundException ex) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps")));
            }

            e.printStackTrace();
        }
    }

    public void dialPhone(String mobileNo) {
        if (isValidString(mobileNo)) {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mobileNo));
            startActivity(intent);

        } else displayShortToast(mContext, "Invalid Mobile no.");
    }

    public File saveBitmapToFile(File file) {

        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }
}
