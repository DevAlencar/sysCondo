package org.sysCondo.types;

import java.util.Date;

public class Message {
    String title;
    String author;
    Date date;
    String text;

    public Message(String title, Date date, String text, String author) {
        this.title = title;
        this.date = date;
        this.text = text;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public Date getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public String getAuthor() {
        return author;
    }
}
