package org.tsofen.ourstory.model.api;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemoryA implements Serializable {

    private final static long serialVersionUID = 4738795329279404307L;
    @SerializedName("memory_id")
    @Expose
    private Integer memoryId;
    @SerializedName("story")
    @Expose
    private Object story;
    @SerializedName("contributer")
    @Expose
    private Contributer contributer;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("memory_date")
    @Expose
    private Object memoryDate;
    @SerializedName("create_date")
    @Expose
    private Object createDate;
    @SerializedName("feeling")
    @Expose
    private Object feeling;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("comments")
    @Expose
    private List<Object> comments = null;
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
    public MemoryA(Integer memoryId, Object story, Contributer contributer, Object description, Object memoryDate, Object createDate, Object feeling, Object location, List<Object> comments, Boolean isPrivate, List<Object> likes, List<Object> pictures, List<Object> videos) {
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

    public Integer getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Integer memoryId) {
        this.memoryId = memoryId;
    }

    public Object getStory() {
        return story;
    }

    public void setStory(Object story) {
        this.story = story;
    }

    public Contributer getContributer() {
        return contributer;
    }

    public void setContributer(Contributer contributer) {
        this.contributer = contributer;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getMemoryDate() {
        return memoryDate;
    }

    public void setMemoryDate(Object memoryDate) {
        this.memoryDate = memoryDate;
    }

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public Object getFeeling() {
        return feeling;
    }

    public void setFeeling(Object feeling) {
        this.feeling = feeling;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public List<Object> getComments() {
        return comments;
    }

    public void setComments(List<Object> comments) {
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