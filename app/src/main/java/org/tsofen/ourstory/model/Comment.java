package org.tsofen.ourstory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.tsofen.ourstory.model.api.User;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    @SerializedName("id")
    @Expose
    Long id;

    @SerializedName("createDate")
    @Expose
    Date createDate;

    @SerializedName("user")
    @Expose
    User user;
    @SerializedName("text")
    @Expose
    String text;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


