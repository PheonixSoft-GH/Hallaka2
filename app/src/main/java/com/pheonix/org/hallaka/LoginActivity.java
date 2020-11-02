package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    EditText userName;
    TextInputLayout pass;
    Button signInBtn;
    CheckBox rememberPass;
    Button newUser, forgotPass;
    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.userNameSignIn);
        pass = findViewById(R.id.passwordSignIn);
        signInBtn = findViewById(R.id.signInBtnSignIn);
        rememberPass = findViewById(R.id.rememberPassIdSignIn);
        newUser = findViewById(R.id.newUserIdSignIn);
        forgotPass = findViewById(R.id.forgotPassIdSignIn);
        spinKitView = findViewById(R.id.spin_kit);

        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        newUser.setOnClickListener(this);
        signInBtn.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        rememberPass.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == signInBtn) {

            Login();

        }

        if (v == newUser) {

            startActivity(new Intent(this, SignupActivity.class));
        }

        if (v == forgotPass) {

            startActivity(new Intent(this, ForgotPassActivity.class));
        }
        if (v == rememberPass) {


        }
    }

    public void Login() {
        final String uEmail = userName.getText().toString();
        String uPassword = pass.getEditText().getText().toString();

        if (!TextUtils.isEmpty(uEmail) && !TextUtils.isEmpty(uPassword)) {

            spinKitView.setVisibility(View.VISIBLE);
            if (uEmail.equalsIgnoreCase("admin@hallaka.com")) {

                if (uPassword.equals("1234567890")) {
                    Toast.makeText(this, "Logged in as Admin!", Toast.LENGTH_SHORT).show();
                    spinKitView.setVisibility(View.GONE);
                } else {
                    Toast.makeText(this, "Admin Password not right!", Toast.LENGTH_SHORT).show();
                    spinKitView.setVisibility(View.GONE);
                }

            } else {

                auth.signInWithEmailAndPassword(uEmail, uPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            spinKitView.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            intent.putExtra("data", uEmail);
                            startActivity(intent);

                        } else {
                            spinKitView.setVisibility(View.INVISIBLE);
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }

        } else {
            Toast.makeText(LoginActivity.this, "Enter required values in above fields", Toast.LENGTH_LONG).show();
            spinKitView.setVisibility(View.GONE);
        }

    }
}