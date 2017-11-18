package com.bignerdranch.android.imenikas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Izbornik extends AppCompatActivity {
    private ImageButton mNotes;
    private ImageButton mContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik);
        mNotes=(ImageButton)findViewById(R.id.notesbutton);
        mContacts=(ImageButton)findViewById(R.id.contactsbutton);
        mNotes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                // QuizActivity.glavna.finish();
                Intent intent = new Intent(Izbornik.this, NoteActivity.class);
                startActivity(intent);
            }
        });

        mContacts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                // QuizActivity.glavna.finish();
                Intent intent = new Intent(Izbornik.this, ContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}
