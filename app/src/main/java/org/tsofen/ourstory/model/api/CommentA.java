
package org.tsofen.ourstory.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentA {

    @SerializedName("createDate")
    @Expose
    private Object createDate;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("user")
    @Expose
    private Object user;
    @SerializedName("_links")
    @Expose
    private Links links;

    public Object getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Object createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

}
