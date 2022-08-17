package com.courier365cloud.faztrex.network.retrofit;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.courier365cloud.faztrex.baseclass.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    public static <T> void callRetrofit(Call<T> call, String endPointName, ApiListener apiListener, boolean isContinue) {

        try {

            call.enqueue(new Callback<T>() {

                @Override
                public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {

                    if (!isContinue)
                        // stop progress dialog
                        BaseActivity.stopProgressDialog();

                    if (response.isSuccessful()) {

                        Log.i(ApiManager.class.getSimpleName(), new Gson().toJson(response.body()));

                        apiListener.onApiSuccess(endPointName, response.body());

                    } else {

                        Log.e(ApiManager.class.getSimpleName(), new Gson().toJson(response.errorBody()));

                        apiListener.onApiError(endPointName, response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {

                    Log.e(ApiManager.class.getSimpleName(), t.getLocalizedMessage());

                    apiListener.onApiFailure(endPointName, t.getLocalizedMessage());

                    // stop progress dialog
                    BaseActivity.stopProgressDialog();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(ApiManager.class.getSimpleName(), e.getLocalizedMessage());
        }
    }
}
