package com.example.personalchef.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.personalchef.R;
import com.example.personalchef.databinding.ActivityViewChefsBinding;

public class ViewChefsActivity extends AppCompatActivity {
    ActivityViewChefsBinding binding;

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
    }
}