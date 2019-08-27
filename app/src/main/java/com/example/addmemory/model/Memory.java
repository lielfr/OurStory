package com.example.addmemory.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

public class Memory {
    long id;
    long storyId;
    long creatorId;
    String creatorName;
    URI creatorPic;
    String description;
    Date memoryDate;
    Date createDate;
    Feeling feeling;
    String location;
    ArrayList<URI> pictures;
    ArrayList<URI> videos;
    ArrayList<Tag> tags;
    ArrayList<Long> likes;
    ArrayList<Comment> comments;
    ArrayList<Long> shares;
    boolean isPrivate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getMemoryDate() {
        return memoryDate;
    }

    public void setMemoryDate(Date memoryDate) {
        this.memoryDate = memoryDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public ArrayList<URI> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<URI> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<URI> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<URI> videos) {
        this.videos = videos;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Long> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Long> likes) {
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


}
