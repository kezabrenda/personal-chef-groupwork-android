package com.example.personalchef.cuisines;

import androidx.appcompat.app.AppCompatActivity;

public class ViewCuisines extends AppCompatActivity {
    public String meals, specialisedChef, viewDirections;
    public int imageId;

    public ViewCuisines(String meals, int imageId) {
        this.meals = meals;
        this.imageId = imageId;
    }
}
