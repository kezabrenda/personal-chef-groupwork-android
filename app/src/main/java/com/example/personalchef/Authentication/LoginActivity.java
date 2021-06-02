package com.example.personalchef.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalchef.Admin.AdminDashboardActivity;
import com.example.personalchef.Chef.ChefDashboardActivity;
import com.example.personalchef.R;
import com.example.personalchef.UI.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button loginBtn,gotoRegister;
    boolean valid = true;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = firebaseFirestore.getInstance();

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        //gotoRegister = findViewById(R.id.gotoRegister);

        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(email);
                checkField(password);
                Log.d("TAG", "onclick:" + email.getText().toString());

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("EMAIL", String.valueOf(email));
                editor.putString("PASS", String.valueOf(password));
                editor.apply();
                Toast.makeText(LoginActivity.this, "information saved!", Toast.LENGTH_SHORT).show();

                if(valid) {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),
                            password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "LoggedIn Successfully", Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        /*gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });*/
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(uid);
        //extract data from document
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("Tag", "onSuccess" + documentSnapshot.getData());
                //Identify user access level
                if (documentSnapshot.getString("isAdmin") != null){
                    //user is admin
                    startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("isChef") != null){
                    startActivity(new Intent(getApplicationContext(), ChefDashboardActivity.class));
                    finish();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Failed To LoggingIn", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
         DocumentReference documentReference = FirebaseFirestore.getInstance()
                 .collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

         documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
             @Override
             public void onSuccess(DocumentSnapshot documentSnapshot) {
                 if (documentSnapshot.getString("isAdmin") != null){
                     startActivity(new Intent(getApplicationContext(), AdminDashboardActivity.class));
                     finish();
                 }
                 if (documentSnapshot.getString("isChef") != null){
                     startActivity(new Intent(getApplicationContext(), ChefDashboardActivity.class));
                     finish();
                 }
             }
         })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         FirebaseAuth.getInstance().signOut();
                         startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                         finish();
                     }
                 });
        }
    }
}