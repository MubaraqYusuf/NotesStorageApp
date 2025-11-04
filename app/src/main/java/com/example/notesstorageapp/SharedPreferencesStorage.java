package com.example.notesstorageapp;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPreferencesStorage implements IStorage {

    private final SharedPreferences preferences;

    public SharedPreferencesStorage(Context context) {
        preferences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void saveNote(Note note) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(note.getTitle(), note.getContent());

        Set<String> titles = preferences.getStringSet(Constants.NOTES_KEY, new HashSet<>());
        titles = new HashSet<>(titles); // make it mutable
        titles.add(note.getTitle());
        editor.putStringSet(Constants.NOTES_KEY, titles);

        editor.apply();
    }

    @Override
    public Note getNote(String title) {
        String content = preferences.getString(title, null);
        return (content != null) ? new Note(title, content) : null;
    }

    @Override
    public void deleteNote(String title) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(title);

        Set<String> titles = preferences.getStringSet(Constants.NOTES_KEY, new HashSet<>());
        titles = new HashSet<>(titles);
        titles.remove(title);
        editor.putStringSet(Constants.NOTES_KEY, titles);

        editor.apply();
    }

    @Override
    public List<String> getAllNoteTitles() {
        Set<String> titles = preferences.getStringSet(Constants.NOTES_KEY, new HashSet<>());
        return new ArrayList<>(titles);
    }
}
