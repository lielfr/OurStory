package org.tsofen.ourstory.model.api;

import android.provider.CalendarContract;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.tsofen.ourstory.model.Comment;

public class MemoryA implements Serializable {

    private final static long serialVersionUID = 4738795329279404307L;
    @SerializedName("memory_id")
    @Expose
    private
    Long memoryId;
    @SerializedName("story")
    @Expose
    private Story story;
    @SerializedName("contributer")
    @Expose
    private Contributer contributer;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("memory_date")
    @Expose
    private Calendar memoryDate;
    @SerializedName("create_date")
    @Expose
    private Calendar createDate;
    @SerializedName("feeling")
    @Expose
    private String feeling;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;
    @SerializedName("is_private")
    @Expose
    private Boolean isPrivate;
    @SerializedName("likes")
    @Expose
    private List<Object> likes = null;
    @SerializedName("pictures")
    @Expose
    private List<Object> pictures = null;
    @SerializedName("videos")
    @Expose
    private List<Object> videos = null;


    /**
     * No args constructor for use in serialization
     */
    public MemoryA() {
    }

    /**
     * @param contributer
     * @param location
     * @param videos
     * @param pictures
     * @param memoryId
     * @param memoryDate
     * @param story
     * @param isPrivate
     * @param description
     * @param feeling
     * @param likes
     * @param createDate
     * @param comments
     */
    public MemoryA(Long memoryId, Story story, Contributer contributer, String description, Calendar memoryDate, Calendar createDate,String feeling, String location, List<Comment> comments, Boolean isPrivate, List<Object> likes, List<Object> pictures, List<Object> videos) {
        super();
        this.memoryId = memoryId;
        this.story = story;
        this.contributer = contributer;
        this.description = description;
        this.memoryDate = memoryDate;
        this.createDate = createDate;
        this.feeling = feeling;
        this.location = location;
        this.comments = comments;
        this.isPrivate = isPrivate;
        this.likes = likes;
        this.pictures = pictures;
        this.videos = videos;
    }

    public Long getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Long memoryId) {
        this.memoryId = memoryId;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Contributer getContributer() {
        return contributer;
    }

    public void setContributer(Contributer contributer) {
        this.contributer = contributer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getMemoryDate() {
        return memoryDate;
    }

    public void setMemoryDate(Calendar memoryDate) {
        this.memoryDate = memoryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public List<Object> getLikes() {
        return likes;
    }

    public void setLikes(List<Object> likes) {
        this.likes = likes;
    }

    public List<Object> getPictures() {
        return pictures;
    }

    public void setPictures(List<Object> pictures) {
        this.pictures = pictures;
    }

    public List<Object> getVideos() {
        return videos;
    }

    public void setVideos(List<Object> videos) {
        this.videos = videos;
    }

}