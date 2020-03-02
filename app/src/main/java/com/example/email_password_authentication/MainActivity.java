package com.example.email_password_authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private EditText etEmail, etPass;
    private Button btnRegistr,btnEntry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        etEmail = (EditText)findViewById(R.id.email);
        etPass = (EditText) findViewById(R.id.password);

        btnEntry = (Button)findViewById(R.id.entry);
        btnEntry.setOnClickListener(this);

        btnRegistr = (Button)findViewById(R.id.registr);
        btnRegistr.setOnClickListener(this);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){

                }else {

                }
            }
        };

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.registr){
            registration(etEmail.getText().toString(), etPass.getText().toString());
        }else if(v.getId() == R.id.entry){
            signin(etEmail.getText().toString(), etPass.getText().toString());
        }
    }

    public void signin(String email, String pass){
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Authentication success .", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Authentication failed .", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void registration(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Registration success .", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Registration failed .", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

