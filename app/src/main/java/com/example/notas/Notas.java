package com.example.notas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Notas extends AppCompatActivity {

    SQLiteDatabase bd;
    ListView listView;

    ArrayList<String> arrayList = new ArrayList<>();
    Cursor cursor = null;

    String id;
    String titulo;
    String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Notas");

        bd = openOrCreateDatabase("meubd",MODE_PRIVATE,null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS notas (" +
                "id integer primary key autoincrement," +
                "titulo varchar not null," +
                "texto varchar not null  )");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Notas.this,Nova_nota.class);
                startActivity(intent);

            }
        });



        Cursor cursor= bd.rawQuery("SELECT * FROM notas",null,null);
        cursor.moveToFirst();

        String id;
        String titulo;
        String texto;
        final ArrayList<String> arrayList = new ArrayList<>();

        while(!cursor.isAfterLast()){

            id = cursor.getString(cursor.getColumnIndex("id"));
            titulo= cursor.getString(cursor.getColumnIndex("titulo"));
            texto = cursor.getString(cursor.getColumnIndex("texto"));

            ContentValues cv = new ContentValues();

            cv.put("id",id);
            cv.put("titulo",titulo);
            cv.put("texto",texto);

            //Log.d("Tabela notas", id +" "+ titulo +" "+ texto);
            arrayList.add(cv.toString());
            cursor.moveToNext();
        }

        listView = findViewById(R.id.listview);

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            Intent restart = new Intent(Notas.this, Notas.class);

            startActivity(restart);

            bd.execSQL("Drop table 'notas';");

            finish();

            }
        });



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arrayList
        );

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String s = arrayList.get(i);

                Intent intent = new Intent(Notas.this,Edit.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Cursor cursor= bd.rawQuery("SELECT * FROM notas",null,null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            id = cursor.getString(cursor.getColumnIndex("id"));
            titulo= cursor.getString(cursor.getColumnIndex("titulo"));
            texto = cursor.getString(cursor.getColumnIndex("texto"));

            //Log.d("Tabela notas", id +" "+ titulo +" "+ texto);
            cursor.moveToNext();
        }

        listView = findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arrayList
        );

        listView.setAdapter(adapter);
    }

}
