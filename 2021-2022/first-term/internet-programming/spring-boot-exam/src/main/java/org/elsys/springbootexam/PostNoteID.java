package org.elsys.springbootexam;

public class PostNoteID {
    private final int id;
    private String text;

    public PostNoteID(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }
}
