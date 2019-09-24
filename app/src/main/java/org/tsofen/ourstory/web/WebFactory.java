package org.tsofen.ourstory.web;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

import java.io.IOException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebFactory {
    static final String BASE_URL = "http://ourstoryserver.herokuapp.com/";
    private static final String Auth_user = "OurStoryAuth";
    private static final String Auth_password = "OurStorySecurity2019";
    public static OurStoryService getService() {
        String userName = Auth_user;
        String userPassword = Auth_password;
        String base = userName + ":" +userPassword;
        String authHeader = "basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new OkHttpProfilerInterceptor());
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Authorization", authHeader)
                        .build();
                return chain.proceed(request);
            }
        });
//        builder.addInterceptor(new GzipRequestInterceptor());
//        builder.addInterceptor(new UnzippingInterceptor());
        // We need that in order to allow passing Date objects.
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(builder.build())
                .build();
        return retrofit.create(OurStoryService.class);
    }
}
