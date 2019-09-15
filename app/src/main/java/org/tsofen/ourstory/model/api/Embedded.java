
package org.tsofen.ourstory.model.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Embedded {

    @SerializedName("comments")
    @Expose
    private List<CommentA> comments = null;

    public List<CommentA> getComments() {
        return comments;
    }

    public void setComments(List<CommentA> comments) {
        this.comments = comments;
    }

}
