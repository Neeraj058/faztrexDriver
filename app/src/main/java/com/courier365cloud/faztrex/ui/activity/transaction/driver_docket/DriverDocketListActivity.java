package com.courier365cloud.faztrex.ui.activity.transaction.driver_docket;

import static com.courier365cloud.faztrex.utils.AppConfig.IMAGE_UPLOAD_BASE_URL;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.DrsDocketAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityDocketListBinding;
import com.courier365cloud.faztrex.databinding.DialogSecureDeliveryBinding;
import com.courier365cloud.faztrex.network.model.request.DocketOTP;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.ui.activity.transaction.drs.DrsDocketUpdateActivity;
import com.courier365cloud.faztrex.ui.fragment.DocketDimensionBottomSheet;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;

public class DriverDocketListActivity extends BaseActivity implements ApiListener, DrsDocketAdapter.OnDocketDetailsClickListener {
    private static final String TAG = DriverDocketListActivity.class.getSimpleName();
    private final Context mContext = this;
    private final int REQUEST_CODE_UPDATE_DOCKET = 1001;
    private ArrayList<DrsDocket> newDriverDocketArrayList = new ArrayList<>();
    private ActivityDocketListBinding binding;
    private boolean isSwipeRefresh = false;
    private DrsDocketAdapter drsDocketAdapter;
    private DialogSecureDeliveryBinding dialogSecureDeliveryBinding;
    private Dialog mDialog;
    private LinearLayoutManager linearLayoutManager;
//    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docket_list);
//        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetDrsDetails.bottomSheetLayout);

        linearLayoutManager = new LinearLayoutManager(this);
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_docket));
        binding.layoutCommonList.recyclerView.setLayoutManager(linearLayoutManager);
        doYourWork();
    }

    private void doYourWork() {
        try {
            getPreferenceData();
            drsDocketAdapter = new DrsDocketAdapter(mContext, newDriverDocketArrayList);
            binding.layoutCommonList.recyclerView.setAdapter(drsDocketAdapter);
            binding.layoutCommonList.recyclerView.setItemAnimator(null);

            getDriverDocketList();

            binding.layoutCommonList.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    isSwipeRefresh = true;
                    getDriverDocketList();
                }
            });

           /* bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View view, int i) {
                    switch (i) {

                        case BottomSheetBehavior.STATE_HIDDEN:
                            Log.d(TAG, "STATE_HIDDEN");
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            break;

                        case BottomSheetBehavior.STATE_COLLAPSED:
//                            bottomSheetBehavior.setHideable(true);

                            bottomSheetBehavior.setPeekHeight(500);
                            Log.d(TAG, "STATE_COLLAPSED");
                            break;

                        case BottomSheetBehavior.STATE_DRAGGING:
                            Log.d(TAG, "STATE_DRAGGING");
                            break;

                        case BottomSheetBehavior.STATE_EXPANDED:
                            bottomSheetBehavior.setPeekHeight(500);
                            Log.d(TAG, "STATE_EXPANDED");
                            break;

                        case BottomSheetBehavior.STATE_HALF_EXPANDED:
                            Log.d(TAG, "STATE_HALF_EXPANDED");
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            break;

                        case BottomSheetBehavior.STATE_SETTLING:
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                            bottomSheetBehavior.setPeekHeight(500);
                            Log.d(TAG, "STATE_SETTLING");
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View view, float v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void getDriverDocketList() {
        try {
            if (NetworkUtils.isConnected(mContext)) {

                if (isSwipeRefresh) {
                    // start swipe layout
                    binding.layoutCommonList.swipeRefreshLayout.setRefreshing(true);
                } else {
                    // start progress indicator
                    startProgressDialog(this, false);
                }

                /*JSONObject jsonObject = new JSONObject();
                jsonObject.put("DriverId", prefUserModel.getId());*/
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("DriverId", prefUserModel.getId());
                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<DrsDocket>>> call = apiService.getNewDriverDocket(hashMap);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_NEW_DOCKET_DETAIL, this, false);

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
        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {
            case ApiConstant.API_NEW_DOCKET_DETAIL:
                CommonResponse<ArrayList<DrsDocket>> docketResponse = (CommonResponse<ArrayList<DrsDocket>>) responseBody;
                processResponse(docketResponse);
                break;
        }
    }

    private void processResponse(CommonResponse<ArrayList<DrsDocket>> docketResponse) {
        try {

            switch (docketResponse.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    // call method to bind docket list
                    bindDriverDocketList(docketResponse);
                    break;

                case AppConstant.STATUS_FAILURE:

                    // call method to show no data image
                    showNoData();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void bindDriverDocketList(CommonResponse<ArrayList<DrsDocket>> response) {
        try {
            ArrayList<DrsDocket> tempDocketList = response.getData();
            newDriverDocketArrayList.clear();
            if (tempDocketList != null && tempDocketList.size() > 0)
                newDriverDocketArrayList.addAll(tempDocketList);

            if (newDriverDocketArrayList != null && newDriverDocketArrayList.size() > 0) {
//                drsDocketAdapter.notifyItemInserted(newDriverDocketArrayList.size() - 1);
                drsDocketAdapter.notifyDataSetChanged();
                visibleView(binding.layoutCommonList.recyclerView);
                hideView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            } else {
                visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
                hideView(binding.layoutCommonList.recyclerView);
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void showNoData() {
        if (newDriverDocketArrayList != null && newDriverDocketArrayList.size() == 0) {
            visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            hideView(binding.layoutCommonList.recyclerView);
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        printErrorLog(TAG, errorMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {
            case ApiConstant.API_NEW_DOCKET_DETAIL:
                displayShortToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        printErrorLog(TAG, failureMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {
            case ApiConstant.API_NEW_DOCKET_DETAIL:
                displayShortToast(mContext, failureMessage);
                break;
        }
    }

    private void stopSwipeLayout() {
        if (isSwipeRefresh) {
            if (binding.layoutCommonList.swipeRefreshLayout.isRefreshing()) {
                binding.layoutCommonList.swipeRefreshLayout.setRefreshing(false);
                isSwipeRefresh = false;
            }
        } else {
            stopProgressDialog();
        }
    }

    @Override
    public void onViewClick(int itemPosition, DrsDocket drsDocket) {
        DocketDimensionBottomSheet docketDimensionBottomSheet = new DocketDimensionBottomSheet(drsDocket);
        docketDimensionBottomSheet.setCancelable(true);
        docketDimensionBottomSheet.show(getSupportFragmentManager(), docketDimensionBottomSheet.getTag());
    }

    @Override
    public void onOptionClick(int position, DrsDocket drsDocket, View view) {
        boolean isDelivered = getIsDelivered(drsDocket);

        // Used to apply style for pop menu
        Context wrapper = new ContextThemeWrapper(mContext, R.style.PopUpMenuStyle);

        PopupMenu popupMenu = new PopupMenu(wrapper, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_action, popupMenu.getMenu());

        // hide extra items from menu
        popupMenu.getMenu().findItem(R.id.action_view).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_delete).setVisible(false);

        if (drsDocket.getPodFilePath() != null) {
            if (drsDocket.getPodFilePath().contains(".jpg")
                    || drsDocket.getPodFilePath().contains(".png")
                    || drsDocket.getPodFilePath().contains(".jpeg")
                    || drsDocket.getPodFilePath().contains(".jfif")
                    || drsDocket.getPodFilePath().contains(".tiff")
                    || drsDocket.getPodFilePath().contains(".bmp")
            ) {
                if (!drsDocket.getPodFilePath().startsWith(IMAGE_UPLOAD_BASE_URL)) {
                    drsDocket.setPodFilePath(IMAGE_UPLOAD_BASE_URL + drsDocket.getPodFilePath());
                }
                popupMenu.getMenu().findItem(R.id.action_add_pod).setVisible(false);

                popupMenu.getMenu().findItem(R.id.action_view_pod).setVisible(true);
            } else {
                popupMenu.getMenu().findItem(R.id.action_view_pod).setVisible(false);
            }
        }

        if (isDelivered /*|| drsDocket.getDeliveryStatus().equalsIgnoreCase("0")*/) {
            popupMenu.getMenu().findItem(R.id.action_edit).setVisible(false);
        } else {
            popupMenu.getMenu().findItem(R.id.action_edit).setVisible(true);

            popupMenu.getMenu().findItem(R.id.action_add_pod).setVisible(false);
        }

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.action_edit:
                    startNavigation(drsDocket.getDRSId(), drsDocket);
                    break;

                case R.id.action_view_pod:
                    if (!isEmptyString(drsDocket.getPodFilePath()))
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(drsDocket.getPodFilePath())));
                    break;

                case R.id.action_add_pod:
                    Intent intent = new Intent(mContext, DrsDocketUpdateActivity.class);
                    intent.putExtra(AppConstant.POD_STATUS, true);
                    intent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsDocket.getDRSId());
                    intent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
                    startActivityForResult(intent, REQUEST_CODE_UPDATE_DOCKET);
                    break;
            }
            return false;
        });

        popupMenu.show();
    }

    private boolean getIsDelivered(DrsDocket drsDocket) {
//        Previously used
       /* if (drsDocket.getDeliveryStatusName() == null) {
            return false;
        } else return drsDocket.getDeliveryStatusName().equalsIgnoreCase(AppConstant.STATUS_DELIVERED);*/

//        New updated for Driver Docket only
        if (drsDocket.getDeliveryStatus() == null)
            return false;
        else if (drsDocket.getDeliveryStatus().equalsIgnoreCase("1") || drsDocket.getDeliveryStatus().equalsIgnoreCase("2"))
            return true;
        else return false;
    }

    @Override
    public void onSecureDeliveryClick(int itemPosition) {
        DrsDocket drsDocket = newDriverDocketArrayList.get(itemPosition);
        dialogSecureDeliveryBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_secure_delivery, null, false);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);

        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(dialogSecureDeliveryBinding.getRoot());
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.show();
        dialogSecureDeliveryBinding.docketNumber.setText(mContext.getString(R.string.docket_number_string, drsDocket.getDocketNo()));
        dialogSecureDeliveryBinding.btnGenerateOtp.setOnClickListener(v -> {
            try {
                startProgressDialog(this, false);
                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                DocketOTP docketOTP = new DocketOTP();
                docketOTP.setId(drsDocket.getDocketId());
                Call<CommonResponse<String>> call = apiService.generateOTP(docketOTP);
                ApiManager.callRetrofit(call, ApiConstant.API_GENERATE_OTP, this, false);
            } catch (Exception e) {
                e.printStackTrace();
                printErrorLog(TAG, e.getLocalizedMessage());
            }
        });

        dialogSecureDeliveryBinding.btnVerifyOtp.setOnClickListener(v -> {
            String otp = dialogSecureDeliveryBinding.tnlOtp.getEditText().getText().toString();
            if (otp.isEmpty()) {
                dialogSecureDeliveryBinding.tnlOtp.setError("Please enter OTP");
                return;
            }
            dialogSecureDeliveryBinding.tnlOtp.setError(null);
            try {
                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                DocketOTP docketOTP = new DocketOTP();
                docketOTP.setId(drsDocket.getDocketId());
                docketOTP.setOtp(otp);
                Call<CommonResponse<String>> call = apiService.verifyOTP(docketOTP);
                ApiManager.callRetrofit(call, ApiConstant.API_VERIFY_OTP, this, false);
            } catch (Exception e) {
                e.printStackTrace();
                printErrorLog(TAG, e.getLocalizedMessage());
            }
        });

        dialogSecureDeliveryBinding.ivClose.setOnClickListener(v -> mDialog.dismiss());
    }

    private void startNavigation(String drsId, DrsDocket drsDocket) {
        //Intent mIntent = new Intent(mContext, PaymentListActivity.class);
        Intent mIntent = new Intent(mContext, DrsDocketUpdateActivity.class);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsId);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
        startActivityForResult(mIntent, REQUEST_CODE_UPDATE_DOCKET);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Log.d(TAG, "Clicked");
            return true;
        } else {
            return false;
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_CODE_UPDATE_DOCKET:

                switch (resultCode) {

                    case RESULT_OK:
                        Log.d(TAG, "Calling API from onActivityResult: " + newDriverDocketArrayList.size());
                        doYourWork();
                       /* newDriverDocketArrayList = new ArrayList<>();

                        // call method to get DRS Docket list
                        getDriverDocketList();*/
                        break;
                }

                break;
        }
    }
}