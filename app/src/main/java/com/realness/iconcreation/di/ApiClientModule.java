package com.realness.iconcreation.di;

import com.realness.iconcreation.data.remote.ApiService;
import com.realness.iconcreation.util.Const;


import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class ApiClientModule {

    @Provides
    @Singleton
    public static ApiService provideApiInterface(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    @Provides
    @Singleton
    public static OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("lang", "en")
                    .addHeader("apiKey", Const.API_KEY)
                    .build();
            return chain.proceed(request);
        });
        client.addInterceptor(logging);
        return client.build();
    }

}
