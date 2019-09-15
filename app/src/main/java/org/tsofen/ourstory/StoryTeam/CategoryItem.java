package org.tsofen.ourstory.StoryTeam;

public class CategoryItem {

    String mName;
    Integer mImage;

    public CategoryItem(String mName, Integer mImage) {
        this.mName = mName;
        this.mImage = mImage;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Integer getmImage() {
        return mImage;
    }

    public void setmImage(Integer mImage) {
        this.mImage = mImage;
    }
}
