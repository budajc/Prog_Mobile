package com.example.shared;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences.Editor editor = null;
    private static final String PREFERENCIAS_FILE = "Notas";
    private SharedPreferences preferences;
    private EditText notas;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notas = findViewById(R.id.text_notas);
        resultado = findViewById(R.id.txt_resultado);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("teste", String.valueOf(notas.getText()));
                editor.commit();
                resultado.setText(preferences.getString("teste","Erro ao Recuperar a String"));
            }
        });

        preferences = getSharedPreferences("notas", MODE_PRIVATE);
        editor = preferences.edit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        resultado.setText(preferences.getString("teste","Erro ao Recuperar a String"));
    }
}
