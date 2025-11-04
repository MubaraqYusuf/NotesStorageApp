package com.example.notesstorageapp;

import java.util.List;

public interface IStorage {
    void saveNote(Note note);
    Note getNote(String title);
    void deleteNote(String title);
    List<String> getAllNoteTitles();
}
