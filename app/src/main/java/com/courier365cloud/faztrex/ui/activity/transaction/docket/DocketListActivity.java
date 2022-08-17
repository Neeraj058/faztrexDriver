package com.courier365cloud.faztrex.ui.activity.transaction.docket;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.DocketAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityDocketListBinding;
import com.courier365cloud.faztrex.network.model.request.DocketRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.docket.Docket;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import java.util.ArrayList;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.getCurrentDate;

public class DocketListActivity extends BaseActivity implements
        DocketAdapter.OnDocketDetailsClickListener,
        ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private final int REQUEST_CODE_DOCKET = 1001;

    private Context mContext = this;

    private ActivityDocketListBinding binding;

    private DocketAdapter docketAdapter;

    private ArrayList<Docket> docketList = new ArrayList<>();

    private int pageCount = 0, pastVisibleItems, visibleItemCount, totalItemCount = 0, totalItems = 0;

    private boolean loading = true, isSwipeRefresh;

    //private String filterFromDate = "", filterToDate = "", filterSearchQuery = "";

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docket_list);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_docket_booking));

        // call method to start operational flow
        doYourWork();
    }

    private void doYourWork() {

        try {

            // call method to get preference values
            getPreferenceData();

            // check rights
            if (checkAccessRights(getAccessRights(AppConstant.PAGE_DOCKET_BOOKING), AppConstant.R_ADD))
                binding.layoutCommonList.fabAdd.show();

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            binding.layoutCommonList.recyclerView.setLayoutManager(mLayoutManager);
            binding.layoutCommonList.recyclerView.setItemAnimator(new DefaultItemAnimator());

            docketAdapter = new DocketAdapter(mContext, docketList);
            binding.layoutCommonList.recyclerView.setAdapter(docketAdapter);

            // call method to get docket list
            getDocketList();

            /*
             * Load more data on scroll event of recycler view
             *
             * */
            binding.layoutCommonList.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                    if (dy > 0) {

                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();
                        pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                        loading = totalItems < totalItemCount;

                        if (loading) {

                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {

                                totalItems = totalItemCount;
                                loading = false;

                                // increment page index
                                pageCount = pageCount + AppConstant.DISPLAY_RECORD_COUNT;

                                // call method to get docket list
                                getDocketList();
                            }
                        }
                    }
                }
            });

            /*
             * Swipe to refresh listener
             *
             * */
            binding.layoutCommonList.swipeRefreshLayout.setOnRefreshListener(() -> {

                isSwipeRefresh = true;
                refreshData();
            });

            binding.layoutCommonList.fabAdd.setOnClickListener(v -> startActivity(new Intent(mContext, DocketBookingActivity.class)));

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onActionClick(int itemPosition, Docket docket, View view) {

        // Used to apply style for pop menu
        Context wrapper = new ContextThemeWrapper(mContext, R.style.PopUpMenuStyle);

        PopupMenu popupMenu = new PopupMenu(wrapper, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_action, popupMenu.getMenu());

        // check access rights
        if (checkAccessRights(getAccessRights(AppConstant.PAGE_DOCKET_BOOKING), AppConstant.R_VIEW))
            popupMenu.getMenu().findItem(R.id.action_view).setVisible(true);

        if (checkAccessRights(getAccessRights(AppConstant.PAGE_DOCKET_BOOKING), AppConstant.R_EDIT))
            popupMenu.getMenu().findItem(R.id.action_edit).setVisible(true);

        if (checkAccessRights(getAccessRights(AppConstant.PAGE_DOCKET_BOOKING), AppConstant.R_DELETE))
            popupMenu.getMenu().findItem(R.id.action_delete).setVisible(true);

        // hide extra items from menu
        popupMenu.getMenu().findItem(R.id.action_view_pod).setVisible(false);

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.action_view:

                    navigateToBookingDetails(docket.getId(), true);
                    break;

                case R.id.action_edit:

                    navigateToBookingDetails(docket.getId(), false);
                    break;

                case R.id.action_delete:

                    displayConfirmationDialog(itemPosition);
                    break;
            }

            return false;
        });

        popupMenu.show();
    }

    private void refreshData() {

        pageCount = 0;
        pastVisibleItems = 0;
        visibleItemCount = 0;
        totalItemCount = 0;
        totalItems = 0;
        loading = true;

        docketList = new ArrayList<>();

        docketAdapter = new DocketAdapter(mContext, docketList);
        binding.layoutCommonList.recyclerView.setAdapter(docketAdapter);

        // call method to get docket list
        getDocketList();
    }

    /*
     * Method to get docket list from server
     *
     * */
    private void getDocketList() {

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
                DocketRequestModel.DocketList docketList = new DocketRequestModel().new DocketList();

                docketList.setCid(prefUserModel.getCompanyId());
                docketList.setBid(prefUserModel.getBranchId());
                docketList.setUserId(prefUserModel.getId());
                docketList.setPageIndex(String.valueOf(pageCount));
                docketList.setPageSize(String.valueOf(AppConstant.DISPLAY_RECORD_COUNT));
                docketList.setFromDate("");
                docketList.setToDate("");
                docketList.setFinFromDate("");
                docketList.setFinToDate("");
                docketList.setFilter("");
                docketList.setKeyword("");
                docketList.setCustomerType("");
                docketList.setDispatchMode("");
                docketList.setPaymentType("");
                docketList.setOriginId("");
                docketList.setDestinatonStateId("");
                docketList.setDestinationCityId("");
                docketList.setFilterBranchId("");
                docketList.setFilterSignedByEmployee("");

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<Docket>>> call = apiService.getDocketList(docketList);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DOCKET_LIST, this, false);

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

            case ApiConstant.API_DOCKET_LIST:

                CommonResponse<ArrayList<Docket>> docketResponse = (CommonResponse<ArrayList<Docket>>) responseBody;
                processResponse(docketResponse);
                break;

            case ApiConstant.API_DOCKET_OPERATION:

                CommonResponse docketUpdateResponse = (CommonResponse) responseBody;
                processDocketDeleteResponse(docketUpdateResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        printErrorLog(TAG, errorMessage);

        // stop swipe refresh layout
        stopSwipeLayout();

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:

                // show error toast message
                displayLongToast(mContext, errorMessage);

                // call method to show no data image
                showNoData();
                break;

            case ApiConstant.API_DOCKET_OPERATION:

                displayShortToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        printErrorLog(TAG, failureMessage);

        switch (endPointName) {

            case ApiConstant.API_DOCKET_LIST:

                // show error toast message
                displayLongToast(mContext, failureMessage);

                // call method to show no data image
                showNoData();
                break;

            case ApiConstant.API_DOCKET_OPERATION:

                displayShortToast(mContext, failureMessage);
                break;
        }
    }

    private void processResponse(CommonResponse<ArrayList<Docket>> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    // call method to bind docket list
                    bindDocketList(response);
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

    private void processDocketDeleteResponse(CommonResponse response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    displayShortToast(mContext, "Docket deleted successfully");
                    refreshData();
                    break;

                case AppConstant.STATUS_FAILURE:

                    displayShortToast(mContext, response.getMessage());
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    private void bindDocketList(CommonResponse<ArrayList<Docket>> response) {

        try {

            ArrayList<Docket> tempDocketList = response.getData();

            if (tempDocketList != null && tempDocketList.size() > 0)
                docketList.addAll(tempDocketList);

            if (docketList != null && docketList.size() > 0) {

                docketAdapter.notifyDataSetChanged();

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

    private void stopSwipeLayout() {

        if (isSwipeRefresh) {

            if (binding.layoutCommonList.swipeRefreshLayout != null && binding.layoutCommonList.swipeRefreshLayout.isRefreshing()) {
                binding.layoutCommonList.swipeRefreshLayout.setRefreshing(false);
                isSwipeRefresh = false;
            }
        }
    }

    private void startSwipeLayout() {
        binding.layoutCommonList.swipeRefreshLayout.post(() -> binding.layoutCommonList.swipeRefreshLayout.setRefreshing(true));
    }

    private void showNoData() {

        if (docketList != null && docketList.size() == 0) {

            visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            hideView(binding.layoutCommonList.recyclerView);
        }
    }

    /*
     * Method to display confirmation dialog
     *
     *
     * */
    private void displayConfirmationDialog(int itemPosition) {

        simpleAlertDialog = new SimpleAlertDialog(mContext) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return mContext.getResources().getString(R.string.dialog_title_confirmation);
            }

            @Override
            public String setDialogMessage() {
                return mContext.getResources().getString(R.string.dialog_msg_docket_delete_confirmation);
            }

            @Override
            public Drawable setDialogIcon() {
                return mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return mContext.getResources().getString(R.string.btn_title_proceed);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    // call API to delete docket by ID
                    deleteDocketById(itemPosition);
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return mContext.getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {
                return (dialog, which) -> dialog.dismiss();
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

    private void navigateToBookingDetails(String docketId, boolean readOnly) {

        try {

            Intent mIntent = new Intent(mContext, DocketBookingActivity.class);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_docket_id), docketId);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_read_only), readOnly);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_is_edit), true);
            startActivityForResult(mIntent, REQUEST_CODE_DOCKET);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to delete docket by ID
     *
     *
     * */
    private void deleteDocketById(int itemPosition) {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                // start progress indicator
                BaseActivity.startProgressDialog(this, false);

                // prepare request body
                DocketRequestModel.DocketCalculationRequest updateDocketRequest = new DocketRequestModel().new DocketCalculationRequest();

                updateDocketRequest.setId(docketList.get(itemPosition).getId());
                updateDocketRequest.setIsDelete(AppConstant.STATUS_ACTIVE);
                updateDocketRequest.setLastModifyDate(getCurrentDate(AppConstant.API_DATE_FORMAT));
                updateDocketRequest.setLastModifyBy(prefUserModel.getId());
                updateDocketRequest.setIsFrom(AppConstant.STATUS_IS_FROM);

                printInfoLog(TAG, new Gson().toJson(updateDocketRequest));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse> call = apiService.docketOperation(updateDocketRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DOCKET_OPERATION, this, false);

            } else {
                displayInternetToastMessage(mContext);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case REQUEST_CODE_DOCKET:

                switch (resultCode) {

                    case RESULT_OK:

                        refreshData();
                        break;
                }

                break;
        }
    }

    @Override
    public void onSinglePermissionGranted(String permissionName) {

    }

    @Override
    public void onMultiplePermissionGranted(String[] permissions) {

    }
}
