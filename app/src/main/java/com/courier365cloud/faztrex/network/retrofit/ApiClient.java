package com.courier365cloud.faztrex.network.retrofit;

import static com.courier365cloud.faztrex.utils.AppUtils.isEmptyString;

import com.courier365cloud.faztrex.BuildConfig;
import com.courier365cloud.faztrex.utils.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .callTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES);

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(AppConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String userName, String password) {

        if (!isEmptyString(userName) && !isEmptyString(password)) {

            String authToken = Credentials.basic(userName, password);
            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        else logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        if (!isEmptyString(authToken)) {

            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                //builder.client(httpClient.build());
            }
//            retrofit = builder.build();
        }
        httpClient.addInterceptor(logging);
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceWithoutLogging(Class<S> serviceClass, final String authToken) {
        if (!isEmptyString(authToken)) {
            AuthenticationInterceptor interceptor = new AuthenticationInterceptor(authToken);
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);
                builder.client(httpClient.build());
            }
            retrofit = builder.build();
        }
      /*  builder.client(httpClient.build());
        retrofit = builder.build();*/
        return retrofit.create(serviceClass);
    }
}