package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.pheonix.org.hallaka.Models.UserDataModel;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;
    EditText userName, email, phoneNumber, address;
    TextInputLayout password;
    RadioGroup radioGroup;
    Button signUpBtn;
    TextView alreadyHaveAccount;
    SpinKitView spinKitView;
    String uName, uEmail, uPhone, uAddress, uPass, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = findViewById(R.id.userNameSignUp);
        email = findViewById(R.id.emailSignUp);
        phoneNumber = findViewById(R.id.numberSignUp);
        address = findViewById(R.id.addressSignUp);
        password = findViewById(R.id.passwordSignUp);
        signUpBtn = findViewById(R.id.signUpBtnSignUp);
        alreadyHaveAccount = findViewById(R.id.haveAccountSignUp);
        spinKitView = findViewById(R.id.spin_kit);
        radioGroup = findViewById(R.id.typeLaySignUp);

        FirebaseApp.initializeApp(this);

        auth = FirebaseAuth.getInstance();

        signUpBtn.setOnClickListener(this);
        alreadyHaveAccount.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == signUpBtn) {

            uName = userName.getText().toString();
            uEmail = email.getText().toString();
            uPhone = phoneNumber.getText().toString();
            uAddress = address.getText().toString();
            uPass = password.getEditText().getText().toString();
            type = getSelectedRadioButtonText();

            if (!uName.isEmpty()) {
                if (!uEmail.isEmpty()) {
                    if (isEmailValid(uEmail)) {
                        if (uPhone.length() == 11) {
                            if (!uAddress.isEmpty()) {
                                if (uPass.length() >= 6) {
                                    progressVisible();
                                    auth.createUserWithEmailAndPassword(uEmail, uPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {

                                                progressGone();
                                                String uid = auth.getUid();
                                                UserDataModel sd = new UserDataModel(uName, uPass, uEmail, uPhone, uAddress, uid, type);
                                                FirebaseDatabase.getInstance().getReference("users").child("profiles").child(Objects.requireNonNull(uid)).setValue(sd).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            auth.signOut();
                                                            Toast.makeText(SignupActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                                                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                                            startActivity(intent);
                                                        } else {
                                                            Toast.makeText(SignupActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(SignupActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                                progressGone();
                                            }
                                        }
                                    });

                                } else {
                                    Toast.makeText(this, "Password cannot be smaller than six digits", Toast.LENGTH_SHORT).show();
                                    progressGone();
                                }
                            } else {
                                Toast.makeText(this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
                                progressGone();
                            }
                        } else {
                            Toast.makeText(this, "Phone Number format not right", Toast.LENGTH_SHORT).show();
                            progressGone();
                        }

                    } else {
                        Toast.makeText(this, "Email format is not right", Toast.LENGTH_SHORT).show();
                        progressGone();
                    }
                } else {
                    Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    progressGone();
                }
            } else {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                progressGone();
            }

        }
        if (v == alreadyHaveAccount) {

            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void progressGone() {
        spinKitView.setVisibility(View.GONE);
    }

    private void progressVisible() {
        spinKitView.setVisibility(View.VISIBLE);
    }

    private String getSelectedRadioButtonText() {
        int checkedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedId);
        return radioButton.getText().toString();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}