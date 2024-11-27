package org.sysCondo.types;

import java.time.LocalDate;

public class Message {
    String title;
    String author;
    LocalDate date;
    String text;

    public Message(String title, LocalDate date, String text, String author) {
        this.title = title;
        this.date = date;
        this.text = text;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public String getAuthor() {
        return author;
    }
}
