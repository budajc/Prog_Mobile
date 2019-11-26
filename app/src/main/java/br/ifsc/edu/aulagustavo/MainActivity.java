package br.ifsc.edu.aulagustavo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {




    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();




            String email = "spenglerback86@gmail.com";
            String password = "admin1234admin";
// mAuth.createUserWithEmailAndPassword(email,password);
//
//        Toast.makeText(MainActivity.this,"Deu certo",Toast.LENGTH_LONG).show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Deu certo",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"Não deu certo",Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}
