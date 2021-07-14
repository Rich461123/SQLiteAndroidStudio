package com.example.sqliteandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private Note selectedNote;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initWidgets();
        checkForEditNote();
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteNoteButton);
    }

    private void checkForEditNote() {
        Intent previousIntent = getIntent();

        int passedNoteID = previousIntent.getIntExtra(Note.NOTE_EDIT_EXTRA, -1);
        selectedNote = Note.getNoteForID(passedNoteID);

        if(selectedNote != null){
            titleEditText.setText(selectedNote.getTitle());
            descriptionEditText.setText(selectedNote.getDescription());
        }else{
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveNote(View view) {

        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatebase(this);
        String title = String.valueOf(titleEditText.getText());
        String description = String.valueOf(descriptionEditText.getText());

        if(selectedNote == null){
            int id = Note.noteArrayList.size();
            Note newNote = new Note(id, title, description);
            Note.noteArrayList.add(newNote);
            sqLiteManager.addNoteToDatabase(newNote);
        }else{
            selectedNote.setTitle(title);
            selectedNote.setDescription(description);
            sqLiteManager.updateNoteInDB(selectedNote);
        }

        finish();
    }

    public void deleteNote(View view) {
        selectedNote.setDelated(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatebase(this);
        sqLiteManager.updateNoteInDB(selectedNote);
        finish();
    }
}