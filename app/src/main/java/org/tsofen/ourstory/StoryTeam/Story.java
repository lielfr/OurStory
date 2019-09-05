package org.tsofen.ourstory.StoryTeam;

import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;
import java.util.Date;

public class Story {
    ArrayList<Memory> mems;
    String FirstName, LastName;
    String date1, date2;
    Date date1D, date2D, date3D;
    int img, id;
    String stamS;
    Date created_date;
    String url_link;
    int Owner_id;
    // User owner;
    String tag1, tag2, tag3;
    int tag_icon1, tag_icon2, tag_icon3;

    public Story(String firstName, String lastName, String date1, String date2/*, int img*/) {
        FirstName = firstName;
        LastName = lastName;
        this.date1 = date1;
        this.date2 = date2;
        // this.img = img;
    }

    public Story(String firstName, String lastName, String date1, String date2,
                 int img, String tag1, String tag2, String tag3,
                 int tag_icon1, int tag_icon2, int tag_icon3) {
        FirstName = firstName;
        LastName = lastName;
        this.date1 = date1;
        this.date2 = date2;
        this.img = img;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag_icon1 = tag_icon1;
        this.tag_icon2 = tag_icon2;
        this.tag_icon3 = tag_icon3;
    }

    // new c'tor
    public Story(ArrayList<Memory> memorys, String firstName, String lastName, Date date1, Date date2, int img,
                 int id, Date created_date, String url_link, int owner_id) {
        super();
        this.mems = memorys;
        FirstName = firstName;
        LastName = lastName;
        this.date1D = date1;
        this.date2D = date2;
        this.img = img;
        this.id = id;
        this.created_date = created_date;
        this.url_link = url_link;
        Owner_id = owner_id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getUrl_link() {
        return url_link;
    }

    public void setUrl_link(String url_link) {
        this.url_link = url_link;
    }

    public int getOwner_id() {
        return Owner_id;
    }

    public void setOwner_id(int owner_id) {
        Owner_id = owner_id;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public int getTag_icon1() {
        return tag_icon1;
    }

    public void setTag_icon1(int tag_icon1) {
        this.tag_icon1 = tag_icon1;
    }

    public int getTag_icon2() {
        return tag_icon2;
    }

    public void setTag_icon2(int tag_icon2) {
        this.tag_icon2 = tag_icon2;
    }

    public int getTag_icon3() {
        return tag_icon3;
    }

    public void setTag_icon3(int tag_icon3) {
        this.tag_icon3 = tag_icon3;
    }
}