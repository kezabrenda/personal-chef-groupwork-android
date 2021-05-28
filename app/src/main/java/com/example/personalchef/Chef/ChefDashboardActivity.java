package com.example.personalchef.Chef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalchef.Authentication.LoginActivity;
import com.example.personalchef.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class ChefDashboardActivity extends AppCompatActivity {
    TextView chefNames, chefEmail, chefLocation;
    SharedPreferences sharedPreferences;
    FirebaseFirestore firebaseFirestore;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private static final String USERS = "Users";
    FirebaseUser firebaseUser;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_dashboard2);

        Intent intent = getIntent();
        String email = intent.getStringExtra("UserEmail");
        firebaseFirestore = FirebaseFirestore.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(USERS);
        //firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //userId = firebaseUser.getUid();
        chefNames = findViewById(R.id.chefNames);
        chefEmail = findViewById(R.id.chefEmail);
        chefLocation = findViewById(R.id.chefLocation);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.child("UserEmail").getValue().equals(email)) {
                        chefNames.setText(dataSnapshot.child("FullName").getValue(String.class));
                        chefEmail.setText(email);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
                Toast.makeText(ChefDashboardActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
       /*sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);
        String email = sharedPreferences.getString("EMAIL", "");
        chefEmail.setText(email);*/

        TextView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }
}