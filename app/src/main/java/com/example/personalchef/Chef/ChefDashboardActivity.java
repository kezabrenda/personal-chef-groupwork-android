package com.example.personalchef.Chef;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.personalchef.Authentication.LoginActivity;
import com.example.personalchef.R;
import com.google.firebase.auth.FirebaseAuth;

public class ChefDashboardActivity extends AppCompatActivity {
    TextView chefNames, chefEmail, chefLocation;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_dashboard2);

        TextView chefNames = findViewById(R.id.chefNames);
        TextView chefEmail = findViewById(R.id.chefEmail);
        TextView chefLocation = findViewById(R.id.chefLocation);
        sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);

        String email = sharedPreferences.getString("EMAIL", "");
        chefEmail.setText(email);

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