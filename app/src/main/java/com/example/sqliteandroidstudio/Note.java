package com.example.sqliteandroidstudio;

import java.util.ArrayList;
import java.util.Date;

public class Note {
    public static ArrayList<Note> noteArrayList = new ArrayList<>();
    public static String NOTE_EDIT_EXTRA = "noteEdit";

    private int id;
    private String title;
    private String description;
    private Date delated;

    public Note(int id, String title, String description, Date delated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.delated = delated;
    }

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        delated = null;
    }

    public static Note getNoteForID(int passedNoteID) {
        for(Note note : noteArrayList){
            if(note.getId() == passedNoteID)
                return note;
        }

        return null;
    }

    public static ArrayList<Note> nonDeletedNotes(){
        ArrayList<Note> nonDeleted = new ArrayList<>();
        for(Note note : noteArrayList){
            if(note.getDelated() == null)
                nonDeleted.add(note);
        }

        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDelated() {
        return delated;
    }

    public void setDelated(Date delated) {
        this.delated = delated;
    }
}
