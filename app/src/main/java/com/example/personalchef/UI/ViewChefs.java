package com.example.personalchef.UI;

import androidx.appcompat.app.AppCompatActivity;

public class ViewChefs extends AppCompatActivity {
    public int imageId;
    public String names, foodSpecialty, hourlyRate;

    public ViewChefs(String names, String foodSpecialty, String hourlyRate, int imageId) {
        this.names = names;
        this.foodSpecialty = foodSpecialty;
        this.hourlyRate = hourlyRate;
        this.imageId = imageId;
    }
}