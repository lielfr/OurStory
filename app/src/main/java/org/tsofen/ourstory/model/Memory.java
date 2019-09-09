package org.tsofen.ourstory.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.tsofen.ourstory.model.api.Story;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.getInstance;

public class Memory implements Serializable {
    private final static long serialVersionUID = -7521612754263171930L;
    @SerializedName("memory_id")
    @Expose
    Long id;
    @SerializedName("story")
    Story story;
    @SerializedName("contributer")
    @Expose
    Long creatorId;
    String creatorName;
    URI creatorPic;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("memory_date")
    @Expose
    Date memoryDate;
    @SerializedName("create_date")
    @Expose
    Date createDate;
    @SerializedName("feeling")
    @Expose
    Feeling feeling;
    @SerializedName("location")
    @Expose
    String location;
    @SerializedName("pictures")
    @Expose
    ArrayList<String> pictures;
    @SerializedName("videos")
    @Expose
    ArrayList<String> videos;
    ArrayList<Tag> tags;
    @SerializedName("likes")
    @Expose
    ArrayList<Long> likes;
    @SerializedName("comments")
    @Expose
    ArrayList<Comment> comments;
    ArrayList<Long> shares;
    @SerializedName("is_private")
    @Expose
    boolean isPrivate;

    public Long getId() {
        return id;
    }

    public long getStoryId() {
        return story.getStoryId();
    }

    public void setStoryId(long storyId) {
        story.setStoryId(storyId);
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getMemoryDate() {
//        return memoryDate;
//    }

    public Memory(String creatorName, URI creatorPic, String description, Date createDate, Date memoryDate) {
        this.creatorName = creatorName;
        this.creatorPic = creatorPic;
        this.description = description;
        this.memoryDate = memoryDate;
        this.createDate = createDate;
    }

    public static ArrayList<Memory> createContactsList() {
        ArrayList<Memory> memories = new ArrayList<Memory>();
        Calendar d1 = getInstance();
        Calendar d2 = getInstance();
        Calendar d3 = getInstance();
        Calendar d4 = getInstance();
        Calendar d5 = getInstance();
        Calendar d6 = getInstance();
        Calendar d7 = getInstance();
        Calendar d8 = getInstance();
        Calendar d9 = getInstance();
        Calendar d10 = getInstance();
        d1.set(2005, 8, 1);
        d2.set(2005, 6, 12);
        d3.set(2005, 10, 1);
        d4.set(2005, 10, 1);
        d5.set(2005, 10, 1);
        d6.set(2000, 10, 1);
        d7.set(2000, 9, 12);
        d8.set(2000, 2, 12);
        d9.set(2000, 2, 12);
        d10.set(1999, 11, 5);
        memories.add(new Memory("Hazar", null, "Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you ", d1.getTime(), d2.getTime()));
        memories.add(new Memory("Hazar", null, "Wish you were here. Wish you were here. Wish you were here. Wish you were here. Wish you were here. Wish you were here. ", d3.getTime(), d4.getTime()));
        memories.add(new Memory("Oron", null, "Wanted to share all the day with you.", d5.getTime(), d6.getTime()));
        memories.add(new Memory("Aya", null, "Missing you. just sat on our beach and thought about you", d7.getTime(), d8.getTime()
        ));
        memories.add(new Memory("Orwa", null, "Wish you were celebrating my birthday with me this YearActivity.", d9.getTime(), d10.getTime()));
       /* memories.add(new Memory("Oron",null,"Celebrating our friend's wedding",date = setDa(2019,03,13),date = setDa(2019,7,6)));
        memories.add(new Memory("Hazar",null,"Our trip to Rome.",date = setDa(2016,02,7),date = setDa(2017,4,1)));
        memories.add(new Memory("Aya",null,"Missed our camping trips.",date = setDa(2015,01,8),date = setDa(2015,3,11)));
        memories.add(new Memory("Aya",null,"Missing you. just dat on our beach and thought about you",date = setDa(2011,06,9),date = setDa(2012,1,1)));
        memories.add(new Memory("Orwa",null,"Missing you. just dat on our beach and thought about you",date = setDa(2012,03,10),date = setDa(2012,12,12)));*/
        return memories;
    }

    public static ArrayList<Memory> createContactsListMyMemories() {
        ArrayList<Memory> memories = new ArrayList<Memory>();
        Calendar d1 = getInstance();
        Calendar d2 = getInstance();
        Calendar d3 = getInstance();
        Calendar d4 = getInstance();
        Calendar d5 = getInstance();
        Calendar d6 = getInstance();
        Calendar d7 = getInstance();
        Calendar d8 = getInstance();
        Calendar d9 = getInstance();
        Calendar d10 = getInstance();
        d1.set(2011, 8, 1);
        d2.set(2010, 6, 12);
        d3.set(2004, 11, 1);
        d4.set(2000, 10, 1);
        d5.set(2000, 9, 1);
        d6.set(2000, 8, 1);
        d7.set(2000, 9, 12);
        d8.set(2000, 2, 12);
        d9.set(2000, 2, 12);
        d10.set(1999, 11, 5);
        memories.add(new Memory("Hazar Nakhleh", null, "Missing you. just sat on our beach and thought about you.Missing you. just sat on our beach and thought about you.Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you Missing you. just sat on our beach and thought about you", d1.getTime(), d2.getTime()));
        memories.add(new Memory("Hazar Nakhleh", null, "Wish you were here.", d3.getTime(), d4.getTime()));
        memories.add(new Memory("Oron Werner", null, "Wanted to share all the day with you.", d5.getTime(), d6.getTime()));
        memories.add(new Memory("Aya Abed", null, "Missing you. just sat on our beach and thought about you", d7.getTime(), d8.getTime()));
        memories.add(new Memory("Orwa Watad", null, "Wish you were celebrating my birthday with me this YearActivity.", d9.getTime(), d10.getTime()));
        return memories;
    }

//    public Date getCreateDate() {
//        return createDate;
//    }

    public Calendar getMemoryDate() {
        Calendar c = getInstance();
        c.setTime(memoryDate);
        return c;
    }

    public void setMemoryDate(Date memoryDate) {
        this.memoryDate = memoryDate;
    }

    public void setMemoryDate(Calendar memoryDate) {
        this.memoryDate = memoryDate.getTime();
    }

    public Feeling getFeeling() {
        return feeling;
    }

    public void setFeeling(Feeling feeling) {
        this.feeling = feeling;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<String> videos) {
        this.videos = videos;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public ArrayList<Long> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Long> likes) {
        this.likes = likes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Long> getShares() {
        return shares;
    }

    public void setShares(ArrayList<Long> shares) {
        this.shares = shares;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public URI getCreatorPic() {
        return creatorPic;
    }

    public Calendar getCreateDate() {
        Calendar c = getInstance();
        c.setTime(createDate);
        return c;
    }

    public Memory() {
    }

    public void setCreatorPic(URI creatorPic) {
        this.creatorPic = creatorPic;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate.getTime();
    }

}
