package com.bignerdranch.android.imenikas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayNote extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DBHNEW mydb ;

    TextView name ;
    TextView datum;
    TextView note;
    TextView grad;
    TextView datumD;
    protected static TextView displayCurrentTime;
    int id_To_Up = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);
        name = (TextView) findViewById(R.id.editTextNameN);

        datum = (TextView) findViewById(R.id.editTextDatum);
        note = (TextView) findViewById(R.id.editTextNote);
        grad = (TextView) findViewById(R.id.editTextCity);
        datumD=(TextView) findViewById(R.id.textViewDatum);
        datum.setVisibility(View.INVISIBLE);
        datumD.setVisibility(View.INVISIBLE);


        mydb = new DBHNEW(this);
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
            {
            int Value = extras.getInt("id");
            //int Value=1;
            if(Value>0)
                 {
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getNote(Value);
                     id_To_Up = Value;
                     rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(DBHNEW.CONTACTS_COLUMN_NAME));
                String dat = rs.getString(rs.getColumnIndex(DBHNEW.CONTACTS_COLUMN_EMAIL));
                String gra = rs.getString(rs.getColumnIndex(DBHNEW.CONTACTS_COLUMN_STREET));
                String not = rs.getString(rs.getColumnIndex(DBHNEW.CONTACTS_COLUMN_CITY));


                if (!rs.isClosed())  {
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.buttonb);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence)nam);
                name.setFocusable(false);
                name.setClickable(false);

                datum.setText((CharSequence)dat);
                     datum.setVisibility(View.VISIBLE);
                     datumD.setVisibility(View.VISIBLE);
                datum.setFocusable(false);
                datum.setClickable(false);

                grad.setText((CharSequence)gra);
                grad.setFocusable(false);
                grad.setClickable(false);

                note.setText((CharSequence)not);
                note.setFocusable(false);
                note.setClickable(false);

            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                getMenuInflater().inflate(R.menu.display_note, menu);
            } else{
                getMenuInflater().inflate(R.menu.note_menu, menu);
                datum.setVisibility(View.INVISIBLE);
                datumD.setVisibility(View.INVISIBLE);
            }
        }
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.Edit_Note:
                Button b = (Button)findViewById(R.id.buttonb);
                b.setVisibility(View.VISIBLE);

                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);


                datum.setEnabled(true);
                datum.setFocusableInTouchMode(true);
               datum.setClickable(true);
                datum.setVisibility(View.VISIBLE);
                datumD.setVisibility(View.VISIBLE);

                grad.setEnabled(true);
                grad.setFocusableInTouchMode(true);
                grad.setClickable(true);

                note.setEnabled(true);
                note.setFocusableInTouchMode(true);
                note.setClickable(true);


                return true;
            case R.id.Delete_Note:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deletenote)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Up);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }




    public void runn(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                if(mydb.updateContact(id_To_Up,name.getText().toString(),
                        datum.getText().toString(), grad.getText().toString(),
                        note.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(mydb.insertContact(name.getText().toString(),
                        grad.getText().toString(), note.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done note",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),NoteActivity.class);
                startActivity(intent);
            }
        }
    }
}
