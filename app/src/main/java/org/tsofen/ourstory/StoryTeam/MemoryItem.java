package org.tsofen.ourstory.StoryTeam;

import java.util.ArrayList;

public class MemoryItem {

    String mName;
    ArrayList<Integer> mImage;


    public MemoryItem(String mName, ArrayList<Integer> mImage) {
        this.mName = mName;
        this.mImage = mImage;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<Integer> getmImage() {
        return mImage;
    }

    public void setmImage(ArrayList<Integer> mImage) {
        this.mImage = mImage;
    }
}
