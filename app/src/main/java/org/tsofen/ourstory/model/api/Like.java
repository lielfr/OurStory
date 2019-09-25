package org.tsofen.ourstory.model.api;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Like implements Serializable {
    private final static long serialVersionUID = 6187412368657906420L;
    @SerializedName("like_id")
    @Expose
    private Integer likeId;
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * No args constructor for use in serialization
     */
    public Like() {
    }

    /**
     * @param user
     * @param likeId
     */
    public Like(Integer likeId, User user) {
        super();
        this.likeId = likeId;
        this.user = user;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}