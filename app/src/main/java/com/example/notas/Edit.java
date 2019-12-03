package com.example.notas;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {

    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bd = openOrCreateDatabase("meubd",MODE_PRIVATE,null);


        final EditText txt1 = findViewById(R.id.txt1_edit);
        final EditText txt2 = findViewById(R.id.txt2_edit);
        final EditText id_ed = findViewById(R.id.txt_id);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String t1 = txt1.getText().toString();
                String t2 = txt2.getText().toString();
                String id = id_ed.getText().toString();

                if (id.equalsIgnoreCase("")){

                    Toast.makeText(Edit.this, "Preencha o campo ID", Toast.LENGTH_SHORT).show();

                }else{
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("titulo", t1);
                    contentValues.put("texto", t2);

                    bd.update("notas", contentValues, "id = " + id, null);


                    Toast.makeText(Edit.this, "Concluido com sucesso", Toast.LENGTH_SHORT).show();

                    t1 = null;
                    t2 = null;

                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
