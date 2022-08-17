package com.courier365cloud.faztrex.ui.activity.transaction.hyperlocal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.baseclass.BaseAdapter;
import com.courier365cloud.faztrex.databinding.ActivityHyperLocalFormBinding;
import com.courier365cloud.faztrex.network.model.request.CommonRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalDetailForm;
import com.courier365cloud.faztrex.network.model.response.hyperlocal.HyperLocalForm;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppUtils;

import java.util.ArrayList;

import retrofit2.Call;

import static com.cittasolutions.cittalibrary.utils.AppUtils.errorLog;
import static com.cittasolutions.cittalibrary.utils.AppUtils.isValidString;
import static com.courier365cloud.faztrex.network.retrofit.ApiConstant.API_HLR_EDIT;

public class HyperLocalFormActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private final Context mContext = this;

    private String hyperLocalId;

    private ActivityHyperLocalFormBinding binding;

    private ArrayList<HyperLocalDetailForm> hyperLocalDetailFormArrayList;

    private HyperLocalForm form;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView((Activity) mContext, R.layout.activity_hyper_local_form);

        binding.toolbarMain.tvHeaderTitle.setText("Hyper Local Request Details");
        binding.toolbarMain.ivBack.setOnClickListener(v -> finish());

        hyperLocalId = getIntent().getStringExtra("hlrId");

        binding.ivPickupCall.setOnClickListener(this);
        binding.ivPickupLocation.setOnClickListener(this);
        binding.ivDeliveryCall.setOnClickListener(this);
        binding.ivDeliveryLocation.setOnClickListener(this);

        if (isValidString(hyperLocalId))
            getHyperLocalDataById();
    }

    private void getHyperLocalDataById() {

        try {

            CommonRequestModel requestModel = new CommonRequestModel();
            requestModel.setId(hyperLocalId);

            startProgressDialog((Activity) mContext, false);

            ApiService apiService = ApiClient.createService(ApiService.class);

            Call<CommonResponse<HyperLocalForm>> call = apiService.getHyperLocalDetailBtyId(requestModel);

            ApiManager.callRetrofit(call, API_HLR_EDIT, this, false);

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case API_HLR_EDIT:
                CommonResponse<HyperLocalForm> editApiResponse = (CommonResponse<HyperLocalForm>) responseBody;
                processEditApiResponse(editApiResponse);
                break;
        }
    }

    private void processEditApiResponse(CommonResponse<HyperLocalForm> response) {

        try {

            stopProgressDialog();

            if (response != null) {

                if (response.getStatus().equalsIgnoreCase("1")) {

                    binding.rootLayout.setVisibility(View.VISIBLE);

                    form = response.getData();

                    String bookingDate = AppUtils.convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, form.getBookingDateTime());
                    form.setBookingDateTime(bookingDate);
                    binding.setData(form);

                    binding.cbDocuments.setChecked(form.getIsDocument() == 1);
                    binding.cbElectronics.setChecked(form.getIsElectronic() == 1);
                    binding.cbMedicine.setChecked(form.getIsMedicine() == 1);
                    binding.cbEssentials.setChecked(form.getIsEssential() == 1);
                    binding.cbOthers.setChecked(form.getIsOthers() == 1);

                    hyperLocalDetailFormArrayList = form.getDetails();

                    BaseAdapter baseAdapter = new BaseAdapter(mContext, hyperLocalDetailFormArrayList, R.layout.item_product_detail);
                    binding.rvProducts.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
                    binding.rvProducts.setAdapter(baseAdapter);

                } else {
                    displayLongToast(mContext, response.getMessage());
                    finish();
                }

            } else {
                displayLongToast(mContext, "Something went wrong!");
                finish();
            }

        } catch (Exception e) {
            stopProgressDialog();
            e.printStackTrace();
            errorLog(e);
        }
    }

    @Override
    public void onApiError(String endPointName, String errorMessage) {

        stopProgressDialog();

        switch (endPointName) {

            case API_HLR_EDIT:
                displayShortToast(mContext, errorMessage);
                break;
        }
    }

    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        stopProgressDialog();

        switch (endPointName) {
            case API_HLR_EDIT:
                displayShortToast(mContext, getString(R.string.err_msg_api_response_failure));
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ivPickupCall:
                dialPhone(form.getPickupContactNo());
                break;

            case R.id.ivPickupLocation:
                if (isValidString(form.getPickupLatitude()) && isValidString(form.getPickupLongitude()))
                    openMapWithLatLong(form.getPickupLatitude() + ", " + form.getPickupLongitude());
                else
                    displayShortToast(mContext, "Location not found!");
                break;

            case R.id.ivDeliveryCall:
                dialPhone(form.getDeliveryContactNo());
                break;

            case R.id.ivDeliveryLocation:
                if (isValidString(form.getDeliveryLatitude()) && isValidString(form.getDeliveryLongitude()))
                    openMapWithLatLong(form.getDeliveryLatitude() + ", " + form.getDeliveryLongitude());
                else
                    displayShortToast(mContext, "Location not found!");
                break;
        }
    }
}