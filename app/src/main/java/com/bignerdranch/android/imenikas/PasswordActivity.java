package com.bignerdranch.android.imenikas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    private Button mPokreni;
    private EditText mImee;
    private EditText mPass;
    private TextView mTekst;
    private String mPoruka="Unesite password!";
    private DBPass mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mImee= (EditText)findViewById(R.id.pass);
        mPokreni=(Button)findViewById(R.id.pokreni);
        mTekst=(TextView)findViewById(R.id.tekst);
        mPass= (EditText)findViewById(R.id.zaconfirm);
        mydb = new DBPass(this);
        if(!mydb.isSetPass())  {
            mTekst.setText("Za pocetak potrebno je da unesete password, koji cete samo vi znati!");


        }
            else {

            mPass.setVisibility(View.INVISIBLE);
        }

        mPokreni.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v) {


                if(!mPass.getText().toString().equals(""))
                {
                    if(!mImee.getText().toString().equals(mPass.getText().toString()))
                {
                    mPoruka="Password i confirmpassword se ne podudaraju";
                    Toast.makeText(PasswordActivity.this, mPoruka, Toast.LENGTH_SHORT).show();
                }
                    else
                    {
                        mydb.insertPass(mImee.getText().toString());
                        Intent pocetak = new Intent(PasswordActivity.this, Izbornik.class);
                        startActivity(pocetak);
                    }
                }else


                {
                if (mImee.getText().toString().equals("")) {

                            Toast.makeText(PasswordActivity.this, mPoruka, Toast.LENGTH_SHORT).show();
                        } else {
                            if( mImee.getText().toString().equals(mydb.getPass(mImee.getText().toString()))) {
                                Intent pocetak = new Intent(PasswordActivity.this, Izbornik.class);
                                startActivity(pocetak);
                    } else {
                        mPoruka="Pogresan password";
                        Toast.makeText(PasswordActivity.this, mPoruka, Toast.LENGTH_SHORT).show();
                    }

                }
            }
            }
        });

    }
}
