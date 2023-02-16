package com.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    /*TextInputEditText etLoginEmail;
    TextInputEditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;

    FirebaseAuth mAuth;*/

    private TextInputEditText signin_email;
    private TextInputEditText signin_pass;
    private Button btnSignin;
    private TextView tvRegisterHere;

    ConstraintLayout bgimage;

    FirebaseAuth mAuth;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ch1),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ch2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ch3),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ch4),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.ch5),3000);

        animationDrawable.setOneShot(false);
        animationDrawable.setEnterFadeDuration(850);
        animationDrawable.setExitFadeDuration(1600);

        bgimage = findViewById(R.id.back2);
        bgimage.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

        signin_email = findViewById(R.id.signin_email);
        signin_pass = findViewById(R.id.signin_pass);
        tvRegisterHere = findViewById(R.id.tvSignupHere);
        btnSignin = findViewById(R.id.SignIn_button);

        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(SignIn.this, SignUp.class));
        });

        signin_email = (TextInputEditText) findViewById(R.id.signin_email);
        signin_pass = (TextInputEditText) findViewById(R.id.signin_pass);
        btnSignin = (Button) findViewById(R.id.SignIn_button);

        btnSignin.setOnClickListener(view -> {
            //loginUser();
            if(signin_email.getText().length()>0 && signin_pass.getText().length()>0){
                loginUser(signin_email.getText().toString(),signin_pass.getText().toString());
            }
            else{
                Toast.makeText(getApplicationContext(),"Give correct email and password",Toast.LENGTH_SHORT).show();
            }
        });

        tvRegisterHere = findViewById(R.id.tvSignupHere);
        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(SignIn.this, SignUp.class));
        });
    }

    private void loginUser(String email, String password)
    {
        Toast.makeText(getApplicationContext(),"YOOOOOOOOOO!",Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(email)){
            signin_email.setError("Email cannot be empty");
            signin_email.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            signin_pass.setError("Password cannot be empty");
            signin_pass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignIn.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignIn.this, Home.class));
                    }else{
                        Toast.makeText(SignIn.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void loginUser(){
        String email = signin_email.getText().toString();
        String password = signin_pass.getText().toString();

        if (TextUtils.isEmpty(email)){
            signin_email.setError("Email cannot be empty");
            signin_email.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            signin_pass.setError("Password cannot be empty");
            signin_pass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignIn.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignIn.this, Home.class));
                    }else{
                        Toast.makeText(SignIn.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}