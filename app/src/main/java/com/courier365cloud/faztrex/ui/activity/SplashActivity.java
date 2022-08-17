package com.courier365cloud.faztrex.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.databinding.DataBindingUtil;

import com.courier365cloud.faztrex.R;
import com.courier365cloud.faztrex.baseclass.BaseActivity;
import com.courier365cloud.faztrex.databinding.ActivitySplashBinding;
import com.courier365cloud.faztrex.ui.activity.user.LoginActivity;
import com.courier365cloud.faztrex.utils.AppPreference;

public class SplashActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = this;

    private ActivitySplashBinding binding;

    private final int SPLASH_TIME_OUT = 2500;

    @Override
    public Activity setCurrentActivity() {
        return this;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        // call method to start animation
        startSplashAnimation();

        // call method to start navigation
        startNavigation();
    }

    /*
     * Method to start animation
     *
     * */
    private void startSplashAnimation() {

        try {

            Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_alpha);
            anim.reset();
            binding.rvMainContainer.clearAnimation();
            binding.rvMainContainer.startAnimation(anim);

            anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_translate);
            anim.reset();
            binding.ivSplashLogo.clearAnimation();
            binding.ivSplashLogo.startAnimation(anim);

            anim.reset();
            binding.tvLoadingText.clearAnimation();
            binding.tvLoadingText.startAnimation(anim);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }

    /*
     * Method to start navigation
     *
     * */
    private void startNavigation() {

        try {

            boolean isLogin = AppPreference.getInstance().getBooleanPreference(mContext, mContext.getResources().getString(R.string.pref_is_login));

            new Handler().postDelayed(() -> {

                if (isLogin) {

                    startActivity(new Intent(mContext, HomeActivity.class));
                    finish();

                } else {

                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                }

            }, SPLASH_TIME_OUT);

        } catch (Exception e) {
            e.printStackTrace();
            printErrorLog(TAG, e.getLocalizedMessage());
        }
    }
}
