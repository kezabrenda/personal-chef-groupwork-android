package com.example.personalchef.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.personalchef.R;
import com.example.personalchef.booking.BookingActivity;
import com.example.personalchef.booking.BookingActivity2;
import com.example.personalchef.databinding.ActivityViewChefsBinding;

public class ViewChefsActivity extends AppCompatActivity {
    ActivityViewChefsBinding binding;
    Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewChefsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null) {
            String chefNames = intent.getStringExtra("name");
            String chefSpeciality = intent.getStringExtra("specialty");
            String hourlyRate = intent.getStringExtra("hourlyRate");
            int imageId = intent.getIntExtra("imageId", R.drawable.chef002);

            binding.chefNames.setText(chefNames);
            binding.chefSpecialty.setText(chefSpeciality);
            binding.hourlyRate.setText(hourlyRate);
            binding.chefImage1.setImageResource(imageId);
        }
        bookButton = findViewById(R.id.bookButton);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), BookingActivity2.class));
            }
        });
    }
}