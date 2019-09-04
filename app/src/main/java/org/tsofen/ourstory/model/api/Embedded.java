
package org.tsofen.ourstory.model.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded {

    @SerializedName("comments")
    @Expose
    private List<Comment> comments = null;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
