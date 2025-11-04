package com.example.notesstorageapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IStorage storage;
    private ListView lvNotes;
    private Button btnAddNote, btnDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = StorageManager.getStorage(this);

        lvNotes = findViewById(R.id.lvNotes);
        btnAddNote = findViewById(R.id.btnAddNote);
        btnDeleteNote = findViewById(R.id.btnDeleteNote);

        btnAddNote.setOnClickListener(v -> {
            startActivity(new Intent(this, AddNoteActivity.class));
        });

        btnDeleteNote.setOnClickListener(v -> {
            startActivity(new Intent(this, DeleteNoteActivity.class));
        });

        lvNotes.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTitle = (String) parent.getItemAtPosition(position);
            Intent detailIntent = new Intent(this, NoteDetailActivity.class);
            detailIntent.putExtra(Constants.EXTRA_NOTE_DATA, selectedTitle);
            startActivity(detailIntent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        List<String> titles = storage.getAllNoteTitles();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lvNotes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_switch_storage) {
            switchStorage();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchStorage() {
        String current = StorageManager.getStorageType(this);
        String newType = current.equals("shared") ? "file" : "shared";
        StorageManager.setStorageType(this, newType);
        Toast.makeText(this, "Switched to " + newType + " storage", Toast.LENGTH_SHORT).show();
        recreate(); // Reload MainActivity with new storage
    }
}
