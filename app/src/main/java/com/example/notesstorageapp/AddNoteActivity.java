package com.example.notesstorageapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText edtTitle, edtContent;
    private IStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        Button btnSave = findViewById(R.id.btnSave);

        storage = StorageManager.getStorage(this);

        btnSave.setOnClickListener(v -> {
            String title = edtTitle.getText().toString().trim();
            String content = edtContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_empty_input), Toast.LENGTH_SHORT).show();
                return;
            }

            Note note = new Note(title, content);
            storage.saveNote(note);
            Toast.makeText(this, getString(R.string.note_saved), Toast.LENGTH_SHORT).show();
            finish(); // go back to main screen
        });
    }
}
