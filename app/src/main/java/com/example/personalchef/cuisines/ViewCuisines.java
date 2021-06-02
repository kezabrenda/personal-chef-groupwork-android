package com.example.personalchef.cuisines;

import androidx.appcompat.app.AppCompatActivity;

public class ViewCuisines extends AppCompatActivity {
    public String meals, specialisedChef, viewDirections;
    public int imageId1;

    public ViewCuisines(String meals, int imageId1) {
        this.meals = meals;
        this.imageId1 = imageId1;
    }
}
