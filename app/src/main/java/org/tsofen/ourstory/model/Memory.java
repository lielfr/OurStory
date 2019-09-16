package org.tsofen.ourstory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import org.tsofen.ourstory.model.api.Contributer;
import org.tsofen.ourstory.model.api.Story;
import org.tsofen.ourstory.model.api.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.getInstance;

public class Memory implements Serializable {
    private final static long serialVersionUID = -7521612754263171930L;
    @SerializedName("memory_id")
    @Expose
    private Long id;
    @SerializedName("story")
    private Story story;
    @SerializedName("contributer")
    @Expose
    private User user;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("memory_date")
    @Expose
    private Date memoryDate;
    @SerializedName("create_date")
    @Expose
    private Date createDate;
    @SerializedName("feeling")
    @Expose
    private Feeling feeling;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("pictures")
    @Expose
    private ArrayList<Picture> pictures = new ArrayList<>();
    @SerializedName("videos")
    @Expose
    private ArrayList<Video> videos = new ArrayList<>();
    private ArrayList<Tag> tags = new ArrayList<>();
    @SerializedName("likes")
    @Expose
    private ArrayList<Object> likes = new ArrayList<Object>();
    @SerializedName("comments")
    @Expose
    private ArrayList<Comment> comments;
    private ArrayList<Long> shares;
    @SerializedName("is_private")
    @Expose
    boolean isPrivate;


    public Long getId() {
        return id;
    }

    public long getStoryId() {
        return story.getStoryId();
    }

    public void setStoryId(long storyId) {
        story.setStoryId(storyId);
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Memory(User user, String description, Date createDate, Date memoryDate) {
        this.user = user;
        this.description = description;
        this.memoryDate = memoryDate;
        this.createDate = createDate;
    }

    public Calendar getMemoryDate() {
        if (memoryDate == null)
            return null;
        Calendar c = getInstance();
        c.setTime(memoryDate);
        return c;
    }

    public void setMemoryDate(Date memoryDate) {
        this.memoryDate = memoryDate;
    }

    public void setMemoryDate(Calendar memoryDate) {
        this.memoryDate = memoryDate.getTime();
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Object> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Object> likes) {
        this.likes = likes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Long> getShares() {
        return shares;
    }

    public void setShares(ArrayList<Long> shares) {
        this.shares = shares;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Calendar getCreateDate() {
        Calendar c = getInstance();
        c.setTime(createDate);
        return c;
    }

    public Memory() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*public Contributer getContributer() {
        return contributer;
    }*/
}
