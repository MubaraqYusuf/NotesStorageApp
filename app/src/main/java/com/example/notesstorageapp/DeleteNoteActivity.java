package com.example.notesstorageapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DeleteNoteActivity extends AppCompatActivity {

    private ListView lvDeleteNotes;
    private Button btnDeleteNote;
    private IStorage storage;
    private String selectedNote = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        lvDeleteNotes = findViewById(R.id.lvDeleteNotes);
        btnDeleteNote = findViewById(R.id.btnDeleteNote);
        storage = StorageManager.getStorage(this);

        loadNotes();

        lvDeleteNotes.setOnItemClickListener((parent, view, position, id) -> {
            selectedNote = (String) parent.getItemAtPosition(position);
        });

        btnDeleteNote.setOnClickListener(v -> {
            if (selectedNote != null) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.menu_delete)
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            storage.deleteNote(selectedNote);
                            Toast.makeText(this, getString(R.string.note_deleted), Toast.LENGTH_SHORT).show();
                            loadNotes();
                            selectedNote = null;
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else {
                Toast.makeText(this, "Please select a note first.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNotes() {
        List<String> titles = storage.getAllNoteTitles();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lvDeleteNotes.setAdapter(adapter);
    }
}
