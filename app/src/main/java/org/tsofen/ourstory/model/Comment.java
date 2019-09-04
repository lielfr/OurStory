package org.tsofen.ourstory.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    Date createDate;
    long user;
    String text;


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


