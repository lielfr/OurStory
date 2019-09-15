package org.tsofen.ourstory.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebFactory {
    static final String BASE_URL = "http://ourstoryserver.herokuapp.com/";

    public static OurStoryService getService() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new OkHttpProfilerInterceptor());
        // We need that in order to allow passing Date objects.
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build();
        return retrofit.create(OurStoryService.class);
    }
}
