
package org.tsofen.ourstory.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Self_ {

    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("templated")
    @Expose
    private Boolean templated;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getTemplated() {
        return templated;
    }

    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }

}
