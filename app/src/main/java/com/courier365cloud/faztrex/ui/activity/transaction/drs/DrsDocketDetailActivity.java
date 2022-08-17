package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import static com.courier365cloud.faztrex.utils.AppConfig.IMAGE_UPLOAD_BASE_URL;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.DrsDocketAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityDrsdocketDetailBinding;
import com.courier365cloud.faztrex.databinding.DialogSecureDeliveryBinding;
import com.courier365cloud.faztrex.network.model.request.DocketOTP;
import com.courier365cloud.faztrex.network.model.request.DrsRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.drs.Drs;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDetail;
import com.courier365cloud.faztrex.network.model.response.drs.DrsDocket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

import retrofit2.Call;

public class DrsDocketDetailActivity extends BaseActivity implements
        ApiListener, DrsDocketAdapter.OnDocketDetailsClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private final int REQUEST_CODE_UPDATE_DOCKET = 1001;

    private final Context mContext = this;

    private ActivityDrsdocketDetailBinding binding;

    private BottomSheetBehavior bottomSheetBehavior;

    private DrsDocketAdapter drsDocketAdapter;

    private ArrayList<DrsDocket> drsDocketList = new ArrayList<>();

    private boolean isSwipeRefresh;

    private Drs drsModel;
    private DialogSecureDeliveryBinding dialogSecureDeliveryBinding;
    private Dialog mDialog;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drsdocket_detail);

        // set layout behaviour
        bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheetDrsDetails.bottomSheetLayout);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_drs_details));

        //disableView(binding.layoutCommonList.swipeRefreshLayout);

        // call method to start operational flow
        doYourWork();
    }

    private void doYourWork() {

        try {

            // call method to get preference values
            getPreferenceData();

            // get intent data
            drsModel = getIntent().getParcelableExtra(mContext.getResources().getString(R.string.key_data_drs));

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            binding.layoutCommonList.recyclerView.setLayoutManager(mLayoutManager);
            binding.layoutCommonList.recyclerView.setItemAnimator(new DefaultItemAnimator());

            // call method to get DRS Docket list
            getDrsDocketList();

            binding.layoutCommonList.swipeRefreshLayout.setOnRefreshListener(() -> {
                isSwipeRefresh = true;
                drsDocketList = new ArrayList<>();
                // call method to get DRS Docket list
                getDrsDocketList();
            });

            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View view, int i) {

                    switch (i) {

                        case BottomSheetBehavior.STATE_HIDDEN:
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            break;

                        case BottomSheetBehavior.STATE_COLLAPSED:
                            break;

                        case BottomSheetBehavior.STATE_DRAGGING:
                            break;

                        case BottomSheetBehavior.STATE_EXPANDED:
                            break;

                        case BottomSheetBehavior.STATE_HALF_EXPANDED:
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                            break;

                        case BottomSheetBehavior.STATE_SETTLING:
                            break;
                    }
                }

                @Override
                public void onSlide(@NonNull View view, float v) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void getDrsDocketList() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                if (isSwipeRefresh) {

                    // start swipe layout
                    startSwipeLayout();

                } else {

                    // start progress indicator
                    startProgressDialog(this, false);
                }

                // prepare request body
                DrsRequestModel.DrsDetail drsDetail = new DrsRequestModel().new DrsDetail();

                drsDetail.setId(drsModel.getId());
                drsDetail.setCid(prefUserModel.getCompanyId());
                drsDetail.setBid(prefUserModel.getBranchId());

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<DrsDetail>> call = apiService.getDrsDetail(drsDetail);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DRS_DETAIL, this, false);

            } else {
                displayInternetToastMessage(mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onViewClick(int itemPosition, DrsDocket drsDocket) {
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onOptionClick(int position, DrsDocket drsDocket, View view) {

        boolean isDelivered = drsDocket.getDeliveryStatusName().equalsIgnoreCase(AppConstant.STATUS_DELIVERED);

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

        if (isDelivered || drsDocket.getDeliveryStatus().equalsIgnoreCase("2")) {
            popupMenu.getMenu().findItem(R.id.action_edit).setVisible(false);
        } else {
            popupMenu.getMenu().findItem(R.id.action_edit).setVisible(true);
            popupMenu.getMenu().findItem(R.id.action_add_pod).setVisible(false);
        }

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.action_edit:
                    startNavigation(drsModel.getId(), drsDocket);
                    break;

                case R.id.action_view_pod:
                    if (!isEmptyString(drsDocket.getPodFilePath()))
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(drsDocket.getPodFilePath())));
                    break;

                case R.id.action_add_pod:
                    Intent intent = new Intent(mContext, DrsDocketUpdateActivity.class);
                    intent.putExtra(AppConstant.POD_STATUS, true);
                    intent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsModel.getId());
                    intent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
                    startActivityForResult(intent, REQUEST_CODE_UPDATE_DOCKET);
                    break;
            }
    
            return false;
        });

        popupMenu.show();
    }

    @Override
    public void onSecureDeliveryClick(int itemPosition) {
        DrsDocket drsDocket = drsDocketList.get(itemPosition);
        dialogSecureDeliveryBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_secure_delivery, null, false);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);

        mDialog = new Dialog(mContext);
        mDialog.setContentView(dialogSecureDeliveryBinding.getRoot());
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();
        mDialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

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

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        // stop swipe refresh layout
        stopSwipeLayout();
        stopProgressDialog();
        switch (endPointName) {
            case ApiConstant.API_DRS_DETAIL:
                CommonResponse<DrsDetail> drsDetailResponse = (CommonResponse<DrsDetail>) responseBody;
                processResponse(drsDetailResponse);
                break;

            case ApiConstant.API_GENERATE_OTP:
                CommonResponse<String> generateOTPResponse = (CommonResponse<String>) responseBody;
                displayShortToast(this, generateOTPResponse.getMessage());
                if (generateOTPResponse.getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                    dialogSecureDeliveryBinding.btnVerifyOtp.setEnabled(true);
                }
                break;

            case ApiConstant.API_VERIFY_OTP:
                CommonResponse<String> verifyOTPResponse = (CommonResponse<String>) responseBody;
                displayShortToast(this, verifyOTPResponse.getMessage());
                if (verifyOTPResponse.getStatus().equalsIgnoreCase(AppConstant.STATUS_SUCCESS)) {
                    mDialog.dismiss();
                }
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        printErrorLog(TAG, errorMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        // show error toast message
        displayLongToast(mContext, errorMessage);

        // call method to show no data image
        showNoData();
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, failureMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        // show error toast message
        displayLongToast(mContext, mContext.getResources().getString(R.string.err_msg_api_response_failure));

        // call method to show no data image
        showNoData();
    }

    private void processResponse(CommonResponse<DrsDetail> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    // call method to bind docket list
                    bindDrsDocketList(response);

                    binding.bottomSheetDrsDetails.setDrsDetail(response.getData());
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

    private void bindDrsDocketList(CommonResponse<DrsDetail> response) {
        try {
            drsDocketList = response.getData().getDocketList();

            if (drsDocketList != null && drsDocketList.size() > 0) {
                drsDocketAdapter = new DrsDocketAdapter(mContext, drsDocketList);
                binding.layoutCommonList.recyclerView.setAdapter(drsDocketAdapter);

                visibleView(binding.layoutCommonList.recyclerView);
                hideView(binding.layoutCommonList.layoutNoData.ivNoDataFound);

            } else {

                hideView(binding.layoutCommonList.recyclerView);
                visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void stopSwipeLayout() {
        if (isSwipeRefresh) {
            if (binding.layoutCommonList.swipeRefreshLayout.isRefreshing()) {
                binding.layoutCommonList.swipeRefreshLayout.setRefreshing(false);
                isSwipeRefresh = false;
            }
        }
    }

    private void startSwipeLayout() {
        binding.layoutCommonList.swipeRefreshLayout.post(() -> binding.layoutCommonList.swipeRefreshLayout.setRefreshing(true));
    }

    private void showNoData() {

        if (drsDocketList != null && drsDocketList.size() == 0) {

            visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            hideView(binding.layoutCommonList.recyclerView);
        }
    }

    private void startNavigation(String drsId, DrsDocket drsDocket) {

        //Intent mIntent = new Intent(mContext, PaymentListActivity.class);
        Intent mIntent = new Intent(mContext, DrsDocketUpdateActivity.class);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsId);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
        startActivityForResult(mIntent, REQUEST_CODE_UPDATE_DOCKET);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_CODE_UPDATE_DOCKET:

                switch (resultCode) {

                    case RESULT_OK:

                        drsDocketList = new ArrayList<>();

                        // call method to get DRS Docket list
                        getDrsDocketList();
                        break;
                }

                break;
        }
    }
}
