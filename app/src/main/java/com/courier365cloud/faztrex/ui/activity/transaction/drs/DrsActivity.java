package com.courier365cloud.faztrex.ui.activity.transaction.drs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.DrsAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivityDrsBinding;
import com.courier365cloud.faztrex.network.model.request.DrsRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.drs.Drs;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;

import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

public class DrsActivity extends BaseActivity implements
        DrsAdapter.OnDrsDetailsClickListener,
        ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private ActivityDrsBinding binding;

    private final int REQUEST_CODE_UPDATE_DRS_DOCKET = 1001;

    private DrsAdapter drsAdapter;

    private ArrayList<Drs> drsList = new ArrayList<>();

    private int pageCount = 0, pastVisibleItems, visibleItemCount, totalItemCount = 0, totalItems = 0;

    private boolean loading = true, isSwipeRefresh;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_drs);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_drs));

        // call method to start operational flow
        doYourWork();


    }

    private void doYourWork() {

        try {

            // call method to get preference values
            getPreferenceData();

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            binding.layoutCommonList.recyclerView.setLayoutManager(mLayoutManager);
            binding.layoutCommonList.recyclerView.setItemAnimator(new DefaultItemAnimator());

            drsAdapter = new DrsAdapter(mContext, drsList);
            binding.layoutCommonList.recyclerView.setAdapter(drsAdapter);

            // call method to get DRS list
            getDrsList();

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

                                // call method to get DRS list
                                getDrsList();
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

                pageCount = 0;
                pastVisibleItems = 0;
                visibleItemCount = 0;
                totalItemCount = 0;
                totalItems = 0;
                loading = true;

                drsList = new ArrayList<>();

                drsAdapter = new DrsAdapter(mContext, drsList);
                binding.layoutCommonList.recyclerView.setAdapter(drsAdapter);

                // call method to get DRS list
                getDrsList();
            });

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onViewClick(int itemPosition, Drs drs) {

        if (drs != null) {

            Intent mIntent = new Intent(mContext, DrsDocketDetailActivity.class);
            mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs), drs);
            startActivity(mIntent);
        }
    }

    @Override
    public void onOptionClick(int itemPosition, Drs drs, View view) {

        Boolean isDrsClosed = drs.getClosedDrs();


        Context wrapper = new ContextThemeWrapper(mContext, R.style.PopUpMenuStyle);

        PopupMenu popupMenu = new PopupMenu(wrapper, view);

        popupMenu.getMenuInflater().inflate(R.menu.menu_action, popupMenu.getMenu());

        // hide extra items from menu
        popupMenu.getMenu().findItem(R.id.action_view).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_delete).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_edit).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_view_pod).setVisible(false);
        popupMenu.getMenu().findItem(R.id.action_add_pod).setVisible(false);

        if (drs.getDrsStatus().equalsIgnoreCase(AppConstant.STATUS_CLOSED)) {
            popupMenu.getMenu().findItem(R.id.action_drs_close).setVisible(false);
            popupMenu.getMenu().findItem(R.id.action_view_signature).setVisible(true);
        } else {
            if (isDrsClosed != null && isDrsClosed) {
                popupMenu.getMenu().findItem(R.id.action_drs_close).setVisible(true);
                popupMenu.getMenu().findItem(R.id.action_view_signature).setVisible(false);
            }
        }


        popupMenu.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {

                case R.id.action_drs_close:

                    startNavigation(drs.getId(), drs);
                    break;

                case R.id.action_view_signature:
                    if (!isEmptyString(drs.getSignaturePath()))
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(drs.getSignaturePath())));
                    break;
            }
            return false;
        });
        popupMenu.show();
    }

    private void startNavigation(String drsId, Drs drsDocket) {

        Intent mIntent = new Intent(mContext, SignatureActivity.class);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_drs_id), drsId);
        mIntent.putExtra(mContext.getResources().getString(R.string.key_data_drs_docket), drsDocket);
        startActivityForResult(mIntent, REQUEST_CODE_UPDATE_DRS_DOCKET);
    }

    /*
     * Method to get DRS list from server
     *
     * */
    private void getDrsList() {

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
                DrsRequestModel.DrsList drsList = new DrsRequestModel().new DrsList();

                drsList.setCid(prefUserModel.getCompanyId());
                drsList.setBid(prefUserModel.getBranchId());
                drsList.setUserId(prefUserModel.getId());
                drsList.setPageIndex(String.valueOf(pageCount));
                drsList.setPageSize(String.valueOf(AppConstant.DISPLAY_RECORD_COUNT));
                drsList.setFromDate("");
                drsList.setToDate("");
                drsList.setFinFromDate("");
                drsList.setFinToDate("");
                drsList.setFilter("");
                drsList.setKeyword("");
                drsList.setSortCol("");
                drsList.setSortDir("");

                String apiRequestString = new Gson().toJson(drsList);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<Drs>>> call = apiService.getDrsList(drsList);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DRS_LIST, this, false);

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

            case ApiConstant.API_DRS_LIST:

                CommonResponse<ArrayList<Drs>> drsResponse = (CommonResponse<ArrayList<Drs>>) responseBody;
                processResponse(drsResponse);
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


    private void processResponse(CommonResponse<ArrayList<Drs>> response) {

        try {

            switch (response.getStatus()) {

                case AppConstant.STATUS_SUCCESS:

                    // call method to bind docket list
                    bindDrsList(response);
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

    private void bindDrsList(CommonResponse<ArrayList<Drs>> response) {

        try {

            ArrayList<Drs> tempDrsList = response.getData();

            if (tempDrsList != null && tempDrsList.size() > 0)
                drsList.addAll(tempDrsList);

            if (drsList != null && drsList.size() > 0) {

                drsAdapter.notifyDataSetChanged();

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

        if (drsList != null && drsList.size() == 0) {

            visibleView(binding.layoutCommonList.layoutNoData.ivNoDataFound);
            hideView(binding.layoutCommonList.recyclerView);
        }
    }

    // region display dimension dialog
    /*
     * Method to show dimension dialog
     *
     * */
}
