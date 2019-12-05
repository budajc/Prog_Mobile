package com.example.notas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Nova_nota extends AppCompatActivity {

    SQLiteDatabase bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_nota);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bd = openOrCreateDatabase("meubd",MODE_PRIVATE,null);

        FloatingActionButton fab = findViewById(R.id.flot);
        final EditText txt1;
        final EditText txt2;


        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String t1 = txt1.getText().toString();
                String t2 = txt2.getText().toString();

                if (t1.equalsIgnoreCase("") || t2.equalsIgnoreCase("")) {

                    Toast.makeText(Nova_nota.this, "Preencha os Campos", Toast.LENGTH_SHORT).show();

                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("titulo", t1);
                    contentValues.put("texto", t2);

                    bd.insert("notas", null, contentValues);

                    Intent restart = new Intent(Nova_nota.this, Notas.class);

                    startActivity(restart);

                    Toast.makeText(Nova_nota.this, "Concluido com sucesso", Toast.LENGTH_SHORT).show();

                    t1 = null;
                    t2 = null;

                    finish();

                }
            }
        });




    }

}
