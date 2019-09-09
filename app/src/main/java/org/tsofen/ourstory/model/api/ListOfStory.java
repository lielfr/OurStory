package org.tsofen.ourstory.model.api;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfStory implements Serializable
{
    public ListOfStory(Integer storyId, Owner owner, String nameOfPerson, String dateOfBirth, String dateOfDeath, Object picture) {
        this.storyId = storyId;
        this.owner = owner;
        this.nameOfPerson = nameOfPerson;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.picture = picture;
    }

    @SerializedName("story_id")
    @Expose
    private Integer storyId;
    @SerializedName("owner")
    @Expose
    private Owner owner;
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
    private final static long serialVersionUID = 7442956770361619051L;

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
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

}