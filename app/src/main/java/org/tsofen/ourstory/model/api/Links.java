
package org.tsofen.ourstory.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private Self self;
    @SerializedName("comment")
    @Expose
    private Comment_ comment;
    @SerializedName("tags")
    @Expose
    private Tags tags;
    @SerializedName("memory")
    @Expose
    private Memory memory;
    @SerializedName("users")
    @Expose
    private Users users;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Comment_ getComment() {
        return comment;
    }

    public void setComment(Comment_ comment) {
        this.comment = comment;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
