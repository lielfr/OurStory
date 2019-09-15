package org.tsofen.ourstory.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


    public class FullViewStory implements Serializable
    {

        @SerializedName("story")
        @Expose
        private Story story;

        @SerializedName("top3tags")
        @Expose
        private List<String> top3tags;// = null;
        @SerializedName("memories")
        @Expose
        private List<VSMemories> memories = null;
        private final static long serialVersionUID = 5414104865585291410L;

        public Story getStory() {
            return story;
        }

        public void setStory(Story story) {
            this.story = story;
        }

        public List<String> getTop3tags() {
            return top3tags;
        }

        public void setTop3tags(List<String> top3tags) {
            this.top3tags = top3tags;
        }

        public List<VSMemories> getMemories() {
            return memories;
        }

        public void setMemories(List<VSMemories> memories) {
            this.memories = memories;
        }

    }

