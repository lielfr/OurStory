package org.tsofen.ourstory.model;

import java.io.Serializable;

public class Tag implements Serializable {
    long id;
    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
