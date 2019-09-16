package org.tsofen.ourstory.web;


import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.MemoryA;
import org.tsofen.ourstory.model.api.Owner;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OurStoryService {
    @FormUrlEncoded
    @Headers("Content-Type: application/json")
    @POST("api/comments")
    Call<Comment> newComment(@Body Comment comment);
    @GET("memories/getUserMemories/{id}")
    Call<ArrayList<MemoryA>> GetMemoriesByUser(@Path("id") long id);
    @GET("memories/story/{story}/findMemoriesByTag/{tag}")
    Call<ArrayList<MemoryA>> GetMemoriesByTag(@Path("story") long id, @Path("tag") String tag);
    @GET("memories/story/{story}/findMemoriesByYear/{year}")
    Call<ArrayList<MemoryA>> GetMemoriesByYear(@Path("story") long story,@Path("year") int year);
    @GET("comments/findById/{id}")
    Call<ArrayList<CommentA>> GetCommentbyId(@Path("id") long id);
    @Headers({"Content-Type: application/json"})
    @DELETE("memories/delete/{id}")
    Call<Memory> DeleteMemory(@Path("id") long id);
    @POST("stories/create")
    Call<Story> CreateStory(@Body Story story);
    @GET("users/findById/{id}")
    Call<Owner> GetUserById(@Path("id") long id);
    @GET("stories/findStoriesByKeyword/")
    Call<ArrayList<ListOfStory>> GetStoriesByName(@Query("name") String n);

    @POST("memories/setMediaToMemory/{id}")
    Call<Memory> SetMediaToMemory(@Path("id") long id, @Body HashMap<String, List<String>> hm);

    @Headers({"Content-Type: application/json"})
    @POST("memories/create")
    Call<Memory> CreateMemory(@Body Memory memory);

    @GET("memories/findById/{id}")
    Call<Memory> GetMemoryById(@Path("id") long id);

    @PUT("memories/update/{id}")
    Call<Memory> EditMemory(@Path("id") long id, @Body Memory memory);

    @GET("memories/findMemoriesByKeyword/{description}")
    Call<ArrayList<MemoryA>> GetMemoriesByKeyword( @Path("description") String description);

    @GET("users/findByEmail/{email}")
    Call<User> GetUserByEmail( @Path("email") String email);


    @GET("stories/findStoriesByDobFull")
    Call<ArrayList<ListOfStory>> GetStoriesByDobFull (@Query("d") int day ,@Query("m") int month , @Query("y") int year);

    @GET("stories/findStoriesByDodFull")
    Call<ArrayList<ListOfStory>> GetStoriesByDodFull (@Query("d") int day ,@Query("m") int month , @Query("y") int year);


    @GET("stories/findStoriesByDobYearMonth")
    Call<ArrayList<ListOfStory>> GetStoriesByDobYearMonth (@Query("m") int month, @Query("y") int year );
    @GET("stories/findStoriesByDobYear")
    Call<ArrayList<Story>> GetStoriesByDobYear (@Query("y") int year);
    @GET("stories/findStoriesByDateOfBirth")
    Call<ArrayList<ListOfStory>> GetStoriesByDateOfBirth (@Query("d") int day, @Query("m") int month , @Query("y") int year , @Query("name") String name_of_person);
    @GET("stories/findStoriesByDateOfDeath")
    Call<ArrayList<ListOfStory>> GetStoriesByDateOfDeath(@Query("d") int day, @Query("m") int month , @Query("y") int year , @Query("name") String name_of_person);


}
