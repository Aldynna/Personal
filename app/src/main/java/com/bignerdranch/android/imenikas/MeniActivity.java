package com.bignerdranch.android.imenikas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Sabina on 28.6.2017.
 */

public class MeniActivity extends AppCompatActivity {

    private ImageButton mShop;
    private ImageButton mONama;
    private ImageButton mInstrukcije;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meni);

        mShop=(ImageButton)findViewById(R.id.shopbutton);
        mONama=(ImageButton)findViewById(R.id.onamabutton);
        mInstrukcije=(ImageButton)findViewById(R.id.instrukcijebutton);

        mShop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                // QuizActivity.glavna.finish();
                Intent intent = new Intent(MeniActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });

          mONama.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v){
                    Intent instrukcije = new Intent(MeniActivity.this, ONamaActivity.class);
                    startActivity(instrukcije);
                }
            });
/*
            mInstrukcije.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick (View v){
                    Intent onama = new Intent(MeniActivity.this, InstrukcijeActivity.class);
                    startActivity(onama);

                }
            });*/
    }
}