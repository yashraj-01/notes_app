package com.example.zappycode.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NotesActivity extends AppCompatActivity {

    EditText noteEditText;
    int noteID;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.zappycode.notes", Context.MODE_PRIVATE);

        noteEditText = findViewById(R.id.noteEditText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID",-1);
        if(noteID != -1){
            noteEditText.setText(MainActivity.notes.get(noteID));
        }else {
            MainActivity.notes.add("");
            noteID = MainActivity.notes.size() - 1;
        }
        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteID, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
