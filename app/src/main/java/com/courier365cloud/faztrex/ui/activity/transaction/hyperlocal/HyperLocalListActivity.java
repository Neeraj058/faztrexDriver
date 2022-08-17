package com.courier365cloud.faztrex.ui.activity.transaction.hyperlocal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.baseclass.BaseAdapter;
import com.courier365cloud.faztrex.databinding.ActivityHyperLocalListBinding;
import com.courier365cloud.faztrex.helper.ConfirmationDialogManager;
import com.courier365cloud.faztrex.listener.HyperLocalItemClickListener;
import com.courier365cloud.faztrex.listener.general.ConfirmationDialogClickListener;
import com.courier365cloud.faztrex.network.model.request.CommonRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalList;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;

import java.util.ArrayList;

import retrofit2.Call;

import static com.cittasolutions.cittalibrary.utils.AppUtils.isValidString;
import static com.cittasolutions.cittalibrary.utils.AppUtils.toInt;

public class HyperLocalListActivity extends BaseActivity
        implements HyperLocalItemClickListener, ConfirmationDialogClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private ActivityHyperLocalListBinding binding;

    private BaseAdapter baseAdapter;

    private ArrayList<HyperLocalList> hyperLocalListArrayList;

    private boolean isSwipeRefresh;

    private HyperLocalList hyperLocalList;
    private int itemPosition = -1;

    private String fromDate, toDate, assignId, statusId = "1";

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_hyper_local_list);

        binding.toolbarMain.tvHeaderTitle.setText(AppConstant.NAV_TITLE_HYPER_LOCAL_REQUEST);

        binding.toolbarMain.ivBack.setOnClickListener(v -> finish());

        getPreferenceData();

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        binding.layoutCommonList.recyclerView.setLayoutManager(mLayoutManager);
        binding.layoutCommonList.recyclerView.setItemAnimator(new DefaultItemAnimator());

        binding.btnStatus.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            try {
                statusId = String.valueOf(findViewById(group.getCheckedButtonId()).getTag());
                getHyperLocalRequestListData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        binding.layoutCommonList.swipeRefreshLayout.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            getHyperLocalRequestListData();
        });

        getHyperLocalRequestListData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getHyperLocalRequestListData() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                if (isSwipeRefresh) {
                    binding.layoutCommonList.swipeRefreshLayout.setRefreshing(true);
                } else
                    startProgressDialog((Activity) mContext, false);

                CommonRequestModel commonRequestModel = new CommonRequestModel();
                //commonRequestModel.setCid(prefUserModel.getCid());
                //commonRequestModel.setBid(prefUserModel.getBid());
                commonRequestModel.setPagefrom(0);
                commonRequestModel.setPagesize(1000);
                commonRequestModel.setSortcol(0);
                commonRequestModel.setSortdir("DESC");
                commonRequestModel.setFromdate(fromDate);
                commonRequestModel.setTodate(toDate);
                commonRequestModel.setUserid(prefUserModel.getId());
                commonRequestModel.setAssignid(prefUserModel.getId());
                commonRequestModel.setStatusid(statusId);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<HyperLocalList>>> call = apiService.getHyperLocalList(commonRequestModel);

                ApiManager.callRetrofit(call, ApiConstant.API_HLR_LIST, this, false);
            }

        } catch (Exception e) {
            stopProgressDialog();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_HLR_LIST:
                CommonResponse<ArrayList<HyperLocalList>> hyperLocalListResponse = (CommonResponse<ArrayList<HyperLocalList>>) responseBody;
                processListResponse(hyperLocalListResponse);
                break;

            case ApiConstant.API_HLR_PICKED:
                CommonResponse<String> statusUpdateResponse = (CommonResponse<String>) responseBody;
                processStatusUpdateResponse(statusUpdateResponse);
                break;

        }
    }

    private void processStatusUpdateResponse(CommonResponse<String> response) {

        if (response != null) {

            if (response.getStatus().equals("1"))
                getHyperLocalRequestListData();
            else
                displayShortToast(mContext, response.getMessage());
        } else
            displayShortToast(mContext, getString(R.string.err_msg_api_response_failure));

        stopLoader();
    }

    private void processListResponse(CommonResponse<ArrayList<HyperLocalList>> response) {

        if (hyperLocalListArrayList != null)
            hyperLocalListArrayList.clear();

        if (response != null) {

            if (response.getStatus().equals("1")) {

                if (response.getData() != null && response.getData().size() > 0) {
                    hyperLocalListArrayList = response.getData();
                    baseAdapter = new BaseAdapter(mContext, hyperLocalListArrayList, R.layout.item_hyper_local_request, this);
                    binding.layoutCommonList.recyclerView.setAdapter(baseAdapter);

                    if (itemPosition > -1)
                        binding.layoutCommonList.recyclerView.scrollToPosition(itemPosition);
                    itemPosition = -1;
                    //baseAdapter.notifyDataSetChanged();
                }
            }
        } else
            displayShortToast(mContext, "Something went wrong! Try again later.");

        noDataLayoutVisibility();
        stopLoader();
    }

    private void noDataLayoutVisibility() {

        if (hyperLocalListArrayList == null || hyperLocalListArrayList.size() <= 0) {

            binding.layoutCommonList.recyclerView.setVisibility(View.GONE);
            binding.layoutCommonList.layoutNoData.ivNoDataFound.setVisibility(View.VISIBLE);
        } else {
            binding.layoutCommonList.recyclerView.setVisibility(View.VISIBLE);
            binding.layoutCommonList.layoutNoData.ivNoDataFound.setVisibility(View.GONE);
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        stopLoader();

        noDataLayoutVisibility();

        switch (endPointName) {
            case ApiConstant.API_HLR_LIST:
            case ApiConstant.API_HLR_PICKED:
                displayShortToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        stopLoader();

        noDataLayoutVisibility();

        switch (endPointName) {
            case ApiConstant.API_HLR_LIST:
            case ApiConstant.API_HLR_PICKED:
                displayShortToast(mContext, getResources().getString(R.string.err_msg_api_response_failure));
                break;
        }
    }

    private void stopLoader() {
        if (isSwipeRefresh) {
            isSwipeRefresh = false;
            binding.layoutCommonList.swipeRefreshLayout.setRefreshing(false);
        } else
            stopProgressDialog();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onActionClick(int itemPosition, Object object, View view) {

        this.itemPosition = itemPosition;

        hyperLocalList = hyperLocalListArrayList.get(itemPosition);

        //if (!hyperLocalList.getStatusName().equalsIgnoreCase("DELIVERED")) {
        Context wrapper = new ContextThemeWrapper(mContext, R.style.PopUpMenuStyle);
        PopupMenu popupMenu = new PopupMenu(wrapper, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_hyper_local_request, popupMenu.getMenu());

        popupMenu.getMenu().findItem(R.id.action_view_on_map).setVisible(
                isValidString(hyperLocalList.getStatusName()) &&
                        !hyperLocalList.getStatusName().equalsIgnoreCase("DELIVERED"));
        popupMenu.getMenu().findItem(R.id.action_mark_as_picked).setVisible(hyperLocalList.getStatusName().equalsIgnoreCase("NEW"));
        popupMenu.getMenu().findItem(R.id.action_mark_as_delivered).setVisible(hyperLocalList.getStatusName().equalsIgnoreCase("PICKED"));

        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.action_view_detail:
                    Intent intent = new Intent(mContext, HyperLocalFormActivity.class);
                    intent.putExtra("hlrId", hyperLocalList.getId());
                    startActivity(intent);
                    break;

                case R.id.action_view_on_map:
                    if (hyperLocalList.getStatusName().equalsIgnoreCase("NEW"))
                        openMapWithLatLong(hyperLocalList.getPickupLatitude() + ", " + hyperLocalList.getPickupLongitude());
                    else if (hyperLocalList.getStatusName().equalsIgnoreCase("PICKED"))
                        openMapWithLatLong(hyperLocalList.getDeliveryLatitude() + ", " + hyperLocalList.getDeliveryLongitude());
                    break;

                case R.id.action_mark_as_picked:
                    ConfirmationDialogManager.getInstance(mContext, this).askConsent("Are you sure to update this as PICKED ?");
                    break;

                case R.id.action_mark_as_delivered:
                    intent = new Intent(mContext, HyperLocalDeliveryConfirmationActivity.class);
                    intent.putExtra("hlr", hyperLocalList);
                    activityResultLauncher.launch(intent);
                    break;
            }

            return false;
        });
        popupMenu.show();
        //}
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK)
                    getHyperLocalRequestListData();
            });

    private void markAsPicked() {

        try {

            if (NetworkUtils.isConnected(mContext)) {

                hyperLocalRequestId = toInt(hyperLocalList.getId());
                appPreference.setIntegerPreference(mContext, "hyperLocalRequestId", hyperLocalRequestId);

                CommonRequestModel commonRequestModel = new CommonRequestModel();
                commonRequestModel.setUserid(prefUserModel.getId());
                commonRequestModel.setId(hyperLocalList.getId());

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                startProgressDialog((Activity) mContext, false);

                Call<CommonResponse<String>> call = apiService.markAsPicked(commonRequestModel);

                ApiManager.callRetrofit(call, ApiConstant.API_HLR_PICKED, this, false);
            }

        } catch (Exception e) {
            displayShortToast(mContext, getString(R.string.err_msg_api_response_failure));
            printErrorLog(TAG, e.getLocalizedMessage());
            stopLoader();
        }
    }

    @Override
    public void onConfirmationPositiveClick() {
        markAsPicked();
    }

    @Override
    public void onConfirmationNegativeClick() {

    }
}