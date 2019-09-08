
package org.tsofen.ourstory.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIComment {

    @SerializedName("_embedded")
    @Expose
    private Embedded embedded;
    @SerializedName("_links")
    @Expose
    private Links_ links;
    @SerializedName("page")
    @Expose
    private Page page;

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
