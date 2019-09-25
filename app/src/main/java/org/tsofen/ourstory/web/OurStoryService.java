package org.tsofen.ourstory.web;


import org.tsofen.ourstory.model.Comment;
import org.tsofen.ourstory.model.Memory;
import org.tsofen.ourstory.model.Tag;
import org.tsofen.ourstory.model.api.CommentA;
import org.tsofen.ourstory.model.api.FullViewStory;
import org.tsofen.ourstory.model.api.Like;
import org.tsofen.ourstory.model.api.ListOfStory;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
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
    @Headers("Content-Type: application/json")
    @POST("comments/create/{id}")
    Call<Comment> newComment(@Path("id") long id, @Body Comment comment);

 @POST("likes/create/{id}")
 Call<Like> addLike(@Path("id") long id, @Body Like like);
    @GET("memories/getUserMemories/{id}")
    Call<ArrayList<Memory>> GetMemoriesByUser(@Path("id") long id);
    @GET("memories/story/{story}/findMemoriesByTag/{tag}")
    Call<ArrayList<Memory>> GetMemoriesByTag(@Path("story") long id, @Path("tag") String tag);
    @GET("memories/story/{story}/findMemoriesByYear/{year}")
    Call<ArrayList<Memory>> GetMemoriesByYear(@Path("story") long story, @Path("year") int year);
    @GET("comments/findById/{id}")
    Call<ArrayList<CommentA>> GetCommentbyId(@Path("id") long id);
    @Headers({"Content-Type: application/json"})
    @DELETE("memories/delete/{id}")
    Call<Void> DeleteMemory(@Path("id") long id);
    @POST("stories/create")
    Call<Story> CreateStory(@Body Story story);
    @GET("users/findById/{id}")
    Call<User> GetUserById(@Path("id") long id);
    @GET("stories/findStoriesByKeyword/")
    Call<ArrayList<ListOfStory>> GetStoriesByName(@Query("name") String n);

    @POST("memories/setMediaToMemory/{id}")
    Observable<Memory> SetMediaToMemory(@Path("id") long id, @Body HashMap<String, List<String>> hm);

    @Headers({"Content-Type: application/json"})
    @POST("memories/create")
    Observable<Memory> CreateMemory(@Body Memory memory);

    @GET("memories/findById/{id}")
    Call<Memory> GetMemoryById(@Path("id") long id);

    @PUT("memories/update/{id}")
    Observable<Memory> EditMemory(@Path("id") long id, @Body Memory memory);

    @GET("tags/findAll")
    Call<List<Tag>> GetAllTags();

    @GET("users/login")
    Call<User> login(@Query("mail")String email,@Query("password") String password);

    @GET("users/findByEmail/{email}")
    Call<User> GetUserByEmail(@Path("email") String email);
    @GET("stories/findStoriesByDobFull")
    Call<ArrayList<ListOfStory>> GetStoriesByDobFull (@Query("d") int day ,@Query("m") int month , @Query("y") int year);

    @GET("stories/findStoriesByDodFull")
    Call<ArrayList<ListOfStory>> GetStoriesByDodFull (@Query("d") int day ,@Query("m") int month , @Query("y") int year);

    @GET("stories/findById/{id}")
    Call<Story> GetStoryById(@Path("id") long id);

    @GET("stories/ViewStoryFull/{id}")
    Call<FullViewStory> GetFullViewStoryById(@Path("id") long id);

    @GET("memories/findMemoriesByKeyword/")
    Call<ArrayList<Memory>> GetMemoriesByKeyword(@Query("description") String description);


    @GET("stories/findStoriesByDobYearMonth")
    Call<ArrayList<ListOfStory>> GetStoriesByDobYearMonth (@Query("m") int month, @Query("y") int year );
    @GET("stories/findStoriesByDobYear")
    Call<ArrayList<Story>> GetStoriesByDobYear (@Query("y") int year);
    @GET("stories/findStoriesByDateOfBirth")
    Call<ArrayList<ListOfStory>> GetStoriesByDateOfBirth (@Query("d") int day, @Query("m") int month , @Query("y") int year , @Query("name") String name_of_person);
    @GET("stories/findStoriesByDateOfDeath")
    Call<ArrayList<ListOfStory>> GetStoriesByDateOfDeath(@Query("d") int day, @Query("m") int month , @Query("y") int year , @Query("name") String name_of_person);

    @POST("users/create")
    Call<User> CreateUser(@Body User newUser);
    @POST("users/forgotPassword")
    Call<Object> resetPassword(@Query("mail") String mail);


}
