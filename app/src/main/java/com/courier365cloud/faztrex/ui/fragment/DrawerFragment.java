package com.courier365cloud.faztrex.ui.fragment;

import static com.courier365cloud.faztrex.utils.AppUtils.convertToUpperCase;
import static com.courier365cloud.faztrex.utils.AppUtils.getStringValue;
import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.adapter.NavigationItemExpandableAdapter;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.baseclass.BaseFragment;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.FragmentDrawerBinding;
import com.courier365cloud.faztrex.model.NavigationItem;
import com.courier365cloud.faztrex.model.NavigationSubItem;
import com.courier365cloud.faztrex.ui.activity.HomeActivity;
import com.courier365cloud.faztrex.ui.activity.transaction.PostcodeTrackingActivity;
import com.courier365cloud.faztrex.ui.activity.transaction.docket.DocketListActivity;
import com.courier365cloud.faztrex.ui.activity.transaction.docket.DocketTrackingActivity;
import com.courier365cloud.faztrex.ui.activity.transaction.driver_docket.DriverDocketListActivity;
import com.courier365cloud.faztrex.ui.activity.transaction.drs.DrsActivity;
import com.courier365cloud.faztrex.ui.activity.transaction.hyperlocal.HyperLocalListActivity;
import com.courier365cloud.faztrex.ui.activity.user.ChangePasswordActivity;
import com.courier365cloud.faztrex.ui.activity.user.LoginActivity;
import com.courier365cloud.faztrex.utils.AppConstant;
import com.courier365cloud.faztrex.utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends BaseFragment implements
        NavigationItemExpandableAdapter.OnNavigationMenuItemClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private FragmentDrawerBinding binding;

    private LinearLayoutManager mLayoutManager;

    private NavigationItemExpandableAdapter navigationItemExpandableAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drawer, container, false);

        requireActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setHasOptionsMenu(true);

        doYourWork();

        return binding.getRoot();
    }

    private void doYourWork() {

        // get preference data
        getPreferenceData();

        mLayoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerViewNavigationItem.setLayoutManager(mLayoutManager);
        binding.recyclerViewNavigationItem.setItemAnimator(new DefaultItemAnimator());

        navigationItemExpandableAdapter = new NavigationItemExpandableAdapter(getActivity(), getNavigationMenuItemList(), DrawerFragment.this);
        binding.recyclerViewNavigationItem.setAdapter(navigationItemExpandableAdapter);

        binding.rvContentChangePassword.setOnClickListener(v -> startActivity(new Intent(getActivity(), ChangePasswordActivity.class)));

        binding.rvContentLogout.setOnClickListener(v -> displayConfirmationDialog());

        // set profile details
        setProfileDetails();
    }

    /*
     * Method to prepare navigation menu list
     *
     *
     * */
    @SuppressLint("UseCompatLoadingForDrawables")
    private List<NavigationItem> getNavigationMenuItemList() {

        List<NavigationItem> itemList = new ArrayList<>();
        List<NavigationSubItem> subItemList;

        /*subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_sales), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_sales), true));
        subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_hub), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_hub), true));
        subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_franchisee), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_franchisee), true));
        subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_billing_account), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_billing_account), true));*/

        //itemList.add(new NavigationItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_dashboard), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_dashboard), true, subItemList));

        subItemList = new ArrayList<>();

//        if (((BaseActivity) requireActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_DOCKET_BOOKING), AppConstant.R_VIEW))
//            subItemList.add(new NavigationSubItem(requireActivity().getResources().getString(R.string.nav_menu_title_docket_booking), requireActivity().getResources().getDrawable(R.drawable.ic_calendar), true));

        /*if (((BaseActivity) getActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_FRANCHISEE_MANIFEST), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_franchisee_manifest), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_franchisee_manifest), true));*/

        /*if (((BaseActivity) getActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_MANIFEST_INWARD_HUB), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_manifest_inward_hub), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_manifest_inward), true));*/

        /*if (((BaseActivity) getActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_HUB_MANIFEST), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_manifest_outward_hub), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_manifest_outward), true));*/

        /*if (((BaseActivity) getActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_LORRY_HIRE), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_lorry_hire), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_lorry_hire), true));*/

        subItemList.add(new NavigationSubItem(AppConstant.NAV_TITLE_DOCKET, requireActivity().getResources().getDrawable(R.drawable.ic_calendar), true));

        if (((BaseActivity) requireActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_DRS), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(AppConstant.NAV_TITLE_DRS, requireActivity().getResources().getDrawable(R.drawable.ic_delivery), true));

        /*if (((BaseActivity) getActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_BILL), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_bill), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_billing_account), true));*/

        /*if (((BaseActivity) getActivity()).checkAccessRights(((BaseActivity) getActivity()).getAccessRights(AppConstant.PAGE_MONEY_RECEIPT), AppConstant.R_VIEW))
            subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_money_receipt), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_lorry_hire), true));*/

        //subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_docket_tracking), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_manifest_outward), true));

        //subItemList.add(new NavigationSubItem(Objects.requireNonNull(getActivity()).getResources().getString(R.string.nav_menu_title_postcode_tracking), Objects.requireNonNull(getActivity()).getResources().getDrawable(R.drawable.ic_pin), true));

        subItemList.add(new NavigationSubItem(AppConstant.NAV_TITLE_HYPER_LOCAL_REQUEST, requireActivity().getResources().getDrawable(R.drawable.ic_delivery), true));

        itemList.add(new NavigationItem(requireActivity().getResources().getString(R.string.nav_menu_title_transactions), requireActivity().getResources().getDrawable(R.drawable.ic_list), true, subItemList));

        return itemList;
    }

    @Override
    public void onChildMenuClick(int parentPosition, int childPosition, NavigationSubItem navigationSubItem) {

        new Handler().postDelayed(() -> {
            // call method to close the drawer
            ((HomeActivity) requireActivity()).closeDrawer();
        }, 300);

        switch (navigationSubItem.getSubMenuName()) {

            case AppConstant.NAV_TITLE_DOCKET_BOOKING:
                startActivity(new Intent(getActivity(), DocketListActivity.class));
                break;

            case AppConstant.NAV_TITLE_DRS:
                startActivity(new Intent(getActivity(), DrsActivity.class));
                break;

            case AppConstant.NAV_TITLE_DOCKET_TRACKING:
                startActivity(new Intent(getActivity(), DocketTrackingActivity.class));
                break;

            case AppConstant.NAV_TITLE_POSTCODE_TRACKING:
                startActivity(new Intent(getActivity(), PostcodeTrackingActivity.class));
                break;

            case AppConstant.NAV_TITLE_HYPER_LOCAL_REQUEST:
                startActivity(new Intent(getActivity(), HyperLocalListActivity.class));
                break;

            case AppConstant.NAV_TITLE_DOCKET:
                startActivity(new Intent(getActivity(), DriverDocketListActivity.class));
                break;
        }
    }

    /*
     * Method to display confirmation dialog
     *
     *
     * */
    private void displayConfirmationDialog() {

        simpleAlertDialog = new SimpleAlertDialog(getActivity()) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return requireActivity().getResources().getString(R.string.dialog_title_sign_out);
            }

            @Override
            public String setDialogMessage() {
                return requireActivity().getResources().getString(R.string.dialog_msg_sign_out);
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public Drawable setDialogIcon() {
                return requireActivity().getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return requireActivity().getResources().getString(R.string.btn_title_proceed);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    AppPreference.getInstance().clearPreferences(requireActivity());

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    getActivity().finish();
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return requireActivity().getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {

                return (dialog, which) -> {

                    dialog.dismiss();

                    new Handler().postDelayed(() -> {

                        // call method to close the drawer
                        ((HomeActivity) requireActivity()).closeDrawer();

                    }, 300);
                };
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

    /*
     * Method to set profile details in side menu drawer
     *
     *
     * */
    private void setProfileDetails() {

        try {

            String displayName = convertToUpperCase(getStringValue(prefUserModel.getFirstName()) + " " + getStringValue(prefUserModel.getLastName())).trim();
            String hubCode = getStringValue(prefUserModel.getHubCode()).toUpperCase();
            String branchName = getStringValue(prefUserModel.getBranchName()).toUpperCase();

            String locationDetails = hubCode + (!isEmptyString(branchName) ? "  |  " + branchName : "");

            binding.tvDisplayName.setText(displayName);
            binding.tvHubDetails.setText(locationDetails);

            Bitmap placeholder = BitmapFactory.decodeResource(requireActivity().getResources(), R.drawable.ic_default_avatar);
            RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), placeholder);

            Glide.with(requireActivity())
                    .load(prefUserModel.getProfilePic())
                    .apply(RequestOptions.bitmapTransform(
                            new CircleCrop()).placeholder(circularBitmapDrawable))
                    .into(binding.ivUserProfile);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}