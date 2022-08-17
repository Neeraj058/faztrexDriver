package com.courier365cloud.faztrex.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseFragment;
import com.courier365cloud.faztrex.databinding.FragmentSliderBinding;

import java.util.Objects;

public class SliderFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();

    private FragmentSliderBinding fragmentSliderBinding;

    private SliderPagerAdapter sliderPagerAdapter;

    private int[] layoutCollection;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentSliderBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = fragmentSliderBinding.getRoot();

        layoutCollection = new int[]{
                R.layout.slider_1,
                R.layout.slider_2,
                R.layout.slider_3,
                R.layout.slider_1,
                R.layout.slider_2,
                R.layout.slider_3
        };

        setUpSlider();

        // Making notification bar transparent
        /*if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }*/

        return view;
    }

    /*
     * Method to setup slider
     *
     * */
    private void setUpSlider() {

        sliderPagerAdapter = new SliderPagerAdapter();

        fragmentSliderBinding.viewpagerSlider.setEnabledSwipe(true);
        fragmentSliderBinding.viewpagerSlider.setAdapter(sliderPagerAdapter);
        fragmentSliderBinding.viewpagerSlider.addOnPageChangeListener(viewPagerPageChangeListener);

        fragmentSliderBinding.viewPagerIndicator.setViewPager(fragmentSliderBinding.viewpagerSlider);

        sliderPagerAdapter.registerDataSetObserver(fragmentSliderBinding.viewPagerIndicator.getDataSetObserver());

        /*if (!preferenceManager.FirstLaunch()) {
            launchMain();
            finish();
        }*/
    }

    public void nextSlider() {

        int i = getItem();

        if (i < layoutCollection.length) {

            fragmentSliderBinding.viewpagerSlider.setCurrentItem(i);

        } else {

            //launchMain();
        }
    }

    /*public void skip(View view) {
        launchMain();
    }*/

    private int getItem() {
        return fragmentSliderBinding.viewpagerSlider.getCurrentItem() + 1;
    }

    /*private void launchMain() {
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(MainScreen.this, MainActivity.class));
        finish();
    }*/

    private ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            if (position == layoutCollection.length - 1) {

                fragmentSliderBinding.btnNext.setVisibility(View.GONE);
                fragmentSliderBinding.btnGetStarted.setVisibility(View.VISIBLE);

            } else {

                fragmentSliderBinding.btnNext.setVisibility(View.VISIBLE);
                fragmentSliderBinding.btnGetStarted.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class SliderPagerAdapter extends PagerAdapter {

        private LayoutInflater inflater;

        SliderPagerAdapter() {

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(layoutCollection[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layoutCollection.length;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View v = (View) object;
            container.removeView(v);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}