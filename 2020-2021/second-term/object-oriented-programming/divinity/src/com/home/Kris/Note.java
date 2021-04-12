package com.home.Kris;

public class Note extends Item{
    private final String textField;

    public Note(String name, String textField) throws Exception {
        if (textField.equals("")) {
            throw new Exception("Invalid text");
        }
        else {
            this.setName(name);
            this.textField = textField;
        }
    }

    public void readNote() {
        System.out.println(textField);
    }
}
