package com.example.notesstorageapp;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage implements IStorage {

    private final Context context;
    private static final String FILE_EXTENSION = ".txt";

    public FileStorage(Context context) {
        this.context = context;
    }

    @Override
    public void saveNote(Note note) {
        try {
            File file = new File(context.getFilesDir(), note.getTitle() + FILE_EXTENSION);
            FileWriter writer = new FileWriter(file);
            writer.write(note.getContent());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Note getNote(String title) {
        try {
            File file = new File(context.getFilesDir(), title + FILE_EXTENSION);
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            br.close();
            return new Note(title, sb.toString().trim());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void deleteNote(String title) {
        File file = new File(context.getFilesDir(), title + FILE_EXTENSION);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public List<String> getAllNoteTitles() {
        List<String> titles = new ArrayList<>();
        File dir = context.getFilesDir();
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(FILE_EXTENSION)) {
                    titles.add(file.getName().replace(FILE_EXTENSION, ""));
                }
            }
        }

        return titles;
    }
}
