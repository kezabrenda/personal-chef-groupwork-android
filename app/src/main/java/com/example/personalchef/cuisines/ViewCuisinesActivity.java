package com.example.personalchef.cuisines;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.personalchef.R;
import com.example.personalchef.booking.BookingActivity;
import com.example.personalchef.databinding.ActivityViewChefsBinding;
import com.example.personalchef.databinding.ActivityViewCuisinesBinding;

public class ViewCuisinesActivity extends AppCompatActivity {
    ActivityViewCuisinesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewCuisinesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null) {
            String meals = intent.getStringExtra("meals");
            int imageId = intent.getIntExtra("imageId", R.drawable.african_cuisines);

            binding.meals.setText(meals);
            binding.cuisineImage2.setImageResource(imageId);
        }

    }
}