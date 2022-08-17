package com.courier365cloud.faztrex.ui.fragment;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_DRIVER_DASHBOARD;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseFragment;
import com.courier365cloud.faztrex.databinding.FragmentHomeBinding;
import com.courier365cloud.faztrex.model.DashboardCount;
import com.courier365cloud.faztrex.network.model.request.CommonRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import retrofit2.Call;

public class HomeFragment extends BaseFragment implements ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private FragmentHomeBinding fragmentHomeBinding;

    private boolean isSwipeRefresh;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        getDashboardCounts();

        fragmentHomeBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            isSwipeRefresh = true;
            fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
            getDashboardCounts();
        });

        return fragmentHomeBinding.getRoot();
    }

    private void getDashboardCounts() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                getPreferenceData();

                CommonRequestModel request = new CommonRequestModel();
                request.setUserid(prefUserModel.getId());

                String requestString = new Gson().toJson(request);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");

                Call<CommonResponse<DashboardCount>> call = apiService.getDashboardCounts(request);

                ApiManager.callRetrofit(call, API_DRIVER_DASHBOARD, this, false);

            } else
                displayInternetToastMessage(getActivity());

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case API_DRIVER_DASHBOARD:
                CommonResponse<DashboardCount> dashboardCountApiResponse = (CommonResponse<DashboardCount>) responseBody;
                processDashboardApiResponse(dashboardCountApiResponse);
                break;
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {
        switch (endPointName) {

            case API_DRIVER_DASHBOARD:
                if (isSwipeRefresh) {
                    isSwipeRefresh = false;
                    fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
                }
                displayLongToast(getActivity(), errorMessage);
                fragmentHomeBinding.tvDashboardValue1.setText("0");
                fragmentHomeBinding.tvDashboardValue3.setText("0");
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {
        switch (endPointName) {
            case API_DRIVER_DASHBOARD:
                if (isSwipeRefresh) {
                    isSwipeRefresh = false;
                    fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
                }
                displayLongToast(getActivity(), failureMessage);
                fragmentHomeBinding.tvDashboardValue1.setText("0");
                fragmentHomeBinding.tvDashboardValue3.setText("0");
                break;
        }
    }

    private void processDashboardApiResponse(CommonResponse<DashboardCount> response) {

        try {

            if (isSwipeRefresh) {
                isSwipeRefresh = false;
                fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
            }

            if (response != null) {
                DashboardCount dashboardCount = response.getData();
                fragmentHomeBinding.tvDashboardValue1.setText(dashboardCount.getDrsCounts());
                fragmentHomeBinding.tvDashboardValue3.setText(dashboardCount.getHyperLocalCounts());
                fragmentHomeBinding.tvDashboardValue4.setText(dashboardCount.getDocketCount());
            } else {
                fragmentHomeBinding.tvDashboardValue1.setText("0");
                fragmentHomeBinding.tvDashboardValue3.setText("0");
                fragmentHomeBinding.tvDashboardValue4.setText("0");
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLog(e);
            if (isSwipeRefresh) {
                isSwipeRefresh = false;
                fragmentHomeBinding.swipeRefreshLayout.setRefreshing(isSwipeRefresh);
            }
        }
    }
}