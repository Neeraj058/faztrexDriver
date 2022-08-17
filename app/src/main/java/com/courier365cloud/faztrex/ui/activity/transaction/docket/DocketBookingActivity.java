package com.courier365cloud.faztrex.ui.activity.transaction.docket;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.customviews.SimpleAlertDialog;
import com.courier365cloud.faztrex.databinding.ActivityDocketBookingBinding;
import com.courier365cloud.faztrex.ui.fragment.docket.DocketChargesFragment;
import com.courier365cloud.faztrex.ui.fragment.docket.DocketDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class DocketBookingActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    public ActivityDocketBookingBinding binding;

    private DocketDetailFragment docketDetailFragment;
    private DocketChargesFragment docketChargesFragment;

    private boolean isReadOnly;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docket_booking);

        // initialize toolbar
        initToolbar(binding.toolbarMain, getResources().getString(R.string.nav_header_title_docket_details));

        // call method to start operational flow
        doYourWork();
    }

    private void doYourWork() {

        try {

            // get intent data
            isReadOnly = getIntent().getBooleanExtra(mContext.getResources().getString(R.string.key_read_only), false);

            // get preference data
            getPreferenceData();

            // set view pager swipe
            binding.viewPagerBookingDetails.setEnabledSwipe(false);

            // call method to set up view pager
            setUpViewPager();

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to set up view pager
     *
     * */
    private void setUpViewPager() {

        try {

            TabLayout.Tab tabBookingDetails = binding.tabDocketBooking.newTab().setText(getResources().getString(R.string.tab_title_docket_booking_details));
            TabLayout.Tab tabChargedDetails = binding.tabDocketBooking.newTab().setText(getResources().getString(R.string.tab_title_docket_charges_details));

            binding.tabDocketBooking.addTab(tabBookingDetails, 0);
            binding.tabDocketBooking.addTab(tabChargedDetails, 1);

            binding.tabDocketBooking.setTabTextColors(
                    ContextCompat.getColor(mContext, R.color.colorTextLight),
                    ContextCompat.getColor(mContext, R.color.colorWhite));

            binding.tabDocketBooking.setSelectedTabIndicatorColor(ContextCompat.getColor(mContext, R.color.colorWhite));

            binding.tabDocketBooking.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    switch (tab.getPosition()) {

                        case 0:

                            binding.viewPagerBookingDetails.setCurrentItem(0);
                            break;

                        case 1:

                            binding.viewPagerBookingDetails.setCurrentItem(1);
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


            binding.viewPagerBookingDetails.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabDocketBooking));

            binding.viewPagerBookingDetails.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            docketDetailFragment = new DocketDetailFragment();
            docketChargesFragment = new DocketChargesFragment();

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();

            viewPagerAdapter.addFragment(docketDetailFragment);
            viewPagerAdapter.addFragment(docketChargesFragment);

            binding.viewPagerBookingDetails.setOffscreenPageLimit(2);
            binding.viewPagerBookingDetails.setAdapter(viewPagerAdapter);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onSinglePermissionGranted(String permissionName) {

    }

    @Override
    public void onMultiplePermissionGranted(String[] permissions) {

    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragmentList = new ArrayList<>();

        ViewPagerAdapter() {
            super(getSupportFragmentManager());
        }

        void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {

        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        onBackPressed();

        return true;
    }

    @Override
    public void onBackPressed() {

        if (!isReadOnly) {

            // show prompt dialog
            showPrompt();

        } else {
            super.onBackPressed();
        }
    }

    /*
     * Method to show prompt dialog
     *
     *
     * */
    private void showPrompt() {

        simpleAlertDialog = new SimpleAlertDialog(mContext) {

            @Override
            public boolean setDialogCancelable() {
                return false;
            }

            @Override
            public String setDialogTitle() {
                return "Are you sure you want to go back?";
            }

            @Override
            public String setDialogMessage() {
                return "Be aware, All your data will be lost! Press OK to continue, or Cancel to stay on this screen.";
            }

            @Override
            public Drawable setDialogIcon() {
                return mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            }

            @Override
            public String setDialogPositiveButtonText() {
                return mContext.getResources().getString(R.string.btn_title_ok);
            }

            @Override
            public DialogInterface.OnClickListener onDialogPositiveButtonClick() {

                return (dialog, which) -> {

                    // hide soft keyboard
                    hideSoftKeyboard();

                    dialog.dismiss();
                    finish();
                };
            }

            @Override
            public String setDialogNegativeButtonText() {
                return mContext.getResources().getString(R.string.btn_title_cancel);
            }

            @Override
            public DialogInterface.OnClickListener onDialogNegativeButtonClick() {

                return (dialog, which) -> {

                    // hide soft keyboard
                    hideSoftKeyboard();

                    dialog.dismiss();
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
}
