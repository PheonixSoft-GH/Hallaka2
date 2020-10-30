package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    Button sendPass, back;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailForgotPass);
        sendPass = findViewById(R.id.sendForgotPass);
        back = findViewById(R.id.backForgotPass);

        sendPass.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == sendPass) {

            String uEmail = email.getText().toString();
            if (!uEmail.isEmpty()) {
                auth.sendPasswordResetEmail(uEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            new AlertDialog.Builder(ForgotPassActivity.this).setTitle("Note").setMessage("Password reset link is sent to you on your email. Follow the instructions to Login.").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    signInIntent();
                                }
                            }).setCancelable(false).show();
                        } else {
                            String m = task.getException().getMessage();
                            Toast.makeText(ForgotPassActivity.this, m, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } else {
                Toast.makeText(this, "Please enter email first", Toast.LENGTH_SHORT).show();
            }

        }

        if (v == back) {

            Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void signInIntent() {
        Intent intent = new Intent(ForgotPassActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}