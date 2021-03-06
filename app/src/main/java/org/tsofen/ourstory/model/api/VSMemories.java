package org.tsofen.ourstory.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VSMemories implements Serializable {

    private final static long serialVersionUID = 2410229032724011412L;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("pics")
    @Expose
    private List<List<String>> pics = null;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<List<String>> getPics() {
        return pics;
    }

    public void setPics(List<List<String>> pics) {
        this.pics = pics;
    }

}