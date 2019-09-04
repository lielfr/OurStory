package org.tsofen.ourstory.model;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    Date date;
    long userId;
    String comment;
}
