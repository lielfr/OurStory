package org.tsofen.ourstory.web;


import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.Comment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface OurStoryService {
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("api/comments")
    Call<Comment> newComment(@Body Comment comment);
    @GET
    Call<ArrayList<Memory>> GetMemoriesByUser(@Body long UserId);

}
