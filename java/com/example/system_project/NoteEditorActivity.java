package com.example.system_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    int noteId,noteId1,noteId2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        this.setTitle("Note down Here");
        EditText name = (EditText)findViewById(R.id.nameofnote);
        EditText datw = (EditText)findViewById(R.id.dateofnote);
        EditText editText = (EditText)findViewById(R.id.editTextTextMultiLine);

        Intent intent = getIntent();
        noteId1 = intent.getIntExtra("noteId1", -1);
        noteId2 = intent.getIntExtra("noteId2", -1);

        noteId = intent.getIntExtra("noteId", -1);
        if(noteId1 != -1){

            name.setText(NotesActivity.notename.get(noteId1));

        }else {
            NotesActivity.notename.add("");
            noteId1 = NotesActivity.notename.size()-1;

            NotesActivity.arrayAdaptername.notifyDataSetChanged();
        }
        if(noteId2 != -1){

            datw.setText(NotesActivity.notedate.get(noteId2));
        }else {
            NotesActivity.notedate.add("");
            noteId2 = NotesActivity.notedate.size()-1;

            NotesActivity.arrayAdapter2.notifyDataSetChanged();
        }
        if(noteId != -1){
            editText.setText(NotesActivity.notes.get(noteId));

        }else {
            NotesActivity.notes.add("");
            noteId = NotesActivity.notes.size()-1;

            NotesActivity.arrayAdapter.notifyDataSetChanged();
        }

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                NotesActivity.notename.set(noteId1, String.valueOf(charSequence));
                NotesActivity.arrayAdaptername.notifyDataSetChanged();

                SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);
                HashSet<String> set1 = new HashSet<>(NotesActivity.notename);
                sharedPreferences1.edit().putStringSet("notename",set1).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        datw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                NotesActivity.notedate.set(noteId2, String.valueOf(charSequence));
                NotesActivity.arrayAdapter2.notifyDataSetChanged();

                SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);
                HashSet<String> set2 = new HashSet<>(NotesActivity.notedate);
                sharedPreferences2.edit().putStringSet("notedate",set2).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                NotesActivity.notes.set(noteId, String.valueOf(charSequence));
                NotesActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.system_project", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(NotesActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}