package org.tsofen.ourstory.model.api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Story implements Serializable
{

    @SerializedName("story_id")
    @Expose
    private long storyId;


    @SerializedName("owner")
    @Expose
    private User owner;
    @SerializedName("name_of_person")
    @Expose
    private String nameOfPerson;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("date_of_death")
    @Expose
    private String dateOfDeath;
    @SerializedName("picture")
    @Expose
    private Object picture;
    private final static long serialVersionUID = -1652510145870620895L;

    public long getStoryId() {
        return storyId;
    }

    public void setStoryId(long storyId) {
        this.storyId = storyId;
    }

    public Story(Integer storyId, User owner, String nameOfPerson, String dateOfBirth, String dateOfDeath, Object picture) {
        this.storyId = storyId;
        this.owner = owner;
        this.nameOfPerson = nameOfPerson;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.picture = picture;
    }

    public Story(User owner, String nameOfPerson, String dateOfBirth, String dateOfDeath, Object picture) {
        this.owner = owner;
        this.nameOfPerson = nameOfPerson;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.picture = picture;
    }

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public void setNameOfPerson(String nameOfPerson) {
        this.nameOfPerson = nameOfPerson;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public Story(Integer storyId, String nameOfPerson, String dateOfDeath, Object picture) {
        this.storyId = storyId;
        this.nameOfPerson = nameOfPerson;
        this.dateOfDeath = dateOfDeath;
        this.picture = picture;
    }

    public Story(String nameOfPerson, String dateOfBirth, String dateOfDeath, Object picture) {
        //this.owner = owner;
        this.nameOfPerson = nameOfPerson;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.picture = picture;
    }

}