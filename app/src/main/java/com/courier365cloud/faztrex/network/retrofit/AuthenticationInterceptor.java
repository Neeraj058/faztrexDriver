package com.courier365cloud.faztrex.network.retrofit;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;

public class AuthenticationInterceptor implements Interceptor {

    private String authToken;

    AuthenticationInterceptor(String token) {
        this.authToken = token;
    }

    @NonNull
    public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {

        Request original = chain.request();

        Request.Builder builder = original.newBuilder().header("Authorization", authToken);

        Request request = builder.build();

        return chain.proceed(request);
    }
}
