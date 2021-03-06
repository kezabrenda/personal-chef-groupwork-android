package com.example.personalchef.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalchef.Chef.ChefViewProfileActivity;
import com.example.personalchef.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText fullName,email,password,phone;
    Button registerBtn,goToLogin;
    boolean valid = true;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    CheckBox isChefBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = firebaseFirestore.getInstance();

        fullName = findViewById(R.id.registerName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        phone = findViewById(R.id.registerPhone);
        registerBtn = findViewById(R.id.registerBtn);
        goToLogin = findViewById(R.id.gotoLogin);

        Button isAdminBox = findViewById(R.id.isAdmin);
        isChefBox = findViewById(R.id.isChef);

        /*//checkboxes logic
        isChefBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()){
                    isAdminBox.setChecked(false);
                }
            }
        });

        isAdminBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()){
                    isChefBox.setChecked(false);
                }
            }
        });*/

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(fullName);
                checkField(email);
                checkField(password);
                checkField(phone);

                /*if (!(isAdminBox.isChecked() || isChefBox.isChecked())) {
                    Toast.makeText(RegisterActivity.this, "Select The Account Type", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                if (valid) {
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                                    DocumentReference documentReference = firebaseFirestore.collection("Users").document(user.getUid());
                                    Map<String,Object> userInfo = new HashMap<>();
                                    userInfo.put("FullName", fullName.getText().toString());
                                    userInfo.put("UserEmail", email.getText().toString());
                                    userInfo.put("PhoneNumber", phone.getText().toString());

                                    //specify if the user is admin
                                    if (isChefBox.isChecked()){
                                        userInfo.put("isChef", "1");
                                    }
                                    documentReference.set(userInfo);

                                    if (isChefBox.isChecked()) {
                                        startActivity(new Intent(getApplicationContext(), ChefViewProfileActivity.class));
                                        finish();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Failed To Create Account", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        isAdminBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }

        return valid;
    }
}