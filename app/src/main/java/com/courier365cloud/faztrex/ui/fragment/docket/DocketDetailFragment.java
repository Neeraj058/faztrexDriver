package com.courier365cloud.faztrex.ui.fragment.docket;

import static android.app.Activity.RESULT_OK;
import static com.courier365cloud.faztrex.baseclass.BaseActivity.cityMasterList;
import static com.courier365cloud.faztrex.baseclass.BaseActivity.deliveryTypeMasterList;
import static com.courier365cloud.faztrex.baseclass.BaseActivity.packingTypeMasterList;
import static com.courier365cloud.faztrex.baseclass.BaseActivity.postcodeMasterList;
import static com.courier365cloud.faztrex.baseclass.BaseActivity.stateMasterList;
import static com.courier365cloud.faztrex.baseclass.BaseActivity.verticleMasterList;
import static com.courier365cloud.faztrex.utils.AppUtils.castToDouble;
import static com.courier365cloud.faztrex.utils.AppUtils.castToInteger;
import static com.courier365cloud.faztrex.utils.AppUtils.convertDateFormat;
import static com.courier365cloud.faztrex.utils.AppUtils.convertToUpperCase;
import static com.courier365cloud.faztrex.utils.AppUtils.getCurrentDate;
import static com.courier365cloud.faztrex.utils.AppUtils.getFormattedString;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.getTrimString;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.DocketDimensionAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.baseclass.BaseFragment;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.DialogDocketDimensionDetailBinding;
import com.courier365cloud.faztrex.databinding.FragmentDocketDetailsBinding;
import com.courier365cloud.faztrex.network.model.request.DocketRequestModel;
import com.courier365cloud.faztrex.network.model.request.MasterRequestModel;
import com.courier365cloud.faztrex.network.model.response.CommonListResponse;
import com.courier365cloud.faztrex.network.model.response.CommonResponse;
import com.courier365cloud.faztrex.network.model.response.docket.Consignor;
import com.courier365cloud.faztrex.network.model.response.docket.Dimension;
import com.courier365cloud.faztrex.network.model.response.docket.DimensionDetail;
import com.courier365cloud.faztrex.network.model.response.docket.DocketCalculation;
import com.courier365cloud.faztrex.network.model.response.docket.DocketDetail;
import com.courier365cloud.faztrex.network.retrofit.ApiClient;
import com.courier365cloud.faztrex.network.retrofit.ApiConstant;
import com.courier365cloud.faztrex.network.retrofit.ApiListener;
import com.courier365cloud.faztrex.network.retrofit.ApiManager;
import com.courier365cloud.faztrex.network.retrofit.ApiService;
import com.courier365cloud.faztrex.ui.activity.transaction.docket.DocketBookingActivity;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppValidation;
import com.courier365cloud.faztrex.utils.NetworkUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;

public class DocketDetailFragment extends BaseFragment implements
        DocketDimensionAdapter.OnDimensionClickListener,
        AdapterView.OnItemSelectedListener,
        ApiListener {

    private final String TAG = this.getClass().getSimpleName();

    private final String CUSTOMER_TYPE_CREDIT = "1";
    private final String CUSTOMER_TYPE_RETAIL = "2";
    private final String MODE_TYPE_SURFACE = "1";
    private final String MODE_TYPE_AIR = "2";
    private final String PAYMENT_TYPE_PAID = "1";
    private final String PAYMENT_TYPE_TO_PAY = "2";
    private final String PAYMENT_TYPE_TO_BE_BILLED = "3";
    private final String RISK_TYPE_OWNER = "1";
    private final String RISK_TYPE_CARRIER = "2";

    private FragmentDocketDetailsBinding binding;
    private DialogDocketDimensionDetailBinding dialogBinding;

    private DocketDimensionAdapter dimensionAdapter;

    private ArrayList<Dimension> dimensionList = new ArrayList<>();

    private String customerType = CUSTOMER_TYPE_CREDIT;
    private String dispatchMode = MODE_TYPE_SURFACE;
    private String paymentType = PAYMENT_TYPE_PAID;
    private String riskType = RISK_TYPE_OWNER;

    private String docketId = "";
    private String docketAutoNo = "";
    private String consignorCode = "";
    private String consignorName = "";
    private String postcode = "";
    private String postcodeTypeName = "";
    private String isSezGst = "0";
    private String stateName = "";

    private int consignorId = 0;
    private int stateId = 0;
    private int cityId = 0;
    private int postcodeId = 0;
    private int postcodeTypeId = 0;
    private int deliveryTypeId = 0;
    private int verticalId = 0;
    private int packingTypeId = 0;

    private boolean isEditable = false, isReadOnly = false, fmCreated = false, isForCalculation = false, firstTime = true;

    private ArrayList<Consignor> consignorList = new ArrayList<>();

    // model class to store docket calculation response
    private DocketCalculation docketCalculation;

    // model class to store docket detail in edit mode
    private DocketDetail docketDetail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docket_details, container, false);

        // hide keyboard
        Objects.requireNonNull(getActivity()).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // get intent values
        isEditable = getActivity().getIntent().getBooleanExtra(getActivity().getResources().getString(R.string.key_is_edit), false);
        isReadOnly = getActivity().getIntent().getBooleanExtra(getActivity().getResources().getString(R.string.key_read_only), false);
        docketId = getActivity().getIntent().getStringExtra(getActivity().getResources().getString(R.string.key_docket_id));

        // get preference data
        getPreferenceData();

        // call method to start operational flow
        doYourWork();

        return binding.getRoot();
    }

    // region initial method
    /*
     * Method to start initial operation
     *
     *
     * */
    private void doYourWork() {

        try {

            // set on item selected listener for spinners
            binding.spinnerConsignor.setOnItemSelectedListener(this);
            binding.spinnerDeliveryType.setOnItemSelectedListener(this);
            binding.spinnerDestinationCity.setOnItemSelectedListener(this);
            binding.spinnerDestinationState.setOnItemSelectedListener(this);
            binding.spinnerPackingType.setOnItemSelectedListener(this);
            binding.spinnerPostcode.setOnItemSelectedListener(this);
            binding.spinnerVertical.setOnItemSelectedListener(this);

            // set layout manager for dimension recycler view
            binding.recyclerViewDimensionDetails.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            binding.recyclerViewDimensionDetails.setItemAnimator(new DefaultItemAnimator());

            // set adapter for recycler view
            dimensionAdapter = new DocketDimensionAdapter(dimensionList, DocketDetailFragment.this, isReadOnly, fmCreated);
            binding.recyclerViewDimensionDetails.setAdapter(dimensionAdapter);

            if (isEditable) {

                // disable docket number field
                BaseActivity.disableView(binding.tnlDocketNo.getEditText());
                BaseActivity.disableView(binding.tnlBookingDate.getEditText());

                // call method to get docket details from docket Id
                getDocketDetails();

            } else {

                // enable docket number field
                BaseActivity.enableView(binding.tnlDocketNo.getEditText());
                BaseActivity.enableView(binding.tnlBookingDate.getEditText());

                // set booking date
                Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(getCurrentDate(AppConstant.CALENDAR_DATE_FORMAT));

                // set origin
                Objects.requireNonNull(binding.tnlOrigin.getEditText()).setText(getStringValue(prefUserModel.getBranchName()).toUpperCase());

                // call method to get auto generated docket number from server
                getDocketAutoNumber();

                // call method to get some predefined spinner data
                getPredefinedValues();

                // set components
                BaseActivity.hideView(binding.rvContainerPaymentType);
                BaseActivity.visibleView(binding.rvContainerConsignorDetails);
                BaseActivity.hideView(binding.tnlConsignor);
                BaseActivity.visibleView(binding.rvContainerVerticalDetails);
                BaseActivity.disableView(binding.spinnerVertical);
                BaseActivity.disableView(binding.spinnerPackingType);
                BaseActivity.disableView(binding.tnlProduct.getEditText());

                BaseActivity.disableView(binding.tnlConsignorAddress.getEditText());
                BaseActivity.disableView(binding.tnlConsignorPostcode.getEditText());
                BaseActivity.disableView(binding.tnlConsignorMobileNumber.getEditText());
                BaseActivity.disableView(binding.tnlConsignorGstNumber.getEditText());

                binding.rbTypeOwner.setChecked(true);

                customerType = CUSTOMER_TYPE_CREDIT;
                dispatchMode = MODE_TYPE_SURFACE;
                paymentType = PAYMENT_TYPE_TO_BE_BILLED;
                riskType = RISK_TYPE_OWNER;
            }

            // click event to add new dimension details
            binding.btnAddDimension.setOnClickListener(v -> {

                if (customerType.equalsIgnoreCase(CUSTOMER_TYPE_CREDIT) && consignorId <= 0) {

                    displayShortToast(getActivity(), "Please select consignor first.");

                } else {

                    // call method to open dimension dialog
                    showDimensionDialog(-1);
                }
            });

            // click event to calculate docket charges
            binding.btnCalculate.setOnClickListener(v -> {

                // update calculation flag
                isForCalculation = true;

                // call method to check validations
                checkFormValidation();
            });

            // click event to save docket details on server
            binding.btnSave.setOnClickListener(v -> {

                // update calculation flag
                isForCalculation = false;

                // call method to check validations
                checkFormValidation();
            });

            // region customer type selection
            binding.rbGroupCustomerType.setOnCheckedChangeListener((group, checkedId) -> {

                switch (checkedId) {

                    case R.id.rb_type_credit:

                        BaseActivity.hideView(binding.rvContainerPaymentType);
                        BaseActivity.visibleView(binding.rvContainerConsignorDetails);
                        BaseActivity.hideView(binding.tnlConsignor);
                        BaseActivity.visibleView(binding.rvContainerVerticalDetails);
                        BaseActivity.disableView(binding.spinnerVertical);
                        BaseActivity.disableView(binding.spinnerPackingType);
                        BaseActivity.disableView(binding.tnlProduct.getEditText());

                        BaseActivity.disableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorGstNumber.getEditText());

                        binding.rbTypeOwner.setChecked(true);

                        customerType = CUSTOMER_TYPE_CREDIT;
                        paymentType = PAYMENT_TYPE_TO_BE_BILLED;
                        riskType = RISK_TYPE_OWNER;

                        // reset dimension summary
                        //setDimensionDetails(null, true);

                        // call API to calculate dimension details
                        calculateDimensions();

                        // call method to set consignor details
                        setConsignorDetails(null);

                        break;

                    case R.id.rb_type_retail:

                        BaseActivity.visibleView(binding.rvContainerPaymentType);
                        BaseActivity.hideView(binding.rvContainerConsignorDetails);
                        BaseActivity.visibleView(binding.tnlConsignor);
                        BaseActivity.hideView(binding.rvContainerVerticalDetails);
                        BaseActivity.enableView(binding.spinnerPackingType);
                        BaseActivity.enableView(binding.tnlProduct.getEditText());

                        BaseActivity.enableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorGstNumber.getEditText());

                        binding.rbTypePaid.setChecked(true);
                        binding.rbTypeCarrier.setChecked(true);

                        customerType = CUSTOMER_TYPE_RETAIL;
                        paymentType = PAYMENT_TYPE_PAID;
                        riskType = RISK_TYPE_CARRIER;

                        if (!isEditable || !firstTime) {

                            // call API to calculate dimension details
                            calculateDimensions();

                            // call method to set consignor details
                            setConsignorDetails(null);
                        }

                        break;
                }
            });
            // endregion

            // region dispatch mode selection
            binding.rbGroupDispatchMode.setOnCheckedChangeListener((group, checkedId) -> {

                switch (checkedId) {

                    case R.id.rb_mode_surface:

                        dispatchMode = MODE_TYPE_SURFACE;
                        break;

                    case R.id.rb_mode_air:

                        dispatchMode = MODE_TYPE_AIR;
                        break;
                }

                if (!firstTime) {
                    // call API to get Estimated Delivery Date (EDD)
                    calculateEstimatedDeliveryDate();
                }
            });
            // endregion

            // region payment type selection
            binding.rbGroupPaymentType.setOnCheckedChangeListener((group, checkedId) -> {

                switch (checkedId) {

                    case R.id.rb_type_paid:

                        paymentType = PAYMENT_TYPE_PAID;
                        break;

                    case R.id.rb_type_to_pay:

                        paymentType = PAYMENT_TYPE_TO_PAY;
                        break;
                }
            });
            // endregion

            // region open date picker dialog
            Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setOnClickListener(v -> {

                // open date picker dialog
                openDatePickerDialog(binding.tnlBookingDate, AppConstant.CALENDAR_DATE_FORMAT);
            });
            // endregion

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region call some API to get spinner data

    private synchronized void getPredefinedValues() {

        try {

            // call method to get consignor list
            getConsignorList();

            // call method to call API to get spinner data
            ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_DELIVERY_TYPE, null, binding.spinnerDeliveryType);
            ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_VERTICLE, null, binding.spinnerVertical);
            ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_PACKING_TYPE, null, binding.spinnerPackingType);
            ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_STATE, null, binding.spinnerDestinationState);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // endregion

    // region spinner item selected listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Map<String, Integer> inputParams;

        switch (parent.getId()) {

            case R.id.spinner_destination_state:

                stateId = castToInteger(BaseActivity.stateMasterList.get(position).getId());
                stateName = BaseActivity.stateMasterList.get(position).getName();

                if (!isEditable || !firstTime) {

                    inputParams = new HashMap<>();
                    inputParams.put(AppConstant.KEY_STATE_ID, stateId);

                    // call method to call API to get spinner data
                    ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_CITY, inputParams, binding.spinnerDestinationCity);
                }

                break;

            case R.id.spinner_destination_city:

                cityId = castToInteger(BaseActivity.cityMasterList.get(position).getId());

                inputParams = new HashMap<>();
                inputParams.put(AppConstant.KEY_CITY_ID, cityId);

                if (!isEditable || !firstTime) {

                    // call method to call API to get spinner data
                    ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_POSTCODE, inputParams, binding.spinnerPostcode);
                }

                break;

            case R.id.spinner_postcode:

                postcodeId = castToInteger(BaseActivity.postcodeMasterList.get(position).getId());
                postcode = BaseActivity.postcodeMasterList.get(position).getPostcode();
                postcodeTypeId = castToInteger(BaseActivity.postcodeMasterList.get(position).getPostcodeTypeId());
                postcodeTypeName = getStringValue(BaseActivity.postcodeMasterList.get(position).getPostcodeTypeName());
                Objects.requireNonNull(binding.tnlPostcodeType.getEditText()).setText(postcodeTypeName);

                if (position > 0)
                    Objects.requireNonNull(binding.tnlConsigneePostcode.getEditText()).setText(getStringValue(BaseActivity.postcodeMasterList.get(position).getPostcode()));
                else
                    Objects.requireNonNull(binding.tnlConsigneePostcode.getEditText()).setText("");

                if (!isEditable || !firstTime) {

                    // call API to get Estimated Delivery Date (EDD)
                    calculateEstimatedDeliveryDate();
                }

                // set flag to false
                firstTime = false;

                break;

            case R.id.spinner_consignor:

                consignorId = castToInteger(consignorList.get(position).getId());
                consignorCode = consignorList.get(position).getCustomerCode();
                consignorName = consignorList.get(position).getCustomerName();

                // call method to set consignor details
                setConsignorDetails(consignorList.get(position));

                break;

            case R.id.spinner_delivery_type:

                deliveryTypeId = castToInteger(deliveryTypeMasterList.get(position).getId());
                break;

            case R.id.spinner_vertical:

                verticalId = castToInteger(BaseActivity.verticleMasterList.get(position).getId());
                break;

            case R.id.spinner_packing_type:

                packingTypeId = castToInteger(BaseActivity.packingTypeMasterList.get(position).getId());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    // endregion

    // region display dimension dialog
    /*
     * Method to show dimension dialog
     *
     * */
    private void showDimensionDialog(int itemPosition) {

        dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.dialog_docket_dimension_detail, null, false);

        Dialog mDialog = new Dialog(Objects.requireNonNull(getActivity()));
        mDialog.setContentView(dialogBinding.getRoot());
        mDialog.show();

        if (itemPosition >= 0) {

            Dimension dimension = dimensionList.get(itemPosition);

            Objects.requireNonNull(dialogBinding.tnlNoOfBoxes.getEditText()).setText(getFormattedString(dimension.getBoxes(), AppConstant.FORMAT_0_F));
            Objects.requireNonNull(dialogBinding.tnlLength.getEditText()).setText(getFormattedString(dimension.getLength(), AppConstant.FORMAT_2_F));
            Objects.requireNonNull(dialogBinding.tnlWidth.getEditText()).setText(getFormattedString(dimension.getWidth(), AppConstant.FORMAT_2_F));
            Objects.requireNonNull(dialogBinding.tnlHeight.getEditText()).setText(getFormattedString(dimension.getHeight(), AppConstant.FORMAT_2_F));
            Objects.requireNonNull(dialogBinding.tnlTotalActualWeight.getEditText()).setText(getFormattedString(dimension.getTotalActualWeight(), AppConstant.FORMAT_2_F));

            // call method to calculate actual weight per box
            calculateActualWeightPerBox();
        }

        Objects.requireNonNull(dialogBinding.tnlNoOfBoxes.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // call method to calculate actual weight per box
                calculateActualWeightPerBox();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Objects.requireNonNull(dialogBinding.tnlTotalActualWeight.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // call method to calculate actual weight per box
                calculateActualWeightPerBox();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dialogBinding.btnSubmit.setOnClickListener(v -> {

            if (checkDialogValidation()) {

                Dimension dimension;

                if (itemPosition >= 0) {
                    dimension = dimensionList.get(itemPosition);
                } else {
                    dimension = new Dimension();
                }

                dimension.setId("");
                dimension.setDocketId("");
                dimension.setBoxes(getFormattedString(getTrimString(dialogBinding.tnlNoOfBoxes), AppConstant.FORMAT_0_F));
                dimension.setLength(getFormattedString(getTrimString(dialogBinding.tnlLength), AppConstant.FORMAT_2_F));
                dimension.setWidth(getFormattedString(getTrimString(dialogBinding.tnlWidth), AppConstant.FORMAT_2_F));
                dimension.setHeight(getFormattedString(getTrimString(dialogBinding.tnlHeight), AppConstant.FORMAT_2_F));
                dimension.setActualWeight(getFormattedString(getTrimString(dialogBinding.tnlActualBoxWeight), AppConstant.FORMAT_2_F));
                dimension.setVolumetricWeight("");
                dimension.setIsActive(AppConstant.STATUS_ACTIVE);
                dimension.setIsDelete(AppConstant.STATUS_DELETE);
                dimension.setTotalActualWeight(getFormattedString(getTrimString(dialogBinding.tnlTotalActualWeight), AppConstant.FORMAT_2_F));

                if (itemPosition == -1)
                    dimensionList.add(dimension);

                // notify dimension adapter
                dimensionAdapter.notifyDataSetChanged();

                //dismiss dialog
                mDialog.dismiss();

                // call API to calculate dimension details
                calculateDimensions();
            }
        });

        dialogBinding.ivClose.setOnClickListener(v -> mDialog.dismiss());

        mDialog.setOnDismissListener(dialog -> BaseActivity.hideSoftKeyboard());
    }
    // endregion

    // region calculate actual weight per box for dimension details
    /*
     * Method to calculate actual weight of box
     *
     *
     * */
    private void calculateActualWeightPerBox() {

        try {

            double noOfBoxes = !isEmptyString(getTrimString(dialogBinding.tnlNoOfBoxes)) ? castToDouble(getTrimString(dialogBinding.tnlNoOfBoxes)) : 0;
            double totalActualWeight = !isEmptyString(getTrimString(dialogBinding.tnlTotalActualWeight)) ? castToDouble(getTrimString(dialogBinding.tnlTotalActualWeight)) : 0;

            // calculate per box actual weight
            double perBoxActualWeight = (totalActualWeight / noOfBoxes);

            if (perBoxActualWeight == Double.POSITIVE_INFINITY || perBoxActualWeight == Double.NEGATIVE_INFINITY || Double.isNaN(perBoxActualWeight))
                perBoxActualWeight = 0;

            Objects.requireNonNull(dialogBinding.tnlActualBoxWeight.getEditText()).setText(getFormattedString(String.valueOf(perBoxActualWeight), AppConstant.FORMAT_2_F));

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    /*
     * Method to check dimension dialog validations
     *
     *
     * */
    private boolean checkDialogValidation() {

        return AppValidation.getInstance().validateAmount(getActivity(), dialogBinding.tnlNoOfBoxes, "This is mandatory field", "Value should be greater than zero") &&
                AppValidation.getInstance().validateAmount(getActivity(), dialogBinding.tnlLength, "This is mandatory field", "Value should be greater than zero") &&
                AppValidation.getInstance().validateAmount(getActivity(), dialogBinding.tnlWidth, "This is mandatory field", "Value should be greater than zero") &&
                AppValidation.getInstance().validateAmount(getActivity(), dialogBinding.tnlHeight, "This is mandatory field", "Value should be greater than zero") &&
                AppValidation.getInstance().validateAmount(getActivity(), dialogBinding.tnlTotalActualWeight, "This is mandatory field", "Value should be greater than zero");
    }

    // region click listener for dimension details
    @Override
    public void onUpdate(int itemPosition, Dimension dimension) {
        showDimensionDialog(itemPosition);
    }

    @Override
    public void onDelete(int itemPosition, Dimension dimension) {
        displayConfirmationDialog(itemPosition);
    }
    // endregion

    /*
     * Method to display confirmation dialog
     *
     *
     * */
    private void displayConfirmationDialog(int itemPosition) {

        simpleAlertDialog = new SimpleAlertDialog(getActivity()) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return Objects.requireNonNull(getActivity()).getResources().getString(R.string.dialog_title_confirmation);
            }

            @Override
            public String setDialogMessage() {
                return Objects.requireNonNull(getActivity()).getResources().getString(R.string.dialog_msg_dimension_delete_confirmation);
            }

            @Override
            public Drawable setDialogIcon() {
                return Objects.requireNonNull(getActivity()).getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return Objects.requireNonNull(getActivity()).getResources().getString(R.string.btn_title_proceed);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    // removed item from list
                    dimensionList.remove(itemPosition);
                    dimensionAdapter.notifyItemRemoved(itemPosition);
                    dimensionAdapter.notifyItemRangeChanged(itemPosition, dimensionList.size());

                    // call API to calculate dimension details
                    calculateDimensions();
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return Objects.requireNonNull(getActivity()).getResources().getString(R.string.btn_title_cancel);
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

    // region calculate estimated delivery date request
    /*
     * Method to call API to calculate Estimated Delivery Date (EDD)
     *
     *
     * */
    private void calculateEstimatedDeliveryDate() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.EDDCalculationRequest eddCalculationRequest = new DocketRequestModel().new EDDCalculationRequest();

                eddCalculationRequest.setCompanyId(prefUserModel.getCompanyId());
                eddCalculationRequest.setBookingBranchStateId(prefUserModel.getStateId());
                eddCalculationRequest.setCustomerType(customerType);
                eddCalculationRequest.setDispatchMode(dispatchMode);
                eddCalculationRequest.setDestinationStateId(String.valueOf(stateId));
                eddCalculationRequest.setDestinationId(String.valueOf(cityId));
                eddCalculationRequest.setDestinationPostcodeType(postcodeTypeName);
                eddCalculationRequest.setBookingDate(convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, getTrimString(binding.tnlBookingDate)));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse> call = apiService.estimatedDeliveryDateCalculation(eddCalculationRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_EDD_CALCULATION, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region calculate dimensions request
    /*
     * Method to call API to calculate dimension details
     *
     *
     * */
    private void calculateDimensions() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.DimensionCalculationRequest calculationRequest = new DocketRequestModel().new DimensionCalculationRequest();

                calculationRequest.setCompanyId(prefUserModel.getCompanyId());
                calculationRequest.setCustomerType(customerType);
                calculationRequest.setConsignorId(String.valueOf(consignorId));
                calculationRequest.setDispatchMode(dispatchMode);
                calculationRequest.setDimensionList(dimensionList);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<DimensionDetail>> call = apiService.dimensionCalculation(calculationRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DIMENSION_CALCULATION, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get consignor list request
    /*
     * Method to call API to get consignor list from server
     *
     *
     * */
    private void getConsignorList() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.ConsignorListRequest consignorListRequest = new DocketRequestModel().new ConsignorListRequest();

                consignorListRequest.setBranchId(prefUserModel.getBranchId());

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<Consignor>>> call = apiService.getConsignorList(consignorListRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_CONSIGNOR_LIST, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get state list request
    /*
     * Method to call API to get state list from server
     *
     *
     * */
    private void getStateList() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                MasterRequestModel.GetStateRequest stateRequest = new MasterRequestModel().new GetStateRequest();

                stateRequest.setCountryId(AppConstant.COUNTRY_ID);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getState(stateRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_STATE, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get city list request
    /*
     * Method to call API to get city list from server
     *
     *
     * */
    private void getCityList() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                MasterRequestModel.GetCityRequest cityRequest = new MasterRequestModel().new GetCityRequest();

                cityRequest.setStateId(String.valueOf(stateId));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getCity(cityRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_CITY, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get postcode list request
    /*
     * Method to call API to get postcode list from server
     *
     *
     * */
    private void getPostcodeList() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                MasterRequestModel.GetPostcodeRequest postcodeRequest = new MasterRequestModel().new GetPostcodeRequest();

                postcodeRequest.setCityId(String.valueOf(cityId));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getPostcode(postcodeRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_POST_CODE, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get packing type list request
    /*
     * Method to call API to get packing type list from server
     *
     *
     * */
    private void getPackingTypeList() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getPackingType();

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_PACKING_TYPE, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get verticle list request
    /*
     * Method to call API to get verticle list from server
     *
     *
     * */
    private void getVerticleList() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<ArrayList<CommonListResponse>>> call = apiService.getVerticle();

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_VERTICLE, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get auto generated docket number request
    /*
     * Method to call API to get auto generated docket number from server
     *
     *
     * */
    private void getDocketAutoNumber() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.GetDocketAutoNo docketAutoNo = new DocketRequestModel().new GetDocketAutoNo();

                docketAutoNo.setCompanyId(prefUserModel.getCompanyId());
                docketAutoNo.setBranchId(prefUserModel.getBranchId());
                docketAutoNo.setTableName(AppConstant.TABLE_NAME_DOCKET);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse> call = apiService.getDocketAutoNo(docketAutoNo);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_AUTO_DOCKET_NUMBER, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region get docket details from docket ID
    /*
     * Method to call API to get docket details from docket ID
     *
     *
     * */
    private void getDocketDetails() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.GetDocketDetailRequest getDocketDetailRequest = new DocketRequestModel().new GetDocketDetailRequest();

                getDocketDetailRequest.setCompanyId(prefUserModel.getCompanyId());
                getDocketDetailRequest.setBranchId(prefUserModel.getBranchId());
                getDocketDetailRequest.setDocketId(docketId);

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<DocketDetail>> call = apiService.getDocketById(getDocketDetailRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_GET_DOCKET_DETAIL, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region response handler listener

    // region API success listener
    @Override
    public void onApiSuccess(String endPointName, Object responseBody) {

        switch (endPointName) {

            case ApiConstant.API_DIMENSION_CALCULATION:

                CommonResponse<DimensionDetail> dimensionDetailResponse = (CommonResponse<DimensionDetail>) responseBody;
                processCalculationResponse(dimensionDetailResponse);
                break;

            case ApiConstant.API_EDD_CALCULATION:

                CommonResponse commonResponse = (CommonResponse) responseBody;
                processEddCalculationResponse(commonResponse);
                break;

            case ApiConstant.API_CONSIGNOR_LIST:

                CommonResponse<ArrayList<Consignor>> consignorListResponse = (CommonResponse<ArrayList<Consignor>>) responseBody;
                processConsignorListResponse(consignorListResponse);
                break;

            case ApiConstant.API_CALCULATE_DOCKET_CHARGES:

                CommonResponse<DocketCalculation> docketCalculationResponse = (CommonResponse<DocketCalculation>) responseBody;
                processDocketCalculationResponse(docketCalculationResponse);
                break;

            case ApiConstant.API_AUTO_DOCKET_NUMBER:

                CommonResponse autoDocketResponse = (CommonResponse) responseBody;
                setAutoDocketNumber(autoDocketResponse);
                break;

            case ApiConstant.API_DOCKET_OPERATION:

                CommonResponse docketOperationResponse = (CommonResponse) responseBody;
                processDocketOperationResponse(docketOperationResponse);
                break;

            case ApiConstant.API_CHECK_DUPLICATE_DOCKET_NO:

                CommonResponse duplicateDocketResponse = (CommonResponse) responseBody;
                processDuplicateDocketNumberResponse(duplicateDocketResponse);
                break;

            case ApiConstant.API_GET_DOCKET_DETAIL:

                CommonResponse<DocketDetail> docketDetailResponse = (CommonResponse<DocketDetail>) responseBody;
                processDocketDetailResponse(docketDetailResponse);
                break;

            case ApiConstant.API_STATE:

                CommonResponse<ArrayList<CommonListResponse>> stateListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processStateListResponse(stateListResponse);
                break;

            case ApiConstant.API_CITY:

                CommonResponse<ArrayList<CommonListResponse>> cityListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processCityListResponse(cityListResponse);
                break;

            case ApiConstant.API_POST_CODE:

                CommonResponse<ArrayList<CommonListResponse>> postcodeListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processPostcodeListResponse(postcodeListResponse);
                break;

            case ApiConstant.API_VERTICLE:

                CommonResponse<ArrayList<CommonListResponse>> verticleListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processVerticleListResponse(verticleListResponse);
                break;

            case ApiConstant.API_PACKING_TYPE:

                CommonResponse<ArrayList<CommonListResponse>> packingTypeListResponse = (CommonResponse<ArrayList<CommonListResponse>>) responseBody;
                processPackingTypeListResponse(packingTypeListResponse);
                break;
        }
    }
    // endregion

    // region API error listener
    @Override
    public void onApiError(String endPointName, String errorMessage) {

        switch (endPointName) {

            case ApiConstant.API_DIMENSION_CALCULATION:

                printErrorLog(TAG, errorMessage);
                break;

            case ApiConstant.API_EDD_CALCULATION:

                printErrorLog(TAG, errorMessage);
                break;

            case ApiConstant.API_CONSIGNOR_LIST:

                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_CONSIGNOR);
                break;

            case ApiConstant.API_CALCULATE_DOCKET_CHARGES:

                printErrorLog(TAG, errorMessage);
                break;

            case ApiConstant.API_AUTO_DOCKET_NUMBER:

                printErrorLog(TAG, errorMessage);
                Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText("");
                break;

            case ApiConstant.API_DOCKET_OPERATION:

                printErrorLog(TAG, errorMessage);
                break;

            case ApiConstant.API_CHECK_DUPLICATE_DOCKET_NO:

                printErrorLog(TAG, errorMessage);
                break;

            case ApiConstant.API_GET_DOCKET_DETAIL:

                printErrorLog(TAG, errorMessage);
                break;

            case ApiConstant.API_STATE:

                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_STATE);
                break;

            case ApiConstant.API_CITY:

                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_CITY);
                break;

            case ApiConstant.API_POST_CODE:

                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_POSTCODE);
                break;

            case ApiConstant.API_VERTICLE:

                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_VERTICLE);
                break;

            case ApiConstant.API_PACKING_TYPE:

                printErrorLog(TAG, errorMessage);
                setSpinnerHeader(AppConstant.SP_PACKING_TYPE);
                break;
        }
    }
    // endregion

    // region API failure listener
    @Override
    public void onApiFailure(String endPointName, String failureMessage) {

        switch (endPointName) {

            case ApiConstant.API_DIMENSION_CALCULATION:

                printErrorLog(TAG, failureMessage);
                break;

            case ApiConstant.API_EDD_CALCULATION:

                printErrorLog(TAG, failureMessage);
                break;

            case ApiConstant.API_CONSIGNOR_LIST:

                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_CONSIGNOR);
                break;

            case ApiConstant.API_CALCULATE_DOCKET_CHARGES:

                printErrorLog(TAG, failureMessage);
                break;

            case ApiConstant.API_AUTO_DOCKET_NUMBER:

                printErrorLog(TAG, failureMessage);
                Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText("");
                break;

            case ApiConstant.API_DOCKET_OPERATION:

                printErrorLog(TAG, failureMessage);
                break;

            case ApiConstant.API_CHECK_DUPLICATE_DOCKET_NO:

                printErrorLog(TAG, failureMessage);
                break;

            case ApiConstant.API_GET_DOCKET_DETAIL:

                printErrorLog(TAG, failureMessage);
                break;

            case ApiConstant.API_STATE:

                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_STATE);
                break;

            case ApiConstant.API_CITY:

                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_CITY);
                break;

            case ApiConstant.API_POST_CODE:

                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_POSTCODE);
                break;

            case ApiConstant.API_VERTICLE:

                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_VERTICLE);
                break;

            case ApiConstant.API_PACKING_TYPE:

                printErrorLog(TAG, failureMessage);
                setSpinnerHeader(AppConstant.SP_PACKING_TYPE);
                break;
        }
    }
    // endregion

    // endregion

    // region response handler

    /*
     * Method to process EDD calculation response
     *
     *
     * */
    private void processEddCalculationResponse(CommonResponse response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:

                        Objects.requireNonNull(binding.tnlEstimatedDeliveryDate.getEditText()).setText(convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, getStringValue(String.valueOf(response.getData()))));
                        break;

                    case AppConstant.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        Objects.requireNonNull(binding.tnlEstimatedDeliveryDate.getEditText()).setText("");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process dimension calculation response
     *
     *
     * */
    private void processCalculationResponse(CommonResponse<DimensionDetail> response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:

                        dimensionList = new ArrayList<>();
                        dimensionList = response.getData().getDimensionList();

                        if (dimensionList != null && dimensionList.size() > 0) {

                            // update IsActive and IsDelete flag in list for calculation purpose
                            for (int i = 0; i < dimensionList.size(); i++) {

                                dimensionList.get(i).setIsActive(AppConstant.STATUS_ACTIVE);
                                dimensionList.get(i).setIsDelete(AppConstant.STATUS_DELETE);
                            }

                        } else {
                            dimensionList = new ArrayList<>();
                        }

                        setDimensionDetails(response.getData(), false);
                        break;

                    case AppConstant.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), response.getMessage());
                        setDimensionDetails(response.getData(), true);
                        break;
                }

                dimensionAdapter = new DocketDimensionAdapter(dimensionList, DocketDetailFragment.this, isReadOnly, fmCreated);
                binding.recyclerViewDimensionDetails.setAdapter(dimensionAdapter);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process docket calculation response
     *
     *
     * */
    private void processDocketCalculationResponse(CommonResponse<DocketCalculation> response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:

                        docketCalculation = response.getData();

                        if (isForCalculation) {

                            // call method to set docket charges
                            setDocketCharges();

                        } else {

                            if (isEditable) {

                                // call method to save docket information on server
                                saveNewDocket();

                            } else {

                                // call method to check duplicate docket number
                                checkDuplicateDocketNo();
                            }
                        }

                        break;

                    case AppConstant.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.err_msg_api_response_failure));
                        docketCalculation = new DocketCalculation();
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process docket operation response
     *
     *
     * */
    private void processDocketOperationResponse(CommonResponse response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:

                        if (!isEmptyString(docketId))
                            displayShortToast(getActivity(), "Docket updated successfully");
                        else
                            displayShortToast(getActivity(), "Docket saved successfully");
                        Intent mIntent = new Intent();
                        Objects.requireNonNull(getActivity()).setResult(RESULT_OK, mIntent);
                        Objects.requireNonNull(getActivity()).finish();
                        break;

                    case AppConstant.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.err_msg_api_response_failure));
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process duplicate docket number response
     *
     *
     * */
    private void processDuplicateDocketNumberResponse(CommonResponse response) {

        try {

            if (response != null) {

                switch (response.getCount()) {

                    case AppConstant.STATUS_SUCCESS:

                        // call method to save docket information on server
                        saveNewDocket();
                        break;

                    case AppConstant.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), "Docket No. Already Exists.");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process docket calculation response
     *
     *
     * */
    private void setAutoDocketNumber(CommonResponse response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:

                        docketAutoNo = getStringValue(response.getData().toString());
                        Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText(getStringValue(response.getData().toString()));
                        break;

                    case AppConstant.STATUS_FAILURE:

                        docketAutoNo = "";
                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.err_msg_api_response_failure));
                        Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText("");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process docket detail response
     *
     *
     * */
    private void processDocketDetailResponse(CommonResponse<DocketDetail> response) {

        try {

            if (response != null) {

                switch (response.getStatus()) {

                    case AppConstant.STATUS_SUCCESS:

                        // call method to set docket details
                        setDocketDetails(response.getData());
                        break;

                    case AppConstant.STATUS_FAILURE:

                        printErrorLog(TAG, response.getMessage());
                        displayShortToast(getActivity(), Objects.requireNonNull(getActivity()).getResources().getString(R.string.err_msg_api_response_failure));
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // endregion

    // region set dimension summary

    /*
     * Method to set dimension details
     *
     *
     * */
    private void setDimensionDetails(DimensionDetail dimensionDetail, boolean clearRequired) {

        try {

            binding.tvTotalNoOfBoxes.setText(clearRequired ? "0.0" : getFormattedString(dimensionDetail.getNoOfPackages(), AppConstant.FORMAT_0_F));
            binding.tvActualWeight.setText(clearRequired ? "0.0" : getFormattedString(dimensionDetail.getActualWeight(), AppConstant.FORMAT_2_F));
            binding.tvVolumetricWeight.setText(clearRequired ? "0.0" : getFormattedString(dimensionDetail.getVolumetricWeight(), AppConstant.FORMAT_2_F));
            binding.tvChargeableWeight.setText(clearRequired ? "0.0" : getFormattedString(dimensionDetail.getChargeWeight(), AppConstant.FORMAT_2_F));
            binding.tvChargeablePercentageWeight.setText(clearRequired ? "0.0" : getFormattedString(dimensionDetail.getChargeWeightPercentage(), AppConstant.FORMAT_2_F));

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // endregion

    /*
     * Method to process consignor list response
     *
     *
     * */
    private void processConsignorListResponse(CommonResponse<ArrayList<Consignor>> response) {

        try {

            consignorList = new ArrayList<>();
            consignorList = response.getData();
            setSpinnerHeader(AppConstant.SP_CONSIGNOR);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process state list response
     *
     *
     * */
    private void processStateListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {

        try {

            // call method to get city list
            getCityList();

            stateMasterList = new ArrayList<>();
            stateMasterList = response.getData();
            setSpinnerHeader(AppConstant.SP_STATE);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process city list response
     *
     *
     * */
    private void processCityListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {

        try {

            // call method to get postcode list
            getPostcodeList();

            cityMasterList = new ArrayList<>();
            cityMasterList = response.getData();
            setSpinnerHeader(AppConstant.SP_CITY);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process postcode list response
     *
     *
     * */
    private void processPostcodeListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {

        try {

            postcodeMasterList = new ArrayList<>();
            postcodeMasterList = response.getData();
            setSpinnerHeader(AppConstant.SP_POSTCODE);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process verticle list response
     *
     *
     * */
    private void processVerticleListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {

        try {

            verticleMasterList = new ArrayList<>();
            verticleMasterList = response.getData();
            setSpinnerHeader(AppConstant.SP_VERTICLE);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to process packing type list response
     *
     *
     * */
    private void processPackingTypeListResponse(CommonResponse<ArrayList<CommonListResponse>> response) {

        try {

            packingTypeMasterList = new ArrayList<>();
            packingTypeMasterList = response.getData();
            setSpinnerHeader(AppConstant.SP_PACKING_TYPE);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to set spinner header at first position of spinner
     *
     *
     * */
    private void setSpinnerHeader(String spinnerType) {

        try {

            switch (spinnerType) {

                case AppConstant.SP_CONSIGNOR:

                    if (consignorList == null)
                        consignorList = new ArrayList<>();

                    consignorList.add(0, new Consignor("0", Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_consignor_header_title)));
                    bindSpinnerData();
                    break;

                case AppConstant.SP_STATE:

                    if (stateMasterList == null)
                        stateMasterList = new ArrayList<>();

                    stateMasterList.add(0, new CommonListResponse("0", Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_state_header_title)));
                    break;

                case AppConstant.SP_CITY:

                    if (cityMasterList == null)
                        cityMasterList = new ArrayList<>();

                    cityMasterList.add(0, new CommonListResponse("0", Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_city_header_title)));
                    break;

                case AppConstant.SP_POSTCODE:

                    if (postcodeMasterList == null)
                        postcodeMasterList = new ArrayList<>();

                    postcodeMasterList.add(0, new CommonListResponse("0", Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_postcode_header_title), Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_postcode_header_title)));

                    // set spinner position
                    ((BaseActivity) Objects.requireNonNull(getActivity())).bindSpinnerData(AppConstant.SP_STATE, binding.spinnerDestinationState, stateMasterList, stateId);
                    ((BaseActivity) Objects.requireNonNull(getActivity())).bindSpinnerData(AppConstant.SP_CITY, binding.spinnerDestinationCity, cityMasterList, cityId);
                    ((BaseActivity) Objects.requireNonNull(getActivity())).bindSpinnerData(AppConstant.SP_POSTCODE, binding.spinnerPostcode, postcodeMasterList, postcodeId);
                    break;

                case AppConstant.SP_PACKING_TYPE:

                    if (packingTypeMasterList == null)
                        packingTypeMasterList = new ArrayList<>();

                    packingTypeMasterList.add(0, new CommonListResponse("0", Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_packing_type_header_title)));

                    // set spinner position
                    ((BaseActivity) Objects.requireNonNull(getActivity())).bindSpinnerData(AppConstant.SP_PACKING_TYPE, binding.spinnerPackingType, packingTypeMasterList, packingTypeId);
                    break;

                case AppConstant.SP_VERTICLE:

                    if (verticleMasterList == null)
                        verticleMasterList = new ArrayList<>();

                    verticleMasterList.add(0, new CommonListResponse("0", Objects.requireNonNull(getActivity()).getResources().getString(R.string.spinner_verticle_header_title)));

                    // set spinner position
                    ((BaseActivity) Objects.requireNonNull(getActivity())).bindSpinnerData(AppConstant.SP_VERTICLE, binding.spinnerVertical, verticleMasterList, verticalId);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to bind spinner data from API response
     *
     *
     * */
    private void bindSpinnerData() {

        try {

            ArrayList<String> itemSet = new ArrayList<>();

            int selectedPosition = 0, currentItemPosition = -1;

            for (Consignor data : consignorList) {

                currentItemPosition = currentItemPosition + 1;

                itemSet.add(getStringValue(convertToUpperCase(data.getCustomerName())));

                if (consignorId > 0 && data.getId().equals(String.valueOf(consignorId))) {
                    selectedPosition = currentItemPosition;
                    printInfoLog(TAG, "Selected Item Position : " + selectedPosition);
                }
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), R.layout.custom_spinner_item, itemSet);
            arrayAdapter.setDropDownViewResource(R.layout.custom_spinner_popup);
            binding.spinnerConsignor.setAdapter(arrayAdapter);

            if (selectedPosition > 0) {
                binding.spinnerConsignor.setSelection(selectedPosition);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to set consignor details
     *
     *
     *
     * */
    private void setConsignorDetails(Consignor consignor) {

        try {

            if (consignor != null) {

                isSezGst = !isEmptyString(consignor.getIsSez()) ? consignor.getIsSez() : "0";
                verticalId = !isEmptyString(consignor.getVerticleId()) ? castToInteger(getStringValue(consignor.getVerticleId())) : 0;
                packingTypeId = !isEmptyString(consignor.getPackingTypeId()) ? castToInteger(getStringValue(consignor.getPackingTypeId())) : 0;
                riskType = !isEmptyString(consignor.getRiskType()) ? consignor.getRiskType() : RISK_TYPE_OWNER;

                if (riskType.equalsIgnoreCase(RISK_TYPE_OWNER))
                    binding.rbTypeOwner.setChecked(true);
                else
                    binding.rbTypeCarrier.setChecked(true);

                Objects.requireNonNull(binding.tnlConsignor.getEditText()).setText("");
                Objects.requireNonNull(binding.tnlConsignorAddress.getEditText()).setText(concatAddress(consignor));
                Objects.requireNonNull(binding.tnlConsignorPostcode.getEditText()).setText(getStringValue(consignor.getPostcode()));
                Objects.requireNonNull(binding.tnlConsignorMobileNumber.getEditText()).setText(getStringValue(consignor.getMobileNo()));
                Objects.requireNonNull(binding.tnlConsignorGstNumber.getEditText()).setText(getStringValue(consignor.getGstNo()).toUpperCase());
                Objects.requireNonNull(binding.tnlProduct.getEditText()).setText(getStringValue(consignor.getProductName()));

                // call method to set spinner position
                setSpinnerPosition(AppConstant.SP_VERTICLE, verticalId);
                setSpinnerPosition(AppConstant.SP_PACKING_TYPE, packingTypeId);

            } else {

                if (!isEditable || !firstTime) {

                    consignorId = 0;
                    consignorCode = "";
                    consignorName = "";

                    isSezGst = "0";
                    verticalId = 0;
                    packingTypeId = 0;
                    riskType = RISK_TYPE_CARRIER;

                    binding.rbTypeCarrier.setChecked(true);

                    Objects.requireNonNull(binding.tnlConsignor.getEditText()).setText("");
                    Objects.requireNonNull(binding.tnlConsignorAddress.getEditText()).setText("");
                    Objects.requireNonNull(binding.tnlConsignorPostcode.getEditText()).setText("");
                    Objects.requireNonNull(binding.tnlConsignorMobileNumber.getEditText()).setText("");
                    Objects.requireNonNull(binding.tnlConsignorGstNumber.getEditText()).setText("");
                    Objects.requireNonNull(binding.tnlProduct.getEditText()).setText("");

                    binding.spinnerConsignor.setSelection(0);

                    // call method to set spinner position
                    setSpinnerPosition(AppConstant.SP_VERTICLE, 0);
                    setSpinnerPosition(AppConstant.SP_PACKING_TYPE, 0);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to set spinner value
     *
     *
     * */
    private void setSpinnerPosition(String spinnerType, int searchId) {

        try {

            ArrayList<CommonListResponse> searchList = new ArrayList<>();
            int selectedPosition, currentItemPosition = -1;

            switch (spinnerType) {

                case AppConstant.SP_VERTICLE:

                    searchList = BaseActivity.verticleMasterList;
                    break;

                case AppConstant.SP_PACKING_TYPE:

                    searchList = BaseActivity.packingTypeMasterList;
                    break;
            }

            for (CommonListResponse item : searchList) {

                currentItemPosition = currentItemPosition + 1;

                if (searchId > 0 && item.getId().equals(String.valueOf(searchId))) {

                    selectedPosition = currentItemPosition;

                    switch (spinnerType) {

                        case AppConstant.SP_VERTICLE:

                            binding.spinnerVertical.setSelection(selectedPosition);
                            break;

                        case AppConstant.SP_PACKING_TYPE:

                            binding.spinnerPackingType.setSelection(selectedPosition);
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to concat consignor address
     *
     *
     * */
    private String concatAddress(Consignor consignor) {

        if (consignor != null) {

            String addressLine1 = getStringValue(consignor.getAdd1());
            String addressLine2 = getStringValue(consignor.getAdd2());
            String addressLine3 = getStringValue(consignor.getAdd3());

            return addressLine1.concat(addressLine2.concat(addressLine3)).trim();
        }

        return "";
    }

    /*
     * Method to set docket details for edit mode
     *
     *
     * */
    private void setDocketDetails(DocketDetail docketDetail) {

        try {

            if (docketDetail != null) {

                this.docketDetail = docketDetail;

                fmCreated = castToInteger(getStringValue(docketDetail.getfMId())) > 0;

                // hide view if FM is created
                if (fmCreated) {
                    BaseActivity.hideView(binding.btnAddDimension);
                    BaseActivity.hideView(binding.btnSave);
                    BaseActivity.hideView(binding.btnCalculate);
                }

                consignorId = castToInteger(docketDetail.getConsignorId());
                stateId = castToInteger(docketDetail.getDestinationStateId());
                cityId = castToInteger(docketDetail.getDestinationId());
                postcodeId = castToInteger(docketDetail.getDestinationPostCodeId());
                deliveryTypeId = castToInteger(docketDetail.getDeliveryType());
                verticalId = castToInteger(docketDetail.getVerticleTypeId());
                packingTypeId = castToInteger(docketDetail.getPackingTypeId());

                customerType = getStringValue(docketDetail.getCustomerType());

                isSezGst = getStringValue(docketDetail.getIsConsignorSEZ()).equalsIgnoreCase("1") ? "1" : "0";

                switch (customerType) {

                    case CUSTOMER_TYPE_CREDIT:

                        binding.rbTypeCredit.setChecked(true);

                        BaseActivity.hideView(binding.rvContainerPaymentType);
                        BaseActivity.visibleView(binding.rvContainerConsignorDetails);
                        BaseActivity.hideView(binding.tnlConsignor);
                        BaseActivity.visibleView(binding.rvContainerVerticalDetails);
                        BaseActivity.disableView(binding.spinnerVertical);
                        BaseActivity.disableView(binding.spinnerPackingType);
                        BaseActivity.disableView(binding.tnlProduct.getEditText());

                        BaseActivity.disableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.disableView(binding.tnlConsignorGstNumber.getEditText());

                        break;

                    case CUSTOMER_TYPE_RETAIL:

                        binding.rbTypeRetail.setChecked(true);

                        BaseActivity.visibleView(binding.rvContainerPaymentType);
                        BaseActivity.hideView(binding.rvContainerConsignorDetails);
                        BaseActivity.visibleView(binding.tnlConsignor);
                        BaseActivity.hideView(binding.rvContainerVerticalDetails);
                        BaseActivity.enableView(binding.spinnerPackingType);
                        BaseActivity.enableView(binding.tnlProduct.getEditText());

                        BaseActivity.enableView(binding.tnlConsignorAddress.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorPostcode.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorMobileNumber.getEditText());
                        BaseActivity.enableView(binding.tnlConsignorGstNumber.getEditText());

                        break;
                }

                dispatchMode = getStringValue(docketDetail.getDispatchMode());
                paymentType = getStringValue(docketDetail.getPaymentType());
                riskType = getStringValue(docketDetail.getRiskType());

                switch (dispatchMode) {

                    case MODE_TYPE_SURFACE:

                        binding.rbModeSurface.setChecked(true);
                        break;

                    case MODE_TYPE_AIR:

                        binding.rbModeAir.setChecked(true);
                        break;
                }

                switch (paymentType) {

                    case PAYMENT_TYPE_PAID:

                        binding.rbTypePaid.setChecked(true);
                        break;

                    case PAYMENT_TYPE_TO_PAY:

                        binding.rbTypeToPay.setChecked(true);
                        break;
                }

                switch (riskType) {

                    case RISK_TYPE_OWNER:

                        binding.rbTypeOwner.setChecked(true);
                        break;

                    case RISK_TYPE_CARRIER:

                        binding.rbTypeCarrier.setChecked(true);
                        break;
                }

                Objects.requireNonNull(binding.tnlDocketNo.getEditText()).setText(getStringValue(docketDetail.getDocketNo()));
                Objects.requireNonNull(binding.tnlBookingDate.getEditText()).setText(convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getBookingDate())));
                Objects.requireNonNull(binding.tnlOrigin.getEditText()).setText(getStringValue(docketDetail.getBookingBranchName()));
                Objects.requireNonNull(binding.tnlPostcodeType.getEditText()).setText(getStringValue(docketDetail.getDestinationPostCodeType()));
                Objects.requireNonNull(binding.tnlEstimatedDeliveryDate.getEditText()).setText(convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getEstimatedDeliveryDate())));
                Objects.requireNonNull(binding.tnlActualDeliveryDate.getEditText()).setText(convertDateFormat(AppConstant.API_DATE_FORMAT, AppConstant.CALENDAR_DATE_FORMAT, getStringValue(docketDetail.getActualDeliveryDate())));
                Objects.requireNonNull(binding.tnlConsignor.getEditText()).setText(getStringValue(docketDetail.getConsignorName()));
                Objects.requireNonNull(binding.tnlConsignorAddress.getEditText()).setText(getStringValue(docketDetail.getConsignorAddress()));
                Objects.requireNonNull(binding.tnlConsignorPostcode.getEditText()).setText(getStringValue(docketDetail.getConsignorPostCode()));
                Objects.requireNonNull(binding.tnlConsignorMobileNumber.getEditText()).setText(getStringValue(docketDetail.getConsignorMobileNo()));
                Objects.requireNonNull(binding.tnlConsignorGstNumber.getEditText()).setText(getStringValue(docketDetail.getConsignorGSTNo()));
                Objects.requireNonNull(binding.tnlConsignee.getEditText()).setText(getStringValue(docketDetail.getConsigneeName()));
                Objects.requireNonNull(binding.tnlConsigneeAddress.getEditText()).setText(getStringValue(docketDetail.getConsigneeAddress()));
                Objects.requireNonNull(binding.tnlConsigneePostcode.getEditText()).setText(getStringValue(docketDetail.getConsigneePostCode()));
                Objects.requireNonNull(binding.tnlConsigneeMobileNumber.getEditText()).setText(getStringValue(docketDetail.getConsigneeMobileNo()));
                Objects.requireNonNull(binding.tnlConsigneeGstNumber.getEditText()).setText(getStringValue(docketDetail.getConsigneeGSTNo()));
                Objects.requireNonNull(binding.tnlInvoiceNumber.getEditText()).setText(getStringValue(docketDetail.getInvoiceNo()));
                Objects.requireNonNull(binding.tnlInvoiceAmount.getEditText()).setText(getStringValue(docketDetail.getInvoiceValue()));
                Objects.requireNonNull(binding.tnlPoNumber.getEditText()).setText(getStringValue(docketDetail.getpONo()));
                Objects.requireNonNull(binding.tnlEwayBillNumber.getEditText()).setText(getStringValue(docketDetail.getEwayBillNo()));
                Objects.requireNonNull(binding.tnlProduct.getEditText()).setText(getStringValue(docketDetail.getProductName()));
                binding.tvTotalNoOfBoxes.setText(getStringValue(docketDetail.getNoOfPackages()));
                binding.tvActualWeight.setText(getStringValue(docketDetail.getActualWeight()));
                binding.tvVolumetricWeight.setText(getStringValue(docketDetail.getVolumetricWeight()));
                binding.tvChargeableWeight.setText(getStringValue(docketDetail.getChargeWeight()));
                binding.tvChargeablePercentageWeight.setText(getStringValue(docketDetail.getChargeWeightPercentage()));

                // set dimension list
                dimensionList = docketDetail.getListDocketDimension();

                if (dimensionList != null && dimensionList.size() > 0) {

                    dimensionAdapter = new DocketDimensionAdapter(dimensionList, DocketDetailFragment.this, isReadOnly, fmCreated);
                    binding.recyclerViewDimensionDetails.setAdapter(dimensionAdapter);
                }

                ((BaseActivity) Objects.requireNonNull(getActivity())).getSpinnerList(AppConstant.SP_DELIVERY_TYPE, null, binding.spinnerDeliveryType);

                // set spinner position
                ((BaseActivity) Objects.requireNonNull(getActivity())).bindSpinnerData(AppConstant.SP_DELIVERY_TYPE, binding.spinnerDeliveryType, deliveryTypeMasterList, deliveryTypeId);

                // call method to get consignor list
                getConsignorList();

                // call method to get verticle list
                getVerticleList();

                // call method to get packing type list
                getPackingTypeList();

                // call method to get state list
                getStateList();

                // hide view if it is read only mode
                if (isReadOnly) {

                    BaseActivity.disableMultipleViews(binding.mainContainer);
                    BaseActivity.hideView(binding.btnAddDimension);
                    BaseActivity.hideView(binding.btnCalculate);
                    BaseActivity.hideView(binding.btnSave);
                }

                // set docket charges
                docketCalculation = new DocketCalculation();
                docketCalculation.setBasicChargeAmount(docketDetail.getBasicChargeAmount());
                docketCalculation.setValueSurchargeAmount(docketDetail.getValueSurchargeAmount());
                docketCalculation.setDocketChargeAmount(docketDetail.getDocketChargeAmount());
                docketCalculation.setGreenChargeAmount(docketDetail.getGreenChargeAmount());
                docketCalculation.setDeliveryChargeAmount(docketDetail.getDeliveryChargeAmount());
                docketCalculation.setOdaChargesAmount(docketDetail.getoDAChargesAmount());
                docketCalculation.setToPayChargesAmount(docketDetail.getToPayChargesAmount());
                docketCalculation.setPkgHandlingChargeAmount(docketDetail.getPkgHandlingChargeAmount());
                docketCalculation.setHardCopyChargeAmount(docketDetail.getHardCopyChargeAmount());
                docketCalculation.setSubTotalAmount(docketDetail.getSubTotalAmount());
                docketCalculation.setSurchargeAmount(docketDetail.getSurchargeAmount());
                docketCalculation.setTotalFreightAmount(docketDetail.getTotalFreightAmount());
                docketCalculation.setSgstPercentage(docketDetail.getsGSTPercentage());
                docketCalculation.setSgstAmount(docketDetail.getsGSTAmount());
                docketCalculation.setCgstPercentage(docketDetail.getcGSTPercentage());
                docketCalculation.setCgstAmount(docketDetail.getcGSTAmount());
                docketCalculation.setIgstPercentage(docketDetail.getiGSTPercentage());
                docketCalculation.setIgstAmount(docketDetail.getiGSTAmount());
                docketCalculation.setTotalGstAmount(docketDetail.getTotalGSTAmount());
                docketCalculation.setNetAmount(docketDetail.getNetAmount());

                // call method from other fragment to set docket charges
                DocketChargesFragment.setDocketCharges(docketCalculation);
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to check docket form validations
     *
     *
     * */
    private void checkFormValidation() {

        try {

            if (AppValidation.getInstance().validateLength(getActivity(), binding.tnlDocketNo, "Enter Docket No.", "Invalid Docket No.", 9, 9, false)) {

                if (stateId > 0) {

                    if (cityId > 0) {

                        if (postcodeId > 0) {

                            if (customerType.equalsIgnoreCase(CUSTOMER_TYPE_CREDIT)) {

                                if (consignorId > 0) {

                                    if (AppValidation.getInstance().validateString(getActivity(), binding.tnlConsignee, "Enter Consignee Name") &&
                                            AppValidation.getInstance().validateString(getActivity(), binding.tnlConsigneeAddress, "Enter Consignee Address") &&
                                            AppValidation.getInstance().validateLength(getActivity(), binding.tnlConsigneePostcode, "Enter Consignee Postcode", "Invalid Postcode", 6, 6, false) &&
                                            AppValidation.getInstance().validateMobile(getActivity(), binding.tnlConsigneeMobileNumber, "Invalid Mobile No.") &&
                                            AppValidation.getInstance().validatePattern(getActivity(), binding.tnlConsigneeGstNumber, AppConstant.PATTERN_GST, "Enter Consignee GST No.", "Invalid GST No.", true)) {

                                        if (dimensionList.size() > 0) {

                                            // call method to calculate docket charges
                                            calculateDocketCharges();

                                        } else {
                                            displayShortToast(getActivity(), "Enter at least one dimension details");
                                        }
                                    }

                                } else {
                                    displayShortToast(getActivity(), "Select Consignor");
                                }

                            } else if (customerType.equalsIgnoreCase(CUSTOMER_TYPE_RETAIL)) {

                                if (AppValidation.getInstance().validateString(getActivity(), binding.tnlConsignor, "Enter Consignor Name") &&
                                        AppValidation.getInstance().validateString(getActivity(), binding.tnlConsignorAddress, "Enter Consignor Address") &&
                                        AppValidation.getInstance().validateLength(getActivity(), binding.tnlConsignorPostcode, "Enter Consignor Postcode", "Invalid Postcode", 6, 6, false) &&
                                        AppValidation.getInstance().validateMobile(getActivity(), binding.tnlConsignorMobileNumber, "Invalid Mobile No.") &&
                                        AppValidation.getInstance().validatePattern(getActivity(), binding.tnlConsignorGstNumber, AppConstant.PATTERN_GST, "Enter Consignor GST No.", "Invalid GST No.", true) &&
                                        AppValidation.getInstance().validateString(getActivity(), binding.tnlConsignee, "Enter Consignee Name") &&
                                        AppValidation.getInstance().validateString(getActivity(), binding.tnlConsigneeAddress, "Enter Consignee Address") &&
                                        AppValidation.getInstance().validateLength(getActivity(), binding.tnlConsigneePostcode, "Enter Consignee Postcode", "Invalid Postcode", 6, 6, false) &&
                                        AppValidation.getInstance().validateMobile(getActivity(), binding.tnlConsigneeMobileNumber, "Invalid Mobile No.") &&
                                        AppValidation.getInstance().validatePattern(getActivity(), binding.tnlConsigneeGstNumber, AppConstant.PATTERN_GST, "Enter Consignee GST No.", "Invalid GST No.", true) &&
                                        AppValidation.getInstance().validateString(getActivity(), binding.tnlProduct, "Enter Product")) {

                                    if (dimensionList.size() > 0) {

                                        // call method to calculate docket charges
                                        calculateDocketCharges();

                                    } else {
                                        displayShortToast(getActivity(), "Enter at least one dimension details");
                                    }
                                }
                            }

                        } else {
                            displayShortToast(getActivity(), "Select Postcode");
                        }

                    } else {
                        displayShortToast(getActivity(), "Select Destination City");
                    }

                } else {
                    displayShortToast(getActivity(), "Select Destination State");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // region calculate docket charges request
    /*
     * Method to calculate docket charges based on data
     *
     *
     * */
    private void calculateDocketCharges() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.DocketCalculationRequest docketCalculationRequest = new DocketRequestModel().new DocketCalculationRequest();

                docketCalculationRequest.setId("");
                docketCalculationRequest.setCompanyId(prefUserModel.getCompanyId());
                docketCalculationRequest.setCustomerType(customerType);
                docketCalculationRequest.setDispatchMode(dispatchMode);
                docketCalculationRequest.setPrefix("");
                docketCalculationRequest.setSuffix("");
                docketCalculationRequest.setAutoNo("");
                docketCalculationRequest.setBarcodeNo("");
                docketCalculationRequest.setDocketNo(getTrimString(binding.tnlDocketNo));
                docketCalculationRequest.setBookingDate(convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, getTrimString(binding.tnlBookingDate)));
                docketCalculationRequest.setPaymentType(paymentType);
                docketCalculationRequest.setBookingBranchId(getStringValue(prefUserModel.getBranchId()));
                docketCalculationRequest.setBookingBranchName(getStringValue(prefUserModel.getBranchName()));
                docketCalculationRequest.setBookingBranchStateId(getStringValue(prefUserModel.getStateId()));  // Logged in Franchise's Hub State Id
                docketCalculationRequest.setBookingBranchGstNo(getStringValue(prefUserModel.getHubGSTNo()));
                docketCalculationRequest.setBookingBranchHubGSTNo(getStringValue(prefUserModel.getHubGSTNo()));
                docketCalculationRequest.setDestinationStateId(String.valueOf(stateId)); // Destination State Id
                docketCalculationRequest.setDestinationStateName(getStringValue(stateName));
                docketCalculationRequest.setDestinationId(String.valueOf(cityId));
                docketCalculationRequest.setDestinationPostCode(getStringValue(postcode));
                docketCalculationRequest.setDestinationPostCodeType(getStringValue(postcodeTypeName));
                docketCalculationRequest.setConsignorId(String.valueOf(consignorId));
                docketCalculationRequest.setConsignorCode(consignorCode);
                docketCalculationRequest.setConsignorName(consignorId > 0 ? consignorName : getTrimString(binding.tnlConsignor));
                docketCalculationRequest.setConsignorAddress(getTrimString(binding.tnlConsignorAddress));
                docketCalculationRequest.setConsignorPostCode(getTrimString(binding.tnlConsignorPostcode));
                docketCalculationRequest.setConsignorMobileNo(getTrimString(binding.tnlConsignorMobileNumber));
                docketCalculationRequest.setConsignorGstNo(getTrimString(binding.tnlConsignorGstNumber));
                docketCalculationRequest.setIsConsignorSez(getStringValue(isSezGst));
                docketCalculationRequest.setrConsignorCode("");
                docketCalculationRequest.setrConsignorName("");
                docketCalculationRequest.setrConsignorAddress("");
                docketCalculationRequest.setrConsignorPostCode("");
                docketCalculationRequest.setrConsignorMobileNo("");
                docketCalculationRequest.setrConsignorGstNo(getTrimString(binding.tnlConsignorGstNumber));
                docketCalculationRequest.setConsigneeCode("");
                docketCalculationRequest.setConsigneeName(getTrimString(binding.tnlConsignee));
                docketCalculationRequest.setConsigneeAddress(getTrimString(binding.tnlConsigneeAddress));
                docketCalculationRequest.setConsigneePostCode(getTrimString(binding.tnlConsigneePostcode));
                docketCalculationRequest.setConsigneeMobileNo(getTrimString(binding.tnlConsigneeMobileNumber));
                docketCalculationRequest.setConsigneeGstNo(getTrimString(binding.tnlConsigneeGstNumber));
                docketCalculationRequest.setInvoiceNo(getTrimString(binding.tnlInvoiceNumber));
                docketCalculationRequest.setInvoiceValue(getTrimString(binding.tnlInvoiceAmount));
                docketCalculationRequest.setPoNo(getTrimString(binding.tnlPoNumber));
                docketCalculationRequest.setEwayBillNo(getTrimString(binding.tnlEwayBillNumber));
                docketCalculationRequest.setRiskType(riskType);
                docketCalculationRequest.setDeliveryType(String.valueOf(deliveryTypeId));
                docketCalculationRequest.setVerticleTypeId(String.valueOf(verticalId));
                docketCalculationRequest.setProductName(getTrimString(binding.tnlProduct));
                docketCalculationRequest.setPackingTypeId(String.valueOf(packingTypeId));
                docketCalculationRequest.setNoOfPackages(String.valueOf(castToDouble(getStringValue(binding.tvTotalNoOfBoxes.getText().toString().trim()))));
                docketCalculationRequest.setActualWeight(String.valueOf(castToDouble(getStringValue(binding.tvActualWeight.getText().toString().trim()))));
                docketCalculationRequest.setChargeWeight(String.valueOf(castToDouble(getStringValue(binding.tvChargeableWeight.getText().toString().trim()))));
                docketCalculationRequest.setVolumetricWeight(String.valueOf(castToDouble(getStringValue(binding.tvVolumetricWeight.getText().toString().trim()))));
                docketCalculationRequest.setChargeWeightPercentage(String.valueOf(castToDouble(getStringValue(binding.tvChargeablePercentageWeight.getText().toString().trim()))));
                docketCalculationRequest.setBasicChargeAmount("");
                docketCalculationRequest.setValueSurchargeAmount("");
                docketCalculationRequest.setGreenChargeAmount("");
                docketCalculationRequest.setDeliveryChargeAmount("");
                docketCalculationRequest.setOdaChargesAmount("");
                docketCalculationRequest.setToPayChargesAmount("");
                docketCalculationRequest.setPkgHandlingChargeAmount("");
                docketCalculationRequest.setSubTotalAmount("");
                docketCalculationRequest.setSurchargeAmount("");
                docketCalculationRequest.setTotalFreightAmount("");
                docketCalculationRequest.setSgstPercentage("");
                docketCalculationRequest.setSgstAmount("");
                docketCalculationRequest.setCgstPercentage("");
                docketCalculationRequest.setCgstAmount("");
                docketCalculationRequest.setIgstPercentage("");
                docketCalculationRequest.setIgstAmount("");
                docketCalculationRequest.setTotalGstAmount("");
                docketCalculationRequest.setNetAmount("");
                docketCalculationRequest.setDocketChargeAmount("");
                docketCalculationRequest.setHardCopyChargeAmount("");
                docketCalculationRequest.setFmId("");
                docketCalculationRequest.setHmId1("");
                docketCalculationRequest.setHmId2("");
                docketCalculationRequest.setHmId3("");
                docketCalculationRequest.setHmId4("");
                docketCalculationRequest.setHmId5("");
                docketCalculationRequest.setDrsId("");
                docketCalculationRequest.setBillId("");
                docketCalculationRequest.setIsDelivered("");
                docketCalculationRequest.setActualDeliveryDate("");
                docketCalculationRequest.setFinalDeliveryDate("");
                docketCalculationRequest.setDocketDimensionDetailId("");
                docketCalculationRequest.setPageMode("");
                docketCalculationRequest.setAddNewRight("");
                docketCalculationRequest.setBoxes("");
                docketCalculationRequest.setLength("");
                docketCalculationRequest.setWidth("");
                docketCalculationRequest.setHeight("");
                docketCalculationRequest.setDimensionActualWeight("");
                docketCalculationRequest.setDimensionVolumetricWeight("");
                docketCalculationRequest.setDimensionTotalActualWeight("");
                docketCalculationRequest.setDocketDimensionDetail("");
                docketCalculationRequest.setBlDocketDimensionDetails("");
                docketCalculationRequest.setFilter("");
                docketCalculationRequest.setFinancialYear("");
                docketCalculationRequest.setSearch("");
                docketCalculationRequest.setRowNo_List("");
                docketCalculationRequest.setId_List("");
                docketCalculationRequest.setDocketNo_List("");
                docketCalculationRequest.setBookingDate_List("");
                docketCalculationRequest.setCustomerType_List("");
                docketCalculationRequest.setDispatchMode_List("");
                docketCalculationRequest.setPaymentType_List("");
                docketCalculationRequest.setDestinationState_List("");
                docketCalculationRequest.setDestination_List("");
                docketCalculationRequest.setConsignorName_List("");
                docketCalculationRequest.setConsigneeName_List("");
                docketCalculationRequest.setNoOfPackages_List("");
                docketCalculationRequest.setActualWeight_List("");
                docketCalculationRequest.setChargeWeight_List("");
                docketCalculationRequest.setNetAmount_List("");
                docketCalculationRequest.setEwayBillNo_List("");
                docketCalculationRequest.setInvoiceValue_List("");
                docketCalculationRequest.setDimensionList(dimensionList);

                String strRequest = new Gson().toJson(docketCalculationRequest);
                printInfoLog(TAG, new Gson().toJson(docketCalculationRequest));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse<DocketCalculation>> call = apiService.calculateDocketCharges(docketCalculationRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_CALCULATE_DOCKET_CHARGES, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    /*
     * Method to set docket charges in second fragment
     *
     *
     * */
    private void setDocketCharges() {

        try {

            ((DocketBookingActivity) Objects.requireNonNull(getActivity())).binding.viewPagerBookingDetails.setCurrentItem(1);

            // call method from other fragment to set docket charges
            DocketChargesFragment.setDocketCharges(docketCalculation);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    // region check duplicate docket number request
    /*
     * Method to check duplicate docket number
     *
     *
     * */
    private void checkDuplicateDocketNo() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.CheckDocketNoRequest checkDocketNoRequest = new DocketRequestModel().new CheckDocketNoRequest();

                checkDocketNoRequest.setCompanyId(prefUserModel.getCompanyId());
                checkDocketNoRequest.setBranchId(prefUserModel.getBranchId());
                checkDocketNoRequest.setDocketNo(getTrimString(binding.tnlDocketNo));

                String strRequest = new Gson().toJson(checkDocketNoRequest);
                printInfoLog(TAG, new Gson().toJson(checkDocketNoRequest));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse> call = apiService.checkDocketNo(checkDocketNoRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_CHECK_DUPLICATE_DOCKET_NO, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion

    // region save new docket request
    /*
     * Method to save/update docket information
     *
     *
     * */
    private void saveNewDocket() {

        try {

            if (NetworkUtils.isConnected(getActivity())) {

                // start progress indicator
                BaseActivity.startProgressDialog(getActivity(), false);

                // prepare request body
                DocketRequestModel.DocketCalculationRequest saveDocketRequest = new DocketRequestModel().new DocketCalculationRequest();

                saveDocketRequest.setId(docketId);
                saveDocketRequest.setCompanyId(prefUserModel.getCompanyId());
                saveDocketRequest.setCustomerType(customerType);
                saveDocketRequest.setDispatchMode(dispatchMode);
                saveDocketRequest.setPrefix(AppConstant.PREFIX_DOCKET_NO);
                saveDocketRequest.setSuffix(AppConstant.SUFFIX_DOCKET_NO);
                saveDocketRequest.setAutoNo(docketAutoNo);
                saveDocketRequest.setBarcodeNo("");
                saveDocketRequest.setDocketNo(getTrimString(binding.tnlDocketNo));
                saveDocketRequest.setBookingDate(convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, getTrimString(binding.tnlBookingDate)));
                saveDocketRequest.setPaymentType(paymentType);
                saveDocketRequest.setBookingBranchId(prefUserModel.getBranchId());
                saveDocketRequest.setBookingBranchName(prefUserModel.getBranchName());
                saveDocketRequest.setBookingBranchStateId(prefUserModel.getStateId());
                saveDocketRequest.setBookingBranchGstNo("");
                saveDocketRequest.setDestinationStateId(String.valueOf(stateId));
                saveDocketRequest.setDestinationStateName(stateName);
                saveDocketRequest.setDestinationId(String.valueOf(cityId));
                saveDocketRequest.setDestinationPostCode(postcode);
                saveDocketRequest.setDestinationPostCodeType(postcodeTypeName);
                saveDocketRequest.setConsignorId(String.valueOf(consignorId));
                saveDocketRequest.setConsignorCode(consignorCode);
                saveDocketRequest.setConsignorName(consignorId > 0 ? consignorName : getTrimString(binding.tnlConsignor));
                saveDocketRequest.setConsignorAddress(getTrimString(binding.tnlConsignorAddress));
                saveDocketRequest.setConsignorPostCode(getTrimString(binding.tnlConsignorPostcode));
                saveDocketRequest.setConsignorMobileNo(getTrimString(binding.tnlConsignorMobileNumber));
                saveDocketRequest.setConsignorGstNo(getTrimString(binding.tnlConsignorGstNumber));
                saveDocketRequest.setIsConsignorSez(isSezGst);
                saveDocketRequest.setrConsignorCode("");
                saveDocketRequest.setrConsignorName("");
                saveDocketRequest.setrConsignorAddress("");
                saveDocketRequest.setrConsignorPostCode("");
                saveDocketRequest.setrConsignorMobileNo("");
                saveDocketRequest.setrConsignorGstNo("");
                saveDocketRequest.setConsigneeCode("");
                saveDocketRequest.setConsigneeName(getTrimString(binding.tnlConsignee));
                saveDocketRequest.setConsigneeAddress(getTrimString(binding.tnlConsigneeAddress));
                saveDocketRequest.setConsigneePostCode(getTrimString(binding.tnlConsigneePostcode));
                saveDocketRequest.setConsigneeMobileNo(getTrimString(binding.tnlConsigneeMobileNumber));
                saveDocketRequest.setConsigneeGstNo(getTrimString(binding.tnlConsigneeGstNumber));
                saveDocketRequest.setInvoiceNo(getTrimString(binding.tnlInvoiceNumber));
                saveDocketRequest.setInvoiceValue(getTrimString(binding.tnlInvoiceAmount));
                saveDocketRequest.setPoNo(getTrimString(binding.tnlPoNumber));
                saveDocketRequest.setEwayBillNo(getTrimString(binding.tnlEwayBillNumber));
                saveDocketRequest.setRiskType(riskType);
                saveDocketRequest.setDeliveryType(String.valueOf(deliveryTypeId));
                saveDocketRequest.setVerticleTypeId(String.valueOf(verticalId));
                saveDocketRequest.setProductName(getTrimString(binding.tnlProduct));
                saveDocketRequest.setPackingTypeId(String.valueOf(packingTypeId));
                saveDocketRequest.setNoOfPackages(String.valueOf(castToInteger(getStringValue(binding.tvTotalNoOfBoxes.getText().toString().trim()))));
                saveDocketRequest.setActualWeight(String.valueOf(castToDouble(getStringValue(binding.tvActualWeight.getText().toString().trim()))));
                saveDocketRequest.setChargeWeight(String.valueOf(castToDouble(getStringValue(binding.tvChargeableWeight.getText().toString().trim()))));
                saveDocketRequest.setVolumetricWeight(String.valueOf(castToDouble(getStringValue(binding.tvVolumetricWeight.getText().toString().trim()))));
                saveDocketRequest.setChargeWeightPercentage(String.valueOf(castToDouble(getStringValue(binding.tvChargeablePercentageWeight.getText().toString().trim()))));
                saveDocketRequest.setBasicChargeAmount(docketCalculation.getBasicChargeAmount());
                saveDocketRequest.setValueSurchargeAmount(docketCalculation.getValueSurchargeAmount());
                saveDocketRequest.setGreenChargeAmount(docketCalculation.getGreenChargeAmount());
                saveDocketRequest.setDeliveryChargeAmount(docketCalculation.getDeliveryChargeAmount());
                saveDocketRequest.setOdaChargesAmount(docketCalculation.getOdaChargesAmount());
                saveDocketRequest.setToPayChargesAmount(docketCalculation.getToPayChargesAmount());
                saveDocketRequest.setPkgHandlingChargeAmount(docketCalculation.getPkgHandlingChargeAmount());
                saveDocketRequest.setSubTotalAmount(docketCalculation.getSubTotalAmount());
                saveDocketRequest.setSurchargeAmount(docketCalculation.getSurchargeAmount());
                saveDocketRequest.setTotalFreightAmount(docketCalculation.getTotalFreightAmount());
                saveDocketRequest.setSgstPercentage(docketCalculation.getSgstPercentage());
                saveDocketRequest.setSgstAmount(docketCalculation.getSgstAmount());
                saveDocketRequest.setCgstPercentage(docketCalculation.getCgstPercentage());
                saveDocketRequest.setCgstAmount(docketCalculation.getCgstAmount());
                saveDocketRequest.setIgstPercentage(docketCalculation.getIgstPercentage());
                saveDocketRequest.setIgstAmount(docketCalculation.getIgstAmount());
                saveDocketRequest.setTotalGstAmount(docketCalculation.getTotalGstAmount());
                saveDocketRequest.setNetAmount(docketCalculation.getNetAmount());
                saveDocketRequest.setDocketChargeAmount(docketCalculation.getDocketChargeAmount());
                saveDocketRequest.setHardCopyChargeAmount(docketCalculation.getHardCopyChargeAmount());
                saveDocketRequest.setIsActive(AppConstant.STATUS_ACTIVE);
                saveDocketRequest.setIsDelete(AppConstant.STATUS_DELETE);
                saveDocketRequest.setLastModifyDate(getCurrentDate(AppConstant.API_DATE_FORMAT));
                saveDocketRequest.setLastModifyBy(prefUserModel.getId());
                saveDocketRequest.setIsFrom(AppConstant.STATUS_IS_FROM);
                saveDocketRequest.setDimensionList(dimensionList);

                if (!isEditable) {

                    saveDocketRequest.setFmId("");
                    saveDocketRequest.setHmId1("");
                    saveDocketRequest.setHmId2("");
                    saveDocketRequest.setHmId3("");
                    saveDocketRequest.setHmId4("");
                    saveDocketRequest.setHmId5("");
                    saveDocketRequest.setDrsId("");
                    saveDocketRequest.setBillId("0");
                    saveDocketRequest.setIsDelivered("0");
                    saveDocketRequest.setActualDeliveryDate(convertDateFormat(AppConstant.CALENDAR_DATE_FORMAT, AppConstant.YYYY_MM_DD, getTrimString(binding.tnlEstimatedDeliveryDate)));
                    saveDocketRequest.setFinalDeliveryDate("");
                    saveDocketRequest.setDocketDimensionDetailId("");
                    saveDocketRequest.setPageMode("");
                    saveDocketRequest.setAddNewRight("");
                    saveDocketRequest.setBoxes("");
                    saveDocketRequest.setLength("");
                    saveDocketRequest.setWidth("");
                    saveDocketRequest.setHeight("");
                    saveDocketRequest.setDimensionActualWeight("");
                    saveDocketRequest.setDimensionVolumetricWeight("");
                    saveDocketRequest.setDimensionTotalActualWeight("");
                    saveDocketRequest.setDocketDimensionDetail("");
                    saveDocketRequest.setBlDocketDimensionDetails("");
                    saveDocketRequest.setFilter("");
                    saveDocketRequest.setFinancialYear("");
                    saveDocketRequest.setSearch("");
                    saveDocketRequest.setRowNo_List("");
                    saveDocketRequest.setId_List("");
                    saveDocketRequest.setDocketNo_List("");
                    saveDocketRequest.setBookingDate_List("");
                    saveDocketRequest.setCustomerType_List("");
                    saveDocketRequest.setDispatchMode_List("");
                    saveDocketRequest.setPaymentType_List("");
                    saveDocketRequest.setDestinationState_List("");
                    saveDocketRequest.setDestination_List("");
                    saveDocketRequest.setConsignorName_List("");
                    saveDocketRequest.setConsigneeName_List("");
                    saveDocketRequest.setNoOfPackages_List("");
                    saveDocketRequest.setActualWeight_List("");
                    saveDocketRequest.setChargeWeight_List("");
                    saveDocketRequest.setNetAmount_List("");
                    saveDocketRequest.setEwayBillNo_List("");
                    saveDocketRequest.setInvoiceValue_List("");
                    saveDocketRequest.setSid(prefUserModel.getSid());
                    saveDocketRequest.setCid(prefUserModel.getCompanyId());
                    saveDocketRequest.setBid(prefUserModel.getBranchId());
                    saveDocketRequest.setUid(prefUserModel.getId());
                    saveDocketRequest.setEntryDate(getCurrentDate(AppConstant.API_DATE_FORMAT));
                    saveDocketRequest.setIsDefault("");
                    saveDocketRequest.setIsEnable("");
                    saveDocketRequest.setStatus("");
                    saveDocketRequest.setIsSync("");
                    saveDocketRequest.setWildSearch("");
                    saveDocketRequest.setNotes("");
                    saveDocketRequest.setExtraInt1("");
                    saveDocketRequest.setExtraInt2("");
                    saveDocketRequest.setExtraVarChar1("");
                    saveDocketRequest.setExtraVarChar2("");
                    saveDocketRequest.setExtraDecimal1("");
                    saveDocketRequest.setExtraDecimal2("");
                    saveDocketRequest.setExtraDateTime1("");
                    saveDocketRequest.setExtraDateTime2("");
                    saveDocketRequest.setExtraBit1("");
                    saveDocketRequest.setExtraBit2("");
                }

                printInfoLog(TAG, new Gson().toJson(saveDocketRequest));

                ApiService apiService = ApiClient.createService(ApiService.class, "", "");
                Call<CommonResponse> call = apiService.docketOperation(saveDocketRequest);

                // call API
                ApiManager.callRetrofit(call, ApiConstant.API_DOCKET_OPERATION, this, false);

            } else {
                displayInternetToastMessage(getActivity());
            }

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
    // endregion
}