package org.tsofen.ourstory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Video implements Serializable {
    private final static long serialVersionUID = -7081945761110518481L;
    @SerializedName("link")
    @Expose
    private String link;

    public Video() {
        super();
    }

    public Video(String link) {
        super();
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
