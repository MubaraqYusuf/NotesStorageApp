package com.example.notesstorageapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView detailTitle, detailContent;
    private IStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        detailTitle = findViewById(R.id.detailTitle);
        detailContent = findViewById(R.id.detailContent);
        storage = StorageManager.getStorage(this);

        String noteTitle = getIntent().getStringExtra(Constants.EXTRA_NOTE_DATA);

        if (noteTitle != null) {
            Note note = storage.getNote(noteTitle);
            if (note != null) {
                detailTitle.setText(note.getTitle());
                detailContent.setText(note.getContent());
            } else {
                detailTitle.setText("");
                detailContent.setText(getString(R.string.note_not_found));
            }
        }
    }
}
