package org.tsofen.ourstory.web;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebFactory {
    static final String BASE_URL = "https://ourstoryserver.herokuapp.com/";

    public static OurStoryService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(OurStoryService.class);
    }
}
