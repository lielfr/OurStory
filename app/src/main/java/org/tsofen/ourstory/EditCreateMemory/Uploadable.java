package org.tsofen.ourstory.EditCreateMemory;

public class Uploadable {
    String url;
    boolean uploaded;

    public Uploadable(String url) {
        this.url = url;
        this.uploaded = false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
