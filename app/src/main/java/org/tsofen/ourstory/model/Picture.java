package org.tsofen.ourstory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Picture implements Serializable {
    private final static long serialVersionUID = 6464089456434690643L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("link")
    @Expose
    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() { return link; }

    public void setLink(String link) {
        this.link = link;
    }

    public Picture(String link) {
        super();
        this.link = link;
    }
}